package com.swyxl.exhibit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.swyxl"})
@EnableFeignClients(basePackages = {"com.swyxl"})
public class ExhibitApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExhibitApplication.class, args);
    }
}
