/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50724
Source Host           : localhost:3306
Source Database       : ctcms

Target Server Type    : MYSQL
Target Server Version : 50724
File Encoding         : 65001

Date: 2019-03-10 22:43:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `account`
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nickName` varchar(50) NOT NULL,
  `userName` varchar(150) NOT NULL,
  `password` varchar(150) NOT NULL,
  `salt` varchar(150) NOT NULL,
  `status` int(11) NOT NULL,
  `createAt` datetime NOT NULL,
  `ip` varchar(100) DEFAULT NULL,
  `avatar` varchar(100) NOT NULL,
  `likeCount` int(11) NOT NULL DEFAULT '0' COMMENT '被赞次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES ('1', '系统管理员', 'test@test.com', 'a1f0917284a75c2c45dfeefd9040ce01144407c1a33d1bc3c45153ceb9d12d72', 'zmxyyZJkE-N6JjRhujp6U8l4Yu7vuQDZ', '1', '2018-04-18 09:00:19', '175.12.244.105', '0/1.jpg', '999');
INSERT INTO `account` VALUES ('2', '伽利略', 'test1@test.com', 'a1f0917284a75c2c45dfeefd9040ce01144407c1a33d1bc3c45153ceb9d12d72', 'zmxyyZJkE-N6JjRhujp6U8l4Yu7vuQDZ', '1', '2018-04-19 10:19:11', '175.12.244.105', '0/1.jpg', '0');
INSERT INTO `account` VALUES ('3', '牛顿', 'test2@test.com', 'a1f0917284a75c2c45dfeefd9040ce01144407c1a33d1bc3c45153ceb9d12d72', 'zmxyyZJkE-N6JjRhujp6U8l4Yu7vuQDZ', '1', '2018-04-20 10:05:08', '175.12.244.105', '0/1.jpg', '0');
INSERT INTO `account` VALUES ('4', '爱因斯坦', 'test3@test.com', 'a1f0917284a75c2c45dfeefd9040ce01144407c1a33d1bc3c45153ceb9d12d72', 'zmxyyZJkE-N6JjRhujp6U8l4Yu7vuQDZ', '1', '2018-04-21 12:20:15', '175.12.244.105', '0/1.jpg', '0');
INSERT INTO `account` VALUES ('5', '银河系', 'test4@test.com', 'a1f0917284a75c2c45dfeefd9040ce01144407c1a33d1bc3c45153ceb9d12d72', 'zmxyyZJkE-N6JjRhujp6U8l4Yu7vuQDZ', '-1', '2018-04-22 09:20:18', '175.12.244.105', '0/1.jpg', '0');
INSERT INTO `account` VALUES ('6', '极速开发', 'test5@test.com', 'a1f0917284a75c2c45dfeefd9040ce01144407c1a33d1bc3c45153ceb9d12d72', 'zmxyyZJkE-N6JjRhujp6U8l4Yu7vuQDZ', '1', '2018-04-23 10:20:35', '175.12.244.105', '0/1.jpg', '0');
INSERT INTO `account` VALUES ('7', 'enjoy', 'test6@test.com', 'a1f0917284a75c2c45dfeefd9040ce01144407c1a33d1bc3c45153ceb9d12d72', 'zmxyyZJkE-N6JjRhujp6U8l4Yu7vuQDZ', '1', '2018-04-23 11:20:31', '175.12.244.105', '0/1.jpg', '0');
INSERT INTO `account` VALUES ('8', 'jfinal', 'test7@test.com', 'a1f0917284a75c2c45dfeefd9040ce01144407c1a33d1bc3c45153ceb9d12d72', 'zmxyyZJkE-N6JjRhujp6U8l4Yu7vuQDZ', '1', '2018-04-24 12:20:53', '175.12.244.105', '0/1.jpg', '0');
INSERT INTO `account` VALUES ('9', '俱乐部第一美女', 'test8@test.com1', 'a1f0917284a75c2c45dfeefd9040ce01144407c1a33d1bc3c45153ceb9d12d72', 'zmxyyZJkE-N6JjRhujp6U8l4Yu7vuQDZ', '1', '2018-04-25 09:58:19', '175.12.244.105', '0/2.jpg', '0');

-- ----------------------------
-- Table structure for `account_role`
-- ----------------------------
DROP TABLE IF EXISTS `account_role`;
CREATE TABLE `account_role` (
  `accountId` int(11) NOT NULL,
  `roleId` int(11) NOT NULL,
  PRIMARY KEY (`accountId`,`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account_role
