package com.swyxl.model.entity.service.comment;

import com.swyxl.model.entity.base.BaseEntity;
import lombok.Data;

@Data
public class Comment extends BaseEntity {
    private Long parent;
    private String content;
    private Long likeCount;
    private Long activeId;
    private Long userId;
}
