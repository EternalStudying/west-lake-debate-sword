package com.swyxl.model.entity.service.active;

import com.swyxl.model.entity.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "用户活动类")
public class UserActive extends BaseEntity {

    @Schema(description = "用户id")
    private Long userId;

    @Schema(description = "活动id")
    private Long activeId;

    @Schema(description = "结束标志：0未知，1未开始，2正在，3已结束")
    private String isOver;

    @Schema(description = "登录来源")
    private String source;
}
