package com.car.controller;

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


    @GetMapping(value = "/H")
    public String getPage() {
        return "OI Mundo, Abraço compass";
    }

    @PostMapping(value = "/car/post")
    public ResponseEntity<?> createCar(@RequestBody Car car) {
        return carService.createCar(car);
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
