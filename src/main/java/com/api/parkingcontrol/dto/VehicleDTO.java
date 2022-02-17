package com.api.parkingcontrol.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@Builder
public class VehicleDTO {

    private UUID id;

    @NotEmpty
    @Size(min = 7, max = 7)
    private String plate;

    @NotEmpty
    @Size(max = 70)
    private String brand;

    @NotEmpty
    @Size(max = 70)
    private String model;

    @NotEmpty
    @Size(min = 3, max = 70)
    private String color;
}
