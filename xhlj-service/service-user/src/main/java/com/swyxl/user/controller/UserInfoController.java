package com.swyxl.user.controller;

import com.swyxl.common.annotation.EnableUserLoginAuthInterceptor;
import com.swyxl.model.dto.service.user.LoginDto;
import com.swyxl.model.dto.service.user.RegisterDto;
import com.swyxl.model.entity.service.user.UserInfo;
import com.swyxl.model.vo.common.Result;
import com.swyxl.model.vo.common.ResultCodeEnum;
import com.swyxl.model.vo.service.user.UserInfoVo;
import com.swyxl.user.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/service/user/userInfo")
@EnableUserLoginAuthInterceptor
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

    @GetMapping("/auth/userInfo")
    public Result userInfo(){
        UserInfoVo userInfoVo = userInfoService.userInfo();
        return Result.build(userInfoVo, ResultCodeEnum.SUCCESS);
    }

    @GetMapping("/auth/userInfo/all")
    public Result userInfoAll(){
        UserInfo userInfo = userInfoService.userInfoAll();
        return Result.build(userInfo, ResultCodeEnum.SUCCESS);
    }

    @PutMapping("/auth/update")
    public Result update(@RequestBody UserInfo userInfo){
        userInfoService.update(userInfo);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
}
