package com.swyxl.model.entity.active;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.swyxl.model.entity.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "活动信息表")
public class activeInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Schema(description = "名称")
    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "日期")
    private Data date;

    @Schema(description = "详情")
    private String detail;

    @Schema(description = "位置")
    private String location;

    @Schema(description ="图片")
    private String image;

    @Schema(description = "价格")
    private int price;
}
