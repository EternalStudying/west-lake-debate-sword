package com.swyxl.manager.controller;

import com.swyxl.manager.service.ExhibitService;
import com.swyxl.model.entity.service.exhibit.Business;
import com.swyxl.model.vo.common.PageResult;
import com.swyxl.model.vo.common.Result;
import com.swyxl.model.vo.common.ResultCodeEnum;
import com.swyxl.model.dto.service.exhibit.ExhibitQueryDto;
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

    @GetMapping("pageSelect/{limit}/{page}")
    public Result pageSelect(@PathVariable Integer limit,
                             @PathVariable Integer page,
                             @RequestBody ExhibitQueryDto exhibitQueryDto){
        PageResult pageResult = exhibitService.page(limit,page, exhibitQueryDto);
        return Result.build(pageResult,ResultCodeEnum.SUCCESS);
    }
}
