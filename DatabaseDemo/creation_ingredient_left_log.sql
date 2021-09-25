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
-- Table structure for table `ingredient_left_log`
--

DROP TABLE IF EXISTS ingredient_left_log;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE ingredient_left_log (
  log_ingredient_left_id bigint NOT NULL AUTO_INCREMENT,
  log_ingredient_left_uuid char(36) DEFAULT NULL,
  staff_id bigint DEFAULT NULL,
  ingredient_id bigint DEFAULT NULL,
  user_id bigint DEFAULT NULL,
  creation_date bigint DEFAULT NULL,
  amount_left_change varchar(10) DEFAULT NULL,
  message varchar(150) DEFAULT NULL,
  PRIMARY KEY (log_ingredient_left_id),
  KEY StaffID_fk_idx (staff_id),
  KEY UserID6_fk_idx (user_id),
  CONSTRAINT StaffID_fk FOREIGN KEY (staff_id) REFERENCES staff (staff_id) ON DELETE SET NULL,
  CONSTRAINT UserID6_fk FOREIGN KEY (user_id) REFERENCES `user` (user_id) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingredient_left_log`
--

INSERT INTO ingredient_left_log (log_ingredient_left_id, log_ingredient_left_uuid, staff_id, ingredient_id, user_id, creation_date, amount_left_change, message) VALUES (1,'bc61a767-ea33-11eb-863e-dc4a3ee3e76f',3,2,3,20210615,'+4','More ingredient');
INSERT INTO ingredient_left_log (log_ingredient_left_id, log_ingredient_left_uuid, staff_id, ingredient_id, user_id, creation_date, amount_left_change, message) VALUES (2,'e6732b1e-ea33-11eb-863e-dc4a3ee3e76f',3,1,3,20210615,'+6','More ingredient');
INSERT INTO ingredient_left_log (log_ingredient_left_id, log_ingredient_left_uuid, staff_id, ingredient_id, user_id, creation_date, amount_left_change, message) VALUES (3,'e92ce4ef-ea33-11eb-863e-dc4a3ee3e76f',6,5,6,20210616,'+4','More ingredient');
INSERT INTO ingredient_left_log (log_ingredient_left_id, log_ingredient_left_uuid, staff_id, ingredient_id, user_id, creation_date, amount_left_change, message) VALUES (4,'ebdc3b10-ea33-11eb-863e-dc4a3ee3e76f',8,6,8,20210618,'+4','More ingredient');
INSERT INTO ingredient_left_log (log_ingredient_left_id, log_ingredient_left_uuid, staff_id, ingredient_id, user_id, creation_date, amount_left_change, message) VALUES (5,'eec4fbf1-ea33-11eb-863e-dc4a3ee3e76f',9,4,9,20210619,'+4','More ingredient');
INSERT INTO ingredient_left_log (log_ingredient_left_id, log_ingredient_left_uuid, staff_id, ingredient_id, user_id, creation_date, amount_left_change, message) VALUES (6,'f16c3a3a-ea33-11eb-863e-dc4a3ee3e76f',7,9,7,20210620,'+5','More ingredient');
INSERT INTO ingredient_left_log (log_ingredient_left_id, log_ingredient_left_uuid, staff_id, ingredient_id, user_id, creation_date, amount_left_change, message) VALUES (7,'f45bd8c0-ea33-11eb-863e-dc4a3ee3e76f',7,10,7,20210620,'+4','More ingredient');
INSERT INTO ingredient_left_log (log_ingredient_left_id, log_ingredient_left_uuid, staff_id, ingredient_id, user_id, creation_date, amount_left_change, message) VALUES (8,'fbb17d90-ea33-11eb-863e-dc4a3ee3e76f',5,3,5,20210621,'+6','More ingredient');
INSERT INTO ingredient_left_log (log_ingredient_left_id, log_ingredient_left_uuid, staff_id, ingredient_id, user_id, creation_date, amount_left_change, message) VALUES (9,'fea3bcc9-ea33-11eb-863e-dc4a3ee3e76f',8,3,8,20210622,'+5','More ingredient');
INSERT INTO ingredient_left_log (log_ingredient_left_id, log_ingredient_left_uuid, staff_id, ingredient_id, user_id, creation_date, amount_left_change, message) VALUES (10,'01203e7d-ea34-11eb-863e-dc4a3ee3e76f',10,12,10,20210623,'+6','More ingredient');
INSERT INTO ingredient_left_log (log_ingredient_left_id, log_ingredient_left_uuid, staff_id, ingredient_id, user_id, creation_date, amount_left_change, message) VALUES (11,'03baca23-ea34-11eb-863e-dc4a3ee3e76f',12,12,12,20210624,'+6','More ingredient');
INSERT INTO ingredient_left_log (log_ingredient_left_id, log_ingredient_left_uuid, staff_id, ingredient_id, user_id, creation_date, amount_left_change, message) VALUES (12,'0684913a-ea34-11eb-863e-dc4a3ee3e76f',9,11,9,20210625,'+6','More ingredient');
INSERT INTO ingredient_left_log (log_ingredient_left_id, log_ingredient_left_uuid, staff_id, ingredient_id, user_id, creation_date, amount_left_change, message) VALUES (13,'09f42c4d-ea34-11eb-863e-dc4a3ee3e76f',15,7,15,20210627,'+6','More ingredient');
INSERT INTO ingredient_left_log (log_ingredient_left_id, log_ingredient_left_uuid, staff_id, ingredient_id, user_id, creation_date, amount_left_change, message) VALUES (14,'10f4157d-ea34-11eb-863e-dc4a3ee3e76f',5,8,5,20210629,'+5','More ingredient');
INSERT INTO ingredient_left_log (log_ingredient_left_id, log_ingredient_left_uuid, staff_id, ingredient_id, user_id, creation_date, amount_left_change, message) VALUES (15,'1399c973-ea34-11eb-863e-dc4a3ee3e76f',5,7,5,20210629,'+4','More ingredient');
INSERT INTO ingredient_left_log (log_ingredient_left_id, log_ingredient_left_uuid, staff_id, ingredient_id, user_id, creation_date, amount_left_change, message) VALUES (16,'169c2509-ea34-11eb-863e-dc4a3ee3e76f',13,14,13,20210701,'+5','More ingredient');
INSERT INTO ingredient_left_log (log_ingredient_left_id, log_ingredient_left_uuid, staff_id, ingredient_id, user_id, creation_date, amount_left_change, message) VALUES (17,'19375d92-ea34-11eb-863e-dc4a3ee3e76f',6,13,6,20210702,'+6','More ingredient');
INSERT INTO ingredient_left_log (log_ingredient_left_id, log_ingredient_left_uuid, staff_id, ingredient_id, user_id, creation_date, amount_left_change, message) VALUES (18,'1bddc636-ea34-11eb-863e-dc4a3ee3e76f',14,6,14,20210705,'+4','More ingredient');
INSERT INTO ingredient_left_log (log_ingredient_left_id, log_ingredient_left_uuid, staff_id, ingredient_id, user_id, creation_date, amount_left_change, message) VALUES (19,'21ba7368-ea34-11eb-863e-dc4a3ee3e76f',4,8,4,20210706,'+7','More ingredient');
INSERT INTO ingredient_left_log (log_ingredient_left_id, log_ingredient_left_uuid, staff_id, ingredient_id, user_id, creation_date, amount_left_change, message) VALUES (20,'2449c9ec-ea34-11eb-863e-dc4a3ee3e76f',10,8,10,20210710,'+5','More ingredient');
INSERT INTO ingredient_left_log (log_ingredient_left_id, log_ingredient_left_uuid, staff_id, ingredient_id, user_id, creation_date, amount_left_change, message) VALUES (21,'274e9b3e-ea34-11eb-863e-dc4a3ee3e76f',9,15,9,20210712,'+4','More ingredient');
INSERT INTO ingredient_left_log (log_ingredient_left_id, log_ingredient_left_uuid, staff_id, ingredient_id, user_id, creation_date, amount_left_change, message) VALUES (22,'2a2603a3-ea34-11eb-863e-dc4a3ee3e76f',15,5,15,20210715,'+4','More ingredient');
INSERT INTO ingredient_left_log (log_ingredient_left_id, log_ingredient_left_uuid, staff_id, ingredient_id, user_id, creation_date, amount_left_change, message) VALUES (23,'2cc096c5-ea34-11eb-863e-dc4a3ee3e76f',15,4,15,20210715,'+6','More ingredient');
INSERT INTO ingredient_left_log (log_ingredient_left_id, log_ingredient_left_uuid, staff_id, ingredient_id, user_id, creation_date, amount_left_change, message) VALUES (24,'2ff9291f-ea34-11eb-863e-dc4a3ee3e76f',4,6,4,20210717,'+4','More ingredient');
INSERT INTO ingredient_left_log (log_ingredient_left_id, log_ingredient_left_uuid, staff_id, ingredient_id, user_id, creation_date, amount_left_change, message) VALUES (25,'32648ee7-ea34-11eb-863e-dc4a3ee3e76f',4,9,4,20210717,'+5','More ingredient');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed
