package com.car.entity.car.dto;

import com.car.validation.CarBrand.AllowedBrand;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {

    @NotNull
    private long idChassi;

    @NotEmpty(message = "Name field is required")
    private String name;

    @AllowedBrand
    @NotEmpty(message = "Brand field is required")
    private String brand;

    @NotEmpty(message = "Color field is required")
    private String color;

    @NotEmpty(message = "Fabrication year field is required")
    private String fabricationYear;
}