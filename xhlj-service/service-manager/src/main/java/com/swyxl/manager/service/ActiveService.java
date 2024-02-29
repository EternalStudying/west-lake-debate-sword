package com.swyxl.manager.service;

import com.github.pagehelper.Page;
import com.swyxl.model.entity.service.active.Active;
import com.swyxl.model.vo.common.PageResult;
import com.swyxl.model.vo.service.active.ActiveQueryVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ActiveService {
    void add(Active active);

    Active findById( Long id);


    void update(Active active);

    void deleteById(Long id);

    List<Active> getAll();

    Integer getRegister();

    String fileUpload(MultipartFile file);

    PageResult page(Integer limit, Integer page, ActiveQueryVo activeQueryVo);
}
