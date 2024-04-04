package com.swyxl.manager.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "xhlj.tencent.yun")
@Data
public class TencentProperty {
    private String secretId;
    private String secretKey;
}
