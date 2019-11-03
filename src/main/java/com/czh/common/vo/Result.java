package com.czh.common.vo;


import com.czh.common.enums.ExceptionEnum;
import com.czh.pojo.Good;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {

    public interface ZeroView{};

    private Integer code;
    private String message;
    private Object data;

    @JsonView(ZeroView.class)
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @JsonView(ZeroView.class)
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

//    @JsonView(ZeroView.class)
//    @JsonView(Good.OneView.class)
    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }



    public Result(ExceptionEnum exceptionEnum) {
        this.code = exceptionEnum.getCode();
        this.message = exceptionEnum.getMsg();
    }
    public Result(Integer code,String message){
        this.code= code;
        this.message = message;
    }
}
