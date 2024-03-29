package com.swyxl.controller;

import com.swyxl.model.dto.uniapp.RegisterDto;
import com.swyxl.model.dto.uniapp.LoginDto;
import com.swyxl.model.dto.uniapp.UserDto;
import com.swyxl.model.entity.service.user.UserInfo;
import com.swyxl.model.vo.common.Result;
import com.swyxl.model.vo.common.ResultCodeEnum;
import com.swyxl.model.vo.uniapp.UserVo;
import com.swyxl.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/uniapp/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody LoginDto loginDto){
        String authentication = userService.login(loginDto);
        return Result.build(authentication, ResultCodeEnum.SUCCESS);
    }

    @PostMapping("/register")
    public Result register(@RequestBody RegisterDto registerDto){
        userService.register(registerDto);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @PostMapping("/auth/imageUpload")
    public Result imageUpload(MultipartFile file){
        String url = userService.imageUpload(file);
        return Result.build(url, ResultCodeEnum.SUCCESS);
    }

    @GetMapping("/auth/user")
    public Result user(HttpServletRequest request){
        String authentication = request.getHeader("authentication");
        UserVo userVo = userService.user(authentication);
        return Result.build(userVo, ResultCodeEnum.SUCCESS);
    }

    @PutMapping("/auth/update")
    public Result update(@RequestBody UserDto userDto, HttpServletRequest request){
        String authentication = request.getHeader("authentication");
        userService.update(userDto, authentication);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
}
