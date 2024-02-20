package com.swyxl.test;

import com.alibaba.fastjson.JSON;
import com.swyxl.model.entity.user.UserInfo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

public class DateTest {
    public static void main(String[] args) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUpdateTime(new Date());
        String jsonString = JSON.toJSONString(userInfo.getUpdateTime());
        System.out.println(jsonString);
    }
}
