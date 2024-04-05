package com.swyxl.active.mapper;

import com.swyxl.model.entity.service.active.Channel;
import com.swyxl.model.entity.service.manager.Live;
import com.swyxl.model.vo.service.active.LivePlayVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LiveMapper {
    List<Live> batchSelectByName(List<Channel> channels);

    List<Live> allRoom();

    LivePlayVo pull(Long id);
}
