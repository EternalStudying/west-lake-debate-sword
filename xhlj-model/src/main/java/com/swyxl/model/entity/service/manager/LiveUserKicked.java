package com.swyxl.model.entity.service.manager;

import com.swyxl.model.entity.base.BaseEntity;
import lombok.Data;

@Data
public class LiveUserKicked extends BaseEntity {
    private Long uid;
    private String uname;
    private Integer rid;
    private Integer time;
    private String cname;
}
