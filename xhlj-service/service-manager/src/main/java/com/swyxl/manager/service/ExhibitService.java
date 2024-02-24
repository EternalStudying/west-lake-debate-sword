package com.swyxl.manager.service;

import com.swyxl.model.entity.service.exhibit.Business;

public interface ExhibitService {
    void add(Business business);

    Business findById(Long id);

    void update(Business business);

    void delete(Long exhibitor);
}
