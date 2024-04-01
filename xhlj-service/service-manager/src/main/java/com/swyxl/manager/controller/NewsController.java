package com.swyxl.manager.controller;

import com.swyxl.manager.service.NewsService;
import com.swyxl.model.entity.service.exhibit.News;
import com.swyxl.model.vo.common.PageResult;
import com.swyxl.model.vo.common.Result;
import com.swyxl.model.vo.common.ResultCodeEnum;
import com.swyxl.model.dto.service.manage.NewsQueryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/service/manager/news/auth")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @PostMapping("/add")
    public Result add(@RequestBody News news){
        newsService.add(news);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @GetMapping("/find/{id}")
    public Result find(@PathVariable Long id){
        News news = newsService.findById(id);
        return Result.build(news,ResultCodeEnum.SUCCESS);
    }

    @PutMapping("/update")
    public Result update(@RequestBody News news){
        newsService.update(news);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    @PutMapping("/delete/{id}")
    public Result delete(@PathVariable  Long id){
        newsService.delete(id);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    @PostMapping("/imageUpload")
    public Result imageUpload(MultipartFile file){
        String url = newsService.imageUpload(file);
        return Result.build(url, ResultCodeEnum.SUCCESS);
    }

    @PostMapping("/pageSelect/{limit}/{page}")
    public Result page(@PathVariable Integer limit,
                       @PathVariable Integer page,
                       @RequestBody NewsQueryDto newsQueryDto){
        PageResult pageResult = newsService.page(limit,page, newsQueryDto);
        return Result.build(pageResult,ResultCodeEnum.SUCCESS);
    }
}
