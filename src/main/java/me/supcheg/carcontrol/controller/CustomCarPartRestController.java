package me.supcheg.carcontrol.controller;

import lombok.RequiredArgsConstructor;
import me.supcheg.carcontrol.entity.CustomCarPart;
import me.supcheg.carcontrol.repository.CustomCarPartRepository;
import me.supcheg.carcontrol.service.CustomCarPartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/custom_car_part")
@RequiredArgsConstructor
public class CustomCarPartRestController {
    private final CustomCarPartRepository customCarPartRepository;
    private final CustomCarPartService service;

    @GetMapping("find_by_id")
    public CustomCarPart findById(@RequestParam UUID uniqueId) {
        return service.getById(uniqueId);
    }

    @GetMapping("find_by_name")
    public List<CustomCarPart> findByName(@RequestParam String name) {
        return customCarPartRepository.findAllByName(name);
    }

    @GetMapping("find_by_type")
    public List<CustomCarPart> findByType(@RequestParam String type) {
        return customCarPartRepository.findAllByTypeKey(type);
    }

    @GetMapping("find_by_parent")
    public List<CustomCarPart> findByParent(@RequestParam UUID uniqueId) {
        return customCarPartRepository.findAllByParentUniqueId(uniqueId);
    }

    @GetMapping("find_by_owner")
    public List<CustomCarPart> findByOwner(@RequestParam UUID uniqueId) {
        return customCarPartRepository.findAllByOwnerUniqueId(uniqueId);
    }


    @GetMapping("create_from_car_part")
    public CustomCarPart createFromCarPart(@RequestParam UUID uniqueId) {
        return service.createFromCarPart(uniqueId);
    }

    @GetMapping("create_new")
    public CustomCarPart createNew(@RequestParam String name,
                                   @RequestParam("type_key") String typeKey,
                                   @RequestParam("original_strength_in_km") int originalStrengthInKilometers,
                                   @RequestParam("left_strength_in_km") int leftStrengthInKilometers,
                                   @RequestParam("installation_date") Date installationDate,
                                   @RequestParam("strength_expire_date") Date strengthExpireDate) {
        return service.createNew(name, typeKey, originalStrengthInKilometers, leftStrengthInKilometers, installationDate, strengthExpireDate);
    }

    @GetMapping("modify")
    public CustomCarPart modify(@RequestParam UUID uniqueId,
                                @RequestParam(required = false) String name,
                                @RequestParam(name = "type_key", required = false) String typeKey,
                                @RequestParam(name = "original_strength_in_km", required = false) Integer originalStrengthInKilometers,
                                @RequestParam(name = "left_strength_in_km", required = false) Integer leftStrengthInKilometers,
                                @RequestParam(name = "installation_date", required = false) Date installationDate,
                                @RequestParam(name = "strength_expire_date", required = false) Date strengthExpireDate) {
        return service.modify(uniqueId, name, typeKey, originalStrengthInKilometers, leftStrengthInKilometers, installationDate, strengthExpireDate);
    }

    @GetMapping("remove")
    public ResponseEntity<?> remove(@RequestParam UUID uniqueId) {
        return service.remove(uniqueId);
    }
}

