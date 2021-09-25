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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS user;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  user_id bigint NOT NULL AUTO_INCREMENT,
  user_uuid char(36) DEFAULT NULL,
  `password` char(64) DEFAULT NULL,
  email varchar(60) DEFAULT NULL,
  phone_number char(10) DEFAULT NULL,
  last_name varchar(20) DEFAULT NULL,
  first_name varchar(20) DEFAULT NULL,
  picture_id bigint DEFAULT NULL,
  PRIMARY KEY (user_id),
  KEY PictureID4_fk_idx (picture_id),
  CONSTRAINT PictureID4_fk FOREIGN KEY (picture_id) REFERENCES picture (picture_id) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

INSERT INTO user (user_id, user_uuid, password, email, phone_number, last_name, first_name, picture_id) VALUES (1,'1a8ecfcb-ea32-11eb-863e-dc4a3ee3e76f','53870F79A73F040FB5075E1A070F6C8BB578D7C3ACCC84297ED340F2EBB1895E','mmanders0@home.pl','4976660078','Manders','Miof mela',45);
INSERT INTO user (user_id, user_uuid, password, email, phone_number, last_name, first_name, picture_id) VALUES (2,'201d30f0-ea32-11eb-863e-dc4a3ee3e76f','A62DD59B6C270596EE6F4BCE63892C0E583EA475D9BD89B73527D91E0CCF1D0D','jprys1@phpbb.com','5405339203','Prys','Jeremias',46);
INSERT INTO user (user_id, user_uuid, password, email, phone_number, last_name, first_name, picture_id) VALUES (3,'2363bca9-ea32-11eb-863e-dc4a3ee3e76f','5F476302CECE854EB7217F00ED97EF3C02D22A48794AC38A905BEFEADA90E4D5','ksustin2@seattletimes.com','1238745360','Sustin','Karil',47);
INSERT INTO user (user_id, user_uuid, password, email, phone_number, last_name, first_name, picture_id) VALUES (4,'264afa20-ea32-11eb-863e-dc4a3ee3e76f','1175F1F1EBCEB579966A40F71855F37973BE70D0ECDCBF5952974E15B83C89EB','delverston3@buzzfeed.com','8051757923','Elverston','Dyanna',48);
INSERT INTO user (user_id, user_uuid, password, email, phone_number, last_name, first_name, picture_id) VALUES (5,'2962d96b-ea32-11eb-863e-dc4a3ee3e76f','33467B3F0E19B38D2B704CE5D37B73CAF7E5667DD6CCA21AD8F30832DD378EF7','ntaffley4@ucla.edu','6818333918','Taffley','Niven',49);
INSERT INTO user (user_id, user_uuid, password, email, phone_number, last_name, first_name, picture_id) VALUES (6,'2c6411b0-ea32-11eb-863e-dc4a3ee3e76f','5B56337D19D3DC40C6FAED8933BA0B446264F36A8BA82A0FDB90DA3B710117F2','rsimants5@rambler.ru','6209164749','Simants','Ronalda',50);
INSERT INTO user (user_id, user_uuid, password, email, phone_number, last_name, first_name, picture_id) VALUES (7,'3029700c-ea32-11eb-863e-dc4a3ee3e76f','F45B80AC0CBF253A1B9BFF4A4A050C858FA87D7AB3D356D6218C1088736F4194','klimer6@bandcamp.com','7384112392','Limer','Kerry',51);
INSERT INTO user (user_id, user_uuid, password, email, phone_number, last_name, first_name, picture_id) VALUES (8,'34b96332-ea32-11eb-863e-dc4a3ee3e76f','61DFE3BA7C7D2F0572D0C01C1BC6252ECC20065B58002FDDFBFC837599115A91','jhamlen7@forbes.com','7913435030','Hamlen','Jermayne',52);
INSERT INTO user (user_id, user_uuid, password, email, phone_number, last_name, first_name, picture_id) VALUES (9,'3794aa94-ea32-11eb-863e-dc4a3ee3e76f','CFE1A29FA677B2EE01AFF3DA1577C5B3F332746EBA35C927CC886060CD53B8B2','wholsey8@github.io','7212072043','Holsey','Walther',53);
INSERT INTO user (user_id, user_uuid, password, email, phone_number, last_name, first_name, picture_id) VALUES (10,'3a31bf25-ea32-11eb-863e-dc4a3ee3e76f','0CD47DD591AFE89ACC41512B783488718347697C356103E36A8B652D572A9092','gjurczik9@nasa.gov','6752449848','Jurczik','Gerhard',54);
INSERT INTO user (user_id, user_uuid, password, email, phone_number, last_name, first_name, picture_id) VALUES (11,'3cd5ca83-ea32-11eb-863e-dc4a3ee3e76f','2B3E953694F66D9F5809F3BD495B4058C3D189BA8E34DFB0C67136E09035E2D7','jdixona@biblegateway.com','4214045127','Dixon','Jerry',55);
INSERT INTO user (user_id, user_uuid, password, email, phone_number, last_name, first_name, picture_id) VALUES (12,'3f9ea50f-ea32-11eb-863e-dc4a3ee3e76f','452A0B04DE73FBCAB363FC9562B64D94440933D3D4A9CB7DA722A1D49857AD12','lsorsbyb@wordpress.com','8897078810','Sorsby','Lucias',56);
INSERT INTO user (user_id, user_uuid, password, email, phone_number, last_name, first_name, picture_id) VALUES (13,'4283e177-ea32-11eb-863e-dc4a3ee3e76f','5B5CFA81A8FAC140C00768BF1F2B6A0F753DFEF8708EDAE55F16693142466D11','rtulkc@deviantart.com','3285377975','Tulk','Raynor',57);
INSERT INTO user (user_id, user_uuid, password, email, phone_number, last_name, first_name, picture_id) VALUES (14,'45945356-ea32-11eb-863e-dc4a3ee3e76f','0D4A45E649DB487264F275036FA51EF7E9F6E3672277402AABBD23751A77C23F','tmilhamd@discovery.com','5711117757','Milham','Tammie',58);
INSERT INTO user (user_id, user_uuid, password, email, phone_number, last_name, first_name, picture_id) VALUES (15,'49266236-ea32-11eb-863e-dc4a3ee3e76f','F11CFC6C187D2628592A5D68279C86224D55F6EAB948AE501280F678F8ADF10D','tsleathe@google.fr','9166745412','Sleath','Tildi',59);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed
