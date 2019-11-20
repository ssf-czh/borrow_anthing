package com.czh.service;

import com.czh.pojo.Request;

import java.util.List;

public interface RequestService {



    //插入一条申请
    void addApplication(Request request);

    //根据uid查询用户者申请的记录
    List<Request> findApplicationByUid(Integer uid);

    //根据uid查询用户被申请的记录 返回gid
    List<Request> findBeApplicationByUid(Integer pid);

    //根据uid和rid处理申请表 接受或者拒接 接受1 拒接0
    void handleApplication(Integer gid, Integer type, Integer uid, Integer rid);

    //归还物品
    void returnThing(Integer uid, Integer rid);

    //拥有者确认归还 type=0 拒绝确认归还   =1确认归还
    void confirmReturn(Integer uid, Integer rid, Integer type);
}
