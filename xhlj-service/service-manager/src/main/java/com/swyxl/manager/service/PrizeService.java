package com.swyxl.manager.service;

import com.swyxl.model.entity.service.exhibit.Prize;
import com.swyxl.model.vo.common.PageResult;
import com.swyxl.model.dto.service.exhibit.PrizeQueryDto;

public interface PrizeService {
    void add(Prize prize);

    Prize getById(Long id);

    void update(Prize prize);

    void deleteById(Long id);

    PageResult page(Integer limit, Integer page, PrizeQueryDto prizeQueryVo);
}
