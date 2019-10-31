package com.czh.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "token_tab")
public class Token {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer tid;
    private Integer uid;
    private String token;
    private Integer buildtime;
}
