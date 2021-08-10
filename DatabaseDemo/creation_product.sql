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
-- Table structure for table `product`
--

DROP TABLE IF EXISTS product;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE product (
  ProductID int NOT NULL AUTO_INCREMENT,
  UserID int DEFAULT NULL,
  `Name` varchar(40) DEFAULT NULL,
  ProductDescript varchar(50) DEFAULT NULL,
  AmountLeft int DEFAULT NULL,
  AlertAmount char(1) DEFAULT NULL,
  PictureID int DEFAULT NULL,
  SellingPrice double DEFAULT NULL,
  CostPerUnit double DEFAULT NULL,
  GroupID int DEFAULT NULL,
  PRIMARY KEY (ProductID),
  KEY PictureID_fk2_idx (PictureID),
  KEY UserID_fk4_idx (UserID),
  CONSTRAINT PictureID_fk1 FOREIGN KEY (PictureID) REFERENCES picture (PictureID) ON DELETE SET NULL,
  CONSTRAINT UserID_fk9 FOREIGN KEY (UserID) REFERENCES `user` (UserID) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

INSERT INTO product VALUES (1,3,'Grilled Shrimp','Grilled Shrimp',25,'O',26,190000,176000,2),(2,5,'Fried Chicken','Fried Chicken',27,'O',27,115000,97000,1),(3,7,'Pork Barbecue','Pork Barbecue',16,'O',28,135000,114000,2),(4,9,'Beef Steak','Beef Steak',20,'O',29,160000,143000,2),(5,11,'Roast Oyster','Roast Oyster',23,'O',30,100000,89000,2),(6,13,'Garlic Butter Bread','Garlic Butter Bread',41,'O',31,80000,62000,1),(7,15,'Salmon Sashimi','Salmon Sashimi',30,'O',32,140000,120000,1),(8,4,'Spring Roll','Spring Roll',22,'O',33,85000,66000,1),(9,6,'Seafood Soup','Seafood Soup',18,'O',34,70000,56000,1),(10,8,'Chicken Orzo','Chicken Orzo',19,'O',35,100000,83000,2),(11,10,'Pan-seared Chicken Breast','Pan-seared Chicken Breast',21,'O',36,100000,82000,2),(12,12,'Beef Wellington','Beef Wellington',18,'O',37,205000,186000,2),(13,14,'Orange Juice','Orange Juice',27,'O',38,40000,29000,3),(14,3,'Kiwi Juice','Kiwi Juice',30,'O',39,55000,40000,3),(15,4,'Shrimp and Butter','Shrimp and Butter',33,'O',40,125000,103000,2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed
