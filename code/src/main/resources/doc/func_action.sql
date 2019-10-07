/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.6.17 : Database - yt_sale
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`yt_sale` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `yt_sale`;

/*Table structure for table `func_action` */

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
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8 COMMENT='功能表_功能（动作）';

/*Data for the table `func_action` */

insert  into `func_action`(`id`,`module_id`,`menu_id`,`name`,`icon`,`url`,`description`,`is_delete`,`sort_no`,`is_distribution`,`create_id`,`create_time`,`update_id`,`update_time`) values (1,1,1,'模块设置',NULL,'/manage/function/module/main','/manage/function/module/main',0,1,1,1,'2018-02-14 09:03:45',1,'2018-02-14 09:03:45'),(2,1,1,'功能概览',NULL,'/manage/function/view',NULL,0,2,1,1,'2018-02-14 09:03:45',1,'2018-02-14 09:03:45'),(3,1,1,'功能设置',NULL,'/manage/user/authorize',NULL,0,3,1,1,'2018-02-14 09:03:45',1,'2018-02-14 09:03:45'),(4,1,2,'权限设置',NULL,'/manage/role/main',NULL,0,1,1,1,'2018-02-14 09:03:45',1,'2018-02-14 09:03:45'),(5,2,3,'网页信息',NULL,'','',0,1,1,1,'2018-02-14 12:02:05',1,'2018-02-14 12:02:05'),(6,2,3,'景区信息',NULL,'/info/scenic/main','',0,1,1,1,'2018-02-14 12:02:16',1,'2018-02-14 12:02:16'),(7,2,3,'商品类别',NULL,'/product/category/main','',0,1,1,1,'2018-02-14 12:02:30',1,'2018-02-14 12:02:30'),(8,4,6,'平台运营商',NULL,'/platform/enterprise/operation','',0,1,1,1,'2018-02-24 13:47:25',1,'2018-02-24 13:52:24'),(9,4,6,'平台商户',NULL,'/platform/enterprise/shop','',0,1,1,1,'2018-02-24 13:48:56',1,'2018-02-24 13:48:56'),(10,4,7,'供应商信息',NULL,'/my/enterprise/supplier','',0,1,1,1,'2018-02-24 13:49:27',1,'2018-02-24 17:43:42'),(11,4,7,'分销商信息',NULL,'/my/enterprise/distributor','',0,1,1,1,'2018-02-24 13:50:05',1,'2018-02-24 17:44:16'),(13,2,3,'公告管理',NULL,'','',0,1,1,1,'2018-03-12 17:39:36',1,'2018-03-12 17:39:36'),(14,6,10,'企业信息',NULL,'/my/enterprise/info/toEdit','',0,1,1,1,'2018-03-12 17:42:48',1,'2018-03-12 17:42:48'),(15,6,10,'权限管理',NULL,'/manage/role/main','',0,1,1,1,'2018-03-12 17:43:33',1,'2018-03-12 17:43:33'),(16,6,10,'员工账号',NULL,'/my/user/main','',0,1,1,1,'2018-03-12 17:44:03',1,'2018-03-12 17:44:03'),(17,6,11,'企业信息',NULL,'/my/enterprise/info/toEdit','',0,1,1,1,'2018-03-12 17:44:45',1,'2018-03-12 17:44:45'),(18,6,11,'权限管理',NULL,'/manage/role/main','',0,1,1,1,'2018-03-12 17:45:01',1,'2018-03-12 17:45:01'),(19,6,11,'员工账号',NULL,'/my/user/main','/my/user/main',0,1,1,1,'2018-03-12 17:45:10',1,'2018-03-15 13:15:33'),(20,5,8,'资金概览',NULL,'/capital/platform/money/capital','',0,1,1,1,'2018-03-14 17:20:31',1,'2018-03-14 17:20:31'),(21,5,8,'充值资金',NULL,'/capital/platform/money/recharge/main','',0,1,1,1,'2018-03-14 17:23:54',1,'2018-03-14 17:23:54'),(22,5,8,'交易资金',NULL,' ','',0,1,1,1,'2018-03-14 17:24:11',1,'2018-03-14 17:24:11'),(23,5,8,'提现资金',NULL,'/capital/platform/money/extract/main','',0,1,1,1,'2018-03-14 17:24:35',1,'2018-03-14 17:24:35'),(24,5,9,'资金账户',NULL,'/capital/enterprise/money/capital','',0,1,1,1,'2018-03-14 17:33:27',1,'2018-03-14 17:33:27'),(25,5,9,'充值',NULL,'/capital/enterprise/info/recharge/main','',0,1,1,1,'2018-03-14 17:34:58',1,'2018-03-14 17:34:58'),(26,5,9,'提现',NULL,'/capital/enterprise/info/extract/main','',0,1,1,1,'2018-03-14 17:35:06',1,'2018-03-14 17:35:06'),(27,5,9,'交易资金',NULL,' ','',0,1,1,1,'2018-03-14 17:35:40',1,'2018-03-14 17:35:40'),(28,5,9,'资金变动',NULL,'/capital/enterprise/accountLog/main','',0,1,1,1,'2018-03-14 17:45:44',1,'2018-03-14 17:45:44'),(29,5,9,'银行卡管理',NULL,'/capital/enterprise/bankCard/main','',0,1,1,1,'2018-03-17 12:15:49',1,'2018-03-17 12:15:49'),(30,5,9,'预收款',NULL,'/capital/enterprise/storageMoney/main','',0,1,1,1,'2018-03-21 12:09:25',1,'2018-03-21 12:09:25'),(32,7,13,'商品信息',NULL,'/product/supplier/main','',0,1,1,1,'2018-04-02 10:38:16',1,'2018-04-02 10:38:16'),(33,7,13,'商品组',NULL,'/product/salegroup/main','',0,1,1,1,'2018-04-02 10:38:45',1,'2018-04-02 10:38:45'),(34,7,12,'商品信息',NULL,'/product/supplier/main','',0,1,1,1,'2018-04-02 10:43:36',1,'2018-04-02 10:43:36'),(35,7,12,'商品组',NULL,'/product/salegroup/main','',0,1,1,1,'2018-04-02 10:43:45',1,'2018-04-02 10:43:45');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
