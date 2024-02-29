package com.swyxl.active.service.impl;

import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import com.alibaba.fastjson.JSON;
import com.swyxl.active.mapper.ActiveMapper;
import com.swyxl.active.mapper.ActiveUserMapper;
import com.swyxl.active.service.ActiveService;
import com.swyxl.common.exception.XHLJException;
import com.swyxl.model.constant.ActiveConstant;
import com.swyxl.model.constant.RedisConstant;
import com.swyxl.model.entity.service.active.Active;
import com.swyxl.model.entity.service.active.UserActive;
import com.swyxl.model.vo.common.ResultCodeEnum;
import com.swyxl.model.vo.service.active.ActiveShareVo;
import com.swyxl.utils.AuthContextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActiveServiceImpl implements ActiveService {

    @Autowired
    private ActiveUserMapper activeUserMapper;
    @Autowired
    private ActiveMapper activeMapper;
    @Autowired
    RedisTemplate<String, String> redisTemplate;
    @Autowired
    private QrConfig config;

    @Override
    @Transactional
    public void enroll(Long activeId) {
        Long userId = AuthContextUtils.getUserInfo().getId();
        UserActive userActive = new UserActive();
        userActive.setUserId(userId);
        userActive.setActiveId(activeId);
        userActive.setIsOver(ActiveConstant.NOT_STARTED);
        activeUserMapper.save(userActive);
        activeMapper.addEnrollment(activeId);
        redisTemplate.delete(RedisConstant.SERVICE_ACTIVE + "all");
        redisTemplate.delete(RedisConstant.SERVICE_ACTIVE_USER + userId);
    }

    @Override
    public List<Active> list() {
        List<Active> activeList;
        String activeListJSON = redisTemplate.opsForValue().get(RedisConstant.SERVICE_ACTIVE + "all");
        if (activeListJSON != null){
             activeList = JSON.parseArray(activeListJSON, Active.class);
             return activeList;
        }
        activeList = activeMapper.selectAll();
        redisTemplate.opsForValue().set(RedisConstant.SERVICE_ACTIVE + "all", JSON.toJSONString(activeList));
        return activeList;
    }

    @Override
    public List<Active> active() {
        Long userId = AuthContextUtils.getUserInfo().getId();
        List<Active> activeList;
        String activeListJSON = redisTemplate.opsForValue().get(RedisConstant.SERVICE_ACTIVE_USER + userId);
        if (activeListJSON != null){
            activeList = JSON.parseArray(activeListJSON, Active.class);
            return activeList;
        }
        activeList = activeMapper.selectByUserId(userId);
        redisTemplate.opsForValue().set(RedisConstant.SERVICE_ACTIVE_USER + userId, JSON.toJSONString(activeList));
        return activeList;
    }

    @Override
    public void like(Long id) {
        activeMapper.addLike(id);
        redisTemplate.delete(RedisConstant.SERVICE_ACTIVE + "all");
    }

    @Override
    public ActiveShareVo share(Long id) {
        ActiveShareVo activeShareVo = new ActiveShareVo();
        String avatar = AuthContextUtils.getUserInfo().getAvatar();
        activeShareVo.setAvatar(avatar);
        Active active = activeMapper.selectById(id);
        activeShareVo.setActiveName(active.getName());
        activeShareVo.setDes(active.getDes());
        activeShareVo.setYear(active.getYear());
        activeShareVo.setMonth(active.getMonth());
        activeShareVo.setDay(active.getDay());
        try {
            String qrCodeBase64 = QrCodeUtil.generateAsBase64("https://www.gcsis.cn/", config, "png");
            activeShareVo.setQrCode(qrCodeBase64);
        }catch (Exception e){
            e.printStackTrace();
            throw new XHLJException(ResultCodeEnum.QRCODE_ERROR);
        }
        return activeShareVo;
    }
}
