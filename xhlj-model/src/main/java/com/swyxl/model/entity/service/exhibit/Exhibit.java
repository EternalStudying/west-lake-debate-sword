package com.swyxl.model.entity.service.exhibit;

import com.swyxl.model.entity.base.BaseEntity;
import lombok.Data;

@Data
public class Exhibit extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String name;

    private String description;

    private String image;

    private String detail;

    private Integer exCode;

}
