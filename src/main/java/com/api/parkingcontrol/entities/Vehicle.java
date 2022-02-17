package com.api.parkingcontrol.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    private UUID id;
    private String plate;
    private String brand;
    private String model;
    private String color;
}
