package com.swyxl.active.controller;

import com.swyxl.active.service.LiveService;
import com.swyxl.model.entity.service.manager.Live;
import com.swyxl.model.vo.common.Result;
import com.swyxl.model.vo.common.ResultCodeEnum;
import com.swyxl.model.vo.service.active.LiveInfoVo;
import com.swyxl.model.vo.service.active.LivePlayVo;
import com.swyxl.model.vo.service.active.LiveVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/service/active/live")
public class LiveController {

    @Autowired
    private LiveService liveService;

    @GetMapping("/auth/getToken")
    public Result getToken(String channelName){
        String token = liveService.getToken(channelName);
        return Result.build(token, ResultCodeEnum.SUCCESS);
    }

    @GetMapping("/allChannel/{limit}/{page}")
    public Result allChannel(@PathVariable Integer limit, @PathVariable Integer page){
        LiveVo liveVo = liveService.allChannel(page, limit);
        return Result.build(liveVo, ResultCodeEnum.SUCCESS);
    }

    @GetMapping("/auth/allRoom")
    public Result allRoom(){
        List<LiveInfoVo> lives = liveService.allRoom();
        return Result.build(lives, ResultCodeEnum.SUCCESS);
    }

    @GetMapping("/auth/pull/{id}")
    public Result pull(@PathVariable Long id){
        LivePlayVo livePlayVo = liveService.pull(id);
        return Result.build(livePlayVo, ResultCodeEnum.SUCCESS);
    }
}
