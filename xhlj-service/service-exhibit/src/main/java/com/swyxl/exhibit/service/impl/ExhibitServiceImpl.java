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
import org.springframework.cache.annotation.Cacheable;
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

    @Override
    @Cacheable(value = "service:business", key = "#root.methodName", sync = true)
    public List<Business> businessList() {
        return businessMapper.selectAll();
    }

    @Override
    @Cacheable(value = "service:achievement", key = "#root.methodName", sync = true)
    public List<Achievement> achievementList() {
        return achievementMapper.selectAll();
    }

    @Override
    public String download(Long id) {
        Achievement achievement = achievementMapper.selectById(id);
        String url = commonFeignClient.download(achievement.getDownload());
        if (url.isEmpty())
            throw new XHLJException(ResultCodeEnum.FILE_CANT_DOWNLOAD);
        return url;
    }

    @Override
    @Cacheable(value = "service:news", key = "#root.methodName", sync = true)
    public List<News> newsList() {
        return newsMapper.selectAll();
    }

    @Override
    @Cacheable(value = "service:collection", key = "#root.methodName", sync = true)
    public List<Collection> collectionList() {
        return collectionMapper.selectAll();
    }
}
