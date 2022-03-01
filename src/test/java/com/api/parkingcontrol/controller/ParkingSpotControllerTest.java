package com.api.parkingcontrol.controller;

import com.api.parkingcontrol.builder.ParkingSpotDtoBuilder;
import com.api.parkingcontrol.controllers.ParkingSpotController;
import com.api.parkingcontrol.dto.ParkingSpotDTO;
import com.api.parkingcontrol.entities.ParkingSpot;
import com.api.parkingcontrol.exceptions.ParkingSpotNotFoundException;
import com.api.parkingcontrol.mapper.ParkingSpotMapper;
import com.api.parkingcontrol.services.ParkingSpotService;
import com.api.parkingcontrol.services.VehicleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static com.api.parkingcontrol.utils.JsonParseUtils.asJsonString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ParkingSpotController.class)
public class ParkingSpotControllerTest {

    private static final String PARKING_SPOT_API_PATH = "/api/v1/parking-spot";
    private static final String SUB_PATH_FIND_ALL_PAGE = "/page";

    @MockBean
    private ParkingSpotService parkingSpotService;

    @MockBean
    private VehicleService vehicleService;

    @Autowired
    private MockMvc mockMvc;

    private final ParkingSpotMapper MAPPER = ParkingSpotMapper.INSTANCE;

