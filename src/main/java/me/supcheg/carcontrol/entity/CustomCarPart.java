package me.supcheg.carcontrol.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "CUSTOM_CAR_PART")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomCarPart {
    @Id
    @Column(nullable = false)
    @JsonProperty("unique_id")
    private UUID uniqueId;

    @Column(nullable = false)
    @JsonProperty("owner_unique_id")
    private UUID ownerUniqueId;

    @Column
    @JsonProperty("parent_unique_id")
    private UUID parentUniqueId;

    @Column(nullable = false)
    @JsonProperty("name")
    private String name;

    @Column(nullable = false)
    @JsonProperty
    private String typeKey;

    @Column(nullable = false)
    @JsonProperty("original_strength_in_km")
    private int originalStrengthInKilometers;

    @Column(nullable = false)
    @JsonProperty("left_strength_in_km")
    private int leftStrengthInKilometers;

    @Column(nullable = false)
    @JsonProperty("installation_date")
    private Date installationDate;

    @Column(nullable = false)
    @JsonProperty("strength_expire_date")
    private Date strengthExpireDate;
}
