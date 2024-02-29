package com.swyxl.model.vo.service.exhibit;

import lombok.Data;

import java.io.Serializable;
@Data
public class PrizeQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;

    private String level;
}
