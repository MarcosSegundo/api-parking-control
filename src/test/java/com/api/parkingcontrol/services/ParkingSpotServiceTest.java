package com.api.parkingcontrol.services;

import com.api.parkingcontrol.builder.ParkingSpotDtoBuilder;
import com.api.parkingcontrol.dto.ParkingSpotDTO;
import com.api.parkingcontrol.entities.ParkingSpot;
import com.api.parkingcontrol.mapper.ParkingSpotMapper;
import com.api.parkingcontrol.repositories.ParkingSpotRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ParkingSpotServiceTest {

    @Mock
    private ParkingSpotRepository parkingSpotRepository;

    @InjectMocks
    private ParkingSpotService parkingSpotService;

    private final ParkingSpotMapper MAPPER = ParkingSpotMapper.INSTANCE;

    @Test
    public void whenAParkingSpotIsInformedThenItShouldBeCreated() {
        //given
        ParkingSpotDTO expectedParkingSpotDTO = ParkingSpotDtoBuilder.builder()
                .build().toParkingSpotDTO();

        ParkingSpot expectedParkingSpot = MAPPER.toModel(expectedParkingSpotDTO);

        //when
        when(parkingSpotRepository.save(expectedParkingSpot)).thenReturn(expectedParkingSpot);

        //then
        ParkingSpot savedParkingSpot = parkingSpotService.save(expectedParkingSpotDTO);

        assertThat(savedParkingSpot).isEqualTo(expectedParkingSpot);
    }

    @Test
    public void whenAParkingSpotIsInformedAndNotExistsThenReturnFalse() {
        //given
        String informedParkingSpotNumber = ParkingSpotDtoBuilder.builder()
                .build().toParkingSpotDTO().getParkingSpotNumber();

        //when
        when(parkingSpotRepository.existsByParkingSpotNumber(informedParkingSpotNumber)).thenReturn(false);

        //then
        boolean savedParkingSpotNumber = parkingSpotService.existsByParkingSpotNumber(informedParkingSpotNumber);

        assertThat(savedParkingSpotNumber).isFalse();
    }

    @Test
    public void whenAParkingSpotIsInformedAndExistsThenReturnTrue() {
        //given
        String informedParkingSpotNumber = ParkingSpotDtoBuilder.builder()
                .build().toParkingSpotDTO().getParkingSpotNumber();

        //when
        when(parkingSpotRepository.existsByParkingSpotNumber(informedParkingSpotNumber)).thenReturn(true);

        //then
        boolean savedParkingSpotNumber = parkingSpotService.existsByParkingSpotNumber(informedParkingSpotNumber);

        assertThat(savedParkingSpotNumber).isTrue();
    }
}
