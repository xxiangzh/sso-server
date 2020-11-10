package com.xzh.sso.config;

import com.xzh.sso.domain.UserInfo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.util.CollectionUtils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * JWT Claims加入额外信息
 *
 * @author 向振华
 * @date 2020/11/10 10:43
 */
public class CustomAccessTokenConverter extends DefaultAccessTokenConverter {

    public CustomAccessTokenConverter() {
        super.setUserTokenConverter(new CustomerUserAuthenticationConverter());
    }

    private static class CustomerUserAuthenticationConverter extends DefaultUserAuthenticationConverter {

        @Override
        public Map<String, ?> convertUserAuthentication(Authentication authentication) {
            UserInfo userInfo = (UserInfo) authentication.getPrincipal();
            LinkedHashMap<String, Object> response = new LinkedHashMap<>(4);
            // 设置JWT Subject（之后可通过claims.getSubject()获取）
            response.put("sub", userInfo.toJSONString());
            if (!CollectionUtils.isEmpty(authentication.getAuthorities())) {
                response.put("authorities", AuthorityUtils.authorityListToSet(authentication.getAuthorities()));
            }
            return response;
        }
    }
}