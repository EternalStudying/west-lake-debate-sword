package com.swyxl.manager.controller;

import com.swyxl.manager.service.AchievementService;
import com.swyxl.model.entity.service.exhibit.Achievement;
import com.swyxl.model.vo.common.PageResult;
import com.swyxl.model.vo.common.Result;
import com.swyxl.model.vo.common.ResultCodeEnum;
import com.swyxl.model.dto.service.exhibit.AchievementQueryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/service/manager/achievement/auth")
public class AchievementController {

    @Autowired
    private AchievementService achievementService;

    @PostMapping("/add")
    public Result add(@RequestBody Achievement achievement){
        achievementService.add(achievement);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @GetMapping("/find/{id}")
    public Result findById(@PathVariable Long id){
        Achievement achievement = achievementService.findById(id);
        return Result.build(achievement,ResultCodeEnum.SUCCESS);
    }

    @PutMapping("/update")
    public Result update(@RequestBody Achievement achievement){
        achievementService.update(achievement);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Long id){
        achievementService.deleteById(id);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    @PostMapping("/imageUpload")
    public Result imageUpload(MultipartFile file){
        String url = achievementService.imageUpload(file);
        return Result.build(url, ResultCodeEnum.SUCCESS);
    }

    @PostMapping("/resourceUpload")
    public Result resourceUpload(MultipartFile file){
        String fileName = achievementService.resourceUpload(file);
        return Result.build(fileName, ResultCodeEnum.SUCCESS);
    }

    @GetMapping("/pageSelect/{limit}/{page}")
    public Result page(@PathVariable Integer limit,
                       @PathVariable Integer page,
                       @RequestBody AchievementQueryDto achievementQueryDto){
        PageResult pageResult = achievementService.page(limit,page, achievementQueryDto);
        return Result.build(pageResult,ResultCodeEnum.SUCCESS);
    }
}
