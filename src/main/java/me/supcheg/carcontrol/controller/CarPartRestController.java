package me.supcheg.carcontrol.controller;

import lombok.AllArgsConstructor;
import me.supcheg.carcontrol.entity.CarPart;
import me.supcheg.carcontrol.repository.CarPartRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/car_part")
@AllArgsConstructor
public class CarPartRestController {
    private final CarPartRepository repository;

    @GetMapping("list")
    public List<CarPart> list() {
        return repository.findAll();
    }

    @GetMapping("find_by_id")
    public Optional<CarPart> findById(@RequestParam UUID uniqueId) {
        return repository.findById(uniqueId);
    }

    @GetMapping("find_by_name")
    public List<CarPart> findByName(@RequestParam String name) {
        return repository.findAllByName(name);
    }

    @GetMapping("find_by_type")
    public List<CarPart> findByType(@RequestParam String type) {
        return repository.findAllByTypeKey(type);
    }
}
