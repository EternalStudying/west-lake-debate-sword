package com.swyxl.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.*;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.swyxl.common.exception.XHLJException;
import com.swyxl.model.constant.RedisConstant;
import com.swyxl.model.vo.common.ResultCodeEnum;
import com.swyxl.user.properties.SmsProperty;
import com.swyxl.user.service.SmsService;
import com.tencentcloudapi.common.AbstractModel;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.sms.v20210111.SmsClient;
import com.tencentcloudapi.sms.v20210111.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20210111.models.SendSmsResponse;
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
        String code = redisTemplate.opsForValue().get(RedisConstant.SERVICE_CAPTCHA + phone);

        //如果验证码在redis数据库中不存在，则生成验证码，若存在，则使用之前的那个
        if (code == null) code = RandomStringUtils.randomNumeric(6);

        redisTemplate.opsForValue().set(RedisConstant.SERVICE_CAPTCHA + phone, code, 5, TimeUnit.MINUTES);

        sendMessage(phone, code);
    }

    private void sendMessage(String phone, String code) {
        try{
            Credential cred = new Credential(smsProperty.getSecretId(), smsProperty.getSecretKey());
            SmsClient client = new SmsClient(cred, "ap-nanjing");
            // 实例化一个请求对象,每个接口都会对应一个request对象
            SendSmsRequest req = new SendSmsRequest();
            String[] phoneNumberSet1 = {phone};
            req.setPhoneNumberSet(phoneNumberSet1);

            req.setSmsSdkAppId(smsProperty.getSmsSdkAppId());
            req.setSignName(smsProperty.getSignName());
            req.setTemplateId(smsProperty.getTemplateId());

            String[] templateParamSet1 = {code};
            req.setTemplateParamSet(templateParamSet1);

            // 返回的resp是一个SendSmsResponse的实例，与请求对象对应
            SendSmsResponse resp = client.SendSms(req);
            // 输出json格式的字符串回包
            System.out.println(AbstractModel.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
    }
    }

    /*private void sendMessage(String phone, String code) {
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
    }*/
