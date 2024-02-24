package com.swyxl.active.service.impl;

import com.swyxl.active.mapper.CommentMapper;
import com.swyxl.active.service.CommentService;
import com.swyxl.feign.user.UserFeignClient;
import com.swyxl.model.dto.service.comment.CommentPostDto;
import com.swyxl.model.entity.comment.Comment;
import com.swyxl.model.entity.service.user.UserInfo;
import com.swyxl.model.vo.service.comment.CommentVo;
import com.swyxl.utils.AuthContextUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private UserFeignClient userFeignClient;

    @Override
    public void post(CommentPostDto commentPostDto) {
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentPostDto, comment);
        comment.setUserId(AuthContextUtils.getUserInfo().getId());
        commentMapper.save(comment);
    }

    //TODO 优化
    @Override
    public List<CommentVo> list(Long activeId) {
        List<Comment> comments = commentMapper.selectByActiveId(activeId);
        List<CommentVo> commentVos = new ArrayList<>();
        for (Comment comment : comments) {
            CommentVo commentVo = new CommentVo();
            Long userId = comment.getUserId();
            UserInfo userInfo = userFeignClient.getById(userId);
            commentVo.setId(comment.getId());
            commentVo.setParent(comment.getParent());
            commentVo.setContent(comment.getContent());
            commentVo.setLikeCount(comment.getLikeCount());
            commentVo.setUsername(userInfo.getUsername());
            commentVo.setAvatar(userInfo.getAvatar());
            commentVos.add(commentVo);
        }
        return commentVos;
    }

    @Override
    public void like(Long id) {
        commentMapper.like(id);
    }
}
