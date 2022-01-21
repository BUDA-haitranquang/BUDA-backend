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
-- Table structure for table `product_left_log`
--

DROP TABLE IF EXISTS `product_left_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_left_log` (
  `product_left_log_id` bigint NOT NULL AUTO_INCREMENT,
  `amount_left_change` int DEFAULT NULL,
  `creation_time` datetime DEFAULT NULL,
  `message` varchar(1000) DEFAULT NULL,
  `staff_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  PRIMARY KEY (`product_left_log_id`),
  KEY `product_left_log_user_id_index` (`user_id`),
  KEY `product_left_log_product_id_index` (`product_id`),
  KEY `product_left_log_staff_id_index` (`staff_id`),
  CONSTRAINT `FK5wnlsgf0spxk24pwp2s2ydsno` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=118 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_left_log`
--

LOCK TABLES `product_left_log` WRITE;
/*!40000 ALTER TABLE `product_left_log` DISABLE KEYS */;
INSERT INTO `product_left_log` VALUES (1,-1,'2021-06-15 00:00:00','New order',5,5,1),(2,-1,'2021-06-15 00:00:00','New order',5,5,9),(3,-2,'2021-06-16 00:00:00','New order',8,8,3),(4,-1,'2021-06-20 00:00:00','New order',9,9,2),(5,-1,'2021-06-20 00:00:00','New order',9,9,13),(6,-1,'2021-06-21 00:00:00','New order',10,10,12),(7,-1,'2021-06-22 00:00:00','New order',12,12,15),(8,-1,'2021-06-22 00:00:00','New order',12,12,13),(9,-2,'2021-06-23 00:00:00','New order',14,14,5),(10,-1,'2021-06-26 00:00:00','New order',3,3,10),(11,-1,'2021-06-26 00:00:00','New order',3,3,6),(12,-2,'2021-06-28 00:00:00','New order',8,8,8),(13,-1,'2021-06-29 00:00:00','New order',9,9,11),(14,-1,'2021-06-29 00:00:00','New order',9,9,14),(15,-2,'2021-06-30 00:00:00','New order',7,7,2),(16,-1,'2021-07-02 00:00:00','New order',6,6,4),(17,-1,'2021-07-02 00:00:00','New order',6,6,6),(18,-2,'2021-07-05 00:00:00','New order',4,4,15),(19,-1,'2021-07-06 00:00:00','New order',15,15,10),(20,-1,'2021-07-06 00:00:00','New order',15,15,11),(21,-2,'2021-07-07 00:00:00','New order',13,13,9),(22,-1,'2021-07-08 00:00:00','New order',11,11,1),(23,-1,'2021-07-08 00:00:00','New order',11,11,2),(24,-2,'2021-07-09 00:00:00','New order',6,6,7),(25,-1,'2021-07-10 00:00:00','New order',10,10,5),(26,-1,'2021-07-10 00:00:00','New order',10,10,6),(27,-2,'2021-07-12 00:00:00','New order',12,12,1),(28,-1,'2021-07-13 00:00:00','New order',14,14,8),(29,-1,'2021-07-15 00:00:00','New order',14,14,13),(30,-2,'2021-07-15 00:00:00','New order',8,8,4),(31,-2,'2021-09-10 23:31:36','More orders',79,79,16),(32,-2,'2021-09-16 13:17:04','More orders',58,58,9),(33,-2,'2021-09-13 11:00:52','More orders',52,52,98),(34,-2,'2021-09-07 04:12:27','More orders',87,87,60),(35,-2,'2021-10-07 17:03:07','More orders',77,77,8),(36,-2,'2021-11-03 21:05:31','More orders',22,22,57),(37,-2,'2021-10-24 22:08:45','More orders',82,82,61),(38,-2,'2021-10-02 00:01:38','More orders',41,41,33),(39,-2,'2021-08-26 21:11:56','More orders',58,58,82),(40,-1,'2021-07-26 02:55:20','More orders',66,66,11),(41,-1,'2021-09-19 17:31:45','More orders',58,58,10),(42,-2,'2021-08-19 03:17:48','More orders',89,89,15),(43,-2,'2021-09-17 11:52:34','More orders',73,73,44),(44,-2,'2021-09-20 03:55:59','More orders',98,98,74),(45,-2,'2021-08-29 19:56:27','More orders',69,69,40),(46,-1,'2021-08-26 07:35:24','More orders',51,51,76),(47,-1,'2021-09-16 14:17:45','More orders',46,46,60),(48,-1,'2021-09-21 16:15:43','More orders',79,79,72),(49,-2,'2021-07-17 19:24:35','More orders',55,55,78),(50,-2,'2021-08-14 11:08:57','More orders',37,37,75),(51,-1,'2021-07-27 15:52:25','More orders',20,20,42),(52,-2,'2021-08-19 05:16:44','More orders',88,88,82),(53,-1,'2021-09-20 18:30:13','More orders',80,80,85),(54,-2,'2021-10-06 11:30:01','More orders',37,37,78),(55,-1,'2021-10-26 18:29:36','More orders',42,42,33),(56,-1,'2021-10-30 01:50:38','More orders',99,99,32),(57,-1,'2021-09-07 10:53:28','More orders',68,68,61),(58,-1,'2021-08-01 00:59:35','More orders',43,43,6),(59,-2,'2021-08-25 01:55:52','More orders',8,8,50),(60,-2,'2021-08-19 00:28:55','More orders',14,14,28),(61,-2,'2021-08-22 01:50:04','More orders',46,46,91),(62,-2,'2021-08-20 07:34:21','More orders',86,86,72),(63,-2,'2021-08-19 20:28:59','More orders',94,94,87),(64,-2,'2021-10-12 02:28:24','More orders',9,9,17),(65,-2,'2021-10-02 00:55:12','More orders',61,61,25),(66,-1,'2021-09-17 11:48:39','More orders',81,81,74),(67,-1,'2021-09-29 02:24:00','More orders',20,20,96),(68,-2,'2021-10-17 13:29:04','More orders',57,57,54),(69,-2,'2021-08-02 09:50:35','More orders',23,23,84),(70,-1,'2021-10-28 05:11:09','More orders',46,46,57),(71,-2,'2021-08-14 11:00:06','More orders',59,59,31),(72,-2,'2021-10-24 21:43:33','More orders',58,58,86),(73,-1,'2021-10-07 19:49:39','More orders',13,13,36),(74,-2,'2021-08-09 22:10:38','More orders',88,88,21),(75,-2,'2021-09-28 21:42:00','More orders',3,3,99),(76,-2,'2021-08-03 21:01:38','More orders',49,49,28),(77,-1,'2021-08-11 16:11:32','More orders',35,35,42),(78,-1,'2021-07-18 19:55:39','More orders',28,28,29),(79,-1,'2021-09-26 00:43:30','More orders',36,36,16),(80,-2,'2021-07-28 15:09:35','More orders',93,93,94),(81,-2,'2021-09-13 01:24:57','More orders',60,60,19),(82,-1,'2021-10-16 16:54:54','More orders',17,17,15),(83,-2,'2021-09-16 22:54:09','More orders',8,8,18),(84,-1,'2021-08-01 22:55:53','More orders',85,85,45),(85,-1,'2021-09-09 05:19:46','More orders',1,1,68),(86,-2,'2021-07-23 02:40:20','More orders',52,52,7),(87,-1,'2021-08-18 06:07:43','More orders',54,54,31),(88,-2,'2021-08-03 06:19:26','More orders',16,16,33),(89,-2,'2021-08-22 12:36:17','More orders',18,18,70),(90,-1,'2021-07-20 16:51:03','More orders',40,40,49),(91,-1,'2021-09-06 00:29:04','More orders',46,46,37),(92,-2,'2021-07-17 18:30:38','More orders',9,9,36),(93,-1,'2021-08-16 20:23:33','More orders',7,7,71),(94,-1,'2021-08-15 01:00:04','More orders',9,9,45),(95,-1,'2021-10-04 10:42:08','More orders',20,20,10),(96,-1,'2021-07-19 16:57:45','More orders',76,76,17),(97,-2,'2021-08-27 23:35:31','More orders',19,19,52),(98,-1,'2021-08-21 10:50:32','More orders',66,66,7),(99,-2,'2021-09-19 06:12:04','More orders',71,71,82),(100,-1,'2021-08-20 09:33:31','More orders',56,56,88),(101,-2,'2021-09-30 09:22:37','More orders',69,69,95),(102,-2,'2021-09-16 22:31:52','More orders',74,74,8),(103,-1,'2021-07-30 18:41:44','More orders',63,63,54),(104,-1,'2021-07-16 12:37:53','More orders',93,93,48),(105,-2,'2021-10-18 19:26:30','More orders',77,77,75),(106,-1,'2021-10-12 03:11:08','More orders',2,2,31),(107,-2,'2021-10-17 06:43:30','More orders',82,82,31),(108,-2,'2021-08-28 07:34:41','More orders',3,3,59),(109,-1,'2021-10-03 17:17:34','More orders',66,66,2),(110,-2,'2021-09-09 16:12:28','More orders',21,21,32),(111,-2,'2021-09-11 17:18:30','More orders',9,9,31),(112,-1,'2021-09-13 11:12:13','More orders',80,80,31),(113,-1,'2021-10-10 10:10:10','More orders',74,74,59),(114,-2,'2021-10-20 15:08:23','More orders',28,28,2),(115,-1,'2021-10-25 17:18:19','More orders',19,19,32),(116,1,'2022-01-11 08:27:25','Cancelled order',NULL,2,161),(117,3,'2022-01-11 08:30:13','Cancelled order',NULL,2,454);
/*!40000 ALTER TABLE `product_left_log` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-01-19 10:00:23
