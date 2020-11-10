-- 客户端详情表
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(255) NOT NULL COMMENT '客户端ID',
  `client_secret` varchar(255) DEFAULT NULL COMMENT '客户端密码',
  `resource_ids` varchar(255) DEFAULT NULL COMMENT '资源ID',
  `scope` varchar(255) DEFAULT NULL COMMENT '范围',
  `authorized_grant_types` varchar(255) DEFAULT NULL COMMENT '授权的类型（授权码授权模式：authorization_code，隐式授权模式：implicit，密码模式：password，客户端凭证模式：client_credentials，刷新TOKEN：refresh_token）',
  `web_server_redirect_uri` varchar(255) DEFAULT NULL COMMENT 'Web服务器重定向',
  `authorities` varchar(255) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL COMMENT '访问令牌有效期（单位：秒）',
  `refresh_token_validity` int(11) DEFAULT NULL COMMENT '刷新令牌有效期（单位：秒）',
  `additional_information` varchar(255) DEFAULT NULL COMMENT '附加信息',
  `autoapprove` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 插入客户端信息（这里的client_secret需要通过BCryptPasswordEncoder.encode加密）
INSERT INTO `oauth_client_details`(`client_id`, `resource_ids`, `client_secret`, `scope`, `authorized_grant_types`, `web_server_redirect_uri`, `authorities`, `access_token_validity`, `refresh_token_validity`, `additional_information`, `autoapprove`) VALUES ('order-client-id', NULL, '$2a$10$iB9nOewRN2TEc8oshEQOqeF9TQVO6fFU4KVTbwMiW49NpghMWfiZu', 'all', 'authorization_code,implicit,password,client_credentials,refresh_token', NULL, NULL, 3600, 36000, NULL, '1');
