package com.xzh.sso.exception;

import lombok.Data;

@Data
public class BusinessException extends RuntimeException {

    private int code;

    private String msg;

    public BusinessException(String msg) {
        super(msg);
        this.code = 1;
        this.msg = msg;
    }

    public BusinessException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }
}