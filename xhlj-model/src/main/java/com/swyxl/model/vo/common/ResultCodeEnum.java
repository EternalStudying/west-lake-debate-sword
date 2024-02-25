package com.swyxl.model.vo.common;

import lombok.Getter;

@Getter // 提供获取属性值的getter方法
public enum ResultCodeEnum {

    SUCCESS(200 , "操作成功") ,
    LOGIN_ERROR(201 , "用户名或者密码错误"),
    VALIDATECODE_ERROR(202 , "验证码错误") ,
    ACTIVE_IS_EXIST(203,"活动已经存在"),
    ACTIVE_IS_NOT_EXIST(204,"活动不存在"),
    REPEATED_SIGN_IN(205, "请勿重复签到"),
    INSUFFICIENT_INTEGRAL(206, "积分不足"),
    DATA_ERROR(207, "数据异常"),
    LOGIN_AUTH(208 , "用户未登录"),
    USER_NAME_IS_EXISTS(209 , "用户名已经存在"),
    EXHIBITOR_IS_EXIST(210 , "展商已经存在"),
    EXHIBITOR_IS_NOT_EXIST(211 , "展商不存在"),
    QRCODE_ERROR(212, "二维码生成失败"),
    FILE_NOT_EXIST(213, "文件不存在"),
    FILE_CANT_DOWNLOAD(214, "文件下载失败"),
    ACHIEVEMENT_IS_NOT_EXIST(215,"成果不存在"),
    ACHIEVEMENT_IS_EXIST(216,"成果已存在"),
    SYSTEM_ERROR(9999 , "您的网络有问题请稍后重试"),
    ACCOUNT_STOP( 216, "账号已停用"),
    INVALID_NUMBER(225, "号码状态异常"),
    ;

    private Integer code ;      // 业务状态码
    private String message ;    // 响应消息

    private ResultCodeEnum(Integer code , String message) {
        this.code = code ;
        this.message = message ;
    }

}
