package com.swyxl.manager;

import com.swyxl.manager.properties.LiveProperty;
import com.swyxl.manager.properties.TencentLiveProperty;
import com.swyxl.manager.properties.TencentProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackages = "com.swyxl")
@EnableFeignClients(basePackages = "com.swyxl")
@EnableDiscoveryClient
@EnableConfigurationProperties(value = {LiveProperty.class, TencentLiveProperty.class, TencentProperty.class})
@EnableScheduling
public class ManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManagerApplication.class,args);
    }

}
