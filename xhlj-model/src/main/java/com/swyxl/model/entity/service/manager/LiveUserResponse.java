package com.swyxl.model.entity.service.manager;

import lombok.Data;

import java.util.List;

@Data
public class LiveUserResponse {

    @Data
    public static class Response{
        private Boolean channel_exist;
        private Integer mode;
        private List<Integer> broadcasters;
        private List<Integer> audience;
        private Integer audience_total;
    }

    private Boolean success;
    private Response data;
}
