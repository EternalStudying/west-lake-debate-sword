package com.swyxl.exhibit.service.impl;

import com.swyxl.common.exception.XHLJException;
import com.swyxl.exhibit.mapper.AchievementMapper;
import com.swyxl.exhibit.mapper.BusinessMapper;
import com.swyxl.exhibit.mapper.CollectionMapper;
import com.swyxl.exhibit.mapper.NewsMapper;
import com.swyxl.exhibit.service.ExhibitService;
import com.swyxl.feign.common.CommonFeignClient;
import com.swyxl.model.entity.service.exhibit.Achievement;
import com.swyxl.model.entity.service.exhibit.Business;
import com.swyxl.model.entity.service.exhibit.Collection;
import com.swyxl.model.entity.service.exhibit.News;
import com.swyxl.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Business> businessList() {
        return businessMapper.selectAll();
    }

    @Override
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
    public List<News> newsList() {
        return newsMapper.selectAll();
    }

    @Override
    public List<Collection> collectionList() {
        return collectionMapper.selectAll();
    }
}
