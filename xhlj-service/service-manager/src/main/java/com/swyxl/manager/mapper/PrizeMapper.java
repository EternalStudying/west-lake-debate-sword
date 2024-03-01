package com.swyxl.manager.mapper;

import com.github.pagehelper.Page;
import com.swyxl.model.entity.service.exhibit.Prize;
import com.swyxl.model.dto.service.exhibit.PrizeQueryDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PrizeMapper {
    Prize getByName(Prize prize);

    void save(Prize prize);

    Prize getById(Long id);

    void update(Prize prize1);

    Page<Prize> pageLike(PrizeQueryDto prizeQueryVo);
}
