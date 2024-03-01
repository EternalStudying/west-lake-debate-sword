package com.swyxl.manager.service;

import com.swyxl.model.entity.service.active.Active;
import com.swyxl.model.vo.common.PageResult;
import com.swyxl.model.dto.service.active.ActiveQueryDto;
import com.swyxl.model.vo.service.active.ActiveStatisticVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ActiveService {
    void add(Active active);

    Active findById( Long id);


    void update(Active active);

    void deleteById(Long id);

    List<Active> getAll();

    String fileUpload(MultipartFile file);

    PageResult page(Integer limit, Integer page, ActiveQueryDto activeQueryDto);

    ActiveStatisticVo getStatistics(Long id);
}
