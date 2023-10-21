package me.supcheg.carcontrol.repository;

import me.supcheg.carcontrol.entity.CarPart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CarPartRepository extends JpaRepository<CarPart, UUID> {
    List<CarPart> findAllByName(String name);

    List<CarPart> findAllByTypeKey(String typeKey);
}
