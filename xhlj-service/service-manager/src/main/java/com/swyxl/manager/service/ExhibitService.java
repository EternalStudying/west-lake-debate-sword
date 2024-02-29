package com.swyxl.manager.service;

import com.swyxl.model.entity.service.exhibit.Business;
import com.swyxl.model.vo.common.PageResult;
import com.swyxl.model.vo.service.exhibit.ExhibitQueryVo;
import org.springframework.web.multipart.MultipartFile;

public interface ExhibitService {
    void add(Business business);

    Business findById(Long id);

    void update(Business business);

    void delete(Long exhibitor);

    String imageUpload(MultipartFile file);

    PageResult page(Integer limit, Integer page, ExhibitQueryVo exhibitQueryVo);
}
