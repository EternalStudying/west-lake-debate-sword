package com.swyxl.manager.controller;

import com.swyxl.manager.service.LiveService;
import com.swyxl.model.dto.service.active.KickingDto;
import com.swyxl.model.dto.service.active.LiveDto;
import com.swyxl.model.entity.service.manager.Live;
import com.swyxl.model.vo.common.PageResult;
import com.swyxl.model.vo.common.Result;
import com.swyxl.model.vo.common.ResultCodeEnum;
import com.swyxl.model.vo.service.manager.LiveUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service/manager/live/auth")
public class LiveController {

    @Autowired
    private LiveService liveService;

    @GetMapping("/getLive")
    public Result getLive(){
        List<Live> lives = liveService.getLive();
        return Result.build(lives, ResultCodeEnum.SUCCESS);
    }

    @GetMapping("/getUser")
    public Result getUser(String channelName){
        LiveUserVo liveUserVo = liveService.getUser(channelName);
        return Result.build(liveUserVo, ResultCodeEnum.SUCCESS);
    }

    @PostMapping("/createLive")
    public Result createLive(@RequestBody LiveDto liveDto){
        liveService.createLive(liveDto);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @PutMapping("/startLive/{id}")
    public Result startLive(@PathVariable Long id){
        liveService.startLive(id);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @PutMapping("/overLive/{id}")
    public Result overLive(@PathVariable Long id){
        liveService.overLive(id);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @PostMapping("/kicking-rule")
    public Result kickingRuleSet(@RequestBody KickingDto kickingDto){
        liveService.kickingRuleSet(kickingDto);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @PostMapping("/kicking-rule/{limit}/{page}")
    public Result kickingRuleGet(@PathVariable Integer limit, @PathVariable Integer page, String cname){
        PageResult pageResult = liveService.kickingRuleGet(limit, page, cname);
        return Result.build(pageResult, ResultCodeEnum.SUCCESS);
    }

    @DeleteMapping("/kicking-rule/{id}")
    public Result kickingRuleDelete(@PathVariable Long id){
        liveService.kickingRuleDelete(id);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
}
