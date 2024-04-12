package com.swyxl.model.vo.service.prize;

import lombok.Data;

@Data
public class PrizeProbabilityVo {
    private String name;
    private Double probability;

    public PrizeProbabilityVo(String name, Double probability) {
        this.name = name;
        this.probability = probability;
    }

    public PrizeProbabilityVo() {}
}
