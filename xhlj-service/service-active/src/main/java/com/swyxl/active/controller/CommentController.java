package com.swyxl.active.controller;

import com.swyxl.active.service.CommentService;
import com.swyxl.model.dto.service.comment.CommentPostDto;
import com.swyxl.model.vo.common.Result;
import com.swyxl.model.vo.common.ResultCodeEnum;
import com.swyxl.model.vo.service.comment.CommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service/active/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/auth/post")
    public Result post(@RequestBody CommentPostDto commentPostDto){
        commentService.post(commentPostDto);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @GetMapping("/list/{id}")
    public Result list(@PathVariable Long id){
        List<CommentVo> commentVoList = commentService.list(id);
        return Result.build(commentVoList, ResultCodeEnum.SUCCESS);
    }

    @PutMapping("/auth/like/{id}")
    public Result like(@PathVariable Long id){
        commentService.like(id);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
}
