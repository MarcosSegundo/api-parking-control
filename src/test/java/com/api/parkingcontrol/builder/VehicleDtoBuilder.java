package com.api.parkingcontrol.builder;

import com.api.parkingcontrol.dto.VehicleDTO;
import lombok.Builder;

import java.util.UUID;

@Builder
public class VehicleDtoBuilder {

    @Builder.Default
    private UUID id = UUID.fromString("ee9d4ec5-648d-4423-99dd-46db65a2bb7e");

    @Builder.Default
    private String plate = "QFG3080";

    @Builder.Default
    private String brand = "chevrolet";

    @Builder.Default
    private String model = "prisma";

    @Builder.Default
    private String color = "black";

    public VehicleDTO toVehicleDTO() {

        return VehicleDTO.builder()
                .id(this.id)
                .plate(this.plate)
                .brand(brand)
                .model(this.model)
                .color(this.color)
                .build();
    }
}
