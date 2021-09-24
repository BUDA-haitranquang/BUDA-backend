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
-- Table structure for table `discount`
--

DROP TABLE IF EXISTS discount;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE discount (
  DiscountID int NOT NULL AUTO_INCREMENT,
  Discount_UUID char(36) DEFAULT NULL,
  `Name` varchar(50) DEFAULT NULL,
  UserID int DEFAULT NULL,
  Percentage double DEFAULT NULL,
  Cash int DEFAULT NULL,
  CashLimit int DEFAULT NULL,
  CreatedTime date DEFAULT NULL,
  ExpiryTime date DEFAULT NULL,
  OrdersCount int DEFAULT NULL,
  DiscountType char(1) DEFAULT NULL,
  `Description` varchar(30) DEFAULT NULL,
  PRIMARY KEY (DiscountID),
  KEY UserID_fk2_idx (UserID),
  CONSTRAINT UserID_fk2 FOREIGN KEY (UserID) REFERENCES `user` (UserID) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discount`
--

INSERT INTO discount (DiscountID, Discount_UUID, Name, UserID, Percentage, Cash, CashLimit, CreatedTime, ExpiryTime, OrdersCount, DiscountType, Description) VALUES (1,'1494c856-ea99-11eb-863e-dc4a3ee3e76f','First',5,0.1,NULL,20000,'2021-06-15','2021-06-20',1,'1','10% Discount, Max 20000');
INSERT INTO discount (DiscountID, Discount_UUID, Name, UserID, Percentage, Cash, CashLimit, CreatedTime, ExpiryTime, OrdersCount, DiscountType, Description) VALUES (2,'18633187-ea99-11eb-863e-dc4a3ee3e76f','Second',9,0.1,NULL,30000,'2021-06-21','2021-06-23',0,'1','10% Discount, Max 30000');
INSERT INTO discount (DiscountID, Discount_UUID, Name, UserID, Percentage, Cash, CashLimit, CreatedTime, ExpiryTime, OrdersCount, DiscountType, Description) VALUES (3,'1b7c7af1-ea99-11eb-863e-dc4a3ee3e76f','Third',12,0.05,NULL,50000,'2021-06-25','2021-06-28',0,'1','5% Discount, Max 50000');
INSERT INTO discount (DiscountID, Discount_UUID, Name, UserID, Percentage, Cash, CashLimit, CreatedTime, ExpiryTime, OrdersCount, DiscountType, Description) VALUES (4,'1f86b19d-ea99-11eb-863e-dc4a3ee3e76f','Fourth',15,0.1,NULL,40000,'2021-06-29','2021-07-02',0,'1','10% Discount, Max 40000');
INSERT INTO discount (DiscountID, Discount_UUID, Name, UserID, Percentage, Cash, CashLimit, CreatedTime, ExpiryTime, OrdersCount, DiscountType, Description) VALUES (5,'229d682b-ea99-11eb-863e-dc4a3ee3e76f','Fifth',8,0.15,NULL,20000,'2021-07-04','2021-07-08',0,'1','15% Discount, Max 20000');
INSERT INTO discount (DiscountID, Discount_UUID, Name, UserID, Percentage, Cash, CashLimit, CreatedTime, ExpiryTime, OrdersCount, DiscountType, Description) VALUES (6,'25edb23f-ea99-11eb-863e-dc4a3ee3e76f','Sixth',10,0.05,NULL,60000,'2021-07-10','2021-07-14',0,'1','5% Discount, Max 60000');
INSERT INTO discount (DiscountID, Discount_UUID, Name, UserID, Percentage, Cash, CashLimit, CreatedTime, ExpiryTime, OrdersCount, DiscountType, Description) VALUES (7,'28c30a4f-ea99-11eb-863e-dc4a3ee3e76f','Seventh',6,0.25,NULL,30000,'2021-07-15','2021-07-21',0,'1','25% Discount, Max 30000');
INSERT INTO discount (DiscountID, Discount_UUID, Name, UserID, Percentage, Cash, CashLimit, CreatedTime, ExpiryTime, OrdersCount, DiscountType, Description) VALUES (8,'9b33b052-ea99-11eb-863e-dc4a3ee3e76f','Eigth',7,0,NULL,NULL,'2021-06-15','9999-12-31',NULL,NULL,'No discount');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed
