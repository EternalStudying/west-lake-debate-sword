package com.swyxl.manager.service.ServiceImpl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.swyxl.common.exception.XHLJException;
import com.swyxl.feign.common.CommonFeignClient;
import com.swyxl.manager.mapper.ExhibitMapper;
import com.swyxl.manager.service.ExhibitService;
import com.swyxl.model.constant.TypeConstant;
import com.swyxl.model.entity.service.exhibit.Business;
import com.swyxl.model.vo.common.PageResult;
import com.swyxl.model.vo.common.ResultCodeEnum;
import com.swyxl.model.dto.service.manage.ExhibitQueryDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Service
public class ExhibitServiceImpl implements ExhibitService {
    @Autowired
    private ExhibitMapper exhibitMapper;
    @Autowired
    private CommonFeignClient commonFeignClient;


    @Override
    public void add(Business business) {
        Integer exCode = business.getExCode();
        Business businessByExCode = exhibitMapper.findByExCode(exCode);
        if(businessByExCode == null){
            Business business1 = new Business();
            BeanUtils.copyProperties(business, business1);
            exhibitMapper.save(business1);
        }else if(businessByExCode.getExCode() == business.getExCode()){
            throw new XHLJException(ResultCodeEnum.EXHIBITOR_IS_EXIST);
        }
    }

    @Override
    public Business findById(Long id) {
        Business business =  exhibitMapper.findById(id);
        if(business == null){
            throw new XHLJException(ResultCodeEnum.EXHIBITOR_IS_NOT_EXIST);
        }else {
            return business;
        }
    }

    @Override
    public void update(Business business) {
        Integer exCode = business.getExCode();
        Business byExCode = exhibitMapper.findByExCode(exCode);
        if(byExCode==null){
            throw new XHLJException(ResultCodeEnum.EXHIBITOR_IS_NOT_EXIST);
        }
        business.setId(byExCode.getId());
        business.setUpdateTime(new Date());
        exhibitMapper.update(business);
    }

    @Override
    public void delete(Long id) {
        Business byId = exhibitMapper.findById(id);
        if(byId == null){
            throw new XHLJException(ResultCodeEnum.EXHIBITOR_IS_NOT_EXIST);
        }
        byId.setIsDeleted(1);
        exhibitMapper.update(byId);
    }

    @Override
    public String imageUpload(MultipartFile file) {
        String url = commonFeignClient.fileUpload(file, TypeConstant.BUSINESS);
        if (url.isEmpty())
            throw new XHLJException(ResultCodeEnum.FILE_ERROR);
        return url;
    }

    @Override
    public PageResult page(Integer limit, Integer page, ExhibitQueryDto exhibitQueryDto) {
        PageHelper.startPage(page,limit);
        Page<Business> businessPage =  exhibitMapper.pageByName(exhibitQueryDto);
        PageResult pageResult = new PageResult();
        pageResult.setRecords(businessPage.getResult());
        pageResult.setTotal(businessPage.getTotal());
        return pageResult;
    }
}
