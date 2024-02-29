package com.swyxl.manager.service;

import com.swyxl.model.entity.service.exhibit.News;
import com.swyxl.model.vo.common.PageResult;
import com.swyxl.model.vo.service.exhibit.NewsQueryVo;

public interface NewsService {
    void add(News news);

    News findById(Long id);

    void update(News news);

    void delete(Long id);

    PageResult page(Integer limit, Integer page, NewsQueryVo newsQueryVo);

}
