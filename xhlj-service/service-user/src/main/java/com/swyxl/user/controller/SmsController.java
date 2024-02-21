package com.swyxl.user.controller;

import com.swyxl.model.vo.common.Result;
import com.swyxl.model.vo.common.ResultCodeEnum;
import com.swyxl.user.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service/user/sms/auth")
public class SmsController {

    @Autowired
    private SmsService smsService;

    @GetMapping("/sendCode/{phone}")
    public Result sendCode(@PathVariable String phone){
        smsService.send(phone);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
}
