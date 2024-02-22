package com.swyxl.user.mapper;

import com.swyxl.model.entity.user.Prize;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PrizeMapper {
    List<Prize> selectAll();

    void update(Prize prize);

    Prize selectByName(String name);
}
