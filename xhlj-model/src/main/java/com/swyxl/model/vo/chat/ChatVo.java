package com.swyxl.model.vo.chat;

import com.swyxl.model.entity.chat.RequestMessage;
import lombok.Data;

@Data
public class ChatVo {
    private RequestMessage requestMessage;
    private String answer;
}
