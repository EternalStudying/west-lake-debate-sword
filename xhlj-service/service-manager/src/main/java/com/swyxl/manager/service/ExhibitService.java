package com.swyxl.manager.service;

import com.swyxl.model.entity.service.exhibit.Exhibit;

public interface ExhibitService {
    void add(Exhibit exhibit);

    Exhibit findById(Long id);

    void update(Exhibit exhibit);

    void delete(Long exhibitor);
}
