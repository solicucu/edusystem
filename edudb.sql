/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50724
Source Host           : localhost:3306
Source Database       : edudb

Target Server Type    : MYSQL
Target Server Version : 50724
File Encoding         : 65001

Date: 2019-12-06 22:07:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` varchar(255) NOT NULL,
  `dept` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of admin
-- ----------------------------

-- ----------------------------
-- Table structure for class_room
-- ----------------------------
DROP TABLE IF EXISTS `class_room`;
CREATE TABLE `class_room` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `rseservation_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FKchbx45casm51yixdn9y36ov12` (`rseservation_id`) USING BTREE,
  CONSTRAINT `FKchbx45casm51yixdn9y36ov12` FOREIGN KEY (`rseservation_id`) REFERENCES `reservation` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of class_room
-- ----------------------------

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` varchar(255) NOT NULL,
  `day` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  `selected` int(11) DEFAULT NULL,
  `session` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('1', '星期三、五', '忍者学校', '体术', '10', '5', '7');
INSERT INTO `course` VALUES ('2', '星期二、四', '忍者学校', '忍术', '10', '3', '5');
INSERT INTO `course` VALUES ('3', '星期一', '体育场', '实训', '10', '4', '1');

-- ----------------------------
-- Table structure for graduation_project
-- ----------------------------
DROP TABLE IF EXISTS `graduation_project`;
CREATE TABLE `graduation_project` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `paper` varchar(255) DEFAULT NULL,
  `project` varchar(255) DEFAULT NULL,
  `schedule` varchar(255) DEFAULT NULL,
  `sid_id` varchar(255) DEFAULT NULL,
  `tid_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FK3kh5bd39bcjcduhwdcj7261tn` (`sid_id`) USING BTREE,
  KEY `FK391cai1fit3c8nel503arivn0` (`tid_id`) USING BTREE,
  CONSTRAINT `FK391cai1fit3c8nel503arivn0` FOREIGN KEY (`tid_id`) REFERENCES `teacher` (`id`),
  CONSTRAINT `FK3kh5bd39bcjcduhwdcj7261tn` FOREIGN KEY (`sid_id`) REFERENCES `student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of graduation_project
-- ----------------------------

-- ----------------------------
-- Table structure for login
-- ----------------------------
DROP TABLE IF EXISTS `login`;
CREATE TABLE `login` (
  `id` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of login
-- ----------------------------
INSERT INTO `login` VALUES ('10000', '123456', '1');
INSERT INTO `login` VALUES ('10086', '123456', '1');
INSERT INTO `login` VALUES ('10087', '123456', '1');
INSERT INTO `login` VALUES ('10088', '123456', '1');
INSERT INTO `login` VALUES ('10089', '123456', '1');
INSERT INTO `login` VALUES ('10090', '123456', '1');
INSERT INTO `login` VALUES ('201801', '123456', '2');
INSERT INTO `login` VALUES ('201802', '123456', '2');
INSERT INTO `login` VALUES ('201803', '123456', '2');
INSERT INTO `login` VALUES ('admin', '123456', '0');

-- ----------------------------
-- Table structure for reservation
-- ----------------------------
DROP TABLE IF EXISTS `reservation`;
CREATE TABLE `reservation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `positon` varchar(255) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `sid_id` varchar(255) DEFAULT NULL,
  `position` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FKphy28yxlt6p8ycl80lsbxlhnf` (`sid_id`) USING BTREE,
  CONSTRAINT `FKphy28yxlt6p8ycl80lsbxlhnf` FOREIGN KEY (`sid_id`) REFERENCES `student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of reservation
-- ----------------------------

