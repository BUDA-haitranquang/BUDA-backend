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
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS staff;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE staff (
  staff_id bigint NOT NULL AUTO_INCREMENT,
  staff_uuid char(36) DEFAULT NULL,
  `name` varchar(40) DEFAULT NULL,
  position varchar(8) DEFAULT NULL,
  user_id bigint DEFAULT NULL,
  `password` char(64) DEFAULT NULL,
  address varchar(45) DEFAULT NULL,
  phone_number char(10) DEFAULT NULL,
  PRIMARY KEY (staff_id),
  KEY UserID16_fk_idx (user_id),
  CONSTRAINT UserID16_fk FOREIGN KEY (user_id) REFERENCES `user` (user_id) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

INSERT INTO staff (staff_id, staff_uuid, name, position, user_id, password, address, phone_number) VALUES (1,'eccc05e1-ea06-11eb-863e-dc4a3ee3e76f','Miof mela Manders','Manager',1,'53870F79A73F040FB5075E1A070F6C8BB578D7C3ACCC84297ED340F2EBB1895E','6155 Thompson Point','4976660078');
INSERT INTO staff (staff_id, staff_uuid, name, position, user_id, password, address, phone_number) VALUES (2,'f208044a-ea06-11eb-863e-dc4a3ee3e76f','Jeremia Prys','Manager',2,'A62DD59B6C270596EE6F4BCE63892C0E583EA475D9BD89B73527D91E0CCF1D0D','3140 Roxbury Center','5405339203');
INSERT INTO staff (staff_id, staff_uuid, name, position, user_id, password, address, phone_number) VALUES (3,'f52f6b51-ea06-11eb-863e-dc4a3ee3e76f','Karil Sustin','Employee',3,'5F476302CECE854EB7217F00ED97EF3C02D22A48794AC38A905BEFEADA90E4D5','85763 Lillian Terrace','1238745360');
INSERT INTO staff (staff_id, staff_uuid, name, position, user_id, password, address, phone_number) VALUES (4,'f839722d-ea06-11eb-863e-dc4a3ee3e76f','Dyanna Ellverston','Employee',4,'1175F1F1EBCEB579966A40F71855F37973BE70D0ECDCBF5952974E15B83C89EB','21 Crescent Oaks Pass','8051757923');
INSERT INTO staff (staff_id, staff_uuid, name, position, user_id, password, address, phone_number) VALUES (5,'fbcb75c2-ea06-11eb-863e-dc4a3ee3e76f','Niven Taffley','Employee',5,'33467B3F0E19B38D2B704CE5D37B73CAF7E5667DD6CCA21AD8F30832DD378EF7','3320 Anhalt Junction','6818333918');
INSERT INTO staff (staff_id, staff_uuid, name, position, user_id, password, address, phone_number) VALUES (6,'ff47cd47-ea06-11eb-863e-dc4a3ee3e76f','Ronalda Simants','Employee',6,'5B56337D19D3DC40C6FAED8933BA0B446264F36A8BA82A0FDB90DA3B710117F2','7073 Nelson Pass','6209164749');
INSERT INTO staff (staff_id, staff_uuid, name, position, user_id, password, address, phone_number) VALUES (7,'0244ee3a-ea07-11eb-863e-dc4a3ee3e76f','Kerry Limer','Employee',7,'F45B80AC0CBF253A1B9BFF4A4A050C858FA87D7AB3D356D6218C1088736F4194','16 Sunbrook Park','7384112392');
INSERT INTO staff (staff_id, staff_uuid, name, position, user_id, password, address, phone_number) VALUES (8,'05dd128b-ea07-11eb-863e-dc4a3ee3e76f','Jermayne Hamlen','Employee',8,'61DFE3BA7C7D2F0572D0C01C1BC6252ECC20065B58002FDDFBFC837599115A91','7 Homewood Center','7913435030');
INSERT INTO staff (staff_id, staff_uuid, name, position, user_id, password, address, phone_number) VALUES (9,'0a526dfb-ea07-11eb-863e-dc4a3ee3e76f','Walther Holsey','Employee',9,'CFE1A29FA677B2EE01AFF3DA1577C5B3F332746EBA35C927CC886060CD53B8B2','1 Clyde Gallagher Parkway','7212072043');
INSERT INTO staff (staff_id, staff_uuid, name, position, user_id, password, address, phone_number) VALUES (10,'0d45df6a-ea07-11eb-863e-dc4a3ee3e76f','Gerhard Jurczik','Employee',10,'0CD47DD591AFE89ACC41512B783488718347697C356103E36A8B652D572A9092','9 Basil Pass','6752449848');
INSERT INTO staff (staff_id, staff_uuid, name, position, user_id, password, address, phone_number) VALUES (11,'10692f1e-ea07-11eb-863e-dc4a3ee3e76f','Jerry Dixon','Employee',11,'2B3E953694F66D9F5809F3BD495B4058C3D189BA8E34DFB0C67136E09035E2D7','7410 Troy Circle','4214045127');
INSERT INTO staff (staff_id, staff_uuid, name, position, user_id, password, address, phone_number) VALUES (12,'13a97e3e-ea07-11eb-863e-dc4a3ee3e76f','Lucias Sorsby','Employee',12,'452A0B04DE73FBCAB363FC9562B64D94440933D3D4A9CB7DA722A1D49857AD12','47 Express Point','8897078810');
INSERT INTO staff (staff_id, staff_uuid, name, position, user_id, password, address, phone_number) VALUES (13,'168d3223-ea07-11eb-863e-dc4a3ee3e76f','Raynor Tulk','Employee',13,'5B5CFA81A8FAC140C00768BF1F2B6A0F753DFEF8708EDAE55F16693142466D11','4 Lotheville Point','3285377975');
INSERT INTO staff (staff_id, staff_uuid, name, position, user_id, password, address, phone_number) VALUES (14,'198fed27-ea07-11eb-863e-dc4a3ee3e76f','Tammie Milham','Employee',14,'0D4A45E649DB487264F275036FA51EF7E9F6E3672277402AABBD23751A77C23F','3196 Warrior Point','5711117757');
INSERT INTO staff (staff_id, staff_uuid, name, position, user_id, password, address, phone_number) VALUES (15,'20bd92f4-ea07-11eb-863e-dc4a3ee3e76f','Tildi Sleath','Employee',15,'F11CFC6C187D2628592A5D68279C86224D55F6EAB948AE501280F678F8ADF10D','17 Kinsman Court','9166745412');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed
