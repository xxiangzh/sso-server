package com.xzh.sso.common;

import lombok.Data;

/**
 * 通用返回类
 *
 * @author 向振华
 * @date 2020/11/10 10:43
 */
@Data
public class DataResult<T> {

    /**
     * 状态码（0：成功，1：失败）
     */
    private int code;

    private String message;

    private T data;

    private DataResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> DataResult<T> success(T data) {
        return new DataResult<>(0, "success", data);
    }

    public static DataResult<Object> fail(String message) {
        return new DataResult<>(1, message, null);
    }
}