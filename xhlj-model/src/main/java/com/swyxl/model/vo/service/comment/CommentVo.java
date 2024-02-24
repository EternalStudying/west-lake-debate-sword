package com.swyxl.model.vo.service.comment;

import lombok.Data;

@Data
public class CommentVo {
    private Long id;
    private Long parent;
    private String content;
    private Long likeCount;
    private String username;
    private String avatar;
}
