package com.czh.service.impl;

import com.czh.common.enums.ExceptionEnum;
import com.czh.common.exception.JieBeiException;
import com.czh.mapper.GoodsMapper;
import com.czh.mapper.RequestMapper;
import com.czh.mapper.UserMapper;
import com.czh.pojo.Good;
import com.czh.pojo.Request;
import com.czh.service.GoodsService;
import com.czh.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class RequestServiceImpl implements RequestService {

    @Autowired
    private RequestMapper requestMapper;


    @Autowired
    private GoodsService goodsService;

    @Autowired
    private GoodsMapper goodsMapper;



    @Override
    @Transactional
    public void addApplication(Request request) {
        Integer count = null;
        count = requestMapper.insert(request);
        if(count == null){
            throw new JieBeiException(ExceptionEnum.ADD_REQUEST_ERROR);
        }
    }

    @Override
    public List<Request> findApplicationByUid(Integer uid) {
        Request request = new Request();
        request.setUid(uid);
        List<Request> gidList = requestMapper.select(request);
        return gidList;
    }

    @Override
    public List<Request> findBeApplicationByUid(Integer pid) {
//        List<Integer> requestList = requestMapper.findBeApplicationByUid(uid);
        Request request = new Request();
        request.setPid(pid);
        List<Request> requestList = requestMapper.select(request);
        System.out.println(requestList);
        return requestList;
    }

    @Override
    @Transactional
    public void handleApplication(Integer gid, Integer type, Integer uid, Integer rid) {

        Request request = new Request();
        Example example = new Example(Request.class);
//        requestMapper.selectOne()

        //函数上的uid 实际上是表中拥有者pid
        example.createCriteria().andEqualTo("pid",uid).andEqualTo("rid",rid);
        //如果拒绝 这申请表直接status 变成2 结束状态
        if(type == 0){
            request.setStatus(3);
            requestMapper.updateByExampleSelective(request,example);
        }else{
            request.setStatus(1);
            requestMapper.updateByExampleSelective(request,example);
            goodsService.updateGoodStatusByGid(gid);
        }
    }


    @Override
    @Transactional
    public void returnThing(Integer uid, Integer rid) {
        Request request = new Request();
        request.setRid(rid);
        try {
            Request request1 = requestMapper.selectOne(request);
            if(uid != request1.getUid()) throw new JieBeiException(ExceptionEnum.RETURN_NOT_MATCH);
            //4 是待确定状态  需要拥有者进行确定实际已经归还
            request1.setStatus(4);
            requestMapper.updateByPrimaryKey(request1);
        } catch (Exception e) {
            throw new JieBeiException(ExceptionEnum.RETURN_THING_ERROR);
        }
    }

    @Override
    @Transactional
    public void confirmReturn(Integer uid, Integer rid, Integer type) {
//        1 确认归还   0 拒绝确认归还
        Request request = new Request();
        request.setRid(rid);
        try {
            Request request1 = requestMapper.selectOne(request);
            if(request1.getPid() != uid) throw new JieBeiException(ExceptionEnum.POSSESS_DENY_ERROR);
            //拒绝确认
            if(type ==  0){
                request1.setStatus(1);
                requestMapper.updateByPrimaryKey(request1);
            }else{
                request1.setStatus(2);
                Good goodByGid = goodsService.findGoodByGid(request1.getGid());
                goodByGid.setStatus(2);
                requestMapper.updateByPrimaryKey(request1);
                goodsMapper.updateByPrimaryKey(goodByGid);
            }
        } catch (Exception e) {
            throw new JieBeiException(ExceptionEnum.CONFIRM_THING_ERROR);
        }


    }


}
