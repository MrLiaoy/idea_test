/*
Navicat MySQL Data Transfer

Source Server         : Mysql
Source Server Version : 50642
Source Host           : localhost:3306
Source Database       : library

Target Server Type    : MYSQL
Target Server Version : 50642
File Encoding         : 65001

Date: 2019-07-24 15:28:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_book`
-- ----------------------------
DROP TABLE IF EXISTS `tb_book`;
CREATE TABLE `tb_book` (
  `isbn` varchar(15) NOT NULL,
  `bookname` varchar(50) NOT NULL,
  `price` double NOT NULL DEFAULT '0',
  `author` varchar(50) NOT NULL,
  `publisher` varchar(50) NOT NULL,
  `pubdate` date DEFAULT NULL,
  `remaining` int(11) unsigned zerofill NOT NULL DEFAULT '00000000000',
  `counter` int(11) unsigned zerofill DEFAULT '00000000000',
  `total` int(11) NOT NULL DEFAULT '0',
  `obtained` int(11) DEFAULT '1',
  PRIMARY KEY (`isbn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_book
-- ----------------------------
INSERT INTO `tb_book` VALUES ('9787111547426', 'Java核心技术卷I', '95.2', '凯 S·霍斯特曼', '机械工业出版社', '2018-12-05', '00000000010', '00000000001', '10', '0');
INSERT INTO `tb_book` VALUES ('9787111573319', 'Java核心技术卷II', '107.1', '凯 S·霍斯特曼', '机械工业出版社', '2018-06-05', '00000000010', '00000000001', '10', '1');
INSERT INTO `tb_book` VALUES ('9787302446699', 'C++从入门到精通', '55', 'yys', '清华大学出版社', '2017-05-01', '00000000010', '00000000000', '10', '1');
INSERT INTO `tb_book` VALUES ('9787569210460', '零基础学JavaScript', '56.4', 'yyj', '吉林大学出版社', '2017-11-01', '00000000010', '00000000002', '10', '1');

-- ----------------------------
-- Table structure for `tb_reader`
-- ----------------------------
DROP TABLE IF EXISTS `tb_reader`;
CREATE TABLE `tb_reader` (
  `rid` int(11) NOT NULL,
  `rname` varchar(50) NOT NULL,
  `gender` varchar(5) NOT NULL,
  `tel` varchar(11) NOT NULL,
  `regdate` date NOT NULL,
  `availble` int(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_reader
-- ----------------------------
INSERT INTO `tb_reader` VALUES ('20190001', 'zhou', '男', '15680320720', '2019-07-23', '1');
INSERT INTO `tb_reader` VALUES ('20190002', '小扎啤', '男', '138200155', '2019-07-23', '1');
INSERT INTO `tb_reader` VALUES ('20190003', '廖益隆', '男', '1783826322', '2019-07-24', '1');

-- ----------------------------
-- Table structure for `tb_rec`
-- ----------------------------
DROP TABLE IF EXISTS `tb_rec`;
CREATE TABLE `tb_rec` (
  `rec_id` int(11) NOT NULL AUTO_INCREMENT,
  `rid` int(11) DEFAULT NULL,
  `isbn` varchar(15) DEFAULT NULL,
  `ldate` date DEFAULT NULL,
  `rdate` date DEFAULT NULL,
  `punish` double DEFAULT NULL,
  PRIMARY KEY (`rec_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1008 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_rec
-- ----------------------------
INSERT INTO `tb_rec` VALUES ('1004', '20190001', '9787111573319', '2019-07-23', '2019-07-23', '0');
INSERT INTO `tb_rec` VALUES ('1005', '20190003', '9787111547426', '2019-07-24', '2019-07-24', '0');
INSERT INTO `tb_rec` VALUES ('1006', '20190001', '9787569210460', '2019-07-24', '2019-07-24', '0');
INSERT INTO `tb_rec` VALUES ('1007', '20190001', '9787569210460', '2019-02-06', '2019-07-24', '11');
