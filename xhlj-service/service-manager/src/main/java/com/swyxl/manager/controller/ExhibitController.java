package com.swyxl.manager.controller;

import com.swyxl.manager.service.ExhibitService;
import com.swyxl.model.entity.service.exhibit.Exhibit;
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
    public Result add(@RequestBody Exhibit exhibit){
        exhibitService.add(exhibit);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @GetMapping("/find/{id}")
    public Result find(@PathVariable Long id){
        Exhibit exhibit = exhibitService.findById(id);
        return Result.build(exhibit,ResultCodeEnum.SUCCESS);
    }

    @PutMapping("/update")
    public Result update(@RequestBody Exhibit exhibit){
        exhibitService.update(exhibit);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    @PutMapping("/delete/{id}")
    public Result delete(@PathVariable Long id){
        exhibitService.delete(id);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }
}
