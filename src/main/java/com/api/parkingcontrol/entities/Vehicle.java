package com.api.parkingcontrol.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true, length = 7)
    private String plate;

    @Column(nullable = false, length = 70)
    private String brand;

    @Column(nullable = false, length = 70)
    private String model;

    @Column(nullable = false, length = 70)
    private String color;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return id.equals(vehicle.id) && plate.equals(vehicle.plate) && brand.equals(vehicle.brand) && model.equals(vehicle.model) && color.equals(vehicle.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, plate, brand, model, color);
    }
}
