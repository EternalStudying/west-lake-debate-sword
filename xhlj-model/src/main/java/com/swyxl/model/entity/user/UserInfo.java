package com.swyxl.model.entity.user;

import com.swyxl.model.entity.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "用户实体类")
@Data
public class UserInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "头像")
    private String avatar;

    @Schema(description = "年龄")
    private Integer age;

    @Schema(description = "手机号")
    private String phone;

    @Schema(description = "性别")
    private Short sex;

    @Schema(description = "权限")
    private Short authority;

}
