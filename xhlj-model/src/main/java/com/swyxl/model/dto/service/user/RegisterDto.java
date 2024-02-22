package com.swyxl.model.dto.service.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "注册传递参数")
public class RegisterDto {

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "手机号")
    private String phone;

    @Schema(description = "验证码")
    private String captcha;
}
