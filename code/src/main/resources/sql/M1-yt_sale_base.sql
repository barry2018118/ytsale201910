USE `yt_sale`;

#
# Source for table info_city    (1_省市信息)
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
# Source for table info_captial_channel (2_资金渠道)
#
DROP TABLE IF EXISTS `info_captial_channel`;
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

INSERT INTO `info_captial_channel` VALUES (1,'支付宝',0.006,NULL,NULL,NULL,0,1);
INSERT INTO `info_captial_channel` VALUES (2,'微信',0.006,NULL,NULL,NULL,0,2);


#
# Source for table info_shop (3_景区信息)
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
  `shop_no` varchar(63) NOT NULL DEFAULT '' COMMENT '景区编号',
  `supplier` varchar(63) NOT NULL DEFAULT '' COMMENT '票纸上显示供应商',
  `printnum` tinyint(4) NOT NULL DEFAULT '1' COMMENT '每次出纸联数',
  `isprint` tinyint(1) NOT NULL DEFAULT '2' COMMENT '打印附加 1平台商 姓名 手机 2 姓名 手机 3 无',
  PRIMARY KEY (`id`),
  UNIQUE KEY(`shop_no`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='景区信息';


#
# Source for table info_notice (4_公告)
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
  `enterprise_id` int(11) DEFAULT NULL COMMENT '企业Id',
  `create_id` int(11) DEFAULT NULL COMMENT '创建人Id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_id` int(11) DEFAULT NULL COMMENT '修改人Id',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公告';


#
# Source for table info_notice_enterprise   (5_公告-企业关系表)
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
# Source for table info_action_type (6_业务动作类型)
#

DROP TABLE IF EXISTS `info_action_type`;
CREATE TABLE `info_action_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(12) DEFAULT NULL COMMENT '业务动作类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='业务动作类型';

INSERT INTO `info_action_type` VALUES (1,'充值');
INSERT INTO `info_action_type` VALUES (2,'提现');
INSERT INTO `info_action_type` VALUES (3,'下单');
INSERT INTO `info_action_type` VALUES (4,'退款');
INSERT INTO `info_action_type` VALUES (5,'消费');

