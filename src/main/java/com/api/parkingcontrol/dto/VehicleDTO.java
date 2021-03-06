package com.api.parkingcontrol.dto;

import com.api.parkingcontrol.validation.constraints.ExistsPlate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDTO {

    private UUID id;

    @NotBlank(message = "the field shouldn't null or empty.")
    @Pattern(regexp = "[a-zA-Z]{3}[0-9][A-Za-z0-9][0-9]{2}", message = "the plate is invalid")
    @ExistsPlate
    private String plate;

    @NotBlank(message = "the field shouldn't null or empty.")
    @Size(max = 15)
    private String brand;

    @NotBlank(message = "the field shouldn't null or empty.")
    @Size(max = 30)
    private String model;

    @NotBlank(message = "the field shouldn't null or empty.")
    @Size(max = 30)
    @Pattern(regexp = "[a-zA-Z]+", message = "the color is must have only letters")
    private String color;
}
