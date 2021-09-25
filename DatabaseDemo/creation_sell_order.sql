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
-- Table structure for table `sell_order`
--

DROP TABLE IF EXISTS sell_order;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE sell_order (
  sell_order_id bigint NOT NULL AUTO_INCREMENT,
  customer_id bigint DEFAULT NULL,
  sell_order_uuid char(36) DEFAULT NULL,
  creation_date date DEFAULT NULL,
  `status` char(1) DEFAULT NULL,
  discount_id bigint DEFAULT NULL,
  final_cost double DEFAULT NULL,
  gender enum('Male','Female') DEFAULT NULL,
  age_group enum('<18','18-29','30-44','45-60','>60') DEFAULT NULL,
  actual_discount_c double DEFAULT NULL,
  actual_discount_p double DEFAULT NULL,
  real_cost double DEFAULT NULL,
  user_id bigint DEFAULT NULL,
  customer_message varchar(150) DEFAULT NULL,
  PRIMARY KEY (sell_order_id),
  KEY CustomerID_fk_idx (customer_id),
  KEY DiscountID1_fk_idx (discount_id),
  KEY UserID14_fk_idx (user_id),
  CONSTRAINT CustomerID_fk FOREIGN KEY (customer_id) REFERENCES customer (customer_id) ON DELETE SET NULL,
  CONSTRAINT DiscountID1_fk FOREIGN KEY (discount_id) REFERENCES discount (discount_id) ON DELETE SET NULL,
  CONSTRAINT UserID14_fk FOREIGN KEY (user_id) REFERENCES `user` (user_id) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sell_order`
--

INSERT INTO sell_order (sell_order_id, customer_id, sell_order_uuid, creation_date, status, discount_id, final_cost, gender, age_group, actual_discount_c, actual_discount_p, real_cost, user_id, customer_message) VALUES (1,1,'fc0abf01-ea32-11eb-863e-dc4a3ee3e76f','2021-06-15','F',1,240000,'Female','<18',20000,0.08,260000,5,'Good');
INSERT INTO sell_order (sell_order_id, customer_id, sell_order_uuid, creation_date, status, discount_id, final_cost, gender, age_group, actual_discount_c, actual_discount_p, real_cost, user_id, customer_message) VALUES (2,2,'032de386-ea33-11eb-863e-dc4a3ee3e76f','2021-06-16','F',8,270000,'Male','30-44',0,0,270000,7,'Not bad');
INSERT INTO sell_order (sell_order_id, customer_id, sell_order_uuid, creation_date, status, discount_id, final_cost, gender, age_group, actual_discount_c, actual_discount_p, real_cost, user_id, customer_message) VALUES (3,3,'05b99a0c-ea33-11eb-863e-dc4a3ee3e76f','2021-06-20','F',NULL,139500,'Male','>60',0,0,155000,9,'Excellent');
INSERT INTO sell_order (sell_order_id, customer_id, sell_order_uuid, creation_date, status, discount_id, final_cost, gender, age_group, actual_discount_c, actual_discount_p, real_cost, user_id, customer_message) VALUES (4,4,'08694138-ea33-11eb-863e-dc4a3ee3e76f','2021-06-21','F',8,205000,'Male','<18',0,0,205000,7,'Fantastic');
INSERT INTO sell_order (sell_order_id, customer_id, sell_order_uuid, creation_date, status, discount_id, final_cost, gender, age_group, actual_discount_c, actual_discount_p, real_cost, user_id, customer_message) VALUES (5,5,'0ad054f5-ea33-11eb-863e-dc4a3ee3e76f','2021-06-22','F',8,165000,'Male','18-29',0,0,165000,7,'Very good');
INSERT INTO sell_order (sell_order_id, customer_id, sell_order_uuid, creation_date, status, discount_id, final_cost, gender, age_group, actual_discount_c, actual_discount_p, real_cost, user_id, customer_message) VALUES (6,6,'0dcf22a5-ea33-11eb-863e-dc4a3ee3e76f','2021-06-23','F',8,200000,'Female','45-60',0,0,200000,7,'Bad');
INSERT INTO sell_order (sell_order_id, customer_id, sell_order_uuid, creation_date, status, discount_id, final_cost, gender, age_group, actual_discount_c, actual_discount_p, real_cost, user_id, customer_message) VALUES (7,7,'107336a7-ea33-11eb-863e-dc4a3ee3e76f','2021-06-26','F',8,90000,'Female','45-60',0,0,90000,7,'Nice');
INSERT INTO sell_order (sell_order_id, customer_id, sell_order_uuid, creation_date, status, discount_id, final_cost, gender, age_group, actual_discount_c, actual_discount_p, real_cost, user_id, customer_message) VALUES (8,8,'13c04f6b-ea33-11eb-863e-dc4a3ee3e76f','2021-06-28','F',8,170000,'Male','>60',0,0,170000,7,'Lovely');
INSERT INTO sell_order (sell_order_id, customer_id, sell_order_uuid, creation_date, status, discount_id, final_cost, gender, age_group, actual_discount_c, actual_discount_p, real_cost, user_id, customer_message) VALUES (9,9,'16918ea9-ea33-11eb-863e-dc4a3ee3e76f','2021-06-29','F',NULL,155000,'Male','30-44',0,0,155000,9,'Stunning');
INSERT INTO sell_order (sell_order_id, customer_id, sell_order_uuid, creation_date, status, discount_id, final_cost, gender, age_group, actual_discount_c, actual_discount_p, real_cost, user_id, customer_message) VALUES (10,10,'1907c6b8-ea33-11eb-863e-dc4a3ee3e76f','2021-06-30','F',8,230000,'Male','30-44',0,0,230000,7,'Gorgeous');
INSERT INTO sell_order (sell_order_id, customer_id, sell_order_uuid, creation_date, status, discount_id, final_cost, gender, age_group, actual_discount_c, actual_discount_p, real_cost, user_id, customer_message) VALUES (11,9,'1e2bf6a7-ea33-11eb-863e-dc4a3ee3e76f','2021-07-02','F',NULL,240000,'Male','30-44',0,0,240000,6,'Terrific');
INSERT INTO sell_order (sell_order_id, customer_id, sell_order_uuid, creation_date, status, discount_id, final_cost, gender, age_group, actual_discount_c, actual_discount_p, real_cost, user_id, customer_message) VALUES (12,7,'20d2e064-ea33-11eb-863e-dc4a3ee3e76f','2021-07-05','F',8,250000,'Female','45-60',0,0,250000,7,'Horrible');
INSERT INTO sell_order (sell_order_id, customer_id, sell_order_uuid, creation_date, status, discount_id, final_cost, gender, age_group, actual_discount_c, actual_discount_p, real_cost, user_id, customer_message) VALUES (13,11,'2352c539-ea33-11eb-863e-dc4a3ee3e76f','2021-07-06','F',8,200000,'Female','18-29',0,0,200000,7,'Wonderful');
INSERT INTO sell_order (sell_order_id, customer_id, sell_order_uuid, creation_date, status, discount_id, final_cost, gender, age_group, actual_discount_c, actual_discount_p, real_cost, user_id, customer_message) VALUES (14,12,'2618b431-ea33-11eb-863e-dc4a3ee3e76f','2021-07-07','F',8,140000,'Male','18-29',0,0,140000,7,'Perfect');
INSERT INTO sell_order (sell_order_id, customer_id, sell_order_uuid, creation_date, status, discount_id, final_cost, gender, age_group, actual_discount_c, actual_discount_p, real_cost, user_id, customer_message) VALUES (15,13,'2ef02989-ea33-11eb-863e-dc4a3ee3e76f','2021-07-08','F',NULL,305000,'Male','>60',0,0,305000,10,'Breathtaking');
INSERT INTO sell_order (sell_order_id, customer_id, sell_order_uuid, creation_date, status, discount_id, final_cost, gender, age_group, actual_discount_c, actual_discount_p, real_cost, user_id, customer_message) VALUES (16,2,'3c4aa402-ea33-11eb-863e-dc4a3ee3e76f','2021-07-09','F',8,280000,'Male','30-44',0,0,280000,7,'Outstanding');
INSERT INTO sell_order (sell_order_id, customer_id, sell_order_uuid, creation_date, status, discount_id, final_cost, gender, age_group, actual_discount_c, actual_discount_p, real_cost, user_id, customer_message) VALUES (17,14,'4024ae88-ea33-11eb-863e-dc4a3ee3e76f','2021-07-10','F',8,180000,'Female','>60',0,0,180000,7,'Exceptional');
INSERT INTO sell_order (sell_order_id, customer_id, sell_order_uuid, creation_date, status, discount_id, final_cost, gender, age_group, actual_discount_c, actual_discount_p, real_cost, user_id, customer_message) VALUES (18,6,'4360a888-ea33-11eb-863e-dc4a3ee3e76f','2021-07-12','F',NULL,380000,'Female','45-60',0,0,380000,12,'Amazing');
INSERT INTO sell_order (sell_order_id, customer_id, sell_order_uuid, creation_date, status, discount_id, final_cost, gender, age_group, actual_discount_c, actual_discount_p, real_cost, user_id, customer_message) VALUES (19,15,'45d8e449-ea33-11eb-863e-dc4a3ee3e76f','2021-07-13','F',NULL,114000,'Female','30-44',0,0,114000,5,'Spectacular');
INSERT INTO sell_order (sell_order_id, customer_id, sell_order_uuid, creation_date, status, discount_id, final_cost, gender, age_group, actual_discount_c, actual_discount_p, real_cost, user_id, customer_message) VALUES (20,8,'487e12e6-ea33-11eb-863e-dc4a3ee3e76f','2021-07-15','F',8,320000,'Male','>60',0,0,320000,7,'Phenomenal');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed
