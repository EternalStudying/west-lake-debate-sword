package com.swyxl.manager.controller;

import com.swyxl.manager.service.ExhibitService;
import com.swyxl.model.entity.service.exhibit.Business;
import com.swyxl.model.vo.common.Result;
import com.swyxl.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/service/manager/exhibit/auth")
public class ExhibitController {

    @Autowired
    private ExhibitService exhibitService;

    @PostMapping("/add")
    public Result add(@RequestBody Business business){
        exhibitService.add(business);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @GetMapping("/find/{id}")
    public Result find(@PathVariable Long id){
        Business business = exhibitService.findById(id);
        return Result.build(business,ResultCodeEnum.SUCCESS);
    }

    @PutMapping("/update")
    public Result update(@RequestBody Business business){
        exhibitService.update(business);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    @PutMapping("/delete/{id}")
    public Result delete(@PathVariable Long id){
        exhibitService.delete(id);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    @PostMapping("/imageUpload")
    public Result imageUpload(MultipartFile file){
        String url = exhibitService.imageUpload(file);
        return Result.build(url, ResultCodeEnum.SUCCESS);
    }
}
