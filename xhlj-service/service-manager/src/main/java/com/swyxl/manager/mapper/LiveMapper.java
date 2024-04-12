package com.swyxl.manager.mapper;

import com.github.pagehelper.Page;
import com.swyxl.model.dto.service.manage.LiveQueryDto;
import com.swyxl.model.entity.service.manager.Live;
import com.swyxl.model.entity.service.manager.StreamOnlineInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LiveMapper {
    List<Live> selectAll();

    void insert(Live live);

    void update(Live live);

    Page<Live> page(LiveQueryDto liveQueryDto);

    List<Live> getByUid(Long userId);

    void status22(List<StreamOnlineInfo> onlineInfos);

    void updateOnline(Integer online, String identifier);

    Live getById(Long id);

    void deleteRoom(Long id);

    void status23(List<StreamOnlineInfo> onlineInfos);
}
