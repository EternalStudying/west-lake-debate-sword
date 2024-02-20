package com.swyxl.user.service;

import com.swyxl.model.dto.system.LoginDto;
import com.swyxl.model.dto.system.RegisterDto;
import com.swyxl.model.entity.user.UserInfo;
import com.swyxl.model.vo.service.user.UserInfoVo;

public interface UserInfoService {
    void register(RegisterDto registerDto);

    String login(LoginDto loginDto);

    UserInfo userInfoAll();

    UserInfoVo userInfo();

    void update(UserInfo userInfo);
}
