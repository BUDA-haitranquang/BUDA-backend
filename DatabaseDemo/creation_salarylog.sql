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
-- Table structure for table `salarylog`
--

DROP TABLE IF EXISTS salarylog;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE salarylog (
  StaffID int DEFAULT NULL,
  Salary double DEFAULT NULL,
  PaymentDate date DEFAULT NULL,
  UserID int DEFAULT NULL,
  KEY StaffID_fk3_idx (StaffID),
  KEY UserID_fork_idx (UserID),
  CONSTRAINT StaffID_fk3 FOREIGN KEY (StaffID) REFERENCES staff (StaffID) ON DELETE SET NULL,
  CONSTRAINT UserID_fork FOREIGN KEY (UserID) REFERENCES `user` (UserID) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salarylog`
--

INSERT INTO salarylog (StaffID, Salary, PaymentDate, UserID) VALUES (1,15000000,'2021-07-02',15);
INSERT INTO salarylog (StaffID, Salary, PaymentDate, UserID) VALUES (2,15000000,'2021-07-02',14);
INSERT INTO salarylog (StaffID, Salary, PaymentDate, UserID) VALUES (3,7000000,'2021-07-06',13);
INSERT INTO salarylog (StaffID, Salary, PaymentDate, UserID) VALUES (4,7000000,'2021-07-06',12);
INSERT INTO salarylog (StaffID, Salary, PaymentDate, UserID) VALUES (5,6000000,'2021-07-06',11);
INSERT INTO salarylog (StaffID, Salary, PaymentDate, UserID) VALUES (6,6000000,'2021-07-06',10);
INSERT INTO salarylog (StaffID, Salary, PaymentDate, UserID) VALUES (7,6000000,'2021-07-06',9);
INSERT INTO salarylog (StaffID, Salary, PaymentDate, UserID) VALUES (8,6000000,'2021-07-06',8);
INSERT INTO salarylog (StaffID, Salary, PaymentDate, UserID) VALUES (9,6000000,'2021-07-06',7);
INSERT INTO salarylog (StaffID, Salary, PaymentDate, UserID) VALUES (10,6000000,'2021-07-06',5);
INSERT INTO salarylog (StaffID, Salary, PaymentDate, UserID) VALUES (11,6000000,'2021-07-06',6);
INSERT INTO salarylog (StaffID, Salary, PaymentDate, UserID) VALUES (12,6000000,'2021-07-06',3);
INSERT INTO salarylog (StaffID, Salary, PaymentDate, UserID) VALUES (13,6000000,'2021-07-06',4);
INSERT INTO salarylog (StaffID, Salary, PaymentDate, UserID) VALUES (14,6000000,'2021-07-06',1);
INSERT INTO salarylog (StaffID, Salary, PaymentDate, UserID) VALUES (15,6000000,'2021-07-06',2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed
