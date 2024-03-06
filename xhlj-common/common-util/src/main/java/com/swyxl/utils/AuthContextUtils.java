package com.swyxl.utils;

import com.swyxl.model.entity.service.user.UserInfo;

public class AuthContextUtils {

    private static final ThreadLocal<UserInfo> userInfoThreadLocal = new ThreadLocal<>();

    // 定义存储数据的静态方法
    public static void setUserInfo(UserInfo userInfo){
        userInfoThreadLocal.set(userInfo);
    }

    // 定义获取数据的方法
    public static UserInfo getUserInfo(){
        return userInfoThreadLocal.get();
    }

    public static void removeUserInfo(){
        userInfoThreadLocal.remove();
    }

    private static final ThreadLocal<Integer> integerThreadLocal = new ThreadLocal<>();

    public static void setInteger(Integer i){
        integerThreadLocal.set(i);
    }

    public static Integer getInteger(){
        return integerThreadLocal.get();
    }

    public static void removeInteger(){
        integerThreadLocal.remove();
    }
}
