package com.swyxl.common.exception;

import com.swyxl.model.vo.common.ResultCodeEnum;
import lombok.Data;

@Data
public class XHLJException extends RuntimeException{
    private Integer code;
    private String message;
    private ResultCodeEnum resultCodeEnum;

    public XHLJException(ResultCodeEnum resultCodeEnum){
        this.resultCodeEnum = resultCodeEnum;
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
    }
}
