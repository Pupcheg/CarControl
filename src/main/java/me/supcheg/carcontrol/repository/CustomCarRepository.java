package me.supcheg.carcontrol.repository;

import me.supcheg.carcontrol.entity.CustomCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CustomCarRepository extends JpaRepository<CustomCar, UUID> {
    List<CustomCar> findAllByName(String name);

    List<CustomCar> findAllByOwnerUniqueId(UUID uniqueId);

    List<CustomCar> findAllByParentUniqueId(UUID uniqueId);

    List<CustomCar> findAllByTypeKey(String type);
}
