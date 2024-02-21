package com.swyxl.user.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PrizeRecordMapper {
    void save(String username, String prizeName);
}
