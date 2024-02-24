package com.swyxl.exhibit.service.impl;

import com.swyxl.exhibit.mapper.ExhibitMapper;
import com.swyxl.exhibit.service.ExhibitService;
import com.swyxl.model.entity.service.exhibit.Exhibit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExhibitServiceImpl implements ExhibitService {

    @Autowired
    private ExhibitMapper exhibitMapper;

    @Override
    public List<Exhibit> list() {
        return exhibitMapper.selectAll();
    }
}
