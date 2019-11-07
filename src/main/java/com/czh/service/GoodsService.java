package com.czh.service;

import com.czh.pojo.Good;

import java.util.List;

public interface GoodsService {

    //根据type和关键词获取所有空闲商品
    List<Good> findAllGoods(String type,String keyword);

    //根据gid查询商品详情
    Good findGoodByGid(Integer gid);

    //根据gid更新物品状态
    void updateGoodStatusByGid(Integer gid);
}
