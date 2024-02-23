package com.swyxl.active;

import com.swyxl.common.annotation.EnableUserLoginAuthInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableUserLoginAuthInterceptor
@EnableTransactionManagement
@EnableFeignClients(basePackages = {"com.swyxl"})
@ComponentScan(basePackages = {"com.swyxl"})
public class ActiveApplication {
    public static void main(String[] args) {
        SpringApplication.run(ActiveApplication.class, args);
    }
}
