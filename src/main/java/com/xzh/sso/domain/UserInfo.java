package com.xzh.sso.domain;

import com.alibaba.fastjson.JSONObject;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * 登录用户
 *
 * @author: 向振华
 * @date: 2020/06/01 11:11
 */
public class UserInfo extends User {
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long userId;

    public UserInfo(Long userId, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.userId = userId;
    }

    public UserInfo(Long userId, String username, String password, boolean enabled, boolean accountNonExpired,
                    boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.userId = userId;
    }

    public String toJSONString() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", this.userId);
        jsonObject.put("username", super.getUsername());
        return jsonObject.toJSONString();
    }
}