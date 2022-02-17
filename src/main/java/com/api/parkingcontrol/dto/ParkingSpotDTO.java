package com.api.parkingcontrol.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@Builder
public class ParkingSpotDTO {

    private UUID id;

    @NotEmpty
    @Size(min = 1, max = 10)
    private String parkingSpotNumber;

    @NotEmpty
    private VehicleDTO vehicleDTO;

    @NotEmpty
    private String registrationDate;

    @NotEmpty
    @Size(min = 3, max = 130)
    private String responsibleName;

    @NotEmpty
    @Size(max = 30)
    private String apartment;

    @NotEmpty
    @Size(max = 30)
    private String block;
}