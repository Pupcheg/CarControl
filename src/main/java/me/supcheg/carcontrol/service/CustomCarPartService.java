package me.supcheg.carcontrol.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import me.supcheg.carcontrol.auth.TokenAuthenticationFilter;
import me.supcheg.carcontrol.entity.CarPart;
import me.supcheg.carcontrol.entity.CustomCarPart;
import me.supcheg.carcontrol.repository.CustomCarPartRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class CustomCarPartService {
    private final CarPartService carPartService;
    private final CustomCarPartRepository repository;

    public CustomCarPart getById(UUID uniqueId) {
        return repository.findById(uniqueId)
                .orElseThrow(() -> new EntityNotFoundException("CustomCarPart with uniqueId=" + uniqueId + " not found"));
    }

    public List<UUID> mapCarPartsToCustomCarParts(List<UUID> list) {
        return list.stream().map(this::createFromCarPart)
                .map(CustomCarPart::getUniqueId)
                .toList();
    }

    public CustomCarPart createFromCarPart(@RequestParam UUID uniqueId) {
        CarPart carPart = carPartService.getById(uniqueId);
        Date now = new Date();

        return repository.save(new CustomCarPart(
                UUID.randomUUID(),
                TokenAuthenticationFilter.getUserUniqueId(),
                carPart.getUniqueId(),
                carPart.getName(),
                carPart.getTypeKey(),
                carPart.getStrengthInKilometers(),
                carPart.getStrengthInKilometers(),
                now,
                new Date(now.getTime() + TimeUnit.DAYS.toMillis(carPart.getStrengthInDays()))
        ));
    }

    public CustomCarPart createNew(String name, String typeKey,
                                   int originalStrengthInKilometers, int leftStrengthInKilometers,
                                   Date installationDate, Date strengthExpireDate) {
        return repository.save(new CustomCarPart(
                UUID.randomUUID(),
                TokenAuthenticationFilter.getUserUniqueId(),
                null,
                name,
                typeKey,
                originalStrengthInKilometers,
                leftStrengthInKilometers,
                installationDate,
                strengthExpireDate
        ));
    }

    public CustomCarPart modify(UUID uniqueId, String name, String typeKey,
                                Integer originalStrengthInKilometers, Integer leftStrengthInKilometers,
                                Date installationDate, Date strengthExpireDate) {
        CustomCarPart customCarPart = getById(uniqueId);

        if (!customCarPart.getOwnerUniqueId().equals(TokenAuthenticationFilter.getUserUniqueId())) {
            throw new AccessDeniedException("user");
        }

        if (name != null) {
            customCarPart.setName(name);
        }
        if (typeKey != null) {
            customCarPart.setTypeKey(typeKey);
        }
        if (originalStrengthInKilometers != null) {
            customCarPart.setOriginalStrengthInKilometers(originalStrengthInKilometers);
        }
        if (leftStrengthInKilometers != null) {
            customCarPart.setLeftStrengthInKilometers(leftStrengthInKilometers);
        }
        if (installationDate != null) {
            customCarPart.setInstallationDate(installationDate);
        }
        if (strengthExpireDate != null) {
            customCarPart.setStrengthExpireDate(strengthExpireDate);
        }

        return repository.save(customCarPart);
    }
}
