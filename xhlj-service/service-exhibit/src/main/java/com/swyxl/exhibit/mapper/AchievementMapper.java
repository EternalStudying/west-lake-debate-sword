package com.swyxl.exhibit.mapper;

import com.swyxl.model.entity.service.exhibit.Achievement;
import com.swyxl.model.entity.service.exhibit.Business;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AchievementMapper {
    List<Achievement> selectAll();

    Achievement selectById(Long id);
}
