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
-- Table structure for table `purchase`
--

DROP TABLE IF EXISTS purchase;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE purchase (
  purchase_id bigint NOT NULL AUTO_INCREMENT,
  purchase_uuid char(36) DEFAULT NULL,
  user_id bigint DEFAULT NULL,
  plan_id bigint DEFAULT NULL,
  creation_date date DEFAULT NULL,
  expiry_date date DEFAULT NULL,
  message varchar(150) DEFAULT NULL,
  PRIMARY KEY (purchase_id),
  KEY PlanID_fk_idx (plan_id),
  KEY UserID12_fk_idx (user_id),
  CONSTRAINT PlanID_fk FOREIGN KEY (plan_id) REFERENCES plan (plan_id) ON DELETE SET NULL,
  CONSTRAINT UserID12_fk FOREIGN KEY (user_id) REFERENCES `user` (user_id) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase`
--

INSERT INTO purchase (purchase_id, purchase_uuid, user_id, plan_id, creation_date, expiry_date, message) VALUES (1,'79d2c2e7-ee8b-11eb-b72b-dc4a3ee3e76f',8,1,'2021-06-15','2021-08-15','New purchase');
INSERT INTO purchase (purchase_id, purchase_uuid, user_id, plan_id, creation_date, expiry_date, message) VALUES (2,'7f2296e0-ee8b-11eb-b72b-dc4a3ee3e76f',14,3,'2021-06-16','2021-07-16','New purchase');
INSERT INTO purchase (purchase_id, purchase_uuid, user_id, plan_id, creation_date, expiry_date, message) VALUES (3,'824094b5-ee8b-11eb-b72b-dc4a3ee3e76f',15,3,'2021-06-18','2021-07-18','New purchase');
INSERT INTO purchase (purchase_id, purchase_uuid, user_id, plan_id, creation_date, expiry_date, message) VALUES (4,'856cccd9-ee8b-11eb-b72b-dc4a3ee3e76f',10,4,'2021-06-20','2021-12-20','New purchase');
INSERT INTO purchase (purchase_id, purchase_uuid, user_id, plan_id, creation_date, expiry_date, message) VALUES (5,'888eca7f-ee8b-11eb-b72b-dc4a3ee3e76f',7,2,'2021-06-21','2021-09-21','New purchase');
INSERT INTO purchase (purchase_id, purchase_uuid, user_id, plan_id, creation_date, expiry_date, message) VALUES (6,'8ba5d0d4-ee8b-11eb-b72b-dc4a3ee3e76f',5,1,'2021-06-22','2021-08-22','New purchase');
INSERT INTO purchase (purchase_id, purchase_uuid, user_id, plan_id, creation_date, expiry_date, message) VALUES (7,'8ecc7762-ee8b-11eb-b72b-dc4a3ee3e76f',9,4,'2021-06-25','2021-12-25','New purchase');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed
