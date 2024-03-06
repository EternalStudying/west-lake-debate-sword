package com.swyxl.model.vo.service.manager;

import lombok.Data;
import com.swyxl.model.entity.service.manager.LiveUser;

import java.util.List;

@Data
public class LiveUserVo {

    private List<LiveUser> broadcasters;
    private List<LiveUser> audiences;
    private Integer audienceTotal;
}
