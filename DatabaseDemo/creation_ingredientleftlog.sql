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
-- Table structure for table `ingredientleftlog`
--

DROP TABLE IF EXISTS ingredientleftlog;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE ingredientleftlog (
  LogIngredientLeftID int NOT NULL AUTO_INCREMENT,
  LogIngredientLeft_UUID char(36) DEFAULT NULL,
  StaffID int DEFAULT NULL,
  IngredientID int DEFAULT NULL,
  UserID int DEFAULT NULL,
  CreationDate date DEFAULT NULL,
  AmountLeftChange varchar(10) DEFAULT NULL,
  Message varchar(150) DEFAULT NULL,
  PRIMARY KEY (LogIngredientLeftID),
  KEY EmployeeID_fk2_idx (StaffID),
  KEY IngredientID_fk1_idx (IngredientID),
  KEY UserID_fk6_idx (UserID),
  CONSTRAINT IngredientID_fk1 FOREIGN KEY (IngredientID) REFERENCES ingredient (IngredientID) ON DELETE SET NULL,
  CONSTRAINT StaffID_fk1 FOREIGN KEY (StaffID) REFERENCES staff (StaffID) ON DELETE SET NULL,
  CONSTRAINT UserID_fk6 FOREIGN KEY (UserID) REFERENCES `user` (UserID) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingredientleftlog`
--

INSERT INTO ingredientleftlog (LogIngredientLeftID, LogIngredientLeft_UUID, StaffID, IngredientID, UserID, CreationDate, AmountLeftChange, Message) VALUES (1,'bc61a767-ea33-11eb-863e-dc4a3ee3e76f',3,2,3,'2021-06-15','+4','More ingredient');
INSERT INTO ingredientleftlog (LogIngredientLeftID, LogIngredientLeft_UUID, StaffID, IngredientID, UserID, CreationDate, AmountLeftChange, Message) VALUES (2,'e6732b1e-ea33-11eb-863e-dc4a3ee3e76f',3,1,3,'2021-06-15','+6','More ingredient');
INSERT INTO ingredientleftlog (LogIngredientLeftID, LogIngredientLeft_UUID, StaffID, IngredientID, UserID, CreationDate, AmountLeftChange, Message) VALUES (3,'e92ce4ef-ea33-11eb-863e-dc4a3ee3e76f',6,5,6,'2021-06-16','+4','More ingredient');
INSERT INTO ingredientleftlog (LogIngredientLeftID, LogIngredientLeft_UUID, StaffID, IngredientID, UserID, CreationDate, AmountLeftChange, Message) VALUES (4,'ebdc3b10-ea33-11eb-863e-dc4a3ee3e76f',8,6,8,'2021-06-18','+4','More ingredient');
INSERT INTO ingredientleftlog (LogIngredientLeftID, LogIngredientLeft_UUID, StaffID, IngredientID, UserID, CreationDate, AmountLeftChange, Message) VALUES (5,'eec4fbf1-ea33-11eb-863e-dc4a3ee3e76f',9,4,9,'2021-06-19','+4','More ingredient');
INSERT INTO ingredientleftlog (LogIngredientLeftID, LogIngredientLeft_UUID, StaffID, IngredientID, UserID, CreationDate, AmountLeftChange, Message) VALUES (6,'f16c3a3a-ea33-11eb-863e-dc4a3ee3e76f',7,9,7,'2021-06-20','+5','More ingredient');
INSERT INTO ingredientleftlog (LogIngredientLeftID, LogIngredientLeft_UUID, StaffID, IngredientID, UserID, CreationDate, AmountLeftChange, Message) VALUES (7,'f45bd8c0-ea33-11eb-863e-dc4a3ee3e76f',7,10,7,'2021-06-20','+4','More ingredient');
INSERT INTO ingredientleftlog (LogIngredientLeftID, LogIngredientLeft_UUID, StaffID, IngredientID, UserID, CreationDate, AmountLeftChange, Message) VALUES (8,'fbb17d90-ea33-11eb-863e-dc4a3ee3e76f',5,3,5,'2021-06-21','+6','More ingredient');
INSERT INTO ingredientleftlog (LogIngredientLeftID, LogIngredientLeft_UUID, StaffID, IngredientID, UserID, CreationDate, AmountLeftChange, Message) VALUES (9,'fea3bcc9-ea33-11eb-863e-dc4a3ee3e76f',8,3,8,'2021-06-22','+5','More ingredient');
INSERT INTO ingredientleftlog (LogIngredientLeftID, LogIngredientLeft_UUID, StaffID, IngredientID, UserID, CreationDate, AmountLeftChange, Message) VALUES (10,'01203e7d-ea34-11eb-863e-dc4a3ee3e76f',10,12,10,'2021-06-23','+6','More ingredient');
INSERT INTO ingredientleftlog (LogIngredientLeftID, LogIngredientLeft_UUID, StaffID, IngredientID, UserID, CreationDate, AmountLeftChange, Message) VALUES (11,'03baca23-ea34-11eb-863e-dc4a3ee3e76f',12,12,12,'2021-06-24','+6','More ingredient');
INSERT INTO ingredientleftlog (LogIngredientLeftID, LogIngredientLeft_UUID, StaffID, IngredientID, UserID, CreationDate, AmountLeftChange, Message) VALUES (12,'0684913a-ea34-11eb-863e-dc4a3ee3e76f',9,11,9,'2021-06-25','+6','More ingredient');
INSERT INTO ingredientleftlog (LogIngredientLeftID, LogIngredientLeft_UUID, StaffID, IngredientID, UserID, CreationDate, AmountLeftChange, Message) VALUES (13,'09f42c4d-ea34-11eb-863e-dc4a3ee3e76f',15,7,15,'2021-06-27','+6','More ingredient');
INSERT INTO ingredientleftlog (LogIngredientLeftID, LogIngredientLeft_UUID, StaffID, IngredientID, UserID, CreationDate, AmountLeftChange, Message) VALUES (14,'10f4157d-ea34-11eb-863e-dc4a3ee3e76f',5,8,5,'2021-06-29','+5','More ingredient');
INSERT INTO ingredientleftlog (LogIngredientLeftID, LogIngredientLeft_UUID, StaffID, IngredientID, UserID, CreationDate, AmountLeftChange, Message) VALUES (15,'1399c973-ea34-11eb-863e-dc4a3ee3e76f',5,7,5,'2021-06-29','+4','More ingredient');
INSERT INTO ingredientleftlog (LogIngredientLeftID, LogIngredientLeft_UUID, StaffID, IngredientID, UserID, CreationDate, AmountLeftChange, Message) VALUES (16,'169c2509-ea34-11eb-863e-dc4a3ee3e76f',13,14,13,'2021-07-01','+5','More ingredient');
INSERT INTO ingredientleftlog (LogIngredientLeftID, LogIngredientLeft_UUID, StaffID, IngredientID, UserID, CreationDate, AmountLeftChange, Message) VALUES (17,'19375d92-ea34-11eb-863e-dc4a3ee3e76f',6,13,6,'2021-07-02','+6','More ingredient');
INSERT INTO ingredientleftlog (LogIngredientLeftID, LogIngredientLeft_UUID, StaffID, IngredientID, UserID, CreationDate, AmountLeftChange, Message) VALUES (18,'1bddc636-ea34-11eb-863e-dc4a3ee3e76f',14,6,14,'2021-07-05','+4','More ingredient');
INSERT INTO ingredientleftlog (LogIngredientLeftID, LogIngredientLeft_UUID, StaffID, IngredientID, UserID, CreationDate, AmountLeftChange, Message) VALUES (19,'21ba7368-ea34-11eb-863e-dc4a3ee3e76f',4,8,4,'2021-07-06','+7','More ingredient');
INSERT INTO ingredientleftlog (LogIngredientLeftID, LogIngredientLeft_UUID, StaffID, IngredientID, UserID, CreationDate, AmountLeftChange, Message) VALUES (20,'2449c9ec-ea34-11eb-863e-dc4a3ee3e76f',10,8,10,'2021-07-10','+5','More ingredient');
INSERT INTO ingredientleftlog (LogIngredientLeftID, LogIngredientLeft_UUID, StaffID, IngredientID, UserID, CreationDate, AmountLeftChange, Message) VALUES (21,'274e9b3e-ea34-11eb-863e-dc4a3ee3e76f',9,15,9,'2021-07-12','+4','More ingredient');
INSERT INTO ingredientleftlog (LogIngredientLeftID, LogIngredientLeft_UUID, StaffID, IngredientID, UserID, CreationDate, AmountLeftChange, Message) VALUES (22,'2a2603a3-ea34-11eb-863e-dc4a3ee3e76f',15,5,15,'2021-07-15','+4','More ingredient');
INSERT INTO ingredientleftlog (LogIngredientLeftID, LogIngredientLeft_UUID, StaffID, IngredientID, UserID, CreationDate, AmountLeftChange, Message) VALUES (23,'2cc096c5-ea34-11eb-863e-dc4a3ee3e76f',15,4,15,'2021-07-15','+6','More ingredient');
INSERT INTO ingredientleftlog (LogIngredientLeftID, LogIngredientLeft_UUID, StaffID, IngredientID, UserID, CreationDate, AmountLeftChange, Message) VALUES (24,'2ff9291f-ea34-11eb-863e-dc4a3ee3e76f',4,6,4,'2021-07-17','+4','More ingredient');
INSERT INTO ingredientleftlog (LogIngredientLeftID, LogIngredientLeft_UUID, StaffID, IngredientID, UserID, CreationDate, AmountLeftChange, Message) VALUES (25,'32648ee7-ea34-11eb-863e-dc4a3ee3e76f',4,9,4,'2021-07-17','+5','More ingredient');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed
