package com.swyxl.model.vo.service.chat;

import com.swyxl.model.entity.service.chat.RequestMessage;
import lombok.Data;

@Data
public class ChatVo {
    private RequestMessage requestMessage;
    private String answer;
}
