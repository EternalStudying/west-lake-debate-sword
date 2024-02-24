package com.swyxl.user.mapper;

import com.swyxl.model.entity.service.user.UserInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserInfoMapper {
    UserInfo selectByUsername(String username);

    void save(UserInfo userInfo);

    UserInfo selectByPhone(String phone);

    void update(UserInfo userInfo);

    void isSignIn20();

    UserInfo selectById(Long id);
}
