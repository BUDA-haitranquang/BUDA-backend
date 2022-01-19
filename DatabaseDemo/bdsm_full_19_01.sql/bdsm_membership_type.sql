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
-- Table structure for table `membership_type`
--

DROP TABLE IF EXISTS `membership_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `membership_type` (
  `membership_type_id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(1000) DEFAULT NULL,
  `membership_name` varchar(255) DEFAULT NULL,
  `minimum_spend` double DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `discount_id` bigint DEFAULT NULL,
  PRIMARY KEY (`membership_type_id`),
  KEY `membership_type_user_id_index` (`user_id`),
  KEY `FKgf0e3nwvc7bk67gn849i4s61h` (`discount_id`),
  CONSTRAINT `FKgf0e3nwvc7bk67gn849i4s61h` FOREIGN KEY (`discount_id`) REFERENCES `discount` (`discount_id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `membership_type`
--

LOCK TABLES `membership_type` WRITE;
/*!40000 ALTER TABLE `membership_type` DISABLE KEYS */;
INSERT INTO `membership_type` VALUES (1,'Member','Member',15000,2,1),(2,'Associate member','Associate member',20000,1,2),(3,'Fellow','Fellow',25000,1,8),(4,'Honorary fellow','Honorary fellow',20000,2,3),(5,'Group','Group',25000,2,4),(6,'Member Emeritus','Member Emeritus',30000,2,5),(7,'Individual Associate','Individual Associate',50000,1,6),(8,'Regular','Regular',35000,2,7),(9,'Temporary','Temporary',100000,1,9),(10,'Benefactor','Benefactor',200000,1,10),(11,'Full','Full',500000,1,11),(12,'Companion','Companion',800000,1,12),(13,'Affiliate','Affiliate',1000000,1,13),(14,'Corporate','Corporate',50000,2,14),(15,'Partner','Partner',100000,2,15),(16,'Trasitional','Trasitional',1500000,1,16),(17,'Social','Social',2000000,1,17),(18,'Personal','Personal',2500000,1,18),(19,'Organizational','Organizational',3000000,1,19),(20,'Special','Special',200000,2,20),(21,'Division','Division',3500000,1,21),(22,'Business','Business',300000,2,22),(23,'Charity','Charity',400000,2,23),(24,'Entry level','Entry level',4000000,1,24),(25,'Validity','Validity',4500000,1,25),(26,'Enterprise','Enterprise',5000000,1,26),(27,'Alumni','Alumni',500000,2,27),(28,'Resident','Resident',5500000,1,28),(29,'International','International',6000000,1,29),(30,'State','State',1000000,2,30);
/*!40000 ALTER TABLE `membership_type` ENABLE KEYS */;
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
