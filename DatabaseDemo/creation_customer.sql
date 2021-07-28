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
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `CustomerID` int NOT NULL,
  `Customer_UUID` char(36) DEFAULT NULL,
  `Name` char(40) DEFAULT NULL,
  `PhoneNumber` char(10) DEFAULT NULL,
  `Address` varchar(100) DEFAULT NULL,
  `AgeGroup` enum('<18','18-29','30-44','45-60','>60') DEFAULT NULL,
  `Gender` enum('Male','Female') DEFAULT NULL,
  `TotalSpend` double DEFAULT NULL,
  `MembershipID` int DEFAULT NULL,
  PRIMARY KEY (`CustomerID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'7f39b76a-ea05-11eb-863e-dc4a3ee3e76f','Davin Renbold','4735520113','21392 Browning Crossing','<18','Female',240000,1),(2,'83b39e6e-ea05-11eb-863e-dc4a3ee3e76f','Ninon Giacobazzi','6382020283','67571 Dayton Terrace','30-44','Male',550000,NULL),(3,'8771f0bf-ea05-11eb-863e-dc4a3ee3e76f','Ham Ganley','6837961494','36693 Trailsway Circle','>60','Male',139500,2),(4,'8b50f539-ea05-11eb-863e-dc4a3ee3e76f','Edythe Glassman','3325356066','100 Dahle Park','<18','Male',205000,NULL),(5,'8f13b9d7-ea05-11eb-863e-dc4a3ee3e76f','Agnella O\'Lynn','1538221336','2 Fieldstone Point','18-29','Male',165000,NULL),(6,'927939a2-ea05-11eb-863e-dc4a3ee3e76f','Emmalynne Youtead','6941515680','6 Petterle Place','45-60','Female',580000,3),(7,'955f4b30-ea05-11eb-863e-dc4a3ee3e76f','Brantley Crenage','7167131346','178 Esch Place','45-60','Female',340000,NULL),(8,'9ca13ee2-ea05-11eb-863e-dc4a3ee3e76f','Emlynn Flintoffe','8276504791','4 Hoard Street','>60','Male',490000,NULL),(9,'9f9fc6fb-ea05-11eb-863e-dc4a3ee3e76f','Cortie Van Leeuwen','5859686981','72 Eastwood Parkway','30-44','Male',395000,4),(10,'a2b8eff5-ea05-11eb-863e-dc4a3ee3e76f','Berk Eagland','3934488094','9 Summerview Parkway','30-44','Male',230000,NULL),(11,'a67b202e-ea05-11eb-863e-dc4a3ee3e76f','Bonni Hales','1805366420','5 Londonderry Street','18-29','Female',200000,NULL),(12,'aa5a5d1b-ea05-11eb-863e-dc4a3ee3e76f','Gunter Cansdall','4516744519','01 Oak Road','18-29','Male',140000,NULL),(13,'acd56e45-ea05-11eb-863e-dc4a3ee3e76f','Aidan McLice','2758886993','7 Petterle Drive','>60','Male',305000,5),(14,'b3460a58-ea05-11eb-863e-dc4a3ee3e76f','Pam Dibbin','9556387885','5371 American Avenue','>60','Female',180000,NULL),(15,'b6496441-ea05-11eb-863e-dc4a3ee3e76f','Tallie Emanson','1819815688','02 Talmadge Court','30-44','Female',114000,6);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
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