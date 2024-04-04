package com.swyxl.model.entity.service.manager;

import lombok.Data;

import java.util.List;

@Data
public class LiveStreamOnlineResponse {
    private Integer TotalNum;
    private Integer TotalPage;
    private Integer PageNum;
    private Integer PageSize;
    private List<StreamOnlineInfo> OnlineInfo;
    private String RequestId;
}
