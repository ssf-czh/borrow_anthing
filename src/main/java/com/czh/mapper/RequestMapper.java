package com.czh.mapper;

import com.czh.pojo.Request;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


public interface RequestMapper extends Mapper<Request> {
//    @Select()
    @Select("select gt.gid from good_tab as gt,request_tab as rt where gt.gid = rt.gid and gt.`status`=2 and gt.uid = #{uid} " )
    List<Integer> findBeApplicationByUid(@Param("uid") Integer uid);



}
