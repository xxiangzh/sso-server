package com.xzh.sso.config;

import com.xzh.sso.domain.UserInfo;
import com.xzh.sso.domain.User;
import com.xzh.sso.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
        // 查询用户信息
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username + "用户名不存在");
        }
        // 用户角色应在数据库中获取
        List<SimpleGrantedAuthority> authorities = getSimpleGrantedAuthorities();
        // 加密后的密码
        String encodePassword = passwordEncoder.encode(user.getPassword());
        // 登录用户信息
        UserInfo userInfo = new UserInfo(user.getId(), user.getUsername(), encodePassword, authorities);
        return userInfo;
    }

    private List<SimpleGrantedAuthority> getSimpleGrantedAuthorities() {
        String role = "ROLE_ADMIN";
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role));
        return authorities;
    }
}