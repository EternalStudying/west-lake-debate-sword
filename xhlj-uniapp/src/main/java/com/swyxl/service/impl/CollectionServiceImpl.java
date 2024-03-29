package com.swyxl.service.impl;

import com.swyxl.common.exception.XHLJException;
import com.swyxl.feign.common.CommonFeignClient;
import com.swyxl.mapper.CollectionMapper;
import com.swyxl.model.entity.service.exhibit.Achievement;
import com.swyxl.model.vo.common.ResultCodeEnum;
import com.swyxl.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectionServiceImpl implements CollectionService {

    @Autowired
    private CollectionMapper collectionMapper;
    @Autowired
    private CommonFeignClient commonFeignClient;

    @Override
    public List<Achievement> ppt() {
        return collectionMapper.getPpt();
    }

    @Override
    public String download(Long id) {
        Achievement achievement = collectionMapper.selectById(id);
        String url = commonFeignClient.download(achievement.getDownload());
        if (url.isEmpty())
            throw new XHLJException(ResultCodeEnum.FILE_CANT_DOWNLOAD);
        return url;
    }
}
