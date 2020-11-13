package com.xzh.sso.exception;

import com.xzh.sso.common.DataResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.common.exceptions.UnsupportedGrantTypeException;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;

/**
 * oauth2 自定义异常处理
 *
 * @author 向振华
 * @date 2020/11/10 10:43
 */
@Slf4j
public class CustomWebResponseExceptionTranslator implements WebResponseExceptionTranslator<OAuth2Exception> {

    @Override
    public ResponseEntity translate(Exception e) {
        log.warn("登录失败: ", e);
        DataResult dataResult = new DataResult();
        dataResult.setCode(1);
        if (e instanceof BusinessException || e.getCause() instanceof BusinessException) {
            dataResult.setMessage(e.getMessage());
        } else if (e instanceof InternalAuthenticationServiceException) {
            dataResult.setMessage("身份验证失败");
        } else if (e instanceof InvalidGrantException) {
            dataResult.setMessage("用户名或密码错误");
        } else if (e instanceof InvalidTokenException) {
            dataResult.setMessage("Token无效或过期");
        } else if (e instanceof UnsupportedGrantTypeException) {
            dataResult.setMessage("不支持的授予类型");
        } else {
            dataResult.setMessage("登录失败");
        }
        return ResponseEntity.ok(dataResult);
    }
}