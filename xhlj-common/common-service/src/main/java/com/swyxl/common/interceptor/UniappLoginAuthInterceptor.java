package com.swyxl.common.interceptor;

import com.swyxl.common.exception.XHLJException;
import com.swyxl.model.constant.RedisConstant;
import com.swyxl.model.vo.common.ResultCodeEnum;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class UniappLoginAuthInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断当前拦截到的是Controller的方法还是其他资源
        if (!(handler instanceof HandlerMethod)) {
            //当前拦截到的不是动态方法，直接放行
            return true;
        }

        String authentication = request.getHeader("authentication");

        String s = redisTemplate.opsForValue().get(RedisConstant.UNIAPP_AUTHENTICATION + authentication);
        if (StringUtils.hasText(s)) return true;
        throw new XHLJException(ResultCodeEnum.LOGIN_AUTH);
    }
}
