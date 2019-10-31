package com.czh.service.impl;

import com.czh.common.enums.ExceptionEnum;
import com.czh.common.exception.JieBeiException;
import com.czh.mapper.TokenMapper;
import com.czh.pojo.Token;
import com.czh.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private TokenMapper tokenMapper;

    /**
     * 根据uid查询token
     * @param uid
     * @return
     */
    @Override
    public Token findByUserId(int uid){
        Token token = new Token();
        token.setUid(uid);
        Token user_token = tokenMapper.selectOne(token);
        return user_token;
    }

    /**
     * 为第一次用户增加token
     * @param token
     */
    @Transactional
    @Override
    public void addToken(Token token) {
//        tokenMapper.insertSelective(token);
        int insert = tokenMapper.insert(token);
        if(insert != 1){
            throw new JieBeiException(ExceptionEnum.INSERT_TOKEN_ERROR);
        }
    }

    /**
     * 更新token
     * @param token
     */
    @Override
    @Transactional
    public void updataToken(Token token) {

        int insert = tokenMapper.updateByPrimaryKey(token);
        if(insert != 1){
            throw new JieBeiException(ExceptionEnum.UPDATE_TOKEN_ERROR);
        }
    }
}
