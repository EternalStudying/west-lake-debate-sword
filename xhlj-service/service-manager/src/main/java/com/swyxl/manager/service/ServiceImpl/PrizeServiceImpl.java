package com.swyxl.manager.service.ServiceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.swyxl.common.exception.XHLJException;
import com.swyxl.manager.mapper.PrizeMapper;
import com.swyxl.manager.service.PrizeService;
import com.swyxl.model.entity.service.exhibit.Prize;
import com.swyxl.model.vo.common.PageResult;
import com.swyxl.model.vo.common.ResultCodeEnum;
import com.swyxl.model.dto.service.manage.PrizeQueryDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PrizeServiceImpl implements PrizeService {

    @Autowired
    private PrizeMapper prizeMapper;
    @Override
    public void add(Prize prize) {
       Prize prize1 =  prizeMapper.getByName(prize);
       if(prize1 != null){
           throw new XHLJException(ResultCodeEnum.PRIZE_IS_EXIST);
       }else {
           prizeMapper.save(prize);
       }
    }

    @Override
    public Prize getById(Long id) {
        Prize prize = prizeMapper.getById(id);
        if(prize == null){
            throw new XHLJException(ResultCodeEnum.PRIZE_IS_NOT_EXIST);
        }else {
            return prize;
        }
    }

    @Override
    public void update(Prize prize) {
        Prize prize1 = prizeMapper.getById(prize.getId());
        if(prize1 == null){
            throw new XHLJException(ResultCodeEnum.PRIZE_IS_NOT_EXIST);
        }else {
            BeanUtils.copyProperties(prize,prize1);
            prize1.setUpdateTime(new Date());
            prizeMapper.update(prize1);
        }
    }

    @Override
    public void deleteById(Long id) {
        Prize prize = prizeMapper.getById(id);
        if(prize == null){
            throw new XHLJException(ResultCodeEnum.PRIZE_IS_NOT_EXIST);
        }else {
            prize.setUpdateTime(new Date());
            prize.setId(id);
            prize.setIsDeleted(1);
            prizeMapper.update(prize);
        }
    }

    @Override
    public PageResult page(Integer limit, Integer page, PrizeQueryDto prizeQueryDto) {
        PageHelper.startPage(page,limit);
        Page<Prize> newsPage = prizeMapper.pageLike(prizeQueryDto);
        PageResult pageResult = new PageResult();
        pageResult.setTotal(newsPage.getTotal());
        pageResult.setRecords(newsPage.getResult());
        return pageResult;
    }
}
