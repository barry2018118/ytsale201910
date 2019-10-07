USE `yt_sale`;

#
# Source for table t_enterprise_storage_money   (1_企业-预收款)
#
DROP TABLE IF EXISTS `t_enterprise_storage_money`;
CREATE TABLE `t_enterprise_storage_money` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) DEFAULT NULL COMMENT '上级企业Id',
  `child_id` int(11) DEFAULT NULL COMMENT '下级企业Id',
  `storage_money` decimal(10,2) DEFAULT NULL COMMENT '当前预收款',
  `is_delete` tinyint(1) DEFAULT NULL COMMENT '是否删除（1：是，0：否）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='企业-预收款';


#
# Source for table t_enterprise_storage_log   (2_企业-预收款日志)
#
DROP TABLE IF EXISTS `t_enterprise_storage_log`;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='企业-预收款日志';


#
# Source for table t_enterprise_captial   (3_企业-平台资金)
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='企业-平台资金';


#
# Source for table t_enterprise_captial   (4_企业-银行卡)
#
DROP TABLE IF EXISTS `t_enterprise_bank_card`;
CREATE TABLE `t_enterprise_bank_card` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `enterprise_id` int(11) DEFAULT NULL COMMENT '企业Id',
  `card_no` varchar(20) DEFAULT NULL COMMENT '卡号',
  `bank` varchar(20) DEFAULT NULL COMMENT '所属银行',
  `card_master` varchar(20) DEFAULT NULL COMMENT '持卡人',
  `card_master_no` varchar(18) DEFAULT NULL COMMENT '持卡人身份证号',
  `card_master_phone` varchar(11) DEFAULT NULL COMMENT '持卡人手机号',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `is_delete` tinyint(1) DEFAULT NULL COMMENT '是否删除（1：是，0：否）',
  `sort_no` tinyint(2) DEFAULT NULL COMMENT '排序号',
  `create_id` int(11) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='企业-银行卡';


#
# Source for table t_recharge   (5_充值)
#
DROP TABLE IF EXISTS `t_recharge`;
CREATE TABLE `t_recharge` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `enterprise_id` int(11) DEFAULT NULL COMMENT '企业Id',
  `recharge_money` decimal(10,2) DEFAULT NULL COMMENT '充值金额',
  `rate` decimal(10,4) DEFAULT NULL COMMENT '费率',
  `rate_money` decimal(10,2) DEFAULT NULL COMMENT '费率扣除金额',
  `actual_money` decimal(10,2) DEFAULT NULL COMMENT '实际充值金额',
  `create_id` int(11) DEFAULT NULL COMMENT '创建人Id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='充值';


#
# Source for table t_extract    (6_提现)
#

DROP TABLE IF EXISTS `t_extract`;
CREATE TABLE `t_extract` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `enterprise_id` int(11) DEFAULT NULL COMMENT '企业Id',
  `extract_money` decimal(10,2) DEFAULT NULL COMMENT '申请提现金额',
  `rate` decimal(10,4) DEFAULT NULL COMMENT '费率',
  `rate_money` decimal(10,2) DEFAULT NULL COMMENT '费率扣除金额',
  `actual_money` decimal(10,2) DEFAULT NULL COMMENT '实际提现金额',
  `status` tinyint(2) DEFAULT NULL COMMENT '审核状态（0：待审核，1：通过，2：不通过）',
  `create_id` int(11) DEFAULT NULL COMMENT '创建人Id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `audit_id` int(11) DEFAULT NULL COMMENT '审核人Id',
  `audit_time` datetime DEFAULT NULL COMMENT '审核时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='提现';


#
# Source for table t_enterprise_account_log (7_企业资金变动)
#
CREATE TABLE `t_enterprise_account_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `child_id` int(11) DEFAULT NULL COMMENT '下级企业Id',
  `parent_id` int(11) DEFAULT NULL COMMENT '上级企业Id',
  `action_type` int(11) DEFAULT NULL COMMENT '业务行为类型',
  `action_id` int(11) DEFAULT NULL COMMENT '具体的行为id',
  `captial_type` tinyint(2) DEFAULT NULL COMMENT '资金变动方式（0： 预收款、1：平台资金）',
  `old_price` decimal(10,2) DEFAULT NULL COMMENT '变动前金额',
  `price` decimal(10,2) DEFAULT NULL COMMENT '本次变动金额',
  `current_price` decimal(10,2) DEFAULT NULL COMMENT '变动后金额',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `terminal` varchar(10) DEFAULT NULL COMMENT '使用终端',
  `phone_system` varchar(255) DEFAULT NULL COMMENT '移动电话系统',
  `action_desc` varchar(100) DEFAULT NULL COMMENT '具体的行为描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='企业资金变动';

