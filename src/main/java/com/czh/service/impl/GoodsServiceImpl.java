package com.czh.service.impl;

import com.czh.common.enums.ExceptionEnum;
import com.czh.common.exception.JieBeiException;
import com.czh.mapper.GoodsMapper;
import com.czh.pojo.Good;
import com.czh.service.GoodsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;


    @Override
    public List<Good> findAllGoods(String type,String keyword) {
        Good good = new Good();
        good.setStatus(0);
        List<Good> goodList = null;
        Example example = new Example(Good.class);
        Example.Criteria criType = example.createCriteria();
        criType.andEqualTo("type",type).andEqualTo("status",2);
        Example.Criteria crilike = example.createCriteria();
        if(StringUtils.isNotBlank(keyword))crilike.orLike("name","%"+keyword+"%").orLike("detail","%"+keyword+"%");
        example.and(crilike);
        try {
            goodList = goodsMapper.selectByExample(example);
        } catch(Exception e) {

        }
        if(CollectionUtils.isEmpty(goodList)){
            throw new JieBeiException(ExceptionEnum.GOOD_FIND_ERROR);
        }

        return goodList;
    }

    @Override
    public Good findGoodByGid(Integer gid) {
        Good good = new Good();
        good.setGid(gid);
        Good good1 = goodsMapper.selectOne(good);
        if(good1 == null){
            throw new JieBeiException(ExceptionEnum.GOOD_FIND_ERROR);
        }
        return good1;

    }

    @Override
    @Transactional
    public void updateGoodStatusByGid(Integer gid) {
        Integer count = null;
        Good good = new Good();
        good.setGid(gid);
        good.setStatus(1);
        try {
            count = goodsMapper.updateByPrimaryKeySelective(good);
        } catch (Exception e) {
            throw new JieBeiException(ExceptionEnum.GOOD_STATUS_ERROR);
        }
        if(count == null){
            throw new JieBeiException(ExceptionEnum.GOOD_STATUS_ERROR);
        }
        System.out.println("count: "+ count);
    }

    @Override
    @Transactional
    public void addGood(Integer uid, Good good) {
        Integer count = null;
        good.setUid(uid);
        try {
            count = goodsMapper.insert(good);
        }catch (Exception e){
            throw new JieBeiException(ExceptionEnum.GOOD_INSERT_ERROR);
        }
        if(count == null){
            throw new JieBeiException(ExceptionEnum.GOOD_INSERT_ERROR);
        }
    }

    @Override
    public List<Good> findAllGoodsByUid(Integer uid) {
        List<Good> goodList = null;
        Good good = new Good();
        good.setUid(uid);

        try {
            goodList = goodsMapper.select(good);
        } catch (Exception e) {
            throw new JieBeiException(ExceptionEnum.GOOD_USER_FIND_ERROR);
        }
        return goodList;
    }

    @Override
    public void delete(Integer uid, Integer gid) {
        Good good = new Good();
        good.setGid(gid);
        try {
            Good good1 = goodsMapper.selectOne(good);
            if(good1.getUid()!=uid) throw new JieBeiException(ExceptionEnum.GOOD_POSSESS_ERROR);
            goodsMapper.deleteByPrimaryKey(good1.getGid());
        } catch (Exception e) {
            throw new JieBeiException(ExceptionEnum.GOOD_DELETE_ERROR);
        }
    }
}
