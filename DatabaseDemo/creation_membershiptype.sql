-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: creation
-- ------------------------------------------------------
-- Server version	8.0.25

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `membershiptype`
--

DROP TABLE IF EXISTS membershiptype;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE membershiptype (
  MembershipID int NOT NULL AUTO_INCREMENT,
  Membership_UUID char(36) DEFAULT NULL,
  MembershipName varchar(40) DEFAULT NULL,
  UserID int DEFAULT NULL,
  DiscountID int DEFAULT NULL,
  MinimumSpend double DEFAULT NULL,
  PRIMARY KEY (MembershipID),
  KEY UserID8_fk_idx (UserID),
  KEY DiscountID2_fk_idx (DiscountID),
  CONSTRAINT DiscountID_fk FOREIGN KEY (DiscountID) REFERENCES discount (DiscountID) ON DELETE SET NULL,
  CONSTRAINT UserID_fk8 FOREIGN KEY (UserID) REFERENCES `user` (UserID) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `membershiptype`
--

INSERT INTO membershiptype VALUES (1,'7ec5ae98-ea33-11eb-863e-dc4a3ee3e76f','Davin Reinbold',5,1,240000),(2,'82ee6164-ea33-11eb-863e-dc4a3ee3e76f','Ham Ganley',9,NULL,139500),(3,'859bd3c8-ea33-11eb-863e-dc4a3ee3e76f','Emmalynne Youtead',15,8,580000),(4,'884cf2fa-ea33-11eb-863e-dc4a3ee3e76f','Cortie Van Leeuwen',8,NULL,395000),(5,'8ae34ee6-ea33-11eb-863e-dc4a3ee3e76f','Aidan McLice',13,NULL,305000),(6,'8d906d78-ea33-11eb-863e-dc4a3ee3e76f','Tallie Emanson',8,NULL,114000);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed
