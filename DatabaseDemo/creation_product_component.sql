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
-- Table structure for table `product_component`
--

DROP TABLE IF EXISTS product_component;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE product_component (
  product_id bigint DEFAULT NULL,
  ingredient_id bigint DEFAULT NULL,
  required_quantity bigint DEFAULT NULL,
  user_id bigint DEFAULT NULL,
  total_cost double DEFAULT NULL,
  KEY ProductID_fk_idx (product_id),
  KEY IngredientID_fk_idx (ingredient_id),
  KEY UserID10_fk_idx (user_id),
  CONSTRAINT IngredientID_fk FOREIGN KEY (ingredient_id) REFERENCES ingredient (ingredient_id) ON DELETE SET NULL,
  CONSTRAINT ProductID_fk FOREIGN KEY (product_id) REFERENCES product (product_id) ON DELETE SET NULL,
  CONSTRAINT UserID10_fk FOREIGN KEY (user_id) REFERENCES `user` (user_id) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_component`
--

INSERT INTO product_component (product_id, ingredient_id, required_quantity, user_id, total_cost) VALUES (1,13,2,14,176000);
INSERT INTO product_component (product_id, ingredient_id, required_quantity, user_id, total_cost) VALUES (2,2,3,7,90000);
INSERT INTO product_component (product_id, ingredient_id, required_quantity, user_id, total_cost) VALUES (2,8,1,10,7000);
INSERT INTO product_component (product_id, ingredient_id, required_quantity, user_id, total_cost) VALUES (3,5,2,3,102000);
INSERT INTO product_component (product_id, ingredient_id, required_quantity, user_id, total_cost) VALUES (3,7,1,9,12000);
INSERT INTO product_component (product_id, ingredient_id, required_quantity, user_id, total_cost) VALUES (4,1,2,5,124000);
INSERT INTO product_component (product_id, ingredient_id, required_quantity, user_id, total_cost) VALUES (4,7,1,9,12000);
INSERT INTO product_component (product_id, ingredient_id, required_quantity, user_id, total_cost) VALUES (4,8,1,10,7000);
INSERT INTO product_component (product_id, ingredient_id, required_quantity, user_id, total_cost) VALUES (5,3,2,8,82000);
INSERT INTO product_component (product_id, ingredient_id, required_quantity, user_id, total_cost) VALUES (5,8,1,10,7000);
INSERT INTO product_component (product_id, ingredient_id, required_quantity, user_id, total_cost) VALUES (6,11,2,4,62000);
INSERT INTO product_component (product_id, ingredient_id, required_quantity, user_id, total_cost) VALUES (7,15,2,4,120000);
INSERT INTO product_component (product_id, ingredient_id, required_quantity, user_id, total_cost) VALUES (8,5,1,3,51000);
INSERT INTO product_component (product_id, ingredient_id, required_quantity, user_id, total_cost) VALUES (8,6,1,6,15000);
INSERT INTO product_component (product_id, ingredient_id, required_quantity, user_id, total_cost) VALUES (9,13,1,14,44000);
INSERT INTO product_component (product_id, ingredient_id, required_quantity, user_id, total_cost) VALUES (9,7,1,9,12000);
INSERT INTO product_component (product_id, ingredient_id, required_quantity, user_id, total_cost) VALUES (10,2,2,7,60000);
INSERT INTO product_component (product_id, ingredient_id, required_quantity, user_id, total_cost) VALUES (10,10,1,15,23000);
INSERT INTO product_component (product_id, ingredient_id, required_quantity, user_id, total_cost) VALUES (11,2,2,7,60000);
INSERT INTO product_component (product_id, ingredient_id, required_quantity, user_id, total_cost) VALUES (11,4,1,12,22000);
INSERT INTO product_component (product_id, ingredient_id, required_quantity, user_id, total_cost) VALUES (12,1,2,5,124000);
INSERT INTO product_component (product_id, ingredient_id, required_quantity, user_id, total_cost) VALUES (12,11,2,4,62000);
INSERT INTO product_component (product_id, ingredient_id, required_quantity, user_id, total_cost) VALUES (13,12,1,13,29000);
INSERT INTO product_component (product_id, ingredient_id, required_quantity, user_id, total_cost) VALUES (14,14,1,3,40000);
INSERT INTO product_component (product_id, ingredient_id, required_quantity, user_id, total_cost) VALUES (15,13,2,14,88000);
INSERT INTO product_component (product_id, ingredient_id, required_quantity, user_id, total_cost) VALUES (15,9,1,11,15000);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed
