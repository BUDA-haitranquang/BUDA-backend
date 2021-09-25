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
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS supplier;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE supplier (
  supplier_id bigint NOT NULL AUTO_INCREMENT,
  supplier_uuiid char(36) DEFAULT NULL,
  user_id bigint DEFAULT NULL,
  `name` varchar(40) DEFAULT NULL,
  phone_number char(10) DEFAULT NULL,
  address varchar(100) DEFAULT NULL,
  email varchar(60) DEFAULT NULL,
  picture_id bigint DEFAULT NULL,
  PRIMARY KEY (supplier_id),
  KEY PictureID2_fk_idx (picture_id),
  KEY PictureID3_fk_idx (picture_id),
  KEY UserID18_fk_idx (user_id),
  CONSTRAINT PictureID3_fk FOREIGN KEY (picture_id) REFERENCES picture (picture_id) ON DELETE SET NULL,
  CONSTRAINT UserID18_fk FOREIGN KEY (user_id) REFERENCES `user` (user_id) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

INSERT INTO supplier (supplier_id, supplier_uuiid, user_id, name, phone_number, address, email, picture_id) VALUES (1,'7566b1d3-ea32-11eb-863e-dc4a3ee3e76f',5,'Viettel','2917056444','3 Prairie Rose Hill','bedens0@aboutads.info',1);
INSERT INTO supplier (supplier_id, supplier_uuiid, user_id, name, phone_number, address, email, picture_id) VALUES (2,'79109839-ea32-11eb-863e-dc4a3ee3e76f',8,'Vinaphone ','6943555633','4 Vernon Point','cmclugaish1@hubpages.com',2);
INSERT INTO supplier (supplier_id, supplier_uuiid, user_id, name, phone_number, address, email, picture_id) VALUES (3,'7be9b850-ea32-11eb-863e-dc4a3ee3e76f',11,'Mobifone','4625222484','76 Carey Terrace','mepps2@cbc.ca',3);
INSERT INTO supplier (supplier_id, supplier_uuiid, user_id, name, phone_number, address, email, picture_id) VALUES (4,'7e9ddf17-ea32-11eb-863e-dc4a3ee3e76f',14,'VNPT','7448498333','21171 Melvin Avenue','rphillps3@comsenz.com',4);
INSERT INTO supplier (supplier_id, supplier_uuiid, user_id, name, phone_number, address, email, picture_id) VALUES (5,'815c41ef-ea32-11eb-863e-dc4a3ee3e76f',3,'Vietnamobile','1912460650','51720 Troy Lane','rlawrey4@patch.com',5);
INSERT INTO supplier (supplier_id, supplier_uuiid, user_id, name, phone_number, address, email, picture_id) VALUES (6,'84248592-ea32-11eb-863e-dc4a3ee3e76f',6,'Beeline','6592734398','33 Jenna Road','bsackes5@loc.gov',6);
INSERT INTO supplier (supplier_id, supplier_uuiid, user_id, name, phone_number, address, email, picture_id) VALUES (7,'86b9bba3-ea32-11eb-863e-dc4a3ee3e76f',9,'FPT','3343238885','131 Steensland Parkway','pscrimshire6@ucsd.edu',7);
INSERT INTO supplier (supplier_id, supplier_uuiid, user_id, name, phone_number, address, email, picture_id) VALUES (8,'8a643588-ea32-11eb-863e-dc4a3ee3e76f',12,'CMC','4248924742','18760 Del Sol Alley','lbredgeland7@skype.com',8);
INSERT INTO supplier (supplier_id, supplier_uuiid, user_id, name, phone_number, address, email, picture_id) VALUES (9,'8e6c34e6-ea32-11eb-863e-dc4a3ee3e76f',15,'Viettelco','9681411388','484 Saint Paul Avenue','cparmeter8@naver.com',9);
INSERT INTO supplier (supplier_id, supplier_uuiid, user_id, name, phone_number, address, email, picture_id) VALUES (10,'90f431c0-ea32-11eb-863e-dc4a3ee3e76f',4,'Netnam','9107279396','08 Haas Trail','jhymers9@prlog.org',10);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed
