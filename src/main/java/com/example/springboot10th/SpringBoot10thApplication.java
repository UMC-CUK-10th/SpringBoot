package com.example.springboot10th;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringBoot10thApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot10thApplication.class, args);
    }

}