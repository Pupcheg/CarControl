package me.supcheg.carcontrol.controller;

import lombok.AllArgsConstructor;
import me.supcheg.carcontrol.entity.CustomCarPart;
import me.supcheg.carcontrol.repository.CustomCarPartRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/custom_car_part")
@AllArgsConstructor
public class CustomCarPartRestController {
    private final CustomCarPartRepository repository;

    @GetMapping("find_by_id")
    public Optional<CustomCarPart> findById(@RequestParam UUID uniqueId) {
        return repository.findById(uniqueId);
    }

    @GetMapping("find_by_name")
    public List<CustomCarPart> findByName(@RequestParam String name) {
        return repository.findAllByName(name);
    }

    @GetMapping("find_by_type")
    public List<CustomCarPart> findByType(@RequestParam String type) {
        return repository.findAllByTypeKey(type);
    }

    @GetMapping("find_by_parent")
    public List<CustomCarPart> findByParent(@RequestParam UUID uniqueId) {
        return repository.findAllByParentUniqueId(uniqueId);
    }

    @GetMapping("find_by_owner")
    public List<CustomCarPart> findByOwner(@RequestParam UUID uniqueId) {
        return repository.findAllByOwnerUniqueId(uniqueId);
    }
}
