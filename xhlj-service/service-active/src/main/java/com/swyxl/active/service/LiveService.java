package com.swyxl.active.service;

import com.swyxl.model.vo.service.active.LiveInfoVo;
import com.swyxl.model.vo.service.active.LiveVo;

import java.util.List;

public interface LiveService {
    String getToken(String channelName);

    LiveVo allChannel(Integer page, Integer limit);

    List<LiveInfoVo> allRoom();

    String pull(Long id);
}
