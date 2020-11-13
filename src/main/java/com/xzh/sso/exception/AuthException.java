package com.xzh.sso.exception;

import lombok.Data;

/**
 * OAuth2认证异常
 *
 * @author 向振华
 * @date 2020/11/10 10:43
 */
@Data
public class AuthException extends RuntimeException {

    private int code;

    private String msg;

    public AuthException(String msg) {
        super(msg);
        this.code = 1;
        this.msg = msg;
    }

    public AuthException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }
}