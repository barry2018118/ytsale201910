USE `yt_sale`;

#
# Source for table info_enterprise  (1_企业信息表)
#
DROP TABLE IF EXISTS `info_enterprise`;
CREATE TABLE `info_enterprise` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `domain` varchar(10) DEFAULT NULL COMMENT '域名',
  `company_type` tinyint(2) DEFAULT NULL COMMENT '0：平台管理、1：运营商、2：供应商、3：分销商',
  `parent_id` int(11) DEFAULT NULL COMMENT '创建企业Id',
  `name` varchar(20) DEFAULT NULL COMMENT '公司名称',
  `contacter_name` varchar(10) DEFAULT NULL COMMENT '公司负责人',
  `contacter_phone` varchar(15) DEFAULT NULL COMMENT '负责人手机',
  `email` varchar(30) DEFAULT NULL COMMENT '公司邮箱',
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='企业信息表';

INSERT INTO `info_enterprise` VALUES (1,'admin',0,1,'平台管理',' Manager','15011230721',NULL,'北京','平台管理机构',NULL,NULL,NULL,NULL,1,0,1,'2018-02-14 09:03:45',1,'2018-02-14 09:03:45');


#
# Source for table info_user    (2_用户信息表)
#
DROP TABLE IF EXISTS `info_user`;
CREATE TABLE `info_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account_type` tinyint(2) unsigned DEFAULT NULL COMMENT '账号类型（0：平台、1：商户）',
  `is_master` tinyint(2) unsigned DEFAULT NULL COMMENT '是否主账户（1：是，0：否）',
  `username` varchar(20) DEFAULT NULL COMMENT '用户名',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `name` varchar(12) DEFAULT NULL COMMENT '姓名',
  `phone` varchar(11) DEFAULT NULL COMMENT '移动电话',
  `status` tinyint(2) unsigned DEFAULT NULL COMMENT '状态（1：启用，0：停用）',
  `is_delete` tinyint(2) unsigned DEFAULT NULL COMMENT '是否删除（1：是，0：否）',
  `role_id` int(11) DEFAULT NULL COMMENT '角色Id',
  `enterprise_id` int(11) DEFAULT NULL COMMENT '所属企业Id',
  `create_id` int(11) unsigned DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_id` int(11) unsigned DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

INSERT INTO `info_user` VALUES (1,0,0,'admin','123456','平台管理员','15011230721',1,0,1,1,1,'2018-02-13 11:08:34',1,'2018-02-13 11:08:34');

-- 修改“企业信息表”增加：平台名称、客服电话
ALTER TABLE info_enterprise ADD COLUMN customer_phone VARCHAR(20) COMMENT '客服电话' ;
ALTER TABLE info_enterprise ADD COLUMN platform_name VARCHAR(15) COMMENT '平台名称' ;

