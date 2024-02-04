package com.swyxl.chat.service;

import com.swyxl.model.dto.chat.ChatDto;
import com.swyxl.model.vo.chat.ChatVo;

public interface ChatService {
    ChatVo chat(ChatDto chatDto);
}
