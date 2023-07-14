package com.car.entity.car;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "db_car")
public class Car {
    @Id
    @Column(name = "id_Chassi",unique = true,nullable = false)
    private long idChassi;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "brand",nullable = false)
    private String brand;

    @Column(name = "color",nullable = false)
    private String color;

    @Column(name = "fabrication_Year",nullable = false)
    private String fabricationYear;

}

