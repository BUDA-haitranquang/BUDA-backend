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
  KEY `product_component_user_id_index` (`user_id`),
  KEY `product_component_product_id_index` (`product_id`),
  KEY `product_component_ingredient_id_index` (`ingredient_id`),
  CONSTRAINT `FK59ur8bog2ssxylw0qo28aumci` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`),
  CONSTRAINT `FK99pdjvmou23eb10r2uj3x34p7` FOREIGN KEY (`ingredient_id`) REFERENCES `ingredient` (`ingredient_id`)
) ENGINE=InnoDB AUTO_INCREMENT=538 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_component`
--

LOCK TABLES `product_component` WRITE;
/*!40000 ALTER TABLE `product_component` DISABLE KEYS */;
INSERT INTO `product_component` VALUES (1,2,176000,2,13,1),(2,3,90000,1,2,2),(3,1,7000,1,8,2),(4,2,102000,1,5,3),(5,1,12000,2,7,3),(6,2,124000,2,1,4),(7,1,12000,1,7,4),(8,1,7000,2,8,4),(9,2,82000,2,3,5),(10,1,7000,2,8,5),(11,2,62000,2,11,6),(12,2,120000,2,15,7),(13,1,51000,2,5,8),(14,1,15000,2,6,8),(15,1,44000,1,13,9),(16,1,12000,1,7,9),(17,2,60000,2,2,10),(18,1,23000,1,10,10),(19,2,60000,1,2,11),(20,1,22000,1,4,11),(21,2,124000,2,1,12),(22,2,62000,1,11,12),(23,1,29000,1,12,13),(24,1,40000,1,14,14),(25,2,88000,2,13,15),(26,1,15000,1,9,15),(27,2,176000,2,13,1),(28,3,90000,1,2,2),(29,1,7000,2,8,2),(30,2,102000,1,5,3),(31,1,12000,2,7,3),(32,2,124000,2,1,4),(33,1,12000,1,7,4),(34,1,7000,2,8,4),(35,2,82000,2,3,5),(36,1,7000,2,8,5),(37,2,62000,1,11,6),(38,2,120000,2,15,7),(39,1,51000,1,5,8),(40,1,15000,1,6,8),(41,1,44000,2,13,9),(42,1,12000,2,7,9),(43,2,60000,2,2,10),(44,1,23000,1,10,10),(45,2,60000,1,2,11),(46,1,22000,2,4,11),(47,2,124000,2,1,12),(48,2,62000,1,11,12),(49,1,29000,1,12,13),(50,1,40000,2,14,14),(51,2,88000,1,13,15),(52,1,15000,1,9,15),(53,1,81000,1,48,16),(54,1,80000,1,84,17),(55,1,90000,1,73,18),(56,1,44000,2,13,19),(57,1,81000,2,48,20),(58,2,152000,1,98,21),(59,2,154000,1,45,22),(60,2,212000,2,32,23),(61,2,200000,1,26,24),(62,2,168000,2,31,25),(63,1,72000,1,76,26),(64,2,152000,1,89,27),(65,1,110000,2,17,28),(66,2,120000,2,15,29),(67,2,158000,1,23,30),(68,2,236000,1,70,31),(69,1,84000,2,80,32),(70,1,89000,2,91,33),(71,2,88000,2,13,34),(72,1,88000,2,93,35),(73,1,111000,2,24,36),(74,2,150000,1,41,37),(75,2,168000,1,31,38),(76,1,101000,1,33,39),(77,1,90000,2,73,40),(78,1,103000,2,64,41),(79,1,116000,2,100,42),(80,1,15000,2,9,43),(81,1,77000,2,45,44),(82,1,115000,2,96,45),(83,2,154000,2,45,46),(84,2,194000,1,38,47),(85,1,84000,1,54,48),(86,1,84000,2,55,49),(87,2,88000,1,13,50),(88,1,75000,2,97,51),(89,1,68000,1,47,52),(90,1,77000,1,45,53),(91,1,83000,1,81,54),(92,1,118000,1,70,55),(93,1,15000,2,6,56),(94,2,154000,1,20,57),(95,2,168000,1,80,58),(96,2,164000,1,39,59),(97,2,184000,2,57,60),(98,2,182000,1,65,61),(99,2,168000,1,55,62),(100,1,102000,1,78,63),(101,1,89000,2,25,64),(102,2,212000,1,92,65),(103,1,98000,2,82,66),(104,1,97000,1,36,67),(105,2,136000,1,34,68),(106,1,82000,2,62,69),(107,1,51000,2,5,70),(108,1,97000,2,38,71),(109,1,99000,1,77,72),(110,2,184000,2,71,73),(111,2,222000,2,24,74),(112,2,24000,2,7,75),(113,1,103000,1,60,76),(114,2,168000,2,80,77),(115,2,228000,2,18,78),(116,1,78000,2,49,79),(117,1,106000,1,92,80),(118,2,46000,1,10,81),(119,1,72000,1,76,82),(120,2,136000,2,47,83),(121,1,15000,1,6,84),(122,2,220000,2,90,85),(123,2,202000,1,33,86),(124,2,178000,2,91,87),(125,2,164000,2,58,88),(126,1,60000,1,15,89),(127,2,82000,2,3,90),(128,2,238000,1,67,91),(129,1,112000,2,27,92),(130,2,168000,2,31,93),(131,1,99000,1,77,94),(132,1,106000,1,88,95),(133,2,58000,2,12,96),(134,1,88000,1,93,97),(135,1,120000,2,30,98),(136,2,196000,1,72,99),(137,1,69000,1,68,100),(138,3,288000,2,244,101),(139,1,114000,2,18,102),(140,1,15000,1,357,103),(141,3,174000,1,228,104),(142,3,354000,1,70,105),(143,3,330000,1,164,106),(144,2,38000,2,111,107),(145,3,309000,2,64,108),(146,3,63000,2,485,109),(147,2,204000,1,234,110),(148,1,49000,2,214,111),(149,2,226000,2,368,112),(150,2,148000,2,198,113),(151,2,150000,1,387,114),(152,3,210000,2,337,115),(153,3,300000,1,26,116),(154,3,126000,2,117,117),(155,2,30000,2,9,118),(156,2,54000,1,192,119),(157,3,210000,2,432,120),(158,3,294000,2,82,121),(159,1,47000,1,114,122),(160,1,104000,1,323,123),(161,3,279000,1,275,124),(162,3,72000,1,404,125),(163,2,142000,2,195,126),(164,1,106000,1,261,127),(165,1,19000,1,222,128),(166,1,115000,1,327,129),(167,2,164000,1,466,130),(168,2,126000,1,351,131),(169,1,15000,2,357,132),(170,1,23000,1,229,133),(171,3,297000,1,77,134),(172,3,270000,1,194,135),(173,1,19000,1,238,136),(174,2,174000,1,110,137),(175,1,104000,1,335,138),(176,2,34000,2,345,139),(177,3,93000,1,219,140),(178,3,246000,2,58,141),(179,2,98000,2,132,142),(180,2,68000,1,486,143),(181,3,252000,1,35,144),(182,2,110000,2,217,145),(183,1,61000,1,479,146),(184,2,82000,1,242,147),(185,3,330000,2,272,148),(186,2,174000,2,133,149),(187,1,25000,1,347,150),(188,1,49000,2,339,151),(189,2,220000,1,154,152),(190,2,188000,1,249,153),(191,1,116000,2,286,154),(192,2,48000,1,179,155),(193,1,111000,2,40,156),(194,3,165000,1,159,157),(195,2,158000,2,177,158),(196,1,30000,1,408,159),(197,1,12000,1,7,160),(198,1,62000,2,309,161),(199,1,100000,1,26,162),(200,1,83000,1,201,163),(201,2,86000,1,425,164),(202,1,111000,2,24,165),(203,2,34000,1,345,166),(204,2,64000,1,152,167),(205,1,69000,1,226,168),(206,2,240000,2,174,169),(207,1,33000,1,188,170),(208,2,226000,1,420,171),(209,2,136000,2,34,172),(210,3,48000,2,412,173),(211,2,162000,1,457,174),(212,2,156000,2,49,175),(213,1,40000,2,371,176),(214,3,75000,2,211,177),(215,1,93000,2,439,178),(216,2,138000,2,61,179),(217,2,188000,1,487,180),(218,1,98000,2,251,181),(219,1,59000,2,296,182),(220,1,119000,2,227,183),(221,3,180000,1,246,184),(222,1,81000,2,48,185),(223,1,30000,2,2,186),(224,3,351000,2,364,187),(225,2,34000,1,313,188),(226,3,300000,2,472,189),(227,2,108000,1,424,190),(228,3,351000,1,200,191),(229,1,58000,1,228,192),(230,3,333000,1,40,193),(231,3,120000,2,14,194),(232,3,96000,1,452,195),(233,3,63000,2,218,196),(234,2,204000,1,234,197),(235,3,87000,2,12,198),(236,1,80000,1,361,199),(237,3,66000,1,267,200),(238,3,264000,1,252,201),(239,1,95000,2,458,202),(240,2,194000,1,36,203),(241,2,138000,1,303,204),(242,1,96000,2,407,205),(243,3,315000,1,125,206),(244,1,92000,1,403,207),(245,3,159000,1,140,208),(246,2,78000,1,490,209),(247,2,152000,1,29,210),(248,2,158000,2,177,211),(249,2,34000,1,297,212),(250,3,231000,2,454,213),(251,3,288000,2,379,214),(252,3,204000,2,34,215),(253,1,76000,2,29,216),(254,3,231000,2,45,217),(255,2,232000,2,138,218),(256,2,208000,1,56,219),(257,3,264000,2,362,220),(258,1,40000,2,143,221),(259,1,47000,2,130,222),(260,3,63000,2,218,223),(261,1,92000,2,199,224),(262,1,113000,1,343,225),(263,3,126000,2,117,226),(264,1,104000,2,56,227),(265,2,36000,1,429,228),(266,2,118000,2,477,229),(267,1,115000,2,96,230),(268,3,243000,1,48,231),(269,3,96000,1,452,232),(270,2,94000,1,114,233),(271,1,49000,1,214,234),(272,3,174000,2,228,235),(273,1,39000,2,497,236),(274,1,22000,2,299,237),(275,2,102000,1,5,238),(276,1,25000,1,126,239),(277,2,68000,1,116,240),(278,1,83000,1,201,241),(279,1,41000,2,155,242),(280,2,240000,1,174,243),(281,2,184000,1,403,244),(282,3,228000,1,492,245),(283,1,58000,2,250,246),(284,2,94000,1,276,247),(285,2,50000,1,126,248),(286,3,276000,2,305,249),(287,3,117000,2,146,250),(288,2,120000,2,314,251),(289,2,166000,2,131,252),(290,1,77000,2,212,253),(291,2,54000,1,168,254),(292,1,83000,2,201,255),(293,2,124000,2,1,256),(294,1,92000,1,403,257),(295,1,23000,2,10,258),(296,1,60000,2,342,259),(297,3,261000,1,180,260),(298,1,40000,1,371,261),(299,3,72000,1,316,262),(300,1,108000,1,465,263),(301,1,42000,2,378,264),(302,3,339000,1,494,265),(303,2,208000,1,335,266),(304,1,71000,2,195,267),(305,1,97000,2,468,268),(306,1,28000,2,253,269),(307,1,79000,1,363,270),(308,2,208000,1,56,271),(309,1,102000,2,190,272),(310,2,224000,2,282,273),(311,1,70000,1,337,274),(312,2,98000,2,339,275),(313,2,158000,2,183,276),(314,2,232000,1,399,277),(315,2,54000,1,445,278),(316,1,76000,1,29,279),(317,2,94000,1,310,280),(318,2,104000,2,463,281),(319,3,360000,1,383,282),(320,3,267000,1,25,283),(321,2,168000,1,476,284),(322,3,276000,1,305,285),(323,1,70000,1,95,286),(324,3,246000,1,62,287),(325,2,222000,1,24,288),(326,2,164000,1,433,289),(327,3,267000,2,91,290),(328,2,150000,1,156,291),(329,3,36000,1,7,292),(330,1,80000,1,66,293),(331,3,249000,2,308,294),(332,3,174000,2,341,295),(333,3,111000,2,279,296),(334,1,54000,1,374,297),(335,3,252000,1,31,298),(336,3,252000,1,31,299),(337,1,83000,1,63,300),(338,2,38000,2,222,301),(339,1,113000,1,420,302),(340,3,87000,1,435,303),(341,3,45000,1,414,304),(342,1,112000,1,264,305),(343,3,252000,2,80,306),(344,2,46000,1,104,307),(345,3,111000,2,279,308),(346,1,115000,2,86,309),(347,3,228000,2,89,310),(348,1,102000,1,190,311),(349,1,87000,2,180,312),(350,1,117000,1,328,313),(351,1,92000,1,101,314),(352,3,219000,2,22,315),(353,3,276000,2,305,316),(354,3,285000,1,458,317),(355,3,99000,2,377,318),(356,1,23000,2,10,319),(357,3,342000,2,416,320),(358,3,303000,1,52,321),(359,2,46000,1,10,322),(360,3,288000,1,394,323),(361,1,93000,1,439,324),(362,2,88000,1,13,325),(363,2,230000,2,245,326),(364,3,321000,2,189,327),(365,1,105000,1,208,328),(366,2,200000,1,472,329),(367,3,57000,2,238,330),(368,1,74000,2,270,331),(369,3,120000,1,139,332),(370,3,297000,1,382,333),(371,2,226000,1,494,334),(372,2,158000,2,321,335),(373,3,315000,1,125,336),(374,2,222000,2,162,337),(375,1,79000,2,434,338),(376,1,79000,1,183,339),(377,3,141000,1,114,340),(378,1,77000,2,20,341),(379,1,36000,2,258,342),(380,1,23000,1,229,343),(381,2,222000,1,372,344),(382,3,234000,1,173,345),(383,2,188000,2,249,346),(384,3,315000,2,225,347),(385,1,57000,1,375,348),(386,2,166000,1,201,349),(387,3,360000,2,381,350),(388,1,60000,2,302,351),(389,1,85000,2,367,352),(390,1,18000,1,429,353),(391,1,75000,1,41,354),(392,3,270000,2,417,355),(393,2,214000,2,461,356),(394,3,312000,2,56,357),(395,1,37000,2,398,358),(396,1,92000,1,318,359),(397,3,210000,1,395,360),(398,3,237000,1,23,361),(399,2,90000,1,427,362),(400,1,80000,1,66,363),(401,2,232000,1,51,364),(402,2,180000,2,53,365),(403,1,96000,1,113,366),(404,3,288000,1,407,367),(405,3,81000,1,192,368),(406,3,123000,1,242,369),(407,2,166000,1,131,370),(408,2,90000,2,427,371),(409,3,345000,1,245,372),(410,3,81000,2,443,373),(411,1,81000,1,481,374),(412,2,180000,2,73,375),(413,3,276000,1,421,376),(414,2,80000,2,388,377),(415,1,79000,1,177,378),(416,1,21000,2,218,379),(417,2,206000,1,60,380),(418,1,39000,1,146,381),(419,3,204000,2,47,382),(420,2,204000,2,298,383),(421,2,50000,2,347,384),(422,2,116000,1,341,385),(423,1,16000,1,166,386),(424,2,138000,1,303,387),(425,1,114000,2,18,388),(426,1,76000,1,178,389),(427,1,49000,2,339,390),(428,3,270000,1,160,391),(429,1,112000,2,282,392),(430,1,18000,2,429,393),(431,1,102000,2,298,394),(432,1,51000,2,202,395),(433,1,42000,1,117,396),(434,2,122000,2,479,397),(435,3,288000,1,43,398),(436,1,37000,1,279,399),(437,3,342000,1,266,400),(438,3,150000,1,493,401),(439,3,216000,1,165,402),(440,1,18000,2,346,403),(441,3,255000,2,233,404),(442,1,25000,2,126,405),(443,2,164000,1,433,406),(444,3,282000,2,284,407),(445,1,110000,1,122,408),(446,3,108000,2,258,409),(447,2,216000,1,422,410),(448,1,104000,2,335,411),(449,3,261000,2,411,412),(450,1,68000,1,47,413),(451,2,60000,1,2,414),(452,2,122000,1,370,415),(453,1,53000,1,344,416),(454,2,124000,1,109,417),(455,1,40000,1,14,418),(456,1,73000,2,239,419),(457,1,75000,1,156,420),(458,3,207000,2,61,421),(459,3,228000,2,338,422),(460,1,15000,2,6,423),(461,1,60000,1,15,424),(462,2,144000,1,59,425),(463,2,120000,2,246,426),(464,3,252000,2,55,427),(465,2,136000,1,34,428),(466,3,36000,2,7,429),(467,3,246000,2,433,430),(468,1,47000,2,144,431),(469,2,184000,2,421,432),(470,3,258000,2,172,433),(471,2,140000,2,95,434),(472,3,336000,1,459,435),(473,3,21000,1,8,436),(474,1,72000,1,165,437),(475,3,114000,1,301,438),(476,3,21000,1,8,439),(477,1,27000,1,135,440),(478,2,46000,2,153,441),(479,3,87000,2,360,442),(480,2,214000,2,340,443),(481,3,342000,1,119,444),(482,2,170000,1,75,445),(483,2,228000,1,18,446),(484,2,58000,2,366,447),(485,1,117000,2,273,448),(486,3,210000,1,268,449),(487,1,114000,2,18,450),(488,1,107000,2,287,451),(489,1,120000,2,381,452),(490,1,88000,2,42,453),(491,2,178000,1,69,454),(492,1,31000,2,219,455),(493,1,76000,2,384,456),(494,2,144000,1,265,457),(495,1,120000,1,174,458),(496,3,294000,2,72,459),(497,2,214000,2,340,460),(498,1,95000,1,482,461),(499,1,69000,1,389,462),(500,2,214000,1,498,463),(501,2,160000,2,324,464),(502,3,63000,2,124,465),(503,3,51000,1,150,466),(504,3,171000,1,375,467),(505,3,114000,1,426,468),(506,3,45000,1,6,469),(507,1,27000,2,248,470),(508,1,105000,2,225,471),(509,2,84000,2,378,472),(510,2,230000,1,216,473),(511,3,81000,2,445,474),(512,1,85000,2,75,475),(513,3,333000,1,40,476),(514,2,152000,2,473,477),(515,2,54000,1,248,478),(516,3,105000,2,317,479),(517,2,120000,1,342,480),(518,1,68000,1,257,481),(519,1,106000,1,261,482),(520,1,84000,2,31,483),(521,3,333000,1,372,484),(522,3,210000,1,268,485),(523,1,75000,1,221,486),(524,1,60000,2,302,487),(525,1,25000,1,347,488),(526,3,357000,1,329,489),(527,2,184000,2,101,490),(528,2,228000,2,18,491),(529,2,132000,2,288,492),(530,2,212000,2,386,493),(531,3,309000,2,64,494),(532,1,110000,2,164,495),(533,2,222000,2,127,496),(534,3,159000,1,140,497),(535,1,32000,2,320,498),(536,1,87000,1,180,499),(537,1,27000,1,438,500);
/*!40000 ALTER TABLE `product_component` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-01-19 10:00:23
