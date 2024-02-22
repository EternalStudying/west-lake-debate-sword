package com.swyxl.model.entity.service.chat;

import lombok.Data;

@Data
public class Usage {
    //问题token数
    int prompt_tokens;
    //回答token数
    int completion_tokens;
    //token总数
    int total_tokens;
}
