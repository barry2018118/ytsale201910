# MySQL-Front 5.1  (Build 4.2)

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;


# Host: 127.0.0.1    Database: yt_sale
# ------------------------------------------------------
# Server version 5.5.46

DROP DATABASE IF EXISTS `yt_sale`;
CREATE DATABASE `yt_sale` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `yt_sale`;

#
# Source for table func_action
#

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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='功能表_功能（动作）';

#
# Dumping data for table func_action
#

INSERT INTO `func_action` VALUES (1,1,1,'模块设置',NULL,'/manage/function/module/main',NULL,0,1,1,1,'2018-02-14 09:03:45',1,'2018-02-14 09:03:45');
INSERT INTO `func_action` VALUES (2,1,1,'功能概览',NULL,'/manage/function/view',NULL,0,2,1,1,'2018-02-14 09:03:45',1,'2018-02-14 09:03:45');
INSERT INTO `func_action` VALUES (3,1,1,'功能设置',NULL,'/manage/user/authorize',NULL,0,3,1,1,'2018-02-14 09:03:45',1,'2018-02-14 09:03:45');
INSERT INTO `func_action` VALUES (4,1,2,'权限设置',NULL,'/manage/role/main',NULL,0,1,1,1,'2018-02-14 09:03:45',1,'2018-02-14 09:03:45');
INSERT INTO `func_action` VALUES (5,2,3,'网页信息',NULL,'','',0,1,1,1,'2018-02-14 12:02:05',1,'2018-02-14 12:02:05');
INSERT INTO `func_action` VALUES (6,2,3,'景区信息',NULL,'/info/scenic/main','',0,1,1,1,'2018-02-14 12:02:16',1,'2018-02-14 12:02:16');
INSERT INTO `func_action` VALUES (7,2,3,'商品类别',NULL,'/product/category/main','',0,1,1,1,'2018-02-14 12:02:30',1,'2018-02-14 12:02:30');
INSERT INTO `func_action` VALUES (8,4,6,'平台运营商',NULL,'/platform/enterprise/operation','',0,1,1,1,'2018-02-24 13:47:25',1,'2018-02-24 13:52:24');
INSERT INTO `func_action` VALUES (9,4,6,'平台商户',NULL,'/platform/enterprise/shop','',0,1,1,1,'2018-02-24 13:48:56',1,'2018-02-24 13:48:56');
INSERT INTO `func_action` VALUES (10,4,7,'供应商信息',NULL,'/my/enterprise/supplier','',0,1,1,1,'2018-02-24 13:49:27',1,'2018-02-24 17:43:42');
INSERT INTO `func_action` VALUES (11,4,7,'分销商信息',NULL,'/my/enterprise/distributor','',0,1,1,1,'2018-02-24 13:50:05',1,'2018-02-24 17:44:16');
INSERT INTO `func_action` VALUES (12,4,7,'信用配置',NULL,'','',0,1,1,1,'2018-02-24 13:50:37',1,'2018-02-24 13:50:37');

#
# Source for table func_menu
#

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
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='功能表_菜单';

#
# Dumping data for table func_menu
#

