package com.swyxl.active.mapper;

import com.swyxl.model.entity.comment.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    void save(Comment comment);

    List<Comment> selectByActiveId(Long activeId);

    void like(Long id);
}
