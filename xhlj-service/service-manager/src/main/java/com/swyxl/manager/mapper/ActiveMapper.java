package com.swyxl.manager.mapper;

import com.swyxl.model.entity.active.Active;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ActiveMapper {
    void save(Active active);

    Active findById(Long id);


    Active getByName(String name);

    void update(Active active);


}

