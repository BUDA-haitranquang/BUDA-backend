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
-- Table structure for table `productleftlog`
--

DROP TABLE IF EXISTS productleftlog;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE productleftlog (
  LogProductLeftID int NOT NULL AUTO_INCREMENT,
  LogProductLeft_UUID char(36) DEFAULT NULL,
  StaffID int DEFAULT NULL,
  ProductID int DEFAULT NULL,
  UserID int DEFAULT NULL,
  CreationDate date DEFAULT NULL,
  AmountLeftChange varchar(20) DEFAULT NULL,
  Message varchar(150) DEFAULT NULL,
  PRIMARY KEY (LogProductLeftID),
  KEY StaffID_fk2_idx (StaffID),
  KEY ProductID_fk2_idx (ProductID),
  KEY UserID_fk7_idx (UserID),
  CONSTRAINT ProductID_fk2 FOREIGN KEY (ProductID) REFERENCES product (ProductID) ON DELETE SET NULL,
  CONSTRAINT StaffID_fk2 FOREIGN KEY (StaffID) REFERENCES staff (StaffID) ON DELETE SET NULL,
  CONSTRAINT UserID_fk7 FOREIGN KEY (UserID) REFERENCES `user` (UserID) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productleftlog`
--

INSERT INTO productleftlog (LogProductLeftID, LogProductLeft_UUID, StaffID, ProductID, UserID, CreationDate, AmountLeftChange, Message) VALUES (1,'242fbe06-ea31-11eb-863e-dc4a3ee3e76f',5,1,5,'2021-06-15','-1','New order');
INSERT INTO productleftlog (LogProductLeftID, LogProductLeft_UUID, StaffID, ProductID, UserID, CreationDate, AmountLeftChange, Message) VALUES (2,'4a594be6-ea31-11eb-863e-dc4a3ee3e76f',5,9,5,'2021-06-15','-1','New order');
INSERT INTO productleftlog (LogProductLeftID, LogProductLeft_UUID, StaffID, ProductID, UserID, CreationDate, AmountLeftChange, Message) VALUES (3,'4dc3005c-ea31-11eb-863e-dc4a3ee3e76f',8,3,8,'2021-06-16','-2','New order');
INSERT INTO productleftlog (LogProductLeftID, LogProductLeft_UUID, StaffID, ProductID, UserID, CreationDate, AmountLeftChange, Message) VALUES (4,'50e5a153-ea31-11eb-863e-dc4a3ee3e76f',9,2,9,'2021-06-20','-1','New order');
INSERT INTO productleftlog (LogProductLeftID, LogProductLeft_UUID, StaffID, ProductID, UserID, CreationDate, AmountLeftChange, Message) VALUES (5,'56d55fcb-ea31-11eb-863e-dc4a3ee3e76f',9,13,9,'2021-06-20','-1','New order');
INSERT INTO productleftlog (LogProductLeftID, LogProductLeft_UUID, StaffID, ProductID, UserID, CreationDate, AmountLeftChange, Message) VALUES (6,'5cac0196-ea31-11eb-863e-dc4a3ee3e76f',10,12,10,'2021-06-21','-1','New order');
INSERT INTO productleftlog (LogProductLeftID, LogProductLeft_UUID, StaffID, ProductID, UserID, CreationDate, AmountLeftChange, Message) VALUES (7,'60891bc9-ea31-11eb-863e-dc4a3ee3e76f',12,15,12,'2021-06-22','-1','New order');
INSERT INTO productleftlog (LogProductLeftID, LogProductLeft_UUID, StaffID, ProductID, UserID, CreationDate, AmountLeftChange, Message) VALUES (8,'646cf85a-ea31-11eb-863e-dc4a3ee3e76f',12,13,12,'2021-06-22','-1','New order');
INSERT INTO productleftlog (LogProductLeftID, LogProductLeft_UUID, StaffID, ProductID, UserID, CreationDate, AmountLeftChange, Message) VALUES (9,'671ef84e-ea31-11eb-863e-dc4a3ee3e76f',14,5,14,'2021-06-23','-2','New order');
INSERT INTO productleftlog (LogProductLeftID, LogProductLeft_UUID, StaffID, ProductID, UserID, CreationDate, AmountLeftChange, Message) VALUES (10,'69eca90e-ea31-11eb-863e-dc4a3ee3e76f',3,10,3,'2021-06-26','-1','New order');
INSERT INTO productleftlog (LogProductLeftID, LogProductLeft_UUID, StaffID, ProductID, UserID, CreationDate, AmountLeftChange, Message) VALUES (11,'6cdea3ff-ea31-11eb-863e-dc4a3ee3e76f',3,6,3,'2021-06-26','-1','New order');
INSERT INTO productleftlog (LogProductLeftID, LogProductLeft_UUID, StaffID, ProductID, UserID, CreationDate, AmountLeftChange, Message) VALUES (12,'72e7bd56-ea31-11eb-863e-dc4a3ee3e76f',8,8,8,'2021-06-28','-2','New order');
INSERT INTO productleftlog (LogProductLeftID, LogProductLeft_UUID, StaffID, ProductID, UserID, CreationDate, AmountLeftChange, Message) VALUES (13,'7630703c-ea31-11eb-863e-dc4a3ee3e76f',9,11,9,'2021-06-29','-1','New order');
INSERT INTO productleftlog (LogProductLeftID, LogProductLeft_UUID, StaffID, ProductID, UserID, CreationDate, AmountLeftChange, Message) VALUES (14,'7a7236f9-ea31-11eb-863e-dc4a3ee3e76f',9,14,9,'2021-06-29','-1','New order');
INSERT INTO productleftlog (LogProductLeftID, LogProductLeft_UUID, StaffID, ProductID, UserID, CreationDate, AmountLeftChange, Message) VALUES (15,'90e63595-ea31-11eb-863e-dc4a3ee3e76f',7,2,7,'2021-06-30','-2','New order');
INSERT INTO productleftlog (LogProductLeftID, LogProductLeft_UUID, StaffID, ProductID, UserID, CreationDate, AmountLeftChange, Message) VALUES (16,'9f09a08f-ea31-11eb-863e-dc4a3ee3e76f',6,4,6,'2021-07-02','-1','New order');
INSERT INTO productleftlog (LogProductLeftID, LogProductLeft_UUID, StaffID, ProductID, UserID, CreationDate, AmountLeftChange, Message) VALUES (17,'a404c80a-ea31-11eb-863e-dc4a3ee3e76f',6,6,6,'2021-07-02','-1','New order');
INSERT INTO productleftlog (LogProductLeftID, LogProductLeft_UUID, StaffID, ProductID, UserID, CreationDate, AmountLeftChange, Message) VALUES (18,'adf02d9a-ea31-11eb-863e-dc4a3ee3e76f',4,15,4,'2021-07-05','-2','New order');
INSERT INTO productleftlog (LogProductLeftID, LogProductLeft_UUID, StaffID, ProductID, UserID, CreationDate, AmountLeftChange, Message) VALUES (19,'b2bb18da-ea31-11eb-863e-dc4a3ee3e76f',15,10,15,'2021-07-06','-1','New order');
INSERT INTO productleftlog (LogProductLeftID, LogProductLeft_UUID, StaffID, ProductID, UserID, CreationDate, AmountLeftChange, Message) VALUES (20,'b58191fc-ea31-11eb-863e-dc4a3ee3e76f',15,11,15,'2021-07-06','-1','New order');
INSERT INTO productleftlog (LogProductLeftID, LogProductLeft_UUID, StaffID, ProductID, UserID, CreationDate, AmountLeftChange, Message) VALUES (21,'b7f11d37-ea31-11eb-863e-dc4a3ee3e76f',13,9,13,'2021-07-07','-2','New order');
INSERT INTO productleftlog (LogProductLeftID, LogProductLeft_UUID, StaffID, ProductID, UserID, CreationDate, AmountLeftChange, Message) VALUES (22,'baeb630f-ea31-11eb-863e-dc4a3ee3e76f',11,1,11,'2021-07-08','-1','New order');
INSERT INTO productleftlog (LogProductLeftID, LogProductLeft_UUID, StaffID, ProductID, UserID, CreationDate, AmountLeftChange, Message) VALUES (23,'bdd5875a-ea31-11eb-863e-dc4a3ee3e76f',11,2,11,'2021-07-08','-1','New order');
INSERT INTO productleftlog (LogProductLeftID, LogProductLeft_UUID, StaffID, ProductID, UserID, CreationDate, AmountLeftChange, Message) VALUES (24,'c180cae4-ea31-11eb-863e-dc4a3ee3e76f',6,7,6,'2021-07-09','-2','New order');
INSERT INTO productleftlog (LogProductLeftID, LogProductLeft_UUID, StaffID, ProductID, UserID, CreationDate, AmountLeftChange, Message) VALUES (25,'c4c6140f-ea31-11eb-863e-dc4a3ee3e76f',10,5,10,'2021-07-10','-1','New order');
INSERT INTO productleftlog (LogProductLeftID, LogProductLeft_UUID, StaffID, ProductID, UserID, CreationDate, AmountLeftChange, Message) VALUES (26,'c7c8aee8-ea31-11eb-863e-dc4a3ee3e76f',10,6,10,'2021-07-10','-1','New order');
INSERT INTO productleftlog (LogProductLeftID, LogProductLeft_UUID, StaffID, ProductID, UserID, CreationDate, AmountLeftChange, Message) VALUES (27,'cbac0bcd-ea31-11eb-863e-dc4a3ee3e76f',12,1,12,'2021-07-12','-2','New order');
INSERT INTO productleftlog (LogProductLeftID, LogProductLeft_UUID, StaffID, ProductID, UserID, CreationDate, AmountLeftChange, Message) VALUES (28,'ceafa112-ea31-11eb-863e-dc4a3ee3e76f',14,8,14,'2021-07-13','-1','New order');
INSERT INTO productleftlog (LogProductLeftID, LogProductLeft_UUID, StaffID, ProductID, UserID, CreationDate, AmountLeftChange, Message) VALUES (29,'d1d152df-ea31-11eb-863e-dc4a3ee3e76f',14,13,14,'2021-07-15','-1','New order');
INSERT INTO productleftlog (LogProductLeftID, LogProductLeft_UUID, StaffID, ProductID, UserID, CreationDate, AmountLeftChange, Message) VALUES (30,'d4ec767a-ea31-11eb-863e-dc4a3ee3e76f',8,4,8,'2021-07-15','-2','New order');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed
