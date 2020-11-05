/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : 127.0.0.1:3306
 Source Schema         : photography

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 05/11/2020 11:27:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `article_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '文章表',
  `title` varchar(125) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文章标题',
  `cover_img` varchar(125) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '封面',
  `article_type` int(11) NULL DEFAULT NULL COMMENT '文章类型 1系统文章，系统内部使用，外部不可编辑(和广告表进行关联的，在添加广告时自动添加一篇文章）',
  `article_type2` int(11) NULL DEFAULT NULL COMMENT '文章二级类别 dictcode:sysarticle_type 仅系统文章有/ 帮助反馈，关于我们...',
  `customer_id` bigint(20) NULL DEFAULT NULL COMMENT '发布人用户id',
  `read_num` int(11) NULL DEFAULT NULL COMMENT '阅读次数',
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '新增时间',
  `enabled` tinyint(4) NULL DEFAULT 1 COMMENT '是否启用/展示',
  PRIMARY KEY (`article_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '文章表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for article_attr
-- ----------------------------
DROP TABLE IF EXISTS `article_attr`;
CREATE TABLE `article_attr`  (
  `article_attr_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '文章属性关联表，如果有关联或属性此表才有对应的数据',
  `article_id` bigint(20) NULL DEFAULT NULL COMMENT '文章ID',
  `item_type` int(11) NULL DEFAULT NULL COMMENT '扩展数据类别/如商品-根据业务内容配置枚举 没有就为0',
  `item_id` bigint(20) NULL DEFAULT NULL COMMENT '扩展数据ID/如商品ID 没有就为0',
  `location` varchar(125) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '定位标识，方便前端定位做展示 文章中使用 <loc></loc> 标识',
  `order_index` bigint(20) NULL DEFAULT NULL COMMENT '结合location确定位置',
  PRIMARY KEY (`article_attr_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '文章属性关联表，如果有关联或属性此表才有对应的数据' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for article_content
-- ----------------------------
DROP TABLE IF EXISTS `article_content`;
CREATE TABLE `article_content`  (
  `articleId` bigint(20) NOT NULL COMMENT '文章内容表 图文，对应文章id，一个文章只有一个内容',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '文章内容',
  PRIMARY KEY (`articleId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '文章内容表 图文，对应文章id，一个文章只有一个内容' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for category_relation
-- ----------------------------
DROP TABLE IF EXISTS `category_relation`;
CREATE TABLE `category_relation`  (
  `category_id` bigint(20) NULL DEFAULT NULL COMMENT '分类标签与实体的关联表',
  `bean_type` tinyint(4) NOT NULL COMMENT '关联实体类型 使用枚举',
  `bean_id` bigint(20) NOT NULL COMMENT '关联实体id',
  PRIMARY KEY (`bean_type`, `bean_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '分类标签与实体的关联表，想了许久是直接在业务bean中加一个标签字段，还是使用专门的关系表来进行管理，最后决定使用关系表，就是分表吧' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for category_tag
-- ----------------------------
DROP TABLE IF EXISTS `category_tag`;
CREATE TABLE `category_tag`  (
  `category_tag_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '分类和标签',
  `pid` bigint(20) NULL DEFAULT NULL COMMENT '上级分类',
  `module_type` int(11) NULL DEFAULT NULL COMMENT '分类类型/位置',
  `category_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `order_index` bigint(20) NULL DEFAULT NULL COMMENT '排序 - 越大排序越前',
  PRIMARY KEY (`category_tag_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '分类和标签' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`  (
  `customer_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户主表，登录信息',
  `account` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户账号',
  `passwd` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `user_type` int(11) NULL DEFAULT NULL COMMENT '用户类型 1管理员 2普通用户 位运算（可登陆平台的权限，通过此字段定义枚举 安全可靠些）',
  `frozen` int(11) NULL DEFAULT NULL COMMENT '是否冻结',
  `frozen_time` datetime(0) NULL DEFAULT NULL COMMENT '冻结时间',
  `frozen_reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '冻结原因',
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '注册时间；添加时间',
  `last_login_time` datetime(0) NULL DEFAULT NULL COMMENT '最后登录时间',
  PRIMARY KEY (`customer_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户主表，登录信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES (1, 'candy', 'e10adc3949ba59abbe56e057f20f883e', 1, NULL, NULL, NULL, '2019-12-18 16:32:40', NULL);

-- ----------------------------
-- Table structure for customer_third_bind
-- ----------------------------
DROP TABLE IF EXISTS `customer_third_bind`;
CREATE TABLE `customer_third_bind`  (
  `customer_third_bind_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '第三方账号关联绑定，主要用于登录，所以这里使用cusid',
  `customer_id` bigint(20) NULL DEFAULT NULL COMMENT '用户id',
  `secret_type` tinyint(4) NULL DEFAULT NULL COMMENT '密钥类型 手机号，微信，qq',
  `secret_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '对应的值',
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '关联时间',
  PRIMARY KEY (`customer_third_bind_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '第三方账号关联绑定，主要用于登录，所以这里使用cusid' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for customer_user
-- ----------------------------
DROP TABLE IF EXISTS `customer_user`;
CREATE TABLE `customer_user`  (
  `customer_user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户表-普通用户',
  `user_nick_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户昵称 用户平台展示昵称',
  `user_real_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户真实姓名 用于图片署名作者',
  `frozen` int(11) NULL DEFAULT NULL COMMENT '是否冻结',
  `frozen_time` datetime(0) NULL DEFAULT NULL COMMENT '冻结时间',
  `frozen_reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '冻结原因',
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '新增时间',
  `last_login_time` datetime(0) NULL DEFAULT NULL COMMENT '最后登录时间',
  PRIMARY KEY (`customer_user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表-普通用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for dict
-- ----------------------------
DROP TABLE IF EXISTS `dict`;
CREATE TABLE `dict`  (
  `dict_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典表',
  `dict_pid` bigint(20) NULL DEFAULT NULL COMMENT '字典父级id',
  `dict_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典名称',
  `dict_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典编码',
  `dict_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典类型；即与根节点code保持一致',
  `order_index` bigint(20) NULL DEFAULT NULL COMMENT '排序-越大越靠前',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `ext_data` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '扩展数据 推荐使用json格式数据',
  `self_limit` int(11) NULL DEFAULT NULL COMMENT '自身限制，一般仅根节点可自选;可读可写，只读，不可新增',
  `children_limit` int(11) NULL DEFAULT NULL COMMENT '子级限制',
  PRIMARY KEY (`dict_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dict
-- ----------------------------
INSERT INTO `dict` VALUES (2, NULL, NULL, NULL, 'xxx', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `dict` VALUES (4, NULL, NULL, NULL, 'xxx', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `dict` VALUES (5, NULL, NULL, NULL, 'xxx', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for file_list
-- ----------------------------
DROP TABLE IF EXISTS `file_list`;
CREATE TABLE `file_list`  (
  `file_list_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '文件统一管理库，如果是为了速度，可使用redis，但是总感觉redis不太可靠',
  `item_type` int(11) NULL DEFAULT NULL COMMENT '文件关联业务类型',
  `item_id` bigint(20) NULL DEFAULT NULL COMMENT '业务id',
  `saved_uri` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '保存地址',
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '添加时间',
  `deleted` tinyint(4) NULL DEFAULT NULL COMMENT '逻辑删除',
  `deleted_time` datetime(0) NULL DEFAULT NULL COMMENT '逻辑删除时间',
  PRIMARY KEY (`file_list_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文件统一管理库，如果是为了速度，可使用redis，但是总感觉redis不太可靠' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for footer_info
-- ----------------------------
DROP TABLE IF EXISTS `footer_info`;
CREATE TABLE `footer_info`  (
  `footer_info_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '页尾的链接和介绍相关的配置',
  `page_model_id` bigint(20) NULL DEFAULT NULL COMMENT '关联页面的具体配置',
  `footer_data` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '文本 或者url;或者是json数据',
  `enabled` tinyint(4) NULL DEFAULT NULL COMMENT '1可用 0不可用',
  PRIMARY KEY (`footer_info_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '页尾的链接和介绍相关的配置' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `menu_client_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单表-客户端',
  `pid` bigint(20) NOT NULL,
  `client_type` tinyint(4) NOT NULL COMMENT '客户端类型：管理端、安卓端、pc端、用户端、商家端...',
  `menu_type` tinyint(4) NOT NULL COMMENT '菜单类型：目录、菜单、按钮（必须区分，目录和按钮都没有url，所以不能仅通过url是否为空来判断）',
  `menu_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `menu_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `order_index` bigint(20) NOT NULL,
  `enabled` int(11) NULL DEFAULT NULL COMMENT '是否可用',
  PRIMARY KEY (`menu_client_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for page_model
-- ----------------------------
DROP TABLE IF EXISTS `page_model`;
CREATE TABLE `page_model`  (
  `page_model_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '页面模块配置',
  `pid` bigint(20) NULL DEFAULT NULL COMMENT '如果存在多级的话那么这里就有值',
  `model_type` tinyint(4) NULL DEFAULT NULL COMMENT '模块类型 单行轮播图 多行展示 about 文章',
  `model_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标题/模块名',
  `model_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '描述',
  `btn_config` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '按钮配置 非必须，若为空则没有按钮 （格式：{text:按钮文字,key:关联类型,val:关联值}',
  `ext_data` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '扩展数据，推荐使用json格式；如：about中的图片',
  `order_index` bigint(20) NULL DEFAULT NULL COMMENT '排序 越大越靠前',
  PRIMARY KEY (`page_model_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '页面模块配置' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for photo_album
-- ----------------------------
DROP TABLE IF EXISTS `photo_album`;
CREATE TABLE `photo_album`  (
  `photo_album_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '相册',
  `customer_user_id` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `album_name` varchar(125) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '相册名',
  `album_desc` varchar(125) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '相册描述',
  `add_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`photo_album_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '相册' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for photo_library
-- ----------------------------
DROP TABLE IF EXISTS `photo_library`;
CREATE TABLE `photo_library`  (
  `photo_library_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '图片库',
  `photo_album_id` bigint(20) NULL DEFAULT NULL COMMENT '相册id 非必须',
  `customer_user_id` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `pic_name` varchar(125) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图片名称',
  `pic_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图片描述',
  `pic_uri` varchar(125) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图片链接',
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '新增时间',
  `deleted` tinyint(4) NULL DEFAULT NULL COMMENT '逻辑删除',
  `deleted_time` datetime(0) NULL DEFAULT NULL COMMENT '逻辑删除时间',
  PRIMARY KEY (`photo_library_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '图片库' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for poster
-- ----------------------------
DROP TABLE IF EXISTS `poster`;
CREATE TABLE `poster`  (
  `poster_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '广告/banner',
  `postion` int(11) NULL DEFAULT NULL COMMENT '广告位置，dictid :adpostion',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '广告标题',
  `cover_img` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '封面',
  `item_type` tinyint(4) NULL DEFAULT NULL COMMENT '关联附件类别，1链接 2文章',
  `item_id` bigint(20) NULL DEFAULT 0 COMMENT '业务实体ID，如果没有对象，则为0',
  `item_url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'url链接',
  `enabled` tinyint(4) NULL DEFAULT 0 COMMENT '是否启用',
  `order_index` bigint(20) NULL DEFAULT NULL COMMENT '排序 越大排序越靠前',
  `show_time` datetime(0) NULL DEFAULT NULL COMMENT '显示时间 发布以后在这个时间后才显示',
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '发布时间',
  PRIMARY KEY (`poster_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '广告/banner' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色权限库-管理端',
  `role_type` tinyint(4) NULL DEFAULT NULL COMMENT '角色作用的位置，如：管理端还是用户端',
  `role_name` varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `enabled` tinyint(4) NULL DEFAULT 1 COMMENT '0禁用 1启用',
  `menu_id` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单id',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色权限库' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`  (
  `sys_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '系统配置',
  `sys_key` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `sys_value` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '推荐使用json格式数据',
  `sys_desc` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`sys_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统配置' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for system_log
-- ----------------------------
DROP TABLE IF EXISTS `system_log`;
CREATE TABLE `system_log`  (
  `log_type` int(2) NULL DEFAULT NULL COMMENT '日志类型 枚举值：外部接口，系统操作',
  `user_account_id` bigint(20) NULL DEFAULT NULL COMMENT '用户id 如果是管理系统的操作则有此记录',
  `describe` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '描述',
  `rest_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '接口地址',
  `rest_param` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '接口参数 json格式',
  `old_param` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '如果是修改或者删除操作 记录修改前的状态 json格式',
  `ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '操作IP',
  `result` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '操作结果',
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '日志时间',
  INDEX `user_account_id`(`user_account_id`) USING BTREE,
  INDEX `add_time`(`add_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统日志表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
