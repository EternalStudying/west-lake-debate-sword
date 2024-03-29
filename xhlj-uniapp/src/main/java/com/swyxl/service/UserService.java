package com.swyxl.service;

import com.swyxl.model.dto.uniapp.LoginDto;
import com.swyxl.model.dto.uniapp.RegisterDto;
import com.swyxl.model.dto.uniapp.UserDto;
import com.swyxl.model.vo.uniapp.UserVo;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    String login(LoginDto loginDto);

    void register(RegisterDto registerDto);

    String imageUpload(MultipartFile file);

    UserVo user(String authentication);

    void update(UserDto userDto, String authentication);
}
