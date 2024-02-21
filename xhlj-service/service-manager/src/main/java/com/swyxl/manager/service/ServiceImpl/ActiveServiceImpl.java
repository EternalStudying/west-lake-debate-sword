package com.swyxl.manager.service.ServiceImpl;

import com.swyxl.common.exception.XHLJException;
import com.swyxl.manager.mapper.ActiveMapper;
import com.swyxl.manager.service.ActiveService;
import com.swyxl.model.entity.active.Active;
import com.swyxl.model.vo.common.ResultCodeEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ActiveServiceImpl implements ActiveService {
    @Autowired
    private ActiveMapper activeMapper;

    @Override
    public void add(Active active) {
        Active activeByName = activeMapper.getByName(active.getName());
        //判断活动是否存在
        if(activeByName == null){
            //不存在时
            Active activeOut = new Active();
            //导入数据
            BeanUtils.copyProperties(active, activeOut);
            activeMapper.save(activeOut);
        }else if(activeByName.getName() == active.getName()) {
            //存在时
           throw new XHLJException(ResultCodeEnum.ACTIVE_IS_EXIST);
        }
    }

    @Override
    public Active findById(Long id){
        Active active = activeMapper.findById(id);
        return active;
    }

    @Override
    public void update(Active active) {
        Active activeByName = activeMapper.getByName(active.getName());
        if(activeByName == null){
            throw new XHLJException(ResultCodeEnum.DATA_ERROR);
        }
        Long activeId = activeByName.getId();
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


}
