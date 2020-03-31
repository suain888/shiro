/*
SQLyog Ultimate v11.33 (64 bit)
MySQL - 5.5.49 : Database - admin
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`admin` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `admin`;

/*Table structure for table `sys_permission` */

DROP TABLE IF EXISTS `sys_permission`;

CREATE TABLE `sys_permission` (
  `id` varchar(64) NOT NULL,
  `permission_name` varchar(64) DEFAULT NULL COMMENT '权限名称',
  `permission_type` varchar(10) DEFAULT NULL COMMENT '权限类型(''directory'',''button'',''menu'')',
  `permission_url` varchar(64) DEFAULT NULL COMMENT '权限路由',
  `permission_str` varchar(32) DEFAULT NULL COMMENT '权限字符串(menu例子：role:*，button例子：role:create,role:update,role:delete,role:view)',
  `parent_id` varchar(64) DEFAULT NULL COMMENT '父类ID',
  `creater` varchar(64) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `updater` varchar(64) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `delete_flag` tinyint(1) DEFAULT '0',
  `permission_icon` varchar(255) DEFAULT NULL COMMENT '权限图标',
  `permission_order` int(11) DEFAULT NULL COMMENT '权限排序',
  `is_view` tinyint(1) DEFAULT '0' COMMENT 'true:隐藏 false:显示',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_permission` */

insert  into `sys_permission`(`id`,`permission_name`,`permission_type`,`permission_url`,`permission_str`,`parent_id`,`creater`,`create_date`,`updater`,`update_date`,`delete_flag`,`permission_icon`,`permission_order`,`is_view`) values ('1236249573083074562','用户管理','menu','','sys:user',NULL,NULL,NULL,NULL,NULL,0,NULL,0,0),('1236249738175074306','角色管理','menu','','sys:role',NULL,NULL,NULL,NULL,NULL,0,NULL,0,0),('1236249852591493122','菜单管理','menu','','sys:permission',NULL,NULL,NULL,NULL,NULL,0,NULL,0,0),('1236250029930860545','用户创建','button','','sys:user:create','1236249573083074562',NULL,NULL,NULL,NULL,0,NULL,2,0),('1236250096934866945','用户编辑','button','','sys:user:update','1236249573083074562',NULL,NULL,NULL,NULL,0,NULL,1,0),('1236250153285341185','用户删除','button','','sys:user:delete','1236249573083074562',NULL,NULL,NULL,NULL,0,NULL,3,0),('1236250259334123522','用户列表','button','','sys:user:view','1236249573083074562',NULL,NULL,NULL,NULL,0,NULL,4,0),('1236250491786645506','角色列表','button','','sys:role:view','1236249738175074306',NULL,NULL,NULL,NULL,0,NULL,1,0),('1236250557628829697','角色创建','button','','sys:role:create','1236249738175074306',NULL,NULL,NULL,NULL,0,NULL,2,0),('1236250615384395777','角色编辑','button','','sys:role:update','1236249738175074306',NULL,NULL,NULL,NULL,0,NULL,3,0),('1236250668450729985','角色删除','button','','sys:role:delete','1236249738175074306',NULL,NULL,NULL,NULL,0,NULL,4,0),('1236250741083492354','菜单删除','button','','sys:permission:delete','1236249852591493122',NULL,NULL,NULL,NULL,0,NULL,1,0),('1236250793734590465','菜单创建','button','','sys:permission:create','1236249852591493122',NULL,NULL,NULL,NULL,0,NULL,2,0),('1236250837355352066','菜单编辑','button','','sys:permission:update','1236249852591493122',NULL,NULL,NULL,NULL,0,NULL,3,0),('1236250946893795329','菜单列表','button','','sys:permission:view','1236249852591493122',NULL,NULL,NULL,NULL,0,NULL,4,0);

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` varchar(64) NOT NULL COMMENT '主键ID',
  `role_name` varchar(64) DEFAULT NULL COMMENT '角色名称',
  `role_desc` varchar(255) DEFAULT NULL COMMENT '角色描述',
  `compay_id` varchar(64) DEFAULT NULL,
  `creater` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL,
  `updater` varchar(64) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `delete_flag` tinyint(1) DEFAULT '0' COMMENT '0:可用 1:不可用',
  `sys_default` tinyint(1) DEFAULT '0' COMMENT 'true:系统默认 false:不是系统默认',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`role_name`,`role_desc`,`compay_id`,`creater`,`create_date`,`updater`,`update_date`,`delete_flag`,`sys_default`) values ('1236258106935721986','超级管理员','拥有一切的男人',NULL,NULL,NULL,NULL,NULL,0,1);

/*Table structure for table `sys_role_permission` */

DROP TABLE IF EXISTS `sys_role_permission`;

