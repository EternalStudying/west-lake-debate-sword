package com.swyxl.manager.controller;

import com.swyxl.manager.service.AchievementService;
import com.swyxl.model.entity.service.exhibit.Achievement;
import com.swyxl.model.vo.common.Result;
import com.swyxl.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/delete/{id}")
    public Result delete(@PathVariable Long id){
        achievementService.deleteById(id);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }
}
