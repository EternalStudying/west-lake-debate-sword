package com.swyxl.manager.service;

import com.swyxl.model.entity.service.exhibit.Prize;

public interface PrizeService {
    void add(Prize prize);

    Prize getById(Long id);

    void update(Prize prize);

    void deleteById(Long id);
}
