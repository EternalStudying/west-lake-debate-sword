package com.swyxl.user.service;

import com.swyxl.model.entity.service.user.Prize;
import com.swyxl.model.vo.service.user.PrizeVo;

import java.util.List;

public interface PrizeService {
    void signIn();

    List<PrizeVo> prizeAll();

    Prize draw();
}
