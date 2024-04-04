package com.swyxl.model.entity.service.manager;

import lombok.Data;

import java.util.List;

@Data
public class StreamOnlineInfo {

    @Data
    static class PublishTime{
        private String PublishTime;
    }

    private String StreamName;
    private List<PublishTime> PublishTimeList;
    private String AppName;
    private String DomainName;
    private Integer PushToDelay;
}
