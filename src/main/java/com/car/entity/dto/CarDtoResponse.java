package com.car.entity.dto;

import jakarta.persistence.Table;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "car")
public class CarDtoResponse {
    private long idChassi;
    private String name;
    private String brand;
    private String color;
    private String fabricationYear;
}

