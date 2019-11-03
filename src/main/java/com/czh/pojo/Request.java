package com.czh.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "request_tab")
public class Request {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer rid;
    private Integer uid;
    private Integer gid;
    private Integer req_time;
    private Integer start_time;
    private Integer end_time;
    private String reason;
    private Integer status;
    private String resp;
    //gid 对应的uid possess
    private Integer pid;
}
