package com.nighthawk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The WeeklyDinnersAppApplication class is the main class of the application.
 * The main() method is the entry point of the application.
 * The @SpringBootApplication annotation is used to enable auto-configuration, component scanning, and to enable the
 * Spring Boot Actuator.
 */
@SpringBootApplication
public class WeeklyDinnersAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeeklyDinnersAppApplication.class, args);
    }

}