-- ----------------------------
INSERT INTO `account_role` VALUES ('1', '1');
INSERT INTO `account_role` VALUES ('4', '4');
INSERT INTO `account_role` VALUES ('6', '3');
INSERT INTO `account_role` VALUES ('7', '2');
INSERT INTO `account_role` VALUES ('8', '1');
INSERT INTO `account_role` VALUES ('9', '4');
INSERT INTO `account_role` VALUES ('9', '5');
INSERT INTO `account_role` VALUES ('9', '6');
INSERT INTO `account_role` VALUES ('9', '7');

-- ----------------------------
-- Table structure for `download_log`
-- ----------------------------
DROP TABLE IF EXISTS `download_log`;
CREATE TABLE `download_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `accountId` int(11) NOT NULL,
  `ip` varchar(100) DEFAULT NULL,
  `fileName` varchar(200) NOT NULL,
  `downloadDate` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of download_log
-- ----------------------------

-- ----------------------------
-- Table structure for `login_log`
-- ----------------------------
DROP TABLE IF EXISTS `login_log`;
CREATE TABLE `login_log` (
  `accountId` int(11) NOT NULL,
  `loginAt` datetime NOT NULL,
  `ip` varchar(100) DEFAULT NULL,
  KEY `accountId_index` (`accountId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of login_log
