package com.swyxl.common.interceptor;

import com.alibaba.fastjson.JSON;
import com.swyxl.model.constant.RedisConstant;
import com.swyxl.model.entity.service.user.UserInfo;
import com.swyxl.utils.AuthContextUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class UserLoginAuthInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if (token != null) {
            String userInfoJSON = redisTemplate.opsForValue().get(RedisConstant.SERVICE_TOKEN + token);

            AuthContextUtils.setUserInfo(JSON.parseObject(userInfoJSON, UserInfo.class));
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        AuthContextUtils.removeUserInfo();
        AuthContextUtils.removeInteger();
    }
}
