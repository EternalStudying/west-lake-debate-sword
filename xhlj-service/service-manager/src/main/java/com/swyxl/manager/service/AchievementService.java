package com.swyxl.manager.service;

import com.swyxl.model.entity.service.exhibit.Achievement;

public interface AchievementService {
    void add(Achievement achievement);

    Achievement findById(Long id);

    void update(Achievement achievement);

    void deleteById(Long id);
}
