package com.swyxl.manager.service.ServiceImpl;

import com.swyxl.common.exception.XHLJException;
import com.swyxl.feign.common.CommonFeignClient;
import com.swyxl.manager.mapper.NewsMapper;
import com.swyxl.manager.service.NewsService;
import com.swyxl.model.constant.TypeConstant;
import com.swyxl.model.entity.service.exhibit.News;
import com.swyxl.model.vo.common.ResultCodeEnum;
import org.apache.ibatis.scripting.xmltags.XMLLanguageDriver;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsMapper newsMapper;
    @Autowired
    private CommonFeignClient commonFeignClient;
    @Override
    public void add(News news) {
        News news1 = newsMapper.getByTitle(news.getTitle());
        if(news1 != null){
            throw new XHLJException(ResultCodeEnum.NEWS_IS_EXIST);
        }else {
            newsMapper.save(news);
        }
    }

    @Override
    public News findById(Long id) {
        News news =newsMapper.getById(id);
        if(news == null){
            throw new XHLJException(ResultCodeEnum.NEWS_IS_NOT_EXIST);
        }else {
            return news;
        }
    }

    @Override
    public void update(News news) {
        News news1 =  newsMapper.getById(news.getId());
        if(news1 == null){
            throw new XHLJException(ResultCodeEnum.NEWS_IS_NOT_EXIST);
        }else {
            BeanUtils.copyProperties(news,news1);
            news1.setUpdateTime(new Date());
            newsMapper.update(news1);
        }
    }

    @Override
    public void delete(Long id) {
        News news = newsMapper.getById(id);
        if(news == null){
            throw new XHLJException(ResultCodeEnum.NEWS_IS_NOT_EXIST);
        }else {
            news.setIsDeleted(1);
            news.setUpdateTime(new Date());
            newsMapper.update(news);
        }
    }

    @Override
    public String imageUpload(MultipartFile file) {
        String url = commonFeignClient.fileUpload(file, TypeConstant.NEWS);
        if (url.isEmpty())
            throw new XHLJException(ResultCodeEnum.FILE_ERROR);
        return url;
    }
}
