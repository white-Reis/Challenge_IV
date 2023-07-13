package com.car.service.impl;

import com.car.entity.dto.CarDtoRequest;
import com.car.entity.dto.CarDtoResponse;
import com.car.entity.Car;
import com.car.repository.CarRepo;
import com.car.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarService {
    @Autowired
    CarRepo carRepo;

    public ResponseEntity<?> createCar(CarDtoRequest carDtoRequest) {
        Optional<Car> existingCar = carRepo.findById(carDtoRequest.getIdChassi());
        if (existingCar.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Car already registered!");
        }
        if (!carDtoRequest.getBrand().equals("Ford") && !carDtoRequest.getBrand().equals("Chevrolet") && !carDtoRequest.getBrand().equals("BMW") && !carDtoRequest.getBrand().equals("Volvo")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid car brand!");
        }

        try {
            carRepo.save(new Car(carDtoRequest.getIdChassi(), carDtoRequest.getName(), carDtoRequest.getBrand(), carDtoRequest.getColor(), carDtoRequest.getFabricationYear()));
            CarDtoResponse savedCar = new CarDtoResponse(carDtoRequest.getIdChassi(), carDtoRequest.getName(), carDtoRequest.getBrand(), carDtoRequest.getColor(), carDtoRequest.getFabricationYear());

            return ResponseEntity.status(HttpStatus.CREATED).body("Created: " + savedCar.toString());
        } catch (DataAccessException err) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error saving car: " + err.getMessage());
        }

    }

    public Car findCar(Long chassi) {
        Optional<Car> car = carRepo.findById(chassi);
        return car.orElseThrow(() -> new ObjectNotFoundException("Car not found"));
    }
}