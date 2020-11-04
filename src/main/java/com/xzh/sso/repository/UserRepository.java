package com.xzh.sso.repository;

import com.xzh.sso.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: 向振华
 * @date: 2020/06/01 11:11
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 根据用户名查询用户
     *
     * @param username
     * @return
     */
    User findByUsername(String username);
}