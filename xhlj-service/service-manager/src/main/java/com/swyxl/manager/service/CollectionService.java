package com.swyxl.manager.service;


import com.swyxl.model.dto.service.manage.CollectionQueryDto;
import com.swyxl.model.entity.service.active.Collection;
import com.swyxl.model.vo.common.PageResult;

public interface CollectionService {
    void add(Collection collection);

    Collection findById(Long id);

    void update(Collection collection);

    void delete(Long id);

    PageResult page(Integer limit, Integer page, CollectionQueryDto collectionQueryDto);
}


