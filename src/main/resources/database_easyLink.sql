
drop database if exists easylink;
create database easylink;
use easylink;

--
-- Pleast do not delete things you do not know about
--

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
	SET NAMES utf8;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `easylink`
--

DROP TABLE IF EXISTS `easylink`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
CREATE TABLE `easylink` (
  `id` varchar(1000) NOT NULL,
  `full_url` varchar(2083) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `easylink`
--

LOCK TABLES `easylink` WRITE;
INSERT INTO `easylink` VALUES
('lockTables','https://dev.mysql.com/doc/refman/8.0/en/lock-tables.html'),
('primaryKey','https://www.w3schools.com/Sql/sql_primarykey.asp'),
('mySQL','http://www.vogella.com/tutorials/MySQLJava/article.html#jdbc');
UNLOCK TABLES;

--
-- Table structure for table `easylink_acc`
--

DROP TABLE IF EXISTS `easylink_acc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
CREATE TABLE `easylink_acc` (
  `username` varchar(24) NOT NULL,
  `password` varchar(24) NOT NULL,
  `email` varchar(64) NOT NULL,
   `id` varchar(1000) NOT NULL,
  `full_url` varchar(2083) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `easylink_acc`
--


LOCK TABLES `easylink_acc` WRITE;
INSERT INTO `easylink_acc`(`username`,`password`,`email`,`id`,`full_url`) VALUES
('user','pass','email','short','long ass line'),
('user1','pass1','email1','shorter','longer ass lineeeee');
UNLOCK TABLES;


--
-- Table structure for table `easylink_reg_data`
--

DROP TABLE IF EXISTS `easylink_reg_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
CREATE TABLE `easylink_reg_data` (
  `user_id` int(16) NOT NULL AUTO_INCREMENT,
  `username` varchar(24) NOT NULL UNIQUE,
  `password` varchar(24) NOT NULL,
  `email` varchar(64) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 auto_increment=1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `easylink_reg_data`
--


LOCK TABLES `easylink_reg_data` WRITE;
INSERT INTO `easylink_reg_data`(`username`,`password`,`email`) VALUES
('user','pass','email'),
('user1','pass1','email1');
UNLOCK TABLES;



--
-- Please do not delete things you do not know about
--

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
