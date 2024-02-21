package com.swyxl.chat.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "xhlj.minio")
@Data
public class MinioProperty {
    private String endpointUrl;
    private String accessKey;
    private String secretKey;
    private String bucketName;
}
