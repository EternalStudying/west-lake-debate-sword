package com.swyxl.active.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "xhlj.live")
public class LiveProperty {
    private String appId;
    private String appCertificate;
    private int tokenExpirationInSeconds;
    private int privilegeExpirationInSeconds;
    private String key;
    private String secret;
}
