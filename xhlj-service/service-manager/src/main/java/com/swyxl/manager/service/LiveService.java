package com.swyxl.manager.service;

import com.swyxl.model.dto.service.active.KickingDto;
import com.swyxl.model.dto.service.active.LiveDto;
import com.swyxl.model.dto.service.manage.LiveEditDto;
import com.swyxl.model.dto.service.manage.LiveQueryDto;
import com.swyxl.model.entity.service.manager.Live;
import com.swyxl.model.vo.common.PageResult;
import com.swyxl.model.vo.service.manager.LiveUserVo;
import org.springframework.web.multipart.MultipartFile;

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

    void start(LiveDto liveDto);

    PageResult AllRoom(Integer limit, Integer page, LiveQueryDto liveQueryDto);

    List<Live> myRoom();

    void editRoom(LiveEditDto liveEditDto);

    void over(Long id);

    String upload(MultipartFile file);

    Live room(Long id);
}
