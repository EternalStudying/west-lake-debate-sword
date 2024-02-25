package com.swyxl.model.entity.service.exhibit;

import com.swyxl.model.entity.base.BaseEntity;
import lombok.Data;

@Data
public class News extends BaseEntity {
    private String title;
    private String introduction;
    private String text;
    private String image;
    private String source;
}
