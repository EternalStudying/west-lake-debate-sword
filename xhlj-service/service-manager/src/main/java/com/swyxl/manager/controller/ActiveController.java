package com.swyxl.manager.controller;

import com.swyxl.manager.service.ActiveService;
import com.swyxl.model.entity.active.Active;
import com.swyxl.model.vo.common.Result;
import com.swyxl.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/service/manager/active/auth")
public class ActiveController {

    @Autowired
    private ActiveService activeService;

    @PostMapping("/add")
    public Result add(@RequestBody Active active){
        activeService.add(active);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @GetMapping("/find/{id}")
    public Result find(@PathVariable Long id){
        Active active = activeService.findById(id);
        return Result.build(active,ResultCodeEnum.SUCCESS);
    }

    @PutMapping("/update")
    public Result update(@RequestBody Active active){
       activeService.update(active);
       return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    @PutMapping("/delete/{id}")
    public Result delete(@PathVariable Long id){
        activeService.deleteById(id);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }
}
