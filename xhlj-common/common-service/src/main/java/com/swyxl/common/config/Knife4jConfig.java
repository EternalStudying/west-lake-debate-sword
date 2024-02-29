package com.swyxl.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Knife4jConfig {


    @Bean
    public GroupedOpenApi serviceApi(){
        return GroupedOpenApi.builder()
                .group("service")
                .pathsToMatch("/service/**")
                .build();
    }

    @Bean
    public OpenAPI xhljOpenApi(){
        return new OpenAPI()
                .info(new Info()
                        .title("西湖论剑服务端接口文档")
                        .description("西湖论剑服务端接口文档")
                        .version("1.0")
                        .contact(new Contact().name("罗淋峰")));
    }
}
