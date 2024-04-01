package com.swyxl.manager.controller;

import com.swyxl.manager.service.PrizeService;
import com.swyxl.model.dto.service.prize.PrizeProbabilityDto;
import com.swyxl.model.entity.service.exhibit.Prize;
import com.swyxl.model.vo.common.PageResult;
import com.swyxl.model.vo.common.Result;
import com.swyxl.model.vo.common.ResultCodeEnum;
import com.swyxl.model.dto.service.manage.PrizeQueryDto;
import com.swyxl.model.vo.service.prize.PrizeProbabilityVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

    @PostMapping("/pageSelect/{limit}/{page}")
    public Result page(@PathVariable Integer limit,
                       @PathVariable Integer page,
                       @RequestBody PrizeQueryDto prizeQueryDto){
        PageResult pageResult =  prizeService.page(limit,page, prizeQueryDto);
        return Result.build(pageResult,ResultCodeEnum.SUCCESS);
    }

    @PostMapping("/imageUpload")
    public Result imageUpload(MultipartFile file){
        String url = prizeService.upload(file);
        return Result.build(url, ResultCodeEnum.SUCCESS);
    }

    @GetMapping("/getProbability")
    public Result getProbability(){
        List<PrizeProbabilityVo> prizeProbabilityVoList = prizeService.getProbability();
        return Result.build(prizeProbabilityVoList, ResultCodeEnum.SUCCESS);
    }

    @PostMapping("/updateProbability")
    public Result updateProbability(@RequestBody List<PrizeProbabilityDto> prizeProbabilityDtos){
        prizeService.updateProbability(prizeProbabilityDtos);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
}
