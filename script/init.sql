create table shiro_filter
(
  id                 int auto_increment comment '自增编号',
  filter_shortening  varchar(16) default ''                                            not null comment '过滤器简称',
  filter_class_path  varchar(64) default ''                                            not null comment '过滤器路径',
  filter_description varchar(32) default ''                                            not null comment '过滤器描述',
  create_time        timestamp   default current_timestamp                             not null comment '创建时间',
  last_update_time   timestamp   default current_timestamp on update current_timestamp not null comment '最近一次更新时间',
  constraint shiro_filter_pk
    primary key (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin
  comment '过滤器配置表';


INSERT INTO shiro_filter (id, filter_shortening, filter_class_path, filter_description, create_time, last_update_time)
VALUES (1, 'jwt', 'org.yang.springboot.shiro.filter.JwtFilter', 'Jwt过滤器', '2019-05-27 06:30:27', '2019-05-27 06:30:27');

create table shiro_filter_chain
(
  id               int auto_increment comment '自增编号'
    primary key,
  url              varchar(16) default ''                not null comment '过滤url',
  permission       varchar(32) default 'anon'            not null comment 'url匹配权限',
  sort_id          int         default 0                 not null comment '排序因子',
  create_time      timestamp   default CURRENT_TIMESTAMP not null comment '创建时间',
  last_update_time timestamp   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '最近一次更新时间'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin
  comment '过滤器链配置表';

INSERT INTO shiro_filter_chain (id, url, permission, sort_id, create_time, last_update_time)
VALUES (1, '/login', 'anon', 0, '2019-05-27 06:58:06', '2019-05-27 06:58:06');
INSERT INTO shiro_filter_chain (id, url, permission, sort_id, create_time, last_update_time)
VALUES (2, '/401', 'anon', 0, '2019-05-27 06:58:06', '2019-05-27 06:58:06');
INSERT INTO shiro_filter_chain (id, url, permission, sort_id, create_time, last_update_time)
VALUES (3, '/500', 'anon', 0, '2019-05-27 06:58:06', '2019-05-27 06:58:06');
INSERT INTO shiro_filter_chain (id, url, permission, sort_id, create_time, last_update_time)
VALUES (4, '/favicon.ico', 'anon', 0, '2019-05-27 06:58:06', '2019-05-27 06:58:06');
INSERT INTO shiro_filter_chain (id, url, permission, sort_id, create_time, last_update_time)
VALUES (5, '/sys/**', 'anon', 0, '2019-05-27 06:58:06', '2019-05-27 06:58:06');
INSERT INTO shiro_filter_chain (id, url, permission, sort_id, create_time, last_update_time)
VALUES (6, '/user/admin', 'jwt,roles[admin]', 1, '2019-05-27 06:58:06', '2019-05-27 06:59:52');
INSERT INTO shiro_filter_chain (id, url, permission, sort_id, create_time, last_update_time)
VALUES (7, '/user/system', 'jwt,roles[system]', 1, '2019-05-27 06:58:06', '2019-05-27 06:59:52');
INSERT INTO shiro_filter_chain (id, url, permission, sort_id, create_time, last_update_time)
VALUES (8, '/**', 'jwt', 10000, '2019-05-27 06:58:06', '2019-05-27 06:59:52');

create table shiro_role
(
  id               int auto_increment comment '角色编号'
    primary key,
  role_name        varchar(16) default ''                not null comment '角色',
  role_desc        varchar(32) default ''                not null comment '角色备注',
  create_time      timestamp   default CURRENT_TIMESTAMP not null comment '创建时间',
  last_update_time timestamp   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '最近一次更新时间'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin
  comment '角色表';

INSERT INTO shiro_role (id, role_name, role_desc, create_time, last_update_time)
VALUES (1, 'admin', '超级管理员', '2019-05-27 08:00:30', '2019-05-27 08:00:30');
INSERT INTO shiro_role (id, role_name, role_desc, create_time, last_update_time)
VALUES (2, 'system', '系统管理员', '2019-05-27 08:00:30', '2019-05-27 08:00:30');

create table user_info
(
  id               int auto_increment comment '用户编号'
    primary key,
  login_account    varchar(32)                         not null comment '用户登录账号',
  create_time      timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
  last_update_time timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '最近一次更新时间'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin
  comment '用户信息表';

INSERT INTO user_info (id, login_account, create_time, last_update_time)
VALUES (1, 'loginAccount', '2019-05-27 08:58:30', '2019-05-27 08:58:30');

create table user_role_relation
(
  id               int auto_increment comment '自增编号'
    primary key,
  user_id          int                                 not null comment '用户编号',
  role_id          int                                 not null comment '角色编号',
  create_time      timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
  last_update_time timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '最近一次更新时间'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin
  comment '用户角色关系表';

INSERT INTO user_role_relation (id, user_id, role_id, create_time, last_update_time)
VALUES (1, 1, 1, '2019-05-27 08:18:56', '2019-05-27 08:18:56');