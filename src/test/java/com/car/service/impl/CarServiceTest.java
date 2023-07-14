package com.car.service.impl;

import com.car.entity.car.Car;
import com.car.entity.car.dto.CarDto;
import com.car.repository.CarRepo;
import com.car.service.exceptions.DataIntegratyViolationException;
import com.car.service.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
class CarServiceTest {

    private long chassi = 11224;
    private String name = "X5";
    private String brand = "BMW";
    private String color = "white";
    private String fabricationyear = "2018";

    @InjectMocks
    private CarService carService;

    @Mock
    private CarRepo carRepo;

    @Mock
    private ModelMapper mapper;
    private Car car;
    private CarDto carDto;
    private Optional<Car> optionalCar;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void whenCreateCarTheReturnSucess() {
        when(carRepo.save(any())).thenReturn(car);

        Car res = carService.createCar(carDto);

        assertNotNull(res);
        assertEquals(Car.class, res.getClass());
        assertEquals(chassi, res.getIdChassi());
        assertEquals(name, res.getName());
        assertEquals(brand, res.getBrand());
        assertEquals(color, res.getColor());
        assertEquals(fabricationyear, res.getFabricationYear());
    }

    @Test
    void whenCreateCarTheReturnAnDataIntegrityViolationExceptiom() {
        when(carRepo.findById(anyLong())).thenReturn(optionalCar);

        try {
            optionalCar.get().setIdChassi(99);
            carService.createCar(carDto);
        } catch (Exception ex) {
            assertEquals(DataIntegratyViolationException.class, ex.getClass());
            assertEquals("car already registered", ex.getMessage());
        }
    }

    @Test
    void whenFindByIdTheReturnedACar() {
        when(carRepo.findById(anyLong())).thenReturn(optionalCar);
        Car res = carService.findCar(chassi);
        assertNotNull(res);
        assertEquals(Car.class, res.getClass());
        assertEquals(chassi, res.getIdChassi());
    }

    @Test
    void whenFindByIdTheReturnAndObjectNotFound() {
        when(carRepo.findById(anyLong())).thenThrow(new ObjectNotFoundException("Car not found"));
        try {
            carService.findCar(chassi);
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals("Car not found", ex.getMessage());
        }
    }

    private void startUser() {
        car = new Car(chassi, name, brand, color, fabricationyear);
        carDto = new CarDto(chassi, name, brand, color, fabricationyear);
        optionalCar = Optional.of(new Car(chassi, name, brand, color, fabricationyear));

    }
}