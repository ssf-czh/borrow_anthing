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
    ADD_USER_ERROR(400,"更新token失败"),
    USER_UPDATE_ERROR(400,"更新用户信息失败"),
    ;
    private int code;
    private String msg;
}
