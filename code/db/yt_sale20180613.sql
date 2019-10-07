-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: 49.4.6.218    Database: yt_sale
-- ------------------------------------------------------
-- Server version	5.6.39-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `func_action`
--

DROP TABLE IF EXISTS `func_action`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8 COMMENT='功能表_功能（动作）';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `func_menu`
--

DROP TABLE IF EXISTS `func_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `func_module`
--

DROP TABLE IF EXISTS `func_module`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='功能表_模块';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `info_action_type`
--

DROP TABLE IF EXISTS `info_action_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `info_action_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(12) DEFAULT NULL COMMENT '业务动作类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='业务动作类型';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `info_captial_channel`
--

DROP TABLE IF EXISTS `info_captial_channel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `info_captial_channel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL COMMENT '资金渠道名称',
  `rate` decimal(10,4) DEFAULT NULL COMMENT '费率',
  `icon` varchar(20) DEFAULT NULL COMMENT '图标',
  `request_url` varchar(300) DEFAULT NULL COMMENT '请求地址',
  `callback_url` varchar(300) DEFAULT NULL COMMENT '回调地址',
  `is_delete` tinyint(1) DEFAULT NULL COMMENT '是否删除（1：是，0：否）',
  `sort_no` int(4) DEFAULT NULL COMMENT '排序号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='资金渠道';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `info_city`
--

DROP TABLE IF EXISTS `info_city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `info_city` (
  `id` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  `parent_id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `short` varchar(50) NOT NULL,
  `is_delete` int(1) DEFAULT NULL,
  `sort_no` int(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `info_enterprise`
--

DROP TABLE IF EXISTS `info_enterprise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `info_enterprise` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `domain` varchar(10) DEFAULT NULL COMMENT '域名',
  `company_type` tinyint(2) DEFAULT NULL COMMENT '0：平台管理、1：运营商、2：供应商、3：分销商',
  `parent_id` int(11) DEFAULT NULL COMMENT '创建企业Id',
  `name` varchar(20) DEFAULT NULL COMMENT '公司名称',
  `contacter_name` varchar(10) DEFAULT NULL COMMENT '公司负责人',
  `contacter_phone` varchar(15) DEFAULT NULL COMMENT '负责人手机',
  `email` varchar(40) DEFAULT NULL COMMENT '公司邮箱',
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
  `customer_phone` varchar(20) DEFAULT NULL COMMENT '客服电话',
  `platform_name` varchar(15) DEFAULT NULL COMMENT '平台名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='企业信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `info_notice`
--

DROP TABLE IF EXISTS `info_notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `info_notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `notice_type` int(4) DEFAULT NULL COMMENT '公告类型',
  `title` varchar(30) DEFAULT NULL COMMENT '标题',
  `content` varchar(300) DEFAULT NULL COMMENT '内容',
  `is_top` tinyint(1) DEFAULT NULL COMMENT '是否置顶（1：是，0：否）',
  `is_delete` tinyint(1) DEFAULT NULL COMMENT '是否删除（1：是，0：否）',
  `sort_no` int(4) DEFAULT NULL COMMENT '排序号',
  `enterprise_id` int(11) DEFAULT NULL COMMENT '企业Id',
  `create_id` int(11) DEFAULT NULL COMMENT '创建人Id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_id` int(11) DEFAULT NULL COMMENT '修改人Id',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='公告';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `info_notice_enterprise`
--

DROP TABLE IF EXISTS `info_notice_enterprise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `info_notice_enterprise` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `notice_id` int(11) DEFAULT NULL COMMENT '公告Id',
  `enterprise_id` int(11) DEFAULT NULL COMMENT '企业Id',
  `is_read` tinyint(1) DEFAULT NULL COMMENT '是否已读（1：是，0：否）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公告-企业关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `info_role`
--

DROP TABLE IF EXISTS `info_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `info_role` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(25) DEFAULT NULL COMMENT '权限组名称',
  `descript` varchar(255) DEFAULT NULL,
  `is_delete` tinyint(1) DEFAULT NULL COMMENT '是否删除（1：是，0：否）',
  `sort_no` tinyint(2) DEFAULT NULL COMMENT '排序号',
  `enterprise_id` int(11) DEFAULT NULL COMMENT '企业Id',
  `is_distribution` tinyint(1) DEFAULT NULL COMMENT '是否允许分配（1：是，0：否）',
  `create_id` int(11) unsigned DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_id` int(11) unsigned DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `info_role_action`
--

DROP TABLE IF EXISTS `info_role_action`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=266 DEFAULT CHARSET=utf8 COMMENT='权限_功能按钮_关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `info_role_menu`
--

DROP TABLE IF EXISTS `info_role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8 COMMENT='权限_功能菜单_关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `info_role_module`
--

DROP TABLE IF EXISTS `info_role_module`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=utf8 COMMENT='权限_功能模块_关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `info_shop`
--

DROP TABLE IF EXISTS `info_shop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
  `shop_no` varchar(63) NOT NULL DEFAULT '' COMMENT '景区编号',
  `supplier` varchar(63) NOT NULL DEFAULT '' COMMENT '票纸上显示供应商',
  `printnum` tinyint(4) NOT NULL DEFAULT '1' COMMENT '每次出纸联数',
  `isprint` tinyint(1) NOT NULL DEFAULT '2' COMMENT '打印附加 1平台商 姓名 手机 2 姓名 手机 3 无',
  PRIMARY KEY (`id`),
  UNIQUE KEY `shop_no` (`shop_no`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='景区信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `info_user`
--

DROP TABLE IF EXISTS `info_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COMMENT='用户信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `info_user_action`
--

DROP TABLE IF EXISTS `info_user_action`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=4418 DEFAULT CHARSET=utf8 COMMENT='用户_功能动作_关系表（存储为用户分配的功能动作信息）';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `info_user_menu`
--

DROP TABLE IF EXISTS `info_user_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=3207 DEFAULT CHARSET=utf8 COMMENT='用户_功能菜单_关系表（存储为用户分配的功能菜单信息）';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `info_user_module`
--

DROP TABLE IF EXISTS `info_user_module`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=3207 DEFAULT CHARSET=utf8 COMMENT='用户_功能模块_关系表（存储为用户分配的功能模块信息）';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_calendar_cost_section`
--

DROP TABLE IF EXISTS `t_calendar_cost_section`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_calendar_cost_section` (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sproduct_id` int(11) NOT NULL COMMENT '产品id',
  `start_date` date NOT NULL COMMENT '开始时间',
  `end_date` date NOT NULL COMMENT '结束时间（包含在区间内）',
  `cost_price` decimal(8,2) DEFAULT NULL COMMENT '成本价',
  `status` tinyint(1) NOT NULL COMMENT '状态（5正常,1删除 )',
  `created_at` int(11) NOT NULL,
  `created_id` int(11) NOT NULL,
  `created_by` varchar(128) NOT NULL,
  `updated_at` int(11) NOT NULL,
  `updated_id` int(11) NOT NULL,
  `updated_by` varchar(128) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `cost_section_index` (`status`,`sproduct_id`,`start_date`,`end_date`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='时间段内产品组产品成本价';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_calendar_cost_special`
--

DROP TABLE IF EXISTS `t_calendar_cost_special`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_calendar_cost_special` (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sproduct_id` int(11) NOT NULL COMMENT '产品id',
  `date` date NOT NULL COMMENT '日期',
  `cost_price` decimal(8,2) DEFAULT '0.00' COMMENT '成本价',
  `status` tinyint(1) NOT NULL COMMENT '状态（5正常,1删除 )',
  `created_at` int(11) NOT NULL,
  `created_id` int(11) NOT NULL,
  `created_by` varchar(128) NOT NULL,
  `updated_at` int(11) NOT NULL,
  `updated_id` int(11) NOT NULL,
  `updated_by` varchar(128) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `cost_special_index` (`status`,`sproduct_id`,`date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='某天产品组产品成本价';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_calendar_cost_week`
--

DROP TABLE IF EXISTS `t_calendar_cost_week`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_calendar_cost_week` (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sproduct_id` int(11) NOT NULL COMMENT '用户产品id',
  `start_date` date NOT NULL COMMENT '开始时间',
  `end_date` date NOT NULL COMMENT '结束时间（包含在区间内）',
  `week` varchar(16) NOT NULL COMMENT '周几,如1，2，3表示周一，周二，周三',
  `cost_price` decimal(8,2) DEFAULT '0.00' COMMENT '成本价',
  `status` tinyint(1) NOT NULL COMMENT '状态（5正常,1删除 )',
  `created_at` int(11) NOT NULL,
  `created_id` int(11) NOT NULL,
  `created_by` varchar(128) NOT NULL,
  `updated_at` int(11) NOT NULL,
  `updated_id` int(11) NOT NULL,
  `updated_by` varchar(128) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `cost_week_index` (`status`,`sproduct_id`,`start_date`,`end_date`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='周期产品组产品成本价';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_calendar_ota_section`
--

DROP TABLE IF EXISTS `t_calendar_ota_section`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_calendar_ota_section` (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sproduct_id` int(11) NOT NULL COMMENT '产品id',
  `enterprise_id` int(11) NOT NULL COMMENT '企业id',
  `start_date` date NOT NULL COMMENT '开始时间',
  `end_date` date NOT NULL COMMENT '结束时间（包含在区间内）',
  `ota_price` decimal(8,2) DEFAULT NULL COMMENT '成本价',
  `status` tinyint(1) NOT NULL COMMENT '状态（5正常,1删除 )',
  `created_at` int(11) NOT NULL,
  `created_id` int(11) NOT NULL,
  `created_by` varchar(128) NOT NULL,
  `updated_at` int(11) NOT NULL,
  `updated_id` int(11) NOT NULL,
  `updated_by` varchar(128) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ota_section_index` (`status`,`sproduct_id`,`start_date`,`end_date`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='时间段内产品OTA价';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_calendar_ota_special`
--

DROP TABLE IF EXISTS `t_calendar_ota_special`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_calendar_ota_special` (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `enterprise_id` int(11) NOT NULL COMMENT '企业id',
  `sproduct_id` int(11) NOT NULL COMMENT '产品id',
  `date` date NOT NULL COMMENT '日期',
  `ota_price` decimal(8,2) DEFAULT '0.00' COMMENT '成本价',
  `status` tinyint(1) NOT NULL COMMENT '状态（5正常,1删除 )',
  `created_at` int(11) NOT NULL,
  `created_id` int(11) NOT NULL,
  `created_by` varchar(128) NOT NULL,
  `updated_at` int(11) NOT NULL,
  `updated_id` int(11) NOT NULL,
  `updated_by` varchar(128) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ota_special_index` (`status`,`sproduct_id`,`date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='某天产品OTA价';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_calendar_ota_week`
--

DROP TABLE IF EXISTS `t_calendar_ota_week`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_calendar_ota_week` (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sproduct_id` int(11) NOT NULL COMMENT '用户产品id',
  `enterprise_id` int(11) NOT NULL COMMENT '企业id',
  `start_date` date NOT NULL COMMENT '开始时间',
  `end_date` date NOT NULL COMMENT '结束时间（包含在区间内）',
  `week` varchar(16) NOT NULL COMMENT '周几,如1，2，3表示周一，周二，周三',
  `ota_price` decimal(8,2) DEFAULT '0.00' COMMENT '成本价',
  `status` tinyint(1) NOT NULL COMMENT '状态（5正常,1删除 )',
  `created_at` int(11) NOT NULL,
  `created_id` int(11) NOT NULL,
  `created_by` varchar(128) NOT NULL,
  `updated_at` int(11) NOT NULL,
  `updated_id` int(11) NOT NULL,
  `updated_by` varchar(128) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ota_week_index` (`status`,`sproduct_id`,`start_date`,`end_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='周期产品OTA价';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_calendar_profit_section`
--

DROP TABLE IF EXISTS `t_calendar_profit_section`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_calendar_profit_section` (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `group_id` int(11) NOT NULL COMMENT '产品组Id',
  `sproduct_id` int(11) NOT NULL COMMENT '产品Id',
  `start_date` date NOT NULL COMMENT '开始时间',
  `end_date` date NOT NULL COMMENT '结束时间（包含在区间内）',
  `profit_price` decimal(8,2) DEFAULT NULL COMMENT '利润',
  `status` tinyint(1) NOT NULL COMMENT '状态（5正常,1删除 )',
  `created_at` int(11) NOT NULL,
  `created_id` int(11) NOT NULL,
  `created_by` varchar(128) NOT NULL,
  `updated_at` int(11) NOT NULL,
  `updated_id` int(11) NOT NULL,
  `updated_by` varchar(128) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `profit_section_index` (`status`,`sproduct_id`,`group_id`,`start_date`,`end_date`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='时间段内产品组产品利润';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_calendar_profit_special`
--

DROP TABLE IF EXISTS `t_calendar_profit_special`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_calendar_profit_special` (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `group_id` int(11) NOT NULL COMMENT '产品组Id',
  `sproduct_id` int(11) NOT NULL COMMENT '产品Id',
  `date` date NOT NULL COMMENT '日期',
  `profit_price` decimal(8,2) DEFAULT '0.00' COMMENT '成本价',
  `status` tinyint(1) NOT NULL COMMENT '状态（5正常,1删除 )',
  `created_at` int(11) NOT NULL,
  `created_id` int(11) NOT NULL,
  `created_by` varchar(128) NOT NULL,
  `updated_at` int(11) NOT NULL,
  `updated_id` int(11) NOT NULL,
  `updated_by` varchar(128) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `profit_special_index` (`status`,`sproduct_id`,`group_id`,`date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='某天产品组产品利润';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_calendar_profit_week`
--

DROP TABLE IF EXISTS `t_calendar_profit_week`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_calendar_profit_week` (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `group_id` int(11) NOT NULL COMMENT '产品组Id',
  `sproduct_id` int(11) NOT NULL COMMENT '产品Id',
  `start_date` date NOT NULL COMMENT '开始时间',
  `end_date` date NOT NULL COMMENT '结束时间（包含在区间内）',
  `week` varchar(16) NOT NULL COMMENT '周几,如1，2，3表示周一，周二，周三',
  `profit_price` decimal(8,2) DEFAULT '0.00' COMMENT '利润',
  `status` tinyint(1) NOT NULL COMMENT '状态（5正常,1删除 )',
  `created_at` int(11) NOT NULL,
  `created_id` int(11) NOT NULL,
  `created_by` varchar(128) NOT NULL,
  `updated_at` int(11) NOT NULL,
  `updated_id` int(11) NOT NULL,
  `updated_by` varchar(128) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `profit_week_index` (`status`,`sproduct_id`,`group_id`,`start_date`,`end_date`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='周期产品组产品利润价';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_code`
--

DROP TABLE IF EXISTS `t_code`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_code` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `orders_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '订单Id',
  `card_pwd` varchar(32) NOT NULL DEFAULT '' COMMENT '电子码',
  `code_name` varchar(100) NOT NULL DEFAULT '单门票' COMMENT '门票名称',
  `sproduct_id` int(11) NOT NULL DEFAULT '0' COMMENT '产品Id',
  `enterprise_id` int(11) DEFAULT NULL COMMENT '所属企业Id',
  `shop_id` varchar(110) NOT NULL DEFAULT '' COMMENT '商户Id',
  `num` smallint(6) unsigned NOT NULL DEFAULT '0' COMMENT '数量',
  `startTim` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '开始验证时间',
  `endTim` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '验证时间截止',
  `printnum` smallint(6) unsigned NOT NULL DEFAULT '0' COMMENT '已核销数',
  `locknum` smallint(6) unsigned NOT NULL DEFAULT '0' COMMENT '已退款数',
  `clocknum` smallint(6) unsigned NOT NULL DEFAULT '0' COMMENT '冻结数（退款申请冻结）',
  `tuanname` varchar(32) NOT NULL DEFAULT '' COMMENT '下单来源（PC、APP、第三方系统）',
  `real_name` varchar(255) DEFAULT '' COMMENT '实名制信息',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '10' COMMENT '状态：同订单状态',
  `updated_at` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '更新时间',
  `version` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `card_pwd` (`card_pwd`),
  KEY `orderid` (`orders_id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 COMMENT='码信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_code_provide`
--

DROP TABLE IF EXISTS `t_code_provide`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_code_provide` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `card_pwd` varchar(32) NOT NULL DEFAULT '' COMMENT '码',
  `status` tinyint(1) NOT NULL DEFAULT '5' COMMENT '状态（5可用、1已使用）',
  PRIMARY KEY (`id`),
  UNIQUE KEY `card_pwd` (`card_pwd`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1221 DEFAULT CHARSET=utf8 COMMENT='码池表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_enterprise_account_log`
--

DROP TABLE IF EXISTS `t_enterprise_account_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_enterprise_account_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `child_id` int(11) DEFAULT NULL COMMENT '下级企业Id',
  `parent_id` int(11) DEFAULT NULL COMMENT '上级企业Id',
  `action_type` int(11) DEFAULT NULL COMMENT '业务行为类型',
  `action_id` int(11) DEFAULT NULL COMMENT '具体的行为id',
  `capital_type` tinyint(2) DEFAULT NULL COMMENT '资金变动方式（0： 预收款、1：平台资金）',
  `old_price` decimal(10,2) DEFAULT NULL COMMENT '变动前资金',
  `price` decimal(10,2) DEFAULT NULL COMMENT '本次变动金额',
  `current_price` decimal(10,2) DEFAULT NULL COMMENT '变动后资金',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `terminal` varchar(10) DEFAULT NULL COMMENT '使用终端',
  `phone_system` varchar(255) DEFAULT NULL COMMENT '移动电话系统',
  `action_desc` varchar(100) DEFAULT NULL COMMENT '具体的行为描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=109 DEFAULT CHARSET=utf8 COMMENT='企业资金变动';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_enterprise_bank_card`
--

DROP TABLE IF EXISTS `t_enterprise_bank_card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_enterprise_bank_card` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `enterprise_id` int(11) DEFAULT NULL COMMENT '企业Id',
  `card_no` varchar(40) DEFAULT NULL COMMENT '卡号',
  `bank` varchar(20) DEFAULT NULL COMMENT '所属银行',
  `card_master` varchar(20) DEFAULT NULL COMMENT '持卡人',
  `card_master_no` varchar(18) DEFAULT NULL COMMENT '持卡人身份证号',
  `card_master_phone` varchar(11) DEFAULT NULL COMMENT '持卡人手机号',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `is_delete` tinyint(1) DEFAULT NULL COMMENT '是否删除（1：是，0：否）',
  `sort_no` tinyint(2) DEFAULT NULL COMMENT '排序号',
  `create_id` int(11) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `address` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='企业-银行卡';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_enterprise_capital`
--

DROP TABLE IF EXISTS `t_enterprise_capital`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_enterprise_capital` (
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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='企业-平台资金';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_enterprise_orders`
--

DROP TABLE IF EXISTS `t_enterprise_orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_enterprise_orders` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `orders_id` int(11) NOT NULL DEFAULT '0' COMMENT '订单id',
  `orderno` varchar(48) NOT NULL COMMENT '订单编号',
  `enterprise_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '订单所属企业id',
  `enterprise_name` varchar(128) NOT NULL DEFAULT '' COMMENT '订单所属企业名称',
  `parent_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '上级id',
  `parent_name` varchar(128) NOT NULL DEFAULT '' COMMENT '上级名称',
  `payment_method` tinyint(1) NOT NULL DEFAULT '1',
  `catid1` int(11) unsigned NOT NULL DEFAULT '0',
  `type_id` tinyint(2) NOT NULL,
  `sproduct_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '产品id',
  `uproduct_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '产品分销id',
  `uproduct_name` varchar(64) DEFAULT NULL COMMENT '产品分销名称',
  `num` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '购买数量',
  `fx_price` decimal(10,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '我对上级的购买价',
  `price` decimal(10,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '购买总价',
  `province` int(11) NOT NULL DEFAULT '0' COMMENT '商品所属省',
  `city` int(11) NOT NULL DEFAULT '0' COMMENT '商品所属城市',
  `supplier_id` int(11) NOT NULL DEFAULT '0' COMMENT '商品供应商Id',
  `supplier_name` varchar(128) NOT NULL DEFAULT '' COMMENT '商品供应商名称',
  `shop_id` int(11) NOT NULL DEFAULT '0' COMMENT '商品所属商户Id',
  `shop_name` varchar(128) NOT NULL DEFAULT '' COMMENT '商品所属商户名称',
  `is_order` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `is_audit` tinyint(1) unsigned NOT NULL DEFAULT '2',
  `refund_fee_type` tinyint(1) unsigned NOT NULL DEFAULT '1',
  `refund_fee` decimal(10,2) unsigned NOT NULL DEFAULT '0.00',
  `lockprice` decimal(10,2) unsigned NOT NULL DEFAULT '0.00',
  `printnum` smallint(6) unsigned NOT NULL DEFAULT '0' COMMENT '核销数',
  `locknum` smallint(6) unsigned NOT NULL DEFAULT '0' COMMENT '已退数',
  `clocknum` smallint(6) unsigned NOT NULL DEFAULT '0' COMMENT '冻结数',
  `is_remind` tinyint(1) unsigned NOT NULL DEFAULT '1',
  `status` tinyint(4) NOT NULL DEFAULT '10' COMMENT '订单状态',
  `created_id` int(11) unsigned NOT NULL,
  `created_by` varchar(128) NOT NULL,
  `created_at` int(11) unsigned NOT NULL,
  `sale_mode` tinyint(1) DEFAULT NULL COMMENT '销售方式(2:众包)',
  `integral` int(10) NOT NULL DEFAULT '0',
  `sintegral` int(10) NOT NULL DEFAULT '0',
  `fx_sale` decimal(10,2) unsigned DEFAULT '0.00',
  `version` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uorder` (`enterprise_id`,`orders_id`,`sproduct_id`),
  KEY `orderno` (`orderno`),
  KEY `uremind` (`enterprise_id`,`is_remind`),
  KEY `puid` (`parent_id`) USING BTREE,
  KEY `idx_stat` (`enterprise_id`,`price`) USING BTREE,
  KEY `idx_id_desc` (`catid1`,`id`,`status`),
  KEY `oid_cat_stat` (`catid1`,`orders_id`,`status`),
  KEY `orders_id` (`orders_id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8 COMMENT='订单分销表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_enterprise_orders_refund`
--

DROP TABLE IF EXISTS `t_enterprise_orders_refund`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_enterprise_orders_refund` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `refund_id` int(11) DEFAULT NULL COMMENT '主退款单Id',
  `orders_id` int(11) DEFAULT NULL COMMENT '主订单Id',
  `enterprise_orders_id` int(11) DEFAULT NULL COMMENT '企业订单Id',
  `sproduct_id` int(11) DEFAULT NULL COMMENT '退款产品Id',
  `refund_fee_type` int(3) DEFAULT NULL COMMENT '退款扣款方式（1：按每张扣款，2：按订单扣款）',
  `refund_fee` decimal(10,2) DEFAULT NULL COMMENT '退款手续费（每张、每单）',
  `child_id` int(11) DEFAULT NULL COMMENT '下级企业Id（发起退款的）',
  `parent_id` int(11) DEFAULT NULL COMMENT '上级企业Id',
  `num` int(11) DEFAULT NULL COMMENT '退款产品数量',
  `unit_price` decimal(10,2) DEFAULT NULL COMMENT '申请退款的产品单价',
  `deduct_price` decimal(10,2) DEFAULT NULL COMMENT '退款扣除费用（根据退款类型和退款手续费计算）',
  `refund_price` decimal(10,2) DEFAULT NULL COMMENT '实际退款费用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 COMMENT='退款子表-企业退款表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_enterprise_platform_interface`
--

DROP TABLE IF EXISTS `t_enterprise_platform_interface`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_enterprise_platform_interface` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL DEFAULT '' COMMENT '别名',
  `enterprise_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '企业id',
  `type_id` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '类型： 1 渠道 2 供应商',
  `interface_id` int(6) unsigned NOT NULL DEFAULT '0' COMMENT '基础配置Id',
  `interface_name` varchar(32) NOT NULL DEFAULT '' COMMENT '接口名称',
  `interface_ename` varchar(32) NOT NULL DEFAULT '' COMMENT '类型为供应商：接口URL访问后缀；渠道则为：渠道核销、异步发码标识',
  `acount_no` varchar(32) NOT NULL DEFAULT '' COMMENT '第三方账号或唯一标识',
  `company` varchar(128) NOT NULL DEFAULT '' COMMENT '所属公司',
  `config` varchar(1023) NOT NULL DEFAULT '' COMMENT '接口配置信息',
  `sendmes` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否发码 1 不发码  2 发码',
  `sort` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '排序',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '状态 1 删除 5正常 2 禁用',
  `created_at` datetime NOT NULL,
  `created_by` varchar(128) NOT NULL DEFAULT '',
  `created_id` int(11) NOT NULL DEFAULT '0',
  `updated_at` datetime NOT NULL,
  `updated_id` int(11) NOT NULL DEFAULT '0',
  `updated_by` varchar(128) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='企业接口配置表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_enterprise_product`
--

DROP TABLE IF EXISTS `t_enterprise_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_enterprise_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) DEFAULT NULL COMMENT '商品Id',
  `parent_id` int(11) DEFAULT NULL COMMENT '上级企业Id',
  `child_id` int(11) DEFAULT NULL COMMENT '下级企业Id',
  `is_supplier` varchar(255) DEFAULT NULL COMMENT '是否商品供应商（1：是，0：否）',
  `group_id` int(11) DEFAULT NULL COMMENT '获取商品的分销组Id',
  `group_tracks` varchar(128) DEFAULT NULL COMMENT '组分销轨迹',
  `user_tracks` varchar(128) DEFAULT NULL COMMENT '用户分销轨迹',
  `is_delete` int(1) DEFAULT NULL COMMENT '是否删除（1：是，0：否）',
  `create_id` int(11) DEFAULT NULL COMMENT '创建人Id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_id` int(11) DEFAULT NULL COMMENT '修改人Id',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `enterprise_id` int(11) DEFAULT NULL COMMENT '商品供应商企业Id',
  `is_distribution` int(1) DEFAULT '0' COMMENT '商品分销状态（0：未分销，1：已分销）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8 COMMENT='企业-商品信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_enterprise_relation`
--

DROP TABLE IF EXISTS `t_enterprise_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_enterprise_relation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `child_id` int(11) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `create_enterprise` int(11) DEFAULT NULL COMMENT '创建企业Id',
  `create_type` int(1) DEFAULT NULL COMMENT '创建类型（2：创建供应商，3：创建分销商）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='企业关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_enterprise_storage_log`
--

DROP TABLE IF EXISTS `t_enterprise_storage_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_enterprise_storage_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) DEFAULT NULL COMMENT '上级企业Id',
  `child_id` int(11) DEFAULT NULL COMMENT '下级企业Id',
  `before_money` decimal(10,2) DEFAULT NULL COMMENT '之前预收款',
  `storage_money` decimal(10,2) DEFAULT NULL COMMENT '本次预收款',
  `after_money` decimal(10,2) DEFAULT NULL COMMENT '之后预收款',
  `create_id` int(11) DEFAULT NULL COMMENT '创建人Id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COMMENT='企业-预收款日志';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_enterprise_storage_money`
--

DROP TABLE IF EXISTS `t_enterprise_storage_money`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_enterprise_storage_money` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) DEFAULT NULL COMMENT '上级企业Id',
  `child_id` int(11) DEFAULT NULL COMMENT '下级企业Id',
  `storage_money` decimal(10,2) DEFAULT NULL COMMENT '当前预收款',
  `is_delete` tinyint(1) DEFAULT NULL COMMENT '是否删除（1：是，0：否）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='企业-预收款';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_extract`
--

DROP TABLE IF EXISTS `t_extract`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_extract` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `enterprise_id` int(11) DEFAULT NULL COMMENT '企业Id',
  `bank_card_id` int(11) DEFAULT NULL COMMENT '提现卡号',
  `extract_money` decimal(10,2) DEFAULT NULL COMMENT '申请提现金额',
  `rate` decimal(10,4) DEFAULT NULL COMMENT '费率',
  `rate_money` decimal(10,2) DEFAULT NULL COMMENT '费率扣除金额',
  `actual_money` decimal(10,2) DEFAULT NULL COMMENT '实际提现金额',
  `status` tinyint(2) DEFAULT NULL COMMENT '审核状态（0：待审核，1：通过，2：不通过）',
  `voucher` varchar(100) DEFAULT NULL COMMENT '打款凭证',
  `reason` varchar(30) DEFAULT NULL COMMENT '拒绝原因',
  `create_id` int(11) DEFAULT NULL COMMENT '创建人Id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `audit_id` int(11) DEFAULT NULL COMMENT '审核人Id',
  `audit_time` datetime DEFAULT NULL COMMENT '审核时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='提现';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_order_times`
--

DROP TABLE IF EXISTS `t_order_times`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_order_times` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `orderno` varchar(40) NOT NULL DEFAULT '0' COMMENT '订单号',
  `type` tinyint(4) NOT NULL COMMENT '1表示重新发码的次数 2表示核销的次数',
  `times` tinyint(4) unsigned NOT NULL DEFAULT '1' COMMENT '重试的次数',
  `last_update_time` int(10) unsigned NOT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单重试次数';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_orders`
--

DROP TABLE IF EXISTS `t_orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_orders` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `orderno` varchar(48) NOT NULL COMMENT '订单号',
  `user_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '下单用户Id',
  `user_name` varchar(128) NOT NULL DEFAULT '' COMMENT '下单用户名称',
  `enterprise_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '企业Id',
  `enterprise_name` varchar(128) NOT NULL DEFAULT '' COMMENT '企业名称',
  `pay_api_id` int(11) NOT NULL DEFAULT '0' COMMENT '渠道接口Id',
  `payid` varchar(50) NOT NULL DEFAULT '' COMMENT '渠道订单号',
  `type_id` tinyint(2) unsigned NOT NULL DEFAULT '1' COMMENT '门票类型Id',
  `sproduct_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '产品Id',
  `customer_name` varchar(60) NOT NULL DEFAULT '' COMMENT '取票人',
  `tel` varchar(30) NOT NULL DEFAULT '' COMMENT '取票人手机（用于接收短信）',
  `idcard` varchar(30) NOT NULL DEFAULT '' COMMENT '身份证',
  `consume_time` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '预订游玩日期',
  `unit_price` decimal(10,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '产品单价',
  `price` decimal(10,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '订单总价',
  `apimsg` int(11) NOT NULL DEFAULT '0' COMMENT '供应商接口Id',
  `thirdno` varchar(100) NOT NULL DEFAULT '' COMMENT '供应商订单Id',
  `trade_no` varchar(300) NOT NULL DEFAULT '' COMMENT '供应商码号',
  `paydate` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '下单时间',
  `tuanname` varchar(20) NOT NULL DEFAULT '' COMMENT '来源',
  `num` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '数量',
  `printnum` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '核销数',
  `locknum` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '已退款数',
  `clocknum` smallint(6) unsigned NOT NULL DEFAULT '0' COMMENT '冻结数（退款审核冻结）',
  `notes` varchar(1000) NOT NULL DEFAULT '' COMMENT '客户备注',
  `created_at` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建时间',
  `status` int(3) unsigned DEFAULT NULL COMMENT '订单状态',
  `sendnum` tinyint(4) unsigned NOT NULL DEFAULT '0',
  `route` varchar(100) NOT NULL DEFAULT '',
  `team_id` varchar(100) NOT NULL DEFAULT '',
  `guide` varchar(60) NOT NULL DEFAULT '',
  `guide_card` varchar(100) NOT NULL DEFAULT '',
  `province` int(11) NOT NULL DEFAULT '0',
  `apptoken` varchar(200) DEFAULT '',
  `is_chainmode` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `phone_province` varchar(20) NOT NULL DEFAULT '',
  `phone_city` varchar(20) NOT NULL DEFAULT '',
  `is_zbtimenum` tinyint(1) DEFAULT '0',
  `sale_mode` tinyint(1) DEFAULT NULL COMMENT '销售方式(2:众包)',
  `is_tag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '订单是否已经标注 0 否  1  是 ',
  `is_email` varchar(32) NOT NULL DEFAULT '' COMMENT '电子邮箱',
  `version` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '版本号',
  `sendtype` int(11) DEFAULT '0',
  `qrcode` varchar(128) DEFAULT NULL COMMENT '二维码地址',
  PRIMARY KEY (`id`),
  KEY `user` (`user_id`),
  KEY `orderno` (`orderno`) USING BTREE,
  KEY `idx_thirdno` (`thirdno`) USING BTREE,
  KEY `payid` (`payid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 COMMENT='订单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_orders_consume`
--

DROP TABLE IF EXISTS `t_orders_consume`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_orders_consume` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `orders_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '订单Id',
  `code_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '码Id',
  `sproduct_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '产品Id',
  `product_sortname` varchar(300) NOT NULL DEFAULT '' COMMENT '产品名称',
  `enterprise_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '供应商企业Id',
  `shop_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '商户Id',
  `shop_name` varchar(30) NOT NULL DEFAULT '' COMMENT '商户名称',
  `unit_price` decimal(10,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '核销单价',
  `price` decimal(10,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '核销总价',
  `printnum` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '核销数',
  `status` tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '状态默认4',
  `tuanname` varchar(20) NOT NULL DEFAULT '' COMMENT '核销源',
  `card_pwd` varchar(20) NOT NULL DEFAULT '' COMMENT '码',
  `isprint` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否打印',
  `printtim` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '核销时间',
  `operator_uid` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '操作用户Id',
  `operator` varchar(30) NOT NULL DEFAULT '' COMMENT '操作用户名',
  `serial_num` varchar(32) NOT NULL DEFAULT '0' COMMENT '流水号',
  `machine_id` varchar(32) NOT NULL DEFAULT '0' COMMENT '机器号',
  PRIMARY KEY (`id`),
  KEY `orders_id` (`orders_id`),
  KEY `card_pwd` (`card_pwd`) USING BTREE,
  KEY `idx_code` (`code_id`) USING BTREE,
  KEY `idx_printtime` (`printtim`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='码核销记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_orders_refund`
--

DROP TABLE IF EXISTS `t_orders_refund`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_orders_refund` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `enterprise_id` int(11) DEFAULT NULL COMMENT '发起退款的企业Id',
  `orders_id` int(11) DEFAULT NULL COMMENT '主订单Id',
  `sproduct_id` int(11) DEFAULT NULL COMMENT '退款产品Id',
  `num` int(11) DEFAULT NULL COMMENT '退款产品数量',
  `notes` varchar(100) DEFAULT NULL COMMENT '退款原因（备注）',
  `status` int(1) DEFAULT NULL COMMENT '状态（0：待审核，1：审核通过，2：审核不通过）',
  `create_id` int(11) DEFAULT NULL COMMENT '发起退款人（操作人）',
  `create_time` int(11) DEFAULT NULL,
  `audit_id` int(11) DEFAULT NULL COMMENT '退款审核人',
  `audit_time` int(11) DEFAULT NULL,
  `serial_number` varchar(50) DEFAULT NULL COMMENT '流水号（避免第三方重复退款使用）',
  `gys_serial_no` varchar(64) DEFAULT '' COMMENT ' 供应商退款流水号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COMMENT='退款主表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_platform_interface`
--

DROP TABLE IF EXISTS `t_platform_interface`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_platform_interface` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL DEFAULT '' COMMENT '接口名称',
  `ename` varchar(32) NOT NULL DEFAULT '' COMMENT '类型为供应商：接口URL访问后缀；渠道则为：渠道英文名',
  `type_id` tinyint(1) NOT NULL DEFAULT '1' COMMENT '类型： 1 渠道 2 供应商',
  `interface_config` varchar(1023) DEFAULT '' COMMENT '接口所需的配置信息',
  `data` varchar(1023) DEFAULT '' COMMENT '固定参数',
  `sort` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
  `status` tinyint(1) NOT NULL DEFAULT '5' COMMENT '状态 1 删除 5正常 2 禁用',
  `created_at` datetime NOT NULL,
  `created_id` int(11) NOT NULL DEFAULT '0',
  `created_by` varchar(128) NOT NULL DEFAULT '',
  `updated_at` datetime NOT NULL,
  `updated_id` int(11) NOT NULL DEFAULT '0',
  `updated_by` varchar(128) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='基础配置需求表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_product_category`
--

DROP TABLE IF EXISTS `t_product_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_product_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) DEFAULT NULL COMMENT '商品类别',
  `is_delete` tinyint(1) DEFAULT NULL COMMENT '是否删除（1：是，0：否）',
  `sort_no` tinyint(3) DEFAULT NULL COMMENT '排序号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='商品类别信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_recharge`
--

DROP TABLE IF EXISTS `t_recharge`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_recharge` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `enterprise_id` int(11) DEFAULT NULL COMMENT '企业Id',
  `terminal` varchar(10) DEFAULT NULL COMMENT '充值终端（手机、PC）',
  `channel` varchar(10) DEFAULT NULL COMMENT '充值渠道（支付宝、微信、银行卡）',
  `recharge_money` decimal(10,2) DEFAULT NULL COMMENT '充值金额',
  `rate` decimal(10,4) DEFAULT NULL COMMENT '费率',
  `rate_money` decimal(10,2) DEFAULT NULL COMMENT '费率扣除金额',
  `actual_money` decimal(10,2) DEFAULT NULL COMMENT '实际充值金额',
  `create_id` int(11) DEFAULT NULL COMMENT '创建人Id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='充值';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_reprint`
--

DROP TABLE IF EXISTS `t_reprint`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_reprint` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `consume_log_id` int(11) NOT NULL DEFAULT '0',
  `orders_id` int(11) NOT NULL DEFAULT '0',
  `shop_id` int(11) NOT NULL DEFAULT '0',
  `shop_name` varchar(60) NOT NULL DEFAULT '',
  `sproduct_id` int(11) NOT NULL DEFAULT '0',
  `product_sortname` varchar(60) NOT NULL DEFAULT '',
  `sharepos_uid` int(11) NOT NULL DEFAULT '0',
  `cardPwd` varchar(60) NOT NULL DEFAULT '',
  `machineId` smallint(8) NOT NULL DEFAULT '0',
  `printtim` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='重新打印记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_sale_group`
--

DROP TABLE IF EXISTS `t_sale_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_sale_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `enterprise_id` int(11) DEFAULT NULL COMMENT '创建企业Id',
  `name` varchar(20) DEFAULT NULL COMMENT '分销组名称',
  `user_number` int(6) DEFAULT NULL COMMENT '分销商数',
  `product_number` int(8) DEFAULT NULL COMMENT '商品数',
  `is_delete` int(1) DEFAULT NULL COMMENT '是否删除（1：是，0：否）',
  `create_id` int(11) DEFAULT NULL COMMENT '创建人Id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_id` int(11) DEFAULT NULL COMMENT '修改人Id',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='分销组';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_sale_group_enterprise`
--

DROP TABLE IF EXISTS `t_sale_group_enterprise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_sale_group_enterprise` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_id` int(11) DEFAULT NULL COMMENT '分销组Id',
  `create_enterprise_id` int(11) DEFAULT NULL COMMENT '分销组创建企业Id',
  `child_enterprise_id` int(11) DEFAULT NULL COMMENT '分销组分销商企业Id',
  `is_delete` int(11) DEFAULT NULL COMMENT '是否删除（1：是，0：否）',
  `create_id` int(11) DEFAULT NULL COMMENT '创建人Id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_id` int(11) DEFAULT NULL COMMENT '修改人Id',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='分销组-分销商';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_sale_group_log`
--

DROP TABLE IF EXISTS `t_sale_group_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_sale_group_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `operate_flag` int(1) DEFAULT NULL COMMENT '操作标识',
  `user_ids` varchar(1500) DEFAULT NULL COMMENT '操作影响的用户Id',
  `product_ids` varchar(1500) DEFAULT NULL COMMENT '操作影响的商品Id',
  `create_id` int(11) DEFAULT NULL COMMENT '创建人Id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8 COMMENT='分销组-操作日志';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_sale_group_product`
--

DROP TABLE IF EXISTS `t_sale_group_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_sale_group_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_id` int(11) DEFAULT NULL COMMENT '分销组Id',
  `group_tracks` varchar(128) DEFAULT NULL COMMENT '组分销轨迹',
  `create_enterprise_id` int(11) DEFAULT NULL COMMENT '分销组创建企业Id',
  `product_id` int(11) DEFAULT NULL COMMENT '商品Id',
  `is_delete` int(1) DEFAULT NULL COMMENT '是否删除（1：是，0：否）',
  `create_id` int(11) DEFAULT NULL COMMENT '创建人Id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_id` int(11) DEFAULT NULL COMMENT '修改人Id',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_distribution` varchar(255) DEFAULT '0' COMMENT '商品分销状态（0：未分销，1：已分销）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COMMENT='分销组-商品';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_supplier_product`
--

DROP TABLE IF EXISTS `t_supplier_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_supplier_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `no` varchar(35) DEFAULT NULL COMMENT '产品编号',
  `enterprise_id` int(11) DEFAULT NULL COMMENT '供应商企业Id',
  `name` varchar(100) DEFAULT NULL COMMENT '商品名称',
  `market_price` decimal(10,2) DEFAULT NULL COMMENT '市场价',
  `limit_price` decimal(10,2) DEFAULT NULL COMMENT '限售价（网络最小销售价格）',
  `scenic_id` int(11) DEFAULT NULL COMMENT '景区Id',
  `category_id` int(11) DEFAULT NULL COMMENT '商品类别',
  `province_id` int(11) DEFAULT NULL COMMENT '省',
  `city_id` int(11) DEFAULT NULL COMMENT '市',
  `introduce` varchar(1200) DEFAULT NULL COMMENT '商品介绍',
  `cost_inside` varchar(300) DEFAULT NULL COMMENT '费用包含',
  `cost_outside` varchar(300) DEFAULT NULL COMMENT '费用不包含',
  `pic` varchar(100) DEFAULT NULL COMMENT '产品图片地址',
  `buy_option` int(1) DEFAULT NULL COMMENT '购买选择',
  `buy_time_hour` int(2) DEFAULT NULL COMMENT '提前购买时间-小时',
  `buy_time_minute` int(2) DEFAULT NULL COMMENT '提前购买时间-分钟',
  `buy_use_day` int(4) DEFAULT NULL COMMENT '游玩前-天',
  `buy_use_hour` int(2) DEFAULT NULL COMMENT '游玩前-小时',
  `buy_use_minute` int(2) DEFAULT NULL COMMENT '游玩前-分钟',
  `buy_min_number` int(4) DEFAULT NULL COMMENT '每单最少购买张数',
  `buy_max_number` int(4) DEFAULT NULL COMMENT '每单最多购买张数',
  `play_mode` int(1) DEFAULT NULL COMMENT '游玩方式',
  `play_date` date DEFAULT NULL COMMENT '游玩日期',
  `valid_start_date` date DEFAULT NULL COMMENT '产品有效期-开始日期',
  `valid_end_date` date DEFAULT NULL COMMENT '产品有效期-截止日期',
  `store_mode` int(1) DEFAULT NULL COMMENT '库存模式',
  `refund_mode` int(1) DEFAULT NULL COMMENT '退款设置',
  `audit_mode` int(1) DEFAULT NULL COMMENT '审核设置',
  `service_mode` int(1) DEFAULT NULL COMMENT '手续费扣费模式',
  `service_product_cost` decimal(10,2) DEFAULT NULL COMMENT '每张票扣除费用',
  `service_order_cost` decimal(10,2) DEFAULT NULL COMMENT '每个订单扣除费用',
  `service_tel` varchar(30) DEFAULT NULL COMMENT '客服电话',
  `message_template` varchar(200) DEFAULT NULL COMMENT '短信模板',
  `third_platform_id` int(11) DEFAULT NULL COMMENT '第三方平台Id',
  `third_platform_no` varchar(35) DEFAULT NULL COMMENT '第三方平台产品编号',
  `status` int(1) DEFAULT NULL COMMENT '商品状态（1：启用，0：停用）',
  `is_delete` int(1) DEFAULT NULL COMMENT '是否删除（1：是，0：否）',
  `create_id` int(11) DEFAULT NULL COMMENT '创建人Id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_id` int(11) DEFAULT NULL COMMENT '修改人Id',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `store_num` int(11) DEFAULT '0' COMMENT '库存量',
  `is_realname` int(1) DEFAULT '0' COMMENT '是否实名制（1：是，0：否）',
  `refund_time` int(11) DEFAULT '1',
  `refund_after_day` int(11) DEFAULT '0',
  `buy_use_after_hour` int(11) DEFAULT '0',
  `product_info` text,
  `refund_after_minute` int(11) DEFAULT '0',
  `refund_after_hour` int(11) DEFAULT '0',
  `is_must_card` int(1) DEFAULT '0' COMMENT '是否必须提供身份证号(1：是，0：否）',
  `refund_method` int(1) DEFAULT '2' COMMENT '是否整退（1：整个订单退款，2：部分退款）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='供应商商品信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping events for database 'yt_sale'
--

--
-- Dumping routines for database 'yt_sale'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-06-13 13:56:46
