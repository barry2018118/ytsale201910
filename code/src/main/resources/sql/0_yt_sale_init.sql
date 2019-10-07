DROP DATABASE IF EXISTS `yt_sale`;
CREATE DATABASE `yt_sale` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `yt_sale`;

#
# Source for table func_module, func_menu, func_action
#
DROP TABLE IF EXISTS `func_module`;
CREATE TABLE `func_module` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL COMMENT '模块名称',
  `icon` varchar(60) DEFAULT NULL COMMENT '模块图标',
  `url` varchar(60) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL COMMENT '描述（介绍）',
  `is_delete` tinyint(1) DEFAULT NULL COMMENT '是否删除（1：是，0：否）',
  `sort_no` tinyint(2) DEFAULT NULL COMMENT '排序号',
  `is_distribution` tinyint(1) DEFAULT NULL COMMENT '是否允许分配（1：是，0：否）',
  `create_id` int(11) unsigned DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_id` int(11) unsigned DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='功能表_模块';
INSERT INTO `func_module` VALUES (1,'平台管理','icon-default','/manage/userFunction/getMenu',NULL,0,1,1,1,'2018-02-14 09:03:45',1,'2018-02-14 09:03:45');

DROP TABLE IF EXISTS `func_menu`;
CREATE TABLE `func_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `module_id` int(11) DEFAULT NULL COMMENT '所属模块Id',
  `name` varchar(20) DEFAULT NULL COMMENT '模块名称',
  `icon` varchar(60) DEFAULT NULL COMMENT '模块图标',
  `url` varchar(60) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL COMMENT '描述（介绍）',
  `is_delete` tinyint(1) DEFAULT NULL COMMENT '是否删除（1：是，0：否）',
  `sort_no` tinyint(2) DEFAULT NULL COMMENT '排序号',
  `is_distribution` tinyint(1) DEFAULT NULL COMMENT '是否允许分配（1：是，0：否）',
  `create_id` int(11) unsigned DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_id` int(11) unsigned DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='功能表_菜单';
INSERT INTO `func_menu` VALUES (1,1,'功能管理',NULL,NULL,'功能管理',0,1,1,1,'2018-02-14 09:03:45',1,'2018-02-14 09:03:45');
INSERT INTO `func_menu` VALUES (2,1,'权限管理',NULL,NULL,'权限管理',0,2,1,1,'2018-02-14 09:03:45',1,'2018-02-14 09:03:45');

DROP TABLE IF EXISTS `func_action`;
CREATE TABLE `func_action` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `module_id` int(11) DEFAULT NULL COMMENT '所属模块Id',
  `menu_id` int(11) DEFAULT NULL COMMENT '所属菜单Id',
  `name` varchar(20) DEFAULT NULL COMMENT '模块名称',
  `icon` varchar(60) DEFAULT NULL COMMENT '模块图标',
  `url` varchar(60) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL COMMENT '描述（介绍）',
  `is_delete` tinyint(1) DEFAULT NULL COMMENT '是否删除（1：是，0：否）',
  `sort_no` tinyint(2) DEFAULT NULL COMMENT '排序号',
  `is_distribution` tinyint(1) DEFAULT NULL COMMENT '是否允许分配（1：是，0：否）',
  `create_id` int(11) unsigned DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_id` int(11) unsigned DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='功能表_功能（动作）';
INSERT INTO `func_action` VALUES (1,1,1,'模块设置',NULL,'/manage/function/module/main',NULL,0,1,1,1,'2018-02-14 09:03:45',1,'2018-02-14 09:03:45');
INSERT INTO `func_action` VALUES (2,1,1,'功能概览',NULL,'/manage/function/view',NULL,0,2,1,1,'2018-02-14 09:03:45',1,'2018-02-14 09:03:45');
INSERT INTO `func_action` VALUES (3,1,1,'功能设置',NULL,'/manage/user/authorize',NULL,0,3,1,1,'2018-02-14 09:03:45',1,'2018-02-14 09:03:45');
INSERT INTO `func_action` VALUES (4,1,2,'权限设置',NULL,'/manage/role/main',NULL,0,1,1,1,'2018-02-14 09:03:45',1,'2018-02-14 09:03:45');