    @Test
    public void whenPOSTIsCalledThenAParkingSpotIsCreated() throws Exception {
        //given
        ParkingSpotDTO parkingSpotDTO = ParkingSpotDtoBuilder.builder()
                .build().toParkingSpotDTO();

        ParkingSpot parkingSpot = MAPPER.toModel(parkingSpotDTO);

        //when
        when(parkingSpotService.save(parkingSpotDTO)).thenReturn(parkingSpot);

        //then
        mockMvc.perform(post(PARKING_SPOT_API_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(parkingSpot))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.apartment").value(parkingSpot.getApartment()));
    }

    @Test
    public void whenPOSTIsCalledWithoutARequiredFieldThenStatusBadRequestIsReturned() throws Exception {
        //given
        ParkingSpotDTO parkingSpotDTO = ParkingSpotDtoBuilder.builder()
                .build().toParkingSpotDTO();

        parkingSpotDTO.setParkingSpotNumber(null);

        ParkingSpot parkingSpot = MAPPER.toModel(parkingSpotDTO);

        //when
        when(parkingSpotService.save(parkingSpotDTO)).thenThrow(ParkingSpotNotFoundException.class);

        //then
        mockMvc.perform(post(PARKING_SPOT_API_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(parkingSpot))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void whenGETIsCalledWithValidIdThenStatusOkIsReturned() throws Exception {
        //given
        ParkingSpotDTO parkingSpotDTO = ParkingSpotDtoBuilder.builder()
                .build().toParkingSpotDTO();

        ParkingSpot parkingSpot = MAPPER.toModel(parkingSpotDTO);

        //when
        when(parkingSpotService.findById(parkingSpotDTO.getId())).thenReturn(parkingSpot);

        //then
        String request = String.format("{\"id\": \"%s\"}", parkingSpotDTO.getId());

        mockMvc.perform(get(PARKING_SPOT_API_PATH + "/{id}", parkingSpotDTO.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.apartment").value(parkingSpot.getApartment()));
    }

    @Test
    public void whenGETIsCalledWithInValidIdThenStatusNotFoundIsReturned() throws Exception {
        //given
        ParkingSpotDTO parkingSpotDTO = ParkingSpotDtoBuilder.builder()
                .build().toParkingSpotDTO();

        //when
        when(parkingSpotService.findById(parkingSpotDTO.getId())).thenThrow(ParkingSpotNotFoundException.class);

        //then
        String request = String.format("{\"id\": \"%s\"}", parkingSpotDTO.getId());

        mockMvc.perform(get(PARKING_SPOT_API_PATH + "/{id}", parkingSpotDTO.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void whenGETFindAllIsCalledThenOkStatusIsReturned() throws Exception {
        //given
        ParkingSpotDTO parkingSpotDTO = ParkingSpotDtoBuilder.builder()
                .build().toParkingSpotDTO();

        ParkingSpot parkingSpot = MAPPER.toModel(parkingSpotDTO);

        List<ParkingSpot> parkingSpots = Collections.singletonList(parkingSpot);

        //when
        when(parkingSpotService.findAll()).thenReturn(parkingSpots);

        //then
        mockMvc.perform(get(PARKING_SPOT_API_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(parkingSpots))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].apartment").value(parkingSpotDTO.getApartment()));
    }

    @Test
    public void whenGETFindAllIsCalledWithoutDataThenOkStatusIsReturned() throws Exception {
        //when
        when(parkingSpotService.findAll()).thenReturn(Collections.emptyList());

        //then
        mockMvc.perform(get(PARKING_SPOT_API_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void whenGETFindAllPageableIsCalledThenOkStatusIsReturned() throws Exception {
        //given
        ParkingSpotDTO parkingSpotDTO = ParkingSpotDtoBuilder.builder()
                .build().toParkingSpotDTO();

        ParkingSpot parkingSpot = MAPPER.toModel(parkingSpotDTO);

        PageRequest pageable = PageRequest.of(0, 1);

        PageImpl<ParkingSpot> parkingSpots = new PageImpl<>(Collections.singletonList(parkingSpot), pageable, 1);

        //when
        when(parkingSpotService.findAllPageable(pageable)).thenReturn(parkingSpots);

        //then
        mockMvc.perform(get(PARKING_SPOT_API_PATH + "/page")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(parkingSpots))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void whenGETFindAllPageableWithoutParkingSpotIsCalledThenOkStatusIsReturned() throws Exception {
        //given
        ParkingSpotDTO parkingSpotDTO = ParkingSpotDtoBuilder.builder()
                .build().toParkingSpotDTO();

        PageRequest pageable = PageRequest.of(0, 1);

        Page<ParkingSpot> parkingSpots = Page.empty();

        //when
        when(parkingSpotService.findAllPageable(pageable)).thenReturn(parkingSpots);

        //then
        mockMvc.perform(get(PARKING_SPOT_API_PATH + "/page")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(parkingSpots))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    public void whenDELETEIsCalledWithValidIdThenStatusNoContentIsReturned() throws Exception {
        //given
        ParkingSpotDTO parkingSpotDTO = ParkingSpotDtoBuilder.builder()
                .build().toParkingSpotDTO();

        //when
        doNothing().when(parkingSpotService).deleteById(parkingSpotDTO.getId());

        mockMvc.perform(delete(PARKING_SPOT_API_PATH + "/{id}", parkingSpotDTO.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(parkingSpotDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void whenDELETEIsCalledWithInValidIdThenStatusNotFoundIsReturned() throws Exception {
        //given
        ParkingSpotDTO parkingSpotDTO = ParkingSpotDtoBuilder.builder()
                .build().toParkingSpotDTO();

        //when
        doThrow(ParkingSpotNotFoundException.class).when(parkingSpotService).deleteById(parkingSpotDTO.getId());

        mockMvc.perform(delete(PARKING_SPOT_API_PATH + "/{id}", parkingSpotDTO.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(parkingSpotDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void whenPUTIsCalledWithValidIdThenOkStatusIsReturned() throws Exception {
        //given
        ParkingSpotDTO parkingSpotDTO = ParkingSpotDtoBuilder.builder()
                .build().toParkingSpotDTO();

        ParkingSpot parkingSpot = MAPPER.toModel(parkingSpotDTO);

        //when
        when(parkingSpotService.save(parkingSpotDTO)).thenReturn(parkingSpot);
        when(parkingSpotService.update(parkingSpotDTO.getId(), parkingSpotDTO)).thenReturn(parkingSpot);

        //then
        mockMvc.perform(post(PARKING_SPOT_API_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(parkingSpot))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(parkingSpot.getId().toString()));
    }

    @Test
    public void whenPUTIsCalledWithInValidIdThenStatusNotFoundIsReturned() throws Exception {
        //given
        ParkingSpotDTO parkingSpotDTO = ParkingSpotDtoBuilder.builder()
                .build().toParkingSpotDTO();

        ParkingSpot parkingSpot = MAPPER.toModel(parkingSpotDTO);

        //when
        when(parkingSpotService.save(parkingSpotDTO)).thenThrow(ParkingSpotNotFoundException.class);

        //then
        mockMvc.perform(post(PARKING_SPOT_API_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(parkingSpot))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
