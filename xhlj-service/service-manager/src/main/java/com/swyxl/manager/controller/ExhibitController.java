package com.swyxl.manager.controller;

import com.swyxl.manager.service.ExhibitService;
import com.swyxl.model.entity.service.exhibit.Exhibitor;
import com.swyxl.model.vo.common.Result;
import com.swyxl.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/service/manager/exhibit/auth")
public class ExhibitController {

    @Autowired
    private ExhibitService exhibitService;

    @PostMapping("/add")
    public Result add(@RequestBody Exhibitor exhibitor){
        exhibitService.add(exhibitor);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @GetMapping("/find/{id}")
    public Result find(@PathVariable Long id){
        Exhibitor exhibitor = exhibitService.findById(id);
        return Result.build(exhibitor,ResultCodeEnum.SUCCESS);
    }

    @PutMapping("/update")
    public Result update(@RequestBody Exhibitor exhibitor){
        exhibitService.update(exhibitor);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    @PutMapping("/delete/{id}")
    public Result delete(@PathVariable Long id){
        exhibitService.delete(id);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }
}
