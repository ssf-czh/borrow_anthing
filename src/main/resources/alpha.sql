/*
MySQL Backup
Database: jiebei
Backup Time: 2019-11-22 15:11:30
*/

SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `jiebei`.`comment_tab`;
DROP TABLE IF EXISTS `jiebei`.`disc_tab`;
DROP TABLE IF EXISTS `jiebei`.`good_tab`;
DROP TABLE IF EXISTS `jiebei`.`request_tab`;
DROP TABLE IF EXISTS `jiebei`.`token_tab`;
DROP TABLE IF EXISTS `jiebei`.`user_tab`;
CREATE TABLE `comment_tab` (
  `cid` int(15) NOT NULL AUTO_INCREMENT,
  `ctime` int(15) NOT NULL COMMENT '评论时间',
  `content` text COMMENT '评论内容',
  `uid` int(15) NOT NULL COMMENT '评论用户uid',
  `did` int(11) NOT NULL COMMENT '所属帖子',
  `likes` int(15) DEFAULT NULL COMMENT '喜欢人数',
  `username` varchar(255) NOT NULL COMMENT '用户名称',
  PRIMARY KEY (`cid`),
  KEY `did` (`did`),
  KEY `userid` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
CREATE TABLE `disc_tab` (
  `did` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL COMMENT '帖子标题',
  `uid` int(15) NOT NULL COMMENT '发帖人uid',
  `ctime` int(15) NOT NULL COMMENT '创建时间',
  `likes` int(11) DEFAULT NULL COMMENT '喜欢数',
  `username` varchar(255) NOT NULL COMMENT '发帖人用户名',
  `imgurl` varchar(255) DEFAULT NULL COMMENT '帖子图片url',
  `useravatar` varchar(255) DEFAULT NULL COMMENT '发帖人头像url',
  `counts` int(15) DEFAULT NULL COMMENT '评论数',
  PRIMARY KEY (`did`),
  KEY `disc_tab` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
CREATE TABLE `good_tab` (
  `gid` int(11) NOT NULL AUTO_INCREMENT COMMENT '物品id',
  `uid` int(11) NOT NULL COMMENT '物品所属用户id',
  `location` varchar(50) DEFAULT NULL COMMENT '物品当前位置',
  `imgurl` varchar(255) DEFAULT NULL COMMENT '物品照片url',
  `status` int(11) NOT NULL COMMENT '物品状态 0表待审核 1表示借出 2表示空闲',
  `detail` varchar(255) DEFAULT NULL COMMENT '物品细节描述',
  `type` varchar(255) NOT NULL COMMENT 'good表示物品，room表示活动室',
  `name` varchar(255) NOT NULL COMMENT '物品名称',
  `price` double(10,2) NOT NULL COMMENT '价格',
  PRIMARY KEY (`gid`),
  KEY `good_user` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
CREATE TABLE `request_tab` (
  `rid` int(15) NOT NULL AUTO_INCREMENT COMMENT '请求记录id',
  `uid` int(15) NOT NULL COMMENT '申请者用户id',
  `gid` int(15) NOT NULL COMMENT '申请物品id',
  `req_time` int(15) NOT NULL COMMENT '申请时间',
  `start_time` int(15) NOT NULL COMMENT '申请开始时间',
  `end_time` int(15) NOT NULL COMMENT '申请结束时间',
  `reason` varchar(255) DEFAULT NULL COMMENT '申请原因',
  `status` int(15) NOT NULL COMMENT '申请记录状态 0是待审核 1是使用中 2是结束 3是拒绝 4是待确定归还',
  `resp` varchar(255) DEFAULT NULL COMMENT '回复',
  `pid` int(11) NOT NULL COMMENT '商品所拥有者对应的uid',
  PRIMARY KEY (`rid`) USING BTREE,
  KEY `request_user` (`uid`),
  KEY `request_good` (`gid`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
CREATE TABLE `token_tab` (
  `tid` int(11) NOT NULL AUTO_INCREMENT,
  `token` varchar(255) NOT NULL,
  `buildtime` int(11) NOT NULL,
  `uid` int(11) NOT NULL,
  PRIMARY KEY (`tid`),
  KEY `uid` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
CREATE TABLE `user_tab` (
  `uid` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id\r\n',
  `imgurl` varchar(255) DEFAULT NULL COMMENT '用户头像url',
  `name` varchar(30) DEFAULT NULL COMMENT '用户真实姓名',
  `schoolid` varchar(30) NOT NULL COMMENT '用户学号',
  `phonenum` varchar(30) DEFAULT NULL COMMENT '用户联系方式',
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  `address` varchar(255) DEFAULT NULL COMMENT '用户地址',
  `token` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `qq` varchar(255) DEFAULT NULL,
  `wechat` varchar(255) DEFAULT NULL,
  `department` varchar(255) DEFAULT NULL COMMENT '院系',
  `speciality` varchar(255) DEFAULT NULL COMMENT '专业',
  `cls` varchar(255) DEFAULT NULL COMMENT '班级',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `schoolid` (`schoolid`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8;
BEGIN;
LOCK TABLES `jiebei`.`comment_tab` WRITE;
DELETE FROM `jiebei`.`comment_tab`;
INSERT INTO `jiebei`.`comment_tab` (`cid`,`ctime`,`content`,`uid`,`did`,`likes`,`username`) VALUES (1, 1572762050, '我评论我自己哈哈哈哈或', 15, 4, 0, 'czh'),(5, 1572764338, '我you来评论我自己哈哈哈哈或', 15, 4, 0, 'czh'),(6, 1572766901, '我hai来评论我自己哈哈哈哈或', 15, 6, 0, 'czhhh');
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `jiebei`.`disc_tab` WRITE;
DELETE FROM `jiebei`.`disc_tab`;
INSERT INTO `jiebei`.`disc_tab` (`did`,`title`,`uid`,`ctime`,`likes`,`username`,`imgurl`,`useravatar`,`counts`) VALUES (4, '第一条帖子', 15, -1452267202, 184, 'czh', NULL, NULL, 2),(6, '第二条帖子', 15, 1572766821, 1, 'czhhh', NULL, NULL, 2),(7, '第三条帖子', 15, 1573991156, 6, 'czhhh', NULL, NULL, 0),(8, '帖子测试', 19, 1574177302, 1, 'zqy', NULL, NULL, 0),(9, '帖子测试12', 19, 1574177382, 17, 'zqy', NULL, NULL, 0),(11, 'asdasdasd', 19, 1574260123, 0, 'zqy', NULL, NULL, 0),(12, '', 19, 1574264238, 0, 'zqy', '/www/server/tomcat/webapps/jiebei/WEB-INF/classes/1915742642383293.png', NULL, 0),(13, '阿松大', 19, 1574348879, 2, 'zqy', '/www/server/tomcat/webapps/jiebei/WEB-INF/classes/1915743488796293.png', NULL, 0);
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `jiebei`.`good_tab` WRITE;
DELETE FROM `jiebei`.`good_tab`;
INSERT INTO `jiebei`.`good_tab` (`gid`,`uid`,`location`,`imgurl`,`status`,`detail`,`type`,`name`,`price`) VALUES (1, 15, NULL, NULL, 1, NULL, 'room', '32活动室', 0.00),(2, 15, NULL, NULL, 2, '', 'good', '螺丝刀', 0.00),(4, 19, NULL, NULL, 2, NULL, 'good', '耳机', 0.00),(5, 19, NULL, NULL, 2, NULL, 'good', '手机', 0.00),(6, 15, NULL, NULL, 2, '无敌czh的鼠标', 'good', '鼠标', 0.00),(7, 15, NULL, NULL, 2, '', 'good', 'u盘', 0.00),(8, 19, NULL, NULL, 1, NULL, 'good', '键盘', 0.00),(9, 15, '606', NULL, 2, 'detail.....', 'good', '口乐', 6.66),(10, 19, '32', '/www/server/tomcat/webapps/jiebei/WEB-INF/classes/1915742652981103.png', 2, '', 'room', '32 4', 0.00),(11, 19, '', '/www/server/tomcat/webapps/jiebei/WEB-INF/classes/1915742657706193.png', 2, '', 'good', '', 0.00),(12, 19, '阿松大', '/www/server/tomcat/webapps/jiebei/WEB-INF/classes/1915743488462912.png', 2, '阿松大', 'good', '阿松大', 0.00),(13, 19, '32', '/www/server/tomcat/webapps/jiebei/WEB-INF/classes/1915743504762652.png', 2, '额呃呃呃', 'room', '32#4活动室', 0.00),(14, 15, 'rrt', '/www/server/tomcat/webapps/jiebei/WEB-INF/classes/1515743901743522.png', 2, '', 'good', '123', 0.00),(15, 15, 'rrt', '/www/server/tomcat/webapps/jiebei/WEB-INF/classes/1515743901761302.png', 2, '', 'good', '123', 0.00),(16, 19, '123', '/www/server/tomcat/webapps/jiebei/WEB-INF/classes/1915744000950582.png', 2, '123', 'good', '物品', 0.00),(17, 19, '123', '/www/server/tomcat/webapps/jiebei/WEB-INF/classes/1915744001324732.png', 2, '123', 'room', '活动室', 0.00),(18, 19, '123', '/www/server/tomcat/webapps/jiebei/WEB-INF/classes/1915744002138722.png', 2, '123', 'room', '活动室', 0.00),(19, 19, 'a啊实打实', '/www/server/tomcat/webapps/jiebei/WEB-INF/classes/1915744005254842.png', 2, '阿松大', 'good', '大师傅士大夫', 0.00);
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `jiebei`.`request_tab` WRITE;
DELETE FROM `jiebei`.`request_tab`;
INSERT INTO `jiebei`.`request_tab` (`rid`,`uid`,`gid`,`req_time`,`start_time`,`end_time`,`reason`,`status`,`resp`,`pid`) VALUES (5, 15, 4, 1572710293, 1572708872, 1572708873, '测试', 3, NULL, 19),(6, 15, 5, 1572714615, 1572708872, 1572708873, '测试2', 3, NULL, 19),(7, 19, 1, 123456, 123123, 321312, '测试3', 0, NULL, 15),(8, 15, 6, 1572749084, 1572708872, 1572708873, '测试4', 0, NULL, 15),(10, 15, 7, 1572756098, 1572708872, 1572708873, '测试4', 3, NULL, 19),(11, 15, 8, 1572766477, 1572708870, 1572708873, '最后测试', 3, NULL, 19),(12, 15, 8, 1572783016, 1572708870, 1572708873, '最后测试', 3, NULL, 19),(13, 19, 1, 1573125340, 1573125338, 1573211738, '测一下', 0, NULL, 15),(14, 19, 1, 1573125411, 1573125338, 1573211738, '测一下', 0, NULL, 15),(15, 19, 1, 1573125414, 1573125338, 1573211738, '测一下', 0, NULL, 15),(16, 19, 1, 1573125465, 1573471059, 1573643859, '测一下', 0, NULL, 15),(17, 19, 2, 1573186897, 1573186895, 1573273295, '测一下', 0, NULL, 15),(18, 19, 1, 1573196540, 1573196528, 1573282928, '爷就是要借', 0, NULL, 15),(19, 19, 1, 1574230010, 1574230002, 1574316402, '发发发', 0, NULL, 15),(21, 19, 1, 1574237978, 1574237969, 1574324369, '11\n\n', 0, NULL, 15),(22, 19, 2, 1574348834, 1574348831, 1574435231, '萨达', 0, NULL, 15),(23, 19, 9, 1574350801, 1574350797, 1574437197, '很适合 ', 0, NULL, 15),(24, 19, 14, 1574390698, 1574390693, 1574477093, '123', 0, NULL, 15);
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `jiebei`.`token_tab` WRITE;
DELETE FROM `jiebei`.`token_tab`;
INSERT INTO `jiebei`.`token_tab` (`tid`,`token`,`buildtime`,`uid`) VALUES (7, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1NzQzOTAzMzksImV4cCI6MTU3NDY0OTUzOSwidWlkIjoiMTUiLCJpc3MiOiJsd3MifQ.xt9AnxRatf_ibgDtAyG_xIrnJg8oJHdszKPpr-EYFs0', 1574390339, 15),(8, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1NzQ0MDY2ODEsImV4cCI6MTU3NDY2NTg4MSwidWlkIjoiMTkiLCJpc3MiOiJsd3MifQ.0Aj6zNjssEj_RWxdgc-Uz-lmbArIJUXvdq-Y9ptUEAo', 1574406681, 19),(9, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1NzQzNTA3NjEsImV4cCI6MTU3NDYwOTk2MSwidWlkIjoiNTAiLCJpc3MiOiJsd3MifQ.zXfnNwzOZpFZc73Udho-vzzJI2uzkue6d989N1NHw4M', 1574350761, 50),(10, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1NzQzNTExOTAsImV4cCI6MTU3NDYxMDM5MCwidWlkIjoiNTEiLCJpc3MiOiJsd3MifQ.qsAtKMbKenDqA0_tqgTYcIFr40aMrcaenAQniqW8D9k', 1574351190, 51),(11, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1NzQzNTEyMjgsImV4cCI6MTU3NDYxMDQyOCwidWlkIjoiNTIiLCJpc3MiOiJsd3MifQ.gORbBE7qPuB-pZBeX-bsjOH2T8kHqSvPDcjkpXP0poA', 1574351228, 52);
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `jiebei`.`user_tab` WRITE;
DELETE FROM `jiebei`.`user_tab`;
INSERT INTO `jiebei`.`user_tab` (`uid`,`imgurl`,`name`,`schoolid`,`phonenum`,`username`,`address`,`token`,`password`,`qq`,`wechat`,`department`,`speciality`,`cls`) VALUES (15, NULL, '陈展鸿', '031702530', '', 'czhhh', '6066', NULL, '123', '123456789', '', '数计', '计算机', '5'),(17, NULL, NULL, '123', NULL, 'cczh', NULL, NULL, '12345', NULL, NULL, NULL, NULL, NULL),(19, '/www/server/tomcat/webapps/jiebei/WEB-INF/classes/1915743509633652.png', '张庆焰', '031702420', '', 'zqy', '111', NULL, '12345', '753340374', '', '数计学院', '计算机', '4班'),(43, NULL, NULL, '031702531', NULL, 'wjh', '111', NULL, '12345', NULL, NULL, NULL, NULL, NULL),(49, NULL, NULL, '251706002', NULL, 'spy', '1234', NULL, '123123', NULL, NULL, NULL, NULL, NULL),(50, NULL, '蔡雅菁', '031702501', '', '031702501', '', NULL, '123456', '836840420', '', '数计', '计算机', '5'),(51, NULL, '陈观鸿', '031702538', '13003870515', '847001315', '', NULL, '123456', '847001315', '122', '数计学院', '计算机', '5班'),(52, NULL, '吴洁敏', '031702502', '13646933739', 'CTJM', '', NULL, 'wjm2cx1314', '1', '2', '计算机', '计算机', '5');
UNLOCK TABLES;
COMMIT;
