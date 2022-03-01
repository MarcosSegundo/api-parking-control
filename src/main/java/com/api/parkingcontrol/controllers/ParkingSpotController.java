package com.api.parkingcontrol.controllers;

import com.api.parkingcontrol.dto.ParkingSpotDTO;
import com.api.parkingcontrol.entities.ParkingSpot;
import com.api.parkingcontrol.services.ParkingSpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/parking-spot")
public class ParkingSpotController implements ParkingSpotControllerDocs{

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

    @GetMapping("/page")
    public ResponseEntity<Page<ParkingSpot>> findAllPageable(
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC)
            Pageable pageable
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.findAllPageable(pageable));
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

    @PutMapping("/{id}")
    public ResponseEntity<ParkingSpot> update(
            @Valid
            @PathVariable UUID id,
            @RequestBody ParkingSpotDTO parkingSpotDTO
    ) {
      return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.update(id, parkingSpotDTO));
    }
}
