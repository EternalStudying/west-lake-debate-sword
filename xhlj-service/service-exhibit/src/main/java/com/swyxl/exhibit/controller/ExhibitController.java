package com.swyxl.exhibit.controller;

import com.swyxl.exhibit.service.ExhibitService;
import com.swyxl.model.entity.service.exhibit.Exhibit;
import com.swyxl.model.vo.common.Result;
import com.swyxl.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/service/exhibit")
public class ExhibitController {

    @Autowired
    private ExhibitService exhibitService;

    @GetMapping("/business/list")
    public Result list(){
        List<Exhibit> exhibits = exhibitService.list();
        return Result.build(exhibits, ResultCodeEnum.SUCCESS);
    }
}
