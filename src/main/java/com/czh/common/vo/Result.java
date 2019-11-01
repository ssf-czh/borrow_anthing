package com.czh.common.vo;


import com.czh.common.enums.ExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {

    private Integer code;
    private String message;
    private Object data;

    public Result(ExceptionEnum exceptionEnum) {
        this.code = exceptionEnum.getCode();
        this.message = exceptionEnum.getMsg();
    }
    public Result(Integer code,String message){
        this.code= code;
        this.message = message;
    }
}
