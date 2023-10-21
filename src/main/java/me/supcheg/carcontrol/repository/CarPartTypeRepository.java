package me.supcheg.carcontrol.repository;

import me.supcheg.carcontrol.entity.CarPartType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CarPartTypeRepository extends JpaRepository<CarPartType, UUID> {
}
