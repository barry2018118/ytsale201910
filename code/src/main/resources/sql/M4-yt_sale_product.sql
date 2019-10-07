USE `yt_sale`;

#
# Source for table t_product_category   (商品类别信息表)
#
DROP TABLE IF EXISTS `t_product_category`;
CREATE TABLE `t_product_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) DEFAULT NULL COMMENT '商品类别',
  `is_delete` tinyint(1) DEFAULT NULL COMMENT '是否删除（1：是，0：否）',
  `sort_no` tinyint(3) DEFAULT NULL COMMENT '排序号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='商品类别信息表';

INSERT INTO `t_product_category` VALUES (1,'常规代理',0,1);
INSERT INTO `t_product_category` VALUES (2,'时段包票',0,2);
INSERT INTO `t_product_category` VALUES (3,'第三方票务',0,3);


#
# Source for table t_supplier_product   (供应商商品信息)
#
DROP TABLE IF EXISTS `t_supplier_product`;
CREATE TABLE `t_supplier_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `no` varchar(35) DEFAULT NULL COMMENT '产品编号',
  `enterprise_id` int(11) DEFAULT NULL COMMENT '供应商企业Id',
  `name` varchar(100) DEFAULT NULL COMMENT '商品名称',
  `market_price` decimal(10,2) DEFAULT NULL COMMENT '市场价',
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='供应商商品信息';

ALTER TABLE t_supplier_product ADD COLUMN store_num int(11) DEFAULT 0 COMMENT '库存量' ;
ALTER TABLE t_supplier_product ADD COLUMN is_realname int(1) DEFAULT 0 COMMENT '是否实名制（1：是，0：否）' ;


#
# Source for table t_sale_group     (分销组)
#
DROP TABLE IF EXISTS `t_sale_group`;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='分销组';


#
# Source for table t_sale_group_enterprise    (分销组-分销商)
#
DROP TABLE IF EXISTS `t_sale_group_enterprise`;
CREATE TABLE `t_sale_group_user` (
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='分销组-分销商';


#
# Source for table t_sale_group_product     (分销组-商品)
#
DROP TABLE IF EXISTS `t_sale_group_product`;
CREATE TABLE `t_sale_group_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_id` int(11) DEFAULT NULL COMMENT '分销组Id',
  `create_enterprise_id` int(11) DEFAULT NULL COMMENT '分销组创建企业Id',
  `product_id` int(11) DEFAULT NULL COMMENT '商品Id',
  `is_delete` int(1) DEFAULT NULL COMMENT '是否删除（1：是，0：否）',
  `create_id` int(11) DEFAULT NULL COMMENT '创建人Id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_id` int(11) DEFAULT NULL COMMENT '修改人Id',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='分销组-商品';


#
# Source for table t_enterprise_product     (企业-商品信息)
#
DROP TABLE IF EXISTS `t_enterprise_product`;
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='企业-商品信息';


#
# Source for table t_sale_group_log     (分销组-操作日志)
#
DROP TABLE IF EXISTS `t_sale_group_log`;
CREATE TABLE `t_sale_group_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `operate_flag` int(1) DEFAULT NULL COMMENT '操作标识',
  `user_ids` varchar(1500) DEFAULT NULL COMMENT '操作影响的用户Id',
  `product_ids` varchar(1500) DEFAULT NULL COMMENT '操作影响的商品Id',
  `create_id` int(11) DEFAULT NULL COMMENT '创建人Id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='分销组-操作日志';


# 供应商商品表增加字段：限售价（网络最小销售价格）
ALTER TABLE t_supplier_product ADD COLUMN limit_price decimal(10,2) DEFAULT NULL COMMENT '限售价（网络最小销售价格）' ;