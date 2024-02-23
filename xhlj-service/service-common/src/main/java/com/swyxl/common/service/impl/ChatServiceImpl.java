package com.swyxl.common.service.impl;

import com.swyxl.common.properties.ChatProperty;
import com.swyxl.common.service.ChatService;
import com.swyxl.model.dto.chat.ChatDto;
import com.swyxl.model.entity.service.chat.RequestMessage;
import com.swyxl.model.vo.service.chat.ChatVo;
import com.swyxl.utils.ChatUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatProperty chatProperty;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public ChatVo chat(ChatDto chatDto) {
        String apiKey = chatProperty.getApiKey();
        String secretKey = chatProperty.getSecretKey();
        if (chatDto.getRequestMessage() == null){
            chatDto.setRequestMessage(new RequestMessage());
        }
        boolean flag = ChatUtils.getAccessToken(apiKey, secretKey);
        if (!flag) {
            throw new RuntimeException();
        }
        ChatVo chatVo = ChatUtils.getAnswer(chatDto.getQuestion(), chatDto.getRequestMessage());
        log.info(chatVo.getAnswer());
        return chatVo;
    }
}
