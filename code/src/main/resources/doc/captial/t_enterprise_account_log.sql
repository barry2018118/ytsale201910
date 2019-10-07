USE `yt_sale`;

DROP TABLE IF EXISTS `t_enterprise_account_log`;
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