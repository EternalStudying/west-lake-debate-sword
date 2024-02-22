package com.swyxl.manager.service;

import com.swyxl.model.entity.service.active.Active;

public interface ActiveService {
    void add(Active active);

    Active findById( Long id);


    void update(Active active);

    void deleteById(Long id);
}
