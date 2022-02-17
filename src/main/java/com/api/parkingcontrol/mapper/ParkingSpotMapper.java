package com.api.parkingcontrol.mapper;

import com.api.parkingcontrol.dto.ParkingSpotDTO;
import com.api.parkingcontrol.entities.ParkingSpot;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ParkingSpotMapper {

    ParkingSpotMapper INSTANCE = Mappers.getMapper(ParkingSpotMapper.class);

    @Mapping(target = "registrationDate", source = "registrationDate", dateFormat = "yyyy-MM-dd HH:mm")
    ParkingSpot toModel(ParkingSpotDTO parkingSpotDTO);

    @Mapping(target = "registrationDate", source = "registrationDate", dateFormat = "yyyy-MM-dd HH:mm")
    ParkingSpotDTO toDTO(ParkingSpot parkingSpot);
}
