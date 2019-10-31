package com.czh.common.advice;

import com.czh.common.enums.ExceptionEnum;
import com.czh.common.exception.JieBeiException;
import com.czh.common.vo.ExceptionResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler({JieBeiException.class})
    public ResponseEntity<ExceptionResult> handerException(JieBeiException e){
        ExceptionEnum exceptionEnum = e.getExceptionEnum();
        return ResponseEntity.status(exceptionEnum.getCode()).body(new ExceptionResult(exceptionEnum));
    }
}
