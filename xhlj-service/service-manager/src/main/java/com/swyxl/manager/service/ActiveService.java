package com.swyxl.manager.service;

import com.swyxl.model.entity.service.active.Active;

import java.util.List;

public interface ActiveService {
    void add(Active active);

    Active findById( Long id);


    void update(Active active);

    void deleteById(Long id);

    List<Active> getAll();

}
