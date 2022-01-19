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
-- Table structure for table `plan`
--

DROP TABLE IF EXISTS `plan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `plan` (
  `plan_id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(200) DEFAULT NULL,
  `duration` int DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `picture_id` bigint DEFAULT NULL,
  `price` double NOT NULL,
  `plan_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`plan_id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plan`
--

LOCK TABLES `plan` WRITE;
/*!40000 ALTER TABLE `plan` DISABLE KEYS */;
INSERT INTO `plan` VALUES (1,'New plan',30,'Basic',41,200000,NULL),(2,'New plan',30,'Premium',42,350000,NULL),(3,'New plan',30,'No ads',43,250000,NULL),(4,'New plan',30,'Platinum',44,600000,NULL),(5,'New plan',30,'Seeding',235,262000,NULL),(6,'New plan',30,'Sprout',236,449000,NULL),(7,'New plan',30,'Evergreen',237,156000,NULL),(8,'New plan',30,'Mighty Oak',238,135000,NULL),(9,'New plan',30,'Lite',239,109000,NULL),(10,'New plan',30,'Plus',240,439000,NULL),(11,'New plan',30,'Select',241,168000,NULL),(12,'New plan',30,'Silver',242,223000,NULL),(13,'New plan',30,'Diamond',243,111000,NULL),(14,'New plan',30,'Gold',244,187000,NULL),(15,'New plan',30,'Bronze',245,100000,NULL),(16,'Brand new plan',30,'Larkin, Jacobs and Gutmann',635,168000,NULL),(17,'Brand new plan',30,'Mayert-Kuhlman',636,289000,NULL),(18,'Brand new plan',30,'Ward and Sons',637,239000,NULL),(19,'Brand new plan',30,'Corwin and Sons',638,227000,NULL),(20,'Brand new plan',30,'Hermiston Group',639,319000,NULL),(21,'Brand new plan',30,'Turcotte, Stanton and Luettgen',640,213000,NULL),(22,'Brand new plan',30,'Beatty, Becker and Armstrong',641,309000,NULL),(23,'Brand new plan',30,'Feeney, Steuber and Bradtke',642,203000,NULL),(24,'Brand new plan',30,'Harber, Davis and Hodkiewicz',643,289000,NULL),(25,'Brand new plan',30,'Swaniawski-Hodkiewicz',644,135000,NULL),(26,'Brand new plan',30,'Rolfson, Kuhic and Koch',645,311000,NULL),(27,'Brand new plan',30,'Morissette, Balistreri and Bahringer',646,149000,NULL),(28,'Brand new plan',30,'Keebler Group',647,314000,NULL),(29,'Brand new plan',30,'Hane, Leffler and Nicolas',648,121000,NULL),(30,'Brand new plan',30,'Cassin LLC',649,166000,NULL),(31,'Brand new plan',30,'Gislason, Harvey and Franecki',650,366000,NULL),(32,'Brand new plan',30,'Auer-Bernhard',651,330000,NULL),(33,'Brand new plan',30,'Dach, Ward and Spencer',652,150000,NULL),(34,'Brand new plan',30,'Runte-Spinka',653,262000,NULL),(35,'Brand new plan',30,'Bauch, Bechtelar and Ferry',654,158000,NULL),(36,'Brand new plan',30,'Dicki, Ondricka and Olson',655,208000,NULL),(37,'Brand new plan',30,'Nader, Klocko and McCullough',656,165000,NULL),(38,'Brand new plan',30,'Heidenreich Inc',657,100000,NULL),(39,'Brand new plan',30,'Schamberger, Ryan and Kovacek',658,208000,NULL),(40,'Brand new plan',30,'Collins Inc',659,339000,NULL),(41,'Brand new plan',30,'Klein Group',660,368000,NULL),(42,'Brand new plan',30,'Wunsch, Abshire and Jerde',661,122000,NULL),(43,'Brand new plan',30,'Olson, Gaylord and Raynor',662,312000,NULL),(44,'Brand new plan',30,'Klein-Hackett',663,190000,NULL),(45,'Brand new plan',30,'Berge Inc',664,216000,NULL),(46,'Brand new plan',30,'Lemke Group',665,108000,NULL),(47,'Brand new plan',30,'Jacobson Inc',666,394000,NULL),(48,'Brand new plan',30,'Cronin, Lockman and Mraz',667,345000,NULL),(49,'Brand new plan',30,'Carroll Inc',668,140000,NULL),(50,'Brand new plan',30,'Conroy, Block and Skiles',669,170000,NULL);
/*!40000 ALTER TABLE `plan` ENABLE KEYS */;
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
