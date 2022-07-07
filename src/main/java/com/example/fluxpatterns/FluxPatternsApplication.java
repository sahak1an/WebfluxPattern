package com.example.fluxpatterns;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.fluxpatterns.splitter")
public class FluxPatternsApplication {

    public static void main(String[] args) {
        SpringApplication.run(FluxPatternsApplication.class, args);
    }
}
