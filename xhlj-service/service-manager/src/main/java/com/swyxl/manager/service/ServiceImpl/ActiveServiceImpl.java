package com.swyxl.manager.service.ServiceImpl;


import com.swyxl.common.client.CommonFeignClient;
import com.swyxl.common.exception.XHLJException;
import com.swyxl.manager.mapper.ActiveMapper;
import com.swyxl.manager.service.ActiveService;
import com.swyxl.model.entity.service.active.Active;
import com.swyxl.model.vo.common.Result;
import com.swyxl.model.vo.common.ResultCodeEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ActiveServiceImpl implements ActiveService {
    @Autowired
    private ActiveMapper activeMapper;

    @Autowired
    private CommonFeignClient commonFeignClient;

    @Override
    public void add(Active active) {
        Active activeByAcCode = activeMapper.getByAcCode(active.getAcCode());
        //判断活动是否存在
        if(activeByAcCode == null){

            //不存在时
            Active activeOut = new Active();
            //导入数据
            BeanUtils.copyProperties(active, activeOut);
            activeMapper.save(activeOut);

        }else if(activeByAcCode.getAcCode() == active.getAcCode()) {
            //存在时
           throw new XHLJException(ResultCodeEnum.ACTIVE_IS_EXIST);
        }
    }

    @Override
    public Active findById(Long id){
        Active active = activeMapper.findById(id);
        if(active == null){
            throw new XHLJException(ResultCodeEnum.ACTIVE_IS_NOT_EXIST);
        }else {
            return active;
        }
    }

    @Override
    public void update(Active active) {
        Active activeByAcCode = activeMapper.getByAcCode(active.getAcCode());
        if(activeByAcCode == null){
            throw new XHLJException(ResultCodeEnum.DATA_ERROR);
        }
        Long activeId = activeByAcCode.getId();
        active.setId(activeId);
        active.setUpdateTime(new Date());
        activeMapper.update(active);
    }

    @Override
    public void deleteById(Long id) {
        Active active = activeMapper.findById(id);
        if(active == null){
            throw new XHLJException(ResultCodeEnum.ACTIVE_IS_NOT_EXIST);
        }else {
            active.setIsDeleted(1);
            activeMapper.update(active);
        }

    }

    @Override
    public List<Active> getAll() {
        List<Active> list = activeMapper.getAll();
        return list;
    }

}
