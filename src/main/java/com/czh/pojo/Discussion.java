package com.czh.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Data
@Table(name = "disc_tab")
public class Discussion {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer did;
    private Integer ctime;
    private String title;
    private Integer uid;
    private Integer likes;
    private String imgurl;
    private String username;
    private String useravatar;
    private Integer counts;


}
