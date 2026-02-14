package com.example.uberserviceauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class UberServiceAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(UberServiceAuthApplication.class, args);
    }

}
