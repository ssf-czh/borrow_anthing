package com.czh.service;

import com.czh.pojo.Comment;

import java.util.List;

public interface CommentService {
    //插入一条评论
    void addComment(Comment comment);

    //根据did来查找帖子的所有回复
    List<Comment> findCommentsByDid(Integer did);

    //根据uid和cid删除评论 uid进行验证
    void deleteComment(Integer uid, Integer cid);
}
