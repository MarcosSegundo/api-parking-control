package com.api.parkingcontrol.validation;

import com.api.parkingcontrol.services.VehicleService;
import com.api.parkingcontrol.validation.constraints.ExistsPlate;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExistsPlateValidator implements ConstraintValidator<ExistsPlate, String> {

    @Autowired
    private VehicleService vehicleService;

    @Override
    public void initialize(ExistsPlate constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !vehicleService.existsByPlate(s);
    }
}
