package com.mylesgamez;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main application class that initializes and starts the Spring Boot application.
 */
@SpringBootApplication
public class App {
    /**
     * The main method to run the Spring Boot application.
     *
     * @param args Command-line arguments (if any).
     */
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
