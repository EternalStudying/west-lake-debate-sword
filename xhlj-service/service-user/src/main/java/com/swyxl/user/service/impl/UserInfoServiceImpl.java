package com.swyxl.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.swyxl.common.exception.XHLJException;
import com.swyxl.model.dto.system.LoginDto;
import com.swyxl.model.dto.system.RegisterDto;
import com.swyxl.model.entity.user.UserInfo;
import com.swyxl.model.vo.common.ResultCodeEnum;
import com.swyxl.user.mapper.UserInfoMapper;
import com.swyxl.user.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public void register(RegisterDto registerDto) {
        String username = registerDto.getUsername();
        String password = registerDto.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        String phone = registerDto.getPhone();
        String captcha = registerDto.getCaptcha();
        String code = redisTemplate.opsForValue().get(phone);
        if(!Objects.equals(code, captcha)) throw new XHLJException(ResultCodeEnum.VALIDATECODE_ERROR);
        UserInfo userInfo = userInfoMapper.selectByUsername(username);
        if(userInfo != null) throw new XHLJException(ResultCodeEnum.USER_NAME_IS_EXISTS);

        userInfo = new UserInfo();
        userInfo.setUsername(username);
        userInfo.setPassword(password);
        userInfo.setPhone(phone);
        userInfoMapper.save(userInfo);
    }

    @Override
    public String login(LoginDto loginDto) {
        Short method = loginDto.getMethod();
        UserInfo userInfo = new UserInfo();
        //0为账密登录
        if(method == 0){
            String username = loginDto.getUsername();
            String password = loginDto.getPassword();
            password = DigestUtils.md5DigestAsHex(password.getBytes());
            userInfo = userInfoMapper.selectByUsername(username);
            if (userInfo == null)
                throw new XHLJException(ResultCodeEnum.LOGIN_ERROR);
            String userInfoPassword = userInfo.getPassword();
            if (!Objects.equals(password, userInfoPassword))
                throw new XHLJException(ResultCodeEnum.LOGIN_ERROR);
        }
        //1为手机验证码登录
        else if (method == 1){
            String phone = loginDto.getPhone();
            String captcha = loginDto.getCaptcha();
            String code = redisTemplate.opsForValue().get(phone);
            if (!Objects.equals(captcha, code))
                throw new XHLJException(ResultCodeEnum.VALIDATECODE_ERROR);
            userInfo = userInfoMapper.selectByPhone(phone);
            if (userInfo == null)
                throw new XHLJException(ResultCodeEnum.LOGIN_ERROR);
        }
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        redisTemplate.opsForValue().set("user:service:" + token, JSON.toJSONString(userInfo), 1, TimeUnit.DAYS);
        return token;
    }
}
