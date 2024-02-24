package com.swyxl.manager.mapper;

import com.swyxl.model.entity.service.exhibit.Exhibit;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ExhibitMapper {
    Exhibit findByExCode(Integer exCode);

    void save(Exhibit exhibit1);

    Exhibit findById(Long id);

    void update(Exhibit exhibit);

}
