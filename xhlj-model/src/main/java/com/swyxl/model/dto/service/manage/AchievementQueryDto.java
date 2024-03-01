package com.swyxl.model.dto.service.manage;

import lombok.Data;

import java.io.Serializable;

@Data
public class AchievementQueryDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;

    private String des;
}
