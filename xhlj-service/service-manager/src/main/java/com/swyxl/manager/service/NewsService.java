package com.swyxl.manager.service;

import com.swyxl.model.entity.service.exhibit.News;
import org.springframework.web.multipart.MultipartFile;

public interface NewsService {
    void add(News news);

    News findById(Long id);

    void update(News news);

    void delete(Long id);

    String imageUpload(MultipartFile file);
}
