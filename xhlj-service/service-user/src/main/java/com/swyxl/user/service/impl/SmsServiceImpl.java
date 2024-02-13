package com.swyxl.user.service.impl;

import com.swyxl.user.service.SmsService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class SmsServiceImpl implements SmsService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void send(String phone) {
        String code = redisTemplate.opsForValue().get(phone);
        if (code != null) return;

        code = RandomStringUtils.randomNumeric(6);

        redisTemplate.opsForValue().set(phone, code, 5, TimeUnit.MINUTES);

        sendMessage(phone, code);
    }

    // TODO 未完成
    private void sendMessage(String phone, String code) {

    }
}
