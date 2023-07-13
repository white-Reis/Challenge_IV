package com.car.controller;

import com.car.dto.CarDtoRequest;
import com.car.entity.Car;
import com.car.service.impl.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/car")
public class ApiControllers {

    @Autowired
    private CarService carService;

    @PostMapping(value = "/post")
    public ResponseEntity<?> createCar(@RequestBody CarDtoRequest carDtoRequest) {
        return carService.createCar(carDtoRequest);
    }
    @GetMapping(value = "/get/{chassi}")
    public Car getCar(@PathVariable Long chassi) {
        return carService.findCar(chassi);
    }
}


