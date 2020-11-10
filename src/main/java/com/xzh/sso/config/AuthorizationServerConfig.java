package com.xzh.sso.config;

import com.xzh.sso.common.SecurityConstants;
import com.xzh.sso.exception.CustomWebResponseExceptionTranslator;
import com.xzh.sso.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * 授权服务器配置
 *
 * @author 向振华
 * @date 2020/11/05 17:43
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * 配置令牌端点(Token Endpoint)的安全约束
     *
     * @param security
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security.allowFormAuthenticationForClients();
        // 开启/oauth/token_key验证端口无权限访问
        security.tokenKeyAccess("isAuthenticated()");
        // 开启/oauth/check_token验证端口认证权限访问
        security.checkTokenAccess("permitAll()");
    }

    /**
     * 配置客户端详情
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
                .inMemory()
                // 客户端ID
                .withClient("order-client-id")
                // 客户端密码
                .secret(passwordEncoder.encode("order-secret-123456"))
                // 授权的类型
                .authorizedGrantTypes("refresh_token", "authorization_code", "password")
                // 令牌有效期
                .accessTokenValiditySeconds(120)
                // 范围
                .scopes("all");
    }

    /**
     * 配置令牌管理
     *
     * @param endpoints
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                // 请求方式
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
                // 用户账号密码认证
                .userDetailsService(userDetailsService)
                // 指定认证管理器
                .authenticationManager(authenticationManager)
                // 指定token存储位置
                .tokenStore(tokenStore())
                // JWTToken
                .tokenEnhancer(jwtTokenConverter())
                // 是否重复使用 refresh_token
                .reuseRefreshTokens(false)
                // 自定义异常翻译
                .exceptionTranslator(new CustomWebResponseExceptionTranslator());
    }

    /**
     * 基于Redis实现，令牌保存到缓存
     */
    @Bean
    public TokenStore tokenStore() {
        RedisTokenStore tokenStore = new RedisTokenStore(redisConnectionFactory);
        // redis key 前缀
        tokenStore.setPrefix("sso:token:");
        return tokenStore;
    }

    /**
     * JWT
     *
     * @return
     */
    @Bean
    protected JwtAccessTokenConverter jwtTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        // 设置JWT签名密钥
        converter.setSigningKey(SecurityConstants.JWT_SIGNING_KEY);
        // JWT加入额外信息
        converter.setAccessTokenConverter(new CustomAccessTokenConverter());
        return converter;
    }
}
