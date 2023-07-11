package com.car.controller;

import com.car.entity.Car;
import com.car.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.car.repository.CarRepo;

import java.util.List;

@RestController
public class ApiControllers {
    private CarService carService;


    @GetMapping(value ="/")
    public String getPage(){
        return "OI Mundo, Abraço compass";
    }

    @PostMapping(value ="/car/post")
    public Car saveCar(@RequestBody Car car){
        return carService.createCar(car);
    }

  
}
