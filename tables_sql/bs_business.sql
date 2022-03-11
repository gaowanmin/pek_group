/*
 Navicat Premium Data Transfer

 Source Server         : 101.200.191.19
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : 101.200.191.19:3306
 Source Schema         : pek_group

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 11/03/2022 15:26:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bs_business
-- ----------------------------
DROP TABLE IF EXISTS `bs_business`;
CREATE TABLE `bs_business`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `airport_code` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '机场三字码',
  `primary_categroy` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '一级分类:shop购物，food餐饮',
  `second_categroy` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '二级分类编码,支持逗号分隔',
  `store_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '店铺名称',
  `terminal` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '航站楼',
  `floor` varchar(5) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '楼层',
  `area` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '枚举值：安检区外、安检区内',
  `business_hours` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '营业时间',
  `address` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '地址位置',
  `logo_pic` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '封面缩率图',
  `detail_pic` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '详情页大图',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '联系电话',
  `business_scope` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '经营范围',
  `store_desc` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '店铺描述',
  `key_words` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '关键词搜索',
  `per_price` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '人均价格',
  `discount_info` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '优惠信息描述',
  `others_service` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '其他服务',
  `flag_24hour` tinyint(4) NULL DEFAULT 0 COMMENT '是否24小时店，0不是，1是',
  `flag_suggest` tinyint(4) NULL DEFAULT 0 COMMENT '是否推荐店铺，0不推荐，1推荐',
  `building_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '室内地图建筑物id',
  `floor_no` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '室内地图楼层',
  `x` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `y` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `map_url` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '跳转地图链接',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '商业店铺' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for bs_category
-- ----------------------------
DROP TABLE IF EXISTS `bs_category`;
CREATE TABLE `bs_category`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `primary_category` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '一级分类代码',
  `second_category` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '二级分类代码',
  `second_category_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '二级分类名称',
  `status` tinyint(4) NULL DEFAULT NULL COMMENT '状态:0未启用1启用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '商贸餐饮二级分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for flight
-- ----------------------------
DROP TABLE IF EXISTS `flight`;
CREATE TABLE `flight`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `source_id` varchar(8) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '本站机场 ',
  `gufi` varchar(64) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '航班唯一标识 ',
  `unit_ufi` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '【源系统】航班唯一标识',
  `airline_iata` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '航空公司二字码',
  `flight_number_iata` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '航班号',
  `flight_date` date NULL DEFAULT NULL COMMENT '航班执行日期',
  `dep_or_arr` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '进出港标识 A/D',
  `mission_property` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '航班任务',
  `flight_segment_dori` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '航班属性(D/I/A)',
  `code_share_flight_list` varchar(64) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '共享航班号',
  `reg_number` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '机号',
  `aircraft_type` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '机型',
  `dep_ap` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '机场三字码',
  `schedule_time_take_off` datetime(0) NULL DEFAULT NULL COMMENT '出发站计划时间',
  `estimate_take_off_time` datetime(0) NULL DEFAULT NULL COMMENT '出发站预计时间',
  `atot` datetime(0) NULL DEFAULT NULL COMMENT '出发站实际时间',
  `dep_terminal` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出发站航站楼',
  `arr_ap` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '到达站机场三字码',
  `schedule_time_landing` datetime(0) NULL DEFAULT NULL COMMENT '到达站计划到达时间',
  `eldt` datetime(0) NULL DEFAULT NULL COMMENT '到达站预计到达时间',
  `aldt` datetime(0) NULL DEFAULT NULL COMMENT '到达站实际到达时间',
  `arr_terminal` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '到达站航站楼',
  `arr_stand` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '到达站机位 ',
  `cah_flight_status` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '航班状态',
  `cnl_reason_chn` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '异常状态原因 ',
  `mission_flyinfo` varchar(128) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '航线',
  `check_in_counter` varchar(32) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '值机柜台',
  `dep_gate` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '登机口',
  `expected_arrival_luggage_turntable` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '行李提取口',
  `asbt` datetime(0) NULL DEFAULT NULL COMMENT '开始登机时间',
  `aebt` datetime(0) NULL DEFAULT NULL COMMENT '结束登机时间',
  `final_call_time` datetime(0) NULL DEFAULT NULL COMMENT '催促登机时间 ',
  `baggage_arr_time` datetime(0) NULL DEFAULT NULL COMMENT '首件行李上架时间',
  `arr_last_luggage_time` datetime(0) NULL DEFAULT NULL COMMENT '末件行李上架时间',
  `former_unit_ufi` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '前序航班计划标识符',
  `next_unit_ufi` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '后序航班计划标识符',
  `pre_atot` datetime(0) NULL DEFAULT NULL COMMENT '进港上一站实际起飞时间',
  `next_aldt` datetime(0) NULL DEFAULT NULL COMMENT '出港下一站实际落地时间',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '当前记录最新更新时间',
  `del_status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '删除标识（0删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for flight_change_info
-- ----------------------------
DROP TABLE IF EXISTS `flight_change_info`;
CREATE TABLE `flight_change_info`  (
  `change_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `identification` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '航班标识,格式为：航空公司二字码-航班号-计划时间(yyyyMMddHHmmss)-进出标志志（A-进港；D-出港）,计划时间说明：进港为本站计划降落时间，出港为本站计划起飞时间',
  `iata` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '航班二字码',
  `flt_no` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '航班号',
  `aord` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '进出港类型（A/D）',
  `domint` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'D/I',
  `change_info` varchar(800) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '变化内容',
  `execute_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '未读标识\"N\"，已读Y',
  `invalid_time` datetime(0) NULL DEFAULT NULL COMMENT '推送失效时间，在计划时间上加1小时',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '首次创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '最近更新时间',
  PRIMARY KEY (`change_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '航班产生变化时所记录的表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for flt_log_info
-- ----------------------------
DROP TABLE IF EXISTS `flt_log_info`;
CREATE TABLE `flt_log_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键(数据ID)',
  `receive_time` datetime(0) NULL DEFAULT NULL COMMENT '获取数据时间',
  `content` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '数据',
  `handle_result` int(4) NULL DEFAULT NULL COMMENT '信息处理结果(0:更新成功，1:更新失败，99异常）',
  `total_count` int(11) NULL DEFAULT NULL COMMENT '传送航班总数',
  `arr_or_dep` int(11) NULL DEFAULT NULL COMMENT '进出港标识 1：进港 2：出港',
  `exception_info` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '异常信息',
  `ffid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '航班标识',
  `styp` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '消息类型',
  `ddtm` datetime(0) NULL DEFAULT NULL COMMENT '发送时间',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for gen_table
-- ----------------------------
DROP TABLE IF EXISTS `gen_table`;
CREATE TABLE `gen_table`  (
  `table_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '编号',
  `table_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '表名称',
  `table_comment` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '表描述',
  `sub_table_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '关联子表的表名',
  `sub_table_fk_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '子表关联的外键名',
  `class_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '实体类名称',
  `tpl_category` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'crud' COMMENT '使用的模板（crud单表操作 tree树表操作 sub主子表操作）',
  `package_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成包路径',
  `module_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成模块名',
  `business_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成业务名',
  `function_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成功能名',
  `function_author` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成功能作者',
  `gen_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '生成代码方式（0zip压缩包 1自定义路径）',
  `gen_path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '/' COMMENT '生成路径（不填默认项目路径）',
  `options` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '其它生成选项',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `parent_menu_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父级菜单',
  `parent_menu_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父级菜单名称',
  `tree_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '树表主键',
  `tree_parent_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '树表父级字段',
  PRIMARY KEY (`table_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '代码生成业务表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for gen_table_column
-- ----------------------------
DROP TABLE IF EXISTS `gen_table_column`;
CREATE TABLE `gen_table_column`  (
  `column_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '编号',
  `table_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '归属表编号',
  `column_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '列名称',
  `column_comment` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '列描述',
  `column_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '列类型',
  `java_type` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'JAVA类型',
  `java_field` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'JAVA字段名',
  `is_pk` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否主键（1是）',
  `is_increment` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否自增（1是）',
  `is_required` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否必填（1是）',
  `is_insert` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否为插入字段（1是）',
  `is_edit` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否编辑字段（1是）',
  `is_list` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否列表字段（1是）',
  `is_query` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否查询字段（1是）',
  `query_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'EQ' COMMENT '查询方式（等于、不等于、大于、小于、范围）',
  `html_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
  `dict_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`column_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '代码生成业务表字段' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for hot_airport
-- ----------------------------
DROP TABLE IF EXISTS `hot_airport`;
CREATE TABLE `hot_airport`  (
  `id` int(10) NOT NULL COMMENT '热门机场Id',
  `curr_airport_code` char(3) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '视角机场三字码',
  `airport_code` char(3) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '机场三字码',
  `domint` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'D' COMMENT '城市国际国内标识 D|I',
  `priority` int(11) NULL DEFAULT NULL COMMENT '当前结果集显示序号',
  `name_cn` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '中文全称',
  `name_en` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '英文全称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '中文全称' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for subscribe_contract
-- ----------------------------
DROP TABLE IF EXISTS `subscribe_contract`;
CREATE TABLE `subscribe_contract`  (
  `contract_id` int(8) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '订阅契约表Id',
  `curr_airport` char(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '当前机场三字码',
  `opendid` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订阅者Id',
  `ffid` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订阅航班唯一标识',
  `flt_no` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '航班号',
  `flt_date` date NULL DEFAULT NULL COMMENT '航班日期',
  `iata` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '航司二字码',
  `aord` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '进出港标识',
  `domint` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '国际国内标识',
  `subscribe_status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'Y' COMMENT '订阅状态，默认Y已订阅，取消订阅的C',
  `invalid_time` datetime(0) NULL DEFAULT NULL COMMENT '订阅失效时间默认+48小时',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '首次订阅契约发生时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '最近更新时间',
  PRIMARY KEY (`contract_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订阅契约表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `dept_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门名称',
  `parent_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父级编号',
  `dept_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '部门名称',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `leader` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '负责人',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系方式',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `status` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '部门状态',
  `create_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '备注',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '详细地址',
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_file
-- ----------------------------
DROP TABLE IF EXISTS `sys_file`;
CREATE TABLE `sys_file`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标识',
  `file_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件名称',
  `file_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件描述',
  `file_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件路径',
  `file_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件类型',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `target_date` datetime(0) NULL DEFAULT NULL COMMENT '所属时间',
  `file_size` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件大小',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户操作',
  `type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求方式',
  `method` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求参数',
  `time` bigint(20) NOT NULL COMMENT '执行时长(毫秒)',
  `ip` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'IP地址',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1495588226612596739 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_logging
-- ----------------------------
DROP TABLE IF EXISTS `sys_logging`;
CREATE TABLE `sys_logging`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '相应消息体',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标题',
  `method` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求方式',
  `business_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '业务类型',
  `request_method` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求方法',
  `operate_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作人',
  `operate_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作路径',
  `operate_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作地址',
  `request_param` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求参数',
  `response_body` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '相应消息体',
  `success` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否成功',
  `error_msg` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '异常信息',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `request_body` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求消息体',
  `browser` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '使用浏览器',
  `system_os` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作系统',
  `logging_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '日志类型，登录日志，操作日志',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice`  (
  `id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '编号',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标题',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '内容',
  `sender` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '发送人',
  `accept` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '接收者',
  `type` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '类型',
  `create_by` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_power
-- ----------------------------
DROP TABLE IF EXISTS `sys_power`;
CREATE TABLE `sys_power`  (
  `power_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限编号',
  `power_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限名称',
  `power_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限类型',
  `power_code` char(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限标识',
  `power_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限路径',
  `open_type` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '打开方式',
  `parent_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父类编号',
  `icon` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图标',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `create_by` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `enable` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否开启',
  PRIMARY KEY (`power_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色编号',
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `role_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色标识',
  `enable` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否启用',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `update_by` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `details` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '详情',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_role_power
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_power`;
CREATE TABLE `sys_role_power`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `role_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `power_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_by` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_by` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '编号',
  `username` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '账户',
  `password` char(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `salt` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态',
  `real_name` char(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `email` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `avatar` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `sex` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '性别',
  `phone` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '电话',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `update_by` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `enable` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否启用',
  `login` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否登录',
  `dept_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '部门编号',
  `last_time` datetime(0) NULL DEFAULT NULL COMMENT '最后一次登录时间',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标识',
  `user_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户编号',
  `role_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ts_activity
-- ----------------------------
DROP TABLE IF EXISTS `ts_activity`;
CREATE TABLE `ts_activity`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `airport_code` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '机场三字码',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标题',
  `img_url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '封面图',
  `start_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '开始时间',
  `end_time` timestamp(0) NULL DEFAULT NULL COMMENT '结束时间',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '图文内容',
  `sort` int(4) NULL DEFAULT 0 COMMENT '排序',
  `link` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '跳转链接',
  `status` tinyint(4) NULL DEFAULT 0 COMMENT '0:未发布 1：已经发布 2：失效',
  `create_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 52 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '活动信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ts_airport_group
-- ----------------------------
DROP TABLE IF EXISTS `ts_airport_group`;
CREATE TABLE `ts_airport_group`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `airport_code` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '机场三字码',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '机场名称',
  `english_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '机场英文名字',
  `address` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '机场详细地址',
  `lat` double(10, 7) NOT NULL DEFAULT 0.0000000 COMMENT '纬度',
  `lng` double(10, 7) NOT NULL DEFAULT 0.0000000 COMMENT '经度',
  `city_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '城市编码',
  `area_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '地区编码',
  `mobile` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '带区号的固定电话',
  `phone` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '责任联系人手机号',
  `logo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '机场logo',
  `status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '机场运行状态0:下线，1:上线 ',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `unix_store_code`(`airport_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '店铺信息数据' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ts_airport_group_multi_function
-- ----------------------------
DROP TABLE IF EXISTS `ts_airport_group_multi_function`;
CREATE TABLE `ts_airport_group_multi_function`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `type_id` int(10) NOT NULL COMMENT '类型ID： 1、常用服务 2、爱心服务 3、应急服务 4、便利设施 5、行李服务 6、交通服务 7、乘机须知 8、出行指南 9、辅助服务 10、出发 11、到达 12、中转',
  `sort_id` int(10) NULL DEFAULT NULL COMMENT '排序id',
  `airport_code` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '机场三字码',
  `title` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '标题',
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '子项图片，主用于行程服务推荐图片',
  `img_detail` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '详情页图片',
  `icon` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '图片',
  `link` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '链接',
  `info` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '描述信息',
  `app_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '第三方跳转ID',
  `status` int(2) NULL DEFAULT 0 COMMENT '状态 0、开启 1、关闭',
  `is_characteristic` varchar(5) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '是否特色: T：是 F：否',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 341 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '机场集团多功能' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ts_airport_group_multi_function_copy1
-- ----------------------------
DROP TABLE IF EXISTS `ts_airport_group_multi_function_copy1`;
CREATE TABLE `ts_airport_group_multi_function_copy1`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `type_id` int(10) NOT NULL COMMENT '类型ID： 1、常用服务 2、爱心服务 3、应急服务 4、便利设施 5、行李服务 6、交通服务 7、乘机须知 8、出行指南 9、辅助服务 10、出发 11、到达 12、中转',
  `sort_id` int(10) NULL DEFAULT NULL COMMENT '排序id',
  `airport_code` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '机场三字码',
  `title` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '标题',
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '子项图片，主用于行程服务推荐图片',
  `img_detail` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '详情页图片',
  `icon` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '图片',
  `link` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '链接',
  `info` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '描述信息',
  `app_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '第三方跳转ID',
  `status` int(2) NULL DEFAULT 0 COMMENT '状态 0、开启 1、关闭',
  `is_characteristic` varchar(5) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '是否特色: T：是 F：否',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 240 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '机场集团多功能' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ts_airport_group_multi_function_detail
-- ----------------------------
DROP TABLE IF EXISTS `ts_airport_group_multi_function_detail`;
CREATE TABLE `ts_airport_group_multi_function_detail`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `function_id` int(10) NOT NULL COMMENT '二级子项ID',
  `sort_id` int(11) NULL DEFAULT NULL COMMENT '排序编号',
  `airport_code` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '机场三字码',
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '细分标题',
  `link` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '链接',
  `info` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '内容',
  `status` int(2) NULL DEFAULT 0 COMMENT '状态 0、开启 1、关闭',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 265 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '机场集团功能明细' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ts_airport_group_multi_function_detail_copy1
-- ----------------------------
DROP TABLE IF EXISTS `ts_airport_group_multi_function_detail_copy1`;
CREATE TABLE `ts_airport_group_multi_function_detail_copy1`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `function_id` int(10) NOT NULL COMMENT '二级子项ID',
  `sort_id` int(11) NULL DEFAULT NULL COMMENT '排序编号',
  `airport_code` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '机场三字码',
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '细分标题',
  `link` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '链接',
  `info` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '内容',
  `status` int(2) NULL DEFAULT 0 COMMENT '状态 0、开启 1、关闭',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 222 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '机场集团功能明细' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ts_airport_group_terminal
-- ----------------------------
DROP TABLE IF EXISTS `ts_airport_group_terminal`;
CREATE TABLE `ts_airport_group_terminal`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `airport_code` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '机场三字码',
  `terminal` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '航站楼名称',
  `floor` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '楼层',
  `status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '状态0:下线，1:上线 ',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '机场航站楼和楼层对应表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ts_airport_group_type
-- ----------------------------
DROP TABLE IF EXISTS `ts_airport_group_type`;
CREATE TABLE `ts_airport_group_type`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `type_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '类型编码',
  `sort_id` int(10) NULL DEFAULT NULL COMMENT '排序id',
  `title` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '标题',
  `info` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '信息描述',
  `status` int(2) NULL DEFAULT 0 COMMENT '状态',
  `airport_code` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '机场三字码',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 64 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '首页一级功能列表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ts_banner
-- ----------------------------
DROP TABLE IF EXISTS `ts_banner`;
CREATE TABLE `ts_banner`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `airport_code` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '机场三字码',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标题',
  `img_url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '封面图',
  `start_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '开始时间',
  `end_time` timestamp(0) NULL DEFAULT NULL COMMENT '结束时间',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '图文内容',
  `sort` int(4) NULL DEFAULT 0 COMMENT '排序',
  `link` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '跳转链接',
  `status` tinyint(4) NULL DEFAULT 0 COMMENT '0:未发布 1：已经发布 2：失效',
  `create_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 48 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'banner信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ts_bas_airline
-- ----------------------------
DROP TABLE IF EXISTS `ts_bas_airline`;
CREATE TABLE `ts_bas_airline`  (
  `airline_iata` char(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '航空公司两字码',
  `airline_icao` char(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '航司三字码',
  `airline_chn` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '航司中文全称',
  `airline_chn_brief` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '航司中文简称',
  `airline_eng` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '航司英文名称',
  `airline_nation` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '全拼',
  `domint` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '国际国内标识（D/I/R）',
  `airline_operation` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '航空公司运输业务分类(PA：客运；GD：货运；PG：客货混；BS：公务；GE：通航；OT：其他)',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`airline_iata`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ts_bas_airport
-- ----------------------------
DROP TABLE IF EXISTS `ts_bas_airport`;
CREATE TABLE `ts_bas_airport`  (
  `airport_iata` char(3) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '机场三字码',
  `airport_icao` char(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '机场四字码',
  `airport_chn` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '中文全称',
  `airport_chn_brief` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '中文简称',
  `airport_name_eng` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '英文名称',
  `airport_international_or_domestic` char(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '国际国内标识',
  `airport_nation` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '机场所属的国家',
  `airport_city` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '城市三字码',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ts_bas_airport_copy
-- ----------------------------
DROP TABLE IF EXISTS `ts_bas_airport_copy`;
CREATE TABLE `ts_bas_airport_copy`  (
  `airport_iata` char(3) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '机场三字码',
  `airport_icao` char(4) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '机场四字码',
  `airport_chn` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '中文全称',
  `airport_chn_brief` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '中文简称',
  `airport_name_eng` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '英文名称',
  `airport_international_or_domestic` char(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '国际国内标识',
  `airport_nation` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '机场所属的国家',
  `airport_city` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '城市三字码',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`airport_icao`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ts_notice
-- ----------------------------
DROP TABLE IF EXISTS `ts_notice`;
CREATE TABLE `ts_notice`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `airport_code` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '机场三字码',
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '消息标题',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '消息内容',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '发布时间',
  `status` tinyint(2) NULL DEFAULT NULL COMMENT '状态:0待发布,1已发布',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ts_weather
-- ----------------------------
DROP TABLE IF EXISTS `ts_weather`;
CREATE TABLE `ts_weather`  (
  `airport_code` char(3) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '机场三字码',
  `ariport_name_abbr` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '机场简称',
  `airprot_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '机场全称',
  `pm` varchar(6) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'PM2.5',
  `temperature` varchar(6) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '摄氏温度',
  `weather` varchar(24) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '天气描述',
  `publish_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发布时间',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`airport_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `sex` char(6) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `pwd` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `email` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `version` bigint(20) NOT NULL DEFAULT 1,
  `create_time` timestamp(0) NULL DEFAULT NULL,
  `update_time` timestamp(0) NULL DEFAULT NULL,
  `flag` int(10) UNSIGNED NOT NULL DEFAULT 0,
  `age` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 67 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