-- ----------------------------
-- Table structure for sc
-- ----------------------------
DROP TABLE IF EXISTS `sc`;
CREATE TABLE `sc` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cid` varchar(255) DEFAULT NULL,
  `cname` varchar(255) DEFAULT NULL,
  `day` varchar(255) DEFAULT NULL,
  `grade` int(11) DEFAULT NULL,
  `session` int(11) DEFAULT NULL,
  `sid` varchar(255) DEFAULT NULL,
  `sname` varchar(255) DEFAULT NULL,
  `cid_id` varchar(255) DEFAULT NULL,
  `sid_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FKSCcourse` (`cid`) USING BTREE,
  KEY `FKSCstudent` (`sid`) USING BTREE,
  KEY `FKea4sahdttui36xw4s094wv1u1` (`cid_id`) USING BTREE,
  KEY `FKqm9j1nfxxnq45ash4c2h5qqpy` (`sid_id`) USING BTREE,
  CONSTRAINT `FKSCcourse` FOREIGN KEY (`cid`) REFERENCES `course` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FKSCstudent` FOREIGN KEY (`sid`) REFERENCES `student` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FKea4sahdttui36xw4s094wv1u1` FOREIGN KEY (`cid_id`) REFERENCES `course` (`id`),
  CONSTRAINT `FKqm9j1nfxxnq45ash4c2h5qqpy` FOREIGN KEY (`sid_id`) REFERENCES `student` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sc
-- ----------------------------
INSERT INTO `sc` VALUES ('3', '2', '忍术', '星期二、四', '60', '5', '201801', '鸣人', null, null);
INSERT INTO `sc` VALUES ('5', '1', '体术', '星期三、五', null, '7', '201802', '佐助', null, null);
INSERT INTO `sc` VALUES ('6', '2', '忍术', '星期二、四', '100', '5', '201802', '佐助', null, null);
INSERT INTO `sc` VALUES ('7', '3', '实训', '星期一', '90', '1', '201802', '佐助', null, null);
INSERT INTO `sc` VALUES ('8', '1', '体术', '星期三、五', null, '7', '201803', '小樱', null, null);
INSERT INTO `sc` VALUES ('9', '2', '忍术', '星期二、四', null, '5', '201803', '小樱', null, null);
INSERT INTO `sc` VALUES ('10', '3', '实训', '星期一', '70', '1', '201803', '小樱', null, null);
INSERT INTO `sc` VALUES ('11', '3', '实训', '星期一', null, '1', '201801', '鸣人', null, null);
INSERT INTO `sc` VALUES ('13', '1', '体术', '星期三、五', '90', '7', '201801', '鸣人', null, null);

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` varchar(255) NOT NULL,
  `age` int(11) DEFAULT NULL,
  `dept` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('201801', '12', '木叶', '鸣人', '男');
INSERT INTO `student` VALUES ('201802', '12', '木叶', '佐助', '男');
INSERT INTO `student` VALUES ('201803', '12', '木叶', '小樱', '女');

-- ----------------------------
-- Table structure for tc
-- ----------------------------
DROP TABLE IF EXISTS `tc`;
CREATE TABLE `tc` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cid` varchar(255) DEFAULT NULL,
  `cname` varchar(255) DEFAULT NULL,
  `tid` varchar(255) DEFAULT NULL,
  `tname` varchar(255) DEFAULT NULL,
  `cid_id` varchar(255) DEFAULT NULL,
  `tid_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FKcourse_idx` (`cid`) USING BTREE,
  KEY `FKteacher_idx` (`tid`) USING BTREE,
  KEY `FKgsgfxhqwv3lwbtcquap1unpwa` (`cid_id`) USING BTREE,
  KEY `FK19hr1l9ov31nswyron1msudo5` (`tid_id`) USING BTREE,
  CONSTRAINT `FK19hr1l9ov31nswyron1msudo5` FOREIGN KEY (`tid_id`) REFERENCES `teacher` (`id`),
  CONSTRAINT `FKTCcourse` FOREIGN KEY (`cid`) REFERENCES `course` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FKTCteacher` FOREIGN KEY (`tid`) REFERENCES `teacher` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FKgsgfxhqwv3lwbtcquap1unpwa` FOREIGN KEY (`cid_id`) REFERENCES `course` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tc
-- ----------------------------
INSERT INTO `tc` VALUES ('1', '1', '体术', '10090', '凯', null, null);
INSERT INTO `tc` VALUES ('2', '2', '忍术', '10087', '伊鲁卡', null, null);
INSERT INTO `tc` VALUES ('3', '3', '实训', '10086', '卡卡西', null, null);

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `id` varchar(255) NOT NULL,
  `age` int(11) DEFAULT NULL,
  `dept` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('10000', '27', '木叶', '波风水门', '男');
INSERT INTO `teacher` VALUES ('10086', '26', '木叶', '卡卡西', '男');
INSERT INTO `teacher` VALUES ('10087', '22', '木叶', '伊鲁卡', '男');
INSERT INTO `teacher` VALUES ('10088', '27', '木叶', '阿斯玛', '男');
INSERT INTO `teacher` VALUES ('10089', '50', '木叶', '自来也', '男');
INSERT INTO `teacher` VALUES ('10090', '26', '木叶', '凯', '男');

-- ----------------------------
-- Table structure for test_plan
-- ----------------------------
DROP TABLE IF EXISTS `test_plan`;
CREATE TABLE `test_plan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `end_time` datetime DEFAULT NULL,
  `max` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `position` varchar(255) DEFAULT NULL,
  `start_tume` datetime DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of test_plan
-- ----------------------------
