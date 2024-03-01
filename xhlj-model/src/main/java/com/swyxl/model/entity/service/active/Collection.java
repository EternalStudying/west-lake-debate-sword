package com.swyxl.model.entity.service.active;

import com.swyxl.model.entity.base.BaseEntity;
import lombok.Data;

@Data
public class Collection extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private String name;

    private String resource;

    private String type;

    private String subtype;


}
