package com.car.validation.CarBrand;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AllowedBrandValidator.class)
public @interface AllowedBrand {
    String message() default "Brand not allowed";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}