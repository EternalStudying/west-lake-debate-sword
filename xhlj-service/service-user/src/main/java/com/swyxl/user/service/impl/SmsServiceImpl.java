package com.swyxl.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.*;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.swyxl.common.exception.XHLJException;
import com.swyxl.model.constant.UserInfoConstant;
import com.swyxl.model.vo.common.ResultCodeEnum;
import com.swyxl.user.properties.SmsProperty;
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
    @Autowired
    private SmsProperty smsProperty;

    @Override
    public void send(String phone) {
        String code = redisTemplate.opsForValue().get(phone);

        //如果验证码在redis数据库中不存在，则生成验证码，若存在，则使用之前的那个
        if (code == null) code = RandomStringUtils.randomNumeric(6);

        redisTemplate.opsForValue().set(UserInfoConstant.SERVICE_CAPTCHA + phone, code, 5, TimeUnit.MINUTES);

        sendMessage(phone, code);
    }

    private void sendMessage(String phone, String code) {
        String accessKey = smsProperty.getAccessKey();
        String accessKeySecret = smsProperty.getAccessKeySecret();
        String signName = smsProperty.getSignName();
        String templateCode = smsProperty.getTemplateCode();
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKey, accessKeySecret);
        IAcsClient client = new DefaultAcsClient(profile);

        SendSmsRequest request = new SendSmsRequest();
        request.setPhoneNumbers(phone);
        request.setSignName(signName);
        request.setTemplateCode(templateCode);
        request.setTemplateParam("{\"code\":\"" + code + "\"}");

        try{
            SendSmsResponse response = client.getAcsResponse(request);
            System.out.println(JSON.toJSONString(response));
        }catch (ServerException e){
            throw new XHLJException(ResultCodeEnum.SYSTEM_ERROR);
        }catch (ClientException e){
            throw new XHLJException(ResultCodeEnum.INVALID_NUMBER);
        }
    }
}
