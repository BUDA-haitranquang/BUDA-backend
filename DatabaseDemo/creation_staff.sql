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
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS staff;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE staff (
  StaffID int NOT NULL AUTO_INCREMENT,
  Staff_UUID char(36) DEFAULT NULL,
  EmployeeName varchar(40) DEFAULT NULL,
  Position varchar(8) DEFAULT NULL,
  UserID int DEFAULT NULL,
  PRIMARY KEY (StaffID),
  KEY UserID_fk14_idx (UserID),
  CONSTRAINT UserID_fk14 FOREIGN KEY (UserID) REFERENCES `user` (UserID) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

INSERT INTO staff VALUES (1,'eccc05e1-ea06-11eb-863e-dc4a3ee3e76f','Miof mela Manders','Manager',1),(2,'f208044a-ea06-11eb-863e-dc4a3ee3e76f','Jeremia Prys','Manager',2),(3,'f52f6b51-ea06-11eb-863e-dc4a3ee3e76f','Karil Sustin','Employee',3),(4,'f839722d-ea06-11eb-863e-dc4a3ee3e76f','Dyanna Ellverston','Employee',4),(5,'fbcb75c2-ea06-11eb-863e-dc4a3ee3e76f','Niven Taffley','Employee',5),(6,'ff47cd47-ea06-11eb-863e-dc4a3ee3e76f','Ronalda Simants','Employee',6),(7,'0244ee3a-ea07-11eb-863e-dc4a3ee3e76f','Kerry Limer','Employee',7),(8,'05dd128b-ea07-11eb-863e-dc4a3ee3e76f','Jermayne Hamlen','Employee',8),(9,'0a526dfb-ea07-11eb-863e-dc4a3ee3e76f','Walther Holsey','Employee',9),(10,'0d45df6a-ea07-11eb-863e-dc4a3ee3e76f','Gerhard Jurczik','Employee',10),(11,'10692f1e-ea07-11eb-863e-dc4a3ee3e76f','Jerry Dixon','Employee',11),(12,'13a97e3e-ea07-11eb-863e-dc4a3ee3e76f','Lucias Sorsby','Employee',12),(13,'168d3223-ea07-11eb-863e-dc4a3ee3e76f','Raynor Tulk','Employee',13),(14,'198fed27-ea07-11eb-863e-dc4a3ee3e76f','Tammie Milham','Employee',14),(15,'20bd92f4-ea07-11eb-863e-dc4a3ee3e76f','Tildi Sleath','Employee',15);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed
