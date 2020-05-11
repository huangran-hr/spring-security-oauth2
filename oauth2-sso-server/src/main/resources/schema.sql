/*
使用官方提供的建表脚本初始化Oauth2相关表，地址如下：
 https://github.com/spring-projects/spring-security-oauth/blob/master/spring-security-oauth2/src/test/resources/schema.sql

 由于我们使用的是MYSQL 数据库，而官方使用的是HSQL数据库，默认建表语句中主键VARCHAR(256)，这超过了最大的主键长度，需要手动修改成VARCHAR(128)，
 并用BLOB替换LONGVARBINARY，修改后的建表语句如下：
*/
create table oauth_client_details (
  client_id VARCHAR(128) PRIMARY KEY,
  resource_ids VARCHAR(256),
  client_secret VARCHAR(256),
  scope VARCHAR(256),
  authorized_grant_types VARCHAR(256),
  web_server_redirect_uri VARCHAR(256),
  authorities VARCHAR(256),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(256)
);

INSERT INTO `oauth_client_details` VALUES ('client', NULL, '$2a$10$nJ22m12gQQUbYwqk66YPLu7sp47oQyPL9bHurJaVjWJzRPvLdMcMe', 'all', 'authorization_code,refresh_token', 'http://localhost:8082/memberSystem/login', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `oauth_client_details` VALUES ('OrderManagement', NULL, '$2a$10$8yVwRGY6zB8wv5o0kRgD0ep/HVcvtSZUZsYu/586Egxc1hv3cI9Q6', 'all', 'authorization_code,refresh_token', 'http://localhost:8081/orderSystem/login', NULL, 7200, NULL, NULL, 'true');
INSERT INTO `oauth_client_details` VALUES ('UserManagement', NULL, '$2a$10$ZRmPFVgE6o2aoaK6hv49pOt5BZIKBDLywCaFkuAs6zYmRkpKHgyuO', 'all', 'authorization_code,refresh_token', 'http://localhost:8082/memberSystem/login', NULL, 7200, NULL, NULL, 'true');

/*
说明：
    web_server_redirect_uri  必须得对应项目的登录地址
    autoapprove值为true,表示自动授权，false 表示需要手动点击授权
*/

create table oauth_client_token (
  token_id VARCHAR(128),
  token BLOB,
  authentication_id VARCHAR(128) PRIMARY KEY,
  user_name VARCHAR(256),
  client_id VARCHAR(256)
);

create table oauth_access_token (
  token_id VARCHAR(256),
  token BLOB,
  authentication_id VARCHAR(128) PRIMARY KEY,
  user_name VARCHAR(256),
  client_id VARCHAR(256),
  authentication BLOB,
  refresh_token VARCHAR(256)
);

create table oauth_refresh_token (
  token_id VARCHAR(256),
  token BLOB,
  authentication BLOB
);

create table oauth_code (
  code VARCHAR(256), authentication BLOB
);

create table oauth_approvals (
	userId VARCHAR(256),
	clientId VARCHAR(256),
	scope VARCHAR(256),
	status VARCHAR(10),
	expiresAt TIMESTAMP,
	lastModifiedAt TIMESTAMP
);


-- customized oauth_client_details table
create table ClientDetails (
  appId VARCHAR(128) PRIMARY KEY,
  resourceIds VARCHAR(256),
  appSecret VARCHAR(256),
  scope VARCHAR(256),
  grantTypes VARCHAR(256),
  redirectUrl VARCHAR(256),
  authorities VARCHAR(256),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additionalInformation VARCHAR(4096),
  autoApproveScopes VARCHAR(256)
);


/*
    RBAC的表结构
*/
CREATE TABLE `tb_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父权限',
  `name` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '权限名称',
  `enname` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '权限英文名称',
  `url` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '权限路径',
  `description` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='权限表';

CREATE TABLE `tb_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父角色',
  `name` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色名称',
  `enname` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色英文名称',
  `description` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='角色表';

CREATE TABLE `tb_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名',
  `password` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码，加密存储',
  `phone` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '注册手机号',
  `email` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '注册邮箱',
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户表';

CREATE TABLE `tb_role_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  `permission_id` bigint(20) NOT NULL COMMENT '权限id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='角色权限表';

CREATE TABLE `tb_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户角色表';


INSERT INTO `tb_permission` VALUES (1, 0, '系统管理', 'System', '/', NULL, '2020-4-13 17:06:21', '2020-4-13 17:06:24');
INSERT INTO `tb_permission` VALUES (2, 1, '内容管理', 'SystemContext', '/contents/', NULL, '2020-4-13 17:08:29', '2020-4-13 17:08:31');
INSERT INTO `tb_permission` VALUES (3, 2, '内容查看', 'SystemContextView', '/contents/view/', NULL, '2020-4-13 17:09:32', '2020-4-13 17:09:35');
INSERT INTO `tb_permission` VALUES (4, 2, '内容保存', 'SystemContextSave', '/contents/save/', NULL, '2020-4-13 17:10:40', '2020-4-13 17:10:43');

INSERT INTO `tb_role` VALUES (1, 0, '超级管理员', 'admin', NULL, '2020-4-13 17:03:34', '2020-4-13 17:03:36');
INSERT INTO `tb_role` VALUES (2, 0, '内容管理员', 'content', NULL, '2020-4-14 16:44:26', '2020-4-14 16:44:29');

INSERT INTO `tb_role_permission` VALUES (1, 1, 1);
INSERT INTO `tb_role_permission` VALUES (2, 1, 2);
INSERT INTO `tb_role_permission` VALUES (3, 1, 3);
INSERT INTO `tb_role_permission` VALUES (4, 1, 4);
INSERT INTO `tb_role_permission` VALUES (5, 2, 1);
INSERT INTO `tb_role_permission` VALUES (6, 2, 2);


INSERT INTO `tb_user` VALUES (1, 'admin', '$2a$10$3tKZ0Nfecev17pY2bobn0uLmXHzmep6ywYgSKkm97VNWltLCVL0wi', NULL, NULL, '2020-4-13 16:59:43', '2020-4-13 16:59:45');
INSERT INTO `tb_user` VALUES (2, 'hr', '$2a$10$wHkczZM//QrsPNJIjIn8JebAxuZDJ76cG0zNR2oaNAdoDNkegV4JG', NULL, NULL, '2020-4-13 17:00:58', '2020-4-13 17:01:01');


INSERT INTO `tb_user_role` VALUES (1, 1, 1);
INSERT INTO `tb_user_role` VALUES (2, 2, 2);