INSERT INTO `func_menu` VALUES (1,1,'功能管理',NULL,NULL,'功能管理',0,1,1,1,'2018-02-14 09:03:45',1,'2018-02-14 09:03:45');
INSERT INTO `func_menu` VALUES (2,1,'权限管理',NULL,NULL,'权限管理',0,2,1,1,'2018-02-14 09:03:45',1,'2018-02-14 09:03:45');
INSERT INTO `func_menu` VALUES (3,2,'信息管理',NULL,'','',0,1,1,1,'2018-02-14 12:01:53',1,'2018-02-14 12:01:53');
INSERT INTO `func_menu` VALUES (4,3,'平台接口管理',NULL,'','',0,1,1,1,'2018-02-14 12:04:59',1,'2018-02-14 12:04:59');
INSERT INTO `func_menu` VALUES (5,3,'接口管理',NULL,'','',0,2,1,1,'2018-02-14 12:05:13',1,'2018-02-14 12:05:13');
INSERT INTO `func_menu` VALUES (6,4,'平台商户管理',NULL,'','',0,1,1,1,'2018-02-14 12:11:05',1,'2018-02-14 12:11:05');
INSERT INTO `func_menu` VALUES (7,4,'商户管理',NULL,'','',0,2,1,1,'2018-02-14 12:11:17',1,'2018-02-14 12:11:17');
INSERT INTO `func_menu` VALUES (8,5,'平台资金管理',NULL,'','',0,1,1,1,'2018-02-14 12:23:03',1,'2018-02-14 12:24:36');
INSERT INTO `func_menu` VALUES (9,5,'资金管理',NULL,'','',0,2,1,1,'2018-02-14 12:23:15',1,'2018-02-14 12:25:16');
INSERT INTO `func_menu` VALUES (10,6,'平台账户管理',NULL,'','',0,1,1,1,'2018-02-14 12:30:59',1,'2018-02-14 12:30:59');
INSERT INTO `func_menu` VALUES (11,6,'账户管理',NULL,'','',0,2,1,1,'2018-02-14 12:31:07',1,'2018-02-14 12:31:07');
INSERT INTO `func_menu` VALUES (12,7,'平台商品管理',NULL,'','',0,1,1,1,'2018-02-14 12:31:54',1,'2018-02-14 12:31:54');
INSERT INTO `func_menu` VALUES (13,7,'商品管理',NULL,'','',0,2,1,1,'2018-02-14 12:32:01',1,'2018-02-14 12:32:01');
INSERT INTO `func_menu` VALUES (14,8,'平台订单管理',NULL,NULL,NULL,0,1,1,1,'2018-02-14 12:32:01',1,'2018-02-14 12:32:01');
INSERT INTO `func_menu` VALUES (15,8,'订单管理',NULL,NULL,NULL,0,2,1,1,'2018-02-14 12:32:01',1,'2018-02-14 12:32:01');
INSERT INTO `func_menu` VALUES (16,9,'平台统计分析',NULL,NULL,NULL,0,1,1,1,'2018-02-14 12:32:01',1,'2018-02-14 12:32:01');
INSERT INTO `func_menu` VALUES (17,9,'统计分析',NULL,NULL,NULL,0,1,1,1,'2018-02-14 12:32:01',1,'2018-02-14 12:32:01');
INSERT INTO `func_menu` VALUES (18,10,'平台辅助功能',NULL,NULL,NULL,0,1,1,1,'2018-02-14 12:32:01',1,'2018-02-14 12:32:01');

#
# Source for table func_module
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='功能表_模块';

#
# Dumping data for table func_module
#

INSERT INTO `func_module` VALUES (1,'平台管理','icon-default','/manage/userFunction/getMenu','',0,1,1,1,'2018-02-14 09:03:45',1,'2018-02-14 12:28:18');
INSERT INTO `func_module` VALUES (2,'基础管理','icon-funcReg','/manage/userFunction/getMenu','',0,2,1,1,'2018-02-14 12:01:14',1,'2018-02-14 12:01:14');
INSERT INTO `func_module` VALUES (3,'接口管理','icon-operator','/manage/userFunction/getMenu','',0,3,1,1,'2018-02-14 12:02:56',1,'2018-02-14 12:02:56');
INSERT INTO `func_module` VALUES (4,'商户管理','icon-account','/manage/userFunction/getMenu','',0,4,1,1,'2018-02-14 12:10:45',1,'2018-02-14 12:10:45');
INSERT INTO `func_module` VALUES (5,'资金管理','icon-finance','/manage/userFunction/getMenu','',0,5,1,1,'2018-02-14 12:14:38',1,'2018-02-14 12:14:50');
INSERT INTO `func_module` VALUES (6,'账户管理','icon-account','/manage/userFunction/getMenu','',0,6,1,1,'2018-02-14 12:30:50',1,'2018-02-14 12:30:50');
INSERT INTO `func_module` VALUES (7,'产品管理','icon-product','/manage/userFunction/getMenu','',0,7,1,1,'2018-02-14 12:31:34',1,'2018-02-14 12:31:34');
INSERT INTO `func_module` VALUES (8,'订单管理','icon-funcReg','/manage/userFunction/getMenu','',0,8,1,1,'2018-02-22 15:02:25',1,'2018-02-22 15:02:25');
INSERT INTO `func_module` VALUES (9,'统计分析','icon-statistical','/manage/userFunction/getMenu','',0,9,1,1,'2018-02-22 15:02:34',1,'2018-02-22 15:02:34');
INSERT INTO `func_module` VALUES (10,'平台辅助','icon-funcReg','/manage/userFunction/getMenu',NULL,0,10,1,1,'2018-02-22 15:02:34',1,'2018-02-22 15:02:34');

