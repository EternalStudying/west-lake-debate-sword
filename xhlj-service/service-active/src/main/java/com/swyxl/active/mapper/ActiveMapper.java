package com.swyxl.active.mapper;

import com.github.pagehelper.Page;
import com.swyxl.model.entity.service.active.Active;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ActiveMapper {
    List<Active> selectAll();

    Page<Active> selectByUserId(Long userId);

    void addEnrollment(Long activeId);

    void addLike(Long id);

    Active selectById(Long id);
}
