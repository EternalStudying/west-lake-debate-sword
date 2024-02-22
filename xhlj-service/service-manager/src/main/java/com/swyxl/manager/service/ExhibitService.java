package com.swyxl.manager.service;

import com.swyxl.model.entity.exhibit.Exhibitor;

public interface ExhibitService {
    void add(Exhibitor exhibitor);

    Exhibitor findById(Long id);

    void update(Exhibitor exhibitor);

    void delete(Long exhibitor);
}
