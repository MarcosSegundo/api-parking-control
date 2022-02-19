package com.api.parkingcontrol.validation;

import com.api.parkingcontrol.services.ParkingSpotService;
import com.api.parkingcontrol.validation.constraints.ExistsParkingSpotNumber;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExistsParkingSpotNumberValidator implements ConstraintValidator<ExistsParkingSpotNumber, String> {

    private final ParkingSpotService parkingSpotService;

    @Autowired
    public ExistsParkingSpotNumberValidator(ParkingSpotService parkingSpotService) {
        this.parkingSpotService = parkingSpotService;
    }

    @Override
    public void initialize(ExistsParkingSpotNumber constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !parkingSpotService.existsByParkingSpotNumber(s);
    }
}
