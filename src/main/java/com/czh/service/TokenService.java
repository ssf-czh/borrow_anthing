package com.czh.service;

import com.czh.pojo.Token;

public interface TokenService {
    //根据token所对应的用户id进行查找
    Token findByUserId(int uid);

    //添加token 里面必须含有uid
    void addToken(Token token);

    //更新token 用户重新登入就需要登入token
    void updataToken(Token token) ;
}
