package com.swyxl.manager.mapper;

import com.github.pagehelper.Page;

import com.swyxl.model.dto.service.manage.CollectionQueryDto;
import com.swyxl.model.entity.service.active.Collection;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CollectionMapper {
    Collection getByName(String name);

    void save(Collection collection);

    Collection findById(Long id);

    void update(Collection collection1);

    Page<Collection> page(CollectionQueryDto collectionQueryDto);
}
