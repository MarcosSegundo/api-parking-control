package com.api.parkingcontrol.controllers;

import com.api.parkingcontrol.dto.ParkingSpotDTO;
import com.api.parkingcontrol.entities.ParkingSpot;
import com.api.parkingcontrol.services.ParkingSpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/parking-spot")
public class ParkingSpotController {

    @Autowired
    private ParkingSpotService parkingSpotService;

    @PostMapping
    public ResponseEntity<ParkingSpot> save(@Valid @RequestBody ParkingSpotDTO parkingSpotDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(parkingSpotService.save(parkingSpotDTO));
    }

    @GetMapping
    public ResponseEntity<List<ParkingSpot>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingSpot> findById(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        parkingSpotService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
