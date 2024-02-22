package com.swyxl.manager.mapper;

import com.swyxl.model.entity.service.exhibit.Exhibitor;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ExhibitMapper {
    Exhibitor findByExCode(Integer exCode);

    void save(Exhibitor exhibitor1);

    Exhibitor findById(Long id);

    void update(Exhibitor exhibitor);

}
