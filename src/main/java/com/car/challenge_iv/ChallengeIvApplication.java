package com.car.challenge_iv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.car.controller")
@EnableJpaRepositories("com.car.repository")
@EntityScan({"com.car.challenge_iv", "com.car.entity", "com.car.controller"})
public class ChallengeIvApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChallengeIvApplication.class, args);
    }

}
