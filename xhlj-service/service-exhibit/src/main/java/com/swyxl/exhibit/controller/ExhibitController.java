package com.swyxl.exhibit.controller;

import com.swyxl.exhibit.service.ExhibitService;
import com.swyxl.model.entity.service.exhibit.Achievement;
import com.swyxl.model.entity.service.exhibit.Business;
import com.swyxl.model.entity.service.exhibit.Collection;
import com.swyxl.model.entity.service.exhibit.News;
import com.swyxl.model.vo.common.Result;
import com.swyxl.model.vo.common.ResultCodeEnum;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/service/exhibit")
public class ExhibitController {

    @Autowired
    private ExhibitService exhibitService;

    @GetMapping("/business/list")
    public Result businessList(){
        List<Business> businesses = exhibitService.businessList();
        return Result.build(businesses, ResultCodeEnum.SUCCESS);
    }

    @GetMapping("/achievement/list")
    public Result achievementList(){
        List<Achievement> achievements = exhibitService.achievementList();
        return Result.build(achievements, ResultCodeEnum.SUCCESS);
    }

    @GetMapping("/auth/download/{id}")
    public Result download(@PathVariable Long id){
        String url = exhibitService.download(id);
        return Result.build(url, ResultCodeEnum.SUCCESS);
    }

    @GetMapping("/news/list")
    public Result newsList(){
        List<News> news = exhibitService.newsList();
        return Result.build(news, ResultCodeEnum.SUCCESS);
    }

    @GetMapping("/collection/list")
    public Result collectionList(){
        List<Collection> collections = exhibitService.collectionList();
        return Result.build(collections, ResultCodeEnum.SUCCESS);
    }
}
