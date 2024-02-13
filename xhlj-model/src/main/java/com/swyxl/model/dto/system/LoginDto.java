package com.swyxl.model.dto.system;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "登录提交参数")
@Data
public class LoginDto {

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "手机号")
    private String phone;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "手机验证码")
    private String captcha;

    @Schema(description = "登录方式")
    private Short method;
}
