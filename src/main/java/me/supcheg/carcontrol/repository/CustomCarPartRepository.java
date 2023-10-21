package me.supcheg.carcontrol.repository;

import me.supcheg.carcontrol.entity.CustomCarPart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CustomCarPartRepository extends JpaRepository<CustomCarPart, UUID> {
    List<CustomCarPart> findAllByOwnerUniqueId(UUID uniqueId);

    List<CustomCarPart> findAllByParentUniqueId(UUID uniqueId);

    List<CustomCarPart> findAllByName(String name);

    List<CustomCarPart> findAllByTypeKey(String type);
}
