DROP TABLE IF EXISTS `t_platform_interface`;
CREATE TABLE `t_platform_interface` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL DEFAULT '' COMMENT '接口名称',
  `ename` varchar(32) NOT NULL DEFAULT '' COMMENT '类型为供应商：接口URL访问后缀；渠道则为：渠道英文名',
  `type_id` tinyint(1) NOT NULL DEFAULT '1' COMMENT '类型： 1 渠道 2 供应商',
  `interface_config` varchar(1023) DEFAULT '' COMMENT '接口所需的配置信息',
  `data` varchar(1023) DEFAULT '' COMMENT '固定参数',
  `sort` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
  `status` tinyint(1) NOT NULL DEFAULT '5' COMMENT '状态 1 删除 5正常 2 禁用',
  `created_at` datetime NOT NULL  ,
  `created_id` int(11) NOT NULL DEFAULT '0' ,
  `created_by` varchar(128) NOT NULL DEFAULT '',
  `updated_at` datetime NOT NULL ,
  `updated_id` int(11) NOT NULL DEFAULT '0',
  `updated_by` varchar(128) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT '基础配置需求表';

DROP TABLE IF EXISTS `t_enterprise_platform_interface`;
CREATE TABLE `t_enterprise_platform_interface` (
  `id` int(11)  NOT NULL AUTO_INCREMENT,
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
  `created_at` datetime  NOT NULL ,
  `created_by` varchar(128) NOT NULL DEFAULT '',
  `created_id` int(11) NOT NULL DEFAULT '0' ,
  `updated_at` datetime  NOT NULL ,
  `updated_id` int(11) NOT NULL DEFAULT '0',
  `updated_by` varchar(128) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT '企业接口配置表';