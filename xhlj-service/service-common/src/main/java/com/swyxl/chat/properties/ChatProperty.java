package com.swyxl.chat.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "xhlj.chat")
public class ChatProperty {
    private String apiKey;
    private String secretKey;
}
