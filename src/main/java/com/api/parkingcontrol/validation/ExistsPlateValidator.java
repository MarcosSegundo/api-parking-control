package com.api.parkingcontrol.validation;

import com.api.parkingcontrol.validation.constraints.ExistsPlate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExistsPlateValidation implements ConstraintValidator<ExistsPlate, String> {

    @Override
    public void initialize(ExistsPlate constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return false;
    }
}
