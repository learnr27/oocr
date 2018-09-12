-- ----------------------------
-- Table structure for bank_card
-- ----------------------------
DROP TABLE IF EXISTS `bank_card`;
CREATE TABLE `bank_card` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bank_card_number` varchar(50) DEFAULT '' COMMENT '银行卡卡号',
  `bank_name` varchar(50) DEFAULT '' COMMENT '银行名',
  `bank_card_type` char(2) DEFAULT '' COMMENT '银行卡类型，0:不能识别; 1: 借记卡; 2: 信用卡',
  `phone` varchar(20) DEFAULT '' COMMENT '手机号码',
  `file_path` varchar(100) DEFAULT '',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='银行卡';

-- ----------------------------
-- Table structure for business_license
-- ----------------------------
DROP TABLE IF EXISTS `business_license`;
CREATE TABLE `business_license` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `company_name` varchar(200) DEFAULT '' COMMENT '单位名称',
  `legal_person` varchar(20) DEFAULT '' COMMENT '法人',
  `address` varchar(150) DEFAULT '' COMMENT '地址',
  `valid_period` varchar(20) DEFAULT '' COMMENT '有效期',
  `license_no` varchar(32) DEFAULT '' COMMENT '证件编号',
  `social_credit_code` varchar(50) DEFAULT '' COMMENT '社会信用代码',
  `file_path` varchar(100) DEFAULT '' COMMENT '文件路径',
  `phone` varchar(20) DEFAULT '' COMMENT '手机号码',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='营业执照';

-- ----------------------------
-- Table structure for driving_license
-- ----------------------------
DROP TABLE IF EXISTS `driving_license`;
CREATE TABLE `driving_license` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `license_no` varchar(50) DEFAULT '' COMMENT '证件号',
  `name` varchar(50) DEFAULT '' COMMENT '姓名',
  `sex` varchar(2) DEFAULT '' COMMENT '性别',
  `nationality` varchar(20) DEFAULT '' COMMENT '国籍',
  `address` varchar(150) DEFAULT '' COMMENT '地址',
  `birthday` varchar(20) DEFAULT '' COMMENT '出生日期',
  `first_issue` varchar(20) DEFAULT '' COMMENT '初次领证日期',
  `driving_type` varchar(20) DEFAULT '' COMMENT '准驾车型',
  `valid_for` varchar(20) DEFAULT '' COMMENT '有效期限',
  `valid_from` varchar(20) DEFAULT '' COMMENT '有效起始日期',
  `file_path` varchar(100) DEFAULT '' COMMENT '文件路径',
  `phone` varchar(20) DEFAULT '' COMMENT '手机号码',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='驾驶证';

-- ----------------------------
-- Table structure for id_card
-- ----------------------------
DROP TABLE IF EXISTS `id_card`;
CREATE TABLE `id_card` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(150) DEFAULT '' COMMENT '地址',
  `card_no` varchar(20) DEFAULT '' COMMENT '公民身份号码',
  `birthday` char(100) DEFAULT '' COMMENT '生日',
  `name` varchar(50) DEFAULT '' COMMENT '姓名',
  `gender` char(2) DEFAULT '' COMMENT '性别 男 女',
  `nation` varchar(20) DEFAULT '' COMMENT '民族',
  `file_path` varchar(100) DEFAULT '',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='身份证';

-- ----------------------------
-- Table structure for vehicle_license
-- ----------------------------
DROP TABLE IF EXISTS `vehicle_license`;
CREATE TABLE `vehicle_license` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `plate_no` varchar(20) DEFAULT '' COMMENT '车牌号',
  `vehicle_type` varchar(20) DEFAULT '' COMMENT '车辆类型',
  `vehicle_owner` varchar(50) DEFAULT '' COMMENT '所有人',
  `address` varchar(150) DEFAULT '' COMMENT '地址',
  `use_character` varchar(100) DEFAULT '' COMMENT '使用性质',
  `brand_model` varchar(100) DEFAULT '' COMMENT '品牌型号',
  `vin` varchar(100) DEFAULT '' COMMENT '车辆识别代号',
  `engine_no` varchar(100) DEFAULT '' COMMENT '发动机号码',
  `register_date` varchar(20) DEFAULT '' COMMENT '注册日期',
  `issue_date` varchar(20) DEFAULT '' COMMENT '发证日期',
  `file_path` varchar(150) DEFAULT '' COMMENT '文件路径',
  `phone` varchar(20) DEFAULT '' COMMENT '手机号码',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='行驶证';
