package com.swyxl.common.interceptor;

import com.alibaba.fastjson.JSON;
import com.swyxl.model.constant.UserInfoConstant;
import com.swyxl.model.entity.service.user.UserInfo;
import com.swyxl.utils.AuthContextUtil;
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
            String userInfoJSON = redisTemplate.opsForValue().get(UserInfoConstant.SERVICE_TOKEN + token);

            AuthContextUtil.setUserInfo(JSON.parseObject(userInfoJSON, UserInfo.class));
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        AuthContextUtil.removeUserInfo();
    }
}