#
# Source for table info_role, info_role_module, info_role_menu, info_role_action
#
DROP TABLE IF EXISTS `info_role`;
CREATE TABLE `info_role` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(25) DEFAULT NULL COMMENT '权限组名称',
  `descript` varchar(255) DEFAULT NULL,
  `is_delete` tinyint(1) DEFAULT NULL COMMENT '是否删除（1：是，0：否）',
  `sort_no` tinyint(2) DEFAULT NULL COMMENT '排序号',
  `is_distribution` tinyint(1) DEFAULT NULL COMMENT '是否允许分配（1：是，0：否）',
  `create_id` int(11) unsigned DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_id` int(11) unsigned DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='权限表';
INSERT INTO `info_role` VALUES (1,'平台管理员','平台管理员',0,1,1,1,'2018-02-14 09:03:45',1,'2018-02-14 09:03:45');

DROP TABLE IF EXISTS `info_role_module`;
CREATE TABLE `info_role_module` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL COMMENT '权限组Id',
  `module_id` int(11) DEFAULT NULL COMMENT '功能模块Id',
  `sort_no` tinyint(2) unsigned DEFAULT NULL COMMENT '排序号',
  `create_id` int(11) unsigned DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_id` int(11) unsigned DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unik_role_module` (`role_id`,`module_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限_功能模块_关系表';

DROP TABLE IF EXISTS `info_role_menu`;
CREATE TABLE `info_role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL COMMENT '权限组Id',
  `module_id` int(11) DEFAULT NULL COMMENT '功能模块Id',
  `menu_id` int(11) DEFAULT NULL COMMENT '功能菜单Id',
  `sort_no` tinyint(2) unsigned DEFAULT NULL COMMENT '排序号',
  `create_id` int(11) unsigned DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_id` int(11) unsigned DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unik_role_menu` (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限_功能菜单_关系表';

DROP TABLE IF EXISTS `info_role_action`;
CREATE TABLE `info_role_action` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL COMMENT '权限组Id',
  `module_id` int(11) DEFAULT NULL COMMENT '功能模块Id',
  `menu_id` int(11) DEFAULT NULL COMMENT '功能菜单Id',
  `action_id` int(11) DEFAULT NULL COMMENT '功能（动作）Id',
  `sort_no` tinyint(2) DEFAULT NULL COMMENT '排序号（针对button）',
  `create_id` int(11) unsigned DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_id` int(11) unsigned DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unik_role_button` (`role_id`,`action_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限_功能按钮_关系表';


#
# Source for table info_user_module, info_user_menu, info_user_action
#
DROP TABLE IF EXISTS `info_user_module`;
CREATE TABLE `info_user_module` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `enterprise_id` int(11) DEFAULT NULL COMMENT '企业Id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户Id',
  `module_id` int(11) DEFAULT NULL COMMENT '功能模块Id',
  `is_delete` tinyint(1) DEFAULT NULL COMMENT '是否删除（1：是，0：否）',
  `sort_no` tinyint(2) DEFAULT NULL COMMENT '排序号',
  `create_id` int(11) unsigned DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_id` int(11) unsigned DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户_功能模块_关系表（存储为用户分配的功能模块信息）';
INSERT INTO `info_user_module` VALUES (1,1,1,1,0,1,1,'2018-02-14 09:16:32',1,'2018-02-14 09:16:32');

DROP TABLE IF EXISTS `info_user_menu`;
CREATE TABLE `info_user_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `enterprise_id` int(11) DEFAULT NULL COMMENT '企业Id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户Id',
  `module_id` int(11) DEFAULT NULL COMMENT '功能模块Id',
  `menu_id` int(11) DEFAULT NULL COMMENT '功能菜单Id',
  `is_delete` tinyint(1) DEFAULT NULL COMMENT '是否删除（1：是，0：否）',
  `sort_no` tinyint(2) DEFAULT NULL COMMENT '排序号',
  `create_id` int(11) unsigned DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_id` int(11) unsigned DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户_功能菜单_关系表（存储为用户分配的功能菜单信息）';
INSERT INTO `info_user_menu` VALUES (1,1,1,1,1,0,1,1,'2018-02-14 09:16:32',1,'2018-02-14 09:16:32');
INSERT INTO `info_user_menu` VALUES (2,1,1,1,2,0,1,1,'2018-02-14 09:16:32',1,'2018-02-14 09:16:32');

DROP TABLE IF EXISTS `info_user_action`;
CREATE TABLE `info_user_action` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `enterprise_id` int(11) DEFAULT NULL COMMENT '企业Id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户Id',
  `module_id` int(11) DEFAULT NULL COMMENT '功能模块Id',
  `menu_id` int(11) DEFAULT NULL COMMENT '功能菜单Id',
  `action_id` int(11) DEFAULT NULL COMMENT '功能动作Id',
  `is_delete` tinyint(1) DEFAULT NULL COMMENT '是否删除（1：是，0：否）',
  `sort_no` tinyint(2) DEFAULT NULL COMMENT '排序号',
  `create_id` int(11) unsigned DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_id` int(11) unsigned DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='用户_功能动作_关系表（存储为用户分配的功能动作信息）';
