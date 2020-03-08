/*
Navicat MySQL Data Transfer

Source Server         : dev
Source Server Version : 50726
Source Host           : localhost:3306
Source Database       : admin

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2020-03-08 19:06:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
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

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('1236249573083074562', '用户管理', 'menu', '', 'sys:user', null, null, null, null, null, '0', null, '0', '0');
INSERT INTO `sys_permission` VALUES ('1236249738175074306', '角色管理', 'menu', '', 'sys:role', null, null, null, null, null, '0', null, '0', '0');
INSERT INTO `sys_permission` VALUES ('1236249852591493122', '菜单管理', 'menu', '', 'sys:permission', null, null, null, null, null, '0', null, '0', '0');
INSERT INTO `sys_permission` VALUES ('1236250029930860545', '用户创建', 'button', '', 'sys:user:create', '1236249573083074562', null, null, null, null, '0', null, '2', '0');
INSERT INTO `sys_permission` VALUES ('1236250096934866945', '用户编辑', 'button', '', 'sys:user:update', '1236249573083074562', null, null, null, null, '0', null, '1', '0');
INSERT INTO `sys_permission` VALUES ('1236250153285341185', '用户删除', 'button', '', 'sys:user:delete', '1236249573083074562', null, null, null, null, '0', null, '3', '0');
INSERT INTO `sys_permission` VALUES ('1236250259334123522', '用户列表', 'button', '', 'sys:user:view', '1236249573083074562', null, null, null, null, '0', null, '4', '0');
INSERT INTO `sys_permission` VALUES ('1236250491786645506', '角色列表', 'button', '', 'sys:role:view', '1236249738175074306', null, null, null, null, '0', null, '1', '0');
INSERT INTO `sys_permission` VALUES ('1236250557628829697', '角色创建', 'button', '', 'sys:role:create', '1236249738175074306', null, null, null, null, '0', null, '2', '0');
INSERT INTO `sys_permission` VALUES ('1236250615384395777', '角色编辑', 'button', '', 'sys:role:update', '1236249738175074306', null, null, null, null, '0', null, '3', '0');
INSERT INTO `sys_permission` VALUES ('1236250668450729985', '角色删除', 'button', '', 'sys:role:delete', '1236249738175074306', null, null, null, null, '0', null, '4', '0');
INSERT INTO `sys_permission` VALUES ('1236250741083492354', '菜单删除', 'button', '', 'sys:permission:delete', '1236249852591493122', null, null, null, null, '0', null, '1', '0');
INSERT INTO `sys_permission` VALUES ('1236250793734590465', '菜单创建', 'button', '', 'sys:permission:create', '1236249852591493122', null, null, null, null, '0', null, '2', '0');
INSERT INTO `sys_permission` VALUES ('1236250837355352066', '菜单编辑', 'button', '', 'sys:permission:update', '1236249852591493122', null, null, null, null, '0', null, '3', '0');
INSERT INTO `sys_permission` VALUES ('1236250946893795329', '菜单列表', 'button', '', 'sys:permission:view', '1236249852591493122', null, null, null, null, '0', null, '4', '0');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
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

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1236258106935721986', '超级管理员', '拥有一切的男人', null, null, null, null, null, '0', '1');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
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

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES ('1236258107258683393', '1236258106935721986', '1236249573083074562', null, null, null, null, '0');
INSERT INTO `sys_role_permission` VALUES ('1236258107422261250', '1236258106935721986', '1236249738175074306', null, null, null, null, '0');
INSERT INTO `sys_role_permission` VALUES ('1236258107430649858', '1236258106935721986', '1236249852591493122', null, null, null, null, '0');
INSERT INTO `sys_role_permission` VALUES ('1236258107506147329', '1236258106935721986', '1236250029930860545', null, null, null, null, '0');
INSERT INTO `sys_role_permission` VALUES ('1236258107522924546', '1236258106935721986', '1236250096934866945', null, null, null, null, '0');
INSERT INTO `sys_role_permission` VALUES ('1236258107539701761', '1236258106935721986', '1236250153285341185', null, null, null, null, '0');
INSERT INTO `sys_role_permission` VALUES ('1236258107548090369', '1236258106935721986', '1236250259334123522', null, null, null, null, '0');
INSERT INTO `sys_role_permission` VALUES ('1236258107556478978', '1236258106935721986', '1236250491786645506', null, null, null, null, '0');
INSERT INTO `sys_role_permission` VALUES ('1236258107569061890', '1236258106935721986', '1236250557628829697', null, null, null, null, '0');
INSERT INTO `sys_role_permission` VALUES ('1236258107577450498', '1236258106935721986', '1236250615384395777', null, null, null, null, '0');
INSERT INTO `sys_role_permission` VALUES ('1236258107585839105', '1236258106935721986', '1236250668450729985', null, null, null, null, '0');
INSERT INTO `sys_role_permission` VALUES ('1236258107598422017', '1236258106935721986', '1236250741083492354', null, null, null, null, '0');
INSERT INTO `sys_role_permission` VALUES ('1236258107606810625', '1236258106935721986', '1236250793734590465', null, null, null, null, '0');
INSERT INTO `sys_role_permission` VALUES ('1236258107615199233', '1236258106935721986', '1236250837355352066', null, null, null, null, '0');
INSERT INTO `sys_role_permission` VALUES ('1236258107623587841', '1236258106935721986', '1236250946893795329', null, null, null, null, '0');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
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

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1236260255979651073', '管理员', '4QrcOUm6Wau+VuBX8g+IPg==', 'tC6iL0SBUJAzpTCTF2bF', null, null, null, null, null, '管理员', '123456789', '123@qq.com', '123456789', null, '0', '0', '0', null, null, null, null);
INSERT INTO `sys_user` VALUES ('1236567374796734466', '1', 'ZRK9Q9nKpuAsmQsKgmUtyg==', 'mQXNWXo5qZdtdx5nX4OO', null, null, null, null, null, null, '11', '111', null, null, '0', '1', '1', '2020-03-08 16:20:11', null, null, null);
INSERT INTO `sys_user` VALUES ('1236567820319899650', '111', 'tZxnvxlqR1gZHkL3ZnDOug==', 'w8rOMeoIPO2tBxqsKPPU', null, null, null, null, null, null, '111111', '11111', null, null, '0', '0', '1', '2020-03-08 16:21:57', null, null, null);
INSERT INTO `sys_user` VALUES ('1236568136973074434', '222', 'vL4zZeasleosA0OiOVg03Q==', 'NUsvuvSGZX38NQOyZPYu', null, null, null, null, null, null, '222', '22', null, null, '0', '0', '1', '2020-03-08 16:23:13', null, null, null);
INSERT INTO `sys_user` VALUES ('1236568285522739201', '111', 'aY1RoZ2KEhzlgUmde3AWaA==', 'gLu6AX2rQ5q5qImmP9ME', null, null, null, null, null, null, '11', '333', null, null, '0', '1', '1', '2020-03-08 16:23:48', null, null, null);
INSERT INTO `sys_user` VALUES ('1236570313795878913', '1', 'xMpCOKC5I4INzFCab3WEmw==', 'hLedFzhhLB62pIHFpfQl', null, null, null, null, null, null, '11', '55', null, null, '0', '1', '1', '2020-03-08 16:31:52', null, null, null);
INSERT INTO `sys_user` VALUES ('1236570552028151810', '11', 'ZRK9Q9nKpuAsmQsKgmUtyg==', 'yNYCFK4vzICs7cpcfQXo', null, null, null, null, null, null, '111', '7', null, null, '0', '1', '1', '2020-03-08 16:32:49', null, null, null);
INSERT INTO `sys_user` VALUES ('1236570811756232706', '111', 'tZxnvxlqR1gZHkL3ZnDOug==', 'uCo41HGScjD08UYoMG8D', null, null, null, null, null, null, '11', '8', null, null, '0', '1', '1', '2020-03-08 16:33:51', null, null, null);
INSERT INTO `sys_user` VALUES ('1236578498887909378', '11', 'aY1RoZ2KEhzlgUmde3AWaA==', 'pDaFcOz3zVWoW2VZCKWY', null, null, null, null, null, null, '13423456756', '582833@qq.com', null, null, '0', '1', '1', '2020-03-08 17:04:23', null, null, null);

-- ----------------------------
-- Table structure for sys_user_online
-- ----------------------------
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

-- ----------------------------
-- Records of sys_user_online
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
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

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1236260256139034626', '1236260255979651073', '1236258106935721986', null, null, null, null, '0');
INSERT INTO `sys_user_role` VALUES ('1236567374998061058', '1236567374796734466', '1236258106935721986', null, null, null, null, '0');
INSERT INTO `sys_user_role` VALUES ('1236567820445728769', '1236567820319899650', '1236258106935721986', null, null, null, null, '0');
INSERT INTO `sys_user_role` VALUES ('1236568137056960514', '1236568136973074434', '1236258106935721986', null, null, null, null, '0');
INSERT INTO `sys_user_role` VALUES ('1236568285686317057', '1236568285522739201', '1236258106935721986', null, null, null, null, '0');
INSERT INTO `sys_user_role` VALUES ('1236570313972039682', '1236570313795878913', '1236258106935721986', null, null, null, null, '0');
INSERT INTO `sys_user_role` VALUES ('1236570552208506881', '1236570552028151810', '1236258106935721986', null, null, null, null, '0');
INSERT INTO `sys_user_role` VALUES ('1236570811944976386', '1236570811756232706', '1236258106935721986', null, null, null, null, '0');
INSERT INTO `sys_user_role` VALUES ('1236578498917269506', '1236578498887909378', '1236258106935721986', null, null, null, null, '0');
