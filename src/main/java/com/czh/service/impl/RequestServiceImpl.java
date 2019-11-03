package com.czh.service.impl;

import com.czh.common.enums.ExceptionEnum;
import com.czh.common.exception.JieBeiException;
import com.czh.mapper.RequestMapper;
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
            request.setStatus(2);
            requestMapper.updateByExampleSelective(request,example);
        }else{
            request.setStatus(1);
            requestMapper.updateByExampleSelective(request,example);
            goodsService.updateGoodStatusByGid(gid);
        }
    }


}
