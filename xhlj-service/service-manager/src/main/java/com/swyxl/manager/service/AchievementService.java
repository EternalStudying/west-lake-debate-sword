package com.swyxl.manager.service;

import com.swyxl.model.entity.service.exhibit.Achievement;
import org.springframework.web.multipart.MultipartFile;

public interface AchievementService {
    void add(Achievement achievement);

    Achievement findById(Long id);

    void update(Achievement achievement);

    void deleteById(Long id);

    String imageUpload(MultipartFile file);

    String resourceUpload(MultipartFile file);
}
