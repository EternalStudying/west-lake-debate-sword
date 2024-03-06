package com.swyxl.manager.mapper;

import com.swyxl.model.entity.service.manager.Live;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LiveMapper {
    List<Live> selectAll();

    void insert(Live live);

    void update(Live live);
}
