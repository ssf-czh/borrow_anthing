package com.czh.service.impl;

import com.czh.common.enums.ExceptionEnum;
import com.czh.common.exception.JieBeiException;
import com.czh.mapper.GoodsMapper;
import com.czh.pojo.Good;
import com.czh.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;


    @Override
    public List<Good> findAllGoods() {
        Good good = new Good();
        good.setStatus(0);
        List<Good> goodList = goodsMapper.select(good);
        if(CollectionUtils.isEmpty(goodList)){
            throw new JieBeiException(ExceptionEnum.GOOD_FIND_ERROR);
        }

        return goodList;
    }
}
