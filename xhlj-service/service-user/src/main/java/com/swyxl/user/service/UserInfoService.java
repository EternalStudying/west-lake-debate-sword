package com.swyxl.user.service;

import com.swyxl.model.dto.service.user.LoginDto;
import com.swyxl.model.dto.service.user.RegisterDto;
import com.swyxl.model.entity.service.manager.LiveUser;
import com.swyxl.model.entity.service.user.UserInfo;
import com.swyxl.model.vo.service.user.UserInfoVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserInfoService {
    void register(RegisterDto registerDto);

    String login(LoginDto loginDto);

    UserInfo userInfoAll();

    UserInfoVo userInfo();

    void update(UserInfo userInfo);

    UserInfo getById(Long id);

    String imageUpload(MultipartFile file);

    List<LiveUser> getLiveUserByIds(List<Integer> ids);
}
