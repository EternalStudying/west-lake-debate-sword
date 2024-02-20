package com.swyxl.model.entity.user;

import com.swyxl.model.entity.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "奖品实体类")
public class Prize extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Schema(description = "名字")
    private String name;

    @Schema(description = "图像")
    private String image;

    @Schema(description = "几等奖")
    private String level;

    @Schema(description = "详情")
    private String description;

}
