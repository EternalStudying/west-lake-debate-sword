package com.swyxl.chat.controller;

import com.swyxl.chat.service.ChatService;
import com.swyxl.model.dto.chat.ChatDto;
import com.swyxl.model.vo.chat.ChatVo;
import com.swyxl.model.vo.common.Result;
import com.swyxl.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/common/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    public Result chat(@RequestBody ChatDto chatDto){
        ChatVo chatVo = chatService.chat(chatDto);
        return Result.build(chatVo, ResultCodeEnum.SUCCESS);
    }
}
