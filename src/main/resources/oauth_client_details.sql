-- 客户端详情表
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(255) NOT NULL COMMENT '客户端ID',
  `client_secret` varchar(255) DEFAULT NULL COMMENT '客户端密码',
  `resource_ids` varchar(255) DEFAULT NULL COMMENT '资源ID',
  `scope` varchar(255) DEFAULT NULL COMMENT '范围',
  `authorized_grant_types` varchar(255) DEFAULT NULL COMMENT '授权的类型（授权码：authorization_code，隐藏式：implicit，密码式：password，客户端凭证：client_credentials，刷新TOKEN：refresh_token）',
  `web_server_redirect_uri` varchar(255) DEFAULT NULL COMMENT 'Web服务器重定向',
  `authorities` varchar(255) DEFAULT NULL COMMENT '用户的权限范围',
  `access_token_validity` int(11) DEFAULT NULL COMMENT '访问令牌有效期（单位：秒）',
  `refresh_token_validity` int(11) DEFAULT NULL COMMENT '刷新令牌有效期（单位：秒）',
  `additional_information` varchar(255) DEFAULT NULL COMMENT '附加信息',
  `autoapprove` varchar(255) DEFAULT NULL COMMENT '是否自动确认',
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 插入客户端信息（这里的client_secret需要通过BCryptPasswordEncoder.encode加密）
INSERT INTO `oauth_client_details`(`client_id`, `resource_ids`, `client_secret`, `scope`, `authorized_grant_types`, `web_server_redirect_uri`, `authorities`, `access_token_validity`, `refresh_token_validity`, `additional_information`, `autoapprove`) VALUES ('order-client-id', NULL, '$2a$10$iB9nOewRN2TEc8oshEQOqeF9TQVO6fFU4KVTbwMiW49NpghMWfiZu', 'all', 'authorization_code,implicit,password,client_credentials,refresh_token', NULL, NULL, 3600, 36000, NULL, '1');


-- client_id	主键，必须唯一，不能为空	用于唯一标识每一个客户端(client)；注册时必须填写(也可以服务端自动生成)，这个字段是必须的，实际应用也有叫app_key	OaH1heR2E4eGnBr87Br8FHaUFrA2Q0kE8HqZgpdg-- 8Sw
-- resource_ids	不能为空，用逗号分隔	客户端能访问的资源id集合，注册客户端时，根据实际需要可选择资源id，也可以根据不-- 同的额注册流程，赋予对应的额资源id	order-resource,pay-resource--
-- client_secret	必须填写	注册填写或者服务端自动生成，实际应用也有叫app_secret, 必须要有前缀代表加密方式	{bcrypt-- }gY/Hauph1tqvVWiH4atxteSH8sRX03IDXRIQi03DVTFGzKfz8ZtGi--
-- scope	不能为空，用逗号分隔	指定client的权限范围，比如读写权限，比如移动端还是web端权限	read,write / web,mobile--
-- authorized_grant_types	不能为空	可选值 授权码模式:authorization_code,密码模式:password,刷新token: refresh_token, 隐式模式: implicit: 客户端模式: client_credentials。支持多个用逗号分隔	-- password,refresh_token
-- web_server_redirect_uri	可为空	客户端重定向uri，authorization_code和implicit需要该值进行校验，注册时填写，	httt://baidu.com
-- authorities	可为空	指定用户的权限范围，如果授权的过程需要用户登陆，该字段不生效，implicit和client_credentials需要	ROLE_ADMIN,ROLE_USER
-- access_token_validity	可空	设置access_token的有效时间(秒),默认(606012,12小时)	3600
-- refresh_token_validity	可空	设置refresh_token有效期(秒)，默认(606024*30, 30填)	7200
-- additional_information	可空	值必须是json格式	{"key", "value"}
-- autoapprove	false/true/read/write	默认false,适用于authorization_code模式,设置用户是否自动approval操作,设置true跳过用户确认授权操作页面，直接跳到redirect_uri	false
