package com.swyxl.manager.service.ServiceImpl;


import com.swyxl.common.exception.XHLJException;
import com.swyxl.manager.mapper.ExhibitMapper;
import com.swyxl.manager.service.ExhibitService;
import com.swyxl.model.entity.service.exhibit.Exhibitor;
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
    public void add(Exhibitor exhibitor) {
        Integer exCode = exhibitor.getExCode();
        Exhibitor exhibitByExCode = exhibitMapper.findByExCode(exCode);
        if(exhibitByExCode == null){
            Exhibitor exhibitor1 = new Exhibitor();
            BeanUtils.copyProperties(exhibitor,exhibitor1);
            exhibitMapper.save(exhibitor1);
        }else if(exhibitByExCode.getExCode() == exhibitor.getExCode()){
            throw new XHLJException(ResultCodeEnum.EXHIBITOR_IS_EXIST);
        }
    }

    @Override
    public Exhibitor findById(Long id) {
        Exhibitor exhibitor =  exhibitMapper.findById(id);
        if(exhibitor == null){
            throw new XHLJException(ResultCodeEnum.EXHIBITOR_IS_NOT_EXIST);
        }else {
            return exhibitor;
        }
    }

    @Override
    public void update(Exhibitor exhibitor) {
        Integer exCode = exhibitor.getExCode();
        Exhibitor byExCode = exhibitMapper.findByExCode(exCode);
        if(byExCode==null){
            throw new XHLJException(ResultCodeEnum.EXHIBITOR_IS_NOT_EXIST);
        }
        exhibitor.setId(byExCode.getId());
        exhibitor.setUpdateTime(new Date());
        exhibitMapper.update(exhibitor);
    }

    @Override
    public void delete(Long id) {
        Exhibitor byId = exhibitMapper.findById(id);
        if(byId == null){
            throw new XHLJException(ResultCodeEnum.EXHIBITOR_IS_NOT_EXIST);
        }
        byId.setIsDeleted(1);
        exhibitMapper.update(byId);
    }
}
