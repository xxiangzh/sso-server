package com.xzh.sso.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author: 向振华
 * @date: 2020/06/01 11:11
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "t_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, columnDefinition = "varchar(15) comment '用户名'", unique = true)
    private String username;

    @Column(nullable = false, columnDefinition = "varchar(15) comment '密码'")
    private String password;
}