package com.swyxl.mapper;

import com.swyxl.model.entity.service.user.UserInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    UserInfo selectByKey(String key);

    void save(UserInfo userInfo);

    void update(UserInfo userInfo);
}
