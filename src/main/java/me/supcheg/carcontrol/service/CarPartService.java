package me.supcheg.carcontrol.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import me.supcheg.carcontrol.entity.CarPart;
import me.supcheg.carcontrol.repository.CarPartRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CarPartService {
    private final CarPartRepository repository;

    public CarPart getById(UUID uniqueId) {
        return repository.findById(uniqueId)
                .orElseThrow(() -> new EntityNotFoundException("CarPart with uniqueId=" + uniqueId + " not found"));
    }
}
