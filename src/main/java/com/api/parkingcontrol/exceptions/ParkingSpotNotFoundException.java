package com.api.parkingcontrol.exceptions;

import java.util.UUID;

public class ParkingSpotNotFoundException extends RuntimeException {

    public ParkingSpotNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParkingSpotNotFoundException(UUID id) {
        super("Parking Spot not found with id " + id);
    }

}
