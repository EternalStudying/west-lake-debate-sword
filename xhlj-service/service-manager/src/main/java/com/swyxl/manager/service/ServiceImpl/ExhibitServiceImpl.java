package com.swyxl.manager.service.ServiceImpl;


import com.swyxl.common.exception.XHLJException;
import com.swyxl.manager.mapper.ExhibitMapper;
import com.swyxl.manager.service.ExhibitService;
import com.swyxl.model.entity.service.exhibit.Exhibit;
import com.swyxl.model.vo.common.ResultCodeEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ExhibitServiceImpl implements ExhibitService {
    @Autowired
    private ExhibitMapper exhibitMapper;



    @Override
    public void add(Exhibit exhibit) {
        Integer exCode = exhibit.getExCode();
        Exhibit exhibitByExCode = exhibitMapper.findByExCode(exCode);
        if(exhibitByExCode == null){
            Exhibit exhibit1 = new Exhibit();
            BeanUtils.copyProperties(exhibit, exhibit1);
            exhibitMapper.save(exhibit1);
        }else if(exhibitByExCode.getExCode() == exhibit.getExCode()){
            throw new XHLJException(ResultCodeEnum.EXHIBITOR_IS_EXIST);
        }
    }

    @Override
    public Exhibit findById(Long id) {
        Exhibit exhibit =  exhibitMapper.findById(id);
        if(exhibit == null){
            throw new XHLJException(ResultCodeEnum.EXHIBITOR_IS_NOT_EXIST);
        }else {
            return exhibit;
        }
    }

    @Override
    public void update(Exhibit exhibit) {
        Integer exCode = exhibit.getExCode();
        Exhibit byExCode = exhibitMapper.findByExCode(exCode);
        if(byExCode==null){
            throw new XHLJException(ResultCodeEnum.EXHIBITOR_IS_NOT_EXIST);
        }
        exhibit.setId(byExCode.getId());
        exhibit.setUpdateTime(new Date());
        exhibitMapper.update(exhibit);
    }

    @Override
    public void delete(Long id) {
        Exhibit byId = exhibitMapper.findById(id);
        if(byId == null){
            throw new XHLJException(ResultCodeEnum.EXHIBITOR_IS_NOT_EXIST);
        }
        byId.setIsDeleted(1);
        exhibitMapper.update(byId);
    }
}
