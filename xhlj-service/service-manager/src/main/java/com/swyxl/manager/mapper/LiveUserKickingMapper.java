package com.swyxl.manager.mapper;

import com.github.pagehelper.Page;
import com.swyxl.model.entity.service.manager.LiveUserKicked;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LiveUserKickingMapper {
    void insert(LiveUserKicked liveUserKicked);

    Page<LiveUserKicked> getAll(String cname);

    LiveUserKicked getById(Long id);

    void update(LiveUserKicked liveUserKicked);
}
