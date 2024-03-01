package com.swyxl.manager.service;

import com.swyxl.model.entity.service.exhibit.News;
import com.swyxl.model.vo.common.PageResult;
import com.swyxl.model.dto.service.exhibit.NewsQueryDto;
import org.springframework.web.multipart.MultipartFile;

public interface NewsService {
    void add(News news);

    News findById(Long id);

    void update(News news);

    void delete(Long id);

    String imageUpload(MultipartFile file);

    PageResult page(Integer limit, Integer page, NewsQueryDto newsQueryDto);

}
