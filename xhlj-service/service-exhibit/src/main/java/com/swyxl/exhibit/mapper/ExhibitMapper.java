package com.swyxl.exhibit.mapper;

import com.swyxl.model.entity.service.exhibit.Exhibit;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ExhibitMapper {
    List<Exhibit> selectAll();

}
