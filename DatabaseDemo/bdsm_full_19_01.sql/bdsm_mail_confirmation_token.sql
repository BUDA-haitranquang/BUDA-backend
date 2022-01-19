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
-- Table structure for table `mail_confirmation_token`
--

DROP TABLE IF EXISTS `mail_confirmation_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mail_confirmation_token` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `confirmed_at` datetime DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `expired_at` datetime NOT NULL,
  `token` varchar(255) NOT NULL,
  `user_id` bigint DEFAULT NULL,
  `mail_token_type` int DEFAULT NULL,
  `target_email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4e3qva5c4quedgjl8l0cpmr5r` (`user_id`),
  CONSTRAINT `FK4e3qva5c4quedgjl8l0cpmr5r` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mail_confirmation_token`
--

LOCK TABLES `mail_confirmation_token` WRITE;
/*!40000 ALTER TABLE `mail_confirmation_token` DISABLE KEYS */;
INSERT INTO `mail_confirmation_token` VALUES (1,'2022-01-07 15:14:52','2022-01-07 15:14:27','2022-01-07 15:29:27','0ed2ec4f-2b65-42ca-ad49-4b743a95dc56',18,NULL,NULL),(3,'2022-01-12 14:21:53','2022-01-12 14:21:02','2022-01-12 14:36:02','f969b75c-34b7-4071-b389-cf7117cd9c29',19,0,'haitranquang.official@gmail.com'),(4,'2022-01-12 14:30:16','2022-01-12 14:27:47','2022-01-12 14:42:47','7e400d14-5055-441a-8563-c1bd6821df5a',19,1,'agoodbudaaccount@gmail.com');
/*!40000 ALTER TABLE `mail_confirmation_token` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-01-19 10:00:22
