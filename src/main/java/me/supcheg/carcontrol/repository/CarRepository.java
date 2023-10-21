package me.supcheg.carcontrol.repository;

import me.supcheg.carcontrol.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CarRepository extends JpaRepository<Car, UUID> {
    List<Car> findAllByName(String name);

    List<Car> findAllByTypeKey(String typeKey);
}
