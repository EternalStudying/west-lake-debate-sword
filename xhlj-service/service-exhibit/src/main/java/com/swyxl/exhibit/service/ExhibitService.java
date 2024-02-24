package com.swyxl.exhibit.service;

import com.swyxl.model.entity.service.exhibit.Achievement;
import com.swyxl.model.entity.service.exhibit.Business;

import java.util.List;

public interface ExhibitService {
    List<Business> businessList();

    List<Achievement> achievementList();

    String download(Long id);
}
