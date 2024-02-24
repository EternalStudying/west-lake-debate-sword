package com.swyxl.model.entity.service.exhibit;

import com.swyxl.model.entity.base.BaseEntity;
import lombok.Data;

@Data
public class Achievement extends BaseEntity {
    private String name;
    private String des;
    private String image;
    private String type;
    private String download;
}
