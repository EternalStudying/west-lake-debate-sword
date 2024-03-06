package com.swyxl.active.service.impl;

import com.alibaba.fastjson.JSON;
import com.swyxl.active.mapper.LiveMapper;
import com.swyxl.active.properties.LiveProperty;
import com.swyxl.active.service.LiveService;
import com.swyxl.common.exception.XHLJException;
import com.swyxl.model.entity.service.active.Channel;
import com.swyxl.model.entity.service.active.ChannelResponse;
import com.swyxl.model.entity.service.manager.Live;
import com.swyxl.model.vo.common.ResultCodeEnum;
import com.swyxl.model.vo.service.active.LiveVo;
import com.swyxl.utils.AuthContextUtils;
import com.swyxl.utils.HttpClientUtils;
import io.agora.media.RtcTokenBuilder2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LiveServiceImpl implements LiveService {

    @Autowired
    private LiveProperty liveProperty;
    @Autowired
    private LiveMapper liveMapper;

    @Override
    public String getToken(String channelName) {
        if (channelName.isEmpty())
            throw new XHLJException(ResultCodeEnum.ROOM_NOT_EXIST);
        Long userId = AuthContextUtils.getUserInfo().getId();
        RtcTokenBuilder2 token = new RtcTokenBuilder2();
        return token.buildTokenWithUid(
                liveProperty.getAppId(),
                liveProperty.getAppCertificate(),
                channelName,
                Math.toIntExact(userId),
                RtcTokenBuilder2.Role.ROLE_SUBSCRIBER,
                liveProperty.getTokenExpirationInSeconds(),
                liveProperty.getPrivilegeExpirationInSeconds()
        );
    }

    @Override
    @Cacheable(value = "service:live", key = "#root.methodName", sync = true)
    public LiveVo allChannel(Integer page, Integer limit) {
        // 拼接客户 ID 和客户密钥并使用 base64 编码
        String plainCredentials = liveProperty.getKey() + ":" + liveProperty.getSecret();
        String base64Credentials = new String(Base64.getEncoder().encode(plainCredentials.getBytes()));
        // 创建 authorization header
        String authorizationHeader = "Basic " + base64Credentials;

        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("page_no", (page - 1) + "");
        queryMap.put("page_size", limit + "");
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", authorizationHeader);
        headers.put("Content-Type", "application/json");
        String result = HttpClientUtils.sendGet("http://api.sd-rtn.com/dev/v1/channel/" + liveProperty.getAppId(), headers, queryMap);

        ChannelResponse channelResponse = JSON.parseObject(result, ChannelResponse.class);

        if (!channelResponse.getSuccess())
            throw new XHLJException(ResultCodeEnum.RESERVE);

        ChannelResponse.ChannelVo data = channelResponse.getData();

        if (data.getTotal_size() == 0)
            throw new XHLJException(ResultCodeEnum.LIVE_NOT_EXIST);

        LiveVo liveVo = new LiveVo();
        liveVo.setTotalSize(data.getTotal_size());

        List<Channel> channels = data.getChannels();
        List<Live> lives = liveMapper.batchSelectByName(channels);

        liveVo.setLives(lives);
        return liveVo;
    }
}