#
# Source for table info_action_type
#

DROP TABLE IF EXISTS `info_action_type`;
CREATE TABLE `info_action_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(12) DEFAULT NULL COMMENT '业务动作类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='业务动作类型';

#
# Dumping data for table info_action_type
#


#
# Source for table info_city
#

DROP TABLE IF EXISTS `info_city`;
CREATE TABLE `info_city` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) DEFAULT NULL COMMENT '所属省Id',
  `name` varchar(255) DEFAULT NULL COMMENT '省/市名称',
  `is_delete` tinyint(1) DEFAULT NULL COMMENT '是否删除（1：是，0：否）',
  `sort_no` int(4) DEFAULT NULL COMMENT '排序号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='省市信息';

#
# Dumping data for table info_city
#

INSERT INTO `info_city` VALUES (1,0,'山东省',0,1);
INSERT INTO `info_city` VALUES (2,1,'济南',0,1);
INSERT INTO `info_city` VALUES (3,1,'青岛',0,2);
INSERT INTO `info_city` VALUES (4,0,'广州省',0,1);
INSERT INTO `info_city` VALUES (5,4,'广东',0,2);

#
# Source for table info_enterprise
#

DROP TABLE IF EXISTS `info_enterprise`;
CREATE TABLE `info_enterprise` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `domain` varchar(10) DEFAULT NULL COMMENT '域名',
  `company_type` tinyint(2) DEFAULT NULL COMMENT '0：平台管理、1：运营商、2：供应商、3：分销商',
  `parent_id` int(11) DEFAULT NULL COMMENT '创建企业Id',
  `name` varchar(20) DEFAULT NULL COMMENT '公司名称',
  `contacter_name` varchar(10) DEFAULT NULL COMMENT '公司负责人',
  `contacter_phone` varchar(15) DEFAULT NULL COMMENT '负责人手机',
  `email` varchar(30) DEFAULT NULL COMMENT '公司邮箱',
  `address` varchar(30) DEFAULT NULL COMMENT '公司地址',
  `introduction` varchar(100) DEFAULT NULL COMMENT '公司简介',
  `logo` varchar(100) DEFAULT NULL COMMENT 'Logo',
  `banner` varchar(100) DEFAULT NULL COMMENT 'Banner',
  `province` int(4) DEFAULT NULL COMMENT '省',
  `city` int(4) DEFAULT NULL COMMENT '市',
  `status` tinyint(1) unsigned DEFAULT NULL COMMENT '状态（1：启用，0：停用）',
  `is_delete` tinyint(1) DEFAULT NULL COMMENT '是否删除（1：是，0：否）',
  `create_id` int(11) unsigned DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_id` int(11) unsigned DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='企业信息表';

#
# Dumping data for table info_enterprise
#

INSERT INTO `info_enterprise` VALUES (1,'www',0,1,'平台管理',' Manager','15011230721',NULL,'北京','平台管理机构',NULL,NULL,NULL,NULL,1,0,1,'2018-02-14 09:03:45',1,'2018-02-14 09:03:45');
INSERT INTO `info_enterprise` VALUES (12,'www',1,1,'11111111111111111111','4444444444','15555555555','666@163.com','77777777777777777777','8888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888','','',1,1,1,0,1,'2018-02-27 17:32:48',1,'2018-02-27 17:32:48');

#
# Source for table info_notice
#

