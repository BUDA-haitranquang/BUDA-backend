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
-- Table structure for table `staff_note`
--

DROP TABLE IF EXISTS staff_note;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE staff_note (
  staff_id bigint DEFAULT NULL,
  user_id bigint DEFAULT NULL,
  note_date date DEFAULT NULL,
  message varchar(150) DEFAULT NULL,
  seen tinyint DEFAULT NULL,
  KEY StaffID3_fk_idx (staff_id),
  KEY UserID17_fk_idx (user_id),
  CONSTRAINT StaffID3_fk FOREIGN KEY (staff_id) REFERENCES staff (staff_id) ON DELETE SET NULL,
  CONSTRAINT UserID17_fk FOREIGN KEY (user_id) REFERENCES `user` (user_id) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff_note`
--

INSERT INTO staff_note (staff_id, user_id, note_date, message, seen) VALUES (1,1,'2021-06-17','No comment',1);
INSERT INTO staff_note (staff_id, user_id, note_date, message, seen) VALUES (3,1,'2021-06-25','Demo',1);
INSERT INTO staff_note (staff_id, user_id, note_date, message, seen) VALUES (8,1,'2021-06-29','Testing',1);
INSERT INTO staff_note (staff_id, user_id, note_date, message, seen) VALUES (6,1,'2021-07-04','Beta',1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed
