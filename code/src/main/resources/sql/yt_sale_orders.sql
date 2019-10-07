#
# Source for table t_code_provide   (码库表)
#
DROP TABLE IF EXISTS `t_code_provide`;
CREATE TABLE `t_code_provide` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `card_pwd` varchar(32) NOT NULL DEFAULT '' COMMENT '码',
  `status` tinyint(1) NOT NULL DEFAULT '5' COMMENT '状态（5可用、1已使用）',
  PRIMARY KEY (`id`),
  UNIQUE KEY `card_pwd` (`card_pwd`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='码库表';


#
# Source for table t_code   (码信息表)
#
DROP TABLE IF EXISTS `t_code`;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='码信息表';


#
# Source for table t_orders    (订单主表)
#
DROP TABLE IF EXISTS `t_orders`;
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
  PRIMARY KEY (`id`),
  KEY `user` (`user_id`),
  KEY `orderno` (`orderno`) USING BTREE,
  KEY `idx_thirdno` (`thirdno`) USING BTREE,
  KEY `payid` (`payid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表';


#
# Source for table t_enterprise_orders    (订单表子-企业订单表)
#
DROP TABLE IF EXISTS `t_enterprise_orders`;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表子-企业订单表';


#
# Source for table t_orders_refund    (退款主表)
#
DROP TABLE IF EXISTS `t_orders_refund`;
CREATE TABLE `t_orders_refund` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `enterprise_id` int(11) DEFAULT NULL COMMENT '发起退款的企业Id',
  `orders_id` int(11) DEFAULT NULL COMMENT '主订单Id',
  `sproduct_id` int(11) DEFAULT NULL COMMENT '退款产品Id',
  `num` int(11) DEFAULT NULL COMMENT '退款产品数量',
  `notes` varchar(100) DEFAULT NULL COMMENT '退款原因（备注）',
  `status` int(1) DEFAULT NULL COMMENT '状态（0：待审核，1：审核通过，2：审核不通过）',
  `create_id` int(11) DEFAULT NULL COMMENT '发起退款人（操作人）',
  `create_time` datetime DEFAULT NULL COMMENT '发起退款时间',
  `audit_id` int(11) DEFAULT NULL COMMENT '退款审核人',
  `audit_time` datetime DEFAULT NULL COMMENT '退款审核时间',
  `serial_number` varchar(50) DEFAULT NULL COMMENT '流水号（避免第三方重复退款使用）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='退款主表';


#
# Source for table t_enterprise_orders_refund    (退款子表-企业退款表)
#
DROP TABLE IF EXISTS `t_enterprise_orders_refund`;
CREATE TABLE `t_enterprise_orders_refund` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `refund_id` int(11) DEFAULT NULL COMMENT '主退款单Id',
  `orders_id` int(11) DEFAULT NULL COMMENT '主订单Id',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='退款子表-企业退款表';


#
# Source for table t_orders_consume    (消费记录表)
#
DROP TABLE IF EXISTS `t_orders_consume`;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='码核销记录表';


#
# Source for table t_reprint    (消费票据打印表)
#
DROP TABLE IF EXISTS `t_reprint`;
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

