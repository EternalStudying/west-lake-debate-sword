package com.swyxl.manager.mapper;

import com.swyxl.model.entity.service.exhibit.News;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NewsMapper {


    News getByTitle(String title);

    void save(News news);

    News getById(Long id);

    void update(News news1);
}
