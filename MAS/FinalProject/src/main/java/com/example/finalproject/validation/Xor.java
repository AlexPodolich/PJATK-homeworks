package com.example.finalproject.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = XorValidation.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
/**
 * Custom annotation XOR
 *
 */
public @interface Xor {
    String message() default "A Resident can have associations with only Document Class or ResidencePermit Class not both";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
