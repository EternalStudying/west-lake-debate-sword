package com.swyxl.model.vo.service.active;

import lombok.Data;

@Data
public class ActiveShareVo {
    private String avatar;
    private String activeName;
    private Integer year;
    private Integer month;
    private Integer day;
    private String des;
    private String qrCode;//base64编码
}
