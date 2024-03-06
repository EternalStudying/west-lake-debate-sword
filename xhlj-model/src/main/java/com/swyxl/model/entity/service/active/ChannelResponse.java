package com.swyxl.model.entity.service.active;

import lombok.Data;

import java.util.List;

@Data
public class ChannelResponse {

    @Data
    public static class ChannelVo {
        private List<Channel> channels;
        private Integer total_size;
    }

    private Boolean success;
    private ChannelVo data;
}
