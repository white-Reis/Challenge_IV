package com.car.controller;

import com.car.dto.CarDtoRequest;
import com.car.dto.CarDtoResponse;
import com.car.entity.Car;
import com.car.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class ApiControllers {

    @Autowired
    private CarService carService;


    @PostMapping(value = "/car/post")
    public ResponseEntity<?> createCar(@RequestBody CarDtoRequest carDtoRequest) {
        return  carService.createCar(carDtoRequest);
    }

    @GetMapping(value = "/car/get/{chassi}")
    public ResponseEntity<?> getCar(@PathVariable Long chassi) {
        return carService.findCar(chassi);

    }

    @GetMapping(value = "/cars/get")
    public List<Car> getCars() {
        return carService.getCars();
    }
}
