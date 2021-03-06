package com.api.parkingcontrol.services;

import com.api.parkingcontrol.dto.ParkingSpotDTO;
import com.api.parkingcontrol.entities.ParkingSpot;
import com.api.parkingcontrol.exceptions.ParkingSpotNotFoundException;
import com.api.parkingcontrol.mapper.ParkingSpotMapper;
import com.api.parkingcontrol.repositories.ParkingSpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

@Service
public class ParkingSpotService {

    private final ParkingSpotRepository parkingSpotRepository;
    private final ParkingSpotMapper MAPPER = ParkingSpotMapper.INSTANCE;

    @Autowired
    public ParkingSpotService(ParkingSpotRepository parkingSpotRepository) {
        this.parkingSpotRepository = parkingSpotRepository;
    }

    @Transactional
    public ParkingSpot save(ParkingSpotDTO parkingSpotDTO) {
        ParkingSpot parkingSpot = MAPPER.toModel(parkingSpotDTO);
        parkingSpot.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));

        return parkingSpotRepository.save(parkingSpot);
    }

    public boolean existsByParkingSpotNumber(String parkingSpotNumber) {
        return parkingSpotRepository.existsByParkingSpotNumber(parkingSpotNumber);
    }

    public List<ParkingSpot> findAll() {
        return parkingSpotRepository.findAll();
    }

    public Page<ParkingSpot> findAllPageable(Pageable pageable) {
        return parkingSpotRepository.findAll(pageable);
    }

    public ParkingSpot findById(UUID id) {
        return parkingSpotRepository.findById(id)
                .orElseThrow(() -> new ParkingSpotNotFoundException(id));
    }

    @Transactional
    public void deleteById(UUID id) {
        parkingSpotRepository.delete(findById(id));
    }

    public ParkingSpot update(UUID id, ParkingSpotDTO parkingSpotDTO) {

        ParkingSpot savedParkingSpot = findById(id);
        ParkingSpot updatedParkingSpot = MAPPER.toModel(parkingSpotDTO);

        updatedParkingSpot.setId(savedParkingSpot.getId());
        updatedParkingSpot.setRegistrationDate(savedParkingSpot.getRegistrationDate());
        updatedParkingSpot.getVehicle().setId(savedParkingSpot.getVehicle().getId());

        return parkingSpotRepository.save(updatedParkingSpot);
    }
}
