package com.czh.common.vo;

import lombok.Data;

@Data
public class TokenResult {
    private boolean flag=false;
    private String msg="";
    private String token="";
    public boolean isFlag() {
        return flag;
    }
}