CREATE TABLE `sys_role_permission` (
  `id` varchar(64) NOT NULL,
  `role_id` varchar(64) DEFAULT NULL,
  `permission_id` varchar(64) DEFAULT NULL,
  `creater` varchar(64) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `updater` varchar(64) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `delete_flag` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_role_permission` */

insert  into `sys_role_permission`(`id`,`role_id`,`permission_id`,`creater`,`create_date`,`updater`,`update_date`,`delete_flag`) values ('1236258107258683393','1236258106935721986','1236249573083074562',NULL,NULL,NULL,NULL,0),('1236258107422261250','1236258106935721986','1236249738175074306',NULL,NULL,NULL,NULL,0),('1236258107430649858','1236258106935721986','1236249852591493122',NULL,NULL,NULL,NULL,0),('1236258107506147329','1236258106935721986','1236250029930860545',NULL,NULL,NULL,NULL,0),('1236258107522924546','1236258106935721986','1236250096934866945',NULL,NULL,NULL,NULL,0),('1236258107539701761','1236258106935721986','1236250153285341185',NULL,NULL,NULL,NULL,0),('1236258107548090369','1236258106935721986','1236250259334123522',NULL,NULL,NULL,NULL,0),('1236258107556478978','1236258106935721986','1236250491786645506',NULL,NULL,NULL,NULL,0),('1236258107569061890','1236258106935721986','1236250557628829697',NULL,NULL,NULL,NULL,0),('1236258107577450498','1236258106935721986','1236250615384395777',NULL,NULL,NULL,NULL,0),('1236258107585839105','1236258106935721986','1236250668450729985',NULL,NULL,NULL,NULL,0),('1236258107598422017','1236258106935721986','1236250741083492354',NULL,NULL,NULL,NULL,0),('1236258107606810625','1236258106935721986','1236250793734590465',NULL,NULL,NULL,NULL,0),('1236258107615199233','1236258106935721986','1236250837355352066',NULL,NULL,NULL,NULL,0),('1236258107623587841','1236258106935721986','1236250946893795329',NULL,NULL,NULL,NULL,0);

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` varchar(64) NOT NULL COMMENT '主键ID',
  `user_name` varchar(64) DEFAULT NULL COMMENT '用户名',
  `user_password` varchar(64) DEFAULT NULL COMMENT '用户密码',
  `salt` varchar(64) DEFAULT NULL COMMENT '盐',
  `nick_name` varchar(64) DEFAULT NULL COMMENT '真实姓名',
  `company_id` varchar(64) DEFAULT NULL,
  `company_name` varchar(100) DEFAULT NULL COMMENT '公司名称',
  `department_id` bigint(64) DEFAULT NULL COMMENT '部门ID',
  `department_name` varchar(100) DEFAULT NULL COMMENT '部门名称',
  `job` varchar(64) DEFAULT NULL COMMENT '岗位',
  `mobile` char(11) DEFAULT NULL COMMENT '手机号',
  `email` varchar(20) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(11) DEFAULT NULL COMMENT '座机',
  `avatar_url` varchar(100) DEFAULT NULL COMMENT '头像地址',
  `secret` tinyint(1) DEFAULT '0' COMMENT '保密字段（0不能，1能）',
  `delete_flag` tinyint(1) DEFAULT '0' COMMENT '可用状态 0：可用 1：禁用',
  `locked` tinyint(1) DEFAULT '0' COMMENT '是否锁定 1：锁定 0：未锁定',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `creator` varchar(64) DEFAULT NULL COMMENT '创建人账号',
  `updater` varchar(64) DEFAULT NULL COMMENT '修改者姓名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`user_name`,`user_password`,`salt`,`nick_name`,`company_id`,`company_name`,`department_id`,`department_name`,`job`,`mobile`,`email`,`phone`,`avatar_url`,`secret`,`delete_flag`,`locked`,`create_time`,`update_time`,`creator`,`updater`) values ('1','132','ICy5YqxZB1uWSwcVLSNLcA==','IDUz2dlUXQE2iXSLgBN5','密码123',NULL,NULL,NULL,NULL,NULL,NULL,'123',NULL,NULL,0,0,0,'2020-03-08 23:31:58',NULL,NULL,NULL),('1236260255979651073','管理员','ICy5YqxZB1uWSwcVLSNLcA==','IDUz2dlUXQE2iXSLgBN5',NULL,NULL,NULL,NULL,NULL,'管理员','123456789','123@qq.com','123456789',NULL,0,0,0,NULL,NULL,NULL,NULL),('1236567374796734466','1','ICy5YqxZB1uWSwcVLSNLcA==','IDUz2dlUXQE2iXSLgBN5',NULL,NULL,NULL,NULL,NULL,NULL,'11','111',NULL,NULL,0,1,1,'2020-03-08 16:20:11',NULL,NULL,NULL),('1236567820319899650','111','ICy5YqxZB1uWSwcVLSNLcA==','IDUz2dlUXQE2iXSLgBN5',NULL,NULL,NULL,NULL,NULL,NULL,'111111','11111',NULL,NULL,0,0,1,'2020-03-08 16:21:57',NULL,NULL,NULL),('1236568136973074434','222','ICy5YqxZB1uWSwcVLSNLcA==','IDUz2dlUXQE2iXSLgBN5',NULL,NULL,NULL,NULL,NULL,NULL,'222','22',NULL,NULL,0,0,1,'2020-03-08 16:23:13',NULL,NULL,NULL),('1236568285522739201','111','ICy5YqxZB1uWSwcVLSNLcA==','IDUz2dlUXQE2iXSLgBN5',NULL,NULL,NULL,NULL,NULL,NULL,'11','333',NULL,NULL,0,1,1,'2020-03-08 16:23:48',NULL,NULL,NULL),('1236570313795878913','1','ICy5YqxZB1uWSwcVLSNLcA==','IDUz2dlUXQE2iXSLgBN5',NULL,NULL,NULL,NULL,NULL,NULL,'11','55',NULL,NULL,0,1,1,'2020-03-08 16:31:52',NULL,NULL,NULL),('1236570552028151810','11','ICy5YqxZB1uWSwcVLSNLcA==','IDUz2dlUXQE2iXSLgBN5',NULL,NULL,NULL,NULL,NULL,NULL,'111','7',NULL,NULL,0,1,1,'2020-03-08 16:32:49',NULL,NULL,NULL),('1236570811756232706','111','ICy5YqxZB1uWSwcVLSNLcA==','IDUz2dlUXQE2iXSLgBN5',NULL,NULL,NULL,NULL,NULL,NULL,'11','8',NULL,NULL,0,1,1,'2020-03-08 16:33:51',NULL,NULL,NULL),('1236578498887909378','11','ICy5YqxZB1uWSwcVLSNLcA==','IDUz2dlUXQE2iXSLgBN5',NULL,NULL,NULL,NULL,NULL,NULL,'13423456756','582833@qq.com',NULL,NULL,0,1,1,'2020-03-08 17:04:23',NULL,NULL,NULL);

/*Table structure for table `sys_user_online` */

DROP TABLE IF EXISTS `sys_user_online`;

CREATE TABLE `sys_user_online` (
  `sessionId` varchar(50) NOT NULL DEFAULT '' COMMENT '用户会话id',
  `login_name` varchar(50) DEFAULT '' COMMENT '登录账号',
  `dept_name` varchar(50) DEFAULT '' COMMENT '部门名称',
  `ipaddr` varchar(50) DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) DEFAULT '' COMMENT '操作系统',
  `status` varchar(10) DEFAULT '' COMMENT '在线状态on_line在线off_line离线',
  `start_timestamp` datetime DEFAULT NULL COMMENT 'session创建时间',
  `last_access_time` datetime DEFAULT NULL COMMENT 'session最后访问时间',
  `expire_time` int(5) DEFAULT '0' COMMENT '超时时间，单位为分钟',
  PRIMARY KEY (`sessionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='在线用户记录';

/*Data for the table `sys_user_online` */

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `id` varchar(64) NOT NULL,
  `user_id` varchar(64) DEFAULT NULL,
  `role_id` varchar(64) DEFAULT NULL,
  `creater` varchar(64) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `updater` varchar(64) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `delete_flag` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`id`,`user_id`,`role_id`,`creater`,`create_date`,`updater`,`update_date`,`delete_flag`) values ('1236260256139034626','1236260255979651073','1236258106935721986',NULL,NULL,NULL,NULL,0),('1236567374998061058','1236567374796734466','1236258106935721986',NULL,NULL,NULL,NULL,0),('1236567820445728769','1236567820319899650','1236258106935721986',NULL,NULL,NULL,NULL,0),('1236568137056960514','1236568136973074434','1236258106935721986',NULL,NULL,NULL,NULL,0),('1236568285686317057','1236568285522739201','1236258106935721986',NULL,NULL,NULL,NULL,0),('1236570313972039682','1236570313795878913','1236258106935721986',NULL,NULL,NULL,NULL,0),('1236570552208506881','1236570552028151810','1236258106935721986',NULL,NULL,NULL,NULL,0),('1236570811944976386','1236570811756232706','1236258106935721986',NULL,NULL,NULL,NULL,0),('1236578498917269506','1236578498887909378','1236258106935721986',NULL,NULL,NULL,NULL,0);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
