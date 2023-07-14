package com.car.service.impl;

import com.car.entity.car.Car;
import com.car.entity.car.dto.CarDto;
import com.car.repository.CarRepo;
import com.car.service.exceptions.DataIntegratyViolationException;
import com.car.service.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    CarRepo carRepo;

    public Car createCar(CarDto carDtoRequest) {
        Optional<Car> car = carRepo.findById(carDtoRequest.getIdChassi());
        if (car.isPresent()) {
            throw new DataIntegratyViolationException("car already registered");
        }
        return carRepo.save(mapper.map(carDtoRequest, Car.class));
    }

    public Car findCar(Long chassi) {
        Optional<Car> car = carRepo.findById(chassi);
        return car.orElseThrow(() -> new ObjectNotFoundException("Car not found"));
    }
}