
DROP TABLE IF EXISTS `t_calendar_cost_section`;
CREATE TABLE `t_calendar_cost_section` (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sproduct_id` int(11) NOT NULL COMMENT '产品id',
  `start_date` date NOT NULL COMMENT '开始时间',
  `end_date` date NOT NULL COMMENT '结束时间（包含在区间内）',
  `cost_price` decimal(8,2) COMMENT '成本价',
  `status` tinyint(1) NOT NULL COMMENT '状态（5正常,1删除 )',
  `created_at` int(11)  NOT NULL,
  `created_id` int(11)  NOT NULL,
  `created_by` varchar(128) NOT NULL,
  `updated_at` int(11)  NOT NULL,
  `updated_id` int(11)  NOT NULL,
  `updated_by` varchar(128) NOT NULL ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='时间段内产品组产品成本价';


DROP TABLE IF EXISTS `t_calendar_cost_week`;
CREATE TABLE `t_calendar_cost_week` (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sproduct_id` int(11) NOT NULL COMMENT '用户产品id',
  `start_date` date NOT NULL COMMENT '开始时间',
  `end_date` date NOT NULL COMMENT '结束时间（包含在区间内）',
  `week` varchar(16) NOT NULL COMMENT '周几,如1，2，3表示周一，周二，周三',
  `cost_price` decimal(8,2) DEFAULT '0' COMMENT '成本价',
  `status` tinyint(1) NOT NULL COMMENT '状态（5正常,1删除 )',
  `created_at` int(11)  NOT NULL,
  `created_id` int(11)  NOT NULL,
  `created_by` varchar(128) NOT NULL,
  `updated_at` int(11)  NOT NULL,
  `updated_id` int(11)  NOT NULL,
  `updated_by` varchar(128) NOT NULL ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='周期产品组产品成本价';


DROP TABLE IF EXISTS `t_calendar_cost_special`;
CREATE TABLE `t_calendar_cost_special` (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sproduct_id` int(11) NOT NULL COMMENT '产品id',
  `date` date NOT NULL COMMENT '日期',
  `cost_price` decimal(8,2) DEFAULT '0' COMMENT '成本价',
  `status` tinyint(1) NOT NULL COMMENT '状态（5正常,1删除 )',
  `created_at` int(11)  NOT NULL,
  `created_id` int(11)  NOT NULL,
  `created_by` varchar(128) NOT NULL,
  `updated_at` int(11)  NOT NULL,
  `updated_id` int(11)  NOT NULL,
  `updated_by` varchar(128) NOT NULL ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='某天产品组产品成本价';


DROP TABLE IF EXISTS `t_calendar_profit_section`;
CREATE TABLE `t_calendar_profit_section` (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `group_id` int(11) NOT NULL COMMENT '产品组Id',
  `sproduct_id` int(11) NOT NULL COMMENT '产品Id',
  `start_date` date NOT NULL COMMENT '开始时间',
  `end_date` date NOT NULL COMMENT '结束时间（包含在区间内）',
  `profit_price` decimal(8,2) COMMENT '利润',
  `status` tinyint(1) NOT NULL COMMENT '状态（5正常,1删除 )',
  `created_at` int(11)  NOT NULL,
  `created_id` int(11)  NOT NULL,
  `created_by` varchar(128) NOT NULL,
  `updated_at` int(11)  NOT NULL,
  `updated_id` int(11)  NOT NULL,
  `updated_by` varchar(128) NOT NULL ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='时间段内产品组产品利润';


DROP TABLE IF EXISTS `t_calendar_profit_week`;
CREATE TABLE `t_calendar_profit_week` (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `group_id` int(11) NOT NULL COMMENT '产品组Id',
  `sproduct_id` int(11) NOT NULL COMMENT '产品Id', 
  `start_date` date NOT NULL COMMENT '开始时间',
  `end_date` date NOT NULL COMMENT '结束时间（包含在区间内）',
  `week` varchar(16) NOT NULL COMMENT '周几,如1，2，3表示周一，周二，周三',
  `profit_price` decimal(8,2) DEFAULT '0' COMMENT '利润',
  `status` tinyint(1) NOT NULL COMMENT '状态（5正常,1删除 )',
  `created_at` int(11)  NOT NULL,
  `created_id` int(11)  NOT NULL,
  `created_by` varchar(128) NOT NULL,
  `updated_at` int(11)  NOT NULL,
  `updated_id` int(11)  NOT NULL,
  `updated_by` varchar(128) NOT NULL ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='周期产品组产品利润价';

DROP TABLE IF EXISTS `t_calendar_profit_special`;
CREATE TABLE `t_calendar_profit_special` (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `group_id` int(11) NOT NULL COMMENT '产品组Id',
  `sproduct_id` int(11) NOT NULL COMMENT '产品Id',    
  `date` date NOT NULL COMMENT '日期',
  `profit_price` decimal(8,2) DEFAULT '0' COMMENT '成本价',
  `status` tinyint(1) NOT NULL COMMENT '状态（5正常,1删除 )',
  `created_at` int(11)  NOT NULL,
  `created_id` int(11)  NOT NULL,
  `created_by` varchar(128) NOT NULL,
  `updated_at` int(11)  NOT NULL,
  `updated_id` int(11)  NOT NULL,
  `updated_by` varchar(128) NOT NULL ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='某天产品组产品利润';


create index cost_section_index on t_calendar_cost_section (status, sproduct_id,start_date,end_date);
create index cost_week_index on t_calendar_cost_week (status, sproduct_id,start_date,end_date);
create index cost_special_index on t_calendar_cost_special (status, sproduct_id,date);

create index profit_section_index on t_calendar_profit_section (status, sproduct_id,group_id,start_date,end_date);
create index profit_week_index on t_calendar_profit_week(status, sproduct_id,group_id,start_date,end_date);
create index profit_special_index on t_calendar_profit_special(status, sproduct_id,group_id,date);



DROP TABLE IF EXISTS `t_calendar_ota_section`;
CREATE TABLE `t_calendar_ota_section` (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sproduct_id` int(11) NOT NULL COMMENT '产品id',
  `enterprise_id` int(11) NOT NULL COMMENT '企业id',
  `start_date` date NOT NULL COMMENT '开始时间',
  `end_date` date NOT NULL COMMENT '结束时间（包含在区间内）',
  `ota_price` decimal(8,2) COMMENT '成本价',
  `status` tinyint(1) NOT NULL COMMENT '状态（5正常,1删除 )',
  `created_at` int(11)  NOT NULL,
  `created_id` int(11)  NOT NULL,
  `created_by` varchar(128) NOT NULL,
  `updated_at` int(11)  NOT NULL,
  `updated_id` int(11)  NOT NULL,
  `updated_by` varchar(128) NOT NULL ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='时间段内产品OTA价';


DROP TABLE IF EXISTS `t_calendar_ota_week`;
CREATE TABLE `t_calendar_ota_week` (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sproduct_id` int(11) NOT NULL COMMENT '用户产品id',
  `enterprise_id` int(11) NOT NULL COMMENT '企业id',
  `start_date` date NOT NULL COMMENT '开始时间',
  `end_date` date NOT NULL COMMENT '结束时间（包含在区间内）',
  `week` varchar(16) NOT NULL COMMENT '周几,如1，2，3表示周一，周二，周三',
  `ota_price` decimal(8,2) DEFAULT '0' COMMENT '成本价',
  `status` tinyint(1) NOT NULL COMMENT '状态（5正常,1删除 )',
  `created_at` int(11)  NOT NULL,
  `created_id` int(11)  NOT NULL,
  `created_by` varchar(128) NOT NULL,
  `updated_at` int(11)  NOT NULL,
  `updated_id` int(11)  NOT NULL,
  `updated_by` varchar(128) NOT NULL ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='周期产品OTA价';


DROP TABLE IF EXISTS `t_calendar_ota_special`;
CREATE TABLE `t_calendar_ota_special` (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `enterprise_id` int(11) NOT NULL COMMENT '企业id',
  `sproduct_id` int(11) NOT NULL COMMENT '产品id',
  `date` date NOT NULL COMMENT '日期',
  `ota_price` decimal(8,2) DEFAULT '0' COMMENT '成本价',
  `status` tinyint(1) NOT NULL COMMENT '状态（5正常,1删除 )',
  `created_at` int(11)  NOT NULL,
  `created_id` int(11)  NOT NULL,
  `created_by` varchar(128) NOT NULL,
  `updated_at` int(11)  NOT NULL,
  `updated_id` int(11)  NOT NULL,
  `updated_by` varchar(128) NOT NULL ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='某天产品OTA价';

create index ota_section_index on t_calendar_ota_section (status, sproduct_id,start_date,end_date);
create index ota_week_index on t_calendar_ota_week (status, sproduct_id,start_date,end_date);
create index ota_special_index on t_calendar_ota_special (status, sproduct_id,date);