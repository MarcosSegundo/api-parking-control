package com.api.parkingcontrol.builder;

import com.api.parkingcontrol.dto.ParkingSpotDTO;
import com.api.parkingcontrol.dto.VehicleDTO;
import lombok.Builder;

import java.util.UUID;

@Builder
public class ParkingSpotDtoBuilder {

    @Builder.Default
    private UUID id = UUID.fromString("e0dcc560-06b3-4282-844d-3c12576fdf0f");

    @Builder.Default
    private String parkingSpotNumber = "105A";

    private VehicleDTO vehicle;

    @Builder.Default
    private String responsibleName = "Kássio Kley";

    @Builder.Default
    private String apartment = "105A";

    @Builder.Default
    private String block = "A";

    public ParkingSpotDTO toParkingSpotDTO() {

        return ParkingSpotDTO.builder()
                .id(id)
                .parkingSpotNumber(parkingSpotNumber)
                .vehicle(VehicleDtoBuilder.builder().build().toVehicleDTO())
                .responsibleName(responsibleName)
                .apartment(apartment)
                .block(block)
                .build();
    }
}
