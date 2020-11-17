package com.xzh.sso.controller;

import com.xzh.sso.common.DataResult;
import com.xzh.sso.exception.CustomWebResponseExceptionTranslator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.endpoint.CheckTokenEndpoint;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

/**
 * OAuth2认证
 *
 * @author 向振华
 * @date 2020/11/13 16:11
 */
@RestController
@RequestMapping("/oauth")
public class OAuth2Controller {

    private final WebResponseExceptionTranslator<OAuth2Exception> exceptionTranslator = new CustomWebResponseExceptionTranslator();

    @Autowired
    private TokenEndpoint tokenEndpoint;

    @Autowired
    private CheckTokenEndpoint checkTokenEndpoint;

    @Autowired
    private TokenStore tokenStore;

    @GetMapping("/token")
    public DataResult<Object> getAccessToken(Principal principal, @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
        return DataResult.success(tokenEndpoint.getAccessToken(principal, parameters).getBody());
    }

    @PostMapping("/token")
    public DataResult<Object> postAccessToken(Principal principal, @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
        return DataResult.success(tokenEndpoint.postAccessToken(principal, parameters).getBody());
    }

    @GetMapping("/check_token")
    public DataResult<Object> checkToken(@RequestParam("token") String value) {
        return DataResult.success(checkTokenEndpoint.checkToken(value));
    }

    @DeleteMapping("/logout")
    public DataResult<Object> logout(@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authorization) {
        if (StringUtils.isBlank(authorization)) {
            return DataResult.success("用户已注销");
        }
        String token = authorization.replace(OAuth2AccessToken.BEARER_TYPE, StringUtils.EMPTY).trim();
        OAuth2AccessToken accessToken = tokenStore.readAccessToken(token);
        if (accessToken != null) {
            // 清空 access_token
            tokenStore.removeAccessToken(accessToken);
            OAuth2RefreshToken refreshToken = accessToken.getRefreshToken();
            if (refreshToken != null) {
                // 清空 refresh_token
                tokenStore.removeRefreshToken(refreshToken);
            }
        }
        return DataResult.success("注销成功");
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<OAuth2Exception> handleException(Exception e) throws Exception {
        return this.exceptionTranslator.translate(e);
    }
}