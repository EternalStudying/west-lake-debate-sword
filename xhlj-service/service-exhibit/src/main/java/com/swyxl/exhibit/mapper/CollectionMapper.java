package com.swyxl.exhibit.mapper;

import com.swyxl.model.entity.service.exhibit.Collection;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CollectionMapper {
    List<Collection> selectAll();
}
