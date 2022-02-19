package com.api.parkingcontrol.validation.constraints;

import com.api.parkingcontrol.validation.ExistsPlateValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ExistsPlateValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistsPlate {

    String message() default "this plate is already exist.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
