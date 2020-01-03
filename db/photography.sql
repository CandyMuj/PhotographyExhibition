/*
 Navicat Premium Data Transfer

 Source Server         : root-3307
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3307
 Source Schema         : photography

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 03/01/2020 17:56:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `articleId` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文章标题',
  `face` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '封面',
  `articleType` int(11) NULL DEFAULT NULL COMMENT '文章类型 1系统文章 2系统广告文章，系统内部使用，外部不可编辑(和广告表进行关联的，在添加广告时自动添加一篇文章）',
  `articleType2` int(11) NULL DEFAULT NULL COMMENT '文章二级类别 dictcode:sysarticle_type 仅系统文章有/ 帮助反馈，关于我们...',
  `enable` int(11) NULL DEFAULT 1 COMMENT '是否启用/展示',
  `customerBusType` int(11) NULL DEFAULT NULL COMMENT '发布人类型 对应relationbustype',
  `customerId` int(11) NULL DEFAULT NULL COMMENT '发布人',
  `viewCount` int(11) NULL DEFAULT NULL COMMENT '阅读次数',
  `addTime` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`articleId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for articleattr
-- ----------------------------
DROP TABLE IF EXISTS `articleattr`;
CREATE TABLE `articleattr`  (
  `articleattrId` int(11) NOT NULL AUTO_INCREMENT COMMENT '文章关联表',
  `articleId` int(11) NULL DEFAULT NULL COMMENT '文章ID',
  `itemType` int(11) NULL DEFAULT NULL COMMENT '扩展数据类别/如商品-根据业务内容配置枚举 没有就为0',
  `itemId` int(11) NULL DEFAULT NULL COMMENT '扩展数据ID/如商品ID 没有就为0',
  PRIMARY KEY (`articleattrId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for articlecontent
-- ----------------------------
DROP TABLE IF EXISTS `articlecontent`;
CREATE TABLE `articlecontent`  (
  `articleId` int(11) NOT NULL COMMENT '文章内容表 图文，对应文章id，一个文章只有一个内容',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '文章内容',
  PRIMARY KEY (`articleId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `categoryId` int(11) NOT NULL AUTO_INCREMENT COMMENT '分类和标签',
  `pid` int(11) NULL DEFAULT NULL COMMENT '上级分类',
  `moduleType` int(11) NULL DEFAULT NULL COMMENT '分类类型/位置',
  `cname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `orderIndex` int(11) NULL DEFAULT NULL COMMENT '排序 - 越大排序越前',
  PRIMARY KEY (`categoryId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for categoryrealation
-- ----------------------------
DROP TABLE IF EXISTS `categoryrealation`;
CREATE TABLE `categoryrealation`  (
  `beanType` tinyint(4) NULL DEFAULT NULL COMMENT '关联实体类型，建议使用枚举，因为这个属于内部东西，不适合开放配置，也没必要，配置写起来也麻烦',
  `beanId` int(11) NULL DEFAULT NULL COMMENT '实体id',
  `categoryId` int(11) NULL DEFAULT NULL COMMENT '分类id'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '分类和实体关联表，适用于一个东西属于多个分类或标签\r\n如果一个东西只有一个分类或标签，那么直接在业务表中弄一个字段即可，不用这么麻烦\r\n如果那个分类只有一级那也没必要做关联表\r\n当然关联表是万能的，无论什么情况都可用，只是某些情况下可以不用这么麻烦' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`  (
  `customerId` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户账号',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `nickName` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户头像',
  `role` int(11) NULL DEFAULT NULL COMMENT '用户角色权限 1管理员 2普通用户 位运算（可登陆平台的权限，通过此字段定义枚举 安全可靠些）',
  `sex` tinyint(4) NULL DEFAULT NULL COMMENT '性别 1男 2女',
  `enable` tinyint(4) NULL DEFAULT 1 COMMENT '是否启用',
  `addTime` datetime(0) NULL DEFAULT NULL COMMENT '注册时间；添加时间',
  PRIMARY KEY (`customerId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for dict
-- ----------------------------
DROP TABLE IF EXISTS `dict`;
CREATE TABLE `dict`  (
  `dictId` int(11) NOT NULL AUTO_INCREMENT,
  `dictpid` int(11) NULL DEFAULT NULL,
  `dictname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `dictcode` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `dicttype` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `orderindex` int(11) NULL DEFAULT NULL,
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `limit` int(2) NULL DEFAULT NULL COMMENT '自身限制 默认无限制（一般是限制最顶级的父级的权限）',
  `childrenLimit` int(2) NULL DEFAULT NULL COMMENT '子级限制',
  PRIMARY KEY (`dictId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for footerinfo
-- ----------------------------
DROP TABLE IF EXISTS `footerinfo`;
CREATE TABLE `footerinfo`  (
  `footerInfoId` int(11) NOT NULL AUTO_INCREMENT COMMENT '页尾的链接和介绍相关的配置',
  `footerType` tinyint(4) NULL DEFAULT NULL COMMENT '类型 文字描述 外链等',
  `content` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文本 或者url',
  `enable` tinyint(4) NULL DEFAULT NULL COMMENT '1可用 0不可用',
  PRIMARY KEY (`footerInfoId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for homemodel
-- ----------------------------
DROP TABLE IF EXISTS `homemodel`;
CREATE TABLE `homemodel`  (
  `homeModelId` int(11) NOT NULL AUTO_INCREMENT COMMENT '页面模块配置',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标题',
  `desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '描述',
  `btnConfig` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '按钮配置 非必须，若为空则没有按钮 （格式：{text:按钮文字,key:关联类型,val:关联值}',
  `pic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图片 非必须（只有about会有这个图)',
  `orderIndex` int(11) NULL DEFAULT NULL COMMENT '排序 越大越靠前',
  `modelType` tinyint(4) NULL DEFAULT NULL COMMENT '模块类型 单行轮播图 多行展示 about 文章',
  PRIMARY KEY (`homeModelId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for photoalbum
-- ----------------------------
DROP TABLE IF EXISTS `photoalbum`;
CREATE TABLE `photoalbum`  (
  `photoAlbumId` int(11) NOT NULL AUTO_INCREMENT COMMENT '相册',
  `albumName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '相册名',
  `albumDesc` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '相册描述',
  `addTime` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`photoAlbumId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for picturelibrary
-- ----------------------------
DROP TABLE IF EXISTS `picturelibrary`;
CREATE TABLE `picturelibrary`  (
  `pictureLibraryId` int(11) NOT NULL AUTO_INCREMENT COMMENT '图片库',
  `photoAlbumId` int(11) NULL DEFAULT NULL COMMENT '相册id',
  `picName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图片名称',
  `picDesc` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图片描述',
  `picUri` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图片链接',
  `addTime` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`pictureLibraryId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for poster
-- ----------------------------
DROP TABLE IF EXISTS `poster`;
CREATE TABLE `poster`  (
  `posterId` int(11) NOT NULL AUTO_INCREMENT COMMENT '广告/banner',
  `postion` int(11) NULL DEFAULT NULL COMMENT '广告位置，dictid :adpostion',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '广告标题',
  `face` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '封面',
  `itemType` int(11) NULL DEFAULT NULL COMMENT '关联附件类别，1链接 2文章',
  `itemId` int(11) NULL DEFAULT 0 COMMENT '业务实体ID，如果没有对象，则为0',
  `url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'url链接',
  `enable` int(11) NULL DEFAULT 0 COMMENT '是否启用',
  `orderIndex` int(11) NULL DEFAULT NULL COMMENT '排序 越大排序越靠前',
  `showTime` datetime(0) NULL DEFAULT NULL COMMENT '显示时间 发布以后在这个时间后才显示',
  `addTime` datetime(0) NULL DEFAULT NULL COMMENT '发布时间',
  PRIMARY KEY (`posterId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sysconfig
-- ----------------------------
DROP TABLE IF EXISTS `sysconfig`;
CREATE TABLE `sysconfig`  (
  `sysId` int(11) NOT NULL AUTO_INCREMENT COMMENT '系统配置',
  `syskey` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `sysvalue` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `desc` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`sysId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for thirdbind
-- ----------------------------
DROP TABLE IF EXISTS `thirdbind`;
CREATE TABLE `thirdbind`  (
  `thirdBindId` int(11) NOT NULL AUTO_INCREMENT COMMENT '第三方账号关联绑定，可用于登录',
  `customerId` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `secretType` tinyint(4) NULL DEFAULT NULL COMMENT '密钥类型 手机号，微信，qq',
  `secretKey` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '对应的值',
  `addTime` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`thirdBindId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
