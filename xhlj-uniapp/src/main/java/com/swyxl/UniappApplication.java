package com.swyxl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.swyxl"})
@EnableFeignClients(basePackages = {"com.swyxl"})
public class UniappApplication {
    public static void main(String[] args) {
        SpringApplication.run(UniappApplication.class, args);
    }
}
