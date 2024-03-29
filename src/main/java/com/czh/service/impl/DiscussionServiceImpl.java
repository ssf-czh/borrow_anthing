package com.czh.service.impl;

import com.czh.common.enums.ExceptionEnum;
import com.czh.common.exception.JieBeiException;
import com.czh.mapper.CommentMapper;
import com.czh.mapper.DiscussionMapper;
import com.czh.pojo.Comment;
import com.czh.pojo.Discussion;
import com.czh.service.DiscussionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class DiscussionServiceImpl implements DiscussionService {

    @Autowired
    private DiscussionMapper discussionMapper;

    @Autowired
    private CommentMapper commentMapper;


    @Override
    @Transactional
    public void createDiscussion(Discussion discussion) {

        Integer count = null;
        try {
            count = discussionMapper.insert(discussion);
        } catch (Exception e) {
            throw new JieBeiException(ExceptionEnum.DISCUSSION_INSERT_ERROR);
        }
        System.out.println(count);
        if(count == null){
            throw new JieBeiException(ExceptionEnum.DISCUSSION_INSERT_ERROR);
        }
    }

    @Override
    public List<Discussion> findAllDisc(String keyword) {

        List<Discussion> discussionList = null;
        Example example = new Example(Discussion.class);
        Example.Criteria criteria = example.createCriteria();


        if(StringUtils.isNotBlank(keyword))criteria.andLike("title","%"+keyword+"%");
        discussionList = discussionMapper.selectByExample(example);

        if(CollectionUtils.isEmpty(discussionList)){
            throw new JieBeiException(ExceptionEnum.DISCUSSION_NOT_ERROR);
        }

        return discussionList;
    }

    @Override
    @Transactional
    public void addCount(Integer did) {
        Discussion discussionByDid = findDiscussionByDid(did);
        discussionByDid.setCounts(discussionByDid.getCounts()+1);

        Integer count = null;
        try {
            count = discussionMapper.updateByPrimaryKeySelective(discussionByDid);
        } catch (Exception e) {
            System.out.println(e);
            throw new JieBeiException(ExceptionEnum.COUNT_ADD_ERROR);
        }
    }

    @Override
    public Discussion findDiscussionByDid(Integer did) {
        Discussion discussion = new Discussion();
        discussion.setDid(did);
        return discussionMapper.selectOne(discussion);
    }

    @Override
    @Transactional
    public void addLikes(Integer did) {

        try {
            Discussion discussionByDid = findDiscussionByDid(did);
            discussionByDid.setLikes(discussionByDid.getLikes()+1);
            discussionMapper.updateByPrimaryKey(discussionByDid);
        } catch (Exception e) {
            throw new JieBeiException(ExceptionEnum.DISC_LIKES_ERROR);
        }
    }

    @Override
    @Transactional
    public void deleteDisc(Integer uid, Integer did) {
        Discussion discussion = new Discussion();
        discussion.setDid(did);
        try {
            Discussion discussion1 = discussionMapper.selectOne(discussion);
            if(discussion1.getUid()!=uid) throw new JieBeiException(ExceptionEnum.DISCUSSION_PRIVILEGE_ERROR);
            discussionMapper.deleteByPrimaryKey(discussion1.getDid());
            Example example = new Example(Comment.class);
            example.createCriteria().andEqualTo("did",discussion1.getDid());
            commentMapper.deleteByExample(example);
        } catch (Exception e) {
            throw new JieBeiException(ExceptionEnum.DISCUSSION_DELETE_ERROR);
        }
    }
}
