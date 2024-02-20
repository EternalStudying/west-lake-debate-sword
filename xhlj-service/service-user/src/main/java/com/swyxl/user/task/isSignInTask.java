package com.swyxl.user.task;

import com.swyxl.user.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class isSignInTask {

    @Autowired
    private UserInfoMapper userInfoMapper;

    //每天0点将所有用户的签到状态改为未签到
    @Scheduled(cron = "0 0 0 * * ?")
    public void isSignIn20(){
        userInfoMapper.isSignIn20();
    }
}
