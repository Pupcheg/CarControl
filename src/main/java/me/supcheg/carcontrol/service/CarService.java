package me.supcheg.carcontrol.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import me.supcheg.carcontrol.entity.Car;
import me.supcheg.carcontrol.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository repository;

    public Car getById(UUID uniqueId) {
        return repository.findById(uniqueId)
                .orElseThrow(() -> new EntityNotFoundException("Car with uniqueId=" + uniqueId + " not found"));
    }
}
