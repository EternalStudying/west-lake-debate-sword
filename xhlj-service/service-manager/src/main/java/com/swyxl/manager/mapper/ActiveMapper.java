package com.swyxl.manager.mapper;

import com.github.pagehelper.Page;
import com.swyxl.model.entity.service.active.Active;
import com.swyxl.model.dto.service.active.ActiveQueryDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ActiveMapper {
    void save(Active active);

    Active findById(Long id);


    Active getByAcCode(Integer acCode);

    void update(Active active);

    List<Active> getAll();

    Page<Active> selectLikeName(ActiveQueryDto activeQueryDto);
}

