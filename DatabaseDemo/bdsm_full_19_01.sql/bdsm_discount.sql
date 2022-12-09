-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: bdsm
-- ------------------------------------------------------
-- Server version	8.0.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `discount`
--

DROP TABLE IF EXISTS `discount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `discount` (
  `discount_id` bigint NOT NULL AUTO_INCREMENT,
  `cash` double DEFAULT '0',
  `cash_limit` double DEFAULT '0',
  `created_time` datetime DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `discount_type` varchar(255) DEFAULT NULL,
  `expiry_time` datetime DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  `order_count` int DEFAULT '0',
  `percentage` double DEFAULT '0',
  `user_id` bigint DEFAULT NULL,
  `minimum_sell_order_cost` double DEFAULT '0',
  PRIMARY KEY (`discount_id`),
  KEY `discount_user_id_index` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discount`
--

LOCK TABLES `discount` WRITE;
/*!40000 ALTER TABLE `discount` DISABLE KEYS */;
INSERT INTO `discount` VALUES (1,0,20000,'2021-06-15 00:00:00','10% Discount, Max 20000','BOTH','2021-06-20 00:00:00','First',0,10,2,0),(2,0,30000,'2021-06-21 00:00:00','10% Discount, Max 30000','BOTH','2021-06-23 00:00:00','Second',1,10,1,0),(3,0,50000,'2021-06-25 00:00:00','5% Discount, Max 50000','BOTH','2021-06-28 00:00:00','Third',0,5,2,0),(4,0,40000,'2021-06-29 00:00:00','10% Discount, Max 40000','BOTH','2021-07-02 00:00:00','Fourth',0,10,2,0),(5,0,20000,'2021-07-04 00:00:00','15% Discount, Max 20000','BOTH','2021-07-08 00:00:00','Fifth',0,15,2,0),(6,0,60000,'2021-07-10 00:00:00','5% Discount, Max 60000','BOTH','2021-07-14 00:00:00','Sixth',0,5,1,0),(7,0,30000,'2021-07-15 00:00:00','25% Discount, Max 30000','BOTH','2021-07-21 00:00:00','Seventh',0,25,2,0),(8,0,0,'2021-06-15 00:00:00','No discount','BOTH','9999-12-31 00:00:00','Eigth',0,0,1,0),(9,15000,15000,'2021-09-18 09:55:59','Discount 15000','CASH_ONLY','9999-12-31 00:00:00','Ninth',1,0,1,0),(10,10000,10000,'2021-09-13 08:11:21','Discount 10000','CASH_ONLY','9999-12-31 00:00:00','Tenth',0,0,1,0),(11,20000,20000,'2021-08-31 05:30:00','Discount 20000','CASH_ONLY','9999-12-31 00:00:00','Eleventh',0,0,1,0),(12,10000,10000,'2021-10-14 17:47:18','Discount 10000','CASH_ONLY','9999-12-31 00:00:00','Twelfth',0,0,1,0),(13,20000,20000,'2021-08-22 09:15:55','Discount 20000','CASH_ONLY','9999-12-31 00:00:00','Thirteenth',0,0,1,0),(14,15000,15000,'2021-11-03 03:08:36','Discount 15000','CASH_ONLY','9999-12-31 00:00:00','Fourteenth',1,0,2,0),(15,0,0,'2021-08-20 06:07:21','10% Discount','PERCENTAGE_ONLY','9999-12-31 00:00:00','Fifteenth',1,10,2,0),(16,0,0,'2021-08-03 15:29:54','5% Discount','PERCENTAGE_ONLY','9999-12-31 00:00:00','Sixteenth',0,5,1,0),(17,0,0,'2021-08-29 01:48:08','15% Discount','PERCENTAGE_ONLY','9999-12-31 00:00:00','Seventeenth',0,15,1,0),(18,0,0,'2021-08-08 16:23:35','20% Discount','PERCENTAGE_ONLY','9999-12-31 00:00:00','Eighteenth',0,20,1,0),(19,0,0,'2021-09-03 12:31:22','15% Discount','PERCENTAGE_ONLY','9999-12-31 00:00:00','Nineteenth',0,15,1,0),(20,0,0,'2021-10-23 05:03:16','10% Discount','PERCENTAGE_ONLY','9999-12-31 00:00:00','Twentyth',2,10,2,0),(21,25000,25000,'2021-09-21 09:20:23','Discount 25000','CASH_ONLY','9999-12-31 00:00:00','Twentyfirst',0,0,1,0),(22,30000,30000,'2021-08-08 05:31:33','Discount 30000','CASH_ONLY','9999-12-31 00:00:00','Twentysecond',0,0,2,0),(23,0,20000,'2021-09-30 02:15:46','5% Discount, Max 20000','BOTH','9999-12-31 00:00:00','Twentythird',0,5,2,0),(24,0,20000,'2021-08-18 09:33:20','10% Discount, Max 20000','BOTH','9999-12-31 00:00:00','Twentyfourth',0,10,1,0),(25,0,30000,'2021-08-26 13:31:46','10% Discount, Max 30000','BOTH','9999-12-31 00:00:00','Twentyfifth',0,10,1,0),(26,0,30000,'2021-09-10 12:41:54','15% Discount, Max 30000','BOTH','9999-12-31 00:00:00','Twentysixth',0,15,1,0),(27,0,40000,'2021-09-19 06:41:53','15% Discount, Max 40000','BOTH','9999-12-31 00:00:00','Twentyseventh',0,15,2,0),(28,0,40000,'2021-10-19 05:21:40','20% Discount, Max 40000','BOTH','9999-12-31 00:00:00','Twentyeighth',0,20,1,0),(29,0,50000,'2021-08-03 01:44:55','20% Discount, Max 50000','BOTH','9999-12-31 00:00:00','Twentynineth',0,20,1,0),(30,0,15000,'2021-07-30 18:16:04','5% Discount, Max 15000','BOTH','9999-12-31 00:00:00','Thirdty',0,5,2,0);
/*!40000 ALTER TABLE `discount` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-01-19 10:00:24
