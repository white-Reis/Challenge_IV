package com.car.controller;

import com.car.entity.car.dto.CarDto;
import com.car.service.exceptions.DataIntegratyViolationException;
import com.car.service.impl.CarService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/car")
public class ApiControllers {

    @Autowired
    private CarService carService;

    @Autowired
    private ModelMapper mapper;

    @PostMapping(value = "/post")
    public ResponseEntity<?> createCar(@RequestBody @Valid CarDto carDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errorMessages = new ArrayList<>();
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                String errorMessage = fieldError.getDefaultMessage();
                errorMessages.add(errorMessage);
            }
            throw new DataIntegratyViolationException(errorMessages.toString());
        }
        URI url = ServletUriComponentsBuilder
                .fromCurrentRequest().replacePath("/get/{id}").buildAndExpand(carService.createCar(carDto).getIdChassi()).toUri();
        return ResponseEntity.created(url).build();
    }

    @GetMapping(value = "/get/{chassi}")
    public ResponseEntity<CarDto> getCar(@PathVariable Long chassi) {
        return ResponseEntity.ok().body(mapper.map(carService.findCar(chassi), CarDto.class));
    }
}


