package com.swyxl.user.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
//@ConfigurationProperties(prefix = "xhlj.aliyun")
@ConfigurationProperties(prefix = "xhlj.tencentyun")
public class SmsProperty {
//    private String accessKey;
//    private String accessKeySecret;
//    private String signName;
//    private String templateCode;
    private String secretId;
    private String secretKey;
    private String smsSdkAppId;
    private String signName;
    private String templateId;
}
