package com.springteam.computerstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class ComputerStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(ComputerStoreApplication.class, args);
    }

}
