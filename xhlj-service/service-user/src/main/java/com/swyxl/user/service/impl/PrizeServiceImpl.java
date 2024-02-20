package com.swyxl.user.service.impl;

import com.swyxl.common.exception.XHLJException;
import com.swyxl.model.entity.user.Prize;
import com.swyxl.model.entity.user.UserInfo;
import com.swyxl.model.vo.common.ResultCodeEnum;
import com.swyxl.model.vo.service.user.PrizeVo;
import com.swyxl.user.mapper.PrizeMapper;
import com.swyxl.user.mapper.UserInfoMapper;
import com.swyxl.user.service.PrizeService;
import com.swyxl.utils.AuthContextUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PrizeServiceImpl implements PrizeService {

    @Autowired
    private PrizeMapper prizeMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public void signIn() {
        UserInfo userInfo = AuthContextUtil.getUserInfo();
        String isSignIn = userInfo.getIsSignIn();
        Integer integral = userInfo.getIntegral();
        if (isSignIn.equals("1")){
            throw new XHLJException(ResultCodeEnum.REPEATED_SIGN_IN);
        }
        UserInfo userInfoData = new UserInfo();
        userInfoData.setIsSignIn("1");
        userInfoData.setIntegral(integral + 100);
        userInfoMapper.update(userInfoData);
    }

    @Override
    public List<PrizeVo> prizeAll() {
        List<Prize> prizeList = prizeMapper.selectAll();
        List<PrizeVo> prizeVoList = new ArrayList<>();
        for (Prize prize : prizeList) {
            PrizeVo prizeVo = new PrizeVo();
            BeanUtils.copyProperties(prize, prizeVo);
            prizeVoList.add(prizeVo);
        }
        return prizeVoList;
    }

    @Override
    public Prize prizeDrawn(Long id) {
        return prizeMapper.selectById(id);
    }
}
