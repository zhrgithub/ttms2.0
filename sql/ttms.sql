DROP DATABASE IF EXISTS ttms;
CREATE DATABASE ttms DEFAULT CHARACTER SET 'utf8';
USE ttms;

DROP TABLE IF EXISTS `tms_projects`;
DROP TABLE IF EXISTS `tms_teams`;
DROP TABLE IF EXISTS `tms_classes`;
DROP TABLE IF EXISTS `tms_products`;
DROP TABLE IF EXISTS `tms_attachements`;
DROP TABLE IF EXISTS `tms_companies`;

DROP TABLE IF EXISTS `sys_organizations`;
DROP TABLE IF EXISTS `sys_resources`;
DROP TABLE IF EXISTS `sys_users`;
DROP TABLE IF EXISTS `sys_roles`;
DROP TABLE IF EXISTS `sys_user_roles`;
DROP TABLE IF EXISTS `sys_role_menus`;

-- 项目表
CREATE TABLE `tms_projects` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `code` VARCHAR(50) DEFAULT NULL COMMENT '项目编码',
  `name` VARCHAR(50) DEFAULT NULL COMMENT '项目名称',
  `beginDate` DATETIME DEFAULT NULL COMMENT '开始日期',
  `endDate` DATETIME   DEFAULT NULL COMMENT '结束日期',
  `valid` TINYINT(1) DEFAULT '1' COMMENT '是否有效',
  `note` VARCHAR(500) DEFAULT NULL COMMENT '备注',
  `createdTime` DATETIME DEFAULT NULL COMMENT '创建时间',
  `modifiedTime` DATETIME DEFAULT NULL COMMENT '修改时间',
  `createdUser`  VARCHAR(20) DEFAULT NULL COMMENT '创建用户',
  `modifiedUser` VARCHAR(20) DEFAULT NULL COMMENT '修改用户',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- 团表
