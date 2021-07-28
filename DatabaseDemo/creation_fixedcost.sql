-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: creation
-- ------------------------------------------------------
-- Server version	8.0.25

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
-- Table structure for table `fixedcost`
--

DROP TABLE IF EXISTS `fixedcost`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fixedcost` (
  `FixedCostID` int NOT NULL,
  `FixedCost_UUID` char(36) DEFAULT NULL,
  `UserID` int DEFAULT NULL,
  `MoneyAmount` double DEFAULT NULL,
  `Name` varchar(40) DEFAULT NULL,
  `PeriodOfTime` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`FixedCostID`),
  KEY `UserID1_fk_idx` (`UserID`),
  CONSTRAINT `UserID1_fk` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fixedcost`
--

LOCK TABLES `fixedcost` WRITE;
/*!40000 ALTER TABLE `fixedcost` DISABLE KEYS */;
INSERT INTO `fixedcost` VALUES (1,'591f2ee7-ee8c-11eb-b72b-dc4a3ee3e76f',3,200000,'Alpha','2 months'),(2,'5e04279c-ee8c-11eb-b72b-dc4a3ee3e76f',7,300000,'Beta','3 months'),(3,'653fd77c-ee8c-11eb-b72b-dc4a3ee3e76f',9,350000,'Gamma','1 month'),(4,'686668cb-ee8c-11eb-b72b-dc4a3ee3e76f',12,600000,'Theta','6 months');
/*!40000 ALTER TABLE `fixedcost` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-07-27 10:49:28
