package com.swyxl.model.entity.service.exhibit;

import com.swyxl.model.entity.base.BaseEntity;
import lombok.Data;

@Data
public class Prize extends BaseEntity {

    private String name;

    private String image;

    private String level;

    private String probability;

    private Integer stock;

    private String description;
}
