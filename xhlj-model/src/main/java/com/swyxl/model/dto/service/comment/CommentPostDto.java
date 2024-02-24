package com.swyxl.model.dto.service.comment;

import lombok.Data;

@Data
public class CommentPostDto {
    private Long parent;
    private Long activeId;
    private String content;
}
