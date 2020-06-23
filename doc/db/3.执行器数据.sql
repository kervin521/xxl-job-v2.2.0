/*
 Navicat Premium Data Transfer

 Source Server         : 172.21.32.102[3307]
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : 172.21.32.102:3307
 Source Schema         : xxl-job

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : 65001

 Date: 16/07/2019 15:51:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;
-- ----------------------------
-- Records of xxl_job_group
-- ----------------------------
INSERT INTO `xxl_job_group` VALUES (2, 'target-calculate-service', 'calculate-dev', 0, NULL);
INSERT INTO `xxl_job_group` VALUES (3, 'target-calculate-service-pro', 'calculate-pro', 0, NULL);
INSERT INTO `xxl_job_group` VALUES (4, 'target-calculate-service-lua', 'calculate-lua', 0, NULL);
INSERT INTO `xxl_job_group` VALUES (5, 'target-calculate-service-test', 'calculate-test', 0, NULL);
INSERT INTO `xxl_job_group` VALUES (6, 'runLua', 'runLua', 1, 0, NULL);

SET FOREIGN_KEY_CHECKS = 1;
