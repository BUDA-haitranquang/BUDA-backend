-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: bdsm
-- ------------------------------------------------------
-- Server version	8.0.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `warranty_order`
--

DROP TABLE IF EXISTS `warranty_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `warranty_order` (
  `warranty_order_id` bigint NOT NULL AUTO_INCREMENT,
  `creation_time` datetime DEFAULT NULL,
  `customer_message` varchar(255) DEFAULT NULL,
  `status` int DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `customer_id` bigint DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  `sell_order_id` bigint DEFAULT NULL,
  PRIMARY KEY (`warranty_order_id`),
  KEY `warranty_order_user_id_index` (`user_id`),
  KEY `warranty_order_product_id_index` (`product_id`),
  KEY `FKlalrubaim8c5ulb2j4s47p7v4` (`customer_id`),
  KEY `FKemiuxe1gl33ms9i6fdb2oauuw` (`sell_order_id`),
  CONSTRAINT `FKemiuxe1gl33ms9i6fdb2oauuw` FOREIGN KEY (`sell_order_id`) REFERENCES `sell_order` (`sell_order_id`),
  CONSTRAINT `FKgof95pi3j9w3od0gn5wv8q8wd` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`),
  CONSTRAINT `FKlalrubaim8c5ulb2j4s47p7v4` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `warranty_order`
--

LOCK TABLES `warranty_order` WRITE;
/*!40000 ALTER TABLE `warranty_order` DISABLE KEYS */;
/*!40000 ALTER TABLE `warranty_order` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-01-19 10:00:25
