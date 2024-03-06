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
    ACCOUNT_STOP( 217, "账号已停用"),
    NEWS_IS_EXIST(219,"新闻已存在"),
    NEWS_IS_NOT_EXIST(220,"新闻不存在"),
    PRIZE_IS_NOT_EXIST(221,"奖品不存在"),
    PRIZE_IS_EXIST(222,"奖品已存在"),
    FILE_ERROR(218, "文件上传失败"),
    COLLECTION_IS_EXIST(223,"集锦已存在"),
    COLLECTION_IS_NOT_EXIST(224,"集锦不存在"),
    INVALID_NUMBER(225, "号码状态异常"),
    REPEATED_ENROLL(226, "不可重复报名"),
    ROOM_NOT_EXIST(227, "房间号不存在"),
    RESERVE(228, "预留中"),
    LIVE_USER_ERROR(229, "获取用户列表失败，请重试"),
    CHANNEL_ERROR(230, "直播频道不存在，请刷新"),
    LIVE_NOT_EXIST(231, "当前没有正在直播的活动，请重新刷新"),
    KICKING_ERROR(232, "封禁失败，请重试"),
    UNKICKING_ERROR(233, "解禁失败，请重试"),
    SYSTEM_ERROR(9999 , "您的网络有问题请稍后重试");

    private Integer code ;      // 业务状态码
    private String message ;    // 响应消息

    private ResultCodeEnum(Integer code , String message) {
        this.code = code ;
        this.message = message ;
    }

}
