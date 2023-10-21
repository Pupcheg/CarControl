package me.supcheg.carcontrol.controller;

import lombok.AllArgsConstructor;
import me.supcheg.carcontrol.entity.CustomCar;
import me.supcheg.carcontrol.repository.CustomCarRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/custom_car")
@AllArgsConstructor
public class CustomCarRestController {
    private final CustomCarRepository repository;

    @GetMapping("find_by_id")
    public Optional<CustomCar> findById(@RequestParam UUID uniqueId) {
        return repository.findById(uniqueId);
    }

    @GetMapping("find_by_name")
    public List<CustomCar> findByName(@RequestParam String name) {
        return repository.findAllByName(name);
    }

    @GetMapping("find_by_owner")
    public List<CustomCar> findWithUser(@RequestParam UUID uniqueId) {
        return repository.findAllByOwnerUniqueId(uniqueId);
    }

    @GetMapping("find_by_parent")
    public List<CustomCar> findWithParent(@RequestParam UUID uniqueId) {
        return repository.findAllByParentUniqueId(uniqueId);
    }

    @GetMapping("find_by_type")
    public List<CustomCar> findWithType(@RequestParam String type) {
        return repository.findAllByTypeKey(type);
    }
}
