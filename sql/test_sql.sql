SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `documents`
-- ----------------------------
DROP TABLE IF EXISTS `documents`;
CREATE TABLE `documents` (
  `id` int(11) NOT NULL auto_increment,
  `date_added` datetime NOT NULL,
  `title` varchar(255) NOT NULL,
  `content` text NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

使用的测试如下

INSERT INTO `documents` VALUES ('3', '2012-01-12 23:15:59', '你好', 'hello');
INSERT INTO `documents` VALUES ('4', '2012-01-13 23:16:30', 'hello 老何', 'hello');
INSERT INTO `documents` VALUES ('5', '2012-01-14 23:16:30', 'hello 阿堂', 'hello');
INSERT INTO `documents` VALUES ('6', '2012-01-15 23:16:30', 'hello 网络时空', 'hello');
INSERT INTO `documents` VALUES ('7', '2012-01-16 23:16:30', 'hello world', 'ok');
INSERT INTO `documents` VALUES ('8', '2012-01-17 23:16:30', '你们好吗', 'yes');
INSERT INTO `documents` VALUES ('9', '2012-01-18 23:16:30', '你们好', 'good');
INSERT INTO `documents` VALUES ('10', '2012-01-19 23:16:30', '我们好', 'haha');
INSERT INTO `documents` VALUES ('11', '2012-01-20 23:16:30', '中国人好', 'happy new year');
INSERT INTO `documents` VALUES ('12', '2012-01-21 23:16:30', '全中国人好', 'come on');
INSERT INTO `documents` VALUES ('13', '2012-01-22 23:16:30', '你们', 'come on baby');
INSERT INTO `documents` VALUES ('14', sysdate(), '你们', 'come on baby yearh');