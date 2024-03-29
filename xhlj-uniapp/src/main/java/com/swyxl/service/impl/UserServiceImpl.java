package com.swyxl.service.impl;

import com.alibaba.fastjson.JSON;
import com.swyxl.common.exception.XHLJException;
import com.swyxl.feign.common.CommonFeignClient;
import com.swyxl.mapper.UserMapper;
import com.swyxl.model.constant.RedisConstant;
import com.swyxl.model.constant.TypeConstant;
import com.swyxl.model.dto.uniapp.LoginDto;
import com.swyxl.model.dto.uniapp.RegisterDto;
import com.swyxl.model.dto.uniapp.UserDto;
import com.swyxl.model.entity.service.user.UserInfo;
import com.swyxl.model.vo.common.ResultCodeEnum;
import com.swyxl.model.vo.uniapp.UserVo;
import com.swyxl.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private CommonFeignClient commonFeignClient;

    @Override
    public String login(LoginDto loginDto) {
        String key = loginDto.getKey();
        String password = loginDto.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        UserInfo userInfo = userMapper.selectByKey(key);
        if (userInfo == null)
            throw new XHLJException(ResultCodeEnum.LOGIN_ERROR);
        String userInfoPassword = userInfo.getPassword();
        if (!Objects.equals(password, userInfoPassword))
            throw new XHLJException(ResultCodeEnum.LOGIN_ERROR);
        String authentication = UUID.randomUUID().toString().replaceAll("-", "");
        redisTemplate.opsForValue().set(RedisConstant.UNIAPP_AUTHENTICATION + authentication, JSON.toJSONString(userInfo), 1, TimeUnit.DAYS);
        return authentication;
    }

    @Override
    public void register(RegisterDto registerDto) {
        String username = registerDto.getUsername();
        String password = registerDto.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        String phone = registerDto.getPhone();

        UserInfo userInfo = userMapper.selectByKey(username);
        if(userInfo != null) throw new XHLJException(ResultCodeEnum.USER_NAME_IS_EXISTS);

        userInfo = new UserInfo();
        userInfo.setUsername(username);
        userInfo.setPassword(password);
        userInfo.setPhone(phone);
        userMapper.save(userInfo);
    }

    @Override
    public String imageUpload(MultipartFile file) {
        String url = commonFeignClient.fileUpload(file, TypeConstant.USER);
        if (url.isEmpty())
            throw new XHLJException(ResultCodeEnum.FILE_ERROR);
        return url;
    }

    @Override
    public UserVo user(String auth) {
        String userJson = redisTemplate.opsForValue().get(RedisConstant.UNIAPP_AUTHENTICATION + auth);
        UserInfo userInfo = JSON.parseObject(userJson, UserInfo.class);
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(userInfo, userVo);
        return userVo;
    }

    @Override
    public void update(UserDto userDto, String auth) {
        String userJson = redisTemplate.opsForValue().get(RedisConstant.UNIAPP_AUTHENTICATION + auth);
        Long userId = JSON.parseObject(userJson, UserInfo.class).getId();
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userDto, userInfo);
        userInfo.setId(userId);
        userInfo.setUpdateTime(new Date());
        userMapper.update(userInfo);
    }
}
