package com.czh.common.exception;


import com.czh.common.enums.ExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Getter
public class JieBeiException extends RuntimeException {
    private ExceptionEnum exceptionEnum;
}
