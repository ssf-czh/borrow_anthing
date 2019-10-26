package com.czh.service.impl;

import com.czh.mapper.GoodsMapper;
import com.czh.pojo.Good;
import com.czh.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public Good findGoodByGid(Good good) {

        System.out.println(good.getGid());
        List<Good> goods = goodsMapper.select(good);
        System.out.println(goods.get(0));
        return goods.get(0);
    }
}
