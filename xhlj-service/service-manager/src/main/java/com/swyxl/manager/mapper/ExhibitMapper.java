package com.swyxl.manager.mapper;

import com.github.pagehelper.Page;
import com.swyxl.model.entity.service.exhibit.Business;
import com.swyxl.model.vo.service.exhibit.ExhibitQueryVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ExhibitMapper {
    Business findByExCode(Integer exCode);

    void save(Business business1);

    Business findById(Long id);

    void update(Business business);

    Page<Business> pageByName(ExhibitQueryVo exhibitQueryVo);
}
