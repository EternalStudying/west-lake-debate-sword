package com.swyxl.manager.mapper;

import com.swyxl.model.entity.service.exhibit.Prize;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PrizeMapper {
    Prize getByName(Prize prize);

    void save(Prize prize);

    Prize getById(Long id);

    void update(Prize prize1);
}
