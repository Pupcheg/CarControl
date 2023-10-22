package me.supcheg.carcontrol.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import me.supcheg.carcontrol.auth.TokenAuthenticationFilter;
import me.supcheg.carcontrol.entity.Car;
import me.supcheg.carcontrol.entity.CustomCar;
import me.supcheg.carcontrol.repository.CustomCarRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomCarService {
    private final CarService carService;
    private final CustomCarPartService carPartService;
    private final CustomCarRepository repository;

    public CustomCar getById(UUID uniqueId) {
        return repository.findById(uniqueId)
                .orElseThrow(() -> new EntityNotFoundException("CustomCar with uniqueId=" + uniqueId + " not found"));
    }

    public CustomCar createFromCar(UUID uniqueId) {
        Car car = carService.getById(uniqueId);

        return repository.save(new CustomCar(
                UUID.randomUUID(),
                TokenAuthenticationFilter.getUserUniqueId(),
                car.getUniqueId(),
                car.getName(),
                car.getTypeKey(),
                carPartService.mapCarPartsToCustomCarParts(car.getParts()),
                0
        ));
    }

    public CustomCar createNew(String name, String type,
                               List<UUID> parts, int mileageInKilometers) {
        return repository.save(new CustomCar(
                UUID.randomUUID(),
                TokenAuthenticationFilter.getUserUniqueId(),
                null,
                name,
                type,
                parts,
                mileageInKilometers
        ));
    }

    public CustomCar modify(UUID uniqueId, String name, String type,
                            List<UUID> parts, Integer mileageInKilometers) {
        CustomCar customCar = getById(uniqueId);

        verifyCanEdit(customCar);

        if (name != null) {
            customCar.setName(name);
        }
        if (type != null) {
            customCar.setTypeKey(type);
        }
        if (parts != null) {
            customCar.setParts(parts);
        }
        if (mileageInKilometers != null) {
            customCar.setMileageInKilometers(mileageInKilometers);
        }

        return repository.save(customCar);
    }

    public ResponseEntity<?> remove(UUID uniqueId) {
        CustomCar customCar = getById(uniqueId);
        verifyCanEdit(customCar);
        repository.delete(customCar);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void verifyCanEdit(CustomCar car) {
        UUID userUniqueId = TokenAuthenticationFilter.getUserUniqueId();
        if (!car.getOwnerUniqueId().equals(userUniqueId)) {
            throw new AccessDeniedException("User with uniqueId=" + userUniqueId
                    + " can't edit CustomCar with uniqueId=" + car.getUniqueId());
        }
    }
}
