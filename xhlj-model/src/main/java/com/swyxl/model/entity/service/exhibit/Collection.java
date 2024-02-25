package com.swyxl.model.entity.service.exhibit;

import com.swyxl.model.entity.base.BaseEntity;
import lombok.Data;

@Data
public class Collection extends BaseEntity {
    private String name;
    private String resource;
    private String type;
    private String subtype;
}
