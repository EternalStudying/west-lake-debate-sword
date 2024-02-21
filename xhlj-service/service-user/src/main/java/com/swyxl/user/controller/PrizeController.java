package com.swyxl.user.controller;

import com.swyxl.model.entity.user.Prize;
import com.swyxl.model.entity.user.UserInfo;
import com.swyxl.model.vo.common.Result;
import com.swyxl.model.vo.common.ResultCodeEnum;
import com.swyxl.model.vo.service.user.PrizeVo;
import com.swyxl.user.service.PrizeService;
import com.swyxl.utils.AuthContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service/user")
public class PrizeController {

    @Autowired
    private PrizeService prizeService;

    @PutMapping("/auth/signIn")
    public Result signIn(){
        prizeService.signIn();
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @GetMapping("/auth/prize/all")
    public Result prizeAll(){
        List<PrizeVo> prizeVoList = prizeService.prizeAll();
        return Result.build(prizeVoList, ResultCodeEnum.SUCCESS);
    }

    @GetMapping("/auth/draw")
    public Result draw(){
        Prize prize = prizeService.draw();
        return Result.build(prize, ResultCodeEnum.SUCCESS);
    }


}
