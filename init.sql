create database if not exists cas;
use cas;

create table if not exists question
(
    id         bigint(11) auto_increment comment '主键'
        primary key,
    question   varchar(1024)        null comment '问题',
    created_at bigint(11)           null comment '创建时间',
    updated_at bigint(11)           null comment '更新时间',
    is_delete  tinyint(3) default 0 null comment '是否删除'
)
    comment '问题列表' charset = utf8mb4;

create index idx_created_at
    on question (created_at);

create index idx_is_delete
    on question (is_delete);

create index idx_updated_at
    on question (updated_at);

create table if not exists user
(
    id         bigint(11) auto_increment comment '主键'
        primary key,
    name       varchar(50)   default ''                                                                               null comment '用户名',
    email      varchar(50)   default 'example@sse.com.cn'                                                             null comment '邮箱',
    phone      varchar(11)   default ''                                                                               null comment '电话号码',
    password   varchar(50)                                                                                            null comment '密码',
    avatar     varchar(1024) default 'https://gw.alipayobjects.com/zos/antfincdn/XAosXuNZyF/BiazfanxmamNRoxxVxka.png' null,
    created_at bigint(11)                                                                                             null comment '创建时间',
    updated_at bigint(11)                                                                                             null comment '更新时间',
    is_delete  tinyint(3)    default 0                                                                                null comment '是否删除',
    constraint uk_name
        unique (name)
)
    comment '用户' charset = utf8mb4;

create index idx_created_at
    on user (created_at);

create index idx_is_delete
    on user (is_delete);

create index idx_updated_at
    on user (updated_at);

create table if not exists user_question
(
    id         bigint(11)    default 0       not null comment '主键',
    user_name  varchar(127)  default ''      not null comment '用户名',
    answer     varchar(1024) default ''      not null comment '答案',
    question   varchar(1024) charset utf8mb4 null comment '问题',
    updated_at bigint(11)                    null comment '更新时间',
    is_delete  tinyint(3)    default 0       null comment '是否删除',
    created_at bigint(11)                    null comment '创建时间'
);


INSERT INTO cas.question (id, question, created_at, updated_at, is_delete) VALUES (1, '你的网名是？', 1585234912, 1585234912, 0);
INSERT INTO cas.question (id, question, created_at, updated_at, is_delete) VALUES (2, '你的宠物的名字？', 1585234912, 1585234912, 0);
INSERT INTO cas.question (id, question, created_at, updated_at, is_delete) VALUES (3, '你的生日是？', 1585234912, 1585234912, 0);
INSERT INTO cas.question (id, question, created_at, updated_at, is_delete) VALUES (4, '你的最喜欢的明星是？', 1585234912, 1585234912, 0);
INSERT INTO cas.question (id, question, created_at, updated_at, is_delete) VALUES (5, '你爱人的名字是？', 1585234912, 1585234912, 0);


INSERT INTO cas.user (id, name, email, phone, password, avatar, created_at, updated_at, is_delete) VALUES (1, '12345', 'bo_bo0425@126.com', '18918503975', '79bc288d6c002864d431f679a4f46d4f', 'https://gw.alipayobjects.com/zos/antfincdn/XAosXuNZyF/BiazfanxmamNRoxxVxka.png', 1585140973, 1585140973, 0);
INSERT INTO cas.user (id, name, email, phone, password, avatar, created_at, updated_at, is_delete) VALUES (2, 'bchen', 'bo_bo0425@126.com', '18918503738', 'b18750a91cc600346c98a8134a62634c', 'https://gw.alipayobjects.com/zos/antfincdn/XAosXuNZyF/BiazfanxmamNRoxxVxka.png', 1585140973, 1585240989, 0);
INSERT INTO cas.user (id, name, email, phone, password, avatar, created_at, updated_at, is_delete) VALUES (3, 'yywang', 'bo_bo0425@126.com', '18918501111', '79bc288d6c002864d431f679a4f46d4f', 'https://gw.alipayobjects.com/zos/antfincdn/XAosXuNZyF/BiazfanxmamNRoxxVxka.png', 1585242715, 1585242715, 0);

INSERT INTO cas.user_question (id, user_name, answer, question, updated_at, is_delete, created_at) VALUES (1, '12345', 'q', '你的网名是？', 1585152354, 0, 1585152354);
INSERT INTO cas.user_question (id, user_name, answer, question, updated_at, is_delete, created_at) VALUES (2, '12345', 'q', '你的宠物的名字？', 1585152355, 0, 1585152355);
INSERT INTO cas.user_question (id, user_name, answer, question, updated_at, is_delete, created_at) VALUES (3, '12345', 'q', '你的生日是？', 1585152355, 0, 1585152355);
INSERT INTO cas.user_question (id, user_name, answer, question, updated_at, is_delete, created_at) VALUES (4, '', 'q', '你的最喜欢的明星是？', 1585152355, 0, 1585152355);
INSERT INTO cas.user_question (id, user_name, answer, question, updated_at, is_delete, created_at) VALUES (5, '', 'q', '你爱人的名字是？', 1585152355, 0, 1585152355);
INSERT INTO cas.user_question (id, user_name, answer, question, updated_at, is_delete, created_at) VALUES (0, 'bchen', 'q', '你的最喜欢的明星是？', 1585239883, 0, 1585239883);
INSERT INTO cas.user_question (id, user_name, answer, question, updated_at, is_delete, created_at) VALUES (0, 'bchen', 'q', '你爱人的名字是？', 1585240989, 0, 1585240989);
INSERT INTO cas.user_question (id, user_name, answer, question, updated_at, is_delete, created_at) VALUES (0, 'bchen', 'q', '你的最喜欢的明星是？', 1585240989, 0, 1585240989);
INSERT INTO cas.user_question (id, user_name, answer, question, updated_at, is_delete, created_at) VALUES (0, 'bchen', 'q', '你的生日是？', 1585240989, 0, 1585240989);
INSERT INTO cas.user_question (id, user_name, answer, question, updated_at, is_delete, created_at) VALUES (0, 'yywang', 'q', '你的最喜欢的明星是？', 1585242715, 0, 1585242715);
INSERT INTO cas.user_question (id, user_name, answer, question, updated_at, is_delete, created_at) VALUES (0, 'yywang', 'q', '你爱人的名字是？', 1585242715, 0, 1585242715);
INSERT INTO cas.user_question (id, user_name, answer, question, updated_at, is_delete, created_at) VALUES (0, 'yywang', 'q', '你的宠物的名字？', 1585242715, 0, 1585242715);
