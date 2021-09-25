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
-- Table structure for table `other_cost`
--

DROP TABLE IF EXISTS other_cost;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE other_cost (
  other_cost_id bigint NOT NULL AUTO_INCREMENT,
  user_id bigint DEFAULT NULL,
  payment_date date DEFAULT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  total_cost double DEFAULT NULL,
  PRIMARY KEY (other_cost_id),
  KEY UserID8_fk_idx (user_id),
  CONSTRAINT UserID8_fk FOREIGN KEY (user_id) REFERENCES `user` (user_id) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `other_cost`
--

INSERT INTO other_cost (other_cost_id, user_id, payment_date, description, name, total_cost) VALUES (1,3,'2021-06-25','Testing','VAT',30000);
INSERT INTO other_cost (other_cost_id, user_id, payment_date, description, name, total_cost) VALUES (2,5,'2021-06-28','Testing','Rent',500000);
INSERT INTO other_cost (other_cost_id, user_id, payment_date, description, name, total_cost) VALUES (3,8,'2021-06-30','Testing','Repair',100000);
INSERT INTO other_cost (other_cost_id, user_id, payment_date, description, name, total_cost) VALUES (4,9,'2021-07-03','Testing','Fuel',120000);
INSERT INTO other_cost (other_cost_id, user_id, payment_date, description, name, total_cost) VALUES (5,12,'2021-07-08','Testing','Insurance',200000);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed
