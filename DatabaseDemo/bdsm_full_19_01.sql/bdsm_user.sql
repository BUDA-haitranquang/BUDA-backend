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
  `password` varchar(128) DEFAULT NULL,
  `phone_number` varchar(15) DEFAULT NULL,
  `picture_id` bigint DEFAULT NULL,
  `user_name` varchar(30) DEFAULT NULL,
  `user_uuid` varchar(50) DEFAULT (uuid()),
  `enabled` bit(1) DEFAULT NULL,
  `plan_type` varchar(255) DEFAULT 'BASIC',
  PRIMARY KEY (`user_id`),
  KEY `user_email_index` (`email`),
  KEY `user_phone_number_index` (`phone_number`),
  KEY `user_user_name_index` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'anotherbadass@gmail.com','Buda','Buda','$2a$10$B5uPPp7NHVjSuC6IIv.kDOmgQPwonJSsH8n02VYLEsLAsLdKs8dKO','1231312313',NULL,'badasssss',NULL,_binary '','BASIC'),(2,'budatester@gmail.com','Buda','Hai GOATe','$2a$10$gepYPzi4FAQA8h1EbGgb5e.iFDH.3XqTC7GQBjACn23G/tg2aVXYq','1231321323',NULL,'budatester',NULL,_binary '','PREMIUM'),(3,'abcxyz@email.com','tien','tien','$2a$10$47Qq75q0ZCzBCedJI7L9Bum.0hvllzVnuDLLXVudW6OSbC0JRBePW','0123456788',NULL,'tien1',NULL,_binary '','BASIC'),(4,'abcxyz1@email.com','tien','tien','$2a$10$Hy3CCAYmYan26h67fo9y8.42rx9T12pnV8QrUbc8tVRctnYaCzQr2','0123456787',NULL,'tien2',NULL,_binary '','BASIC'),(5,'abcxyz2@email.com','tien','tien','$2a$10$X.PjvsjGrH6tsWVnniHGYOVaXSwn2yOjTWpMuCK4tcLIqV2DRTjse','0123456786',NULL,'tien3',NULL,_binary '','BASIC'),(6,'tqhai@gmail.com','Tran','Hai','$2a$10$BW2ZGd/.SQdww.O4mSMCY.ZyXB/E4JXSmhjLR715ikleUnn5.lzF.','031256748',NULL,'tqhai',NULL,_binary '','BASIC'),(7,'nnling@gmail.com','Nguyen','Linh','$2a$10$VW.CyRznBc5rwY2rROp.p.B5GCuvN7.YJUc3fJmSk4yDbS8bMhQtS','03489427284',NULL,'nnling',NULL,_binary '','BASIC'),(8,'haitq_new@mail.edu','hai','kas','$2a$10$A2bnHphXZJGRU7qTjWAzwOUUlMWjIHSjjhYg/tAHmuVIoNbfBcFRK','01231231344',NULL,'haitq_new',NULL,_binary '','BASIC'),(9,'tien4@email.com','tien','tien','$2a$10$nxKQgWcS16nYpT.L/4dekux9LOWfDAOdlAhl1u8tSzB.sCLV9OdKi','0123456784',NULL,'tien4',NULL,_binary '','BASIC'),(17,'budatesteraew@gmail.com','Buda','Tester','$2a$10$WaP0KiGIipeykWWUewgrzurA6iSOjr2upA762fjj3dJ6bfP8uW/D2','127312322434',NULL,'budatesterewa',NULL,_binary '','BASIC'),(18,'123azulgrana2001@gmail.com','Buda','Tester','$2a$10$3tfvlyPLXKpJ.Dkc/0hGXOUH3Dzhs/zaHYa.4Q0WB7fnaEmip2OE.','1273123224',NULL,'fortesting',NULL,_binary '','BASIC'),(19,'agoodbudaaccount@gmail.com','Buda','Tester','$2a$10$O7ze4EGPeccgkvaZs.sRUOP9oKS4e7eUVXIw89A4WwJYxgXtT5Nv2','02312322434',NULL,'budatesterewad',NULL,_binary '','BASIC');
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

-- Dump completed on 2022-01-19 10:00:24
