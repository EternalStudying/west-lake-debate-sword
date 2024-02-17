package com.swyxl.user.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "xhlj.aliyun")
public class SmsProperty {
    private String accessKey;
    private String accessKeySecret;
    private String signName;
    private String templateCode;
}
