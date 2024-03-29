package com.czh.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ExceptionEnum {

    USER_NOT_ERROR(400,"用户不存在或密码错误"),
    TOKEN_NOT_ERROR(400,"用户不存在或密码错误"),
    INSERT_TOKEN_ERROR(400,"初始化token失败"),
    UPDATE_TOKEN_ERROR(400,"更新token失败"),
    ADD_USER_ERROR(400,"该学号已绑定"),
    USER_UPDATE_ERROR(400,"更新用户信息失败"),
    GOOD_FIND_ERROR(400,"查询闲置商品出错"),
    TOKEN_FIND_ERROR(400,"查询token失败"),
    INVAILD_FILE_TYPE(400,"非法图片类型"),
    FAIL_UPLOAD_IMAGE(400,"上传图片失败"),
    ADD_REQUEST_ERROR(400,"申请失败"),
    GOOD_STATUS_ERROR(400,"改变物品status失败"),
    DISCUSSION_INSERT_ERROR(400,"创建帖子失败"),
    DISCUSSION_NOT_ERROR(400,"查找所有帖子失败"),
    COMMNET_ADD_ERROR(400,"评论失败"),
    COUNT_ADD_ERROR(400,"评论数增加失败"),
    GOOD_INSERT_ERROR(400,"新增商品失败"),
    DISC_LIKES_ERROR(400,"帖子点赞失败"),
    GOOD_USER_FIND_ERROR(400,"该用户下发布商品查询出错"),
    RETURN_NOT_MATCH(400,"该用户并未拥有此物品"),
    RETURN_THING_ERROR(400,"该用户下发布商品查询出错"),
    POSSESS_DENY_ERROR(400,"该用户不是该商品的拥有者"),
    CONFIRM_THING_ERROR(400,"处理带确定申请失败"),
    GOOD_POSSESS_ERROR(400,"该用户没有该商品"),
    GOOD_DELETE_ERROR(400,"删除商品错误"),
    COMMENT_PRIVILEGE_ERROR(400,"删除商品错误"),
    COMMENT_DELETE_ERROR(400,"删除评论失败"),
    DISCUSSION_PRIVILEGE_ERROR(400,"该用户不是帖主"),
    DISCUSSION_DELETE_ERROR(400,"删除帖子失败"),

	//
    ;
    private int code;
    private String msg;
}
