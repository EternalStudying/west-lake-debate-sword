package com.swyxl.common.service.impl;

import com.alibaba.fastjson.JSON;
import com.swyxl.common.properties.ChatProperty;
import com.swyxl.common.service.ChatService;
import com.swyxl.model.entity.service.chat.RequestMessage;
import com.swyxl.model.vo.service.chat.ChatVo;
import com.swyxl.utils.AuthContextUtils;
import com.swyxl.utils.ChatUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatProperty chatProperty;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public String chat(String question) {
        String apiKey = chatProperty.getApiKey();
        String secretKey = chatProperty.getSecretKey();
        boolean flag = ChatUtils.getAccessToken(apiKey, secretKey);
        if (!flag) {
            throw new RuntimeException();
        }
        RequestMessage requestMessage;
        String s = redisTemplate.opsForValue().get("service:chat:" + AuthContextUtils.getUserInfo().getId());
        if (s == null || s.isEmpty()) requestMessage = new RequestMessage();
        else requestMessage = JSON.parseObject(s, RequestMessage.class);
        ChatVo chatVo = ChatUtils.getAnswer(question, requestMessage);
        redisTemplate.opsForValue().set("service:chat:" + AuthContextUtils.getUserInfo().getId(), JSON.toJSONString(chatVo.getRequestMessage()), 10, TimeUnit.MINUTES);
        log.info(chatVo.getAnswer());
        return chatVo.getAnswer();
    }
}
