package com.swyxl.exhibit.mapper;

import com.swyxl.model.entity.service.exhibit.News;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NewsMapper {
    List<News> selectAll();
}
