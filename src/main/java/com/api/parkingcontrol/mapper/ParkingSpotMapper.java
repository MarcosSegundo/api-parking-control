package com.api.parkingcontrol.mapper;

import com.api.parkingcontrol.dto.ParkingSpotDTO;
import com.api.parkingcontrol.entities.ParkingSpot;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ParkingSpotMapper {

    ParkingSpotMapper INSTANCE = Mappers.getMapper(ParkingSpotMapper.class);

    ParkingSpot toModel(ParkingSpotDTO parkingSpotDTO);
    ParkingSpotDTO toDTO(ParkingSpot parkingSpot);
}
