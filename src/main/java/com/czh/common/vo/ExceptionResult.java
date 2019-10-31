package com.czh.common.vo;


import com.czh.common.enums.ExceptionEnum;
import lombok.Data;
import lombok.Getter;


@Data
public class ExceptionResult {

    private Integer code;
    private String message;
    private Long timestamp;

    public ExceptionResult(ExceptionEnum exceptionEnum) {
        this.code = exceptionEnum.getCode();
        this.message = exceptionEnum.getMsg();
        this.timestamp = System.currentTimeMillis();
    }
}
