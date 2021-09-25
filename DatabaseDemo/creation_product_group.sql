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
-- Table structure for table `product_group`
--

DROP TABLE IF EXISTS product_group;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE product_group (
  group_id bigint DEFAULT NULL,
  product_id bigint DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  KEY ProductID1_fk_idx (product_id),
  CONSTRAINT ProductID1_fk FOREIGN KEY (product_id) REFERENCES product (product_id) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_group`
--

INSERT INTO product_group (group_id, product_id, name) VALUES (1,2,'Appetizer');
INSERT INTO product_group (group_id, product_id, name) VALUES (1,6,'Appetizer');
INSERT INTO product_group (group_id, product_id, name) VALUES (1,7,'Appetizer');
INSERT INTO product_group (group_id, product_id, name) VALUES (1,8,'Appetizer');
INSERT INTO product_group (group_id, product_id, name) VALUES (1,9,'Appetizer');
INSERT INTO product_group (group_id, product_id, name) VALUES (1,15,'Appetizer');
INSERT INTO product_group (group_id, product_id, name) VALUES (2,1,'Entree');
INSERT INTO product_group (group_id, product_id, name) VALUES (2,3,'Entree');
INSERT INTO product_group (group_id, product_id, name) VALUES (2,4,'Entree');
INSERT INTO product_group (group_id, product_id, name) VALUES (2,5,'Entree');
INSERT INTO product_group (group_id, product_id, name) VALUES (2,10,'Entree');
INSERT INTO product_group (group_id, product_id, name) VALUES (2,11,'Entree');
INSERT INTO product_group (group_id, product_id, name) VALUES (2,12,'Entree');
INSERT INTO product_group (group_id, product_id, name) VALUES (3,13,'Drinks');
INSERT INTO product_group (group_id, product_id, name) VALUES (3,14,'Drinks');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed
