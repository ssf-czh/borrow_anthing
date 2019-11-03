package com.czh.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "comment_tab")
public class Comment {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer cid;
    private Integer ctime;
    private String content;
    private Integer uid;
    private Integer did;
    private Integer likes;
    private String  username;
}
