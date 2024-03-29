package com.swyxl.mapper;

import com.swyxl.model.entity.service.exhibit.Achievement;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CollectionMapper {
    List<Achievement> getPpt();

    Achievement selectById(Long id);
}
