/*
MySQL Backup
Database: jiebei
Backup Time: 2019-11-20 15:51:05
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
  KEY `userid` (`uid`),
  CONSTRAINT `did` FOREIGN KEY (`did`) REFERENCES `disc_tab` (`did`),
  CONSTRAINT `userid` FOREIGN KEY (`uid`) REFERENCES `user_tab` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
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
  KEY `disc_tab` (`uid`),
  CONSTRAINT `disc_tab` FOREIGN KEY (`uid`) REFERENCES `user_tab` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
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
  KEY `good_user` (`uid`),
  CONSTRAINT `good_user` FOREIGN KEY (`uid`) REFERENCES `user_tab` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
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
  KEY `request_good` (`gid`),
  CONSTRAINT `request_good` FOREIGN KEY (`gid`) REFERENCES `good_tab` (`gid`),
  CONSTRAINT `request_user` FOREIGN KEY (`uid`) REFERENCES `user_tab` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
CREATE TABLE `token_tab` (
  `tid` int(11) NOT NULL AUTO_INCREMENT,
  `token` varchar(255) NOT NULL,
  `buildtime` int(11) NOT NULL,
  `uid` int(11) NOT NULL,
  PRIMARY KEY (`tid`),
  KEY `uid` (`uid`),
  CONSTRAINT `uid` FOREIGN KEY (`uid`) REFERENCES `user_tab` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
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
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;
BEGIN;
LOCK TABLES `jiebei`.`comment_tab` WRITE;
DELETE FROM `jiebei`.`comment_tab`;
INSERT INTO `jiebei`.`comment_tab` (`cid`,`ctime`,`content`,`uid`,`did`,`likes`,`username`) VALUES (1, 1572762050, '我评论我自己哈哈哈哈或', 15, 4, 0, 'czh'),(5, 1572764338, '我you来评论我自己哈哈哈哈或', 15, 4, 0, 'czh'),(6, 1572766901, '我hai来评论我自己哈哈哈哈或', 15, 6, 0, 'czhhh'),(7, 1572783924, '我haillll来评论我自己哈哈哈哈或', 15, 6, 0, 'czhhh');
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `jiebei`.`disc_tab` WRITE;
DELETE FROM `jiebei`.`disc_tab`;
INSERT INTO `jiebei`.`disc_tab` (`did`,`title`,`uid`,`ctime`,`likes`,`username`,`imgurl`,`useravatar`,`counts`) VALUES (4, '第一条帖子', 15, -1452267202, 144, 'czh', NULL, NULL, 2),(6, '第二条帖子', 15, 1572766821, 1, 'czhhh', NULL, NULL, 2),(7, '第三条帖子', 15, 1573991156, 1, 'czhhh', NULL, NULL, 0),(8, '帖子测试', 19, 1574177302, 1, 'zqy', NULL, NULL, 0),(9, '帖子测试12', 19, 1574177382, 15, 'zqy', NULL, NULL, 0);
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `jiebei`.`good_tab` WRITE;
DELETE FROM `jiebei`.`good_tab`;
INSERT INTO `jiebei`.`good_tab` (`gid`,`uid`,`location`,`imgurl`,`status`,`detail`,`type`,`name`,`price`) VALUES (1, 15, NULL, NULL, 2, NULL, 'room', '32活动室', 0.00),(2, 15, NULL, NULL, 2, '', 'good', '螺丝刀', 0.00),(3, 15, NULL, NULL, 2, NULL, 'good', '书', 0.00),(4, 19, NULL, NULL, 2, NULL, 'good', '耳机', 0.00),(5, 19, NULL, NULL, 2, NULL, 'good', '手机', 0.00),(6, 15, NULL, NULL, 2, '无敌czh的鼠标', 'good', '鼠标', 0.00),(7, 15, NULL, NULL, 2, '', 'good', 'u盘', 0.00),(8, 19, NULL, NULL, 1, NULL, 'good', '键盘', 0.00),(9, 15, '606', NULL, 2, 'detail.....', 'good', '口乐', 6.66);
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `jiebei`.`request_tab` WRITE;
DELETE FROM `jiebei`.`request_tab`;
INSERT INTO `jiebei`.`request_tab` (`rid`,`uid`,`gid`,`req_time`,`start_time`,`end_time`,`reason`,`status`,`resp`,`pid`) VALUES (5, 15, 4, 1572710293, 1572708872, 1572708873, '测试', 0, NULL, 19),(6, 15, 5, 1572714615, 1572708872, 1572708873, '测试2', 0, NULL, 19),(7, 19, 1, 123456, 123123, 321312, '测试3', 0, NULL, 15),(8, 15, 6, 1572749084, 1572708872, 1572708873, '测试4', 0, NULL, 15),(10, 15, 7, 1572756098, 1572708872, 1572708873, '测试4', 2, NULL, 19),(11, 15, 8, 1572766477, 1572708870, 1572708873, '最后测试', 1, NULL, 19),(12, 15, 8, 1572783016, 1572708870, 1572708873, '最后测试', 0, NULL, 19),(13, 19, 1, 1573125340, 1573125338, 1573211738, '测一下', 0, NULL, 15),(14, 19, 1, 1573125411, 1573125338, 1573211738, '测一下', 0, NULL, 15),(15, 19, 1, 1573125414, 1573125338, 1573211738, '测一下', 0, NULL, 15),(16, 19, 1, 1573125465, 1573471059, 1573643859, '测一下', 0, NULL, 15),(17, 19, 2, 1573186897, 1573186895, 1573273295, '测一下', 0, NULL, 15),(18, 19, 1, 1573196540, 1573196528, 1573282928, '爷就是要借', 0, NULL, 15),(19, 19, 1, 1574230010, 1574230002, 1574316402, '发发发', 0, NULL, 15),(20, 19, 3, 1574230702, 1574230695, 1574317095, '公积金', 0, NULL, 15);
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `jiebei`.`token_tab` WRITE;
DELETE FROM `jiebei`.`token_tab`;
INSERT INTO `jiebei`.`token_tab` (`tid`,`token`,`buildtime`,`uid`) VALUES (7, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1NzM5ODAzNjEsImV4cCI6MTU3NDIzOTU2MSwidWlkIjoiMTUiLCJpc3MiOiJsd3MifQ.GglxhTahh1nJSz2C594MFXWhUOGLsTE0foFAaylHvPU', 1573980361, 15),(8, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1NzQyMzQ4MzUsImV4cCI6MTU3NDQ5NDAzNSwidWlkIjoiMTkiLCJpc3MiOiJsd3MifQ.01_pvzRD0bOSzouRj_PsG2jun0d4lbVBvjR6aVJsaqc', 1574234835, 19);
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `jiebei`.`user_tab` WRITE;
DELETE FROM `jiebei`.`user_tab`;
INSERT INTO `jiebei`.`user_tab` (`uid`,`imgurl`,`name`,`schoolid`,`phonenum`,`username`,`address`,`token`,`password`,`qq`,`wechat`,`department`,`speciality`,`cls`) VALUES (15, NULL, NULL, '031702530', NULL, 'czhhh', '6066', NULL, '123', NULL, NULL, '数计', '计算机', '5'),(17, NULL, NULL, '123', NULL, 'cczh', NULL, NULL, '12345', NULL, NULL, NULL, NULL, NULL),(19, NULL, NULL, '031702420', NULL, 'zqy', '111', NULL, '12345', NULL, NULL, NULL, NULL, NULL),(43, NULL, NULL, '031702531', NULL, 'wjh', '111', NULL, '12345', NULL, NULL, NULL, NULL, NULL),(49, NULL, NULL, '251706002', NULL, 'spy', '1234', NULL, '123123', NULL, NULL, NULL, NULL, NULL);
UNLOCK TABLES;
COMMIT;