INSERT INTO `info_user_action` VALUES (1,1,1,1,1,1,0,1,1,'2018-02-14 09:16:32',1,'2018-02-14 09:16:32');
INSERT INTO `info_user_action` VALUES (2,1,1,1,1,2,0,1,1,'2018-02-14 09:16:32',1,'2018-02-14 09:16:32');
INSERT INTO `info_user_action` VALUES (3,1,1,1,1,3,0,1,1,'2018-02-14 09:16:32',1,'2018-02-14 09:16:32');
INSERT INTO `info_user_action` VALUES (4,1,1,1,2,4,0,1,1,'2018-02-14 09:16:32',1,'2018-02-14 09:16:32');


#
# Source for table info_enterprise, info_user
#
DROP TABLE IF EXISTS `info_enterprise`;
CREATE TABLE `info_enterprise` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `is_platform` tinyint(1) DEFAULT NULL COMMENT '是否平台（1：是，0：否）',
  `domain` varchar(10) DEFAULT NULL COMMENT '域名',
  `name` varchar(20) DEFAULT NULL COMMENT '企业名称',
  `contacter_name` varchar(20) DEFAULT NULL COMMENT '联系人',
  `contacter_tel` varchar(11) DEFAULT NULL COMMENT '联系电话',
  `address` varchar(30) DEFAULT NULL COMMENT '公司地址',
  `introduction` varchar(200) DEFAULT NULL COMMENT '公司简介',
  `status` tinyint(1) unsigned DEFAULT NULL COMMENT '状态（1：启用，0：停用）',
  `is_delete` tinyint(1) DEFAULT NULL COMMENT '是否删除（1：是，0：否）',
  `create_id` int(11) unsigned DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_id` int(11) unsigned DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='企业信息表';
INSERT INTO `info_enterprise` VALUES (1,1,'www','平台管理',' Manager','15011230721','北京','平台管理机构',1,0,1,'2018-02-14 09:03:45',1,'2018-02-14 09:03:45');

DROP TABLE IF EXISTS `info_user`;
CREATE TABLE `info_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account_type` tinyint(1) unsigned DEFAULT NULL COMMENT '账号类型（0：平台、1：商户）',
  `is_master` tinyint(1) unsigned DEFAULT NULL COMMENT '是否主账户（1：是，0：否）',
  `username` varchar(20) DEFAULT NULL COMMENT '用户名',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `name` varchar(12) DEFAULT NULL COMMENT '姓名',
  `phone` varchar(20) DEFAULT NULL COMMENT '移动电话',
  `status` tinyint(1) unsigned DEFAULT NULL COMMENT '状态（1：启用，0：停用）',
  `is_delete` tinyint(1) DEFAULT NULL COMMENT '是否删除（1：是，0：否）',
  `role_id` int(11) DEFAULT NULL COMMENT '角色Id',
  `enterprise_id` int(11) DEFAULT NULL COMMENT '所属企业Id',
  `create_id` int(11) unsigned DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_id` int(11) unsigned DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户信息表';
INSERT INTO `info_user` VALUES (1,0,0,'15011230721','Abc123','平台管理员','15011230721',1,0,1,1,1,'2018-02-13 11:08:34',1,'2018-02-13 11:08:34');


