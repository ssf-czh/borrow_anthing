package com.czh.common.vo;

import lombok.Data;

@Data
public class RegisterResult {
    private boolean flag=false;
    private String msg="";
    public boolean isFlag() {
        return flag;
    }
}
