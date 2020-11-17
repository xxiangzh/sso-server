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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "varchar(15) comment '用户名'", unique = true)
    private String username;

    @Column(nullable = false, columnDefinition = "varchar(255) comment '密码'")
    private String password;

    @Column(nullable = false, columnDefinition = "int(11) DEFAULT 1 COMMENT '删除标记（1：未删除，2：已删除）'", unique = true)
    private Integer deleteFlag;
}