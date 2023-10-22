package me.supcheg.carcontrol.controller;

import lombok.RequiredArgsConstructor;
import me.supcheg.carcontrol.entity.CustomCar;
import me.supcheg.carcontrol.repository.CustomCarRepository;
import me.supcheg.carcontrol.service.CustomCarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/custom_car")
@RequiredArgsConstructor
public class CustomCarRestController {
    private final CustomCarRepository repository;
    private final CustomCarService service;

    @GetMapping("find_by_id")
    public Optional<CustomCar> findById(@RequestParam UUID uniqueId) {
        return repository.findById(uniqueId);
    }

    @GetMapping("find_by_name")
    public List<CustomCar> findByName(@RequestParam String name) {
        return repository.findAllByName(name);
    }

    @GetMapping("find_by_owner")
    public List<CustomCar> findByOwner(@RequestParam UUID uniqueId) {
        return repository.findAllByOwnerUniqueId(uniqueId);
    }

    @GetMapping("find_by_parent")
    public List<CustomCar> findByParent(@RequestParam UUID uniqueId) {
        return repository.findAllByParentUniqueId(uniqueId);
    }

    @GetMapping("find_by_type")
    public List<CustomCar> findByType(@RequestParam String type) {
        return repository.findAllByTypeKey(type);
    }


    @GetMapping("create_from_car")
    public CustomCar createFromCar(@RequestParam UUID uniqueId) {
        return service.createFromCar(uniqueId);
    }

    @GetMapping("create_new")
    public CustomCar createNew(@RequestParam String name,
                               @RequestParam("type_key") String type,
                               @RequestParam("parts") List<UUID> parts,
                               @RequestParam("mileage_in_km") int mileageInKilometers) {
        return service.createNew(name, type, parts, mileageInKilometers);
    }

    @GetMapping("modify")
    public CustomCar modify(@RequestParam UUID uniqueId,
                            @RequestParam(required = false) String name,
                            @RequestParam(name = "type_key", required = false) String type,
                            @RequestParam(name = "parts", required = false) List<UUID> parts,
                            @RequestParam(name = "mileage_in_km", required = false) int mileageInKilometers) {
        return service.modify(uniqueId, name, type, parts, mileageInKilometers);
    }

    @GetMapping("remove")
    public ResponseEntity<?> remove(@RequestParam UUID uniqueId) {
        return service.remove(uniqueId);
    }
}
