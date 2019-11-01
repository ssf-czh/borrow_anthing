package com.czh.service;

import com.czh.pojo.Good;

import java.util.List;

public interface GoodsService {

    //获取所有空闲商品
    List<Good> findAllGoods();
}
