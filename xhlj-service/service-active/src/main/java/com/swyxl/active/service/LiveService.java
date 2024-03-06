package com.swyxl.active.service;

import com.swyxl.model.vo.service.active.LiveVo;

public interface LiveService {
    String getToken(String channelName);

    LiveVo allChannel(Integer page, Integer limit);
}
