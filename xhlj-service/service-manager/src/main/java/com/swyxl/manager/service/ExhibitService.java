package com.swyxl.manager.service;

import com.swyxl.model.entity.service.exhibit.Business;
import org.springframework.web.multipart.MultipartFile;

public interface ExhibitService {
    void add(Business business);

    Business findById(Long id);

    void update(Business business);

    void delete(Long exhibitor);

    String imageUpload(MultipartFile file);
}
