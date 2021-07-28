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
-- Table structure for table `productcomponent`
--

DROP TABLE IF EXISTS `productcomponent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productcomponent` (
  `ProductID` int DEFAULT NULL,
  `IngredientID` int DEFAULT NULL,
  `RequiredQuantity` int DEFAULT NULL,
  `UserID` int DEFAULT NULL,
  `TotalCost` double DEFAULT NULL,
  KEY `ProductID_fk1_idx` (`ProductID`),
  KEY `IngredientID_fk1_idx` (`IngredientID`),
  KEY `IngredientID_fk2_idx` (`IngredientID`),
  KEY `UserID3_fk_idx` (`UserID`),
  CONSTRAINT `IngredientID_fk2` FOREIGN KEY (`IngredientID`) REFERENCES `ingredient` (`IngredientID`) ON DELETE SET NULL,
  CONSTRAINT `ProductID_fk1` FOREIGN KEY (`ProductID`) REFERENCES `product` (`ProductID`) ON DELETE SET NULL,
  CONSTRAINT `UserID3_fk` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productcomponent`
--

LOCK TABLES `productcomponent` WRITE;
/*!40000 ALTER TABLE `productcomponent` DISABLE KEYS */;
INSERT INTO `productcomponent` VALUES (1,13,2,14,176000),(2,2,3,7,90000),(2,8,1,10,7000),(3,5,2,3,102000),(3,7,1,9,12000),(4,1,2,5,124000),(4,7,1,9,12000),(4,8,1,10,7000),(5,3,2,8,82000),(5,8,1,10,7000),(6,11,2,4,62000),(7,15,2,4,120000),(8,5,1,3,51000),(8,6,1,6,15000),(9,13,1,14,44000),(9,7,1,9,12000),(10,2,2,7,60000),(10,10,1,15,23000),(11,2,2,7,60000),(11,4,1,12,22000),(12,1,2,5,124000),(12,11,2,4,62000),(13,12,1,13,29000),(14,14,1,3,40000),(15,13,2,14,88000),(15,9,1,11,15000);
/*!40000 ALTER TABLE `productcomponent` ENABLE KEYS */;
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
