/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50530
Source Host           : localhost:3306
Source Database       : cmatrix

Target Server Type    : MYSQL
Target Server Version : 50530
File Encoding         : 65001

Date: 2014-08-06 10:15:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `cm_entry`
-- ----------------------------
DROP TABLE IF EXISTS `cm_entry`;
CREATE TABLE `cm_entry` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `creation_time` datetime DEFAULT NULL,
  `dest_port` varchar(10) DEFAULT NULL,
  `dest_system` varchar(10) DEFAULT NULL,
  `owner` varchar(10) DEFAULT NULL,
  `project` varchar(10) DEFAULT NULL,
  `source_port` varchar(10) DEFAULT NULL,
  `source_system` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cm_entry
-- ----------------------------
INSERT INTO `cm_entry` VALUES ('1', null, '8080', 'AAA OAM', 'x36zhao', 'EP2', 'any', 'WAS');
INSERT INTO `cm_entry` VALUES ('2', null, '8080', 'AAA OAM', 'x36zhao', 'EP2', 'any', 'WAS');
INSERT INTO `cm_entry` VALUES ('3', null, '8080', 'AAA OAM', 'x36zhao', 'EP2', 'any', 'WAS');
INSERT INTO `cm_entry` VALUES ('4', null, '8080', 'AAA OAM', 'x36zhao', 'EP2', 'any', 'WAS');

-- ----------------------------
-- Table structure for `cm_member`
-- ----------------------------
DROP TABLE IF EXISTS `cm_member`;
CREATE TABLE `cm_member` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `user_account` varchar(10) NOT NULL,
  `user_name` varchar(10) DEFAULT NULL,
  `user_mail` varchar(20) NOT NULL,
  `user_status` tinyint(4) DEFAULT NULL,
  `user_logintime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cm_member
-- ----------------------------

-- ----------------------------
-- Table structure for `cm_project`
-- ----------------------------
DROP TABLE IF EXISTS `cm_project`;
CREATE TABLE `cm_project` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_name` varchar(10) NOT NULL,
  `project_desc` text,
  `project_status` tinyint(4) DEFAULT NULL,
  `creation_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cm_project
-- ----------------------------
INSERT INTO `cm_project` VALUES ('1', 'NetAct 15', 'NetAct8 EP2', '1', '2014-08-06 08:43:38');
INSERT INTO `cm_project` VALUES ('2', 'NetAct 15', 'NetAct8 EP2', '1', '2014-08-06 08:45:56');

-- ----------------------------
-- Table structure for `cm_project_cm_project_sheet`
-- ----------------------------
DROP TABLE IF EXISTS `cm_project_cm_project_sheet`;
CREATE TABLE `cm_project_cm_project_sheet` (
  `cm_project_id` bigint(20) NOT NULL,
  `projectSheets_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_fd90mn8mefqgvx84yrl52q37q` (`projectSheets_id`),
  KEY `FK_agi7f9h0mtbmbphsxqrvnsf3a` (`cm_project_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cm_project_cm_project_sheet
-- ----------------------------

-- ----------------------------
-- Table structure for `cm_project_sheet`
-- ----------------------------
DROP TABLE IF EXISTS `cm_project_sheet`;
CREATE TABLE `cm_project_sheet` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) NOT NULL,
  `sheet_name` varchar(10) NOT NULL,
  `sheet_desc` text,
  `sheet_index` int(11) DEFAULT NULL,
  `sheet_status` tinyint(4) DEFAULT NULL,
  `creation_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `project_id` (`project_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cm_project_sheet
-- ----------------------------

-- ----------------------------
-- Table structure for `cm_setting`
-- ----------------------------
DROP TABLE IF EXISTS `cm_setting`;
CREATE TABLE `cm_setting` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `system_key` varchar(10) NOT NULL,
  `system_value` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cm_setting
-- ----------------------------
