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
-- Table structure for table `othercost`
--

DROP TABLE IF EXISTS othercost;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE othercost (
  OtherCostID int NOT NULL AUTO_INCREMENT,
  CreationTime date DEFAULT NULL,
  `Description` varchar(2000) DEFAULT NULL,
  `Name` varchar(200) DEFAULT NULL,
  Totalcost double DEFAULT NULL,
  UserID int DEFAULT NULL,
  PRIMARY KEY (OtherCostID),
  KEY UserID1_fk_idx (UserID),
  CONSTRAINT UserID1_fk FOREIGN KEY (UserID) REFERENCES `user` (UserID) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `othercost`
--

INSERT INTO othercost (OtherCostID, CreationTime, Description, Name, Totalcost, UserID) VALUES (1,'2021-06-25','Testing','VAT',30000,3);
INSERT INTO othercost (OtherCostID, CreationTime, Description, Name, Totalcost, UserID) VALUES (2,'2021-06-28','Testing','Rent',500000,5);
INSERT INTO othercost (OtherCostID, CreationTime, Description, Name, Totalcost, UserID) VALUES (3,'2021-06-30','Testing','Repair',100000,8);
INSERT INTO othercost (OtherCostID, CreationTime, Description, Name, Totalcost, UserID) VALUES (4,'2021-07-03','Testing','Fuel',120000,9);
INSERT INTO othercost (OtherCostID, CreationTime, Description, Name, Totalcost, UserID) VALUES (5,'2021-07-08','Testing','Insurance',200000,12);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed
