package com.swyxl.manager.mapper;

import com.swyxl.model.entity.service.exhibit.Achievement;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AchievementMapper {
    Achievement getByName(String name);

    void save(Achievement achievement);

    Achievement getById(Long id);

    void update(Achievement achievement1);
}
