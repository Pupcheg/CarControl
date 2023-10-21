package me.supcheg.carcontrol.controller;

import lombok.RequiredArgsConstructor;
import me.supcheg.carcontrol.entity.CarPart;
import me.supcheg.carcontrol.repository.CarPartRepository;
import me.supcheg.carcontrol.service.CarPartService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/car_part")
@RequiredArgsConstructor
public class CarPartRestController {
    private final CarPartRepository repository;
    private final CarPartService service;

    @GetMapping("list")
    public List<CarPart> list() {
        return repository.findAll();
    }

    @GetMapping("find_by_id")
    public CarPart findById(@RequestParam UUID uniqueId) {
        return service.getById(uniqueId);
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
