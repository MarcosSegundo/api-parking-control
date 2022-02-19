package com.api.parkingcontrol.validation.constraints;

import com.api.parkingcontrol.validation.ExistsParkingSpotNumberValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ExistsParkingSpotNumberValidator.class)
@Target(value = {ElementType.METHOD, ElementType.FIELD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface ExistsParkingSpotNumber {

    String message() default "Parking Spot is already in use.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
