package com.car.config;

import com.car.entity.car.Car;
import com.car.repository.CarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("local")
public class LocalConfig {

    @Autowired
    private CarRepo carRepo;
    @Bean
    public void startDB(){
        Car car = new Car(12345,"mustang", "ford", "purple", "2022");
        carRepo.saveAll(List.of(car));

    }
}
