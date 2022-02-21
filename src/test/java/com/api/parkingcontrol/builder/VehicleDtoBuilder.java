package com.api.parkingcontrol.builder;

import com.api.parkingcontrol.dto.VehicleDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true, builderClassName = "Builder")
public class VehicleDtoBuilder {
    private UUID id = UUID.fromString("ee9d4ec5-648d-4423-99dd-46db65a2bb7e");

    private String plate = "QFG3080";

    private String brand = "chevrolet";

    private String model = "prisma";

    private String color = "black";

    public VehicleDTO toVehicleDTO() {

        return VehicleDTO.builder()
                .id(this.id)
                .plate(this.plate)
                .model(this.model)
                .color(this.color)
                .build();
    }
}
