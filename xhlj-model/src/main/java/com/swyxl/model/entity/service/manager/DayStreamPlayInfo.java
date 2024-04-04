package com.swyxl.model.entity.service.manager;

import lombok.Data;

@Data
public class DayStreamPlayInfo {
    private String Time;
    private Float Bandwidth;
    private Float Flux;
    private Integer Request;
    private Integer Online;
}
