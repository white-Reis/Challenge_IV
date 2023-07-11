package com.car.service;

import com.car.dto.CarDtoRequest;
import com.car.dto.CarDtoResponse;
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

    public ResponseEntity<?> createCar(CarDtoRequest carDtoRequest) {
        Optional<Car> existingCar = carRepo.findById(carDtoRequest.getIdChassi());
        if (existingCar.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Car already registered!");
        } else {
            if (String.valueOf(carDtoRequest.getIdChassi()).length() != 17) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid chassis number!");
            }
        }
        if (!carDtoRequest.getBrand().equals("Ford") && !carDtoRequest.getBrand().equals("Chevrolet") && !carDtoRequest.getBrand().equals("BMW") && !carDtoRequest.getBrand().equals("Volvo")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid car brand!");
        }

        try {
            carRepo.save(new Car(carDtoRequest.getIdChassi(),carDtoRequest.getName(),carDtoRequest.getBrand(),carDtoRequest.getColor(),carDtoRequest.getFabricationYear()));
            CarDtoResponse savedCar = new CarDtoResponse(carDtoRequest.getIdChassi(),carDtoRequest.getName(),carDtoRequest.getBrand(),carDtoRequest.getColor(),carDtoRequest.getFabricationYear());

            return ResponseEntity.status(HttpStatus.CREATED).body("Created: "+ savedCar.toString());
        } catch (DataAccessException err) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error saving car: " + err.getMessage());
        }

    }

    public ResponseEntity<?> findCar(Long chassi) {
        Optional<Car> car = carRepo.findById(chassi);
        CarDtoResponse carResponse = new CarDtoResponse(car.get().getIdChassi(),car.get().getName(),car.get().getBrand(),car.get().getColor(),car.get().getFabricationYear());

        if (car.isPresent()) {

            return ResponseEntity.ok(carResponse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Car not found");
        }
    }

    public List<Car> getCars() {
        return carRepo.findAll();
    }


}