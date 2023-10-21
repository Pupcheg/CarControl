package me.supcheg.carcontrol.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CAR_PART_TYPE")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CarPartType {
    @Id
    @Column(nullable = false)
    @JsonProperty("type_key")
    private String typeKey;

    @Column(nullable = false)
    @JsonProperty("name")
    private String name;

    @Column(nullable = false)
    @JsonProperty("icon_url")
    private String iconUrl;
}
