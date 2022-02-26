package com.api.parkingcontrol.dto;

import com.api.parkingcontrol.validation.constraints.ExistsParkingSpotNumber;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkingSpotDTO {

    private UUID id;

    @NotBlank(message = "the field shouldn't null or empty.")
    @Size(min = 1, max = 10)
    @ExistsParkingSpotNumber
    private String parkingSpotNumber;

    @Valid
    private VehicleDTO vehicle;

    @NotBlank(message = "the field shouldn't null or empty.")
    @Size(max = 70)
    private String responsibleName;

    @NotBlank(message = "the field shouldn't null or empty.")
    @Size(max = 10)
    private String apartment;

    @NotBlank(message = "the field shouldn't null or empty.")
    @Size(max = 10)
    private String block;
}
