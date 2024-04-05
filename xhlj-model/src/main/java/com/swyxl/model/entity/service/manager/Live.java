package com.swyxl.model.entity.service.manager;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.swyxl.model.entity.base.BaseEntity;
import lombok.Data;

import java.util.Date;

@Data
public class Live extends BaseEntity {
    private String identifier;
    private String name;
    private String cover;
    private Integer participants;
    private Integer status;
    private String pushAdd;
    private String pullAdd;
    private String video;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date beginTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date overTime;

    private Long createUid;
    private String createName;
    private String createAvatar;
}
