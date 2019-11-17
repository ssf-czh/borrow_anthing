package com.czh.service;

import com.czh.pojo.Discussion;

import java.util.List;

public interface DiscussionService {
    //创建一个帖子
    void createDiscussion(Discussion discussion);

    //查询所有的帖子 可以根据关键词搜索
    List<Discussion> findAllDisc(String keyword);

    //根据主键did来增加count
    void addCount(Integer did);

    //根据主键did查找帖子
    Discussion findDiscussionByDid(Integer did);

    //增加喜欢
    void addLikes(Integer did);


}