DROP TABLE IF EXISTS `info_notice`;
CREATE TABLE `info_notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `notice_type` int(4) DEFAULT NULL COMMENT '公告类型',
  `title` varchar(30) DEFAULT NULL COMMENT '标题',
  `content` varchar(300) DEFAULT NULL COMMENT '内容',
  `is_top` tinyint(1) DEFAULT NULL COMMENT '是否置顶（1：是，0：否）',
  `is_delete` tinyint(1) DEFAULT NULL COMMENT '是否删除（1：是，0：否）',
  `sort_no` int(4) DEFAULT NULL COMMENT '排序号',
  `create_id` int(11) DEFAULT NULL COMMENT '创建人Id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_id` int(11) DEFAULT NULL COMMENT '修改人Id',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公告';

#
# Dumping data for table info_notice
#


#
# Source for table info_notice_enterprise
#

DROP TABLE IF EXISTS `info_notice_enterprise`;
CREATE TABLE `info_notice_enterprise` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `notice_id` int(11) DEFAULT NULL COMMENT '公告Id',
  `enterprise_id` int(11) DEFAULT NULL COMMENT '企业Id',
  `is_read` tinyint(1) DEFAULT NULL COMMENT '是否已读（1：是，0：否）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公告-企业关系表';

#
# Dumping data for table info_notice_enterprise
#


#
# Source for table info_role
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='权限表';

#
# Dumping data for table info_role
#

INSERT INTO `info_role` VALUES (1,'平台管理员',NULL,0,0,1,1,'2018-02-22 14:20:08',1,'2018-02-22 14:20:08');
INSERT INTO `info_role` VALUES (2,'商户管理员',NULL,0,0,NULL,1,'2018-02-14 14:47:20',1,'2018-02-14 14:47:20');

#
# Source for table info_role_action
#

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
# Dumping data for table info_role_action
#


#
# Source for table info_role_menu
#

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

#
# Dumping data for table info_role_menu
#


#
# Source for table info_role_module
#

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

#
# Dumping data for table info_role_module
#


#
# Source for table info_shop
#

DROP TABLE IF EXISTS `info_shop`;
CREATE TABLE `info_shop` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL COMMENT '景区名称',
  `address` varchar(100) DEFAULT NULL COMMENT '景区地址',
  `level` tinyint(1) DEFAULT NULL COMMENT '星级',
  `tel` varchar(30) DEFAULT NULL COMMENT '景区电话',
  `pic` varchar(100) DEFAULT NULL COMMENT '景区图片',
  `province_id` int(11) DEFAULT NULL COMMENT '省',
  `city_id` int(11) DEFAULT NULL COMMENT '市',
  `is_delete` tinyint(1) DEFAULT NULL COMMENT '是否删除（1：是，0：否）',
  `sort_no` int(4) DEFAULT NULL COMMENT '排序号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='景区信息';

#
# Dumping data for table info_shop
#


#
# Source for table info_user
#

