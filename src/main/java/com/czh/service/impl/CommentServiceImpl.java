package com.czh.service.impl;

import com.czh.common.enums.ExceptionEnum;
import com.czh.common.exception.JieBeiException;
import com.czh.mapper.CommentMapper;
import com.czh.pojo.Comment;
import com.czh.pojo.Discussion;
import com.czh.service.CommentService;
import com.czh.service.DiscussionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private DiscussionService discussionService;

    @Override
    @Transactional
    public void addComment(Comment comment) {
        Integer count = null;
        try {
            count = commentMapper.insert(comment);
            discussionService.addCount(comment.getDid());
        } catch (Exception e) {
            throw new JieBeiException(ExceptionEnum.COMMNET_ADD_ERROR);
        }
        System.out.println(count);
        if(count == null){
            throw new JieBeiException(ExceptionEnum.COMMNET_ADD_ERROR);
        }
    }

    @Override
    public List<Comment> findCommentsByDid(Integer did) {

        Comment comment = new Comment();
        comment.setDid(did);

        List<Comment> commentList = commentMapper.select(comment);
        return commentList;

    }
}
