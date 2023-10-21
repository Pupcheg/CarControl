package me.supcheg.carcontrol;

import me.supcheg.carcontrol.entity.Car;
import me.supcheg.carcontrol.entity.CarPart;
import me.supcheg.carcontrol.entity.CarPartType;
import me.supcheg.carcontrol.repository.CarPartRepository;
import me.supcheg.carcontrol.repository.CarPartTypeRepository;
import me.supcheg.carcontrol.repository.CarRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class TestDataSource {
    public TestDataSource(CarPartTypeRepository partTypeRepository, CarPartRepository partRepository, CarRepository carRepository) {
        partTypeRepository.saveAll(List.of(
                new CarPartType("candles", "Свечи", "*url*"),
                new CarPartType("oil", "Масло", "*url*"),
                new CarPartType("gdm", "ГРМ", "*url*"),
                new CarPartType("accumulator", "Аккумулятор", "*url*"),
                new CarPartType("shock_absorbers", "Амортизаторы", "*url*"),
                new CarPartType("brake_discs", "Тормозные диски", "*url*")
        ));
        List<CarPart> parts = List.of(
                new CarPart(UUID.randomUUID(), "Свечи KX-34", "candles", 15_000, 365 * 2),
                new CarPart(UUID.randomUUID(), "Масло FA-594", "oil", 1_000, 90),
                new CarPart(UUID.randomUUID(), "ГРМ UOG78", "gdm", 100_000, 365 * 5),
                new CarPart(UUID.randomUUID(), "Аккумулятор 5000", "accumulator", 1_000_000, 365 / 2),
                new CarPart(UUID.randomUUID(), "Амортизаторы WD-BLUE", "shock_absorbers", 10_000, 365),
                new CarPart(UUID.randomUUID(), "Тормозные диски", "brake_discs", 1_000, 365 / 2)
        );
        partRepository.saveAll(parts);

        carRepository.save(new Car(UUID.randomUUID(), "Пятёра", "passenger_car", parts.stream().map(CarPart::getUniqueId).toList()));
    }
}
