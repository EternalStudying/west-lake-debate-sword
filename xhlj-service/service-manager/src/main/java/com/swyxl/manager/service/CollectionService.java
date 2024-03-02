package com.swyxl.manager.service;


import com.swyxl.model.dto.service.manage.CollectionQueryDto;
import com.swyxl.model.entity.service.exhibit.Collection;
import com.swyxl.model.vo.common.PageResult;
import org.springframework.web.multipart.MultipartFile;

public interface CollectionService {
    void add(Collection collection);

    Collection findById(Long id);

    void update(Collection collection);

    void delete(Long id);

    PageResult page(Integer limit, Integer page, CollectionQueryDto collectionQueryDto);

    String imageUpload(MultipartFile file);

    String videoUpload(MultipartFile file);
}


