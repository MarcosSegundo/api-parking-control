package com.api.parkingcontrol.builder;

import com.api.parkingcontrol.dto.ParkingSpotDTO;
import com.api.parkingcontrol.dto.VehicleDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true, builderClassName = "Builder")
public class ParkingSpotDtoBuilder {

    private UUID id = UUID.fromString("e0dcc560-06b3-4282-844d-3c12576fdf0f");

    private String parkingSpotNumber = "105A";

    private VehicleDTO vehicle;

    private String responsibleName = "Kássio Kley";

    private String apartment = "105A";

    private String block = "A";

    public ParkingSpotDTO toParkingSpotDTO() {

        return ParkingSpotDTO.builder()
                .id(this.id)
                .parkingSpotNumber(this.parkingSpotNumber)
                .vehicle(VehicleDtoBuilder.builder().build().toVehicleDTO())
                .responsibleName(this.responsibleName)
                .apartment(this.apartment)
                .block(this.block)
                .build();
    }
}
