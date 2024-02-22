package com.swyxl.active.controller;

import com.swyxl.active.service.ActiveService;
import com.swyxl.model.vo.common.Result;
import com.swyxl.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service/active/active")
public class ActiveController {

    @Autowired
    private ActiveService activeService;

    @GetMapping("/auth/enroll/{activeId}")
    public Result enroll(@PathVariable Long activeId){
        activeService.enroll(activeId);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
}
