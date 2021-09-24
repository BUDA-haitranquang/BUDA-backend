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
-- Table structure for table `productcomponent`
--

DROP TABLE IF EXISTS productcomponent;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE productcomponent (
  ProductID int DEFAULT NULL,
  IngredientID int DEFAULT NULL,
  RequiredQuantity int DEFAULT NULL,
  UserID int DEFAULT NULL,
  TotalCost double DEFAULT NULL,
  KEY IngredientID_fk2_idx (IngredientID),
  KEY ProductID_fk_idx (ProductID),
  KEY UserID_fk10_idx (UserID),
  CONSTRAINT IngredientID_fk FOREIGN KEY (IngredientID) REFERENCES ingredient (IngredientID) ON DELETE SET NULL,
  CONSTRAINT ProductID_fk FOREIGN KEY (ProductID) REFERENCES product (ProductID) ON DELETE SET NULL,
  CONSTRAINT UserID_fk10 FOREIGN KEY (UserID) REFERENCES `user` (UserID) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productcomponent`
--

INSERT INTO productcomponent (ProductID, IngredientID, RequiredQuantity, UserID, TotalCost) VALUES (1,13,2,14,176000);
INSERT INTO productcomponent (ProductID, IngredientID, RequiredQuantity, UserID, TotalCost) VALUES (2,2,3,7,90000);
INSERT INTO productcomponent (ProductID, IngredientID, RequiredQuantity, UserID, TotalCost) VALUES (2,8,1,10,7000);
INSERT INTO productcomponent (ProductID, IngredientID, RequiredQuantity, UserID, TotalCost) VALUES (3,5,2,3,102000);
INSERT INTO productcomponent (ProductID, IngredientID, RequiredQuantity, UserID, TotalCost) VALUES (3,7,1,9,12000);
INSERT INTO productcomponent (ProductID, IngredientID, RequiredQuantity, UserID, TotalCost) VALUES (4,1,2,5,124000);
INSERT INTO productcomponent (ProductID, IngredientID, RequiredQuantity, UserID, TotalCost) VALUES (4,7,1,9,12000);
INSERT INTO productcomponent (ProductID, IngredientID, RequiredQuantity, UserID, TotalCost) VALUES (4,8,1,10,7000);
INSERT INTO productcomponent (ProductID, IngredientID, RequiredQuantity, UserID, TotalCost) VALUES (5,3,2,8,82000);
INSERT INTO productcomponent (ProductID, IngredientID, RequiredQuantity, UserID, TotalCost) VALUES (5,8,1,10,7000);
INSERT INTO productcomponent (ProductID, IngredientID, RequiredQuantity, UserID, TotalCost) VALUES (6,11,2,4,62000);
INSERT INTO productcomponent (ProductID, IngredientID, RequiredQuantity, UserID, TotalCost) VALUES (7,15,2,4,120000);
INSERT INTO productcomponent (ProductID, IngredientID, RequiredQuantity, UserID, TotalCost) VALUES (8,5,1,3,51000);
INSERT INTO productcomponent (ProductID, IngredientID, RequiredQuantity, UserID, TotalCost) VALUES (8,6,1,6,15000);
INSERT INTO productcomponent (ProductID, IngredientID, RequiredQuantity, UserID, TotalCost) VALUES (9,13,1,14,44000);
INSERT INTO productcomponent (ProductID, IngredientID, RequiredQuantity, UserID, TotalCost) VALUES (9,7,1,9,12000);
INSERT INTO productcomponent (ProductID, IngredientID, RequiredQuantity, UserID, TotalCost) VALUES (10,2,2,7,60000);
INSERT INTO productcomponent (ProductID, IngredientID, RequiredQuantity, UserID, TotalCost) VALUES (10,10,1,15,23000);
INSERT INTO productcomponent (ProductID, IngredientID, RequiredQuantity, UserID, TotalCost) VALUES (11,2,2,7,60000);
INSERT INTO productcomponent (ProductID, IngredientID, RequiredQuantity, UserID, TotalCost) VALUES (11,4,1,12,22000);
INSERT INTO productcomponent (ProductID, IngredientID, RequiredQuantity, UserID, TotalCost) VALUES (12,1,2,5,124000);
INSERT INTO productcomponent (ProductID, IngredientID, RequiredQuantity, UserID, TotalCost) VALUES (12,11,2,4,62000);
INSERT INTO productcomponent (ProductID, IngredientID, RequiredQuantity, UserID, TotalCost) VALUES (13,12,1,13,29000);
INSERT INTO productcomponent (ProductID, IngredientID, RequiredQuantity, UserID, TotalCost) VALUES (14,14,1,3,40000);
INSERT INTO productcomponent (ProductID, IngredientID, RequiredQuantity, UserID, TotalCost) VALUES (15,13,2,14,88000);
INSERT INTO productcomponent (ProductID, IngredientID, RequiredQuantity, UserID, TotalCost) VALUES (15,9,1,11,15000);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed
