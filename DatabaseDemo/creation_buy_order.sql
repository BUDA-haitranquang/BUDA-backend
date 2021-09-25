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
-- Table structure for table `buy_order`
--

DROP TABLE IF EXISTS buy_order;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE buy_order (
  buy_order_id bigint NOT NULL,
  buy_order_uuid varchar(36) DEFAULT NULL,
  supplier_id bigint DEFAULT NULL,
  creation_date date DEFAULT NULL,
  user_id bigint DEFAULT NULL,
  total_cost double DEFAULT NULL,
  `status` char(1) DEFAULT NULL,
  PRIMARY KEY (buy_order_id),
  KEY SupplierID_fk_idx (supplier_id),
  KEY UserID_fk_idx (user_id),
  CONSTRAINT SupplierID_fk FOREIGN KEY (supplier_id) REFERENCES supplier (supplier_id) ON DELETE SET NULL,
  CONSTRAINT UserID_fk FOREIGN KEY (user_id) REFERENCES `user` (user_id) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `buy_order`
--

INSERT INTO buy_order (buy_order_id, buy_order_uuid, supplier_id, creation_date, user_id, total_cost, status) VALUES (1,'a88be88b-ea04-11eb-863e-dc4a3ee3e76f',4,'2021-06-15',3,492000,'F');
INSERT INTO buy_order (buy_order_id, buy_order_uuid, supplier_id, creation_date, user_id, total_cost, status) VALUES (2,'ccc381ea-ea04-11eb-863e-dc4a3ee3e76f',5,'2021-06-16',6,204000,'F');
INSERT INTO buy_order (buy_order_id, buy_order_uuid, supplier_id, creation_date, user_id, total_cost, status) VALUES (3,'d277a144-ea04-11eb-863e-dc4a3ee3e76f',2,'2021-06-18',9,128000,'F');
INSERT INTO buy_order (buy_order_id, buy_order_uuid, supplier_id, creation_date, user_id, total_cost, status) VALUES (4,'d7e93474-ea04-11eb-863e-dc4a3ee3e76f',9,'2021-06-19',12,88000,'F');
INSERT INTO buy_order (buy_order_id, buy_order_uuid, supplier_id, creation_date, user_id, total_cost, status) VALUES (5,'df429b99-ea04-11eb-863e-dc4a3ee3e76f',7,'2021-06-20',15,167000,'F');
INSERT INTO buy_order (buy_order_id, buy_order_uuid, supplier_id, creation_date, user_id, total_cost, status) VALUES (6,'e2c83d80-ea04-11eb-863e-dc4a3ee3e76f',6,'2021-06-21',4,246000,'F');
INSERT INTO buy_order (buy_order_id, buy_order_uuid, supplier_id, creation_date, user_id, total_cost, status) VALUES (7,'e6a80da8-ea04-11eb-863e-dc4a3ee3e76f',8,'2021-06-22',8,205000,'F');
INSERT INTO buy_order (buy_order_id, buy_order_uuid, supplier_id, creation_date, user_id, total_cost, status) VALUES (8,'ec78a350-ea04-11eb-863e-dc4a3ee3e76f',1,'2021-06-23',3,174000,'F');
INSERT INTO buy_order (buy_order_id, buy_order_uuid, supplier_id, creation_date, user_id, total_cost, status) VALUES (9,'efce5d03-ea04-11eb-863e-dc4a3ee3e76f',2,'2021-06-24',7,174000,'F');
INSERT INTO buy_order (buy_order_id, buy_order_uuid, supplier_id, creation_date, user_id, total_cost, status) VALUES (10,'f302dc2e-ea04-11eb-863e-dc4a3ee3e76f',4,'2021-06-25',6,186000,'F');
INSERT INTO buy_order (buy_order_id, buy_order_uuid, supplier_id, creation_date, user_id, total_cost, status) VALUES (11,'f82fbb93-ea04-11eb-863e-dc4a3ee3e76f',10,'2021-06-27',11,72000,'F');
INSERT INTO buy_order (buy_order_id, buy_order_uuid, supplier_id, creation_date, user_id, total_cost, status) VALUES (12,'fc05d744-ea04-11eb-863e-dc4a3ee3e76f',7,'2021-06-29',10,83000,'F');
INSERT INTO buy_order (buy_order_id, buy_order_uuid, supplier_id, creation_date, user_id, total_cost, status) VALUES (13,'0114de7e-ea05-11eb-863e-dc4a3ee3e76f',7,'2021-07-01',10,200000,'F');
INSERT INTO buy_order (buy_order_id, buy_order_uuid, supplier_id, creation_date, user_id, total_cost, status) VALUES (14,'041d6ba0-ea05-11eb-863e-dc4a3ee3e76f',8,'2021-07-02',14,264000,'F');
INSERT INTO buy_order (buy_order_id, buy_order_uuid, supplier_id, creation_date, user_id, total_cost, status) VALUES (15,'09f0e5e4-ea05-11eb-863e-dc4a3ee3e76f',3,'2021-07-05',5,128000,'F');
INSERT INTO buy_order (buy_order_id, buy_order_uuid, supplier_id, creation_date, user_id, total_cost, status) VALUES (16,'0e18a2f5-ea05-11eb-863e-dc4a3ee3e76f',1,'2021-07-06',5,49000,'F');
INSERT INTO buy_order (buy_order_id, buy_order_uuid, supplier_id, creation_date, user_id, total_cost, status) VALUES (17,'12977a1a-ea05-11eb-863e-dc4a3ee3e76f',5,'2021-07-10',7,35000,'F');
INSERT INTO buy_order (buy_order_id, buy_order_uuid, supplier_id, creation_date, user_id, total_cost, status) VALUES (18,'16cd71af-ea05-11eb-863e-dc4a3ee3e76f',6,'2021-07-12',13,240000,'F');
INSERT INTO buy_order (buy_order_id, buy_order_uuid, supplier_id, creation_date, user_id, total_cost, status) VALUES (19,'19c218d6-ea05-11eb-863e-dc4a3ee3e76f',9,'2021-07-15',4,336000,'F');
INSERT INTO buy_order (buy_order_id, buy_order_uuid, supplier_id, creation_date, user_id, total_cost, status) VALUES (20,'1c9f7146-ea05-11eb-863e-dc4a3ee3e76f',8,'2021-07-17',8,203000,'F');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed
