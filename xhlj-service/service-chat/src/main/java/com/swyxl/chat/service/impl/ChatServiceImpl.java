package com.swyxl.chat.service.impl;

import com.swyxl.chat.properties.ChatProperty;
import com.swyxl.chat.service.ChatService;
import com.swyxl.model.dto.chat.ChatDto;
import com.swyxl.model.vo.chat.ChatVo;
import com.swyxl.utils.ChatUtil;
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
        //TODO 此处为多次调用，之后需更改
        boolean flag = ChatUtil.getAccessToken(apiKey, secretKey);
        if (!flag) {
            throw new RuntimeException();
        }
        ChatVo chatVo = ChatUtil.getAnswer(chatDto.getQuestion(), chatDto.getRequestMessage());
        log.info(chatVo.getAnswer());
        return chatVo;
    }
}
