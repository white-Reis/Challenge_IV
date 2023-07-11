package com.car.service;

import com.car.entity.Car;
import com.car.repository.CarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    @Autowired
    CarRepo carRepo;

    public ResponseEntity<?> createCar(Car car) {
        Optional<Car> existingCar = carRepo.findById(car.getIdChassi());
        if (existingCar.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Car already registered!");
        }
        Car savedCar = carRepo.save(car);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCar);
    }

    public ResponseEntity<?> findCar(Long chassi) {
        Optional<Car> car = carRepo.findById(chassi);

        if (car.isPresent()) {
            return ResponseEntity.ok(car.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Car not found");
        }
    }

    public List<Car> getCars() {
        return carRepo.findAll();
    }


}