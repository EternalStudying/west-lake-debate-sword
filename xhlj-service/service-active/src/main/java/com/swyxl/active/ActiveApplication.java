package com.swyxl.active;

import com.swyxl.common.annotation.EnableUserLoginAuthInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableUserLoginAuthInterceptor
@EnableTransactionManagement
public class ActiveApplication {
    public static void main(String[] args) {
        SpringApplication.run(ActiveApplication.class, args);
    }
}
