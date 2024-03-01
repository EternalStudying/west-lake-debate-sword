package com.swyxl.manager.mapper;

import com.github.pagehelper.Page;
import com.swyxl.model.entity.service.exhibit.Achievement;
import com.swyxl.model.dto.service.exhibit.AchievementQueryDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AchievementMapper {
    Achievement getByName(String name);

    void save(Achievement achievement);

    Achievement getById(Long id);

    void update(Achievement achievement1);

    Page<Achievement> pageLike(AchievementQueryDto achievementQueryDto);
}
