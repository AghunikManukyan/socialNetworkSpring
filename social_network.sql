/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.18-log : Database - social_network
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`social_network` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `social_network`;

/*Table structure for table `friend_request` */

DROP TABLE IF EXISTS `friend_request`;

CREATE TABLE `friend_request` (
  `from_id` int(11) NOT NULL AUTO_INCREMENT,
  `to_id` int(11) NOT NULL,
  PRIMARY KEY (`from_id`,`to_id`),
  KEY `to_id` (`to_id`),
  CONSTRAINT `friend_request_ibfk_1` FOREIGN KEY (`to_id`) REFERENCES `user` (`id`),
  CONSTRAINT `friend_request_ibfk_2` FOREIGN KEY (`from_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `friend_request` */

/*Table structure for table `message` */

DROP TABLE IF EXISTS `message`;

CREATE TABLE `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `from_id` int(11) NOT NULL,
  `to_id` int(11) NOT NULL,
  `message` varchar(255) NOT NULL,
  `send_date` datetime NOT NULL,
  `text` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`,`from_id`,`to_id`),
  KEY `from_id` (`from_id`),
  KEY `to_id` (`to_id`),
  CONSTRAINT `message_ibfk_1` FOREIGN KEY (`from_id`) REFERENCES `user` (`id`),
  CONSTRAINT `message_ibfk_2` FOREIGN KEY (`to_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `message` */

insert  into `message`(`id`,`from_id`,`to_id`,`message`,`send_date`,`text`) values (1,8,9,'barev','2019-03-20 07:41:15',NULL),(2,9,8,'reagareg','2019-03-20 10:51:18',NULL);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `surname` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `user_type` enum('USER','ADMIN') NOT NULL DEFAULT 'USER',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`name`,`surname`,`email`,`password`,`user_type`) values (8,'Poxos','Poxosyan','Poxos@mail.ru','$2a$10$EGkna2YXg3ChzMsuTu30WOKYcP7Eq6pd86EIRPMPptwYSXlSy0rym','USER'),(9,'Petros','Petrosyan','Petros@mail.ru','$2a$10$M0MV0RhKWQ66qDwdskldj.UeJaDk2BaOVW.zgIZ54rdEQYIHpOJwS','USER');

/*Table structure for table `user_friend` */

DROP TABLE IF EXISTS `user_friend`;

CREATE TABLE `user_friend` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `friend_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`friend_id`),
  KEY `friend_id` (`friend_id`),
  CONSTRAINT `user_friend_ibfk_2` FOREIGN KEY (`friend_id`) REFERENCES `user` (`id`),
  CONSTRAINT `user_friend_ibfk_3` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `user_friend` */

insert  into `user_friend`(`user_id`,`friend_id`) values (8,9);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
