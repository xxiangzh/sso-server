package com.xzh.sso.service;

import com.xzh.sso.domain.User;
import com.xzh.sso.domain.UserInfo;
import com.xzh.sso.exception.AuthException;
import com.xzh.sso.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户详细信息服务
 *
 * @author 向振华
 * @date 2020/11/10 10:43
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) {
        // 获取并校验用户
        User user = getAndVerifyUser(username);
        // 用户角色应在数据库中获取
        List<SimpleGrantedAuthority> authorities = getAuthorities();
        // 加密后的密码，注意：如果数据库存的是encode密码，这里不需再加密
        String encodePassword = passwordEncoder.encode(user.getPassword());
        // 登录用户信息
        UserInfo userInfo = new UserInfo(user.getId(), user.getUsername(), encodePassword, authorities);
        return userInfo;
    }

    /**
     * 获取并校验用户
     * 这里得BusinessException会转成InternalAuthenticationServiceException
     *
     * @param username
     */
    private User getAndVerifyUser(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new AuthException("用户不存在");
        }
        if (user.getDeleteFlag() != 1) {
            throw new AuthException("用户已删除");
        }
        if (StringUtils.isBlank(user.getPassword())){
            throw new AuthException("密码尚未初始化");
        }
        return user;
    }

    private List<SimpleGrantedAuthority> getAuthorities() {
        String role = "ROLE_DEMO";
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role));
        return authorities;
    }
}