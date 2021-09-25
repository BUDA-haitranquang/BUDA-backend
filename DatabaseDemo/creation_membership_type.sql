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
-- Table structure for table `membership_type`
--

DROP TABLE IF EXISTS membership_type;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE membership_type (
  membership_id bigint NOT NULL AUTO_INCREMENT,
  membership_uuid char(36) DEFAULT NULL,
  membership_name varchar(40) DEFAULT NULL,
  user_id bigint DEFAULT NULL,
  discount_id bigint DEFAULT NULL,
  minimum_spend double DEFAULT NULL,
  PRIMARY KEY (membership_id),
  KEY DiscountID_fk_idx (discount_id),
  KEY UserID7_fk_idx (user_id),
  CONSTRAINT DiscountID_fk FOREIGN KEY (discount_id) REFERENCES discount (discount_id) ON DELETE SET NULL,
  CONSTRAINT UserID7_fk FOREIGN KEY (user_id) REFERENCES `user` (user_id) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `membership_type`
--

INSERT INTO membership_type (membership_id, membership_uuid, membership_name, user_id, discount_id, minimum_spend) VALUES (1,'7ec5ae98-ea33-11eb-863e-dc4a3ee3e76f','Davin Reinbold',5,1,240000);
INSERT INTO membership_type (membership_id, membership_uuid, membership_name, user_id, discount_id, minimum_spend) VALUES (2,'82ee6164-ea33-11eb-863e-dc4a3ee3e76f','Ham Ganley',9,NULL,139500);
INSERT INTO membership_type (membership_id, membership_uuid, membership_name, user_id, discount_id, minimum_spend) VALUES (3,'859bd3c8-ea33-11eb-863e-dc4a3ee3e76f','Emmalynne Youtead',15,8,580000);
INSERT INTO membership_type (membership_id, membership_uuid, membership_name, user_id, discount_id, minimum_spend) VALUES (4,'884cf2fa-ea33-11eb-863e-dc4a3ee3e76f','Cortie Van Leeuwen',8,NULL,395000);
INSERT INTO membership_type (membership_id, membership_uuid, membership_name, user_id, discount_id, minimum_spend) VALUES (5,'8ae34ee6-ea33-11eb-863e-dc4a3ee3e76f','Aidan McLice',13,NULL,305000);
INSERT INTO membership_type (membership_id, membership_uuid, membership_name, user_id, discount_id, minimum_spend) VALUES (6,'8d906d78-ea33-11eb-863e-dc4a3ee3e76f','Tallie Emanson',8,NULL,114000);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed
