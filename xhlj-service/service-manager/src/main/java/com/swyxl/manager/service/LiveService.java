package com.swyxl.manager.service;

import com.swyxl.model.dto.service.active.KickingDto;
import com.swyxl.model.dto.service.active.LiveDto;
import com.swyxl.model.entity.service.manager.Live;
import com.swyxl.model.vo.common.PageResult;
import com.swyxl.model.vo.service.manager.LiveUserVo;

import java.util.List;

public interface LiveService {
    LiveUserVo getUser(String channelName);

    List<Live> getLive();

    void kickingRuleSet(KickingDto kickingDto);

    PageResult kickingRuleGet(Integer limit, Integer page, String cname);

    void kickingRuleDelete(Long id);

    void createLive(LiveDto liveDto);

    void startLive(Long id);

    void overLive(Long id);
}
