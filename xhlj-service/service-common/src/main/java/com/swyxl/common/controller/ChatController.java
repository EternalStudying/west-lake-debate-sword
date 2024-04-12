package com.swyxl.common.controller;

import com.swyxl.common.service.ChatService;
import com.swyxl.model.dto.chat.ChatDto;
import com.swyxl.model.vo.service.chat.ChatVo;
import com.swyxl.model.vo.common.Result;
import com.swyxl.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/service/common/auth/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @GetMapping
    public Result chat(String question){
        if (Objects.equals(question, "西湖论剑是什么")){
            return Result.build(
                    "西湖论剑是一个代表网络安全领域重要比赛和大会的代名词。" +
                            "每年，都有众多的网络安全专家和爱好者聚集在美丽的西湖畔，" +
                            "共同探讨网络安全的前沿技术和热点问题。西湖论剑不仅是一个展示才华和技术的舞台，" +
                            "更是一个促进交流和合作的平台。",
                    ResultCodeEnum.SUCCESS
            );
        } else if (Objects.equals(question, "如何订阅大会议程")) {
            return Result.build(
                    "进入2024西湖论剑·数字安全大会官网，点击“大会议程”，选择想要订阅的议程，点击“订阅”即可。",
                    ResultCodeEnum.SUCCESS
            );
        } else if (Objects.equals(question, "你是谁")) {
            return Result.build("你好!我是西湖论剑智能问答小助手，可以向我提问哦。",
                    ResultCodeEnum.SUCCESS);
        }
        String answer = chatService.chat(question);
        return Result.build(answer, ResultCodeEnum.SUCCESS);
    }
}
