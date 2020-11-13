package com.xzh.sso.granttype;

import com.xzh.sso.domain.User;
import com.xzh.sso.domain.UserInfo;
import com.xzh.sso.exception.AuthException;
import com.xzh.sso.repository.UserRepository;
import com.xzh.sso.utils.SpringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 短信验证码登录（改造ResourceOwnerPasswordTokenGranter）
 *
 * @author 向振华
 * @date 2020/09/27 16:11
 */
public class ResourceOwnerSmsTokenGranter extends AbstractTokenGranter {
    private static final String GRANT_TYPE = "sms";

    public ResourceOwnerSmsTokenGranter(AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory) {
        super(tokenServices, clientDetailsService, requestFactory, GRANT_TYPE);
    }

    @Override
    protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {
        Map<String, String> parameters = new LinkedHashMap<>(tokenRequest.getRequestParameters());
        // 校验短信验证码
        this.verifySms(parameters);
        // 登录用户信息
        String username = (String) parameters.get("username");
        UserRepository userRepository = SpringUtils.getBean(UserRepository.class);
        User user = userRepository.findByUsername(username);
        UserInfo userInfo = new UserInfo(user.getId(), user.getUsername(), "", new ArrayList<>());

        PreAuthenticatedAuthenticationToken authentication = new PreAuthenticatedAuthenticationToken(userInfo, null, userInfo.getAuthorities());
        authentication.setDetails(userInfo);

        OAuth2Request storedOAuth2Request = this.getRequestFactory().createOAuth2Request(client, tokenRequest);
        return new OAuth2Authentication(storedOAuth2Request, authentication);
    }

    /**
     * 校验短信验证码
     *
     * @param parameters
     */
    private void verifySms(Map<String, String> parameters) {
        String username = (String) parameters.get("username");
        String code = (String) parameters.get("code");
        if (StringUtils.isBlank(username)) {
            throw new AuthException("手机号不能为空");
        }
        if (StringUtils.isBlank(code)) {
            throw new AuthException("验证码不能为空");
        }
        // 根据手机号获取redis验证码
        String codeCache = "123456";
        if (StringUtils.isBlank(codeCache)) {
            throw new AuthException("验证码已失效，请重新获取");
        }
        if (!code.equals(codeCache)) {
            throw new AuthException("验证码错误");
        }
    }
}