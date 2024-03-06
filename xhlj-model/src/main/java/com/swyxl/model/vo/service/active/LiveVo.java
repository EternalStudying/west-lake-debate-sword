package com.swyxl.model.vo.service.active;

import com.swyxl.model.entity.service.manager.Live;
import lombok.Data;

import java.util.List;

@Data
public class LiveVo {
    private List<Live> lives;
    private Integer totalSize;
}
