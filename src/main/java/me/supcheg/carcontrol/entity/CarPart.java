package me.supcheg.carcontrol.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "car_part")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CarPart {
    @Id
    @Column(nullable = false)
    @JsonProperty("unique_id")
    private UUID uniqueId;

    @Column(nullable = false)
    @JsonProperty("name")
    private String name;

    @Column(nullable = false)
    @JsonProperty("type_key")
    private String typeKey;

    @Column(nullable = false)
    @JsonProperty("strength_in_km")
    private int strengthInKilometers;

    @Column(nullable = false)
    @JsonProperty("strength_in_days")
    private int strengthInDays;
}
