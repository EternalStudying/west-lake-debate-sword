package com.swyxl.active.service.impl;

import com.swyxl.active.mapper.ActiveMapper;
import com.swyxl.active.mapper.ActiveUserMapper;
import com.swyxl.active.service.ActiveService;
import com.swyxl.model.constant.ActiveConstant;
import com.swyxl.model.entity.service.active.Active;
import com.swyxl.model.entity.service.active.UserActive;
import com.swyxl.utils.AuthContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ActiveServiceImpl implements ActiveService {

    @Autowired
    private ActiveUserMapper activeUserMapper;
    @Autowired
    private ActiveMapper activeMapper;

    @Override
    @Transactional
    public void enroll(Long activeId) {
        Long userId = AuthContextUtil.getUserInfo().getId();
        UserActive userActive = new UserActive();
        userActive.setUserId(userId);
        userActive.setActiveId(activeId);
        userActive.setIsOver(ActiveConstant.NOT_STARTED);
        activeUserMapper.save(userActive);
        activeMapper.addEnrollment(activeId);
    }

    @Override
    public List<Active> list() {
        return activeMapper.selectAll();
    }

    @Override
    public List<Active> active() {
        Long userId = AuthContextUtil.getUserInfo().getId();
        return activeMapper.selectByUserId(userId);
    }

    @Override
    public void like(Long id) {
        activeMapper.addLike(id);
    }
}
