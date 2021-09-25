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
-- Table structure for table `buy_order_item`
--

DROP TABLE IF EXISTS buy_order_item;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE buy_order_item (
  buy_order_id bigint DEFAULT NULL,
  ingredient_id bigint DEFAULT NULL,
  user_id bigint DEFAULT NULL,
  quantity bigint DEFAULT NULL,
  price_per_unit double DEFAULT NULL,
  creation_date date DEFAULT NULL,
  supplier_id bigint DEFAULT NULL,
  KEY BuyOrderID_fk_idx (buy_order_id),
  KEY SupplierID1_fk_idx (supplier_id),
  KEY UserID1_fk_idx (user_id),
  CONSTRAINT BuyOrderID_fk FOREIGN KEY (buy_order_id) REFERENCES buy_order (buy_order_id) ON DELETE SET NULL,
  CONSTRAINT SupplierID1_fk FOREIGN KEY (supplier_id) REFERENCES supplier (supplier_id) ON DELETE SET NULL,
  CONSTRAINT UserID1_fk FOREIGN KEY (user_id) REFERENCES `user` (user_id) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `buy_order_item`
--

INSERT INTO buy_order_item (buy_order_id, ingredient_id, user_id, quantity, price_per_unit, creation_date, supplier_id) VALUES (1,2,3,4,30000,'2021-06-15',1);
INSERT INTO buy_order_item (buy_order_id, ingredient_id, user_id, quantity, price_per_unit, creation_date, supplier_id) VALUES (1,1,3,6,62000,'2021-06-15',7);
INSERT INTO buy_order_item (buy_order_id, ingredient_id, user_id, quantity, price_per_unit, creation_date, supplier_id) VALUES (2,5,6,4,51000,'2021-06-16',4);
INSERT INTO buy_order_item (buy_order_id, ingredient_id, user_id, quantity, price_per_unit, creation_date, supplier_id) VALUES (3,6,8,4,32000,'2021-06-18',4);
INSERT INTO buy_order_item (buy_order_id, ingredient_id, user_id, quantity, price_per_unit, creation_date, supplier_id) VALUES (4,4,9,4,22000,'2021-06-19',4);
INSERT INTO buy_order_item (buy_order_id, ingredient_id, user_id, quantity, price_per_unit, creation_date, supplier_id) VALUES (5,9,7,5,15000,'2021-06-20',8);
INSERT INTO buy_order_item (buy_order_id, ingredient_id, user_id, quantity, price_per_unit, creation_date, supplier_id) VALUES (5,10,7,4,23000,'2021-06-20',3);
INSERT INTO buy_order_item (buy_order_id, ingredient_id, user_id, quantity, price_per_unit, creation_date, supplier_id) VALUES (6,3,5,6,41000,'2021-06-21',4);
INSERT INTO buy_order_item (buy_order_id, ingredient_id, user_id, quantity, price_per_unit, creation_date, supplier_id) VALUES (7,3,8,5,41000,'2021-06-22',4);
INSERT INTO buy_order_item (buy_order_id, ingredient_id, user_id, quantity, price_per_unit, creation_date, supplier_id) VALUES (8,12,10,6,29000,'2021-06-23',2);
INSERT INTO buy_order_item (buy_order_id, ingredient_id, user_id, quantity, price_per_unit, creation_date, supplier_id) VALUES (9,12,12,6,29000,'2021-06-24',2);
INSERT INTO buy_order_item (buy_order_id, ingredient_id, user_id, quantity, price_per_unit, creation_date, supplier_id) VALUES (10,11,9,6,31000,'2021-06-25',10);
INSERT INTO buy_order_item (buy_order_id, ingredient_id, user_id, quantity, price_per_unit, creation_date, supplier_id) VALUES (11,7,15,6,12000,'2021-06-27',6);
INSERT INTO buy_order_item (buy_order_id, ingredient_id, user_id, quantity, price_per_unit, creation_date, supplier_id) VALUES (12,8,5,5,7000,'2021-06-29',1);
INSERT INTO buy_order_item (buy_order_id, ingredient_id, user_id, quantity, price_per_unit, creation_date, supplier_id) VALUES (12,7,5,4,12000,'2021-06-29',6);
INSERT INTO buy_order_item (buy_order_id, ingredient_id, user_id, quantity, price_per_unit, creation_date, supplier_id) VALUES (13,14,13,5,40000,'2021-07-01',2);
INSERT INTO buy_order_item (buy_order_id, ingredient_id, user_id, quantity, price_per_unit, creation_date, supplier_id) VALUES (14,13,6,6,44000,'2021-07-02',2);
INSERT INTO buy_order_item (buy_order_id, ingredient_id, user_id, quantity, price_per_unit, creation_date, supplier_id) VALUES (15,6,14,4,32000,'2021-07-05',4);
INSERT INTO buy_order_item (buy_order_id, ingredient_id, user_id, quantity, price_per_unit, creation_date, supplier_id) VALUES (16,8,4,7,7000,'2021-07-06',1);
INSERT INTO buy_order_item (buy_order_id, ingredient_id, user_id, quantity, price_per_unit, creation_date, supplier_id) VALUES (17,8,10,5,7000,'2021-07-10',1);
INSERT INTO buy_order_item (buy_order_id, ingredient_id, user_id, quantity, price_per_unit, creation_date, supplier_id) VALUES (18,15,9,4,60000,'2021-07-12',2);
INSERT INTO buy_order_item (buy_order_id, ingredient_id, user_id, quantity, price_per_unit, creation_date, supplier_id) VALUES (19,5,15,4,51000,'2021-07-15',4);
INSERT INTO buy_order_item (buy_order_id, ingredient_id, user_id, quantity, price_per_unit, creation_date, supplier_id) VALUES (19,4,15,6,22000,'2021-07-15',4);
INSERT INTO buy_order_item (buy_order_id, ingredient_id, user_id, quantity, price_per_unit, creation_date, supplier_id) VALUES (20,6,4,4,32000,'2021-07-17',4);
INSERT INTO buy_order_item (buy_order_id, ingredient_id, user_id, quantity, price_per_unit, creation_date, supplier_id) VALUES (20,9,4,5,15000,'2021-07-17',8);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed
