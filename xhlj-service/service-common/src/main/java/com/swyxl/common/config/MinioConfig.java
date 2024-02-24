package com.swyxl.common.config;

import com.swyxl.common.properties.MinioProperty;
import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioConfig {

    @Autowired
    private MinioProperty minioProperty;

    @Bean
    public MinioClient minioClient(){
        return MinioClient.builder()
                .endpoint(minioProperty.getEndpointUrl())  //传入url地址
                //传入用户名和密码
                .credentials(minioProperty.getAccessKey(), minioProperty.getSecretKey())
                .build();  //完成MinioClient的初始化
    }
}
