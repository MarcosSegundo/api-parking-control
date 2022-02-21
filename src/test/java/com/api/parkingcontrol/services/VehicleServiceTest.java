package com.api.parkingcontrol.services;

import com.api.parkingcontrol.builder.VehicleDtoBuilder;
import com.api.parkingcontrol.mapper.ParkingSpotMapper;
import com.api.parkingcontrol.repositories.VehicleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VehicleServiceTest {

    @Mock
    private VehicleRepository vehicleRepository;

    @InjectMocks
    private VehicleService vehicleService;

    private final ParkingSpotMapper MAPPER = ParkingSpotMapper.INSTANCE;

    @Test
    public void whenAVehiclePlateInformedAndNotExistsThenReturnFalse() {
        //given
        String informedPlate = VehicleDtoBuilder.builder()
                .build().toVehicleDTO().getPlate();

        //when
        when(vehicleRepository.existsByPlate(informedPlate)).thenReturn(false);

        //then
        boolean savedPlate = vehicleService.existsByPlate(informedPlate);

        assertThat(savedPlate).isFalse();
    }

    @Test
    public void whenAVehiclePlateInformedAndItExistsThenReturnTrue() {
        //given
        String informedPlate = VehicleDtoBuilder.builder()
                .build().toVehicleDTO().getPlate();

        //when
        when(vehicleRepository.existsByPlate(informedPlate)).thenReturn(true);

        //then
        boolean savedPlate = vehicleService.existsByPlate(informedPlate);

        assertThat(savedPlate).isTrue();
    }
}