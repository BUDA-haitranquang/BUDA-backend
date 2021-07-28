-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: creation
-- ------------------------------------------------------
-- Server version	8.0.25

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
-- Table structure for table `picture`
--

DROP TABLE IF EXISTS `picture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `picture` (
  `PictureID` int NOT NULL,
  `Picture_UUID` char(36) DEFAULT NULL,
  `Link` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`PictureID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `picture`
--

LOCK TABLES `picture` WRITE;
/*!40000 ALTER TABLE `picture` DISABLE KEYS */;
INSERT INTO `picture` VALUES (1,'63f23d87-ea34-11eb-863e-dc4a3ee3e76f','https://upload.wikimedia.org/wikipedia/commons/f/fe/Viettel_logo_2021.svg'),(2,'6eb7e71c-ea34-11eb-863e-dc4a3ee3e76f','https://cdn.tgdd.vn/Files/2015/04/21/634843/cach-dang-ki-cuoc-goi-va-nhan-tin-mien-phi-vinaphone-2.jpg'),(3,'71610ee1-ea34-11eb-863e-dc4a3ee3e76f','https://i2.wp.com/marketingai.admicro.vn/wp-content/uploads/2018/11/bo-nhan-dien-thuong-hieu-mobifone-min.png?resize=766%2C216&ssl=1'),(4,'73c5f7ee-ea34-11eb-863e-dc4a3ee3e76f','https://vnptvinaphone.net.vn/wp-content/uploads/2021/01/logo-vnpt-h%C3%A0-n%E1%BB%8Di.png'),(5,'761d2a1e-ea34-11eb-863e-dc4a3ee3e76f','https://upload.wikimedia.org/wikipedia/vi/thumb/a/a8/Vietnamobile_Logo.svg/1200px-Vietnamobile_Logo.svg.png'),(6,'790e264d-ea34-11eb-863e-dc4a3ee3e76f','https://upload.wikimedia.org/wikipedia/vi/7/7a/BeeLine_logo.png'),(7,'7bd06f3f-ea34-11eb-863e-dc4a3ee3e76f','https://www.fpt.com.vn/Content/home/images/icon/ic-logo.png'),(8,'7e471400-ea34-11eb-863e-dc4a3ee3e76f','https://cmc.com.vn/main/imgs/logo.svg'),(9,'82c41462-ea34-11eb-863e-dc4a3ee3e76f','https://viettelco.net/wp-content/uploads/2020/01/Logo-Viettelco.png'),(10,'88425931-ea34-11eb-863e-dc4a3ee3e76f','https://netnam.com/o/netnam/images/logo.png'),(11,'8b53e0cf-ea34-11eb-863e-dc4a3ee3e76f','https://www.foodsafetynews.com/files/2020/06/raw-sirloin-steak-beef.jpg'),(12,'8de64ada-ea34-11eb-863e-dc4a3ee3e76f','https://i2.wp.com/hygienicmeat.com/wp-content/uploads/2020/07/s735965455334319150_p6_i1_w2048.jpeg'),(13,'903a19dc-ea34-11eb-863e-dc4a3ee3e76f','https://www.collinsdictionary.com/images/full/oyster_245235925.jpg'),(14,'92e6a736-ea34-11eb-863e-dc4a3ee3e76f','https://static.julinse.com/i/a250031cfd4543cd-1024x684.jpg'),(15,'9616c73c-ea34-11eb-863e-dc4a3ee3e76f','https://passiondelivery.com/pub/media/catalog/product/cache/7e6e59e80a69ca81b40190dbfa9e211f/5/7/57._pork_loin_skin-off.jpg'),(16,'98fa6634-ea34-11eb-863e-dc4a3ee3e76f','https://media.cooky.vn/images/blog-2016/cach-phan-biet-nuoc-mam-truyen-thong-va-nuoc-cham-cong-nghiep%201.jpg'),(17,'9d09157e-ea34-11eb-863e-dc4a3ee3e76f','http://kolala.com.vn/upload/public/TI%C3%AAu/where-does-black-pepper-come-from.jpg'),(18,'9fc19aae-ea34-11eb-863e-dc4a3ee3e76f','https://cdn-prod.medicalnewstoday.com/content/images/articles/322/322745/salt-shaker.jpg'),(19,'a2a5a086-ea34-11eb-863e-dc4a3ee3e76f','https://parenting.firstcry.ae/memories/getMyMemoryImages/2018/04/528721780-H.jpg'),(20,'a5c35ce1-ea34-11eb-863e-dc4a3ee3e76f','https://bizweb.dktcdn.net/100/408/264/products/5116995hanh-tay-onion-000000005272-jpeg.jpg?v=1603792333943'),(21,'a8a492ca-ea34-11eb-863e-dc4a3ee3e76f','https://www.escoffieronline.com/wp-content/uploads/2013/04/iStock-995038782-small.jpg'),(22,'ab75fcd4-ea34-11eb-863e-dc4a3ee3e76f','https://st.depositphotos.com/1049691/4267/i/950/depositphotos_42673487-stock-photo-fresh-orange.jpg'),(23,'ae317a3b-ea34-11eb-863e-dc4a3ee3e76f','http://suckhoedoisong.vn/Images/thutrang/2016/04/25/tanghoocmon_1.jpg'),(24,'b0a5b37c-ea34-11eb-863e-dc4a3ee3e76f','https://wallpapercave.com/wp/wp2691685.jpg'),(25,'b47caeb9-ea34-11eb-863e-dc4a3ee3e76f','https://images.thefishsite.com/fish%2Farticles%2Fprocessing%2Fsalmon-fillet.jpeg'),(26,'b70964b4-ea34-11eb-863e-dc4a3ee3e76f','https://hips.hearstapps.com/vidthumb/images/grilled-shrimp-horizontal-1553275047.jpg'),(27,'b9795e2e-ea34-11eb-863e-dc4a3ee3e76f','https://www.thespruceeats.com/thmb/RuiS8PlPaUkJFbrH8skujr90Q8A=/1500x1000/filters:fill(auto,1)/terris-crispy-fried-chicken-legs-3056879-10_preview-5b05ec40a474be00362260d7.jpeg'),(28,'bbe8585d-ea34-11eb-863e-dc4a3ee3e76f','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ0Wi_Yf-V1yc_gruFfIvs4wJtvjngDsRG-kg&usqp=CAU'),(29,'be4e8e5a-ea34-11eb-863e-dc4a3ee3e76f','https://armyhaus.com/wp-content/uploads/2020/12/marinated-top-round-steak-3060302-hero-02-ed071d5d7e584bea82857112aa734a94.jpg'),(30,'c1133696-ea34-11eb-863e-dc4a3ee3e76f','https://islandlifenc.com/wp-content/uploads/2021/01/How-to-do-an-Oyster-Roast.jpg'),(31,'c3bec97b-ea34-11eb-863e-dc4a3ee3e76f','https://www.foxandbriar.com/wp-content/uploads/2020/09/Garlic-Bread-7-of-10.jpg'),(32,'c6c46ff2-ea34-11eb-863e-dc4a3ee3e76f','http://cdn.shopify.com/s/files/1/2956/7160/articles/sashimi_ca_hoi_tuoi.jpg?v=1536459593'),(33,'ccf0227f-ea34-11eb-863e-dc4a3ee3e76f','https://kenh14cdn.com/2019/7/16/2-15632570826061657541652.jpg'),(34,'cf6b73d3-ea34-11eb-863e-dc4a3ee3e76f','https://images.food52.com/9HR-m4RBTbuZpZah01hA4Jgco7s=/2016x1344/filters:format(webp)/a6022657-cd16-47ec-8d78-63f717d869f7--seafood_stew.jpg'),(35,'d210c347-ea34-11eb-863e-dc4a3ee3e76f','https://www.simplyrecipes.com/thmb/63rPxOV5ooiVaaczVVCGTrGWi_w=/1600x1067/filters:no_upscale():max_bytes(150000):strip_icc()/__opt__aboutcom__coeus__resources__content_migration__simply_recipes__uploads__2016__04__skillet-chicken-orzo-horiz-a-1600-17a598c79cc4a09aec515430ac51cb7.jpg'),(36,'d48f63ff-ea34-11eb-863e-dc4a3ee3e76f','https://imagesvc.meredithcorp.io/v3/mm/image?url=https%3A%2F%2Fimg1.cookinglight.timeinc.net%2Fsites%2Fdefault%2Ffiles%2Fstyles%2Fmedium_2x%2Fpublic%2Fimage%2F2016%2F09%2Fmain%2F1610p130-smoky-pan-seared-chicken-breasts.jpg%3Fitok%3DdS7OZvu0'),(37,'d7467585-ea34-11eb-863e-dc4a3ee3e76f','https://images.ctfassets.net/3s5io6mnxfqz/VTFplkmqQemCmaQgYEeGU/a70e0c14de212de3444179fee2223121/image1.jpg?fm=jpg&w=900&fl=progressive'),(38,'da59c856-ea34-11eb-863e-dc4a3ee3e76f','https://previews.123rf.com/images/photomaru/photomaru1011/photomaru101100054/8297195-orange-juice-and-slices-of-orange-isolated-on-white.jpg'),(39,'dd3dd1fe-ea34-11eb-863e-dc4a3ee3e76f','https://recipe52.com/wp-content/uploads/2020/01/Kiwi-Juice-recipe-6.jpg'),(40,'e045a73c-ea34-11eb-863e-dc4a3ee3e76f','https://www.onceuponachef.com/images/2014/03/Easy-Garlic-Butter-Shrimp-1.jpg'),(41,'10fdf55e-ee8c-11eb-b72b-dc4a3ee3e76f',NULL),(42,'144e68b3-ee8c-11eb-b72b-dc4a3ee3e76f',NULL),(43,'17096130-ee8c-11eb-b72b-dc4a3ee3e76f',NULL),(44,'19a92544-ee8c-11eb-b72b-dc4a3ee3e76f',NULL);
/*!40000 ALTER TABLE `picture` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-07-27 10:49:29
