/*
 Navicat Premium Data Transfer

 Source Server         : local 5.7.26 mysql
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : rbac0

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 15/04/2020 17:48:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单id',
  `menu_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单url',
  `permission` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单权限值',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_id` int(11) NULL DEFAULT NULL COMMENT '角色id',
  `menu_id` int(11) NULL DEFAULT NULL COMMENT '菜单id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色菜单关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `enable` tinyint(1) NULL DEFAULT 1 COMMENT '可用，1可用，0不可用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `role_id` int(11) NULL DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色关联表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
