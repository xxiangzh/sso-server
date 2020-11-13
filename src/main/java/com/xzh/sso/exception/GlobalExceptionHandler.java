package com.xzh.sso.exception;

import com.xzh.sso.common.DataResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常捕获处理
 *
 * @author 向振华
 * @date 2020/09/27 17:43
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 参数验证异常
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Object handler(MethodArgumentNotValidException exception) {
        String message = exception.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return DataResult.fail(message);
    }

    /**
     * 业务异常
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(value = BusinessException.class)
    public Object handler(BusinessException exception) {
        return DataResult.fail(exception.getMsg());
    }

    /**
     * 未知异常
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public Object handler(Exception exception) {
        log.error("未知异常: ", exception);
        return DataResult.fail("系统繁忙，请稍后重试");
    }
}