DROP TABLE IF EXISTS `info_user`;
CREATE TABLE `info_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account_type` tinyint(2) unsigned DEFAULT NULL COMMENT '账号类型（0：平台、1：商户）',
  `is_master` tinyint(2) unsigned DEFAULT NULL COMMENT '是否主账户（1：是，0：否）',
  `username` varchar(20) DEFAULT NULL COMMENT '用户名',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `name` varchar(12) DEFAULT NULL COMMENT '姓名',
  `phone` varchar(11) DEFAULT NULL COMMENT '移动电话',
  `status` tinyint(2) unsigned DEFAULT NULL COMMENT '状态（1：启用，0：停用）',
  `is_delete` tinyint(2) unsigned DEFAULT NULL COMMENT '是否删除（1：是，0：否）',
  `role_id` int(11) DEFAULT NULL COMMENT '角色Id',
  `enterprise_id` int(11) DEFAULT NULL COMMENT '所属企业Id',
  `create_id` int(11) unsigned DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_id` int(11) unsigned DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

#
# Dumping data for table info_user
#

INSERT INTO `info_user` VALUES (1,0,0,'admin','123456','平台管理员','15011230721',1,0,1,1,1,'2018-02-13 11:08:34',1,'2018-02-13 11:08:34');
INSERT INTO `info_user` VALUES (7,1,1,'12222222222','333333333333333A',NULL,NULL,1,0,NULL,1,NULL,'2018-02-27 17:32:48',NULL,'2018-02-27 17:32:48');

#
# Source for table info_user_action
#

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
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 COMMENT='用户_功能动作_关系表（存储为用户分配的功能动作信息）';

#
# Dumping data for table info_user_action
#

INSERT INTO `info_user_action` VALUES (21,1,1,1,1,1,0,1,1,'2018-02-24 17:44:32',1,'2018-02-24 17:44:32');
INSERT INTO `info_user_action` VALUES (22,1,1,1,1,2,0,1,1,'2018-02-24 17:44:32',1,'2018-02-24 17:44:32');
INSERT INTO `info_user_action` VALUES (23,1,1,1,1,3,0,1,1,'2018-02-24 17:44:32',1,'2018-02-24 17:44:32');
INSERT INTO `info_user_action` VALUES (24,1,1,1,2,4,0,1,1,'2018-02-24 17:44:32',1,'2018-02-24 17:44:32');
INSERT INTO `info_user_action` VALUES (25,1,1,2,3,5,0,1,1,'2018-02-24 17:44:32',1,'2018-02-24 17:44:32');
INSERT INTO `info_user_action` VALUES (26,1,1,2,3,6,0,1,1,'2018-02-24 17:44:32',1,'2018-02-24 17:44:32');
INSERT INTO `info_user_action` VALUES (27,1,1,2,3,7,0,1,1,'2018-02-24 17:44:32',1,'2018-02-24 17:44:32');
INSERT INTO `info_user_action` VALUES (28,1,1,4,6,8,0,1,1,'2018-02-24 17:44:32',1,'2018-02-24 17:44:32');
INSERT INTO `info_user_action` VALUES (29,1,1,4,6,9,0,1,1,'2018-02-24 17:44:32',1,'2018-02-24 17:44:32');
INSERT INTO `info_user_action` VALUES (30,1,1,4,7,10,0,1,1,'2018-02-24 17:44:32',1,'2018-02-24 17:44:32');
INSERT INTO `info_user_action` VALUES (31,1,1,4,7,11,0,1,1,'2018-02-24 17:44:32',1,'2018-02-24 17:44:32');
INSERT INTO `info_user_action` VALUES (32,1,1,4,7,12,0,1,1,'2018-02-24 17:44:32',1,'2018-02-24 17:44:32');

#
# Source for table info_user_menu
#

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
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8 COMMENT='用户_功能菜单_关系表（存储为用户分配的功能菜单信息）';

#
# Dumping data for table info_user_menu
#

INSERT INTO `info_user_menu` VALUES (25,1,1,1,1,0,1,1,'2018-02-24 17:44:32',1,'2018-02-24 17:44:32');
INSERT INTO `info_user_menu` VALUES (26,1,1,1,2,0,1,1,'2018-02-24 17:44:32',1,'2018-02-24 17:44:32');
INSERT INTO `info_user_menu` VALUES (27,1,1,2,3,0,1,1,'2018-02-24 17:44:32',1,'2018-02-24 17:44:32');
INSERT INTO `info_user_menu` VALUES (28,1,1,3,4,0,1,1,'2018-02-24 17:44:32',1,'2018-02-24 17:44:32');
INSERT INTO `info_user_menu` VALUES (29,1,1,4,6,0,1,1,'2018-02-24 17:44:32',1,'2018-02-24 17:44:32');
INSERT INTO `info_user_menu` VALUES (30,1,1,4,7,0,1,1,'2018-02-24 17:44:32',1,'2018-02-24 17:44:32');
INSERT INTO `info_user_menu` VALUES (31,1,1,5,8,0,1,1,'2018-02-24 17:44:32',1,'2018-02-24 17:44:32');
INSERT INTO `info_user_menu` VALUES (32,1,1,6,10,0,1,1,'2018-02-24 17:44:32',1,'2018-02-24 17:44:32');
INSERT INTO `info_user_menu` VALUES (33,1,1,7,12,0,1,1,'2018-02-24 17:44:32',1,'2018-02-24 17:44:32');
INSERT INTO `info_user_menu` VALUES (34,1,1,8,14,0,1,1,'2018-02-24 17:44:32',1,'2018-02-24 17:44:32');
INSERT INTO `info_user_menu` VALUES (35,1,1,9,16,0,1,1,'2018-02-24 17:44:32',1,'2018-02-24 17:44:32');
INSERT INTO `info_user_menu` VALUES (36,1,1,10,18,0,1,1,'2018-02-24 17:44:32',1,'2018-02-24 17:44:32');

#
# Source for table info_user_module
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
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COMMENT='用户_功能模块_关系表（存储为用户分配的功能模块信息）';

#
# Dumping data for table info_user_module
#

INSERT INTO `info_user_module` VALUES (22,1,1,1,0,1,1,'2018-02-24 17:44:32',1,'2018-02-24 17:44:32');
INSERT INTO `info_user_module` VALUES (23,1,1,2,0,1,1,'2018-02-24 17:44:32',1,'2018-02-24 17:44:32');
INSERT INTO `info_user_module` VALUES (24,1,1,3,0,1,1,'2018-02-24 17:44:32',1,'2018-02-24 17:44:32');
INSERT INTO `info_user_module` VALUES (25,1,1,4,0,1,1,'2018-02-24 17:44:32',1,'2018-02-24 17:44:32');
INSERT INTO `info_user_module` VALUES (26,1,1,5,0,1,1,'2018-02-24 17:44:32',1,'2018-02-24 17:44:32');
INSERT INTO `info_user_module` VALUES (27,1,1,6,0,1,1,'2018-02-24 17:44:32',1,'2018-02-24 17:44:32');
INSERT INTO `info_user_module` VALUES (28,1,1,7,0,1,1,'2018-02-24 17:44:32',1,'2018-02-24 17:44:32');
INSERT INTO `info_user_module` VALUES (29,1,1,8,0,1,1,'2018-02-24 17:44:32',1,'2018-02-24 17:44:32');
INSERT INTO `info_user_module` VALUES (30,1,1,9,0,1,1,'2018-02-24 17:44:32',1,'2018-02-24 17:44:32');
INSERT INTO `info_user_module` VALUES (31,1,1,10,0,1,1,'2018-02-24 17:44:32',1,'2018-02-24 17:44:32');

#
# Source for table t_enterprise_captial
#

DROP TABLE IF EXISTS `t_enterprise_captial`;
CREATE TABLE `t_enterprise_captial` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `enterprise_id` int(11) DEFAULT NULL COMMENT '企业Id',
  `total_money` decimal(10,2) DEFAULT NULL COMMENT '平台余额',
  `usable_money` decimal(10,2) DEFAULT NULL COMMENT '可提现金额',
  `forzen_money` decimal(10,2) DEFAULT NULL COMMENT '冻结金额',
  `create_id` int(11) DEFAULT NULL COMMENT '创建人Id',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  `update_id` varchar(255) DEFAULT NULL COMMENT '修改人Id',
  `update_time` date DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='企业-平台资金';

#
# Dumping data for table t_enterprise_captial
#

INSERT INTO `t_enterprise_captial` VALUES (4,1,0,0,0,NULL,'2018-02-27',NULL,'2018-02-27');

#
# Source for table t_product_category
#

DROP TABLE IF EXISTS `t_product_category`;
CREATE TABLE `t_product_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) DEFAULT NULL COMMENT '商品类别',
  `is_delete` tinyint(1) DEFAULT NULL COMMENT '是否删除（1：是，0：否）',
  `sort_no` tinyint(3) DEFAULT NULL COMMENT '排序号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='商品类别信息表';

#
# Dumping data for table t_product_category
#

INSERT INTO `t_product_category` VALUES (1,'常规代理',0,1);
INSERT INTO `t_product_category` VALUES (2,'时段包票',0,2);
INSERT INTO `t_product_category` VALUES (3,'第三方票务',0,3);

/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
