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

    //新增物品
    void addGood(Integer uid, Good good);

    //查询uid用户下的所有商品
    List<Good> findAllGoodsByUid(Integer uid);

    //删除物品
    void delete(Integer uid, Integer gid);
}
