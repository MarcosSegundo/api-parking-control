package com.api.parkingcontrol.services;

import com.api.parkingcontrol.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {
    
    private final VehicleRepository vehicleRepository;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }
    
    public boolean existsByPlate(String plate) {
        return vehicleRepository.existsByPlate(plate);
    }
}
