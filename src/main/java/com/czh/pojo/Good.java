package com.czh.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "good_tab")
@Data
public class Good {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer gid;
    private Integer uid;
    private String location;
    private String imgurl;
    private Integer status;
    private String detail;
    private String type;

}
