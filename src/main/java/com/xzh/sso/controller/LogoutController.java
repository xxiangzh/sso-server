package com.xzh.sso.controller;

import com.xzh.sso.common.DataResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 注销
 *
 * @author 向振华
 * @date 2020/09/27 16:11
 */
@RestController
@RequestMapping
public class LogoutController {

    @Autowired
    private TokenStore tokenStore;

    @DeleteMapping("/oauth/logout")
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
}