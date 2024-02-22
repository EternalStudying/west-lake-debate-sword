package com.swyxl.active;

import com.swyxl.common.annotation.EnableUserLoginAuthInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableUserLoginAuthInterceptor
public class ActiveApplication {
    public static void main(String[] args) {
        SpringApplication.run(ActiveApplication.class, args);
    }
}
