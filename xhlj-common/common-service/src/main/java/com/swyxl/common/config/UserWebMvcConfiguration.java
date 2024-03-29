package com.swyxl.common.config;

import com.swyxl.common.interceptor.UniappLoginAuthInterceptor;
import com.swyxl.common.interceptor.UserLoginAuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class UserWebMvcConfiguration implements WebMvcConfigurer {

    @Autowired
    private UserLoginAuthInterceptor userLoginAuthInterceptor;
    @Autowired
    private UniappLoginAuthInterceptor uniappLoginAuthInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userLoginAuthInterceptor)
                .addPathPatterns("/service/**");
        registry.addInterceptor(uniappLoginAuthInterceptor)
                .addPathPatterns("/uniapp/**/auth/**");
    }
}
