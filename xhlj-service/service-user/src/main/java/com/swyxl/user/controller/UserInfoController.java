package com.swyxl.user.controller;

import com.swyxl.model.dto.system.LoginDto;
import com.swyxl.model.dto.system.RegisterDto;
import com.swyxl.model.vo.common.Result;
import com.swyxl.model.vo.common.ResultCodeEnum;
import com.swyxl.user.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service/user/userInfo")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @PostMapping("/register")
    public Result register(@RequestBody RegisterDto registerDto){
        userInfoService.register(registerDto);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @PostMapping("/login")
    public Result login(@RequestBody LoginDto loginDto){
        String token = userInfoService.login(loginDto);
        return Result.build(token, ResultCodeEnum.SUCCESS);
    }
}
