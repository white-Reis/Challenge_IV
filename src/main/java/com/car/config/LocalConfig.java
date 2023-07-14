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
        Car car1 = new Car(12345,"Van", "Ford", "Blue", "2022");
        Car car2 = new Car(67890,"Compass", "Volvo", "Red", "2023");
        Car car3 = new Car(54321,"A100", "BMW", "Black", "2021");
        carRepo.saveAll(List.of(car1,car2,car3));

    }
}
