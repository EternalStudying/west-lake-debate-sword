package com.swyxl.manager.mapper;

import com.swyxl.model.entity.service.active.Active;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ActiveMapper {
    void save(Active active);

    Active findById(Long id);


    Active getByAcCode(Integer acCode);

    void update(Active active);

    List<Active> getAll();
}

