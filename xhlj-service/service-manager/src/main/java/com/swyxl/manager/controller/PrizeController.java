package com.swyxl.manager.controller;

import com.swyxl.manager.service.PrizeService;
import com.swyxl.model.entity.service.exhibit.Prize;
import com.swyxl.model.vo.common.PageResult;
import com.swyxl.model.vo.common.Result;
import com.swyxl.model.vo.common.ResultCodeEnum;
import com.swyxl.model.vo.service.exhibit.PrizeQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//奖品增删改查
@RestController
@RequestMapping("/service/manager/prize/auth")
public class PrizeController {


    @Autowired
    private PrizeService prizeService;
    @PostMapping("/add")
    public Result add(@RequestBody Prize prize){
        prizeService.add(prize);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @GetMapping("/find/{id}")
    public Result findById(@PathVariable Long id){
        Prize prize = prizeService.getById(id);
        return  Result.build(prize,ResultCodeEnum.SUCCESS);
    }

    @PutMapping("/update")
    public Result update(@RequestBody Prize prize){
        prizeService.update(prize);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    @PutMapping("/delete/{id}")
    public Result delete(@PathVariable Long id){
        prizeService.deleteById(id);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    @GetMapping("/pageSelect/{limit}/{page}")
    public Result page(@PathVariable Integer limit,
                       @PathVariable Integer page,
                       @RequestBody PrizeQueryVo prizeQueryVo){
        PageResult pageResult =  prizeService.page(limit,page,prizeQueryVo);
        return Result.build(pageResult,ResultCodeEnum.SUCCESS);
    }
}
