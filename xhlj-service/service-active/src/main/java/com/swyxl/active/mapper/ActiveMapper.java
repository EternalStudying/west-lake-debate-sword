package com.swyxl.active.mapper;

import com.swyxl.model.entity.service.active.UserActive;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ActiveMapper {
    void save(UserActive userActive);
}
