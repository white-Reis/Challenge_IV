package com.car.dto;

public class CarDtoResponse {
    private long idChassi;


    private String name;


    private String brand;

    private String color;


    private String fabricationYear;

    public CarDtoResponse(long idChassi, String name, String brand, String color, String fabricationYear) {
        this.idChassi = idChassi;
        this.name = name;
        this.brand = brand;
        this.color = color;
        this.fabricationYear = fabricationYear;

    }

    public long getIdChassi() {
        return idChassi;
    }

    public void setIdChassi(long idChassi) {
        this.idChassi = idChassi;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFabricationYear() {
        return fabricationYear;
    }

    public void setFabricationYear(String fabricationYear) {
        this.fabricationYear = fabricationYear;
    }

    @Override
    public String toString() {
        return "Car{ " +
                "idChassi= " + idChassi +
                ", name= '" + name + '\'' +
                ", brand= '" + brand + '\'' +
                ", color= '" + color + '\'' +
                ", fabricationYear= '" + fabricationYear + '\'' +
                '}';
    }
}

