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
-- Table structure for table `fixed_cost_bill`
--

DROP TABLE IF EXISTS fixed_cost_bill;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE fixed_cost_bill (
  bill_id bigint NOT NULL AUTO_INCREMENT,
  fixed_cost_id bigint DEFAULT NULL,
  bill_uuid char(36) DEFAULT NULL,
  creation_date date DEFAULT NULL,
  due_date date DEFAULT NULL,
  total_spend double DEFAULT NULL,
  `status` char(1) DEFAULT NULL,
  user_id bigint DEFAULT NULL,
  PRIMARY KEY (bill_id),
  KEY FixedCostID_fk_idx (fixed_cost_id),
  KEY UserID4_fk_idx (user_id),
  CONSTRAINT FixedCostID_fk FOREIGN KEY (fixed_cost_id) REFERENCES fixed_cost (fixed_cost_id) ON DELETE SET NULL,
  CONSTRAINT UserID4_fk FOREIGN KEY (user_id) REFERENCES `user` (user_id) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fixed_cost_bill`
--

INSERT INTO fixed_cost_bill (bill_id, fixed_cost_id, bill_uuid, creation_date, due_date, total_spend, status, user_id) VALUES (1,3,'52a047d8-ee85-11eb-ae1e-dc4a3ee3e76f','2021-06-15','2021-07-15',350000,'F',4);
INSERT INTO fixed_cost_bill (bill_id, fixed_cost_id, bill_uuid, creation_date, due_date, total_spend, status, user_id) VALUES (2,4,'5699294b-ee85-11eb-ae1e-dc4a3ee3e76f','2021-06-15','2021-07-15',600000,'F',6);
INSERT INTO fixed_cost_bill (bill_id, fixed_cost_id, bill_uuid, creation_date, due_date, total_spend, status, user_id) VALUES (3,1,'5ab7142f-ee85-11eb-ae1e-dc4a3ee3e76f','2021-06-15','2021-07-15',200000,'F',7);
INSERT INTO fixed_cost_bill (bill_id, fixed_cost_id, bill_uuid, creation_date, due_date, total_spend, status, user_id) VALUES (4,2,'5d9cc08d-ee85-11eb-ae1e-dc4a3ee3e76f','2021-06-20','2021-07-20',300000,'F',9);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed
