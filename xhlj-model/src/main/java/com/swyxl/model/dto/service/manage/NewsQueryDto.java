package com.swyxl.model.dto.service.manage;

import lombok.Data;

import java.io.Serializable;

@Data
public class NewsQueryDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String title;

    private String introduction;
}
