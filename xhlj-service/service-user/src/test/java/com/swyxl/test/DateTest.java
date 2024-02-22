package com.swyxl.test;

import com.alibaba.fastjson.JSON;
import com.swyxl.model.entity.service.user.UserInfo;

import java.util.Date;

public class DateTest {
    public static void main(String[] args) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUpdateTime(new Date());
        String jsonString = JSON.toJSONString(userInfo.getUpdateTime());
        System.out.println(jsonString);
    }
}
