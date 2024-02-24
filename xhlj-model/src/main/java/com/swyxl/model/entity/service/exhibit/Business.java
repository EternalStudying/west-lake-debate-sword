package com.swyxl.model.entity.service.exhibit;

import com.swyxl.model.entity.base.BaseEntity;
import lombok.Data;

@Data
public class Business extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String name;

    private String description;

    private String image;

    private String detail;

    private String url;

    private Integer exCode;

}
