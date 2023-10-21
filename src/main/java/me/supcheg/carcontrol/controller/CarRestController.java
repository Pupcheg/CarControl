package me.supcheg.carcontrol.controller;

import lombok.AllArgsConstructor;
import me.supcheg.carcontrol.entity.Car;
import me.supcheg.carcontrol.repository.CarRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/car")
@AllArgsConstructor
public class CarRestController {
    private final CarRepository repository;

    @GetMapping("list")
    public List<Car> list() {
        return repository.findAll();
    }

    @GetMapping("find_by_id")
    public Optional<Car> findById(@RequestParam UUID uniqueId) {
        return repository.findById(uniqueId);
    }

    @GetMapping("find_by_name")
    public List<Car> findByName(@RequestParam String name) {
        return repository.findAllByName(name);
    }

    @GetMapping("find_by_type")
    public List<Car> findByType(@RequestParam String type) {
        return repository.findAllByTypeKey(type);
    }
}
