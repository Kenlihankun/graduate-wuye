/*
SQLyog Ultimate v12.5.0 (64 bit)
MySQL - 8.0.22 : Database - sys_wygl
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`sys_wygl` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `sys_wygl`;

/*Table structure for table `fee_park` */

DROP TABLE IF EXISTS `fee_park`;

CREATE TABLE `fee_park` (
  `park_fee_id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int DEFAULT NULL COMMENT '业主id',
  `park_id` int DEFAULT NULL COMMENT '车位id',
  `pay_park_month` varchar(16) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '缴费年月',
  `pay_park_money` decimal(18,2) DEFAULT NULL COMMENT '缴费金额',
  `pay_park_status` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '0:未缴费  1：已缴费',
  `pay_park_time` datetime DEFAULT NULL,
  PRIMARY KEY (`park_fee_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

/*Data for the table `fee_park` */

insert  into `fee_park`(`park_fee_id`,`user_id`,`park_id`,`pay_park_month`,`pay_park_money`,`pay_park_status`,`pay_park_time`) values 
(1,34,13,'2022-02',100.00,'1','2022-01-25 20:27:42');

/*Table structure for table `fee_power` */

DROP TABLE IF EXISTS `fee_power`;

CREATE TABLE `fee_power` (
  `power_id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `house_id` int DEFAULT NULL COMMENT '房屋id',
  `user_id` int DEFAULT NULL COMMENT '业主id',
  `pay_power_month` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '缴费年月',
  `pay_power_money` decimal(18,2) DEFAULT NULL COMMENT '缴费金额',
  `power_num` varchar(16) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '表显',
  `pay_power_status` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '0:未缴费  1：已缴费',
  `pay_power_time` datetime DEFAULT NULL COMMENT '缴费时间',
  PRIMARY KEY (`power_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

/*Data for the table `fee_power` */

insert  into `fee_power`(`power_id`,`house_id`,`user_id`,`pay_power_month`,`pay_power_money`,`power_num`,`pay_power_status`,`pay_power_time`) values 
(35,26,34,'2022-01',100.00,'1','1','2022-01-25 20:26:45');

/*Table structure for table `fee_water` */

DROP TABLE IF EXISTS `fee_water`;

CREATE TABLE `fee_water` (
  `water_id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `house_id` int DEFAULT NULL COMMENT '房屋id',
  `user_id` int DEFAULT NULL COMMENT '业主id',
  `pay_water_month` varchar(16) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '缴费年月',
  `pay_water_money` decimal(18,2) DEFAULT NULL COMMENT '缴费金额',
  `water_num` varchar(16) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '表显',
  `pay_water_status` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '0:未缴费  1：已缴费',
  `pay_water_time` datetime DEFAULT NULL COMMENT '缴费时间',
  PRIMARY KEY (`water_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

/*Data for the table `fee_water` */

/*Table structure for table `house_building` */

DROP TABLE IF EXISTS `house_building`;

CREATE TABLE `house_building` (
  `build_id` int NOT NULL AUTO_INCREMENT COMMENT '栋数id',
  `type` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '栋数类型 0：普通房 1：电梯房',
  `build_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '栋数名称',
  `order_num` int DEFAULT NULL COMMENT '序号',
  PRIMARY KEY (`build_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=COMPACT;

/*Data for the table `house_building` */

insert  into `house_building`(`build_id`,`type`,`build_name`,`order_num`) values 
(11,'1','A栋',1),
(12,'0','B栋',1),
(13,'0','C栋',3),
(14,'1','E栋',4);

/*Table structure for table `house_list` */

DROP TABLE IF EXISTS `house_list`;

CREATE TABLE `house_list` (
  `house_id` int NOT NULL AUTO_INCREMENT COMMENT '房屋id',
  `unit_id` int DEFAULT NULL COMMENT '单元id',
  `house_num` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '房屋编号',
  `house_area` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '房屋面积',
  `status` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '使用状态 0:未使用 1：已使用',
  PRIMARY KEY (`house_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=COMPACT;

/*Data for the table `house_list` */

insert  into `house_list`(`house_id`,`unit_id`,`house_num`,`house_area`,`status`) values 
(23,20,'1','123','0'),
(26,22,'101','123','1');

/*Table structure for table `house_unit` */

DROP TABLE IF EXISTS `house_unit`;

CREATE TABLE `house_unit` (
  `unit_id` int NOT NULL AUTO_INCREMENT COMMENT '单元id',
  `build_id` int DEFAULT NULL COMMENT '栋数id',
  `unit_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '单元名称',
  PRIMARY KEY (`unit_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=COMPACT;

/*Data for the table `house_unit` */

insert  into `house_unit`(`unit_id`,`build_id`,`unit_name`) values 
(20,11,'a'),
(21,13,'c'),
(22,14,'E101');

/*Table structure for table `live_house` */

DROP TABLE IF EXISTS `live_house`;

CREATE TABLE `live_house` (
  `live_house_id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int DEFAULT NULL COMMENT '业主id',
  `house_id` int DEFAULT NULL COMMENT '房屋id',
  `use_status` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '0:使用中 1： 解绑、退房',
  PRIMARY KEY (`live_house_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=COMPACT;

/*Data for the table `live_house` */

insert  into `live_house`(`live_house_id`,`user_id`,`house_id`,`use_status`) values 
(30,34,26,'1'),
(31,34,23,'1'),
(32,34,23,'1'),
(33,34,23,'1'),
(34,34,26,'0');

/*Table structure for table `live_park` */

DROP TABLE IF EXISTS `live_park`;

CREATE TABLE `live_park` (
  `live_park_id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int DEFAULT NULL COMMENT '业主id',
  `park_id` int DEFAULT NULL COMMENT '车位id',
  `live_statue` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '0:使用 1：解绑、退车位状态',
  PRIMARY KEY (`live_park_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=COMPACT;

/*Data for the table `live_park` */

insert  into `live_park`(`live_park_id`,`user_id`,`park_id`,`live_statue`) values 
(50,34,13,'1'),
(51,34,13,'1'),
(52,34,13,'0');

/*Table structure for table `live_role` */

DROP TABLE IF EXISTS `live_role`;

CREATE TABLE `live_role` (
  `liv_role_id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` int DEFAULT NULL COMMENT '角色id',
  `user_id` int DEFAULT NULL COMMENT '业主id',
  PRIMARY KEY (`liv_role_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=COMPACT;

/*Data for the table `live_role` */

insert  into `live_role`(`liv_role_id`,`role_id`,`user_id`) values 
(38,17,34);

/*Table structure for table `live_user` */

DROP TABLE IF EXISTS `live_user`;

CREATE TABLE `live_user` (
  `user_id` int NOT NULL AUTO_INCREMENT COMMENT '业主id',
  `username` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '登录账号',
  `phone` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '业主电话',
  `sex` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '性别 0：男 1：女',
  `login_name` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '姓名',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '登录密码',
  `status` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT ' 0：启用 1：禁用',
  `is_account_non_expired` int DEFAULT NULL COMMENT '帐户是否过期(1 未过期，0已过期)	',
  `is_account_non_locked` int DEFAULT NULL COMMENT ' 帐户是否被锁定(1 未过期，0已过期)	',
  `is_credentials_non_expired` int DEFAULT NULL COMMENT '密码是否过期(1 未过期，0已过期)	',
  `is_enabled` int DEFAULT NULL COMMENT '帐户是否可用(1 可用，0 删除用户)	',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=COMPACT;

/*Data for the table `live_user` */

insert  into `live_user`(`user_id`,`username`,`phone`,`sex`,`login_name`,`password`,`status`,`is_account_non_expired`,`is_account_non_locked`,`is_credentials_non_expired`,`is_enabled`) values 
(34,'lisi','17875184374','1','lisi','$2a$10$h47khzr1VpMQcUWlKtrNMuyRIKq/TJfah0e3bwZSuhTJI2QySxlqy','0',1,1,1,1);

/*Table structure for table `parking_list` */

DROP TABLE IF EXISTS `parking_list`;

CREATE TABLE `parking_list` (
  `park_id` int NOT NULL AUTO_INCREMENT COMMENT '车位id',
  `park_type` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '车位类型 0:地上 1：地下',
  `park_name` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '车位名称',
  `park_status` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '车位使用状态 0：未使用 1：已使用',
  `park_num` int DEFAULT NULL COMMENT '车位序号',
  PRIMARY KEY (`park_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=COMPACT;

/*Data for the table `parking_list` */

insert  into `parking_list`(`park_id`,`park_type`,`park_name`,`park_status`,`park_num`) values 
(13,'0','E101','1',1);

/*Table structure for table `sys_menu` */

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
  `menu_id` int NOT NULL AUTO_INCREMENT COMMENT '菜单id',
  `parent_id` int DEFAULT NULL COMMENT '父级菜单id',
  `menu_label` varchar(56) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '菜单名称',
  `menu_code` varchar(56) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '权限字段',
  `name` varchar(56) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '路由名称',
  `path` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '路由地址',
  `url` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '组件路由',
  `type` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '类型 0：目录 1：菜单 2：按钮',
  `icon` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '图标',
  `order_num` int DEFAULT NULL COMMENT '序号',
  `remark` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `parent_name` varchar(56) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '上级菜单名称',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

/*Data for the table `sys_menu` */

insert  into `sys_menu`(`menu_id`,`parent_id`,`menu_label`,`menu_code`,`name`,`path`,`url`,`type`,`icon`,`order_num`,`remark`,`parent_name`) values 
(3,0,'系统管理','sys:system','','/system','','0','el-icon-menu',1,'','顶级菜单'),
(4,3,'员工管理','sys:sysUserList','sys:user:index','/sysUserList','/system/sysUserList','1','el-icon-s-custom',1,'','系统管理'),
(5,3,'角色管理','sys:sysRoleList','sysRoleList','/sysRoleList','/system/sysRoleList','1','el-icon-date',2,'','系统管理'),
(6,3,'权限管理','sys:sysMenuList','sysMenuList','/sysMenuList','/system/sysMenuList','1','el-icon-school',3,'','系统管理'),
(7,4,'新增','sys:user:add','','','','2','',1,'','员工管理'),
(8,4,'编辑','sys:user:edit','','','','2','',2,'','员工管理'),
(9,4,'删除','sys:user:delete','','','','2','',3,'','员工管理'),
(10,5,'新增','sys:role:add','','','','2','',1,'','角色管理'),
(11,5,'编辑','sys:role:edit','','','','2','',2,'','角色管理'),
(12,5,'删除','sys:role:delete','','','','2','',3,'','角色管理'),
(13,6,'新增','sys:menu:add','','','','2','',1,'','权限管理'),
(14,6,'编辑','sys:menu:edit','','','','2','',2,'','权限管理'),
(15,6,'删除','sys:menu:delete','','','','2','',3,'','权限管理'),
(16,0,'房屋管理','sys:home:index','','/sysHouse','','0','el-icon-s-help',2,'','顶级菜单'),
(17,16,'房屋列表','sys:house:list','sysHouseList','/sysHouseList','/house/houseList','1','el-icon-mobile',3,'','房屋管理'),
(18,17,'新增','sys:house:add','','','','2','',1,'','房屋列表'),
(19,17,'编辑','sys:house:edit','','','','2','',2,'','房屋列表'),
(20,17,'删除','sys:house:delete','','','','2','',3,'','房屋列表'),
(21,16,'单元管理','sys:houseUnit','houseUnit','/houseUnit','/house/houseUnit','1','table',2,'','房屋管理'),
(22,16,'栋数管理','sys:houseBuilding','houseBuilding','/houseBuilding','/house/houseBuilding','1','table',1,'','房屋管理'),
(23,22,'新增','sys:houseBuilding:add','','','','2','',1,'','栋数管理'),
(24,22,'编辑','sys:houseBuilding:edit','','','','2','',2,'','栋数管理'),
(25,22,'删除','sys:houseBuilding:delete','','','','2','',3,'','栋数管理'),
(26,21,'新增','sys:houseUnit:add','','','','2','',1,'','单元管理'),
(27,21,'编辑','sys:houseUnit:edit','','','','2','',2,'','单元管理'),
(28,21,'删除','sys:houseUnit:delete','','','','2','',3,'','单元管理'),
(29,0,'车位管理','sys:sysPark','','/sysPark','','0','el-icon-money',3,'','顶级菜单'),
(30,29,'车位管理','sys:parkList','parkList','/parkList','/park/parkList','1','el-icon-money',1,'','车位管理'),
(31,30,'新增','sys:parkList:add','','','','2','',1,'','车位管理'),
(32,30,'编辑','sys:parkList:edit','','','','2','',2,'','车位管理'),
(33,30,'删除','sys:parkList:delete','','','','2','',3,'','车位管理'),
(34,0,'业主管理','sys:live','','/live','','0','el-icon-s-grid',4,'','顶级菜单'),
(35,34,'业主列表','sys:liveUser','liveUser','/liveUser','/live/liveUser','1','el-icon-s-data',1,'','业主管理'),
(36,35,'新增','sys:liveUser:add','','','','2','',1,'','业主列表'),
(37,35,'编辑','sys:liveUser:edit','','','','2','',2,'','业主列表'),
(38,35,'删除','sys:liveUser:delete','','','','2','',3,'','业主列表'),
(39,35,'分配房屋','sys:liveUser:assignHome','','','','2','',4,'','业主列表'),
(40,35,'分配车位','sys:liveUser:assignCar','','','','2','',6,'','业主列表'),
(41,35,'退房','sys:liveUser:returnHome','','','','2','',7,'','业主列表'),
(42,35,'退车位','sys:liveUser:returnCar','','','','2','',8,'','业主列表'),
(43,0,'收费管理','sys:fee','','/fee','','0','el-icon-s-open',5,'','顶级菜单'),
(44,43,'电费管理','sys:feePower','feePower','/feePower','/fee/feePower','1','el-icon-picture',1,'','收费管理'),
(45,43,'水费管理','sys:feeWater','feeWater','/feeWater','/fee/feeWater','1','el-icon-s-data',2,'','收费管理'),
(46,43,'停车管理','sys:feePark','feePark','/feePark','/fee/feePark','1','el-icon-s-order',3,'','收费管理'),
(47,44,'新增','sys:feePower:add','','','','2','',1,'','电费管理'),
(48,44,'编辑','sys:feePower:edit','','','','2','',2,'','电费管理'),
(49,44,'删除','sys:feePower:delete','','','','2','',3,'','电费管理'),
(50,45,'新增','sys:feeWater:add','','','','2','',1,'','水费管理'),
(51,45,'编辑','sys:feeWater:edit','','','','2','',2,'','水费管理'),
(52,45,'删除','sys:feeWater:delete','','','','2','',3,'','水费管理'),
(53,46,'新增','sys:feePark:add','','','','2','',1,'','停车管理'),
(54,46,'编辑','sys:feePark:edit','','','','2','',2,'','停车管理'),
(55,46,'删除','sys:feePark:delete','','','','2','',3,'','停车管理'),
(56,0,'投诉管理','sys:userComplaint','','/userComplaint','','0','el-icon-date',6,'','顶级菜单'),
(57,56,'投诉列表','sys:userComplaintList','userComplaintList','/userComplaintList','/userComplaint/userComplaint','1','el-icon-edit-outline',1,'','投诉管理'),
(58,56,'我的投诉','sys:myUserComplaint','myUserComplaint','/myUserComplaint','/userComplaint/myUserComplaint','1','el-icon-menu',2,'','投诉管理'),
(62,58,'新增','sys:myUserComplaint:add','','','','2','',1,'','我的投诉'),
(63,58,'编辑','sys:myUserComplaint:edit','','','','2','',2,'','我的投诉'),
(64,58,'删除','sys:myUserComplaint:delete','','','','2','',3,'','我的投诉'),
(65,0,'维修管理','sys:repairModel','','/repairModel','','0','el-icon-picture-outline',7,'','顶级菜单'),
(66,65,'维修列表','sys:repairList','repairList','/repairList','/repair/repairList','1','el-icon-s-marketing',1,'','维修管理'),
(67,65,'我的维修','sys:myRepair','myRepair','/myRepair','/repair/myRepair','1','el-icon-picture-outline',2,'','维修管理'),
(68,67,'新增','sys:myRepair:add','','','','2','',1,'','我的维修'),
(69,67,'编辑','sys:myRepair:edit','','','','2','',2,'','我的维修'),
(70,67,'删除','sys:myRepair:delete','','','','2','',3,'','我的维修'),
(71,0,'公告管理','sys:notice','','/notice','','0','el-icon-document-copy',8,'','顶级菜单'),
(72,71,'公告列表','sys:noticeList','noticeList','/noticeList','/notice/noticeList','1','el-icon-s-marketing',1,'','公告管理'),
(73,72,'新增','sys:noticeList:add','','','','2','',1,'','公告列表'),
(74,72,'编辑','sys:noticeList:edit','','','','2','',2,'','公告列表'),
(75,72,'删除','sys:noticeList:delete','','','','2','',3,'','公告列表'),
(76,72,'查看','sys:notice:look','','','','2','',4,'','公告列表'),
(77,4,'分配角色','sys:user:assignRole','','','','2','',5,'','员工管理'),
(78,5,'分配权限','sys:role:assignMenu','','','','2','',4,'','角色管理'),
(79,66,'处理','sys:repairList:do','','','','2','',1,'','维修列表'),
(80,57,'处理','sys:myUserComplaint:do','','','','2','',1,'','投诉列表'),
(81,44,'缴费','sys:feePower:pay','','','','2','',4,'','电费管理'),
(82,46,'缴费','sys:feePark:pay','','','','2','',4,'','停车管理'),
(83,45,'缴费','sys:feeWater:pay','','','','2','',4,'','水费管理'),
(84,0,'缴费记录','sys:feeList','','/feeList','','0','el-icon-c-scale-to-original',6,'','顶级菜单'),
(85,84,'我的电费','sys:myPowerFee','myPowerFee','/myPowerFee','/feeList/myPowerFee','1','el-icon-date',1,'','缴费记录'),
(86,84,'我的水费','sys:myWaterFee','myWaterFee','/myWaterFee','/feeList/myWaterFee','1','el-icon-s-shop',2,'','缴费记录'),
(87,84,'我的停车费','sys:myParkFee','myParkFee','/myParkFee','/feeList/myParkFee','1','el-icon-s-finance',3,'','缴费记录');

/*Table structure for table `sys_notice` */

DROP TABLE IF EXISTS `sys_notice`;

CREATE TABLE `sys_notice` (
  `notice_id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int DEFAULT NULL COMMENT '用户id',
  `title` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '标题',
  `notice_content` text CHARACTER SET utf8 COLLATE utf8_bin COMMENT '内容',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`notice_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

/*Data for the table `sys_notice` */

insert  into `sys_notice`(`notice_id`,`user_id`,`title`,`notice_content`,`create_time`) values 
(4,NULL,'大家好','欢迎来到本小区居住','2022-01-25 20:29:50');

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `role_id` int NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_name` varchar(56) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(56) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

/*Data for the table `sys_role` */

insert  into `sys_role`(`role_id`,`role_name`,`remark`) values 
(1,'管理员','管理权限'),
(15,'root','次级管理员'),
(16,'收费管理员',''),
(17,'业主','');

/*Table structure for table `sys_role_menu` */

DROP TABLE IF EXISTS `sys_role_menu`;

CREATE TABLE `sys_role_menu` (
  `role_menu_id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` int DEFAULT NULL COMMENT '角色id',
  `menu_id` int DEFAULT NULL COMMENT '菜单id',
  PRIMARY KEY (`role_menu_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1039 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

/*Data for the table `sys_role_menu` */

insert  into `sys_role_menu`(`role_menu_id`,`role_id`,`menu_id`) values 
(28,5,13),
(29,5,14),
(30,5,6),
(31,5,15),
(32,5,16),
(33,5,17),
(34,5,1),
(35,5,5),
(321,8,8),
(322,8,9),
(323,8,76),
(324,8,3),
(325,8,4),
(326,8,71),
(327,8,72),
(328,9,76),
(329,9,71),
(330,9,72),
(502,10,58),
(503,10,62),
(504,10,63),
(505,10,64),
(506,10,67),
(507,10,68),
(508,10,69),
(509,10,70),
(510,10,76),
(511,10,84),
(512,10,85),
(513,10,86),
(514,10,87),
(515,10,56),
(516,10,65),
(517,10,71),
(518,10,72),
(519,11,4),
(520,11,7),
(521,11,8),
(522,11,9),
(523,11,77),
(524,11,5),
(525,11,10),
(526,11,11),
(527,11,12),
(528,11,78),
(529,11,16),
(530,11,17),
(531,11,18),
(532,11,19),
(533,11,20),
(534,11,21),
(535,11,26),
(536,11,27),
(537,11,28),
(538,11,22),
(539,11,23),
(540,11,24),
(541,11,25),
(542,11,29),
(543,11,30),
(544,11,31),
(545,11,32),
(546,11,33),
(547,11,34),
(548,11,35),
(549,11,36),
(550,11,37),
(551,11,38),
(552,11,39),
(553,11,40),
(554,11,41),
(555,11,42),
(556,11,43),
(557,11,44),
(558,11,47),
(559,11,48),
(560,11,49),
(561,11,81),
(562,11,45),
(563,11,50),
(564,11,51),
(565,11,52),
(566,11,83),
(567,11,46),
(568,11,53),
(569,11,54),
(570,11,55),
(571,11,82),
(572,11,56),
(573,11,57),
(574,11,80),
(575,11,58),
(576,11,62),
(577,11,63),
(578,11,64),
(579,11,65),
(580,11,66),
(581,11,79),
(582,11,67),
(583,11,68),
(584,11,69),
(585,11,70),
(586,11,71),
(587,11,72),
(588,11,73),
(589,11,74),
(590,11,75),
(591,11,76),
(592,11,84),
(593,11,85),
(594,11,86),
(595,11,87),
(596,11,3),
(597,12,4),
(598,12,7),
(599,12,8),
(600,12,9),
(601,12,77),
(602,12,5),
(603,12,10),
(604,12,11),
(605,12,12),
(606,12,78),
(607,12,16),
(608,12,17),
(609,12,18),
(610,12,19),
(611,12,20),
(612,12,21),
(613,12,26),
(614,12,27),
(615,12,28),
(616,12,22),
(617,12,23),
(618,12,24),
(619,12,25),
(620,12,29),
(621,12,30),
(622,12,31),
(623,12,32),
(624,12,33),
(625,12,34),
(626,12,35),
(627,12,36),
(628,12,37),
(629,12,38),
(630,12,39),
(631,12,40),
(632,12,41),
(633,12,42),
(634,12,43),
(635,12,44),
(636,12,47),
(637,12,48),
(638,12,49),
(639,12,81),
(640,12,45),
(641,12,50),
(642,12,51),
(643,12,52),
(644,12,83),
(645,12,46),
(646,12,53),
(647,12,54),
(648,12,55),
(649,12,82),
(650,12,56),
(651,12,57),
(652,12,80),
(653,12,58),
(654,12,62),
(655,12,63),
(656,12,64),
(657,12,65),
(658,12,66),
(659,12,79),
(660,12,67),
(661,12,68),
(662,12,69),
(663,12,70),
(664,12,71),
(665,12,72),
(666,12,73),
(667,12,74),
(668,12,75),
(669,12,76),
(670,12,84),
(671,12,85),
(672,12,86),
(673,12,87),
(674,12,3),
(675,13,43),
(676,13,44),
(677,13,47),
(678,13,48),
(679,13,49),
(680,13,81),
(681,13,45),
(682,13,50),
(683,13,51),
(684,13,52),
(685,13,83),
(686,13,46),
(687,13,53),
(688,13,54),
(689,13,55),
(690,13,82),
(691,1,3),
(692,1,4),
(693,1,7),
(694,1,8),
(695,1,9),
(696,1,77),
(697,1,5),
(698,1,10),
(699,1,11),
(700,1,12),
(701,1,78),
(702,1,6),
(703,1,13),
(704,1,14),
(705,1,15),
(706,1,16),
(707,1,17),
(708,1,18),
(709,1,19),
(710,1,20),
(711,1,21),
(712,1,26),
(713,1,27),
(714,1,28),
(715,1,22),
(716,1,23),
(717,1,24),
(718,1,25),
(719,1,29),
(720,1,30),
(721,1,31),
(722,1,32),
(723,1,33),
(724,1,34),
(725,1,35),
(726,1,36),
(727,1,37),
(728,1,38),
(729,1,39),
(730,1,40),
(731,1,41),
(732,1,42),
(733,1,43),
(734,1,44),
(735,1,47),
(736,1,48),
(737,1,49),
(738,1,81),
(739,1,45),
(740,1,50),
(741,1,51),
(742,1,52),
(743,1,83),
(744,1,46),
(745,1,53),
(746,1,54),
(747,1,55),
(748,1,82),
(749,1,56),
(750,1,57),
(751,1,80),
(752,1,58),
(753,1,62),
(754,1,63),
(755,1,64),
(756,1,65),
(757,1,66),
(758,1,79),
(759,1,67),
(760,1,68),
(761,1,69),
(762,1,70),
(763,1,71),
(764,1,72),
(765,1,73),
(766,1,74),
(767,1,75),
(768,1,76),
(769,1,84),
(770,1,85),
(771,1,86),
(772,1,87),
(851,16,43),
(852,16,44),
(853,16,47),
(854,16,48),
(855,16,49),
(856,16,81),
(857,16,45),
(858,16,50),
(859,16,51),
(860,16,52),
(861,16,83),
(862,16,46),
(863,16,53),
(864,16,54),
(865,16,55),
(866,16,82),
(867,17,58),
(868,17,62),
(869,17,63),
(870,17,64),
(871,17,67),
(872,17,68),
(873,17,69),
(874,17,70),
(875,17,76),
(876,17,84),
(877,17,85),
(878,17,86),
(879,17,87),
(880,17,56),
(881,17,65),
(882,17,71),
(883,17,72),
(961,15,4),
(962,15,7),
(963,15,8),
(964,15,9),
(965,15,77),
(966,15,5),
(967,15,10),
(968,15,11),
(969,15,12),
(970,15,78),
(971,15,16),
(972,15,17),
(973,15,18),
(974,15,19),
(975,15,20),
(976,15,21),
(977,15,26),
(978,15,27),
(979,15,28),
(980,15,22),
(981,15,23),
(982,15,24),
(983,15,25),
(984,15,29),
(985,15,30),
(986,15,31),
(987,15,32),
(988,15,33),
(989,15,34),
(990,15,35),
(991,15,36),
(992,15,37),
(993,15,38),
(994,15,39),
(995,15,40),
(996,15,41),
(997,15,42),
(998,15,43),
(999,15,44),
(1000,15,47),
(1001,15,48),
(1002,15,49),
(1003,15,81),
(1004,15,45),
(1005,15,50),
(1006,15,51),
(1007,15,52),
(1008,15,83),
(1009,15,46),
(1010,15,53),
(1011,15,54),
(1012,15,55),
(1013,15,82),
(1014,15,56),
(1015,15,57),
(1016,15,80),
(1017,15,58),
(1018,15,62),
(1019,15,63),
(1020,15,64),
(1021,15,65),
(1022,15,66),
(1023,15,79),
(1024,15,67),
(1025,15,68),
(1026,15,69),
(1027,15,70),
(1028,15,71),
(1029,15,72),
(1030,15,73),
(1031,15,74),
(1032,15,75),
(1033,15,76),
(1034,15,84),
(1035,15,85),
(1036,15,86),
(1037,15,87),
(1038,15,3);

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `user_id` int NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `login_name` varchar(36) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '姓名',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '登录密码',
  `username` varchar(36) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '登录账户',
  `phone` varchar(16) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '电话',
  `sex` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '性别 0：女 1：男',
  `id_card` varchar(36) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '身份证号码',
  `is_admin` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '是否是管理员 0：不是 1：是',
  `status` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '0：在职  1：离职',
  `is_used` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '0：启用 1：禁用',
  `is_account_non_expired` int DEFAULT NULL COMMENT ' 帐户是否过期(1 未过期，0已过期)',
  `is_account_non_locked` int DEFAULT NULL COMMENT '帐户是否被锁定(1 未锁定，0锁定)	',
  `is_credentials_non_expired` int DEFAULT NULL COMMENT '密码是否过期(1 未过期，0已过期)',
  `is_enabled` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '帐户是否可用(1 可用，0 删除用户)		',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

/*Data for the table `sys_user` */

insert  into `sys_user`(`user_id`,`login_name`,`password`,`username`,`phone`,`sex`,`id_card`,`is_admin`,`status`,`is_used`,`is_account_non_expired`,`is_account_non_locked`,`is_credentials_non_expired`,`is_enabled`) values 
(17,'admin','$2a$10$h47khzr1VpMQcUWlKtrNMuyRIKq/TJfah0e3bwZSuhTJI2QySxlqy','admin','18922468764','1','445222200004201','1','0','0',1,1,1,'1'),
(30,'zhangsan','$2a$10$h47khzr1VpMQcUWlKtrNMuyRIKq/TJfah0e3bwZSuhTJI2QySxlqy','root','17874821631','1','445222200003945',NULL,'0','1',1,1,1,'1');

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `user_role_id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` int DEFAULT NULL COMMENT '角色id',
  `user_id` int DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`user_role_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`user_role_id`,`role_id`,`user_id`) values 
(1,1,17),
(18,15,30);

/*Table structure for table `user_complaint` */

DROP TABLE IF EXISTS `user_complaint`;

CREATE TABLE `user_complaint` (
  `complaint_id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int DEFAULT NULL COMMENT '业主Id',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '标题',
  `complaint_content` text CHARACTER SET utf8 COLLATE utf8_bin COMMENT '投诉内容',
  `create_time` datetime DEFAULT NULL COMMENT '投诉时间',
  `status` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '处理状态 0：未处理 1：已处理',
  PRIMARY KEY (`complaint_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

/*Data for the table `user_complaint` */

insert  into `user_complaint`(`complaint_id`,`user_id`,`title`,`complaint_content`,`create_time`,`status`) values 
(8,17,'垃圾处理','楼下垃圾桶垃圾太多未及时处理','2022-01-25 20:28:43','1'),
(9,34,'电梯','电梯太小了，人太多了','2022-05-26 17:48:57','0');

/*Table structure for table `user_repair` */

DROP TABLE IF EXISTS `user_repair`;

CREATE TABLE `user_repair` (
  `repair_id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int DEFAULT NULL COMMENT '业主id',
  `phone` varchar(13) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '联系电话',
  `repair_address` tinytext CHARACTER SET utf8 COLLATE utf8_bin COMMENT '维修地址',
  `repair_content` text CHARACTER SET utf8 COLLATE utf8_bin COMMENT '维修内容',
  `commit_time` datetime DEFAULT NULL COMMENT '报修时间',
  `status` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '维修状态 0：未维修 1：已维修',
  PRIMARY KEY (`repair_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

/*Data for the table `user_repair` */

insert  into `user_repair`(`repair_id`,`user_id`,`phone`,`repair_address`,`repair_content`,`commit_time`,`status`) values 
(8,17,'17836154384','E栋','漏水','2022-01-25 20:29:20','1');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
