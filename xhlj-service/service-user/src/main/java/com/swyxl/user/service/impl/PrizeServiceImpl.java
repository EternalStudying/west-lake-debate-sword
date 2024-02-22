package com.swyxl.user.service.impl;

import com.swyxl.common.exception.XHLJException;
import com.swyxl.model.entity.service.user.Prize;
import com.swyxl.model.entity.service.user.UserInfo;
import com.swyxl.model.vo.common.ResultCodeEnum;
import com.swyxl.model.vo.service.user.PrizeVo;
import com.swyxl.user.annotation.Recache;
import com.swyxl.user.mapper.PrizeMapper;
import com.swyxl.user.mapper.PrizeRecordMapper;
import com.swyxl.user.mapper.UserInfoMapper;
import com.swyxl.user.service.PrizeService;
import com.swyxl.utils.AuthContextUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class PrizeServiceImpl implements PrizeService {

    @Autowired
    private PrizeMapper prizeMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private PrizeRecordMapper prizeRecordMapper;

    @Override
    @Recache
    public void signIn() {
        UserInfo userInfo = AuthContextUtil.getUserInfo();
        String isSignIn = userInfo.getIsSignIn();
        if (isSignIn.equals("1")){
            throw new XHLJException(ResultCodeEnum.REPEATED_SIGN_IN);
        }
        userInfo.setIsSignIn("1");
        userInfo.setIntegral(userInfo.getIntegral() + 100);
        userInfo.setUpdateTime(new Date());
        userInfoMapper.update(userInfo);
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
    @Transactional
    @Recache
    public Prize draw() {

        UserInfo userInfo = AuthContextUtil.getUserInfo();
        if (userInfo.getIntegral() < 1000)
            throw new XHLJException(ResultCodeEnum.INSUFFICIENT_INTEGRAL);

        //概率
        double winningIndex = BigDecimal.valueOf(new Random().nextDouble() * 100)
                .setScale(1, RoundingMode.HALF_UP)
                .doubleValue();

        List<Prize> prizeList = prizeMapper.selectAll();
        int n = prizeList.size();
        String name = "";
        for(int i = 0; i < n; i++){
            if (winningIndex > sum(i, prizeList) && winningIndex <= sum(i + 1, prizeList))
                name = prizeList.get(i).getName();
        }
        Prize prize = prizeMapper.selectByName(name);
        if (prize.getStock() <= 0){
            prize = prizeMapper.selectByName("谢谢参与");
        }else {
            //减少库存
            prize.setStock(prize.getStock() - 1);
            prizeMapper.update(prize);
        }

        //保存中奖记录
        prizeRecordMapper.save(userInfo.getUsername(), prize.getName());

        //减少积分
        userInfo.setIntegral(userInfo.getIntegral() - 1000);
        userInfoMapper.update(userInfo);

        return prize;
    }

    private double sum(int n, List<Prize> prizeList){
        if (n == 0) return 0;
        double count = 0;
        if (n <= prizeList.size()){
            for(int i = 0; i < n; i++)
                count += Double.parseDouble(prizeList.get(i).getProbability());
        }
        return count;
    }

}
