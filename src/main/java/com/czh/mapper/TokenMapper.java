package com.czh.mapper;

import com.czh.pojo.Token;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

public interface TokenMapper extends Mapper<Token> {
    Token findByUserId(int uid);

}
