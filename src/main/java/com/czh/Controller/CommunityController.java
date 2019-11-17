package com.czh.Controller;

import com.czh.common.utils.JWTUtil;
import com.czh.common.vo.Result;
import com.czh.pojo.Comment;
import com.czh.pojo.Discussion;
import com.czh.service.CommentService;
import com.czh.service.DiscussionService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/community")
public class CommunityController {

    @Autowired
    private DiscussionService discussionService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/createDisc")
    public ResponseEntity<Result> createDiscussion(Discussion discussion, HttpServletRequest httpServletRequest){

        String token = httpServletRequest.getHeader("XW-Token");
        Integer uid = JWTUtil.parseToUid(token);

        discussion.setUid(uid);
        int ctime = (int) (System.currentTimeMillis()/1000);
        discussion.setCtime(ctime);
        discussion.setLikes(0);
        discussion.setCounts(0);

        discussionService.createDiscussion(discussion);

        Result result = new Result(200,"创建帖子成功");
        return ResponseEntity.ok(result);
    }

//    查找帖子 支持关键词搜索
    @GetMapping("/findAllDisc")
    public ResponseEntity<Result> getAllDisc(String keyword){
        List<Discussion> discussionList = discussionService.findAllDisc(keyword);
        Result result = new Result(200,"查询所有帖子成功",discussionList);
        return ResponseEntity.ok(result);
    }


    @PostMapping("/publCommnet")
    public ResponseEntity<Result> publishComment(Comment comment
            , HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader("XW-Token");
        Integer uid = JWTUtil.parseToUid(token);
        int ctime = (int) (System.currentTimeMillis()/1000);
        comment.setCtime(ctime);
        comment.setUid(uid);
        comment.setLikes(0);

        commentService.addComment(comment);
        Result result = new Result(200, "评论成功");
        return ResponseEntity.ok(result);
    }

    @GetMapping("/findAllComment")
    public ResponseEntity<Result> findAllComment(@RequestParam("did") Integer did){
        List<Comment> commentList = commentService.findCommentsByDid(did);
        Result result = new Result(200, "查找帖子所有回复成功 ", commentList);
        return ResponseEntity.ok(result);
    }
}
