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
-- Table structure for table `fixedcostbill`
--

DROP TABLE IF EXISTS fixedcostbill;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE fixedcostbill (
  BillID int NOT NULL AUTO_INCREMENT,
  FixedCostID int DEFAULT NULL,
  Bill_UUID char(36) DEFAULT NULL,
  CreationDate date DEFAULT NULL,
  DueDate date DEFAULT NULL,
  TotalSpend double DEFAULT NULL,
  `Status` char(1) DEFAULT NULL,
  UserID int DEFAULT NULL,
  PRIMARY KEY (BillID),
  KEY UserID_fk4_idx (UserID),
  KEY FixID_fk_idx (FixedCostID),
  CONSTRAINT FixID_fk FOREIGN KEY (FixedCostID) REFERENCES fixedcost (FixedCostID) ON DELETE SET NULL,
  CONSTRAINT UserID_fk4 FOREIGN KEY (UserID) REFERENCES `user` (UserID) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fixedcostbill`
--

INSERT INTO fixedcostbill (BillID, FixedCostID, Bill_UUID, CreationDate, DueDate, TotalSpend, Status, UserID) VALUES (1,3,'52a047d8-ee85-11eb-ae1e-dc4a3ee3e76f','2021-06-15','2021-07-15',350000,'F',4);
INSERT INTO fixedcostbill (BillID, FixedCostID, Bill_UUID, CreationDate, DueDate, TotalSpend, Status, UserID) VALUES (2,4,'5699294b-ee85-11eb-ae1e-dc4a3ee3e76f','2021-06-15','2021-07-15',600000,'F',6);
INSERT INTO fixedcostbill (BillID, FixedCostID, Bill_UUID, CreationDate, DueDate, TotalSpend, Status, UserID) VALUES (3,1,'5ab7142f-ee85-11eb-ae1e-dc4a3ee3e76f','2021-06-15','2021-07-15',200000,'F',7);
INSERT INTO fixedcostbill (BillID, FixedCostID, Bill_UUID, CreationDate, DueDate, TotalSpend, Status, UserID) VALUES (4,2,'5d9cc08d-ee85-11eb-ae1e-dc4a3ee3e76f','2021-06-20','2021-07-20',300000,'F',9);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed
