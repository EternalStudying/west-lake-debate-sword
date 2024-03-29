package com.swyxl.controller;

import com.swyxl.model.entity.service.exhibit.Achievement;
import com.swyxl.model.vo.common.Result;
import com.swyxl.model.vo.common.ResultCodeEnum;
import com.swyxl.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/uniapp/collection")
public class CollectionController {

    @Autowired
    private CollectionService collectionService;

    @GetMapping("/auth/ppt")
    public Result ppt(){
        List<Achievement> list = collectionService.ppt();
        return Result.build(list, ResultCodeEnum.SUCCESS);
    }

    @GetMapping("/auth/download/{id}")
    public Result download(@PathVariable Long id){
        String url = collectionService.download(id);
        return Result.build(url, ResultCodeEnum.SUCCESS);
    }
}
