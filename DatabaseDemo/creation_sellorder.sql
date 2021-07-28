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
-- Table structure for table `sellorder`
--

DROP TABLE IF EXISTS `sellorder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sellorder` (
  `SellOrderID` int NOT NULL,
  `CustomerID` int DEFAULT NULL,
  `SellOrder_UUID` char(36) DEFAULT NULL,
  `CreationDate` date DEFAULT NULL,
  `Status` char(1) DEFAULT NULL,
  `DiscountID` int DEFAULT NULL,
  `FinalCost` double DEFAULT NULL,
  `Gender` enum('Male','Female') DEFAULT NULL,
  `AgeGroup` enum('<18','18-29','30-44','45-60','>60') DEFAULT NULL,
  `ActualDiscountC` double DEFAULT NULL,
  `ActualDiscountP` double DEFAULT NULL,
  `RealCost` double DEFAULT NULL,
  `UserID` int DEFAULT NULL,
  `CustomerMessage` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`SellOrderID`),
  KEY `CustomerID_fk_idx` (`CustomerID`),
  KEY `UserID_fk5_idx` (`UserID`),
  CONSTRAINT `CustomerID_fk` FOREIGN KEY (`CustomerID`) REFERENCES `customer` (`CustomerID`) ON DELETE SET NULL,
  CONSTRAINT `UserID_fk5` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sellorder`
--

LOCK TABLES `sellorder` WRITE;
/*!40000 ALTER TABLE `sellorder` DISABLE KEYS */;
INSERT INTO `sellorder` VALUES (1,1,'fc0abf01-ea32-11eb-863e-dc4a3ee3e76f','2021-06-15','F',1,240000,'Female','<18',20000,0.08,260000,5,'Good'),(2,2,'032de386-ea33-11eb-863e-dc4a3ee3e76f','2021-06-16','F',8,270000,'Male','30-44',0,0,270000,7,'Not bad'),(3,3,'05b99a0c-ea33-11eb-863e-dc4a3ee3e76f','2021-06-20','F',NULL,139500,'Male','>60',0,0,155000,9,'Excellent'),(4,4,'08694138-ea33-11eb-863e-dc4a3ee3e76f','2021-06-21','F',8,205000,'Male','<18',0,0,205000,7,'Fantastic'),(5,5,'0ad054f5-ea33-11eb-863e-dc4a3ee3e76f','2021-06-22','F',8,165000,'Male','18-29',0,0,165000,7,'Very good'),(6,6,'0dcf22a5-ea33-11eb-863e-dc4a3ee3e76f','2021-06-23','F',8,200000,'Female','45-60',0,0,200000,7,'Bad'),(7,7,'107336a7-ea33-11eb-863e-dc4a3ee3e76f','2021-06-26','F',8,90000,'Female','45-60',0,0,90000,7,'Nice'),(8,8,'13c04f6b-ea33-11eb-863e-dc4a3ee3e76f','2021-06-28','F',8,170000,'Male','>60',0,0,170000,7,'Lovely'),(9,9,'16918ea9-ea33-11eb-863e-dc4a3ee3e76f','2021-06-29','F',NULL,155000,'Male','30-44',0,0,155000,9,'Stunning'),(10,10,'1907c6b8-ea33-11eb-863e-dc4a3ee3e76f','2021-06-30','F',8,230000,'Male','30-44',0,0,230000,7,'Gorgeous'),(11,9,'1e2bf6a7-ea33-11eb-863e-dc4a3ee3e76f','2021-07-02','F',NULL,240000,'Male','30-44',0,0,240000,6,'Terrific'),(12,7,'20d2e064-ea33-11eb-863e-dc4a3ee3e76f','2021-07-05','F',8,250000,'Female','45-60',0,0,250000,7,'Horrible'),(13,11,'2352c539-ea33-11eb-863e-dc4a3ee3e76f','2021-07-06','F',8,200000,'Female','18-29',0,0,200000,7,'Wonderful'),(14,12,'2618b431-ea33-11eb-863e-dc4a3ee3e76f','2021-07-07','F',8,140000,'Male','18-29',0,0,140000,7,'Perfect'),(15,13,'2ef02989-ea33-11eb-863e-dc4a3ee3e76f','2021-07-08','F',NULL,305000,'Male','>60',0,0,305000,10,'Breathtaking'),(16,2,'3c4aa402-ea33-11eb-863e-dc4a3ee3e76f','2021-07-09','F',8,280000,'Male','30-44',0,0,280000,7,'Outstanding'),(17,14,'4024ae88-ea33-11eb-863e-dc4a3ee3e76f','2021-07-10','F',8,180000,'Female','>60',0,0,180000,7,'Exceptional'),(18,6,'4360a888-ea33-11eb-863e-dc4a3ee3e76f','2021-07-12','F',NULL,380000,'Female','45-60',0,0,380000,12,'Amazing'),(19,15,'45d8e449-ea33-11eb-863e-dc4a3ee3e76f','2021-07-13','F',NULL,114000,'Female','30-44',0,0,114000,5,'Spectacular'),(20,8,'487e12e6-ea33-11eb-863e-dc4a3ee3e76f','2021-07-15','F',8,320000,'Male','>60',0,0,320000,7,'Phenomenal');
/*!40000 ALTER TABLE `sellorder` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-07-27 10:49:27