CREATE TABLE `tms_teams` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) DEFAULT NULL COMMENT '团名称',
  `projectId` INT(11) DEFAULT NULL COMMENT '项目id',
  `valid` TINYINT(1) DEFAULT '1' COMMENT '是否有效',
  `note` VARCHAR(500) DEFAULT NULL COMMENT '备注',
  `createdTime` DATETIME DEFAULT NULL COMMENT '创建时间',
  `modifiedTime` DATETIME DEFAULT NULL COMMENT '修改时间',
  `createdUser`  VARCHAR(20) DEFAULT NULL COMMENT '创建用户',
  `modifiedUser` VARCHAR(20) DEFAULT NULL COMMENT '修改用户',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tms_classes`;
CREATE TABLE `tms_classes` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '分类主键',
  `name` VARCHAR(200) DEFAULT NULL COMMENT '分类名称',
  `sort` int(11) DEFAULT NULL COMMENT '分类序号',
  `parentId` INT(11) DEFAULT NULL COMMENT '父类id ',
  `note` VARCHAR(500) DEFAULT NULL COMMENT '备注',
  `createdUser` VARCHAR(255) DEFAULT NULL COMMENT '创建人用户名',
  `createdTime` DATETIME DEFAULT NULL COMMENT '创建时间',
  `modifiedUser` VARCHAR(255) DEFAULT NULL COMMENT '修改人用户名',
  `modifiedTime` DATETIME DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=135 DEFAULT CHARSET=utf8;

-- 系统产品表
CREATE TABLE `tms_products` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '自增长主键',
  `code` VARCHAR(50) NOT NULL COMMENT '产品编号',
  `name` VARCHAR(200) DEFAULT '' COMMENT '产品名称',
  `teamId` INT(11) DEFAULT NULL COMMENT '团号Id',
  `exText` VARCHAR(500) DEFAULT NULL COMMENT '特殊提示',
  `onlineDate` DATE DEFAULT NULL COMMENT '上架时间',
  `offlineDate` DATE DEFAULT NULL COMMENT '下架时间',
  `quantity` INT(11) DEFAULT '0' COMMENT '预售数量',
  `minQty` INT(11) DEFAULT '0' COMMENT '最低数量',
  `soldQty` INT(11) DEFAULT '0' COMMENT '已售数量',
  `price` DECIMAL(10,0) DEFAULT '0' COMMENT '产品价格',
  `classId` INT(11) DEFAULT '0' COMMENT '产品分类编号',
  `nights` INT(11) DEFAULT '0' COMMENT '晚数',
  `state` INT(11) DEFAULT '0' COMMENT '产品状态  0：待售  1：上架   2：下架',
  `note` VARCHAR(2000) DEFAULT NULL COMMENT '备注',
  `createdUser` VARCHAR(255) DEFAULT NULL COMMENT '创建人用户名',
  `createdTime` DATETIME DEFAULT NULL COMMENT '创建时间',
  `modifiedUser` VARCHAR(255) DEFAULT NULL COMMENT '最后修改人用户名',
  `modifiedTime` DATETIME DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- 产品附件表
CREATE TABLE `tms_attachements` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '附件主键',
  `title` VARCHAR(200) DEFAULT NULL COMMENT '标题',
  `fileName` INT(11) DEFAULT NULL COMMENT '文件名称 ',
  `contentType` VARCHAR(50) DEFAULT NULL COMMENT '文件类型',
  `filePath` VARCHAR(200) DEFAULT NULL COMMENT '备注',
  `fileDisgest` VARCHAR(200) DEFAULT NULL COMMENT '文件摘要',
  `createdUser` VARCHAR(255) DEFAULT NULL COMMENT '创建人用户名',
  `createdTime` DATETIME DEFAULT NULL COMMENT '创建时间',
  `modifiedUser` VARCHAR(255) DEFAULT NULL COMMENT '修改人用户名',
  `modifiedTime` DATETIME DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=135 DEFAULT CHARSET=utf8;

-- 分销企业表
CREATE TABLE `sys_companies` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) DEFAULT NULL COMMENT '姓名',
  `category` VARCHAR(50) DEFAULT NULL COMMENT '类型',
  `phone` VARCHAR(50) DEFAULT NULL COMMENT '电话',
  `email` VARCHAR(50) DEFAULT NULL COMMENT '电子邮箱',
  `address` VARCHAR(100) DEFAULT NULL COMMENT '电话',
  `note` VARCHAR(500) DEFAULT NULL COMMENT '备注',
  `valid` TINYINT(1) DEFAULT '1' COMMENT '有效标志',
  `createdTime` DATETIME DEFAULT NULL COMMENT '新增时间',
  `modifiedTime` DATETIME DEFAULT NULL COMMENT '更新时间',
  `createdUser`  VARCHAR(20) DEFAULT NULL COMMENT '创建用户',
  `modifiedUser` VARCHAR(20) DEFAULT NULL COMMENT '更新用户',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- 组织结构表
CREATE TABLE `sys_organizations` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) DEFAULT NULL COMMENT '机构名称',
  `code` VARCHAR(50) DEFAULT NULL COMMENT '机构编码',
  `parentId` INT(11) DEFAULT NULL COMMENT '父机构id',
  `parentIds` INT(11) DEFAULT NULL COMMENT '父机构ids 0/1/2/3',
  `valid` TINYINT(1)  DEFAULT '1' COMMENT '是否有效',
  `note` VARCHAR(500) DEFAULT NULL COMMENT '备注',
  `createdTime` DATETIME DEFAULT NULL COMMENT '创建时间',
  `modifiedTime` DATETIME DEFAULT NULL COMMENT '修改时间',
  `createdUser`  VARCHAR(20) DEFAULT NULL COMMENT '创建用户',
  `modifiedUser` VARCHAR(20) DEFAULT NULL COMMENT '修改用户',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- 资源菜单
CREATE TABLE `sys_resources` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) COMMENT '资源名称',
  `url` VARCHAR(200) COMMENT '资源URL',
  `type` INT COMMENT '类型     1：菜单   2：按钮',
  `sort` INT COMMENT '排序',
  `note` VARCHAR(100) COMMENT '备注',
  `parentId` INT COMMENT '父菜单ID，一级菜单为0',
  `permission` VARCHAR(500) COMMENT '授权(如：user:create)',
  `createdTime` DATETIME DEFAULT NULL COMMENT '创建时间',
  `modifiedTime` DATETIME DEFAULT NULL COMMENT '修改时间',
  `createdUser`  VARCHAR(20) DEFAULT NULL COMMENT '创建用户',
  `modifiedUser` VARCHAR(20) DEFAULT NULL COMMENT '修改用户',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='资源管理';

-- 系统用户
CREATE TABLE `sys_users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(50) NOT NULL COMMENT '用户名',
  `password` VARCHAR(100) COMMENT '密码',
  `email` VARCHAR(100) COMMENT '邮箱',
  `mobile` VARCHAR(100) COMMENT '手机号',
  `valid` TINYINT COMMENT '状态  0：禁用   1：正常',
  `orgId` INT(11) COMMENT '手机号',
  `note` VARCHAR(100) COMMENT '备注',
  `createdTime` DATETIME DEFAULT NULL COMMENT '创建时间',
  `modifiedTime` DATETIME DEFAULT NULL COMMENT '修改时间',
  `createdUser`  VARCHAR(20) DEFAULT NULL COMMENT '创建用户',
  `modifiedUser` VARCHAR(20) DEFAULT NULL COMMENT '修改用户',
  PRIMARY KEY (`id`),
  UNIQUE INDEX (`username`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='系统用户';

-- 角色
CREATE TABLE `sys_roles` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) COMMENT '角色名称',
  `note` VARCHAR(100) COMMENT '备注',
  `createdTime` DATETIME DEFAULT NULL COMMENT '创建时间',
  `modifiedTime` DATETIME DEFAULT NULL COMMENT '修改时间',
  `createdUser`  VARCHAR(20) DEFAULT NULL COMMENT '创建用户',
  `modifiedUser` VARCHAR(20) DEFAULT NULL COMMENT '修改用户',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='角色';

-- 用户与角色对应关系
CREATE TABLE `sys_user_roles` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT COMMENT '用户ID',
  `role_id` INT COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='用户与角色对应关系';

-- 角色与菜单对应关系
CREATE TABLE `sys_role_resources` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `role_id` INT COMMENT '角色ID',
  `resource_id` INT COMMENT 'ID',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='角色与菜单对应关系';

SHOW TABLES;
SELECT*FROM sys_companies;      
SELECT*FROM sys_organizations;  
SELECT*FROM sys_resources;      
SELECT*FROM sys_role_resources; 
SELECT*FROM sys_roles;          
SELECT*FROM sys_user_roles;     
SELECT*FROM sys_users;          
SELECT*FROM tms_attachements;   
SELECT*FROM tms_classes;        
SELECT*FROM tms_products;       
SELECT*FROM tms_projects;       
SELECT*FROM tms_teams;

在黑窗口显示中文方法
SET NAMES gbk;
SELECT * FROM ttms.tms_projects 