-- ----------------------------
INSERT INTO `login_log` VALUES ('1', '2018-09-01 17:10:08', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES ('1', '2019-03-06 22:36:49', '127.0.0.1');
INSERT INTO `login_log` VALUES ('1', '2019-03-06 22:37:20', '127.0.0.1');
INSERT INTO `login_log` VALUES ('1', '2019-03-06 22:37:35', '127.0.0.1');
INSERT INTO `login_log` VALUES ('1', '2019-03-07 21:47:28', '127.0.0.1');
INSERT INTO `login_log` VALUES ('1', '2019-03-07 21:47:38', '127.0.0.1');
INSERT INTO `login_log` VALUES ('1', '2019-03-07 21:57:30', '127.0.0.1');
INSERT INTO `login_log` VALUES ('1', '2019-03-08 21:12:29', '127.0.0.1');
INSERT INTO `login_log` VALUES ('1', '2019-03-10 19:27:06', '127.0.0.1');
INSERT INTO `login_log` VALUES ('1', '2019-03-10 19:27:16', '127.0.0.1');
INSERT INTO `login_log` VALUES ('1', '2019-03-10 19:28:26', '127.0.0.1');
INSERT INTO `login_log` VALUES ('1', '2019-03-10 19:28:54', '127.0.0.1');
INSERT INTO `login_log` VALUES ('1', '2019-03-10 19:29:19', '127.0.0.1');
INSERT INTO `login_log` VALUES ('1', '2019-03-10 22:07:09', '127.0.0.1');
INSERT INTO `login_log` VALUES ('1', '2019-03-10 22:11:06', '127.0.0.1');

-- ----------------------------
-- Table structure for `permission`
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `actionKey` varchar(512) NOT NULL DEFAULT '',
  `controller` varchar(512) NOT NULL DEFAULT '',
  `remark` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', '/admin', 'live.autu.ctcms._admin.index.IndexAdminController', null);
INSERT INTO `permission` VALUES ('2', '/admin/account', 'live.autu.ctcms._admin.account.AccountAdminController', null);
INSERT INTO `permission` VALUES ('3', '/admin/account/addRole', 'live.autu.ctcms._admin.account.AccountAdminController', null);
INSERT INTO `permission` VALUES ('4', '/admin/account/assignRoles', 'live.autu.ctcms._admin.account.AccountAdminController', null);
INSERT INTO `permission` VALUES ('5', '/admin/account/avatar', 'live.autu.ctcms._admin.account.AccountAdminController', null);
INSERT INTO `permission` VALUES ('6', '/admin/account/deleteRole', 'live.autu.ctcms._admin.account.AccountAdminController', null);
INSERT INTO `permission` VALUES ('7', '/admin/account/edit', 'live.autu.ctcms._admin.account.AccountAdminController', null);
INSERT INTO `permission` VALUES ('8', '/admin/account/lock', 'live.autu.ctcms._admin.account.AccountAdminController', null);
INSERT INTO `permission` VALUES ('9', '/admin/account/saveAvatar', 'live.autu.ctcms._admin.account.AccountAdminController', null);
INSERT INTO `permission` VALUES ('10', '/admin/account/showAdminList', 'live.autu.ctcms._admin.account.AccountAdminController', null);
INSERT INTO `permission` VALUES ('11', '/admin/account/unlock', 'live.autu.ctcms._admin.account.AccountAdminController', null);
INSERT INTO `permission` VALUES ('12', '/admin/account/update', 'live.autu.ctcms._admin.account.AccountAdminController', null);
INSERT INTO `permission` VALUES ('13', '/admin/account/uploadAvatar', 'live.autu.ctcms._admin.account.AccountAdminController', null);
INSERT INTO `permission` VALUES ('14', '/admin/permission', 'live.autu.ctcms._admin.permission.PermissionAdminController', null);
INSERT INTO `permission` VALUES ('15', '/admin/permission/delete', 'live.autu.ctcms._admin.permission.PermissionAdminController', null);
INSERT INTO `permission` VALUES ('16', '/admin/permission/edit', 'live.autu.ctcms._admin.permission.PermissionAdminController', null);
INSERT INTO `permission` VALUES ('17', '/admin/permission/sync', 'live.autu.ctcms._admin.permission.PermissionAdminController', null);
INSERT INTO `permission` VALUES ('18', '/admin/permission/update', 'live.autu.ctcms._admin.permission.PermissionAdminController', null);
INSERT INTO `permission` VALUES ('19', '/admin/role', 'live.autu.ctcms._admin.role.RoleAdminController', null);
INSERT INTO `permission` VALUES ('20', '/admin/role/add', 'live.autu.ctcms._admin.role.RoleAdminController', null);
INSERT INTO `permission` VALUES ('21', '/admin/role/addPermission', 'live.autu.ctcms._admin.role.RoleAdminController', null);
INSERT INTO `permission` VALUES ('22', '/admin/role/assignPermissions', 'live.autu.ctcms._admin.role.RoleAdminController', null);
INSERT INTO `permission` VALUES ('23', '/admin/role/delete', 'live.autu.ctcms._admin.role.RoleAdminController', null);
INSERT INTO `permission` VALUES ('24', '/admin/role/deletePermission', 'live.autu.ctcms._admin.role.RoleAdminController', null);
INSERT INTO `permission` VALUES ('25', '/admin/role/edit', 'live.autu.ctcms._admin.role.RoleAdminController', null);
INSERT INTO `permission` VALUES ('26', '/admin/role/save', 'live.autu.ctcms._admin.role.RoleAdminController', null);
INSERT INTO `permission` VALUES ('27', '/admin/role/update', 'live.autu.ctcms._admin.role.RoleAdminController', null);

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL DEFAULT '',
  `createAt` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '超级管理员', '2018-03-19 09:58:19');
INSERT INTO `role` VALUES ('2', 'CEO', '2018-04-27 22:37:18');
INSERT INTO `role` VALUES ('3', 'CTO', '2018-04-27 22:37:25');
INSERT INTO `role` VALUES ('4', '项目经理', '2018-04-27 22:37:44');
INSERT INTO `role` VALUES ('5', '小编', '2018-04-27 22:37:59');
INSERT INTO `role` VALUES ('6', 'new', '2018-05-19 00:16:36');
INSERT INTO `role` VALUES ('7', 'test', '2018-05-23 21:32:07');
INSERT INTO `role` VALUES ('8', '新加个角色', '2018-06-12 17:55:09');

-- ----------------------------
-- Table structure for `role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `roleId` int(11) NOT NULL,
  `permissionId` int(11) NOT NULL,
  PRIMARY KEY (`roleId`,`permissionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_permission
-- ----------------------------

