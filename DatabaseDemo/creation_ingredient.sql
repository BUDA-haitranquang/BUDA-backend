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
-- Table structure for table `ingredient`
--

DROP TABLE IF EXISTS ingredient;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE ingredient (
  ingredient_id bigint NOT NULL AUTO_INCREMENT,
  ingredient_uuid char(36) DEFAULT NULL,
  user_id bigint DEFAULT NULL,
  `name` varchar(40) DEFAULT NULL,
  `description` varchar(40) DEFAULT NULL,
  amount_left bigint DEFAULT NULL,
  price double DEFAULT NULL,
  alert_amount_left char(1) DEFAULT NULL,
  picture_id bigint DEFAULT NULL,
  PRIMARY KEY (ingredient_id),
  KEY PictureID_fk_idx (picture_id),
  KEY UserID5_fk_idx (user_id),
  CONSTRAINT PictureID_fk FOREIGN KEY (picture_id) REFERENCES picture (picture_id) ON DELETE SET NULL,
  CONSTRAINT UserID5_fk FOREIGN KEY (user_id) REFERENCES `user` (user_id) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingredient`
--

INSERT INTO ingredient (ingredient_id, ingredient_uuid, user_id, name, description, amount_left, price, alert_amount_left, picture_id) VALUES (1,'ee06182a-ea08-11eb-863e-dc4a3ee3e76f',5,'Beef','Meat',40,62000,'O',11);
INSERT INTO ingredient (ingredient_id, ingredient_uuid, user_id, name, description, amount_left, price, alert_amount_left, picture_id) VALUES (2,'f2c37fb8-ea08-11eb-863e-dc4a3ee3e76f',7,'Chicken','Meat',87,30000,'O',12);
INSERT INTO ingredient (ingredient_id, ingredient_uuid, user_id, name, description, amount_left, price, alert_amount_left, picture_id) VALUES (3,'f6ed6df4-ea08-11eb-863e-dc4a3ee3e76f',8,'Oyster','Seafood',60,41000,'O',13);
INSERT INTO ingredient (ingredient_id, ingredient_uuid, user_id, name, description, amount_left, price, alert_amount_left, picture_id) VALUES (4,'fb7572bd-ea08-11eb-863e-dc4a3ee3e76f',12,'Mayonnaise','Condiment',63,22000,'O',14);
INSERT INTO ingredient (ingredient_id, ingredient_uuid, user_id, name, description, amount_left, price, alert_amount_left, picture_id) VALUES (5,'ff845140-ea08-11eb-863e-dc4a3ee3e76f',3,'Pork','Meat',41,51000,'O',15);
INSERT INTO ingredient (ingredient_id, ingredient_uuid, user_id, name, description, amount_left, price, alert_amount_left, picture_id) VALUES (6,'08e0677d-ea09-11eb-863e-dc4a3ee3e76f',6,'Fish Sauce','Condiment',63,15000,'O',16);
INSERT INTO ingredient (ingredient_id, ingredient_uuid, user_id, name, description, amount_left, price, alert_amount_left, picture_id) VALUES (7,'0c41f735-ea09-11eb-863e-dc4a3ee3e76f',9,'Pepper','Spice',65,12000,'O',17);
INSERT INTO ingredient (ingredient_id, ingredient_uuid, user_id, name, description, amount_left, price, alert_amount_left, picture_id) VALUES (8,'11c58379-ea09-11eb-863e-dc4a3ee3e76f',10,'Salt','Spice',44,7000,'O',18);
INSERT INTO ingredient (ingredient_id, ingredient_uuid, user_id, name, description, amount_left, price, alert_amount_left, picture_id) VALUES (9,'14c20318-ea09-11eb-863e-dc4a3ee3e76f',11,'Vinegar','Condiment',41,15000,'O',19);
INSERT INTO ingredient (ingredient_id, ingredient_uuid, user_id, name, description, amount_left, price, alert_amount_left, picture_id) VALUES (10,'1795a850-ea09-11eb-863e-dc4a3ee3e76f',15,'Onion','Vegetable',50,23000,'O',20);
INSERT INTO ingredient (ingredient_id, ingredient_uuid, user_id, name, description, amount_left, price, alert_amount_left, picture_id) VALUES (11,'1ae5341b-ea09-11eb-863e-dc4a3ee3e76f',4,'Bread','Bread',61,31000,'O',21);
INSERT INTO ingredient (ingredient_id, ingredient_uuid, user_id, name, description, amount_left, price, alert_amount_left, picture_id) VALUES (12,'1e140c86-ea09-11eb-863e-dc4a3ee3e76f',13,'Orange','Fruit',63,29000,'O',22);
INSERT INTO ingredient (ingredient_id, ingredient_uuid, user_id, name, description, amount_left, price, alert_amount_left, picture_id) VALUES (13,'245d3348-ea09-11eb-863e-dc4a3ee3e76f',14,'Shrimp','Seafood',94,44000,'O',23);
INSERT INTO ingredient (ingredient_id, ingredient_uuid, user_id, name, description, amount_left, price, alert_amount_left, picture_id) VALUES (14,'276ba372-ea09-11eb-863e-dc4a3ee3e76f',3,'Kiwi','Fruit',70,40000,'O',24);
INSERT INTO ingredient (ingredient_id, ingredient_uuid, user_id, name, description, amount_left, price, alert_amount_left, picture_id) VALUES (15,'2ab51dd1-ea09-11eb-863e-dc4a3ee3e76f',4,'Salmon','Seafood',54,60000,'O',25);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed
