package com.swyxl.user.aspect;

import com.alibaba.fastjson.JSON;
import com.swyxl.model.constant.UserInfoConstant;
import com.swyxl.model.entity.service.user.UserInfo;
import com.swyxl.user.mapper.UserInfoMapper;
import com.swyxl.utils.AuthContextUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.concurrent.TimeUnit;

@Aspect
@Component
public class RecacheAspect {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Pointcut("@annotation(com.swyxl.user.annotation.Recache)")
    public void RecachePointCut(){}

    @AfterReturning("RecachePointCut()")
    public void Recache(){
        System.err.println("开始重新缓存");
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String username = AuthContextUtil.getUserInfo().getUsername();
        UserInfo userInfo = userInfoMapper.selectByUsername(username);
        String token = request.getHeader("token");
        redisTemplate.opsForValue().set(UserInfoConstant.SERVICE_TOKEN + token, JSON.toJSONString(userInfo), 1, TimeUnit.DAYS);
    }
}
