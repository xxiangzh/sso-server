package com.xzh.sso.config;

import com.xzh.sso.common.SecurityConstants;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;

import javax.sql.DataSource;

/**
 * 重写原生方法支持redis缓存
 *
 * @author 向振华
 * @date 2020/11/10 10:43
 */
public class RedisClientDetailsService extends JdbcClientDetailsService {

    private static final String DEFAULT_FIND_STATEMENT = "select client_id, client_secret, resource_ids, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove from oauth_client_details order by client_id";
    private static final String DEFAULT_SELECT_STATEMENT = "select client_id, client_secret, resource_ids, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove from oauth_client_details where client_id = ?";

    public RedisClientDetailsService(DataSource dataSource) {
        super(dataSource);
        super.setFindClientDetailsSql(DEFAULT_FIND_STATEMENT);
        super.setSelectClientDetailsSql(DEFAULT_SELECT_STATEMENT);
    }

    @Override
    // 开启@Cacheable需要加入在启动类加@EnableCaching
    //@Cacheable(value = SecurityConstants.CLIENT_KEY, key = "#clientId", unless = "#result == null")
    public ClientDetails loadClientByClientId(String clientId) {
        return super.loadClientByClientId(clientId);
    }
}