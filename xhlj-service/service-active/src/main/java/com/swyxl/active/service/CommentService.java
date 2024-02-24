package com.swyxl.active.service;

import com.swyxl.model.dto.service.comment.CommentPostDto;
import com.swyxl.model.vo.service.comment.CommentVo;

import java.util.List;

public interface CommentService {
    void post(CommentPostDto commentPostDto);

    List<CommentVo> list(Long activeId);

    void like(Long id);
}
