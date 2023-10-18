/*
Navicat MySQL Data Transfer

Source Server         : demo
Source Server Version : 80032
Source Host           : localhost:3306
Source Database       : robot

Target Server Type    : MYSQL
Target Server Version : 80032
File Encoding         : 65001

Date: 2023-09-29 12:52:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for explo_table
-- ----------------------------
DROP TABLE IF EXISTS `explo_table`;
CREATE TABLE `explo_table` (
  `robotID` char(100) NOT NULL,
  `time_begin` datetime(6) NOT NULL,
  `time_end` datetime(6) NOT NULL,
  `treasure_num` int NOT NULL,
  KEY `robotid` (`robotID`),
  CONSTRAINT `robotid` FOREIGN KEY (`robotID`) REFERENCES `userinfo` (`robotid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of explo_table
-- ----------------------------
INSERT INTO `explo_table` VALUES ('r0001', '2023-01-14 13:30:37.000000', '2023-01-14 13:40:37.000000', '1');
INSERT INTO `explo_table` VALUES ('r0001', '2023-02-01 13:33:08.000000', '2023-02-01 13:37:22.000000', '1');
INSERT INTO `explo_table` VALUES ('r0002', '2023-03-01 13:33:49.000000', '2023-03-01 13:44:02.000000', '2');
INSERT INTO `explo_table` VALUES ('r0003', '2023-03-01 14:34:28.000000', '2023-03-01 14:45:37.000000', '3');
INSERT INTO `explo_table` VALUES ('r0002', '2023-04-01 13:35:09.000000', '2023-04-01 13:40:17.000000', '2');
INSERT INTO `explo_table` VALUES ('r0002', '2023-05-01 14:35:34.000000', '2023-05-01 14:40:43.000000', '1');
INSERT INTO `explo_table` VALUES ('r0001', '2023-06-01 13:36:26.000000', '2023-06-01 13:37:35.000000', '2');
INSERT INTO `explo_table` VALUES ('r0001', '2023-06-01 14:36:51.000000', '2023-06-01 14:41:58.000000', '1');
INSERT INTO `explo_table` VALUES ('r0003', '2023-06-05 13:37:35.000000', '2023-06-05 13:39:41.000000', '1');
INSERT INTO `explo_table` VALUES ('r0001', '2023-06-06 13:38:10.000000', '2023-06-06 13:43:15.000000', '1');

-- ----------------------------
-- Table structure for robotinfo
-- ----------------------------
DROP TABLE IF EXISTS `robotinfo`;
CREATE TABLE `robotinfo` (
  `robotpicture` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `robotName` char(100) DEFAULT NULL,
  `robotid` char(100) NOT NULL,
  PRIMARY KEY (`robotid`),
  CONSTRAINT `robotid1` FOREIGN KEY (`robotid`) REFERENCES `userinfo` (`robotid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of robotinfo
-- ----------------------------
INSERT INTO `robotinfo` VALUES ('green', 'green', 'r0001');
INSERT INTO `robotinfo` VALUES ('yellow', 'bbb', 'r0002');
INSERT INTO `robotinfo` VALUES ('green', 'ccc', 'r0003');
INSERT INTO `robotinfo` VALUES ('red', 'fgs', 'r0004');

-- ----------------------------
-- Table structure for userinfo
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `username` char(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` char(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `robotid` char(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`username`),
  KEY `robbitid` (`robotid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of userinfo
-- ----------------------------
INSERT INTO `userinfo` VALUES ('Andy', '321', 'r0004');
INSERT INTO `userinfo` VALUES ('Bob', '000000', 'r0001');
INSERT INTO `userinfo` VALUES ('Lily', '123456', 'r0003');
INSERT INTO `userinfo` VALUES ('Mary', '654321', 'r0002');
