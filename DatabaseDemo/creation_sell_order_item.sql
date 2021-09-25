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
-- Table structure for table `sell_order_item`
--

DROP TABLE IF EXISTS sell_order_item;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE sell_order_item (
  sell_order_id bigint DEFAULT NULL,
  product_id bigint DEFAULT NULL,
  user_id bigint DEFAULT NULL,
  quantity bigint DEFAULT NULL,
  price_per_unit double DEFAULT NULL,
  creation_date date DEFAULT NULL,
  cost_per_unit double DEFAULT NULL,
  gender enum('Male','Female') DEFAULT NULL,
  age_group enum('<18','18-29','30-44','45-60','>60') DEFAULT NULL,
  actual_total_sale double DEFAULT NULL,
  KEY SellOrderID_fk_idx (sell_order_id),
  KEY ProductID3_fk_idx (product_id),
  KEY UserID15_fk_idx (user_id),
  CONSTRAINT ProductID3_fk FOREIGN KEY (product_id) REFERENCES product (product_id) ON DELETE SET NULL,
  CONSTRAINT SellOrderID_fk FOREIGN KEY (sell_order_id) REFERENCES sell_order (sell_order_id) ON DELETE SET NULL,
  CONSTRAINT UserID15_fk FOREIGN KEY (user_id) REFERENCES `user` (user_id) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sell_order_item`
--

INSERT INTO sell_order_item (sell_order_id, product_id, user_id, quantity, price_per_unit, creation_date, cost_per_unit, gender, age_group, actual_total_sale) VALUES (1,1,5,1,190000,'2021-06-15',176000,'Female','<18',190000);
INSERT INTO sell_order_item (sell_order_id, product_id, user_id, quantity, price_per_unit, creation_date, cost_per_unit, gender, age_group, actual_total_sale) VALUES (1,9,5,1,70000,'2021-06-15',56000,'Female','<18',70000);
INSERT INTO sell_order_item (sell_order_id, product_id, user_id, quantity, price_per_unit, creation_date, cost_per_unit, gender, age_group, actual_total_sale) VALUES (2,3,8,2,135000,'2021-06-16',114000,'Male','30-44',270000);
INSERT INTO sell_order_item (sell_order_id, product_id, user_id, quantity, price_per_unit, creation_date, cost_per_unit, gender, age_group, actual_total_sale) VALUES (3,2,9,1,115000,'2021-06-20',97000,'Male','>60',115000);
INSERT INTO sell_order_item (sell_order_id, product_id, user_id, quantity, price_per_unit, creation_date, cost_per_unit, gender, age_group, actual_total_sale) VALUES (3,13,9,1,40000,'2021-06-20',29000,'Male','>60',40000);
INSERT INTO sell_order_item (sell_order_id, product_id, user_id, quantity, price_per_unit, creation_date, cost_per_unit, gender, age_group, actual_total_sale) VALUES (4,12,10,1,205000,'2021-06-21',186000,'Male','<18',205000);
INSERT INTO sell_order_item (sell_order_id, product_id, user_id, quantity, price_per_unit, creation_date, cost_per_unit, gender, age_group, actual_total_sale) VALUES (5,15,12,1,125000,'2021-06-22',103000,'Male','18-29',125000);
INSERT INTO sell_order_item (sell_order_id, product_id, user_id, quantity, price_per_unit, creation_date, cost_per_unit, gender, age_group, actual_total_sale) VALUES (5,13,12,1,40000,'2021-06-22',29000,'Male','18-29',40000);
INSERT INTO sell_order_item (sell_order_id, product_id, user_id, quantity, price_per_unit, creation_date, cost_per_unit, gender, age_group, actual_total_sale) VALUES (6,5,14,2,100000,'2021-06-23',89000,'Female','45-60',200000);
INSERT INTO sell_order_item (sell_order_id, product_id, user_id, quantity, price_per_unit, creation_date, cost_per_unit, gender, age_group, actual_total_sale) VALUES (7,10,3,1,100000,'2021-06-26',83000,'Female','45-60',10000);
INSERT INTO sell_order_item (sell_order_id, product_id, user_id, quantity, price_per_unit, creation_date, cost_per_unit, gender, age_group, actual_total_sale) VALUES (7,6,3,1,80000,'2021-06-26',62000,'Female','45-60',80000);
INSERT INTO sell_order_item (sell_order_id, product_id, user_id, quantity, price_per_unit, creation_date, cost_per_unit, gender, age_group, actual_total_sale) VALUES (8,8,8,2,85000,'2021-06-28',66000,'Male','>60',170000);
INSERT INTO sell_order_item (sell_order_id, product_id, user_id, quantity, price_per_unit, creation_date, cost_per_unit, gender, age_group, actual_total_sale) VALUES (9,11,9,1,100000,'2021-06-29',82000,'Male','30-44',100000);
INSERT INTO sell_order_item (sell_order_id, product_id, user_id, quantity, price_per_unit, creation_date, cost_per_unit, gender, age_group, actual_total_sale) VALUES (9,14,9,1,55000,'2021-06-29',40000,'Male','30-44',55000);
INSERT INTO sell_order_item (sell_order_id, product_id, user_id, quantity, price_per_unit, creation_date, cost_per_unit, gender, age_group, actual_total_sale) VALUES (10,2,7,2,115000,'2021-06-30',97000,'Male','30-44',230000);
INSERT INTO sell_order_item (sell_order_id, product_id, user_id, quantity, price_per_unit, creation_date, cost_per_unit, gender, age_group, actual_total_sale) VALUES (11,4,6,1,160000,'2021-07-02',143000,'Male','30-44',160000);
INSERT INTO sell_order_item (sell_order_id, product_id, user_id, quantity, price_per_unit, creation_date, cost_per_unit, gender, age_group, actual_total_sale) VALUES (11,6,6,1,80000,'2021-07-02',62000,'Male','30-44',80000);
INSERT INTO sell_order_item (sell_order_id, product_id, user_id, quantity, price_per_unit, creation_date, cost_per_unit, gender, age_group, actual_total_sale) VALUES (12,15,4,2,125000,'2021-07-05',103000,'Female','45-60',250000);
INSERT INTO sell_order_item (sell_order_id, product_id, user_id, quantity, price_per_unit, creation_date, cost_per_unit, gender, age_group, actual_total_sale) VALUES (13,10,15,1,100000,'2021-07-06',83000,'Female','18-29',100000);
INSERT INTO sell_order_item (sell_order_id, product_id, user_id, quantity, price_per_unit, creation_date, cost_per_unit, gender, age_group, actual_total_sale) VALUES (13,11,15,1,100000,'2021-07-06',82000,'Female','18-29',100000);
INSERT INTO sell_order_item (sell_order_id, product_id, user_id, quantity, price_per_unit, creation_date, cost_per_unit, gender, age_group, actual_total_sale) VALUES (14,9,13,2,70000,'2021-07-07',56000,'Male','18-29',140000);
INSERT INTO sell_order_item (sell_order_id, product_id, user_id, quantity, price_per_unit, creation_date, cost_per_unit, gender, age_group, actual_total_sale) VALUES (15,1,11,1,190000,'2021-07-08',176000,'Male','>60',190000);
INSERT INTO sell_order_item (sell_order_id, product_id, user_id, quantity, price_per_unit, creation_date, cost_per_unit, gender, age_group, actual_total_sale) VALUES (15,2,11,1,115000,'2021-07-08',97000,'Male','>60',115000);
INSERT INTO sell_order_item (sell_order_id, product_id, user_id, quantity, price_per_unit, creation_date, cost_per_unit, gender, age_group, actual_total_sale) VALUES (16,7,6,2,140000,'2021-07-09',120000,'Male','30-44',280000);
INSERT INTO sell_order_item (sell_order_id, product_id, user_id, quantity, price_per_unit, creation_date, cost_per_unit, gender, age_group, actual_total_sale) VALUES (17,5,10,1,100000,'2021-07-10',89000,'Female','>60',100000);
INSERT INTO sell_order_item (sell_order_id, product_id, user_id, quantity, price_per_unit, creation_date, cost_per_unit, gender, age_group, actual_total_sale) VALUES (17,6,10,1,80000,'2021-07-10',62000,'Female','>60',80000);
INSERT INTO sell_order_item (sell_order_id, product_id, user_id, quantity, price_per_unit, creation_date, cost_per_unit, gender, age_group, actual_total_sale) VALUES (18,1,12,2,190000,'2021-07-12',176000,'Female','45-60',380000);
INSERT INTO sell_order_item (sell_order_id, product_id, user_id, quantity, price_per_unit, creation_date, cost_per_unit, gender, age_group, actual_total_sale) VALUES (19,8,14,1,85000,'2021-07-13',66000,'Female','30-44',85000);
INSERT INTO sell_order_item (sell_order_id, product_id, user_id, quantity, price_per_unit, creation_date, cost_per_unit, gender, age_group, actual_total_sale) VALUES (19,13,14,1,40000,'2021-07-13',176000,'Female','30-44',29000);
INSERT INTO sell_order_item (sell_order_id, product_id, user_id, quantity, price_per_unit, creation_date, cost_per_unit, gender, age_group, actual_total_sale) VALUES (20,4,8,2,160000,'2021-07-15',143000,'Male','>60',320000);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed
