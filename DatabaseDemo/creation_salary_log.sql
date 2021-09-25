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
-- Table structure for table `salary_log`
--

DROP TABLE IF EXISTS salary_log;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE salary_log (
  staff_id bigint DEFAULT NULL,
  salary double DEFAULT NULL,
  payment_date date DEFAULT NULL,
  user_id bigint DEFAULT NULL,
  KEY StaffID_fk3_idx (staff_id),
  KEY UserID13_fk_idx (user_id),
  CONSTRAINT StaffID2_fk FOREIGN KEY (staff_id) REFERENCES staff (staff_id) ON DELETE SET NULL,
  CONSTRAINT UserID13_fk FOREIGN KEY (user_id) REFERENCES `user` (user_id) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salary_log`
--

INSERT INTO salary_log (staff_id, salary, payment_date, user_id) VALUES (1,15000000,'2021-07-02',15);
INSERT INTO salary_log (staff_id, salary, payment_date, user_id) VALUES (2,15000000,'2021-07-02',14);
INSERT INTO salary_log (staff_id, salary, payment_date, user_id) VALUES (3,7000000,'2021-07-06',13);
INSERT INTO salary_log (staff_id, salary, payment_date, user_id) VALUES (4,7000000,'2021-07-06',12);
INSERT INTO salary_log (staff_id, salary, payment_date, user_id) VALUES (5,6000000,'2021-07-06',11);
INSERT INTO salary_log (staff_id, salary, payment_date, user_id) VALUES (6,6000000,'2021-07-06',10);
INSERT INTO salary_log (staff_id, salary, payment_date, user_id) VALUES (7,6000000,'2021-07-06',9);
INSERT INTO salary_log (staff_id, salary, payment_date, user_id) VALUES (8,6000000,'2021-07-06',8);
INSERT INTO salary_log (staff_id, salary, payment_date, user_id) VALUES (9,6000000,'2021-07-06',7);
INSERT INTO salary_log (staff_id, salary, payment_date, user_id) VALUES (10,6000000,'2021-07-06',5);
INSERT INTO salary_log (staff_id, salary, payment_date, user_id) VALUES (11,6000000,'2021-07-06',6);
INSERT INTO salary_log (staff_id, salary, payment_date, user_id) VALUES (12,6000000,'2021-07-06',3);
INSERT INTO salary_log (staff_id, salary, payment_date, user_id) VALUES (13,6000000,'2021-07-06',4);
INSERT INTO salary_log (staff_id, salary, payment_date, user_id) VALUES (14,6000000,'2021-07-06',1);
INSERT INTO salary_log (staff_id, salary, payment_date, user_id) VALUES (15,6000000,'2021-07-06',2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed
