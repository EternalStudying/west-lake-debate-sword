package com.swyxl.manager.controller;

import com.swyxl.manager.service.CollectionService;

import com.swyxl.model.dto.service.manage.CollectionQueryDto;
import com.swyxl.model.entity.service.exhibit.Collection;
import com.swyxl.model.vo.common.PageResult;
import com.swyxl.model.vo.common.Result;
import com.swyxl.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/service/manager/collection/auth")
public class CollectionController {
    @Autowired
    private CollectionService collectionService;

    @PostMapping("/add")
    public Result add(@RequestBody Collection collection){
        collectionService.add(collection);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @GetMapping("/find/{id}")
    public Result findById(@PathVariable Long id){
        Collection collection = collectionService.findById(id);
        return Result.build(collection,ResultCodeEnum.SUCCESS);
    }

    @PutMapping("/update")
    public Result update(@RequestBody Collection collection){
        collectionService.update(collection);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    @PutMapping("/delete/{id}")
    public Result delete(@PathVariable Long id){
        collectionService.delete(id);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }


    @GetMapping("/pageSelect/{limit}/{page}")
    public Result page(@PathVariable Integer limit,
                       @PathVariable Integer page,
                       @RequestBody CollectionQueryDto collectionQueryDto){
        PageResult pageResult = collectionService.page(limit,page,collectionQueryDto);
        return Result.build(pageResult,ResultCodeEnum.SUCCESS);
    }

    @PostMapping("/imageUpload")
    public Result imageUpload(MultipartFile file){
        String url = collectionService.imageUpload(file);
        return Result.build(url, ResultCodeEnum.SUCCESS);
    }

    @PostMapping("/videoUpload")
    public Result videoUpload(MultipartFile file){
        String url = collectionService.videoUpload(file);
        return Result.build(url, ResultCodeEnum.SUCCESS);
    }
}
