package com.swyxl.active.service.impl;

import com.alibaba.fastjson.JSON;
import com.swyxl.active.mapper.CommentMapper;
import com.swyxl.active.service.CommentService;
import com.swyxl.feign.user.UserFeignClient;
import com.swyxl.model.constant.RedisConstant;
import com.swyxl.model.dto.service.comment.CommentPostDto;
import com.swyxl.model.entity.service.comment.Comment;
import com.swyxl.model.entity.service.user.UserInfo;
import com.swyxl.model.vo.service.comment.CommentVo;
import com.swyxl.utils.AuthContextUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private UserFeignClient userFeignClient;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void post(CommentPostDto commentPostDto) {
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentPostDto, comment);
        comment.setUserId(AuthContextUtils.getUserInfo().getId());
        commentMapper.save(comment);
        redisTemplate.delete(RedisConstant.SERVICE_COMMENT_ACTIVE + comment.getActiveId());
    }

    //TODO 优化
    @Override
    @Cacheable(value = "service:comment:active", key = "#activeId", sync = true)
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
    @CacheEvict(value = "service:comment:active", allEntries = true)
    public void like(Long id) {
        commentMapper.like(id);
    }
}
