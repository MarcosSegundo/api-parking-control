package com.api.parkingcontrol.controllers;

import com.api.parkingcontrol.dto.ParkingSpotDTO;
import com.api.parkingcontrol.entities.ParkingSpot;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@Api("Manages Parking Spot")
public interface ParkingSpotControllerDocs {

    @ApiOperation(value = "create a parking spot.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success parking spot creation."),
            @ApiResponse(code = 400, message = "the field shouldn't null or empty"),
            @ApiResponse(code = 400, message = "Parking Spot is already in use."),
            @ApiResponse(code = 400, message = "this plate is already exist.")
    })
    public ResponseEntity<ParkingSpot> save(ParkingSpotDTO parkingSpotDTO);

    @ApiOperation(value = "return a list of all parking spots registered in the system.")
    @ApiResponse(code = 200, message = "List of parking spots registered in the system.")
    public ResponseEntity<List<ParkingSpot>> findAll();

    @ApiOperation(value = "return a page of all parking spots registered in the system.")
    @ApiResponse(code = 200, message = "Page of parking spots registered in the system.")
    public ResponseEntity<Page<ParkingSpot>> findAllPageable(Pageable pageable);

    @ApiOperation(value = "Returns parking spot found by a given id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success parking spot found in the system"),
            @ApiResponse(code = 404, message = "Parking Spot not found.")
    })
    public ResponseEntity<ParkingSpot> findById(@PathVariable UUID id);

    @ApiOperation(value = "Delete a parking spot found by a given valid Id")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success parking spot deleted in the system"),
            @ApiResponse(code = 404, message = "Parking Spot not found.")
    })
    public ResponseEntity<Void> delete(@PathVariable UUID id);

    @ApiOperation(value = "update a parking spot.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success parking spot updated."),
            @ApiResponse(code = 400, message = "the field shouldn't null or empty"),
            @ApiResponse(code = 400, message = "Parking Spot is already in use."),
            @ApiResponse(code = 400, message = "this plate is already exist.")
    })
    public ResponseEntity<ParkingSpot> update(@PathVariable UUID id, ParkingSpotDTO parkingSpotDTO);
}
