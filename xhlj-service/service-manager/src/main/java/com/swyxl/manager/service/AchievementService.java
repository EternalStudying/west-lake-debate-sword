package com.swyxl.manager.service;

import com.swyxl.model.entity.service.exhibit.Achievement;
import com.swyxl.model.vo.common.PageResult;
import com.swyxl.model.dto.service.exhibit.AchievementQueryDto;
import org.springframework.web.multipart.MultipartFile;

public interface AchievementService {
    void add(Achievement achievement);

    Achievement findById(Long id);

    void update(Achievement achievement);

    void deleteById(Long id);

    String imageUpload(MultipartFile file);

    String resourceUpload(MultipartFile file);

    PageResult page(Integer limit, Integer page, AchievementQueryDto achievementQueryDto);
}
