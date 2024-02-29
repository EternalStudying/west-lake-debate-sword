package com.swyxl.exhibit.service.impl;

import com.alibaba.fastjson.JSON;
import com.swyxl.common.exception.XHLJException;
import com.swyxl.exhibit.mapper.AchievementMapper;
import com.swyxl.exhibit.mapper.BusinessMapper;
import com.swyxl.exhibit.mapper.CollectionMapper;
import com.swyxl.exhibit.mapper.NewsMapper;
import com.swyxl.exhibit.service.ExhibitService;
import com.swyxl.feign.common.CommonFeignClient;
import com.swyxl.model.constant.RedisConstant;
import com.swyxl.model.entity.service.exhibit.Achievement;
import com.swyxl.model.entity.service.exhibit.Business;
import com.swyxl.model.entity.service.exhibit.Collection;
import com.swyxl.model.entity.service.exhibit.News;
import com.swyxl.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ExhibitServiceImpl implements ExhibitService {

    @Autowired
    private BusinessMapper businessMapper;
    @Autowired
    private AchievementMapper achievementMapper;
    @Autowired
    private NewsMapper newsMapper;
    @Autowired
    private CollectionMapper collectionMapper;
    @Autowired
    private CommonFeignClient commonFeignClient;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public List<Business> businessList() {
        String businessListJSON = redisTemplate.opsForValue().get(RedisConstant.SERVICE_BUSINESS + "all");
        List<Business> businessList;
        if (businessListJSON != null){
            businessList = JSON.parseArray(businessListJSON, Business.class);
            return businessList;
        }
        businessList = businessMapper.selectAll();
        redisTemplate.opsForValue().set(RedisConstant.SERVICE_BUSINESS + "all", JSON.toJSONString(businessList));
        return businessList;
    }

    @Override
    public List<Achievement> achievementList() {
        String achievementListJSON = redisTemplate.opsForValue().get(RedisConstant.SERVICE_ACHIEVEMENT + "all");
        List<Achievement> achievementList;
        if (achievementListJSON != null){
            achievementList = JSON.parseArray(achievementListJSON, Achievement.class);
            return achievementList;
        }
        achievementList = achievementMapper.selectAll();
        redisTemplate.opsForValue().set(RedisConstant.SERVICE_ACHIEVEMENT + "all", JSON.toJSONString(achievementList));
        return achievementList;
    }

    @Override
    public String download(Long id) {
        Achievement achievement = achievementMapper.selectById(id);
        String url = commonFeignClient.download(achievement.getDownload());
        if (url.isEmpty())
            throw new XHLJException(ResultCodeEnum.FILE_CANT_DOWNLOAD);
        return url;
    }

    //TODO 数据库优化，将查询数据保存到redis中，太过于繁琐，优化至此放弃
    @Override
    public List<News> newsList() {
        return newsMapper.selectAll();
    }

    @Override
    public List<Collection> collectionList() {
        return collectionMapper.selectAll();
    }
}
