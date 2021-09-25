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
-- Table structure for table `plan`
--

DROP TABLE IF EXISTS plan;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE plan (
  plan_id bigint NOT NULL AUTO_INCREMENT,
  plan_uuid char(36) DEFAULT NULL,
  `name` varchar(40) DEFAULT NULL,
  price double DEFAULT NULL,
  duration varchar(10) DEFAULT NULL,
  picture_id bigint DEFAULT NULL,
  PRIMARY KEY (plan_id),
  KEY PictureID_fk_idx (picture_id),
  KEY PictureID_fk1_idx (picture_id),
  CONSTRAINT PictureID1_fk FOREIGN KEY (picture_id) REFERENCES picture (picture_id) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plan`
--

INSERT INTO plan (plan_id, plan_uuid, name, price, duration, picture_id) VALUES (1,'6e5a78bd-ee8c-11eb-b72b-dc4a3ee3e76f','Basic',200000,'2 months',41);
INSERT INTO plan (plan_id, plan_uuid, name, price, duration, picture_id) VALUES (2,'72f2d60e-ee8c-11eb-b72b-dc4a3ee3e76f','Premium',350000,'3 months',42);
INSERT INTO plan (plan_id, plan_uuid, name, price, duration, picture_id) VALUES (3,'759f7dbc-ee8c-11eb-b72b-dc4a3ee3e76f','No ads',250000,'1 month',43);
INSERT INTO plan (plan_id, plan_uuid, name, price, duration, picture_id) VALUES (4,'78758ce9-ee8c-11eb-b72b-dc4a3ee3e76f','Platinum',600000,'6 months',44);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed
