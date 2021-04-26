/*
 Navicat Premium Data Transfer

 Source Server         : aliyun-liaojiexin
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : www.liaojiexin.top:3306
 Source Schema         : videoweb

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 26/04/2021 10:22:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for comments
-- ----------------------------
DROP TABLE IF EXISTS `comments`;
CREATE TABLE `comments`  (
  `uid` int(11) NOT NULL,
  `vid` int(11) NOT NULL,
  `comment` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `commenttime` datetime(0) NULL,
  PRIMARY KEY (`uid`, `vid`, `commenttime`) USING BTREE,
  INDEX `fk_vid_idx`(`vid`) USING BTREE,
  CONSTRAINT `fk_uid_comments` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_vid_comments` FOREIGN KEY (`vid`) REFERENCES `video` (`vid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comments
-- ----------------------------
INSERT INTO `comments` VALUES (1, 1, '156', '2004-12-12 00:00:00');
INSERT INTO `comments` VALUES (1, 1, '156', '2005-12-12 00:00:01');
INSERT INTO `comments` VALUES (1, 1, '156', '2006-12-12 00:00:00');
INSERT INTO `comments` VALUES (1, 1, '156', '2007-12-12 00:00:01');
INSERT INTO `comments` VALUES (1, 1, '11', '2014-12-12 00:00:00');
INSERT INTO `comments` VALUES (1, 1, '6666', '2015-10-13 12:12:12');
INSERT INTO `comments` VALUES (1, 1, '6666', '2016-10-13 12:12:13');
INSERT INTO `comments` VALUES (1, 1, '11', '2018-12-12 00:00:00');
INSERT INTO `comments` VALUES (1, 1, '6666', '2019-10-13 12:12:12');
INSERT INTO `comments` VALUES (1, 1, '6666', '2019-10-13 12:12:13');
INSERT INTO `comments` VALUES (1, 1, '156', '2019-12-12 00:00:00');
INSERT INTO `comments` VALUES (1, 1, '156', '2019-12-12 00:00:01');
INSERT INTO `comments` VALUES (1, 1, '666', '2020-02-11 18:17:55');
INSERT INTO `comments` VALUES (1, 1, '好好好', '2020-02-14 11:47:42');
INSERT INTO `comments` VALUES (1, 1, 'hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh', '2020-03-05 10:30:41');
INSERT INTO `comments` VALUES (1, 2, '156', '2019-12-12 00:00:00');
INSERT INTO `comments` VALUES (1, 2, '156', '2019-12-12 00:00:01');
INSERT INTO `comments` VALUES (1, 3, '11', '2001-12-13 00:00:00');
INSERT INTO `comments` VALUES (1, 3, '6666', '2002-10-13 12:12:12');
INSERT INTO `comments` VALUES (1, 3, '6666', '2003-10-13 12:12:13');
INSERT INTO `comments` VALUES (1, 3, '156', '2004-12-12 00:00:00');
INSERT INTO `comments` VALUES (1, 3, '156', '2005-12-12 00:00:01');
INSERT INTO `comments` VALUES (1, 3, '156', '2006-12-12 00:00:00');
INSERT INTO `comments` VALUES (1, 3, '156', '2007-12-12 00:00:01');
INSERT INTO `comments` VALUES (1, 3, '11', '2014-12-12 00:00:00');
INSERT INTO `comments` VALUES (1, 3, '6666', '2015-10-13 12:12:12');
INSERT INTO `comments` VALUES (1, 3, '6666', '2016-10-13 12:12:13');
INSERT INTO `comments` VALUES (1, 3, '156', '2017-12-12 00:00:00');
INSERT INTO `comments` VALUES (1, 3, '11', '2018-09-12 00:00:00');
INSERT INTO `comments` VALUES (1, 3, '11', '2018-12-12 00:00:00');
INSERT INTO `comments` VALUES (1, 3, '156', '2018-12-12 00:00:01');
INSERT INTO `comments` VALUES (1, 3, '11', '2018-12-12 00:05:00');
INSERT INTO `comments` VALUES (1, 3, '156', '2019-10-12 00:00:00');
INSERT INTO `comments` VALUES (1, 3, '6666', '2019-10-13 12:02:13');
INSERT INTO `comments` VALUES (1, 3, '6666', '2019-10-13 12:12:12');
INSERT INTO `comments` VALUES (1, 3, '6666', '2019-10-13 12:12:13');
INSERT INTO `comments` VALUES (1, 3, '6666', '2019-10-13 12:22:12');
INSERT INTO `comments` VALUES (1, 3, '6666', '2019-10-13 12:32:13');
INSERT INTO `comments` VALUES (1, 3, '6666', '2019-10-13 12:42:12');
INSERT INTO `comments` VALUES (1, 3, '156', '2019-12-02 00:00:01');
INSERT INTO `comments` VALUES (1, 3, '156', '2019-12-05 00:00:01');
INSERT INTO `comments` VALUES (1, 3, '156', '2019-12-06 00:00:00');
INSERT INTO `comments` VALUES (1, 3, '156', '2019-12-07 00:00:01');
INSERT INTO `comments` VALUES (1, 3, '156', '2019-12-08 00:00:00');
INSERT INTO `comments` VALUES (1, 3, '156', '2019-12-12 00:00:00');
INSERT INTO `comments` VALUES (1, 3, '156', '2019-12-12 00:00:01');
INSERT INTO `comments` VALUES (1, 3, '156', '2019-12-12 00:00:07');
INSERT INTO `comments` VALUES (1, 3, '156', '2019-12-12 00:00:21');
INSERT INTO `comments` VALUES (1, 3, '156', '2019-12-12 00:00:27');
INSERT INTO `comments` VALUES (1, 3, '156', '2019-12-12 00:00:29');
INSERT INTO `comments` VALUES (1, 3, '156', '2019-12-12 00:00:38');
INSERT INTO `comments` VALUES (1, 3, '156', '2019-12-12 00:40:00');
INSERT INTO `comments` VALUES (1, 3, 'aaa', '2020-02-11 16:37:47');
INSERT INTO `comments` VALUES (1, 3, 'aaa', '2020-02-11 16:37:51');
INSERT INTO `comments` VALUES (1, 3, 'aaa', '2020-02-11 16:38:34');
INSERT INTO `comments` VALUES (1, 3, 'aa', '2020-02-11 16:38:36');
INSERT INTO `comments` VALUES (1, 3, 'www', '2020-02-11 16:38:41');
INSERT INTO `comments` VALUES (1, 3, '123', '2020-02-13 15:27:30');
INSERT INTO `comments` VALUES (1, 9, 'aa', '2020-02-14 09:18:50');
INSERT INTO `comments` VALUES (22, 1, '66', '2019-12-12 00:00:00');
INSERT INTO `comments` VALUES (22, 1, '66', '2019-12-12 00:00:03');
INSERT INTO `comments` VALUES (22, 2, '66', '2019-12-12 00:00:00');
INSERT INTO `comments` VALUES (22, 2, '66', '2019-12-12 00:00:03');
INSERT INTO `comments` VALUES (22, 3, '66', '2008-12-12 00:04:00');
INSERT INTO `comments` VALUES (22, 3, '66', '2009-12-12 00:00:07');
INSERT INTO `comments` VALUES (22, 3, '66', '2009-12-12 01:00:00');
INSERT INTO `comments` VALUES (22, 3, '66', '2010-12-12 00:00:00');
INSERT INTO `comments` VALUES (22, 3, '66', '2011-12-12 00:00:03');
INSERT INTO `comments` VALUES (22, 3, '66', '2018-12-12 10:00:03');
INSERT INTO `comments` VALUES (22, 3, '66', '2019-12-04 00:00:03');
INSERT INTO `comments` VALUES (22, 3, '66', '2019-12-12 00:00:00');
INSERT INTO `comments` VALUES (22, 3, '66', '2019-12-12 00:00:03');
INSERT INTO `comments` VALUES (22, 3, '66', '2019-12-12 00:00:09');
INSERT INTO `comments` VALUES (22, 3, '66', '2019-12-12 00:00:18');
INSERT INTO `comments` VALUES (22, 3, '66', '2019-12-12 00:00:28');
INSERT INTO `comments` VALUES (22, 3, '66', '2019-12-12 00:00:30');
INSERT INTO `comments` VALUES (22, 3, '66', '2019-12-12 00:00:33');
INSERT INTO `comments` VALUES (22, 3, '66', '2019-12-12 00:07:03');
INSERT INTO `comments` VALUES (22, 3, '66', '2019-12-12 00:10:00');
INSERT INTO `comments` VALUES (22, 3, '66', '2019-12-12 00:18:03');
INSERT INTO `comments` VALUES (22, 3, '66', '2019-12-12 00:23:00');
INSERT INTO `comments` VALUES (22, 3, '66', '2019-12-12 00:42:03');
INSERT INTO `comments` VALUES (22, 3, '66', '2019-12-12 00:43:00');
INSERT INTO `comments` VALUES (22, 3, '66', '2019-12-12 00:44:03');
INSERT INTO `comments` VALUES (22, 3, '66', '2019-12-12 00:45:00');
INSERT INTO `comments` VALUES (22, 3, '66', '2019-12-12 02:00:03');
INSERT INTO `comments` VALUES (22, 3, '66', '2019-12-12 05:00:00');
INSERT INTO `comments` VALUES (22, 3, '11', '2020-02-11 16:34:05');
INSERT INTO `comments` VALUES (22, 3, '11', '2020-02-11 16:34:12');
INSERT INTO `comments` VALUES (22, 3, '11', '2020-02-11 16:34:15');
INSERT INTO `comments` VALUES (22, 3, '57778', '2020-02-11 16:34:58');
INSERT INTO `comments` VALUES (22, 3, '嘻嘻嘻嘻嘻嘻嘻嘻嘻嘻嘻嘻嘻哈哈哈哈哈哈哈哈哈哈', '2020-02-11 16:35:05');
INSERT INTO `comments` VALUES (22, 5, '88888', '2020-02-11 16:32:48');
INSERT INTO `comments` VALUES (22, 5, '11', '2020-02-11 16:33:39');
INSERT INTO `comments` VALUES (23, 1, '111', '2019-12-12 00:00:00');
INSERT INTO `comments` VALUES (23, 2, '111', '2019-12-12 00:00:00');
INSERT INTO `comments` VALUES (23, 3, '111', '2012-12-12 00:00:00');
INSERT INTO `comments` VALUES (23, 3, '111', '2013-12-12 00:10:00');
INSERT INTO `comments` VALUES (23, 3, '111', '2019-07-12 00:00:00');
INSERT INTO `comments` VALUES (23, 3, '111', '2019-12-12 00:00:00');
INSERT INTO `comments` VALUES (23, 3, '111', '2019-12-12 00:00:40');
INSERT INTO `comments` VALUES (23, 3, '111', '2019-12-12 00:06:00');
INSERT INTO `comments` VALUES (23, 3, '111', '2019-12-12 00:09:00');
INSERT INTO `comments` VALUES (23, 3, '111', '2019-12-12 00:11:00');
INSERT INTO `comments` VALUES (23, 3, '111', '2019-12-12 00:41:00');
INSERT INTO `comments` VALUES (23, 3, '111', '2019-12-12 00:50:00');
INSERT INTO `comments` VALUES (23, 3, '111', '2019-12-12 09:00:00');
INSERT INTO `comments` VALUES (27, 5, '123456', '2020-02-11 15:55:05');
INSERT INTO `comments` VALUES (27, 5, '123456', '2020-02-11 16:03:59');
INSERT INTO `comments` VALUES (27, 5, '666', '2020-02-11 16:07:08');

-- ----------------------------
-- Table structure for contact
-- ----------------------------
DROP TABLE IF EXISTS `contact`;
CREATE TABLE `contact`  (
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `message` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of contact
-- ----------------------------
INSERT INTO `contact` VALUES ('jiexin liao', '12345678@qq.com', 'sssss');
INSERT INTO `contact` VALUES ('jiexin liao', '570839281@qq.com', 'oooo');
INSERT INTO `contact` VALUES ('jiexin liao', 'aa@qq.com', 'aa');
INSERT INTO `contact` VALUES ('jiexin liao', '51421581@qq.com', 'aaaaaaa');
INSERT INTO `contact` VALUES ('郑帅帅无敌帅', '1025905091@qq.com', '廖师傅，你过孤毒，么么哒');

-- ----------------------------
-- Table structure for likes
-- ----------------------------
DROP TABLE IF EXISTS `likes`;
CREATE TABLE `likes`  (
  `vid` int(11) NOT NULL,
  `uid` int(11) NOT NULL,
  PRIMARY KEY (`vid`, `uid`) USING BTREE,
  INDEX `fk_uid_likes_idx`(`uid`) USING BTREE,
  CONSTRAINT `fk_uid_likes` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_vid_likes` FOREIGN KEY (`vid`) REFERENCES `video` (`vid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of likes
-- ----------------------------
INSERT INTO `likes` VALUES (1, 1);
INSERT INTO `likes` VALUES (2, 1);
INSERT INTO `likes` VALUES (4, 1);
INSERT INTO `likes` VALUES (6, 1);
INSERT INTO `likes` VALUES (7, 1);
INSERT INTO `likes` VALUES (45, 1);
INSERT INTO `likes` VALUES (46, 1);
INSERT INTO `likes` VALUES (50, 1);
INSERT INTO `likes` VALUES (51, 1);
INSERT INTO `likes` VALUES (1, 25);
INSERT INTO `likes` VALUES (1, 26);

-- ----------------------------
-- Table structure for manage
-- ----------------------------
DROP TABLE IF EXISTS `manage`;
CREATE TABLE `manage`  (
  `mid` int(11) NOT NULL AUTO_INCREMENT,
  `mname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `mpassword` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `cellphone` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`mid`) USING BTREE,
  UNIQUE INDEX `mid_UNIQUE`(`mid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of manage
-- ----------------------------
INSERT INTO `manage` VALUES (1, 'root', '123456', '123456@qq.com', '13726200209');
INSERT INTO `manage` VALUES (2, 'admin', '201314', 'admin@qq.com', '18824146878');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `birthday` date NULL DEFAULT NULL,
  PRIMARY KEY (`uid`) USING BTREE,
  UNIQUE INDEX `userid_UNIQUE`(`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '201314', 'admin@qq.com', '2000-03-03');
INSERT INTO `user` VALUES (22, '12345678', '12345678', 'aa66666aa@qq.com', '2020-02-12');
INSERT INTO `user` VALUES (23, '999999999', '666666666', '1111111@qq.com', '2020-01-24');
INSERT INTO `user` VALUES (24, '159159159', '159159159', '159159159@qq.com', '2020-01-24');
INSERT INTO `user` VALUES (25, '789789789', '789789789', 'aaa@qq.com', '2020-01-24');
INSERT INTO `user` VALUES (26, '15945678', '1212121212', '159@qq.com', '2020-01-24');
INSERT INTO `user` VALUES (27, '570839281', '12345678', '12345678@qq.com', '2020-02-11');
INSERT INTO `user` VALUES (28, '1111111111', 'aA201314', 'likevideonowpage@qq.com', '2020-02-13');
INSERT INTO `user` VALUES (29, '1025905091', '1025905091', '1025905091@qq.com', '2020-03-13');

-- ----------------------------
-- Table structure for video
-- ----------------------------
DROP TABLE IF EXISTS `video`;
CREATE TABLE `video`  (
  `vid` int(11) NOT NULL AUTO_INCREMENT,
  `vname` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `vtag` set('动漫','教育','娱乐','影视','广告','搞笑','音乐','生活','运动','科技','游戏') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `date` datetime(0) NULL,
  `uid` int(11) NOT NULL,
  `introduce` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `url` varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `imageurl` varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state` tinyint(4) NOT NULL DEFAULT 0,
  `special` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '普通',
  `countcomments` int(11) NOT NULL DEFAULT 0,
  `countlikes` int(11) NOT NULL DEFAULT 0,
  `countlooks` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`vid`) USING BTREE,
  UNIQUE INDEX `vid_UNIQUE`(`vid`) USING BTREE,
  INDEX `fk_uid_idx`(`uid`) USING BTREE,
  CONSTRAINT `fk_uid_video` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB AUTO_INCREMENT = 131 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of video
-- ----------------------------
INSERT INTO `video` VALUES (1, '一二三四五六七八九十一二三四五六七八九十一二', '动漫', '2019-12-12 00:00:00', 22, '一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十', '/video/videourl/video.mp4', '/video/imagesurl/photo1.PNG', 1, '第四届NEW ERA青年电影季', 18, 3, 514);
INSERT INTO `video` VALUES (2, '2', '动漫,教育,音乐,科技,游戏', '2019-08-12 12:12:12', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/login2.jpg', 1, '第四届NEW ERA青年电影季', 5, 1, 227);
INSERT INTO `video` VALUES (3, '3', '动漫', '2019-08-12 10:10:10', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/photo.PNG', 1, '第四届NEW ERA青年电影季', 81, 0, 174);
INSERT INTO `video` VALUES (4, '4', '动漫', '2019-10-12 10:10:10', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/photo.PNG', 1, '第四届NEW ERA青年电影季', 0, 1, 165);
INSERT INTO `video` VALUES (5, '5', '动漫', '2019-09-12 10:10:10', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/login2.jpg', 1, '第四届NEW ERA青年电影季', 5, 0, 209);
INSERT INTO `video` VALUES (6, '123456789012345678901234567890123456789012345678901234567890', '动漫', '2019-11-12 10:10:10', 1, '/video/videourl/true/video.mp4', '/video/videourl/movie.mp4', '/video/imagesurl/login2.jpg', 1, '第四届NEW ERA青年电影季', 0, 1, 172);
INSERT INTO `video` VALUES (7, '7', '动漫', '2019-08-13 10:10:10', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/photo.PNG', 1, '第四届NEW ERA青年电影季', 0, 1, 155);
INSERT INTO `video` VALUES (8, '8', '动漫', '2019-02-12 10:10:10', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/login2.jpg', 1, '第四届NEW ERA青年电影季', 0, 0, 5);
INSERT INTO `video` VALUES (9, '9', '动漫', '2019-03-12 10:10:10', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/login2.jpg', 1, '第四届NEW ERA青年电影季', 1, 0, 46);
INSERT INTO `video` VALUES (10, '10', '动漫', '2019-04-12 10:10:10', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/photo.PNG', 1, '第四届NEW ERA青年电影季', 0, 0, 6);
INSERT INTO `video` VALUES (11, '11', '动漫', '2019-06-12 10:10:10', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/login2.jpg', 1, '第四届NEW ERA青年电影季', 0, 0, 2);
INSERT INTO `video` VALUES (12, '12', '动漫', '2019-02-12 10:10:11', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/photo.PNG', 1, '第四届NEW ERA青年电影季', 0, 0, 0);
INSERT INTO `video` VALUES (13, '13', '动漫', '2019-02-12 10:10:12', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/login2.jpg', 1, '第四届NEW ERA青年电影季', 0, 0, 0);
INSERT INTO `video` VALUES (14, '14', '动漫', '2019-02-12 10:10:13', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/photo.PNG', 1, '第四届NEW ERA青年电影季', 0, 0, 0);
INSERT INTO `video` VALUES (15, '15', '动漫', '2019-02-12 10:10:14', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/login2.jpg', 1, '第四届NEW ERA青年电影季', 0, 0, 0);
INSERT INTO `video` VALUES (16, '16', '动漫', '2019-02-12 10:10:15', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/photo.PNG', 1, '第四届NEW ERA青年电影季', 0, 0, 0);
INSERT INTO `video` VALUES (17, '17', '动漫', '2019-02-12 10:10:16', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/login2.jpg', 1, '第四届NEW ERA青年电影季', 0, 0, 0);
INSERT INTO `video` VALUES (18, '18', '动漫', '2019-02-12 10:10:17', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/photo.PNG', 1, '第四届NEW ERA青年电影季', 0, 0, 0);
INSERT INTO `video` VALUES (19, '19', '动漫', '2019-02-12 10:10:18', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/login2.jpg', 1, '第四届NEW ERA青年电影季', 0, 0, 0);
INSERT INTO `video` VALUES (20, '20', '动漫', '2019-02-12 10:10:19', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/photo.PNG', 1, '第四届NEW ERA青年电影季', 0, 0, 12);
INSERT INTO `video` VALUES (21, '16', '广告', '2019-02-12 10:10:15', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/photo.PNG', 1, '第四届NEW ERA青年电影季', 0, 0, 159);
INSERT INTO `video` VALUES (22, '17', '搞笑', '2019-02-12 10:10:16', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/login2.jpg', 1, '第四届NEW ERA青年电影季', 0, 0, 153);
INSERT INTO `video` VALUES (23, '18', '运动', '2019-02-12 10:10:17', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/photo.PNG', 1, '第四届NEW ERA青年电影季', 0, 0, 154);
INSERT INTO `video` VALUES (24, '19', '生活', '2019-02-12 10:10:18', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/login2.jpg', 1, '第四届NEW ERA青年电影季', 0, 0, 152);
INSERT INTO `video` VALUES (25, '20', '影视', '2019-02-12 10:10:19', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/photo.PNG', 1, '第四届NEW ERA青年电影季', 0, 0, 166);
INSERT INTO `video` VALUES (26, '16', '娱乐', '2019-02-12 10:10:15', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/photo.PNG', 1, '第四届NEW ERA青年电影季', 0, 0, 158);
INSERT INTO `video` VALUES (27, '9', '动漫', '2019-03-12 10:10:10', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/login2.jpg', 1, '第四届NEW ERA青年电影季', 0, 0, 4);
INSERT INTO `video` VALUES (28, '10', '动漫', '2019-04-12 10:10:10', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/photo.PNG', 1, '第四届NEW ERA青年电影季', 0, 0, 2);
INSERT INTO `video` VALUES (29, '11', '动漫', '2019-06-12 10:10:10', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/login2.jpg', 1, '第四届NEW ERA青年电影季', 0, 0, 1);
INSERT INTO `video` VALUES (30, '12', '动漫', '2019-02-12 10:10:11', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/photo.PNG', 1, '第四届NEW ERA青年电影季', 0, 0, 0);
INSERT INTO `video` VALUES (31, '13', '动漫', '2019-02-12 10:10:12', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/login2.jpg', 1, '第四届NEW ERA青年电影季', 0, 0, 0);
INSERT INTO `video` VALUES (32, '14', '动漫', '2019-02-12 10:10:13', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/photo.PNG', 1, '第四届NEW ERA青年电影季', 0, 0, 0);
INSERT INTO `video` VALUES (33, '15', '动漫', '2019-02-12 10:10:14', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/login2.jpg', 1, '第四届NEW ERA青年电影季', 0, 0, 0);
INSERT INTO `video` VALUES (34, '16', '动漫', '2019-02-12 10:10:15', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/photo.PNG', 1, '第四届NEW ERA青年电影季', 0, 0, 0);
INSERT INTO `video` VALUES (35, '17', '动漫', '2019-02-12 10:10:16', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/login2.jpg', 1, '第四届NEW ERA青年电影季', 0, 0, 0);
INSERT INTO `video` VALUES (36, '18', '动漫', '2019-02-12 10:10:17', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/photo.PNG', 1, '第四届NEW ERA青年电影季', 0, 0, 0);
INSERT INTO `video` VALUES (37, '19', '动漫', '2019-02-12 10:10:18', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/login2.jpg', 1, '第四届NEW ERA青年电影季', 0, 0, 0);
INSERT INTO `video` VALUES (38, '20', '动漫', '2019-02-12 10:10:19', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/photo.PNG', 1, '第四届NEW ERA青年电影季', 0, 0, 12);
INSERT INTO `video` VALUES (39, '16', '广告', '2019-02-12 10:10:15', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/photo.PNG', 1, '第四届NEW ERA青年电影季', 0, 0, 152);
INSERT INTO `video` VALUES (40, '17', '搞笑', '2019-02-12 10:10:16', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/login2.jpg', 1, '第四届NEW ERA青年电影季', 0, 0, 152);
INSERT INTO `video` VALUES (41, '18', '运动', '2019-02-12 10:10:17', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/photo.PNG', 1, '第四届NEW ERA青年电影季', 0, 0, 153);
INSERT INTO `video` VALUES (42, '19', '生活', '2019-02-12 10:10:18', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/login2.jpg', 1, '第四届NEW ERA青年电影季', 0, 0, 152);
INSERT INTO `video` VALUES (43, '20', '影视', '2019-02-12 10:10:19', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/photo.PNG', 1, '第四届NEW ERA青年电影季', 0, 0, 163);
INSERT INTO `video` VALUES (44, '16', '娱乐', '2019-02-12 10:10:15', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/photo.PNG', 1, '第四届NEW ERA青年电影季', 0, 0, 154);
INSERT INTO `video` VALUES (45, '一二三四五六七八九十一二三四五六七八九十一二', '动漫', '2019-12-12 00:00:00', 22, '一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十', '/video/videourl/video.mp4', '/video/imagesurl/photo1.PNG', 1, '第四届NEW ERA青年电影季', 0, 1, 484);
INSERT INTO `video` VALUES (46, '2', '动漫,教育,音乐,科技,游戏', '2019-08-12 12:12:12', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/login2.jpg', 1, '第四届NEW ERA青年电影季', 0, 1, 203);
INSERT INTO `video` VALUES (47, '47', '动漫', '2019-08-12 10:10:10', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/photo.PNG', 1, '第四届NEW ERA青年电影季', 0, 0, 87);
INSERT INTO `video` VALUES (48, '4', '动漫', '2019-10-12 10:10:10', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/photo.PNG', 0, '第四届NEW ERA青年电影季', 0, 0, 4);
INSERT INTO `video` VALUES (49, '5', '动漫', '2019-09-12 10:10:10', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/login2.jpg', 1, '第四届NEW ERA青年电影季', 0, 0, 155);
INSERT INTO `video` VALUES (50, '123456789012345678901234567890123456789012345678901234567890', '动漫', '2019-11-12 10:10:10', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/login2.jpg', 1, '第四届NEW ERA青年电影季', 0, 1, 171);
INSERT INTO `video` VALUES (51, '7', '动漫', '2019-08-13 10:10:10', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/photo.PNG', 1, '第四届NEW ERA青年电影季', 0, 1, 154);
INSERT INTO `video` VALUES (53, '9', '动漫', '2019-03-12 10:10:10', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/login2.jpg', 1, '第四届NEW ERA青年电影季', 0, 0, 4);
INSERT INTO `video` VALUES (54, '10', '动漫', '2019-04-12 10:10:10', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/photo.PNG', 1, '第四届NEW ERA青年电影季', 0, 0, 3);
INSERT INTO `video` VALUES (55, '11', '动漫', '2019-06-12 10:10:10', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/login2.jpg', 1, '第四届NEW ERA青年电影季', 0, 0, 0);
INSERT INTO `video` VALUES (56, '12', '动漫', '2019-02-12 10:10:11', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/photo.PNG', 1, '第四届NEW ERA青年电影季', 0, 0, 0);
INSERT INTO `video` VALUES (57, '13', '动漫', '2019-02-12 10:10:12', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/login2.jpg', 1, '第四届NEW ERA青年电影季', 0, 0, 0);
INSERT INTO `video` VALUES (58, '14', '动漫', '2019-02-12 10:10:13', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/photo.PNG', 1, '第四届NEW ERA青年电影季', 0, 0, 0);
INSERT INTO `video` VALUES (59, '15', '动漫', '2019-02-12 10:10:14', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/login2.jpg', 1, '第四届NEW ERA青年电影季', 0, 0, 0);
INSERT INTO `video` VALUES (60, '16', '动漫', '2019-02-12 10:10:15', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/photo.PNG', 1, '第四届NEW ERA青年电影季', 0, 0, 0);
INSERT INTO `video` VALUES (61, '17', '动漫', '2019-02-12 10:10:16', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/login2.jpg', 1, '第四届NEW ERA青年电影季', 0, 0, 0);
INSERT INTO `video` VALUES (62, '18', '动漫', '2019-02-12 10:10:17', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/photo.PNG', 1, '第四届NEW ERA青年电影季', 0, 0, 0);
INSERT INTO `video` VALUES (63, '19', '动漫', '2019-02-12 10:10:18', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/login2.jpg', 1, '第四届NEW ERA青年电影季', 0, 0, 0);
INSERT INTO `video` VALUES (64, '20', '动漫', '2019-02-12 10:10:19', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/photo.PNG', 1, '第四届NEW ERA青年电影季', 0, 0, 12);
INSERT INTO `video` VALUES (65, '16', '广告', '2019-02-12 10:10:15', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/photo.PNG', 1, '第四届NEW ERA青年电影季', 0, 0, 152);
INSERT INTO `video` VALUES (66, '17', '搞笑', '2019-02-12 10:10:16', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/login2.jpg', 1, '第四届NEW ERA青年电影季', 0, 0, 152);
INSERT INTO `video` VALUES (67, '18', '运动', '2019-02-12 10:10:17', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/photo.PNG', 1, '第四届NEW ERA青年电影季', 0, 0, 152);
INSERT INTO `video` VALUES (68, '19', '生活', '2019-02-12 10:10:18', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/login2.jpg', 1, '第四届NEW ERA青年电影季', 0, 0, 152);
INSERT INTO `video` VALUES (69, '20', '影视', '2019-02-12 10:10:19', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/photo.PNG', 1, '第四届NEW ERA青年电影季', 0, 0, 163);
INSERT INTO `video` VALUES (70, '16', '娱乐', '2019-02-12 10:10:15', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/photo.PNG', 1, '第四届NEW ERA青年电影季', 0, 0, 154);
INSERT INTO `video` VALUES (71, '9', '动漫', '2019-03-12 10:10:10', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/login2.jpg', 1, '第四届NEW ERA青年电影季', 0, 0, 4);
INSERT INTO `video` VALUES (72, '10', '动漫', '2019-04-12 10:10:10', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/photo.PNG', 1, '第四届NEW ERA青年电影季', 0, 0, 2);
INSERT INTO `video` VALUES (73, '11', '动漫', '2019-06-12 10:10:10', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/login2.jpg', 1, '第四届NEW ERA青年电影季', 0, 0, 1);
INSERT INTO `video` VALUES (74, '12', '动漫', '2019-02-12 10:10:11', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/photo.PNG', 1, '第四届NEW ERA青年电影季', 0, 0, 0);
INSERT INTO `video` VALUES (75, '13', '动漫', '2019-02-12 10:10:12', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/login2.jpg', 1, '第四届NEW ERA青年电影季', 0, 0, 0);
INSERT INTO `video` VALUES (76, '14', '动漫', '2019-02-12 10:10:13', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/photo.PNG', 1, '第四届NEW ERA青年电影季', 0, 0, 0);
INSERT INTO `video` VALUES (77, '15', '动漫', '2019-02-12 10:10:14', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/login2.jpg', 1, '第四届NEW ERA青年电影季', 0, 0, 0);
INSERT INTO `video` VALUES (78, '16', '动漫', '2019-02-12 10:10:15', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/photo.PNG', 1, '第四届NEW ERA青年电影季', 0, 0, 0);
INSERT INTO `video` VALUES (79, '17', '动漫', '2019-02-12 10:10:16', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/login2.jpg', 1, '第四届NEW ERA青年电影季', 0, 0, 1);
INSERT INTO `video` VALUES (80, '18', '动漫', '2019-02-12 10:10:17', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/photo.PNG', 1, '第四届NEW ERA青年电影季', 0, 0, 0);
INSERT INTO `video` VALUES (81, '19', '动漫', '2019-02-12 10:10:18', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/login2.jpg', 1, '第四届NEW ERA青年电影季', 0, 0, 0);
INSERT INTO `video` VALUES (82, '20', '动漫', '2019-02-12 10:10:19', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/photo.PNG', 1, '第四届NEW ERA青年电影季', 0, 0, 12);
INSERT INTO `video` VALUES (83, '16', '广告', '2019-02-12 10:10:15', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/photo.PNG', 1, '第四届NEW ERA青年电影季', 0, 0, 152);
INSERT INTO `video` VALUES (84, '17', '搞笑', '2019-02-12 10:10:16', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/login2.jpg', 1, '第四届NEW ERA青年电影季', 0, 0, 152);
INSERT INTO `video` VALUES (85, '18', '运动', '2019-02-12 10:10:17', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/photo.PNG', 1, '第四届NEW ERA青年电影季', 0, 0, 153);
INSERT INTO `video` VALUES (86, '19', '生活', '2019-02-12 10:10:18', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/login2.jpg', 1, '第四届NEW ERA青年电影季', 0, 0, 152);
INSERT INTO `video` VALUES (87, '20', '影视', '2019-02-12 10:10:19', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/photo.PNG', 1, '第四届NEW ERA青年电影季', 0, 0, 163);
INSERT INTO `video` VALUES (88, '16', '娱乐', '2019-02-12 10:10:15', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/photo.PNG', 1, '第四届NEW ERA青年电影季', 0, 0, 154);
INSERT INTO `video` VALUES (93, '16', '动漫', '2019-02-12 10:10:15', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/photo.PNG', 1, '第四届NEW ERA青年电影季', 0, 0, 0);
INSERT INTO `video` VALUES (94, '17', '动漫', '2019-02-12 10:10:16', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/login2.jpg', 1, '第四届NEW ERA青年电影季', 0, 0, 0);
INSERT INTO `video` VALUES (95, '18', '动漫', '2019-02-12 10:10:17', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/photo.PNG', 1, '第四届NEW ERA青年电影季', 0, 0, 0);
INSERT INTO `video` VALUES (96, '19', '动漫', '2019-02-12 10:10:18', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/login2.jpg', 1, '第四届NEW ERA青年电影季', 0, 0, 0);
INSERT INTO `video` VALUES (98, '16', '广告', '2019-02-12 10:10:15', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/photo.PNG', 1, '第四届NEW ERA青年电影季', 0, 0, 153);
INSERT INTO `video` VALUES (99, '17', '搞笑', '2019-02-12 10:10:16', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/login2.jpg', 1, '第四届NEW ERA青年电影季', 0, 0, 153);
INSERT INTO `video` VALUES (100, '18', '运动', '2019-02-12 10:10:17', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/photo.PNG', 1, '第四届NEW ERA青年电影季', 0, 0, 152);
INSERT INTO `video` VALUES (101, '19', '生活', '2019-02-12 10:10:18', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/login2.jpg', 0, '第四届NEW ERA青年电影季', 0, 0, 0);
INSERT INTO `video` VALUES (102, '20', '影视', '2019-02-12 10:10:19', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/photo.PNG', -1, '第四届NEW ERA青年电影季', 0, 0, 11);
INSERT INTO `video` VALUES (103, '18', '动漫', '2019-02-12 10:10:17', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/photo.PNG', 0, '第四届NEW ERA青年电影季', 0, 0, 0);
INSERT INTO `video` VALUES (104, '19', '动漫', '2019-02-12 10:10:18', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/login2.jpg', 0, '第四届NEW ERA青年电影季', 0, 0, 0);
INSERT INTO `video` VALUES (105, '20', '动漫', '2019-02-12 10:10:19', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/photo.PNG', 0, '第四届NEW ERA青年电影季', 0, 0, 12);
INSERT INTO `video` VALUES (106, '16', '广告', '2019-02-12 10:10:15', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/photo.PNG', 0, '第四届NEW ERA青年电影季', 0, 0, 7);
INSERT INTO `video` VALUES (107, '17', '搞笑', '2019-02-12 10:10:16', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/login2.jpg', 0, '第四届NEW ERA青年电影季', 0, 0, 1);
INSERT INTO `video` VALUES (108, '18', '运动', '2019-02-12 10:10:17', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/photo.PNG', 0, '第四届NEW ERA青年电影季', 0, 0, 1);
INSERT INTO `video` VALUES (109, '19', '生活', '2019-02-12 10:10:18', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/login2.jpg', 0, '第四届NEW ERA青年电影季', 0, 0, 0);
INSERT INTO `video` VALUES (110, '20', '影视', '2019-02-12 10:10:19', 1, '/video/videourl/true/video.mp4', '/video/videourl/video.mp4', '/video/imagesurl/photo.PNG', 0, '第四届NEW ERA青年电影季', 0, 0, 12);
INSERT INTO `video` VALUES (113, 'ss', '动漫', '2020-03-04 13:07:02', 1, 'sss', '/video/videourl/20200304130701_ss.mp4', NULL, -2, '普通', 0, 0, 0);
INSERT INTO `video` VALUES (118, '测试1', '动漫', '2020-03-04 21:26:37', 1, 'ssss', '/video/videourl/20200304212636_测试1.mp4', NULL, -2, '普通', 0, 0, 0);
INSERT INTO `video` VALUES (119, '测试2', '动漫', '2020-03-04 21:26:42', 1, 'ssss', '/video/videourl/20200304212641_测试2.mp4', NULL, -1, '普通', 0, 0, 0);
INSERT INTO `video` VALUES (120, '测试3', '动漫', '2020-03-04 21:26:46', 1, 'ssss', '/video/videourl/20200304212646_测试3.mp4', '/video/imagesurl/120_20200304212750_photo.PNG', 1, '普通', 0, 0, 165);
INSERT INTO `video` VALUES (123, '测试5', '动漫,教育', '2020-03-04 21:50:45', 1, 's\'s\'ssss', '/video/videourl/20200304215045_测试5.mp4', NULL, -1, '普通', 0, 0, 0);
INSERT INTO `video` VALUES (130, 'video', '动漫', '2020-03-04 22:38:05', 1, 'ss', '/video/videourl/130_20200304223805_video.png', NULL, -2, '普通', 0, 0, 0);
