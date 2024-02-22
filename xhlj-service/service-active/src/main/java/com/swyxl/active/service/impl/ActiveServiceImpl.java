package com.swyxl.active.service.impl;

import com.swyxl.active.mapper.ActiveUserMapper;
import com.swyxl.active.service.ActiveService;
import com.swyxl.model.constant.ActiveConstant;
import com.swyxl.model.entity.service.active.UserActive;
import com.swyxl.utils.AuthContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActiveServiceImpl implements ActiveService {

    @Autowired
    private ActiveUserMapper activeUserMapper;

    @Override
    public void enroll(Long activeId) {
        Long userId = AuthContextUtil.getUserInfo().getId();
        UserActive userActive = new UserActive();
        userActive.setUserId(userId);
        userActive.setActiveId(activeId);
        userActive.setIsOver(ActiveConstant.NOT_STARTED);
        activeUserMapper.save(userActive);
    }
}
