package com.swyxl.model.entity.service.manager;

import lombok.Data;

import java.util.List;

@Data
public class StreamPlayInfoResponse {
    private List<DayStreamPlayInfo> DataInfoList;
    private String RequestId;
}
