CREATE TABLE `t_api_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户 ID',
  `user_name` varchar(128) NOT NULL DEFAULT '' COMMENT '用户名',
  `type_id` int(11) NOT NULL DEFAULT '1' COMMENT '接口类型 1销售  2 采购',
  `interface_id` int(11) NOT NULL DEFAULT '0' COMMENT '接口 ID',
  `interface_name` varchar(32) NOT NULL DEFAULT '' COMMENT '接口名',
  `level` tinyint(1) NOT NULL DEFAULT '1' COMMENT '错误级别',
  `return_code` varchar(11) NOT NULL DEFAULT '' COMMENT '返回码',
  `api_act` varchar(50) NOT NULL DEFAULT '' COMMENT '方法名',
  `api_msg` varchar(5000) NOT NULL DEFAULT '' COMMENT '错误信息',
  `note` varchar(1100) NOT NULL DEFAULT '' COMMENT '备注',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态 1-已处理  2-处理中  5-待处理',
  `createdAt` int(11) NOT NULL DEFAULT '0' COMMENT '添加时间',
  `createdBy` varchar(128) NOT NULL DEFAULT '' COMMENT '添加人',
  `updatedAt` int(11) NOT NULL DEFAULT '0' COMMENT '最后修改时间',
  `updatedBy` varchar(128) NOT NULL DEFAULT '' COMMENT '最后修改人',
  `orderno` varchar(48) DEFAULT '0' COMMENT '订单号',
  `payid` varchar(50) DEFAULT '0' COMMENT '分销商订单号',
  `tel` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT '接口日志';


CREATE TABLE `t_order_times` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `orderno` varchar(40) NOT NULL DEFAULT '0' COMMENT '订单号',
  `type` tinyint(4) NOT NULL COMMENT '1表示重新发码的次数 2表示核销的次数',
  `times` tinyint(4) unsigned NOT NULL DEFAULT '1' COMMENT '重试的次数',
  `last_update_time` int(10) unsigned NOT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '订单重试次数';


ALTER TABLE `t_orders_refund` 
ADD COLUMN `gys_serial_no` VARCHAR(64) NULL DEFAULT '' COMMENT ' 供应商退款流水号' AFTER `serial_number`;
