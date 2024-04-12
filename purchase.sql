/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.7.30-log : Database - purchase
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE = ''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS */`purchase` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `purchase`;

/*Table structure for table `academy_category` */

DROP TABLE IF EXISTS `academy_category`;

CREATE TABLE `academy_category`
(
    `cid`           int(11) NOT NULL AUTO_INCREMENT COMMENT '学院信息表 主键',
    `sid`           int(11)      DEFAULT NULL COMMENT '外键 学校id',
    `academy_name`  varchar(30)  DEFAULT NULL COMMENT '学院名称',
    `budget`        int(20)      DEFAULT NULL COMMENT '一学期 预算开支',
    `count`         int(30)      DEFAULT NULL COMMENT '银行账号',
    `maximum_order` int(10)      DEFAULT NULL COMMENT '单笔最大金额',
    `tag`           varchar(255) DEFAULT NULL,
    PRIMARY KEY (`cid`),
    KEY `sid` (`sid`),
    CONSTRAINT `academy_category_ibfk_1` FOREIGN KEY (`sid`) REFERENCES `school_category` (`sid`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 16
  DEFAULT CHARSET = utf8;

/*Data for the table `academy_category` */

insert into `academy_category`(`cid`, `sid`, `academy_name`, `budget`, `count`, `maximum_order`, `tag`)
values (4, 1, '数学与计算机学院', 50000, NULL, 50000, NULL),
       (5, 1, '生化学院', 100000, NULL, 50000, NULL),
       (8, 1, '学院名', 0, NULL, 0, NULL),
       (9, 1, '土建学院', 50000, NULL, 20000, NULL),
       (15, 1, '艺术学院', 50000, NULL, 50000, NULL);

/*Table structure for table `apply_board` */

DROP TABLE IF EXISTS `apply_board`;

CREATE TABLE `apply_board`
(
    `id`        int(10) NOT NULL AUTO_INCREMENT,
    `startTime` date    NOT NULL,
    `endTime`   date    NOT NULL,
    `boardname` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 20
  DEFAULT CHARSET = utf8;

/*Data for the table `apply_board` */

insert into `apply_board`(`id`, `startTime`, `endTime`, `boardname`)
values (19, '2022-04-03', '2022-04-15', '2022年攀枝花学院春季征集实验耗材采购供应商公告');

/*Table structure for table `applyForShortlist` */

DROP TABLE IF EXISTS `applyForShortlist`;

CREATE TABLE `applyForShortlist`
(
    `id`                          int(11) NOT NULL AUTO_INCREMENT COMMENT '申请表主键',
    `sid`                         int(11)      DEFAULT NULL COMMENT '外键 供应商id',
    `scid`                        int(11)      DEFAULT NULL COMMENT '外键学校id',
    `date`                        date         DEFAULT NULL COMMENT '申请时间',
    `recommends`                  varchar(30)  DEFAULT NULL COMMENT '推荐人 姓名，用逗号隔开',
    `academy_access`              varchar(11)  DEFAULT NULL COMMENT '是否通过，用逗号隔开',
    `school_administrator_access` int(11)      DEFAULT NULL COMMENT '学校管理员是否通过',
    `causes`                      varchar(255) DEFAULT NULL COMMENT '原因 用逗号隔开',
    `tag`                         varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `Apply_for_shortlist` (`sid`),
    KEY `scid` (`scid`),
    CONSTRAINT `Apply_for_shortlist` FOREIGN KEY (`sid`) REFERENCES `supplier` (`id`),
    CONSTRAINT `apply_for_shortlist_ibfk_1` FOREIGN KEY (`scid`) REFERENCES `school_category` (`sid`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 30
  DEFAULT CHARSET = utf8;

/*Data for the table `applyForShortlist` */

insert into `applyForShortlist`(`id`, `sid`, `scid`, `date`, `recommends`, `academy_access`,
                                `school_administrator_access`, `causes`, `tag`)
values (15, 33, 1, '2021-09-17', '', '1', 1, '', ''),
       (16, 41, 1, '2021-09-24', '', '1', 1, '', ''),
       (17, 42, 1, '2021-09-30', '', '1', 1, '', ''),
       (18, 43, 1, '2021-10-01', '', '1', 1, '', ''),
       (19, 44, 1, '2021-10-02', '', '1', 1, '', ''),
       (20, 45, 1, '2021-10-04', '', '1', 1, '', ''),
       (28, 46, 1, '2022-04-04', '', '1', 1, '', ''),
       (29, 47, 1, '2022-04-04', '', '1', 1, '', '');

/*Table structure for table `bidding_application` */

DROP TABLE IF EXISTS `bidding_application`;

CREATE TABLE `bidding_application`
(
    `id`           int(11) NOT NULL AUTO_INCREMENT,
    `sid`          int(11)      DEFAULT NULL COMMENT '外键 供应商id',
    `purid`        int(11)      DEFAULT NULL COMMENT '外键 采购名单id',
    `data`         date         DEFAULT NULL COMMENT '申请时间',
    `product_name` varchar(255) DEFAULT NULL COMMENT '产品具体名称',
    `realy_price`  int(11)      DEFAULT NULL COMMENT '商家出价',
    `prescribe`    varchar(255) DEFAULT NULL COMMENT '产品描述',
    `tag`          varchar(255) DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`),
    KEY `sid` (`sid`),
    KEY `purid` (`purid`),
    CONSTRAINT `bidding_application_ibfk_1` FOREIGN KEY (`sid`) REFERENCES `supplier` (`id`),
    CONSTRAINT `bidding_application_ibfk_2` FOREIGN KEY (`purid`) REFERENCES `purchasing_items` (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 123
  DEFAULT CHARSET = utf8;

/*Data for the table `bidding_application` */

insert into `bidding_application`(`id`, `sid`, `purid`, `data`, `product_name`, `realy_price`, `prescribe`, `tag`)
values (100, 41, 82, '2021-10-18', '酷睿冰尊A9电脑散热器', 800, '六风扇迅猛动力，静音不噪，主流尺寸兼容', '无'),
       (101, 41, 87, '2022-02-05', 'HP惠普鼠标垫办公家用加厚锁边笔记本电脑垫', 250, '柔和顺滑，天然橡胶，精密锁边', '无'),
       (102, 42, 83, '2021-10-30', 'WD西部数据 台式机电脑2T机械硬盘3.5英寸SATA3硬盘5400转 7200转', 6850,
        '台式电脑理想伴侣，提升PC储存容量', '无'),
       (103, 42, 82, '2021-10-20', '酷睿冰尊A9电脑散热器', 850, '六风扇迅猛动力，静音不噪，主流尺寸兼容', '无'),
       (104, 42, 86, '2022-01-01', 'Dell/戴尔电脑键盘有线USB台式笔记本办公商务打字专用外接KB216', 625,
        'chiclet式按键布局，标准104键设计', '无'),
       (105, 43, 84, '2021-11-10', '惠普S1内存条8G单条DDR4 2666 3200兼容2400 2133MHz', 5800,
        '新一代内存规格，性能强悍，大幅提升电脑性能', '无'),
       (106, 43, 82, '2021-10-21', '酷睿冰尊A9电脑散热器', 825, '六风扇迅猛动力，静音不噪，主流尺寸兼容', '无'),
       (107, 43, 85, '2021-12-11', 'Dell/戴尔鼠标有线USB办公游戏cf可用商务MS116原装笔记本台式电脑鼠标', 300,
        '人体工程学设计，重力系统配置，高性能光学引擎', '无'),
       (108, 44, 85, '2021-12-07', 'Dell/戴尔鼠标有线USB办公游戏cf可用商务MS116原装笔记本台式电脑鼠标', 295,
        '人体工程学设计，重力系统配置，高性能光学引擎', '无'),
       (109, 44, 88, '2022-03-05',
        'intel英特尔酷睿十代I5 10400F/I5 10400 CPU+技嘉H410/H510/B560小雕/雪雕电脑游戏主板CPU套装', 29500,
        '多任务秒切换，提升电脑体验，更流畅不卡顿', '无'),
       (110, 44, 87, '2022-02-10', 'HP惠普鼠标垫办公家用加厚锁边笔记本电脑垫', 180, '柔和顺滑，天然橡胶，精密锁边', '无'),
       (111, 45, 88, '2022-03-09',
        'intel英特尔酷睿十代I5 10400F/I5 10400 CPU+技嘉H410/H510/B560小雕/雪雕电脑游戏主板CPU套装', 29000,
        '多任务秒切换，提升电脑体验，更流畅不卡顿', '无'),
       (112, 45, 84, '2021-11-07', '惠普S1内存条8G单条DDR4 2666 3200兼容2400 2133MHz', 5900,
        '新一代内存规格，性能强悍，大幅提升电脑性能', '无'),
       (113, 45, 85, '2021-12-10',
        'Dell/戴尔鼠标有线USB办公游戏cf可用商务MS116原装笔记本台式电脑鼠标 （Dell/戴尔MS116有线鼠标）', 300,
        '人体工程学设计，重力系统配置，高性能光学引擎', '无'),
       (114, 41, 83, '2021-10-31', 'WD西部数据 台式机电脑2T机械硬盘3.5英寸SATA3硬盘5400转 7200转', 6950,
        '台式电脑理想伴侣，提升PC储存容量', '无'),
       (115, 44, 86, '2021-12-08', 'Dell/戴尔电脑键盘鼠标套装有线USB台式笔记本办公商务打字专用外接KB216', 630,
        'chiclet式按键布局，标准104键设计', '无'),
       (116, 42, 85, '2021-12-09', 'Dell/戴尔鼠标有线USB办公游戏cf可用商务MS116原装笔记本台式电脑鼠标', 320,
        '人体工程学设计，重力系统配置，高性能光学引擎', '无'),
       (119, 46, 94, '2022-04-04', '绿联VGA线电脑主机显示器投影仪连接线VGA公对公视频延长数据线1.5米', 380,
        '镀锡铜线芯 1080P 镀金接口 磁环屏蔽 3+9芯', '无'),
       (120, 47, 94, '2022-04-04', '绿联vga线电脑显示器连接线数据传输信号延长视频线1.5米', 365,
        '1080P高清 稳定清晰 拒绝蓝屏/闪屏/拖影等', '无'),
       (121, 46, 95, '2022-04-13', '12', 12, '12', 'wu');

/*Table structure for table `bidding_application_copy` */

DROP TABLE IF EXISTS `bidding_application_copy`;

CREATE TABLE `bidding_application_copy`
(
    `id`           int(11) NOT NULL AUTO_INCREMENT,
    `sid`          int(11)      DEFAULT NULL COMMENT '外键 供应商id',
    `purid`        int(11)      DEFAULT NULL COMMENT '外键 采购名单id',
    `data`         varchar(8)   DEFAULT NULL COMMENT '申请时间',
    `product_name` varchar(255) DEFAULT NULL COMMENT '产品具体名称',
    `realy_price`  int(11)      DEFAULT NULL COMMENT '商家出价',
    `prescribe`    varchar(255) DEFAULT NULL COMMENT '产品描述',
    `tag`          varchar(255) DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`),
    KEY `sid` (`sid`),
    KEY `purid` (`purid`),
    CONSTRAINT `bidding_application_copy_ibfk_1` FOREIGN KEY (`sid`) REFERENCES `supplier` (`id`),
    CONSTRAINT `bidding_application_copy_ibfk_2` FOREIGN KEY (`purid`) REFERENCES `purchasing_items` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/*Data for the table `bidding_application_copy` */

/*Table structure for table `board_result` */

DROP TABLE IF EXISTS `board_result`;

CREATE TABLE `board_result`
(
    `id`                   int(11)      NOT NULL,
    `rid`                  int(11)      DEFAULT NULL,
    `purchase_name`        varchar(255) DEFAULT NULL,
    `site_inspection_time` datetime     NOT NULL,
    `qualifications`       varchar(255) DEFAULT NULL,
    `notifiedcation`       varchar(255) DEFAULT NULL,
    `errol_contract`       varchar(255) DEFAULT NULL,
    `errol_tel`            varchar(255) DEFAULT NULL,
    `errol_type`           varchar(255) DEFAULT NULL,
    `inspection_contract`  varchar(255) DEFAULT NULL,
    `inspection_tel`       varchar(255) DEFAULT NULL,
    `click_count`          int(11)      DEFAULT NULL,
    `file_place`           varchar(255) DEFAULT NULL,
    `tag`                  varchar(255) NOT NULL,
    PRIMARY KEY (`id`),
    KEY `rid` (`rid`),
    CONSTRAINT `board_result_ibfk_1` FOREIGN KEY (`rid`) REFERENCES `result` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/*Data for the table `board_result` */

/*Table structure for table `president` */

DROP TABLE IF EXISTS `president`;

CREATE TABLE `president`
(
    `id`            int(11)     NOT NULL AUTO_INCREMENT COMMENT '院长 id',
    `cid`           int(11)      DEFAULT NULL COMMENT '外键学院id',
    `sid`           int(11)      DEFAULT NULL COMMENT '外键 学校id',
    `contract_name` varchar(30)  DEFAULT NULL COMMENT '联系人姓名',
    `tel`           varchar(11)  DEFAULT NULL COMMENT '电话号码',
    `count`         varchar(18)  DEFAULT NULL COMMENT '院长账户',
    `name`          varchar(30) NOT NULL COMMENT '用户名',
    `pwd`           varchar(30)  DEFAULT NULL COMMENT '密码',
    `tag`           varchar(255) DEFAULT NULL,
    `last_time`     date         DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `president` (`cid`),
    KEY `schoolPreKey` (`sid`),
    CONSTRAINT `president` FOREIGN KEY (`cid`) REFERENCES `academy_category` (`cid`),
    CONSTRAINT `schoolPreKey` FOREIGN KEY (`sid`) REFERENCES `school_category` (`sid`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 18
  DEFAULT CHARSET = utf8;

/*Data for the table `president` */

insert into `president`(`id`, `cid`, `sid`, `contract_name`, `tel`, `count`, `name`, `pwd`, `tag`, `last_time`)
values (4, 4, 1, '张清学', '1584235035', '242364326435754725', 'president', '123456', NULL, NULL),
       (5, 5, 1, '刘老师', '1584235035', '242364326435754725', 'president1', '123456', NULL, NULL),
       (11, 9, 1, '土建管理员', '1584235035', '242364326435754725', 'tujian', '123456', NULL, NULL),
       (17, 15, 1, 'shini', '1584235035', '242364326435754725', 'nihao', '123456', NULL, '2022-03-15');

/*Table structure for table `product_category` */

DROP TABLE IF EXISTS `product_category`;

CREATE TABLE `product_category`
(
    `pid`          int(11) NOT NULL AUTO_INCREMENT COMMENT '产品类别 主键',
    `product_name` varchar(30)  DEFAULT NULL COMMENT '产品名称',
    `types`        varchar(255) DEFAULT NULL COMMENT '有那些具体类别',
    `sid`          int(11)      DEFAULT NULL COMMENT '外键 学校id',
    `tag`          varchar(255) DEFAULT NULL,
    PRIMARY KEY (`pid`),
    KEY `sid` (`sid`),
    CONSTRAINT `product_category_ibfk_1` FOREIGN KEY (`sid`) REFERENCES `school_category` (`sid`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 7
  DEFAULT CHARSET = utf8;

/*Data for the table `product_category` */

insert into `product_category`(`pid`, `product_name`, `types`, `sid`, `tag`)
values (1, '体育类', '体育类', 1, '乒乓球等'),
       (2, '计算机类', '计算机类', 1, '电脑'),
       (3, '化学类', '化学类', 1, '试管'),
       (4, '土建类', '土建类', 1, '测量工具'),
       (5, '艺术类', '艺术类', 1, '画板'),
       (6, '其他类', '其他', 1, '服装');

/*Table structure for table `purchase_apply` */

DROP TABLE IF EXISTS `purchase_apply`;

CREATE TABLE `purchase_apply`
(
    `id`            int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `cid`           int(11)      DEFAULT NULL COMMENT '外键 学院id',
    `pid`           int(11)      DEFAULT NULL COMMENT '外键  产品类别id',
    `sid`           int(11)      DEFAULT NULL COMMENT '外键学校id',
    `apply_time`    varchar(8)   DEFAULT NULL COMMENT '截至时间',
    `product_name`  varchar(100) DEFAULT NULL COMMENT '产品名称',
    `highest_price` int(11)      DEFAULT NULL COMMENT '最高价格',
    `specified`     int(11)      DEFAULT '1' COMMENT '是否需求精确  ，就是代表 要一类（苹果手机）的还是一类中的哪一个（苹果6s 2015生产 ）',
    `reason`        varchar(255) DEFAULT NULL COMMENT '申请原因，备注',
    `tag`           varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/*Data for the table `purchase_apply` */

/*Table structure for table `purchaser` */

DROP TABLE IF EXISTS `purchaser`;

CREATE TABLE `purchaser`
(
    `id`            int(11)     NOT NULL AUTO_INCREMENT COMMENT '主键',
    `cid`           int(11)      DEFAULT NULL COMMENT '外键 学院id',
    `prid`          int(11)     NOT NULL COMMENT '外键 院长id',
    `sid`           int(11)      DEFAULT NULL COMMENT '外键学校id',
    `contract_name` varchar(30)  DEFAULT NULL COMMENT '联系人姓名',
    `tel`           varchar(11) NOT NULL COMMENT '电话号码',
    `count`         varchar(18)  DEFAULT NULL COMMENT '采购者账户',
    `name`          varchar(30) NOT NULL COMMENT '用户名',
    `pwd`           varchar(30)  DEFAULT NULL COMMENT '密码',
    `tag`           varchar(255) DEFAULT NULL,
    `last_time`     date         DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `purchaser` (`cid`),
    KEY `id` (`id`),
    KEY `3` (`prid`),
    KEY `guishu` (`sid`),
    CONSTRAINT `3` FOREIGN KEY (`prid`) REFERENCES `president` (`id`),
    CONSTRAINT `guishu` FOREIGN KEY (`sid`) REFERENCES `school_category` (`sid`),
    CONSTRAINT `purchaser` FOREIGN KEY (`cid`) REFERENCES `academy_category` (`cid`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 8
  DEFAULT CHARSET = utf8 COMMENT ='InnoDB free: 3072 kB; (`sid`) REFER `purchase/school_categor';

/*Data for the table `purchaser` */

insert into `purchaser`(`id`, `cid`, `prid`, `sid`, `contract_name`, `tel`, `count`, `name`, `pwd`, `tag`, `last_time`)
values (5, 4, 4, 1, '李晨', '1584235035', '242364326435754725', 'purchase', '123456', NULL, NULL),
       (7, 5, 5, 1, 'shini', '1584235035', '242364326435754725', 'nihao', '123456', NULL, NULL);

/*Table structure for table `purchasing_items` */

DROP TABLE IF EXISTS `purchasing_items`;

CREATE TABLE `purchasing_items`
(
    `id`                   int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `cid`                  int(11)      DEFAULT NULL COMMENT '外键 学院id',
    `pid`                  int(11)      DEFAULT NULL COMMENT '外键  产品类别id',
    `sid`                  int(11)      DEFAULT NULL COMMENT '外键学校id',
    `apply_time`           date         DEFAULT NULL COMMENT '截至时间',
    `product_name`         varchar(100) DEFAULT NULL COMMENT '产品名称',
    `highest_price`        int(11)      DEFAULT NULL COMMENT '最高价格',
    `specified`            int(11)      DEFAULT '1' COMMENT '是否需求精确  ，就是代表 要一类（苹果手机）的还是一类中的哪一个（苹果6s 2015生产 ）',
    `reason`               varchar(255) DEFAULT NULL COMMENT '申请原因，备注',
    `tag`                  varchar(255) DEFAULT NULL,
    `purchase_name`        varchar(255) DEFAULT NULL,
    `publish_time`         datetime     DEFAULT NULL,
    `start_time`           datetime     DEFAULT NULL,
    `end_time`             datetime     DEFAULT NULL,
    `title`                varchar(255) DEFAULT NULL,
    `purpose`              varchar(255) DEFAULT NULL,
    `site`                 varchar(255) DEFAULT NULL,
    `site_inspection_time` datetime     DEFAULT NULL,
    `qualifications`       varchar(255) DEFAULT NULL,
    `notifiedcation`       varchar(255) DEFAULT NULL,
    `errol_contract`       varchar(255) DEFAULT NULL,
    `errol_tel`            varchar(255) DEFAULT NULL,
    `errol_type`           int(255)     DEFAULT NULL COMMENT '0购买公告1为拍卖公告',
    `inspection_contract`  varchar(255) DEFAULT NULL,
    `inspection_tel`       varchar(255) DEFAULT NULL,
    `click_count`          int(11)      DEFAULT '0',
    `file_place`           varchar(255) DEFAULT NULL,
    `rules`                varchar(255) DEFAULT NULL,
    `auction_site`         varchar(255) DEFAULT NULL,
    `is_result`            int(11)      DEFAULT '0' COMMENT '是否有结果',
    `others`               varchar(255) DEFAULT NULL,
    `acceess`              int(2)       DEFAULT '0' COMMENT '是否通过',
    PRIMARY KEY (`id`),
    KEY `cid` (`cid`),
    KEY `pid` (`pid`),
    KEY `sid` (`sid`),
    CONSTRAINT `purchasing_items_ibfk_1` FOREIGN KEY (`cid`) REFERENCES `academy_category` (`cid`),
    CONSTRAINT `purchasing_items_ibfk_2` FOREIGN KEY (`pid`) REFERENCES `product_category` (`pid`),
    CONSTRAINT `purchasing_items_ibfk_3` FOREIGN KEY (`sid`) REFERENCES `school_category` (`sid`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 96
  DEFAULT CHARSET = utf8;

/*Data for the table `purchasing_items` */

insert into `purchasing_items`(`id`, `cid`, `pid`, `sid`, `apply_time`, `product_name`, `highest_price`, `specified`,
                               `reason`, `tag`, `purchase_name`, `publish_time`, `start_time`, `end_time`, `title`,
                               `purpose`, `site`, `site_inspection_time`, `qualifications`, `notifiedcation`,
                               `errol_contract`, `errol_tel`, `errol_type`, `inspection_contract`, `inspection_tel`,
                               `click_count`, `file_place`, `rules`, `auction_site`, `is_result`, `others`, `acceess`)
values (82, 4, 2, 1, '2021-10-16', '电脑散热器', 900, NULL, '配置实验室计算机', '20', '电脑散热器招标公告',
        '2021-10-16 11:46:42', '2021-10-16 00:00:00', '2021-10-23 00:00:00', NULL, '酷睿冰尊A9电脑散热器', NULL, NULL,
        '有正规营业执照，身份证复印件及其他资质证明', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        '先通过网上申请竞标,最低价中标', NULL, 1, '无', 11),
       (83, 4, 2, 1, '2021-10-26', '台式机电脑机械硬盘', 7000, NULL, '配置实验室计算机', '20',
        '台式机电脑机械硬盘招标公告', '2021-10-26 11:48:17', '2021-10-26 00:00:00', '2021-11-01 00:00:00', NULL,
        'WD西部数据 台式机电脑2T机械硬盘3.5英寸SATA3硬盘5400转 7200转', NULL, NULL,
        '有正规营业执照，身份证复印件及其他资质证明', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        '先通过网上申请竞标,最低价中标', NULL, 1, '无', 11),
       (84, 4, 2, 1, '2021-11-04', '内存条', 6000, NULL, '配置实验室计算机', '20', '内存条招标公告',
        '2021-11-04 11:49:35', '2021-11-04 00:00:00', '2021-11-14 00:00:00', NULL,
        '惠普S1内存条8G单条DDR4 2666 3200兼容2400 2133MHz', NULL, NULL, '有正规营业执照，身份证复印件及其他资质证明',
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '先通过网上申请竞标,最低价中标', NULL, 1, '无', 11),
       (85, 4, 2, 1, '2021-12-01', '有线鼠标', 350, NULL, '配置实验室计算机', '20', '有线鼠标招标公告',
        '2021-12-01 11:51:29', '2021-12-01 00:00:00', '2021-12-15 00:00:00', NULL, 'Dell/戴尔MS116有线鼠标', NULL, NULL,
        '有正规营业执照，身份证复印件及其他资质证明', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        '先通过网上申请竞标,最低价中标', NULL, 1, '无', 11),
       (86, 4, 2, 1, '2021-12-28', '电脑有线键盘', 650, NULL, '配置实验室计算机', '20', '电脑有线键盘招标公告',
        '2021-12-28 11:51:47', '2021-12-28 00:00:00', '2022-01-05 00:00:00', NULL, 'Dell/戴尔KB216电脑有线键盘', NULL,
        NULL, '有正规营业执照，身份证复印件及其他资质证明', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        '先通过网上申请竞标,最低价中标', NULL, 1, '无', 11),
       (87, 4, 2, 1, '2022-02-03', '鼠标垫', 200, NULL, '配置实验室计算机', '20', '鼠标垫招标公告',
        '2022-02-03 11:52:12', '2022-02-03 00:00:00', '2022-02-16 00:00:00', NULL, 'HP惠普鼠标垫360mm*280nm', NULL,
        NULL, '有正规营业执照，身份证复印件及其他资质证明', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        '先通过网上申请竞标,最低价中标', NULL, 1, '无', 11),
       (88, 4, 2, 1, '2022-03-03', 'CPU', 900, NULL, '配置实验室计算机', '20', 'CPU招标公告', '2022-03-03 11:52:36',
        '2022-03-03 00:00:00', '2022-03-12 00:00:00', NULL, 'intel英特尔酷睿十代I5 CPU 6核12线程 2.90GHZ', NULL, NULL,
        '有正规营业执照，身份证复印件及其他资质证明', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        '先通过网上申请竞标,最低价中标', NULL, 1, '无', 11),
       (94, 4, 2, 1, '2022-04-04', '电脑显示器连接线', 400, NULL, '配置实验室计算机', '20', '电脑显示器连接线招标公告',
        '2022-04-04 22:21:02', '2022-04-04 00:00:00', '2022-04-05 00:00:00', NULL, '绿联VGA电脑显示器公对公连接线1.5米',
        NULL, NULL, '有正规营业执照，身份证复印件及其他资质证明', NULL, NULL, NULL, 89, NULL, NULL, NULL, NULL,
        '先通过网上申请竞标,最低价中标', NULL, 1, '无', 11),
       (95, 4, 2, 1, '2022-04-13', '111', 900, NULL, '配置实验室计算机', '20', '111招标公告', '2022-04-13 16:12:39',
        '2022-04-13 16:12:27', '2022-04-13 16:12:27', NULL, '111', NULL, NULL,
        '有正规营业执照，身份证复印件及其他资质证明', NULL, NULL, NULL, 95, NULL, NULL, NULL, NULL,
        '先通过网上申请竞标,最低价中标', NULL, 1, NULL, 11);

/*Table structure for table `result` */

DROP TABLE IF EXISTS `result`;

CREATE TABLE `result`
(
    `id`                  int(11)      NOT NULL AUTO_INCREMENT COMMENT '主键',
    `pid`                 int(11)      DEFAULT NULL COMMENT '外键 商品id',
    `sid`                 int(11)      DEFAULT NULL COMMENT '外键 供应商id',
    `scis`                int(11)      DEFAULT NULL COMMENT '外键 学校id',
    `Purchase_id`         int(11)      DEFAULT NULL COMMENT '外键 采购者id',
    `actual_product_name` varchar(100) NOT NULL COMMENT '实际物品名称',
    `reality_price`       int(11)      DEFAULT NULL COMMENT '实际价格',
    `date`                date         DEFAULT NULL,
    `tag`                 varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `sid` (`sid`),
    KEY `pid` (`pid`),
    KEY `Purchase_id` (`Purchase_id`),
    KEY `4` (`scis`),
    CONSTRAINT `4` FOREIGN KEY (`scis`) REFERENCES `school_category` (`sid`),
    CONSTRAINT `result_ibfk_1` FOREIGN KEY (`sid`) REFERENCES `supplier` (`id`),
    CONSTRAINT `result_ibfk_2` FOREIGN KEY (`pid`) REFERENCES `purchasing_items` (`id`),
    CONSTRAINT `result_ibfk_3` FOREIGN KEY (`Purchase_id`) REFERENCES `purchaser` (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 41
  DEFAULT CHARSET = utf8;

/*Data for the table `result` */

insert into `result`(`id`, `pid`, `sid`, `scis`, `Purchase_id`, `actual_product_name`, `reality_price`, `date`, `tag`)
values (26, 82, 41, 1, 5, '电脑散热器', 800, '2021-10-23', NULL),
       (27, 83, 42, 1, 5, '台式机电脑机械硬盘', 6850, '2021-11-01', NULL),
       (28, 84, 43, 1, 5, '内存条', 5800, '2021-11-14', NULL),
       (29, 85, 44, 1, 5, '有线鼠标', 295, '2021-12-15', NULL),
       (30, 86, 42, 1, 5, '电脑有线键盘', 625, '2022-01-05', NULL),
       (31, 87, 44, 1, 5, '鼠标垫', 180, '2022-02-16', NULL),
       (33, 88, 45, 1, 5, 'CPU', 29000, '2022-03-12', NULL),
       (38, 94, 47, 1, 5, '电脑显示器连接线', 365, '2022-04-05', NULL),
       (40, 95, NULL, 1, 5, '111', 12, '2022-04-13', NULL);

/*Table structure for table `school_administrator` */

DROP TABLE IF EXISTS `school_administrator`;

CREATE TABLE `school_administrator`
(
    `id`            int(11) NOT NULL AUTO_INCREMENT COMMENT '学校管理员 主键',
    `sid`           int(11)      DEFAULT NULL COMMENT '外键 学校id',
    `contract_name` varchar(30)  DEFAULT NULL COMMENT '联系人姓名',
    `tel`           varchar(11)  DEFAULT NULL COMMENT '电话号码',
    `count`         varchar(18)  DEFAULT NULL COMMENT '学校账户',
    `name`          varchar(30)  DEFAULT NULL COMMENT '用户名',
    `pwd`           varchar(30)  DEFAULT NULL COMMENT '密码',
    `tag`           varchar(255) DEFAULT NULL,
    `last_time`     date         DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `sid` (`sid`),
    CONSTRAINT `school_administrator_ibfk_2` FOREIGN KEY (`sid`) REFERENCES `school_category` (`sid`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 7
  DEFAULT CHARSET = utf8;

/*Data for the table `school_administrator` */

insert into `school_administrator`(`id`, `sid`, `contract_name`, `tel`, `count`, `name`, `pwd`, `tag`, `last_time`)
values (1, 1, '聂梅', '15388494045', '2314252365236', 'school', '123456', '攀枝花学院管理员', '2022-03-05'),
       (5, 14, 'dfas', '1584235035', '242364326435754725', 'dianda', '111111', '电子科技大学管理员', '2022-03-15'),
       (6, 15, 'shini', '1584235035', '242364326435754725', 'nihao', '111111', '备注', '2022-03-15');

/*Table structure for table `school_category` */

DROP TABLE IF EXISTS `school_category`;

CREATE TABLE `school_category`
(
    `sid`         int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `school_name` varchar(30)  DEFAULT NULL COMMENT '学校名称',
    `tag`         varchar(255) DEFAULT NULL,
    PRIMARY KEY (`sid`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 17
  DEFAULT CHARSET = utf8;

/*Data for the table `school_category` */

insert into `school_category`(`sid`, `school_name`, `tag`)
values (1, '攀枝花学院', '四川省攀枝花市'),
       (14, '电子科技大学', '电子科技大学管理员'),
       (15, '四川大学', '备注'),
       (16, '四川大学1', '备注');

/*Table structure for table `supper` */

DROP TABLE IF EXISTS `supper`;

CREATE TABLE `supper`
(
    `id`            int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name`          varchar(30)  DEFAULT NULL COMMENT '用户名',
    `pwd`           varchar(30)  DEFAULT NULL COMMENT '密码',
    `contract_name` varchar(30)  DEFAULT NULL COMMENT '联系人姓名',
    `tel`           varchar(11)  DEFAULT NULL COMMENT '电话号码',
    `tag`           varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8;

/*Data for the table `supper` */

insert into `supper`(`id`, `name`, `pwd`, `contract_name`, `tel`, `tag`)
values (1, 'supper', '123456', '李佳辕', '15388494045', '超级管理员');

/*Table structure for table `supplier` */

DROP TABLE IF EXISTS `supplier`;

CREATE TABLE `supplier`
(
    `id`                 int(11)     NOT NULL AUTO_INCREMENT COMMENT '主键',
    `sid`                int(11)      DEFAULT NULL COMMENT '外键学校 id',
    `cid`                int(11)      DEFAULT NULL COMMENT '商品类别',
    `contract_name`      varchar(30)  DEFAULT NULL COMMENT '联系姓名',
    `tel`                varchar(11)  DEFAULT NULL COMMENT '电话号码',
    `name`               varchar(30) NOT NULL COMMENT '用户名',
    `pwd`                varchar(30) NOT NULL COMMENT '密码',
    `account`            varchar(30)  DEFAULT NULL COMMENT '银行账号',
    `company_name`       varchar(30)  DEFAULT NULL COMMENT '公司名称',
    `company_place`      varchar(30)  DEFAULT NULL COMMENT '公司位置',
    `access`             int(1)       DEFAULT NULL COMMENT '是否通过竞标',
    `corporation`        varchar(30)  DEFAULT NULL COMMENT '法人姓名',
    `corporation_tel`    varchar(11)  DEFAULT NULL COMMENT '法人电话',
    `brief_introduction` varchar(255) DEFAULT NULL COMMENT '公司简介',
    `tag`                varchar(255) DEFAULT NULL,
    `last_time`          date         DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `supplier` (`cid`),
    KEY `2` (`sid`),
    CONSTRAINT `2` FOREIGN KEY (`sid`) REFERENCES `school_category` (`sid`),
    CONSTRAINT `supplier` FOREIGN KEY (`cid`) REFERENCES `product_category` (`pid`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 48
  DEFAULT CHARSET = utf8;

/*Data for the table `supplier` */

insert into `supplier`(`id`, `sid`, `cid`, `contract_name`, `tel`, `name`, `pwd`, `account`, `company_name`,
                       `company_place`, `access`, `corporation`, `corporation_tel`, `brief_introduction`, `tag`,
                       `last_time`)
values (33, 1, 2, '李佳辕', '15388494045', 'nihao', '123456', '2541654654', 'tencent', '深圳', 1, NULL, NULL, '风大哥',
        NULL, NULL),
       (41, NULL, 2, '唐星月', '19108126273', 'sup1001', '123456', '', '攀枝花市捷通科技有限责任公司',
        '攀枝花东区榕树街14号', 1, NULL, NULL, NULL, NULL, NULL),
       (42, NULL, 2, '刘珈伶', '15283995712', 'sup1002', '123456', '', '攀枝花市复瑞鑫科技有限公司', '攀枝花市东区', 1,
        NULL, NULL, NULL, NULL, NULL),
       (43, NULL, 2, '牛晓天', '19108126273', 'sup1003', '123456', '', '成都赛尔文斯科技有限公司', '四川省成都市成华区',
        1, NULL, NULL, NULL, NULL, NULL),
       (44, NULL, 2, '邹建华', '18090103415', 'sup1004', '123456', '', '四川华越数码科技有限公司', '攀枝花市东区', 1,
        NULL, NULL, NULL, NULL, NULL),
       (45, NULL, 2, '许可儿', '18383515722', 'sup1005', '123456', '', '四川烈阳科技发展有限公司', '攀枝花市东区', 1,
        NULL, NULL, NULL, NULL, NULL),
       (46, NULL, 2, '席学敏', '15283995712', 'sup1006', '123456', '', '四川杰马科技有限公司', '攀枝花市东区', 1, NULL,
        NULL, '提供计算机类实验耗材', NULL, NULL),
       (47, NULL, 2, '何江川', '13056586758', 'sup1007', '123456', '', '攀枝花市东顺环保科技有限公司',
        '攀枝花市西区苏铁中路913号', 1, NULL, NULL, NULL, NULL, NULL);

/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;
