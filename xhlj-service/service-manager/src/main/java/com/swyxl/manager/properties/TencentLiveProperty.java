package com.swyxl.manager.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "xhlj.tencent.live")
public class TencentLiveProperty {
    private String pushDomain;
    private String pullDomain;
    private String appName;
    private String key;
}
