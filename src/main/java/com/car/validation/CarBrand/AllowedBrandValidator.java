package com.car.validation.CarBrand;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class AllowedBrandValidator implements ConstraintValidator<AllowedBrand, String> {

    private static List<String> allowedBrands;

    @Override
    public void initialize(AllowedBrand constraintAnnotation) {
        allowedBrands= Arrays.asList("Ford", "Chevrolet", "BMW", "Volvo");
    }

    @Override
    public boolean isValid(String brand, ConstraintValidatorContext context) {
        return brand == null || allowedBrands.contains(brand);
    }
}
