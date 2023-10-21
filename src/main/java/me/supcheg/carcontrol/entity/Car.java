package me.supcheg.carcontrol.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "CAR")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Car {
    @Id
    @Column(unique = true, nullable = false)
    @JsonProperty("unique_id")
    private UUID uniqueId;

    @Column(nullable = false)
    @JsonProperty("name")
    private String name;

    @Column(nullable = false)
    @JsonProperty("type_key")
    private String typeKey;

    @Column(nullable = false)
    @ElementCollection
    @JsonProperty("parts")
    private List<UUID> parts;
}
