package com.api.parkingcontrol.services;

import com.api.parkingcontrol.builder.ParkingSpotDtoBuilder;
import com.api.parkingcontrol.dto.ParkingSpotDTO;
import com.api.parkingcontrol.entities.ParkingSpot;
import com.api.parkingcontrol.exceptions.ParkingSpotNotFoundException;
import com.api.parkingcontrol.mapper.ParkingSpotMapper;
import com.api.parkingcontrol.repositories.ParkingSpotRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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

    @Test
    public void whenFindAllIsCalledThenReturnAEmptyListOfParkingSpot() {
        //when
        when(parkingSpotRepository.findAll()).thenReturn(Collections.emptyList());

        //then
        List<ParkingSpot> foundedParkingSpots = parkingSpotService.findAll();

        assertThat(foundedParkingSpots).isEmpty();
    }

    @Test
    public void whenFindAllIsCalledThenReturnAListOfParkingSpot() {
        //given
        ParkingSpotDTO expectedParkingSpotDTO = ParkingSpotDtoBuilder.builder()
                .build().toParkingSpotDTO();

        ParkingSpot expectedParkingSpot = MAPPER.toModel(expectedParkingSpotDTO);

        //when
        when(parkingSpotRepository.findAll()).thenReturn(Collections.singletonList(expectedParkingSpot));

        //then
        List<ParkingSpot> foundedParkingSpots = parkingSpotService.findAll();

        assertThat(foundedParkingSpots).hasSize(1);
    }

    @Test
    public void whenFindAllPageableIsCalledThenReturnAPageOfParkingSpot() {
        //given
        ParkingSpotDTO expectedParkingSpotDTO = ParkingSpotDtoBuilder.builder()
                .build().toParkingSpotDTO();

        ParkingSpot expectedParkingSpot = MAPPER.toModel(expectedParkingSpotDTO);

        Pageable pageable = PageRequest.of(0, 1); //criando um page.

        //criando um Page<ParkingSpot> a partir de uma lista.
        Page<ParkingSpot> pageParkingSpots = new PageImpl<>(Collections.singletonList(expectedParkingSpot), pageable, 1L);

        //when
        when(parkingSpotRepository.findAll(pageable)).thenReturn(pageParkingSpots);

        //then
        Page<ParkingSpot> foundedPages = parkingSpotService.findAllPageable(pageable);

        assertThat(foundedPages).hasSize(1);
    }

    @Test
    public void whenFindAllPageableIsCalledThenReturnAEmptyPageOfParkingSpot() {
        //given
        ParkingSpotDTO expectedParkingSpotDTO = ParkingSpotDtoBuilder.builder()
                .build().toParkingSpotDTO();

        ParkingSpot expectedParkingSpot = MAPPER.toModel(expectedParkingSpotDTO);

        Pageable pageable = PageRequest.ofSize(1); //criando um page.

        //criando um Page<ParkingSpot> vazio
        Page<ParkingSpot> pageParkingSpots = Page.empty();

        //when
        when(parkingSpotRepository.findAll(pageable)).thenReturn(pageParkingSpots);

        //then
        Page<ParkingSpot> foundedPages = parkingSpotService.findAllPageable(pageable);

        assertThat(foundedPages).isEmpty();
    }
}
