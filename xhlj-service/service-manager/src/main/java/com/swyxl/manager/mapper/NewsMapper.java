package com.swyxl.manager.mapper;

import com.github.pagehelper.Page;
import com.swyxl.model.entity.service.exhibit.News;
import com.swyxl.model.dto.service.manage.NewsQueryDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NewsMapper {


    News getByTitle(String title);

    void save(News news);

    News getById(Long id);

    void update(News news1);

    Page<News> pageByName(NewsQueryDto newsQueryDto);
}
