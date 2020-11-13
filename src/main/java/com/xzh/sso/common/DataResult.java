package com.xzh.sso.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataResult<T> {

    /**
     * 状态码（0：成功，1：失败）
     */
    private int code;

    private String message;

    private T data;

}