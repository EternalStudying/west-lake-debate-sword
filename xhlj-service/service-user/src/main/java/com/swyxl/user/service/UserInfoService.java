package com.swyxl.user.service;

import com.swyxl.model.dto.system.LoginDto;
import com.swyxl.model.dto.system.RegisterDto;

public interface UserInfoService {
    void register(RegisterDto registerDto);

    String login(LoginDto loginDto);
}
