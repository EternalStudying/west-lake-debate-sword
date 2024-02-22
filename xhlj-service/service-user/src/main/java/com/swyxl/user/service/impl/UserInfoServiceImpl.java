package com.swyxl.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.swyxl.common.exception.XHLJException;
import com.swyxl.model.constant.UserInfoConstant;
import com.swyxl.model.dto.service.user.LoginDto;
import com.swyxl.model.dto.service.user.RegisterDto;
import com.swyxl.model.entity.service.user.UserInfo;
import com.swyxl.model.vo.common.ResultCodeEnum;
import com.swyxl.model.vo.service.user.UserInfoVo;
import com.swyxl.user.mapper.UserInfoMapper;
import com.swyxl.user.service.UserInfoService;
import com.swyxl.utils.AuthContextUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
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

        String code = redisTemplate.opsForValue().get(UserInfoConstant.SERVICE_CAPTCHA + phone);
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
        String method = loginDto.getMethod();
        UserInfo userInfo = new UserInfo();
        //0为账密登录
        if(method.equals("0")){
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
        else if (method.equals("1")){
            String phone = loginDto.getPhone();
            String captcha = loginDto.getCaptcha();
            String code = redisTemplate.opsForValue().get(UserInfoConstant.SERVICE_CAPTCHA + phone);
            if (!Objects.equals(captcha, code))
                throw new XHLJException(ResultCodeEnum.VALIDATECODE_ERROR);
            userInfo = userInfoMapper.selectByPhone(phone);
            if (userInfo == null)
                throw new XHLJException(ResultCodeEnum.LOGIN_ERROR);
        }
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        redisTemplate.opsForValue().set(UserInfoConstant.SERVICE_TOKEN + token, JSON.toJSONString(userInfo), 1, TimeUnit.DAYS);
        return token;
    }

    @Override
    public UserInfo userInfoAll() {
        return AuthContextUtil.getUserInfo();
    }

    @Override
    public UserInfoVo userInfo() {
        UserInfo userInfo = AuthContextUtil.getUserInfo();
        UserInfoVo userInfoVo = new UserInfoVo();
        BeanUtils.copyProperties(userInfo, userInfoVo);
        return userInfoVo;
    }

    @Override
    public void update(UserInfo userInfo) {
        Long userId = AuthContextUtil.getUserInfo().getId();
        userInfo.setId(userId);
        userInfo.setUpdateTime(new Date());
        userInfoMapper.update(userInfo);
    }
}
