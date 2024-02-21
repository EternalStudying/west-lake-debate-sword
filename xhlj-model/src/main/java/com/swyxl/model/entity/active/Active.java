package com.swyxl.model.entity.active;

import com.swyxl.model.entity.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "活动信息表")
public class Active extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Schema(description = "名称")
    private String name;

    private Integer year;

    private Integer month;

    private Integer day;

    @Schema(description = "详情")
    private String detail;

    @Schema(description = "位置")
    private String location;

    @Schema(description ="图片")
    private String image;

    @Schema(description = "价格")
    private int price;
}
