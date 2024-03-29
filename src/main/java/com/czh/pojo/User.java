package com.czh.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "user_tab")
public class User {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer uid;
    private String imgurl;
    private String name;
    private String schoolid;
    private String phonenum;
    private String username;
    private String address;
    private String password;
    private String qq;
    private String wechat;
    private String department;
    private String speciality;
    private String cls;
}
