package com.swyxl.active.mapper;

import com.swyxl.model.entity.service.active.Active;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ActiveMapper {
    List<Active> selectAll();

    List<Active> selectByUserId(Long userId);

    void addEnrollment(Long activeId);

    void addLike(Long id);

    Active selectById(Long id);
}
