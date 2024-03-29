package com.swyxl.service;

import com.swyxl.model.entity.service.exhibit.Achievement;

import java.util.List;

public interface CollectionService {
    List<Achievement> ppt();

    String download(Long id);
}
