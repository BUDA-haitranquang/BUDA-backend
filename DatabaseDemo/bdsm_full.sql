-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: bdsm
-- ------------------------------------------------------
-- Server version	8.0.23

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
-- Table structure for table `buy_order`
--

DROP TABLE IF EXISTS `buy_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `buy_order` (
  `buy_order_id` bigint NOT NULL AUTO_INCREMENT,
  `creation_time` datetime DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `total_cost` double NOT NULL,
  `user_id` bigint DEFAULT NULL,
  `supplier_id` bigint DEFAULT NULL,
  PRIMARY KEY (`buy_order_id`),
  KEY `FK88ltngi7h7dpaxp39mmbey9s` (`supplier_id`),
  CONSTRAINT `FK88ltngi7h7dpaxp39mmbey9s` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`supplier_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `buy_order`
--

LOCK TABLES `buy_order` WRITE;
/*!40000 ALTER TABLE `buy_order` DISABLE KEYS */;
/*!40000 ALTER TABLE `buy_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `buy_order_item`
--

DROP TABLE IF EXISTS `buy_order_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `buy_order_item` (
  `buy_order_item_id` bigint NOT NULL AUTO_INCREMENT,
  `creation_time` datetime DEFAULT NULL,
  `price_per_unit` double NOT NULL,
  `quantity` int NOT NULL,
  `supplier_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `buy_order_id` bigint DEFAULT NULL,
  `ingredient_id` bigint DEFAULT NULL,
  PRIMARY KEY (`buy_order_item_id`),
  KEY `FK32250op8ovrywbl2atdcayg8d` (`buy_order_id`),
  KEY `FKq1mhy6rlxhacj77hbd462nry2` (`ingredient_id`),
  CONSTRAINT `FK32250op8ovrywbl2atdcayg8d` FOREIGN KEY (`buy_order_id`) REFERENCES `buy_order` (`buy_order_id`),
  CONSTRAINT `FKq1mhy6rlxhacj77hbd462nry2` FOREIGN KEY (`ingredient_id`) REFERENCES `ingredient` (`ingredient_id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `buy_order_item`
--

LOCK TABLES `buy_order_item` WRITE;
/*!40000 ALTER TABLE `buy_order_item` DISABLE KEYS */;
INSERT INTO `buy_order_item` (`buy_order_item_id`, `creation_time`, `price_per_unit`, `quantity`, `supplier_id`, `user_id`, `buy_order_id`, `ingredient_id`) VALUES (1,'2021-06-15 00:00:00',30000,4,1,3,1,2),(2,'2021-06-15 00:00:00',62000,6,7,3,1,1),(3,'2021-06-16 00:00:00',51000,4,4,6,2,5),(4,'2021-06-18 00:00:00',32000,4,4,8,3,6),(5,'2021-06-19 00:00:00',22000,4,4,9,4,4),(6,'2021-06-20 00:00:00',15000,5,8,7,5,9),(7,'2021-06-20 00:00:00',23000,4,3,7,5,10),(8,'2021-06-21 00:00:00',41000,6,4,5,6,3),(9,'2021-06-22 00:00:00',41000,5,4,8,7,3),(10,'2021-06-23 00:00:00',29000,6,2,10,8,12),(11,'2021-06-24 00:00:00',29000,6,2,12,9,12),(12,'2021-06-25 00:00:00',31000,6,10,9,10,11),(13,'2021-06-27 00:00:00',12000,6,6,15,11,7),(14,'2021-06-29 00:00:00',7000,5,1,5,12,8),(15,'2021-06-29 00:00:00',12000,4,6,5,12,7),(16,'2021-07-01 00:00:00',40000,5,2,13,13,14),(17,'2021-07-02 00:00:00',44000,6,2,6,14,13),(18,'2021-07-05 00:00:00',32000,4,4,14,15,6),(19,'2021-07-06 00:00:00',7000,7,1,4,16,8),(20,'2021-07-10 00:00:00',7000,5,1,10,17,8),(21,'2021-07-12 00:00:00',60000,4,2,9,18,15),(22,'2021-07-15 00:00:00',51000,4,4,15,19,5),(23,'2021-07-15 00:00:00',22000,6,4,15,19,4),(24,'2021-07-17 00:00:00',32000,4,4,4,20,6),(25,'2021-07-17 00:00:00',15000,5,8,4,20,9),(26,'2021-06-15 00:00:00',30000,4,1,3,1,2),(27,'2021-06-15 00:00:00',62000,6,7,3,1,1),(28,'2021-06-16 00:00:00',51000,4,4,6,2,5),(29,'2021-06-18 00:00:00',32000,4,4,8,3,6),(30,'2021-06-19 00:00:00',22000,4,4,9,4,4),(31,'2021-06-20 00:00:00',15000,5,8,7,5,9),(32,'2021-06-20 00:00:00',23000,4,3,7,5,10),(33,'2021-06-21 00:00:00',41000,6,4,5,6,3),(34,'2021-06-22 00:00:00',41000,5,4,8,7,3),(35,'2021-06-23 00:00:00',29000,6,2,10,8,12),(36,'2021-06-24 00:00:00',29000,6,2,12,9,12),(37,'2021-06-25 00:00:00',31000,6,10,9,10,11),(38,'2021-06-27 00:00:00',12000,6,6,15,11,7),(39,'2021-06-29 00:00:00',7000,5,1,5,12,8),(40,'2021-06-29 00:00:00',12000,4,6,5,12,7),(41,'2021-07-01 00:00:00',40000,5,2,13,13,14),(42,'2021-07-02 00:00:00',44000,6,2,6,14,13),(43,'2021-07-05 00:00:00',32000,4,4,14,15,6),(44,'2021-07-06 00:00:00',7000,7,1,4,16,8),(45,'2021-07-10 00:00:00',7000,5,1,10,17,8),(46,'2021-07-12 00:00:00',60000,4,2,9,18,15),(47,'2021-07-15 00:00:00',51000,4,4,15,19,5),(48,'2021-07-15 00:00:00',22000,6,4,15,19,4),(49,'2021-07-17 00:00:00',32000,4,4,4,20,6),(50,'2021-07-17 00:00:00',15000,5,8,4,20,9);
/*!40000 ALTER TABLE `buy_order_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `customer_id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(200) DEFAULT NULL,
  `age_group` varchar(50) DEFAULT 'UNKNOWN',
  `gender` varchar(50) DEFAULT 'UNKNOWN',
  `membership_id` bigint DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `phone_number` varchar(15) DEFAULT NULL,
  `total_spend` double DEFAULT '0',
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` (`customer_id`, `address`, `age_group`, `gender`, `membership_id`, `name`, `phone_number`, `total_spend`, `user_id`) VALUES (1,'21392 Browning Crossing','<18','Female',1,'Davin Renbold','4735520113',240000,NULL),(2,'67571 Dayton Terrace','30-44','Male',NULL,'Ninon Giacobazzi','6382020283',550000,NULL),(3,'36693 Trailsway Circle','>60','Male',2,'Ham Ganley','6837961494',139500,NULL),(4,'100 Dahle Park','<18','Male',NULL,'Edythe Glassman','3325356066',205000,NULL),(5,'2 Fieldstone Point','18-29','Male',NULL,'Agnella O\'Lynn','1538221336',165000,NULL),(6,'6 Petterle Place','45-60','Female',3,'Emmalynne Youtead','6941515680',580000,NULL),(7,'178 Esch Place','45-60','Female',NULL,'Brantley Crenage','7167131346',340000,NULL),(8,'4 Hoard Street','>60','Male',NULL,'Emlynn Flintoffe','8276504791',490000,NULL),(9,'72 Eastwood Parkway','30-44','Male',4,'Cortie Van Leeuwen','5859686981',395000,NULL),(10,'9 Summerview Parkway','30-44','Male',NULL,'Berk Eagland','3934488094',230000,NULL),(11,'5 Londonderry Street','18-29','Female',NULL,'Bonni Hales','1805366420',200000,NULL),(12,'01 Oak Road','18-29','Male',NULL,'Gunter Cansdall','4516744519',140000,NULL),(13,'7 Petterle Drive','>60','Male',5,'Aidan McLice','2758886993',305000,NULL),(14,'5371 American Avenue','>60','Female',NULL,'Pam Dibbin','9556387885',180000,NULL),(15,'02 Talmadge Court','30-44','Female',6,'Tallie Emanson','1819815688',114000,NULL);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `discount`
--

DROP TABLE IF EXISTS `discount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `discount` (
  `discount_id` bigint NOT NULL AUTO_INCREMENT,
  `cash` double DEFAULT '0',
  `cash_limit` double DEFAULT '0',
  `created_time` datetime DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `discount_type` varchar(255) DEFAULT NULL,
  `expiry_time` datetime DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  `order_count` int NOT NULL,
  `percentage` double DEFAULT '0',
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`discount_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discount`
--

LOCK TABLES `discount` WRITE;
/*!40000 ALTER TABLE `discount` DISABLE KEYS */;
INSERT INTO `discount` (`discount_id`, `cash`, `cash_limit`, `created_time`, `description`, `discount_type`, `expiry_time`, `name`, `order_count`, `percentage`, `user_id`) VALUES (1,NULL,20000,'2021-06-15 00:00:00','10% Discount, Max 20000','1','2021-06-20 00:00:00','First',1,0.1,5),(2,NULL,30000,'2021-06-21 00:00:00','10% Discount, Max 30000','1','2021-06-23 00:00:00','Second',0,0.1,9),(3,NULL,50000,'2021-06-25 00:00:00','5% Discount, Max 50000','1','2021-06-28 00:00:00','Third',0,0.05,12),(4,NULL,40000,'2021-06-29 00:00:00','10% Discount, Max 40000','1','2021-07-02 00:00:00','Fourth',0,0.1,15),(5,NULL,20000,'2021-07-04 00:00:00','15% Discount, Max 20000','1','2021-07-08 00:00:00','Fifth',0,0.15,8),(6,NULL,60000,'2021-07-10 00:00:00','5% Discount, Max 60000','1','2021-07-14 00:00:00','Sixth',0,0.05,10),(7,NULL,30000,'2021-07-15 00:00:00','25% Discount, Max 30000','1','2021-07-21 00:00:00','Seventh',0,0.25,6),(8,NULL,NULL,'2021-06-15 00:00:00','No discount',NULL,'9999-12-31 00:00:00','Eigth',0,0,7);
/*!40000 ALTER TABLE `discount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fixed_cost`
--

DROP TABLE IF EXISTS `fixed_cost`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fixed_cost` (
  `fixed_cost_id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(1000) DEFAULT NULL,
  `money_amount` double NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `period` int NOT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`fixed_cost_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fixed_cost`
--

LOCK TABLES `fixed_cost` WRITE;
/*!40000 ALTER TABLE `fixed_cost` DISABLE KEYS */;
INSERT INTO `fixed_cost` (`fixed_cost_id`, `description`, `money_amount`, `name`, `period`, `user_id`) VALUES (1,NULL,200000,'Alpha',30,3),(2,NULL,300000,'Beta',30,7),(3,NULL,350000,'Gamma',30,9),(4,NULL,600000,'Theta',30,12);
/*!40000 ALTER TABLE `fixed_cost` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fixed_cost_bill`
--

DROP TABLE IF EXISTS `fixed_cost_bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fixed_cost_bill` (
  `fixed_cost_bill_id` bigint NOT NULL AUTO_INCREMENT,
  `creation_time` datetime DEFAULT NULL,
  `due_time` datetime DEFAULT NULL,
  `message` varchar(1000) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `total_spend` double NOT NULL,
  `user_id` bigint DEFAULT NULL,
  `fixed_cost_id` bigint DEFAULT NULL,
  PRIMARY KEY (`fixed_cost_bill_id`),
  KEY `FKgesv2ed8fj3upqupcq0jrg8gp` (`fixed_cost_id`),
  CONSTRAINT `FKgesv2ed8fj3upqupcq0jrg8gp` FOREIGN KEY (`fixed_cost_id`) REFERENCES `fixed_cost` (`fixed_cost_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fixed_cost_bill`
--

LOCK TABLES `fixed_cost_bill` WRITE;
/*!40000 ALTER TABLE `fixed_cost_bill` DISABLE KEYS */;
INSERT INTO `fixed_cost_bill` (`fixed_cost_bill_id`, `creation_time`, `due_time`, `message`, `status`, `total_spend`, `user_id`, `fixed_cost_id`) VALUES (1,'2021-06-15 00:00:00','2021-07-15 00:00:00',NULL,'F',350000,4,3),(2,'2021-06-15 00:00:00','2021-07-15 00:00:00',NULL,'F',600000,6,4),(3,'2021-06-15 00:00:00','2021-07-15 00:00:00',NULL,'F',200000,7,1),(4,'2021-06-20 00:00:00','2021-07-20 00:00:00',NULL,'F',300000,9,2);
/*!40000 ALTER TABLE `fixed_cost_bill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ingredient`
--

DROP TABLE IF EXISTS `ingredient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ingredient` (
  `ingredient_id` bigint NOT NULL AUTO_INCREMENT,
  `alert_amount_left` int NOT NULL,
  `amount_left` int NOT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `picture_id` bigint DEFAULT NULL,
  `price` double NOT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`ingredient_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingredient`
--

LOCK TABLES `ingredient` WRITE;
/*!40000 ALTER TABLE `ingredient` DISABLE KEYS */;
INSERT INTO `ingredient` (`ingredient_id`, `alert_amount_left`, `amount_left`, `description`, `name`, `picture_id`, `price`, `user_id`) VALUES (1,0,40,'Meat','Beef',11,62000,5),(2,0,87,'Meat','Chicken',12,30000,7),(3,0,60,'Seafood','Oyster',13,41000,8),(4,0,63,'Condiment','Mayonnaise',14,22000,12),(5,0,41,'Meat','Pork',15,51000,3),(6,0,63,'Condiment','Fish Sauce',16,15000,6),(7,0,65,'Spice','Pepper',17,12000,9),(8,0,44,'Spice','Salt',18,7000,10),(9,0,41,'Condiment','Vinegar',19,15000,11),(10,0,50,'Vegetable','Onion',20,23000,15),(11,0,61,'Bread','Bread',21,31000,4),(12,0,63,'Fruit','Orange',22,29000,13),(13,0,94,'Seafood','Shrimp',23,44000,14),(14,0,70,'Fruit','Kiwi',24,40000,3),(15,0,54,'Seafood','Salmon',25,60000,4);
/*!40000 ALTER TABLE `ingredient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ingredient_left_log`
--

DROP TABLE IF EXISTS `ingredient_left_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ingredient_left_log` (
  `ingredient_left_log_id` bigint NOT NULL AUTO_INCREMENT,
  `amount_left_change` int NOT NULL,
  `creation_time` datetime DEFAULT NULL,
  `message` varchar(1000) DEFAULT NULL,
  `staff_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `ingredient_id` bigint DEFAULT NULL,
  PRIMARY KEY (`ingredient_left_log_id`),
  KEY `FKq31rk23tcy7gia43peu7pfdul` (`ingredient_id`),
  CONSTRAINT `FKq31rk23tcy7gia43peu7pfdul` FOREIGN KEY (`ingredient_id`) REFERENCES `ingredient` (`ingredient_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingredient_left_log`
--

LOCK TABLES `ingredient_left_log` WRITE;
/*!40000 ALTER TABLE `ingredient_left_log` DISABLE KEYS */;
INSERT INTO `ingredient_left_log` (`ingredient_left_log_id`, `amount_left_change`, `creation_time`, `message`, `staff_id`, `user_id`, `ingredient_id`) VALUES (1,4,'2021-06-15 00:00:00','More ingredient',3,3,2),(2,6,'2021-06-15 00:00:00','More ingredient',3,3,1),(3,4,'2021-06-16 00:00:00','More ingredient',6,6,5),(4,4,'2021-06-18 00:00:00','More ingredient',8,8,6),(5,4,'2021-06-19 00:00:00','More ingredient',9,9,4),(6,5,'2021-06-20 00:00:00','More ingredient',7,7,9),(7,4,'2021-06-20 00:00:00','More ingredient',7,7,10),(8,6,'2021-06-21 00:00:00','More ingredient',5,5,3),(9,5,'2021-06-22 00:00:00','More ingredient',8,8,3),(10,6,'2021-06-23 00:00:00','More ingredient',10,10,12),(11,6,'2021-06-24 00:00:00','More ingredient',12,12,12),(12,6,'2021-06-25 00:00:00','More ingredient',9,9,11),(13,6,'2021-06-27 00:00:00','More ingredient',15,15,7),(14,5,'2021-06-29 00:00:00','More ingredient',5,5,8),(15,4,'2021-06-29 00:00:00','More ingredient',5,5,7),(16,5,'2021-07-01 00:00:00','More ingredient',13,13,14),(17,6,'2021-07-02 00:00:00','More ingredient',6,6,13),(18,4,'2021-07-05 00:00:00','More ingredient',14,14,6),(19,7,'2021-07-06 00:00:00','More ingredient',4,4,8),(20,5,'2021-07-10 00:00:00','More ingredient',10,10,8),(21,4,'2021-07-12 00:00:00','More ingredient',9,9,15),(22,4,'2021-07-15 00:00:00','More ingredient',15,15,5),(23,6,'2021-07-15 00:00:00','More ingredient',15,15,4),(24,4,'2021-07-17 00:00:00','More ingredient',4,4,6),(25,5,'2021-07-17 00:00:00','More ingredient',4,4,9);
/*!40000 ALTER TABLE `ingredient_left_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `membership_type`
--

DROP TABLE IF EXISTS `membership_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `membership_type` (
  `membership_type_id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(1000) DEFAULT NULL,
  `membership_name` varchar(255) DEFAULT NULL,
  `minimum_spend` double NOT NULL,
  `user_id` bigint DEFAULT NULL,
  `discount_id` bigint DEFAULT NULL,
  PRIMARY KEY (`membership_type_id`),
  KEY `FKgf0e3nwvc7bk67gn849i4s61h` (`discount_id`),
  CONSTRAINT `FKgf0e3nwvc7bk67gn849i4s61h` FOREIGN KEY (`discount_id`) REFERENCES `discount` (`discount_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `membership_type`
--

LOCK TABLES `membership_type` WRITE;
/*!40000 ALTER TABLE `membership_type` DISABLE KEYS */;
INSERT INTO `membership_type` (`membership_type_id`, `description`, `membership_name`, `minimum_spend`, `user_id`, `discount_id`) VALUES (1,NULL,'Davin Reinbold',240000,5,1),(2,NULL,'Ham Ganley',139500,9,NULL),(3,NULL,'Emmalynne Youtead',580000,15,8),(4,NULL,'Cortie Van Leeuwen',395000,8,NULL),(5,NULL,'Aidan McLice',305000,13,NULL),(6,NULL,'Tallie Emanson',114000,8,NULL);
/*!40000 ALTER TABLE `membership_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `other_cost`
--

DROP TABLE IF EXISTS `other_cost`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `other_cost` (
  `other_cost_id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(2000) DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  `payment_date` datetime DEFAULT NULL,
  `total_cost` double DEFAULT '0',
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`other_cost_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `other_cost`
--

LOCK TABLES `other_cost` WRITE;
/*!40000 ALTER TABLE `other_cost` DISABLE KEYS */;
INSERT INTO `other_cost` (`other_cost_id`, `description`, `name`, `payment_date`, `total_cost`, `user_id`) VALUES (1,'Testing','VAT','2021-06-25 00:00:00',30000,3),(2,'Testing','Rent','2021-06-28 00:00:00',500000,5),(3,'Testing','Repair','2021-06-30 00:00:00',100000,8),(4,'Testing','Fuel','2021-07-03 00:00:00',120000,9),(5,'Testing','Insurance','2021-07-08 00:00:00',200000,12);
/*!40000 ALTER TABLE `other_cost` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `picture`
--

DROP TABLE IF EXISTS `picture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `picture` (
  `picture_id` bigint NOT NULL AUTO_INCREMENT,
  `picture_link` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`picture_id`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `picture`
--

LOCK TABLES `picture` WRITE;
/*!40000 ALTER TABLE `picture` DISABLE KEYS */;
INSERT INTO `picture` (`picture_id`, `picture_link`) VALUES (1,'https://upload.wikimedia.org/wikipedia/commons/f/fe/Viettel_logo_2021.svg'),(2,'https://cdn.tgdd.vn/Files/2015/04/21/634843/cach-dang-ki-cuoc-goi-va-nhan-tin-mien-phi-vinaphone-2.jpg'),(3,'https://i2.wp.com/marketingai.admicro.vn/wp-content/uploads/2018/11/bo-nhan-dien-thuong-hieu-mobifone-min.png?resize=766%2C216&ssl=1'),(4,'https://vnptvinaphone.net.vn/wp-content/uploads/2021/01/logo-vnpt-h%C3%A0-n%E1%BB%8Di.png'),(5,'https://upload.wikimedia.org/wikipedia/vi/thumb/a/a8/Vietnamobile_Logo.svg/1200px-Vietnamobile_Logo.svg.png'),(6,'https://upload.wikimedia.org/wikipedia/vi/7/7a/BeeLine_logo.png'),(7,'https://www.fpt.com.vn/Content/home/images/icon/ic-logo.png'),(8,'https://cmc.com.vn/main/imgs/logo.svg'),(9,'https://viettelco.net/wp-content/uploads/2020/01/Logo-Viettelco.png'),(10,'https://netnam.com/o/netnam/images/logo.png'),(11,'https://www.foodsafetynews.com/files/2020/06/raw-sirloin-steak-beef.jpg'),(12,'https://i2.wp.com/hygienicmeat.com/wp-content/uploads/2020/07/s735965455334319150_p6_i1_w2048.jpeg'),(13,'https://www.collinsdictionary.com/images/full/oyster_245235925.jpg'),(14,'https://static.julinse.com/i/a250031cfd4543cd-1024x684.jpg'),(15,'https://passiondelivery.com/pub/media/catalog/product/cache/7e6e59e80a69ca81b40190dbfa9e211f/5/7/57._pork_loin_skin-off.jpg'),(16,'https://media.cooky.vn/images/blog-2016/cach-phan-biet-nuoc-mam-truyen-thong-va-nuoc-cham-cong-nghiep%201.jpg'),(17,'http://kolala.com.vn/upload/public/TI%C3%AAu/where-does-black-pepper-come-from.jpg'),(18,'https://cdn-prod.medicalnewstoday.com/content/images/articles/322/322745/salt-shaker.jpg'),(19,'https://parenting.firstcry.ae/memories/getMyMemoryImages/2018/04/528721780-H.jpg'),(20,'https://bizweb.dktcdn.net/100/408/264/products/5116995hanh-tay-onion-000000005272-jpeg.jpg?v=1603792333943'),(21,'https://www.escoffieronline.com/wp-content/uploads/2013/04/iStock-995038782-small.jpg'),(22,'https://st.depositphotos.com/1049691/4267/i/950/depositphotos_42673487-stock-photo-fresh-orange.jpg'),(23,'http://suckhoedoisong.vn/Images/thutrang/2016/04/25/tanghoocmon_1.jpg'),(24,'https://wallpapercave.com/wp/wp2691685.jpg'),(25,'https://images.thefishsite.com/fish%2Farticles%2Fprocessing%2Fsalmon-fillet.jpeg'),(26,'https://hips.hearstapps.com/vidthumb/images/grilled-shrimp-horizontal-1553275047.jpg'),(27,'https://www.thespruceeats.com/thmb/RuiS8PlPaUkJFbrH8skujr90Q8A=/1500x1000/filters:fill(auto,1)/terris-crispy-fried-chicken-legs-3056879-10_preview-5b05ec40a474be00362260d7.jpeg'),(28,'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ0Wi_Yf-V1yc_gruFfIvs4wJtvjngDsRG-kg&usqp=CAU'),(29,'https://armyhaus.com/wp-content/uploads/2020/12/marinated-top-round-steak-3060302-hero-02-ed071d5d7e584bea82857112aa734a94.jpg'),(30,'https://islandlifenc.com/wp-content/uploads/2021/01/How-to-do-an-Oyster-Roast.jpg'),(31,'https://www.foxandbriar.com/wp-content/uploads/2020/09/Garlic-Bread-7-of-10.jpg'),(32,'http://cdn.shopify.com/s/files/1/2956/7160/articles/sashimi_ca_hoi_tuoi.jpg?v=1536459593'),(33,'https://kenh14cdn.com/2019/7/16/2-15632570826061657541652.jpg'),(34,'https://images.food52.com/9HR-m4RBTbuZpZah01hA4Jgco7s=/2016x1344/filters:format(webp)/a6022657-cd16-47ec-8d78-63f717d869f7--seafood_stew.jpg'),(35,'https://www.simplyrecipes.com/thmb/63rPxOV5ooiVaaczVVCGTrGWi_w=/1600x1067/filters:no_upscale():max_bytes(150000):strip_icc()/__opt__aboutcom__coeus__resources__content_migration__simply_recipes__uploa'),(36,'https://imagesvc.meredithcorp.io/v3/mm/image?url=https%3A%2F%2Fimg1.cookinglight.timeinc.net%2Fsites%2Fdefault%2Ffiles%2Fstyles%2Fmedium_2x%2Fpublic%2Fimage%2F2016%2F09%2Fmain%2F1610p130-smoky-pan-sea'),(37,'https://images.ctfassets.net/3s5io6mnxfqz/VTFplkmqQemCmaQgYEeGU/a70e0c14de212de3444179fee2223121/image1.jpg?fm=jpg&w=900&fl=progressive'),(38,'https://previews.123rf.com/images/photomaru/photomaru1011/photomaru101100054/8297195-orange-juice-and-slices-of-orange-isolated-on-white.jpg'),(39,'https://recipe52.com/wp-content/uploads/2020/01/Kiwi-Juice-recipe-6.jpg'),(40,'https://www.onceuponachef.com/images/2014/03/Easy-Garlic-Butter-Shrimp-1.jpg'),(41,'https://www.slideteam.net/media/catalog/product/cache/960x720/m/e/merchandise_buying_6_month_plan_ppt_show_slideshow_Slide01.jpg'),(42,'https://mir-s3-cdn-cf.behance.net/project_modules/1400/b5594273128611.5bff152c43fec.jpg'),(43,'https://docs.oracle.com/cd/E75762_01/assortplan/pdf/141/html/ug/img/bplan_bm_rev_receiptpl_look.png'),(44,'https://images.slideplayer.com/13/3902380/slides/slide_8.jpg'),(45,'https://image.shutterstock.com/image-vector/user-login-authenticate-icon-human-260nw-1365533969.jpg'),(46,'https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/2048px-User_icon_2.svg.png'),(47,'https://upload.wikimedia.org/wikipedia/commons/thumb/c/ce/User-info.svg/768px-User-info.svg.png'),(48,'http://simpleicon.com/wp-content/uploads/user1.png'),(49,'https://www.kindpng.com/picc/m/24-248253_user-profile-default-image-png-clipart-png-download.png'),(50,'https://image.shutterstock.com/image-vector/default-avatar-profile-icon-vector-260nw-1725655669.jpg'),(51,'https://cdn-icons-png.flaticon.com/512/149/149071.png'),(52,'https://upload.wikimedia.org/wikipedia/commons/9/99/Sample_User_Icon.png'),(53,'https://cdn.icon-icons.com/icons2/2506/PNG/512/user_icon_150670.png'),(54,'https://icon-library.com/images/google-user-icon/google-user-icon-11.jpg'),(55,'https://sothis.es/wp-content/plugins/all-in-one-seo-pack/images/default-user-image.png'),(56,'https://cdn.pixabay.com/photo/2020/07/01/12/58/icon-5359553_1280.png'),(57,'https://t4.ftcdn.net/jpg/02/29/75/83/360_F_229758328_7x8jwCwjtBMmC6rgFzLFhZoEpLobB6L8.jpg'),(58,'https://thumbs.dreamstime.com/b/default-avatar-profile-icon-vector-social-media-user-image-182145777.jpg'),(59,'https://cdn5.vectorstock.com/i/1000x1000/04/09/user-icon-vector-5770409.jpg');
/*!40000 ALTER TABLE `picture` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plan`
--

DROP TABLE IF EXISTS `plan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `plan` (
  `plan_id` bigint NOT NULL AUTO_INCREMENT,
  `price` double NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `duration` int NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `picture_id` bigint DEFAULT NULL,
  PRIMARY KEY (`plan_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plan`
--

LOCK TABLES `plan` WRITE;
/*!40000 ALTER TABLE `plan` DISABLE KEYS */;
INSERT INTO `plan` (`plan_id`, `price`, `description`, `duration`, `name`, `picture_id`) VALUES (1,200000,NULL,30,'Basic',41),(2,350000,NULL,30,'Premium',42),(3,250000,NULL,30,'No ads',43),(4,600000,NULL,30,'Platinum',44);
/*!40000 ALTER TABLE `plan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `product_id` bigint NOT NULL AUTO_INCREMENT,
  `alert_amount` int NOT NULL,
  `amount_left` int NOT NULL,
  `cost_per_unit` double NOT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `picture_id` bigint DEFAULT NULL,
  `selling_price` double NOT NULL,
  `user_id` bigint DEFAULT NULL,
  `product_group_id` bigint DEFAULT NULL,
  PRIMARY KEY (`product_id`),
  KEY `FKd1puiblqvkggoc63q7c3ux5x6` (`product_group_id`),
  CONSTRAINT `FKd1puiblqvkggoc63q7c3ux5x6` FOREIGN KEY (`product_group_id`) REFERENCES `product_group` (`product_group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` (`product_id`, `alert_amount`, `amount_left`, `cost_per_unit`, `description`, `name`, `picture_id`, `selling_price`, `user_id`, `product_group_id`) VALUES (1,0,25,176000,'Grilled Shrimp','Grilled Shrimp',26,190000,3,2),(2,0,27,97000,'Fried Chicken','Fried Chicken',27,115000,5,1),(3,0,16,114000,'Pork Barbecue','Pork Barbecue',28,135000,7,2),(4,0,20,143000,'Beef Steak','Beef Steak',29,160000,9,2),(5,0,23,89000,'Roast Oyster','Roast Oyster',30,100000,11,2),(6,0,41,62000,'Garlic Butter Bread','Garlic Butter Bread',31,80000,13,1),(7,0,30,120000,'Salmon Sashimi','Salmon Sashimi',32,140000,15,1),(8,0,22,66000,'Spring Roll','Spring Roll',33,85000,4,1),(9,0,18,56000,'Seafood Soup','Seafood Soup',34,70000,6,1),(10,0,19,83000,'Chicken Orzo','Chicken Orzo',35,100000,8,2),(11,0,21,82000,'Pan-seared Chicken Breast','Pan-seared Chicken Breast',36,100000,10,2),(12,0,18,186000,'Beef Wellington','Beef Wellington',37,205000,12,2),(13,0,27,29000,'Orange Juice','Orange Juice',38,40000,14,3),(14,0,30,40000,'Kiwi Juice','Kiwi Juice',39,55000,3,3),(15,0,33,103000,'Shrimp and Butter','Shrimp and Butter',40,125000,4,2);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_component`
--

DROP TABLE IF EXISTS `product_component`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_component` (
  `product_component_id` bigint NOT NULL AUTO_INCREMENT,
  `required_quantity` bigint DEFAULT NULL,
  `total_cost` double DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `ingredient_id` bigint DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  PRIMARY KEY (`product_component_id`),
  KEY `FK99pdjvmou23eb10r2uj3x34p7` (`ingredient_id`),
  KEY `FK59ur8bog2ssxylw0qo28aumci` (`product_id`),
  CONSTRAINT `FK59ur8bog2ssxylw0qo28aumci` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`),
  CONSTRAINT `FK99pdjvmou23eb10r2uj3x34p7` FOREIGN KEY (`ingredient_id`) REFERENCES `ingredient` (`ingredient_id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_component`
--

LOCK TABLES `product_component` WRITE;
/*!40000 ALTER TABLE `product_component` DISABLE KEYS */;
INSERT INTO `product_component` (`product_component_id`, `required_quantity`, `total_cost`, `user_id`, `ingredient_id`, `product_id`) VALUES (1,2,176000,14,13,1),(2,3,90000,7,2,2),(3,1,7000,10,8,2),(4,2,102000,3,5,3),(5,1,12000,9,7,3),(6,2,124000,5,1,4),(7,1,12000,9,7,4),(8,1,7000,10,8,4),(9,2,82000,8,3,5),(10,1,7000,10,8,5),(11,2,62000,4,11,6),(12,2,120000,4,15,7),(13,1,51000,3,5,8),(14,1,15000,6,6,8),(15,1,44000,14,13,9),(16,1,12000,9,7,9),(17,2,60000,7,2,10),(18,1,23000,15,10,10),(19,2,60000,7,2,11),(20,1,22000,12,4,11),(21,2,124000,5,1,12),(22,2,62000,4,11,12),(23,1,29000,13,12,13),(24,1,40000,3,14,14),(25,2,88000,14,13,15),(26,1,15000,11,9,15),(27,2,176000,14,13,1),(28,3,90000,7,2,2),(29,1,7000,10,8,2),(30,2,102000,3,5,3),(31,1,12000,9,7,3),(32,2,124000,5,1,4),(33,1,12000,9,7,4),(34,1,7000,10,8,4),(35,2,82000,8,3,5),(36,1,7000,10,8,5),(37,2,62000,4,11,6),(38,2,120000,4,15,7),(39,1,51000,3,5,8),(40,1,15000,6,6,8),(41,1,44000,14,13,9),(42,1,12000,9,7,9),(43,2,60000,7,2,10),(44,1,23000,15,10,10),(45,2,60000,7,2,11),(46,1,22000,12,4,11),(47,2,124000,5,1,12),(48,2,62000,4,11,12),(49,1,29000,13,12,13),(50,1,40000,3,14,14),(51,2,88000,14,13,15),(52,1,15000,11,9,15);
/*!40000 ALTER TABLE `product_component` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_group`
--

DROP TABLE IF EXISTS `product_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_group` (
  `product_group_id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`product_group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_group`
--

LOCK TABLES `product_group` WRITE;
/*!40000 ALTER TABLE `product_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_left_log`
--

DROP TABLE IF EXISTS `product_left_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_left_log` (
  `product_left_log_id` bigint NOT NULL AUTO_INCREMENT,
  `amount_left_change` int NOT NULL,
  `creation_time` datetime DEFAULT NULL,
  `message` varchar(1000) DEFAULT NULL,
  `staff_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  PRIMARY KEY (`product_left_log_id`),
  KEY `FK5wnlsgf0spxk24pwp2s2ydsno` (`product_id`),
  CONSTRAINT `FK5wnlsgf0spxk24pwp2s2ydsno` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_left_log`
--

LOCK TABLES `product_left_log` WRITE;
/*!40000 ALTER TABLE `product_left_log` DISABLE KEYS */;
INSERT INTO `product_left_log` (`product_left_log_id`, `amount_left_change`, `creation_time`, `message`, `staff_id`, `user_id`, `product_id`) VALUES (1,-1,'2021-06-15 00:00:00','New order',5,5,1),(2,-1,'2021-06-15 00:00:00','New order',5,5,9),(3,-2,'2021-06-16 00:00:00','New order',8,8,3),(4,-1,'2021-06-20 00:00:00','New order',9,9,2),(5,-1,'2021-06-20 00:00:00','New order',9,9,13),(6,-1,'2021-06-21 00:00:00','New order',10,10,12),(7,-1,'2021-06-22 00:00:00','New order',12,12,15),(8,-1,'2021-06-22 00:00:00','New order',12,12,13),(9,-2,'2021-06-23 00:00:00','New order',14,14,5),(10,-1,'2021-06-26 00:00:00','New order',3,3,10),(11,-1,'2021-06-26 00:00:00','New order',3,3,6),(12,-2,'2021-06-28 00:00:00','New order',8,8,8),(13,-1,'2021-06-29 00:00:00','New order',9,9,11),(14,-1,'2021-06-29 00:00:00','New order',9,9,14),(15,-2,'2021-06-30 00:00:00','New order',7,7,2),(16,-1,'2021-07-02 00:00:00','New order',6,6,4),(17,-1,'2021-07-02 00:00:00','New order',6,6,6),(18,-2,'2021-07-05 00:00:00','New order',4,4,15),(19,-1,'2021-07-06 00:00:00','New order',15,15,10),(20,-1,'2021-07-06 00:00:00','New order',15,15,11),(21,-2,'2021-07-07 00:00:00','New order',13,13,9),(22,-1,'2021-07-08 00:00:00','New order',11,11,1),(23,-1,'2021-07-08 00:00:00','New order',11,11,2),(24,-2,'2021-07-09 00:00:00','New order',6,6,7),(25,-1,'2021-07-10 00:00:00','New order',10,10,5),(26,-1,'2021-07-10 00:00:00','New order',10,10,6),(27,-2,'2021-07-12 00:00:00','New order',12,12,1),(28,-1,'2021-07-13 00:00:00','New order',14,14,8),(29,-1,'2021-07-15 00:00:00','New order',14,14,13),(30,-2,'2021-07-15 00:00:00','New order',8,8,4);
/*!40000 ALTER TABLE `product_left_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchase`
--

DROP TABLE IF EXISTS `purchase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `purchase` (
  `purchase_id` bigint NOT NULL AUTO_INCREMENT,
  `creation_time` datetime DEFAULT NULL,
  `expiry_date` datetime DEFAULT NULL,
  `message` varchar(1000) DEFAULT NULL,
  `total_cost` double DEFAULT '0',
  `plan_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`purchase_id`),
  KEY `FKgfjsk9koc95lsw8hbvyw76atf` (`plan_id`),
  KEY `FK86i0stm7cqsglqptdvjij1k3m` (`user_id`),
  CONSTRAINT `FK86i0stm7cqsglqptdvjij1k3m` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FKgfjsk9koc95lsw8hbvyw76atf` FOREIGN KEY (`plan_id`) REFERENCES `plan` (`plan_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase`
--

LOCK TABLES `purchase` WRITE;
/*!40000 ALTER TABLE `purchase` DISABLE KEYS */;
INSERT INTO `purchase` (`purchase_id`, `creation_time`, `expiry_date`, `message`, `total_cost`, `plan_id`, `user_id`) VALUES (1,'2021-06-15 00:00:00','2021-08-15 00:00:00','New purchase',0,1,8),(2,'2021-06-16 00:00:00','2021-07-16 00:00:00','New purchase',0,3,14),(3,'2021-06-18 00:00:00','2021-07-18 00:00:00','New purchase',0,3,15),(4,'2021-06-20 00:00:00','2021-12-20 00:00:00','New purchase',0,4,10),(5,'2021-06-21 00:00:00','2021-09-21 00:00:00','New purchase',0,2,7),(6,'2021-06-22 00:00:00','2021-08-22 00:00:00','New purchase',0,1,5),(7,'2021-06-25 00:00:00','2021-12-25 00:00:00','New purchase',0,4,9);
/*!40000 ALTER TABLE `purchase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salary_log`
--

DROP TABLE IF EXISTS `salary_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `salary_log` (
  `salary_log_id` bigint NOT NULL AUTO_INCREMENT,
  `creation_time` datetime DEFAULT NULL,
  `salary` double NOT NULL,
  `staff_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`salary_log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salary_log`
--

LOCK TABLES `salary_log` WRITE;
/*!40000 ALTER TABLE `salary_log` DISABLE KEYS */;
INSERT INTO `salary_log` (`salary_log_id`, `creation_time`, `salary`, `staff_id`, `user_id`) VALUES (1,'2021-07-02 00:00:00',15000000,1,15),(2,'2021-07-02 00:00:00',15000000,2,14),(3,'2021-07-06 00:00:00',7000000,3,13),(4,'2021-07-06 00:00:00',7000000,4,12),(5,'2021-07-06 00:00:00',6000000,5,11),(6,'2021-07-06 00:00:00',6000000,6,10),(7,'2021-07-06 00:00:00',6000000,7,9),(8,'2021-07-06 00:00:00',6000000,8,8),(9,'2021-07-06 00:00:00',6000000,9,7),(10,'2021-07-06 00:00:00',6000000,10,5),(11,'2021-07-06 00:00:00',6000000,11,6),(12,'2021-07-06 00:00:00',6000000,12,3),(13,'2021-07-06 00:00:00',6000000,13,4),(14,'2021-07-06 00:00:00',6000000,14,1),(15,'2021-07-06 00:00:00',6000000,15,2),(16,'2021-07-02 00:00:00',15000000,1,15),(17,'2021-07-02 00:00:00',15000000,2,14),(18,'2021-07-06 00:00:00',7000000,3,13),(19,'2021-07-06 00:00:00',7000000,4,12),(20,'2021-07-06 00:00:00',6000000,5,11),(21,'2021-07-06 00:00:00',6000000,6,10),(22,'2021-07-06 00:00:00',6000000,7,9),(23,'2021-07-06 00:00:00',6000000,8,8),(24,'2021-07-06 00:00:00',6000000,9,7),(25,'2021-07-06 00:00:00',6000000,10,5),(26,'2021-07-06 00:00:00',6000000,11,6),(27,'2021-07-06 00:00:00',6000000,12,3),(28,'2021-07-06 00:00:00',6000000,13,4),(29,'2021-07-06 00:00:00',6000000,14,1),(30,'2021-07-06 00:00:00',6000000,15,2);
/*!40000 ALTER TABLE `salary_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sell_order`
--

DROP TABLE IF EXISTS `sell_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sell_order` (
  `sell_order_id` bigint NOT NULL AUTO_INCREMENT,
  `actual_discount_cash` double DEFAULT '0',
  `actual_discount_percentage` double DEFAULT '0',
  `age_group` varchar(255) DEFAULT NULL,
  `creation_time` datetime DEFAULT NULL,
  `customer_message` varchar(1000) DEFAULT NULL,
  `final_cost` double NOT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `real_cost` double NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `customer_id` bigint DEFAULT NULL,
  `discount_id` bigint DEFAULT NULL,
  PRIMARY KEY (`sell_order_id`),
  KEY `FKqwrurng4dlomoi49goxtuoqtk` (`customer_id`),
  KEY `FKjdk02h3vt4x77pkfcykvtdhqp` (`discount_id`),
  CONSTRAINT `FKjdk02h3vt4x77pkfcykvtdhqp` FOREIGN KEY (`discount_id`) REFERENCES `discount` (`discount_id`),
  CONSTRAINT `FKqwrurng4dlomoi49goxtuoqtk` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sell_order`
--

LOCK TABLES `sell_order` WRITE;
/*!40000 ALTER TABLE `sell_order` DISABLE KEYS */;
INSERT INTO `sell_order` (`sell_order_id`, `actual_discount_cash`, `actual_discount_percentage`, `age_group`, `creation_time`, `customer_message`, `final_cost`, `gender`, `real_cost`, `status`, `user_id`, `customer_id`, `discount_id`) VALUES (1,20000,0.08,'<18','2021-06-15 00:00:00','Good',240000,'Female',260000,'F',5,1,1),(2,0,0,'30-44','2021-06-16 00:00:00','Not bad',270000,'Male',270000,'F',7,2,8),(3,0,0,'>60','2021-06-20 00:00:00','Excellent',139500,'Male',155000,'F',9,3,NULL),(4,0,0,'<18','2021-06-21 00:00:00','Fantastic',205000,'Male',205000,'F',7,4,8),(5,0,0,'18-29','2021-06-22 00:00:00','Very good',165000,'Male',165000,'F',7,5,8),(6,0,0,'45-60','2021-06-23 00:00:00','Bad',200000,'Female',200000,'F',7,6,8),(7,0,0,'45-60','2021-06-26 00:00:00','Nice',90000,'Female',90000,'F',7,7,8),(8,0,0,'>60','2021-06-28 00:00:00','Lovely',170000,'Male',170000,'F',7,8,8),(9,0,0,'30-44','2021-06-29 00:00:00','Stunning',155000,'Male',155000,'F',9,9,NULL),(10,0,0,'30-44','2021-06-30 00:00:00','Gorgeous',230000,'Male',230000,'F',7,10,8),(11,0,0,'30-44','2021-07-02 00:00:00','Terrific',240000,'Male',240000,'F',6,9,NULL),(12,0,0,'45-60','2021-07-05 00:00:00','Horrible',250000,'Female',250000,'F',7,7,8),(13,0,0,'18-29','2021-07-06 00:00:00','Wonderful',200000,'Female',200000,'F',7,11,8),(14,0,0,'18-29','2021-07-07 00:00:00','Perfect',140000,'Male',140000,'F',7,12,8),(15,0,0,'>60','2021-07-08 00:00:00','Breathtaking',305000,'Male',305000,'F',10,13,NULL),(16,0,0,'30-44','2021-07-09 00:00:00','Outstanding',280000,'Male',280000,'F',7,2,8),(17,0,0,'>60','2021-07-10 00:00:00','Exceptional',180000,'Female',180000,'F',7,14,8),(18,0,0,'45-60','2021-07-12 00:00:00','Amazing',380000,'Female',380000,'F',12,6,NULL),(19,0,0,'30-44','2021-07-13 00:00:00','Spectacular',114000,'Female',114000,'F',5,15,NULL),(20,0,0,'>60','2021-07-15 00:00:00','Phenomenal',320000,'Male',320000,'F',7,8,8);
/*!40000 ALTER TABLE `sell_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sell_order_item`
--

DROP TABLE IF EXISTS `sell_order_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sell_order_item` (
  `sell_order_item_id` bigint NOT NULL AUTO_INCREMENT,
  `actual_total_sale` double NOT NULL,
  `age_group` varchar(255) DEFAULT NULL,
  `cost_per_unit` double NOT NULL,
  `creation_time` datetime DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `price_per_unit` double NOT NULL,
  `quantity` int NOT NULL,
  `user_id` bigint DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  `sell_order_id` bigint DEFAULT NULL,
  PRIMARY KEY (`sell_order_item_id`),
  KEY `FKlf0yd32g5warxxm5tyjn9cgbu` (`product_id`),
  KEY `FK7k048u5isvn7dnrrsq9s55d0a` (`sell_order_id`),
  CONSTRAINT `FK7k048u5isvn7dnrrsq9s55d0a` FOREIGN KEY (`sell_order_id`) REFERENCES `sell_order` (`sell_order_id`),
  CONSTRAINT `FKlf0yd32g5warxxm5tyjn9cgbu` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sell_order_item`
--

LOCK TABLES `sell_order_item` WRITE;
/*!40000 ALTER TABLE `sell_order_item` DISABLE KEYS */;
INSERT INTO `sell_order_item` (`sell_order_item_id`, `actual_total_sale`, `age_group`, `cost_per_unit`, `creation_time`, `gender`, `price_per_unit`, `quantity`, `user_id`, `product_id`, `sell_order_id`) VALUES (1,190000,'<18',176000,'2021-06-15 00:00:00','Female',190000,1,5,1,1),(2,70000,'<18',56000,'2021-06-15 00:00:00','Female',70000,1,5,9,1),(3,270000,'30-44',114000,'2021-06-16 00:00:00','Male',135000,2,8,3,2),(4,115000,'>60',97000,'2021-06-20 00:00:00','Male',115000,1,9,2,3),(5,40000,'>60',29000,'2021-06-20 00:00:00','Male',40000,1,9,13,3),(6,205000,'<18',186000,'2021-06-21 00:00:00','Male',205000,1,10,12,4),(7,125000,'18-29',103000,'2021-06-22 00:00:00','Male',125000,1,12,15,5),(8,40000,'18-29',29000,'2021-06-22 00:00:00','Male',40000,1,12,13,5),(9,200000,'45-60',89000,'2021-06-23 00:00:00','Female',100000,2,14,5,6),(10,10000,'45-60',83000,'2021-06-26 00:00:00','Female',100000,1,3,10,7),(11,80000,'45-60',62000,'2021-06-26 00:00:00','Female',80000,1,3,6,7),(12,170000,'>60',66000,'2021-06-28 00:00:00','Male',85000,2,8,8,8),(13,100000,'30-44',82000,'2021-06-29 00:00:00','Male',100000,1,9,11,9),(14,55000,'30-44',40000,'2021-06-29 00:00:00','Male',55000,1,9,14,9),(15,230000,'30-44',97000,'2021-06-30 00:00:00','Male',115000,2,7,2,10),(16,160000,'30-44',143000,'2021-07-02 00:00:00','Male',160000,1,6,4,11),(17,80000,'30-44',62000,'2021-07-02 00:00:00','Male',80000,1,6,6,11),(18,250000,'45-60',103000,'2021-07-05 00:00:00','Female',125000,2,4,15,12),(19,100000,'18-29',83000,'2021-07-06 00:00:00','Female',100000,1,15,10,13),(20,100000,'18-29',82000,'2021-07-06 00:00:00','Female',100000,1,15,11,13),(21,140000,'18-29',56000,'2021-07-07 00:00:00','Male',70000,2,13,9,14),(22,190000,'>60',176000,'2021-07-08 00:00:00','Male',190000,1,11,1,15),(23,115000,'>60',97000,'2021-07-08 00:00:00','Male',115000,1,11,2,15),(24,280000,'30-44',120000,'2021-07-09 00:00:00','Male',140000,2,6,7,16),(25,100000,'>60',89000,'2021-07-10 00:00:00','Female',100000,1,10,5,17),(26,80000,'>60',62000,'2021-07-10 00:00:00','Female',80000,1,10,6,17),(27,380000,'45-60',176000,'2021-07-12 00:00:00','Female',190000,2,12,1,18),(28,85000,'30-44',66000,'2021-07-13 00:00:00','Female',85000,1,14,8,19),(29,29000,'30-44',176000,'2021-07-13 00:00:00','Female',40000,1,14,13,19),(30,320000,'>60',143000,'2021-07-15 00:00:00','Male',160000,2,8,4,20),(31,190000,'<18',176000,'2021-06-15 00:00:00','Female',190000,1,5,1,1),(32,70000,'<18',56000,'2021-06-15 00:00:00','Female',70000,1,5,9,1),(33,270000,'30-44',114000,'2021-06-16 00:00:00','Male',135000,2,8,3,2),(34,115000,'>60',97000,'2021-06-20 00:00:00','Male',115000,1,9,2,3),(35,40000,'>60',29000,'2021-06-20 00:00:00','Male',40000,1,9,13,3),(36,205000,'<18',186000,'2021-06-21 00:00:00','Male',205000,1,10,12,4),(37,125000,'18-29',103000,'2021-06-22 00:00:00','Male',125000,1,12,15,5),(38,40000,'18-29',29000,'2021-06-22 00:00:00','Male',40000,1,12,13,5),(39,200000,'45-60',89000,'2021-06-23 00:00:00','Female',100000,2,14,5,6),(40,10000,'45-60',83000,'2021-06-26 00:00:00','Female',100000,1,3,10,7),(41,80000,'45-60',62000,'2021-06-26 00:00:00','Female',80000,1,3,6,7),(42,170000,'>60',66000,'2021-06-28 00:00:00','Male',85000,2,8,8,8),(43,100000,'30-44',82000,'2021-06-29 00:00:00','Male',100000,1,9,11,9),(44,55000,'30-44',40000,'2021-06-29 00:00:00','Male',55000,1,9,14,9),(45,230000,'30-44',97000,'2021-06-30 00:00:00','Male',115000,2,7,2,10),(46,160000,'30-44',143000,'2021-07-02 00:00:00','Male',160000,1,6,4,11),(47,80000,'30-44',62000,'2021-07-02 00:00:00','Male',80000,1,6,6,11),(48,250000,'45-60',103000,'2021-07-05 00:00:00','Female',125000,2,4,15,12),(49,100000,'18-29',83000,'2021-07-06 00:00:00','Female',100000,1,15,10,13),(50,100000,'18-29',82000,'2021-07-06 00:00:00','Female',100000,1,15,11,13),(51,140000,'18-29',56000,'2021-07-07 00:00:00','Male',70000,2,13,9,14),(52,190000,'>60',176000,'2021-07-08 00:00:00','Male',190000,1,11,1,15),(53,115000,'>60',97000,'2021-07-08 00:00:00','Male',115000,1,11,2,15),(54,280000,'30-44',120000,'2021-07-09 00:00:00','Male',140000,2,6,7,16),(55,100000,'>60',89000,'2021-07-10 00:00:00','Female',100000,1,10,5,17),(56,80000,'>60',62000,'2021-07-10 00:00:00','Female',80000,1,10,6,17),(57,380000,'45-60',176000,'2021-07-12 00:00:00','Female',190000,2,12,1,18),(58,85000,'30-44',66000,'2021-07-13 00:00:00','Female',85000,1,14,8,19),(59,29000,'30-44',176000,'2021-07-13 00:00:00','Female',40000,1,14,13,19),(60,320000,'>60',143000,'2021-07-15 00:00:00','Male',160000,2,8,4,20);
/*!40000 ALTER TABLE `sell_order_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `staff` (
  `staff_id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone_number` varchar(15) DEFAULT NULL,
  `salary` double DEFAULT '0',
  `staff_position` varchar(255) DEFAULT NULL,
  `staff_uuid` varchar(36) DEFAULT (uuid()),
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`staff_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` (`staff_id`, `address`, `name`, `password`, `phone_number`, `salary`, `staff_position`, `staff_uuid`, `user_id`) VALUES (1,'6155 Thompson Point','Miof mela Manders','53870F79A73F040FB5075E1A070F6C8BB578D7C3ACCC84297ED340F2EBB1895E','4976660078',0,'Manager','eccc05e1-ea06-11eb-863e-dc4a3ee3e76f',1),(2,'3140 Roxbury Center','Jeremia Prys','A62DD59B6C270596EE6F4BCE63892C0E583EA475D9BD89B73527D91E0CCF1D0D','5405339203',0,'Manager','f208044a-ea06-11eb-863e-dc4a3ee3e76f',2),(3,'85763 Lillian Terrace','Karil Sustin','5F476302CECE854EB7217F00ED97EF3C02D22A48794AC38A905BEFEADA90E4D5','1238745360',0,'Employee','f52f6b51-ea06-11eb-863e-dc4a3ee3e76f',3),(4,'21 Crescent Oaks Pass','Dyanna Ellverston','1175F1F1EBCEB579966A40F71855F37973BE70D0ECDCBF5952974E15B83C89EB','8051757923',0,'Employee','f839722d-ea06-11eb-863e-dc4a3ee3e76f',4),(5,'3320 Anhalt Junction','Niven Taffley','33467B3F0E19B38D2B704CE5D37B73CAF7E5667DD6CCA21AD8F30832DD378EF7','6818333918',0,'Employee','fbcb75c2-ea06-11eb-863e-dc4a3ee3e76f',5),(6,'7073 Nelson Pass','Ronalda Simants','5B56337D19D3DC40C6FAED8933BA0B446264F36A8BA82A0FDB90DA3B710117F2','6209164749',0,'Employee','ff47cd47-ea06-11eb-863e-dc4a3ee3e76f',6),(7,'16 Sunbrook Park','Kerry Limer','F45B80AC0CBF253A1B9BFF4A4A050C858FA87D7AB3D356D6218C1088736F4194','7384112392',0,'Employee','0244ee3a-ea07-11eb-863e-dc4a3ee3e76f',7),(8,'7 Homewood Center','Jermayne Hamlen','61DFE3BA7C7D2F0572D0C01C1BC6252ECC20065B58002FDDFBFC837599115A91','7913435030',0,'Employee','05dd128b-ea07-11eb-863e-dc4a3ee3e76f',8),(9,'1 Clyde Gallagher Parkway','Walther Holsey','CFE1A29FA677B2EE01AFF3DA1577C5B3F332746EBA35C927CC886060CD53B8B2','7212072043',0,'Employee','0a526dfb-ea07-11eb-863e-dc4a3ee3e76f',9),(10,'9 Basil Pass','Gerhard Jurczik','0CD47DD591AFE89ACC41512B783488718347697C356103E36A8B652D572A9092','6752449848',0,'Employee','0d45df6a-ea07-11eb-863e-dc4a3ee3e76f',10),(11,'7410 Troy Circle','Jerry Dixon','2B3E953694F66D9F5809F3BD495B4058C3D189BA8E34DFB0C67136E09035E2D7','4214045127',0,'Employee','10692f1e-ea07-11eb-863e-dc4a3ee3e76f',11),(12,'47 Express Point','Lucias Sorsby','452A0B04DE73FBCAB363FC9562B64D94440933D3D4A9CB7DA722A1D49857AD12','8897078810',0,'Employee','13a97e3e-ea07-11eb-863e-dc4a3ee3e76f',12),(13,'4 Lotheville Point','Raynor Tulk','5B5CFA81A8FAC140C00768BF1F2B6A0F753DFEF8708EDAE55F16693142466D11','3285377975',0,'Employee','168d3223-ea07-11eb-863e-dc4a3ee3e76f',13),(14,'3196 Warrior Point','Tammie Milham','0D4A45E649DB487264F275036FA51EF7E9F6E3672277402AABBD23751A77C23F','5711117757',0,'Employee','198fed27-ea07-11eb-863e-dc4a3ee3e76f',14),(15,'17 Kinsman Court','Tildi Sleath','F11CFC6C187D2628592A5D68279C86224D55F6EAB948AE501280F678F8ADF10D','9166745412',0,'Employee','20bd92f4-ea07-11eb-863e-dc4a3ee3e76f',15);
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff_note`
--

DROP TABLE IF EXISTS `staff_note`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `staff_note` (
  `staff_note_id` bigint NOT NULL AUTO_INCREMENT,
  `message` varchar(1000) DEFAULT NULL,
  `note_date` datetime DEFAULT NULL,
  `seen` bit(1) DEFAULT NULL,
  `staff_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`staff_note_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff_note`
--

LOCK TABLES `staff_note` WRITE;
/*!40000 ALTER TABLE `staff_note` DISABLE KEYS */;
INSERT INTO `staff_note` (`staff_note_id`, `message`, `note_date`, `seen`, `staff_id`, `user_id`) VALUES (1,'No comment','2021-06-17 00:00:00',_binary '',1,1),(2,'Demo','2021-06-25 00:00:00',_binary '',3,1),(3,'Testing','2021-06-29 00:00:00',_binary '',8,1),(4,'Beta','2021-07-04 00:00:00',_binary '',6,1),(5,'No comment','2021-06-17 00:00:00',_binary '',1,1),(6,'Demo','2021-06-25 00:00:00',_binary '',3,1),(7,'Testing','2021-06-29 00:00:00',_binary '',8,1),(8,'Beta','2021-07-04 00:00:00',_binary '',6,1);
/*!40000 ALTER TABLE `staff_note` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `supplier` (
  `supplier_id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(220) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `phone_number` varchar(15) DEFAULT NULL,
  `picture_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`supplier_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` (`supplier_id`, `address`, `email`, `name`, `phone_number`, `picture_id`, `user_id`) VALUES (1,'3 Prairie Rose Hill','bedens0@aboutads.info','Viettel','2917056444',1,5),(2,'4 Vernon Point','cmclugaish1@hubpages.com','Vinaphone ','6943555633',2,8),(3,'76 Carey Terrace','mepps2@cbc.ca','Mobifone','4625222484',3,11),(4,'21171 Melvin Avenue','rphillps3@comsenz.com','VNPT','7448498333',4,14),(5,'51720 Troy Lane','rlawrey4@patch.com','Vietnamobile','1912460650',5,3),(6,'33 Jenna Road','bsackes5@loc.gov','Beeline','6592734398',6,6),(7,'131 Steensland Parkway','pscrimshire6@ucsd.edu','FPT','3343238885',7,9),(8,'18760 Del Sol Alley','lbredgeland7@skype.com','CMC','4248924742',8,12),(9,'484 Saint Paul Avenue','cparmeter8@naver.com','Viettelco','9681411388',9,15),(10,'08 Haas Trail','jhymers9@prlog.org','Netnam','9107279396',10,4);
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(50) DEFAULT NULL,
  `first_name` varchar(20) DEFAULT NULL,
  `last_name` varchar(20) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `phone_number` varchar(15) DEFAULT NULL,
  `picture_id` bigint DEFAULT NULL,
  `user_name` varchar(30) DEFAULT NULL,
  `user_uuid` varchar(50) DEFAULT (uuid()),
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`user_id`, `email`, `first_name`, `last_name`, `password`, `phone_number`, `picture_id`, `user_name`, `user_uuid`) VALUES (1,'mmanders0@home.pl','Miof mela','Manders','53870F79A73F040FB5075E1A070F6C8BB578D7C3ACCC84297ED340F2EBB1895E','4976660078',45,NULL,'1a8ecfcb-ea32-11eb-863e-dc4a3ee3e76f'),(2,'jprys1@phpbb.com','Jeremias','Prys','A62DD59B6C270596EE6F4BCE63892C0E583EA475D9BD89B73527D91E0CCF1D0D','5405339203',46,NULL,'201d30f0-ea32-11eb-863e-dc4a3ee3e76f'),(3,'ksustin2@seattletimes.com','Karil','Sustin','5F476302CECE854EB7217F00ED97EF3C02D22A48794AC38A905BEFEADA90E4D5','1238745360',47,NULL,'2363bca9-ea32-11eb-863e-dc4a3ee3e76f'),(4,'delverston3@buzzfeed.com','Dyanna','Elverston','1175F1F1EBCEB579966A40F71855F37973BE70D0ECDCBF5952974E15B83C89EB','8051757923',48,NULL,'264afa20-ea32-11eb-863e-dc4a3ee3e76f'),(5,'ntaffley4@ucla.edu','Niven','Taffley','33467B3F0E19B38D2B704CE5D37B73CAF7E5667DD6CCA21AD8F30832DD378EF7','6818333918',49,NULL,'2962d96b-ea32-11eb-863e-dc4a3ee3e76f'),(6,'rsimants5@rambler.ru','Ronalda','Simants','5B56337D19D3DC40C6FAED8933BA0B446264F36A8BA82A0FDB90DA3B710117F2','6209164749',50,NULL,'2c6411b0-ea32-11eb-863e-dc4a3ee3e76f'),(7,'klimer6@bandcamp.com','Kerry','Limer','F45B80AC0CBF253A1B9BFF4A4A050C858FA87D7AB3D356D6218C1088736F4194','7384112392',51,NULL,'3029700c-ea32-11eb-863e-dc4a3ee3e76f'),(8,'jhamlen7@forbes.com','Jermayne','Hamlen','61DFE3BA7C7D2F0572D0C01C1BC6252ECC20065B58002FDDFBFC837599115A91','7913435030',52,NULL,'34b96332-ea32-11eb-863e-dc4a3ee3e76f'),(9,'wholsey8@github.io','Walther','Holsey','CFE1A29FA677B2EE01AFF3DA1577C5B3F332746EBA35C927CC886060CD53B8B2','7212072043',53,NULL,'3794aa94-ea32-11eb-863e-dc4a3ee3e76f'),(10,'gjurczik9@nasa.gov','Gerhard','Jurczik','0CD47DD591AFE89ACC41512B783488718347697C356103E36A8B652D572A9092','6752449848',54,NULL,'3a31bf25-ea32-11eb-863e-dc4a3ee3e76f'),(11,'jdixona@biblegateway.com','Jerry','Dixon','2B3E953694F66D9F5809F3BD495B4058C3D189BA8E34DFB0C67136E09035E2D7','4214045127',55,NULL,'3cd5ca83-ea32-11eb-863e-dc4a3ee3e76f'),(12,'lsorsbyb@wordpress.com','Lucias','Sorsby','452A0B04DE73FBCAB363FC9562B64D94440933D3D4A9CB7DA722A1D49857AD12','8897078810',56,NULL,'3f9ea50f-ea32-11eb-863e-dc4a3ee3e76f'),(13,'rtulkc@deviantart.com','Raynor','Tulk','5B5CFA81A8FAC140C00768BF1F2B6A0F753DFEF8708EDAE55F16693142466D11','3285377975',57,NULL,'4283e177-ea32-11eb-863e-dc4a3ee3e76f'),(14,'tmilhamd@discovery.com','Tammie','Milham','0D4A45E649DB487264F275036FA51EF7E9F6E3672277402AABBD23751A77C23F','5711117757',58,NULL,'45945356-ea32-11eb-863e-dc4a3ee3e76f'),(15,'tsleathe@google.fr','Tildi','Sleath','F11CFC6C187D2628592A5D68279C86224D55F6EAB948AE501280F678F8ADF10D','9166745412',59,NULL,'49266236-ea32-11eb-863e-dc4a3ee3e76f');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-09-26  9:28:37