-- ----------------------------
-- Table structure for `session`
-- ----------------------------
DROP TABLE IF EXISTS `session`;
CREATE TABLE `session` (
  `id` varchar(33) NOT NULL,
  `accountId` int(11) NOT NULL,
  `expireAt` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of session
-- ----------------------------
INSERT INTO `session` VALUES ('185e2d0fe4b24c18a3e9a9195014299a', '1', '1624459338971');
INSERT INTO `session` VALUES ('23b38c1956cf4554b6642862ae818f07', '1', '1646574457675');
INSERT INTO `session` VALUES ('5930dc89ce2146bbb8d399a0ede3df0c', '1', '1646825236289');
INSERT INTO `session` VALUES ('5b8e2a2109a9449a8fe660a9ab1b69aa', '1', '1621269626427');
INSERT INTO `session` VALUES ('5fe04df586aa498b8305e42252b68f54', '1', '1619454238258');
INSERT INTO `session` VALUES ('6d6bb97c9bc64a409ee56cdd8be6404e', '1', '1623645425909');
INSERT INTO `session` VALUES ('9ecce60a582a422d8aa214308d962fcb', '1', '1630401008373');
INSERT INTO `session` VALUES ('9f6b61963d244520b32284fddcdddea6', '1', '1646491008978');

-- ----------------------------
-- Table structure for `upload_counter`
-- ----------------------------
DROP TABLE IF EXISTS `upload_counter`;
CREATE TABLE `upload_counter` (
  `uploadType` varchar(50) NOT NULL,
  `counter` int(11) NOT NULL,
  `descr` varchar(50) NOT NULL,
  PRIMARY KEY (`uploadType`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of upload_counter
-- ----------------------------
INSERT INTO `upload_counter` VALUES ('club', '0', '记录club模块上传图片的总数量，用于生成相对路径');
INSERT INTO `upload_counter` VALUES ('document', '0', '记录document模块上传图片的总数量，用于生成相对路径');
INSERT INTO `upload_counter` VALUES ('feedback', '313', '记录feedback模块上传图片的总数量，用于生成相对路径');
INSERT INTO `upload_counter` VALUES ('project', '72', '记录project模块上传图片的总数量，用于生成相对路径');
INSERT INTO `upload_counter` VALUES ('share', '197', '记录share模块上传图片的总数量，用于生成相对路径');

-- ----------------------------
-- Table structure for `video`
-- ----------------------------
DROP TABLE IF EXISTS `video`;
CREATE TABLE `video` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fileName` varchar(280) NOT NULL,
  `descr` varchar(280) NOT NULL COMMENT '描述',
  `fileType` varchar(20) NOT NULL COMMENT '文件类型',
  `size` varchar(20) NOT NULL,
  `createDate` date NOT NULL,
  `path` varchar(280) NOT NULL,
  `downloadCount` int(11) NOT NULL,
  `isShow` int(11) NOT NULL DEFAULT '0',
  `preview` varchar(255) DEFAULT NULL COMMENT '预览图',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of video
-- ----------------------------
INSERT INTO `video` VALUES ('42', 'jfinal-3.4&#160;manual.pdf', 'JFinal 手册', 'PDF', '1.39 MB', '2016-01-19', '/download/3.4/', '140252', '1', null);
INSERT INTO `video` VALUES ('43', 'jfinal-3.4&#160;all.zip', 'JFinal 3.4 all', 'ZIP', '20.26 MB', '2016-01-19', '/download/3.4/', '127351', '1', null);
INSERT INTO `video` VALUES ('44', 'jfinal-3.4_demo.zip', 'JFinal demo', 'ZIP', '5.91 MB', '2016-01-19', '/download/3.4/', '123110', '1', null);
INSERT INTO `video` VALUES ('45', 'GeneratorDemo.java', 'Generator demo', 'Java', '2 KB', '2016-01-19', '/download/3.4/', '110699', '1', null);
INSERT INTO `video` VALUES ('46', 'jfinal-weixin-1.7-bin-with-src.jar', 'JFinal weixin 1.7', 'JAR', '258 KB', '2016-01-12', '/download/3.4/', '11633', '0', null);
INSERT INTO `video` VALUES ('47', 'jfinal-weixin-1.8-bin-with-src.jar', 'JFinal Weixin 1.8', 'JAR', '279 KB', '2016-07-11', '/download/3.4/', '13503', '1', null);
INSERT INTO `video` VALUES ('48', 'jfinal-weixin-1.8-lib.zip', 'JFinal Weixin 1.8 lib', 'ZIP', '4.31 MB', '2016-07-11', '/download/3.4/', '11312', '1', null);
INSERT INTO `video` VALUES ('49', 'jfinal-3.4-changelog.txt', 'JFinal changelog', 'TXT', '6 KB', '2016-01-19', '/download/3.4/', '15590', '1', null);
