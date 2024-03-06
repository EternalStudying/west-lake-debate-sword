package com.swyxl.active.controller;

import com.swyxl.active.service.ActiveService;
import com.swyxl.model.entity.service.active.Active;
import com.swyxl.model.vo.common.PageResult;
import com.swyxl.model.vo.common.Result;
import com.swyxl.model.vo.common.ResultCodeEnum;
import com.swyxl.model.vo.service.active.ActiveShareVo;
import com.swyxl.utils.AuthContextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service/active/active")
public class ActiveController {

    @Autowired
    private ActiveService activeService;

    @GetMapping("/auth/enroll/{id}")
    public Result enroll(@PathVariable Long id){
        activeService.enroll(id);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @GetMapping("/list")
    public Result list(){
        List<Active> activeList = activeService.list();
        return Result.build(activeList, ResultCodeEnum.SUCCESS);
    }

    @GetMapping("auth/active/{limit}/{page}")
    public Result active(@PathVariable Integer limit, @PathVariable Integer page){
        Long userId = AuthContextUtils.getUserInfo().getId();
        PageResult pageResult = activeService.active(userId, limit, page);
        return Result.build(pageResult, ResultCodeEnum.SUCCESS);
    }

    @PutMapping("/auth/like/{id}")
    public Result like(@PathVariable Long id){
        activeService.like(id);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @GetMapping("/auth/share/{id}")
    public Result share(@PathVariable Long id){
        ActiveShareVo activeShareVo = activeService.share(id);
        return Result.build(activeShareVo, ResultCodeEnum.SUCCESS);
    }
}
