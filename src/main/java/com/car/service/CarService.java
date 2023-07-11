package com.car.service;

import com.car.entity.Car;
import com.car.repository.CarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
        } else {
            if (String.valueOf(car.getIdChassi()).length() != 17) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid chassis number!");
            }
        }
        if (!car.getBrand().equals("Ford") && !car.getBrand().equals("Chevrolet") && !car.getBrand().equals("BMW") && !car.getBrand().equals("Volvo")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid car brand!");
        }

        try {
            Car savedCar = carRepo.save(car);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCar);
        } catch (DataAccessException err) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error saving car: " + err.getMessage());
        }

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