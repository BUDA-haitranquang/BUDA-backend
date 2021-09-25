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
-- Table structure for table `picture`
--

DROP TABLE IF EXISTS picture;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE picture (
  picture_id bigint NOT NULL AUTO_INCREMENT,
  picture_uuid char(36) DEFAULT NULL,
  picture_link varchar(500) DEFAULT NULL,
  PRIMARY KEY (picture_id)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `picture`
--

INSERT INTO picture (picture_id, picture_uuid, picture_link) VALUES (1,'63f23d87-ea34-11eb-863e-dc4a3ee3e76f','https://upload.wikimedia.org/wikipedia/commons/f/fe/Viettel_logo_2021.svg');
INSERT INTO picture (picture_id, picture_uuid, picture_link) VALUES (2,'6eb7e71c-ea34-11eb-863e-dc4a3ee3e76f','https://cdn.tgdd.vn/Files/2015/04/21/634843/cach-dang-ki-cuoc-goi-va-nhan-tin-mien-phi-vinaphone-2.jpg');
INSERT INTO picture (picture_id, picture_uuid, picture_link) VALUES (3,'71610ee1-ea34-11eb-863e-dc4a3ee3e76f','https://i2.wp.com/marketingai.admicro.vn/wp-content/uploads/2018/11/bo-nhan-dien-thuong-hieu-mobifone-min.png?resize=766%2C216&ssl=1');
INSERT INTO picture (picture_id, picture_uuid, picture_link) VALUES (4,'73c5f7ee-ea34-11eb-863e-dc4a3ee3e76f','https://vnptvinaphone.net.vn/wp-content/uploads/2021/01/logo-vnpt-h%C3%A0-n%E1%BB%8Di.png');
INSERT INTO picture (picture_id, picture_uuid, picture_link) VALUES (5,'761d2a1e-ea34-11eb-863e-dc4a3ee3e76f','https://upload.wikimedia.org/wikipedia/vi/thumb/a/a8/Vietnamobile_Logo.svg/1200px-Vietnamobile_Logo.svg.png');
INSERT INTO picture (picture_id, picture_uuid, picture_link) VALUES (6,'790e264d-ea34-11eb-863e-dc4a3ee3e76f','https://upload.wikimedia.org/wikipedia/vi/7/7a/BeeLine_logo.png');
INSERT INTO picture (picture_id, picture_uuid, picture_link) VALUES (7,'7bd06f3f-ea34-11eb-863e-dc4a3ee3e76f','https://www.fpt.com.vn/Content/home/images/icon/ic-logo.png');
INSERT INTO picture (picture_id, picture_uuid, picture_link) VALUES (8,'7e471400-ea34-11eb-863e-dc4a3ee3e76f','https://cmc.com.vn/main/imgs/logo.svg');
INSERT INTO picture (picture_id, picture_uuid, picture_link) VALUES (9,'82c41462-ea34-11eb-863e-dc4a3ee3e76f','https://viettelco.net/wp-content/uploads/2020/01/Logo-Viettelco.png');
INSERT INTO picture (picture_id, picture_uuid, picture_link) VALUES (10,'88425931-ea34-11eb-863e-dc4a3ee3e76f','https://netnam.com/o/netnam/images/logo.png');
INSERT INTO picture (picture_id, picture_uuid, picture_link) VALUES (11,'8b53e0cf-ea34-11eb-863e-dc4a3ee3e76f','https://www.foodsafetynews.com/files/2020/06/raw-sirloin-steak-beef.jpg');
INSERT INTO picture (picture_id, picture_uuid, picture_link) VALUES (12,'8de64ada-ea34-11eb-863e-dc4a3ee3e76f','https://i2.wp.com/hygienicmeat.com/wp-content/uploads/2020/07/s735965455334319150_p6_i1_w2048.jpeg');
INSERT INTO picture (picture_id, picture_uuid, picture_link) VALUES (13,'903a19dc-ea34-11eb-863e-dc4a3ee3e76f','https://www.collinsdictionary.com/images/full/oyster_245235925.jpg');
INSERT INTO picture (picture_id, picture_uuid, picture_link) VALUES (14,'92e6a736-ea34-11eb-863e-dc4a3ee3e76f','https://static.julinse.com/i/a250031cfd4543cd-1024x684.jpg');
INSERT INTO picture (picture_id, picture_uuid, picture_link) VALUES (15,'9616c73c-ea34-11eb-863e-dc4a3ee3e76f','https://passiondelivery.com/pub/media/catalog/product/cache/7e6e59e80a69ca81b40190dbfa9e211f/5/7/57._pork_loin_skin-off.jpg');
INSERT INTO picture (picture_id, picture_uuid, picture_link) VALUES (16,'98fa6634-ea34-11eb-863e-dc4a3ee3e76f','https://media.cooky.vn/images/blog-2016/cach-phan-biet-nuoc-mam-truyen-thong-va-nuoc-cham-cong-nghiep%201.jpg');
INSERT INTO picture (picture_id, picture_uuid, picture_link) VALUES (17,'9d09157e-ea34-11eb-863e-dc4a3ee3e76f','http://kolala.com.vn/upload/public/TI%C3%AAu/where-does-black-pepper-come-from.jpg');
INSERT INTO picture (picture_id, picture_uuid, picture_link) VALUES (18,'9fc19aae-ea34-11eb-863e-dc4a3ee3e76f','https://cdn-prod.medicalnewstoday.com/content/images/articles/322/322745/salt-shaker.jpg');
INSERT INTO picture (picture_id, picture_uuid, picture_link) VALUES (19,'a2a5a086-ea34-11eb-863e-dc4a3ee3e76f','https://parenting.firstcry.ae/memories/getMyMemoryImages/2018/04/528721780-H.jpg');
INSERT INTO picture (picture_id, picture_uuid, picture_link) VALUES (20,'a5c35ce1-ea34-11eb-863e-dc4a3ee3e76f','https://bizweb.dktcdn.net/100/408/264/products/5116995hanh-tay-onion-000000005272-jpeg.jpg?v=1603792333943');
INSERT INTO picture (picture_id, picture_uuid, picture_link) VALUES (21,'a8a492ca-ea34-11eb-863e-dc4a3ee3e76f','https://www.escoffieronline.com/wp-content/uploads/2013/04/iStock-995038782-small.jpg');
INSERT INTO picture (picture_id, picture_uuid, picture_link) VALUES (22,'ab75fcd4-ea34-11eb-863e-dc4a3ee3e76f','https://st.depositphotos.com/1049691/4267/i/950/depositphotos_42673487-stock-photo-fresh-orange.jpg');
INSERT INTO picture (picture_id, picture_uuid, picture_link) VALUES (23,'ae317a3b-ea34-11eb-863e-dc4a3ee3e76f','http://suckhoedoisong.vn/Images/thutrang/2016/04/25/tanghoocmon_1.jpg');
INSERT INTO picture (picture_id, picture_uuid, picture_link) VALUES (24,'b0a5b37c-ea34-11eb-863e-dc4a3ee3e76f','https://wallpapercave.com/wp/wp2691685.jpg');
INSERT INTO picture (picture_id, picture_uuid, picture_link) VALUES (25,'b47caeb9-ea34-11eb-863e-dc4a3ee3e76f','https://images.thefishsite.com/fish%2Farticles%2Fprocessing%2Fsalmon-fillet.jpeg');
INSERT INTO picture (picture_id, picture_uuid, picture_link) VALUES (26,'b70964b4-ea34-11eb-863e-dc4a3ee3e76f','https://hips.hearstapps.com/vidthumb/images/grilled-shrimp-horizontal-1553275047.jpg');
INSERT INTO picture (picture_id, picture_uuid, picture_link) VALUES (27,'b9795e2e-ea34-11eb-863e-dc4a3ee3e76f','https://www.thespruceeats.com/thmb/RuiS8PlPaUkJFbrH8skujr90Q8A=/1500x1000/filters:fill(auto,1)/terris-crispy-fried-chicken-legs-3056879-10_preview-5b05ec40a474be00362260d7.jpeg');
INSERT INTO picture (picture_id, picture_uuid, picture_link) VALUES (28,'bbe8585d-ea34-11eb-863e-dc4a3ee3e76f','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ0Wi_Yf-V1yc_gruFfIvs4wJtvjngDsRG-kg&usqp=CAU');
INSERT INTO picture (picture_id, picture_uuid, picture_link) VALUES (29,'be4e8e5a-ea34-11eb-863e-dc4a3ee3e76f','https://armyhaus.com/wp-content/uploads/2020/12/marinated-top-round-steak-3060302-hero-02-ed071d5d7e584bea82857112aa734a94.jpg');
INSERT INTO picture (picture_id, picture_uuid, picture_link) VALUES (30,'c1133696-ea34-11eb-863e-dc4a3ee3e76f','https://islandlifenc.com/wp-content/uploads/2021/01/How-to-do-an-Oyster-Roast.jpg');
INSERT INTO picture (picture_id, picture_uuid, picture_link) VALUES (31,'c3bec97b-ea34-11eb-863e-dc4a3ee3e76f','https://www.foxandbriar.com/wp-content/uploads/2020/09/Garlic-Bread-7-of-10.jpg');
INSERT INTO picture (picture_id, picture_uuid, picture_link) VALUES (32,'c6c46ff2-ea34-11eb-863e-dc4a3ee3e76f','http://cdn.shopify.com/s/files/1/2956/7160/articles/sashimi_ca_hoi_tuoi.jpg?v=1536459593');
INSERT INTO picture (picture_id, picture_uuid, picture_link) VALUES (33,'ccf0227f-ea34-11eb-863e-dc4a3ee3e76f','https://kenh14cdn.com/2019/7/16/2-15632570826061657541652.jpg');
INSERT INTO picture (picture_id, picture_uuid, picture_link) VALUES (34,'cf6b73d3-ea34-11eb-863e-dc4a3ee3e76f','https://images.food52.com/9HR-m4RBTbuZpZah01hA4Jgco7s=/2016x1344/filters:format(webp)/a6022657-cd16-47ec-8d78-63f717d869f7--seafood_stew.jpg');
INSERT INTO picture (picture_id, picture_uuid, picture_link) VALUES (35,'d210c347-ea34-11eb-863e-dc4a3ee3e76f','https://www.simplyrecipes.com/thmb/63rPxOV5ooiVaaczVVCGTrGWi_w=/1600x1067/filters:no_upscale():max_bytes(150000):strip_icc()/__opt__aboutcom__coeus__resources__content_migration__simply_recipes__uploads__2016__04__skillet-chicken-orzo-horiz-a-1600-17a598c79cc4a09aec515430ac51cb7.jpg');
INSERT INTO picture (picture_id, picture_uuid, picture_link) VALUES (36,'d48f63ff-ea34-11eb-863e-dc4a3ee3e76f','https://imagesvc.meredithcorp.io/v3/mm/image?url=https%3A%2F%2Fimg1.cookinglight.timeinc.net%2Fsites%2Fdefault%2Ffiles%2Fstyles%2Fmedium_2x%2Fpublic%2Fimage%2F2016%2F09%2Fmain%2F1610p130-smoky-pan-seared-chicken-breasts.jpg%3Fitok%3DdS7OZvu0');
INSERT INTO picture (picture_id, picture_uuid, picture_link) VALUES (37,'d7467585-ea34-11eb-863e-dc4a3ee3e76f','https://images.ctfassets.net/3s5io6mnxfqz/VTFplkmqQemCmaQgYEeGU/a70e0c14de212de3444179fee2223121/image1.jpg?fm=jpg&w=900&fl=progressive');
INSERT INTO picture (picture_id, picture_uuid, picture_link) VALUES (38,'da59c856-ea34-11eb-863e-dc4a3ee3e76f','https://previews.123rf.com/images/photomaru/photomaru1011/photomaru101100054/8297195-orange-juice-and-slices-of-orange-isolated-on-white.jpg');
INSERT INTO picture (picture_id, picture_uuid, picture_link) VALUES (39,'dd3dd1fe-ea34-11eb-863e-dc4a3ee3e76f','https://recipe52.com/wp-content/uploads/2020/01/Kiwi-Juice-recipe-6.jpg');
INSERT INTO picture (picture_id, picture_uuid, picture_link) VALUES (40,'e045a73c-ea34-11eb-863e-dc4a3ee3e76f','https://www.onceuponachef.com/images/2014/03/Easy-Garlic-Butter-Shrimp-1.jpg');
INSERT INTO picture (picture_id, picture_uuid, picture_link) VALUES (41,'10fdf55e-ee8c-11eb-b72b-dc4a3ee3e76f','https://www.slideteam.net/media/catalog/product/cache/960x720/m/e/merchandise_buying_6_month_plan_ppt_show_slideshow_Slide01.jpg');
INSERT INTO picture (picture_id, picture_uuid, picture_link) VALUES (42,'144e68b3-ee8c-11eb-b72b-dc4a3ee3e76f','https://mir-s3-cdn-cf.behance.net/project_modules/1400/b5594273128611.5bff152c43fec.jpg');
INSERT INTO picture (picture_id, picture_uuid, picture_link) VALUES (43,'17096130-ee8c-11eb-b72b-dc4a3ee3e76f','https://docs.oracle.com/cd/E75762_01/assortplan/pdf/141/html/ug/img/bplan_bm_rev_receiptpl_look.png');
INSERT INTO picture (picture_id, picture_uuid, picture_link) VALUES (44,'19a92544-ee8c-11eb-b72b-dc4a3ee3e76f','https://images.slideplayer.com/13/3902380/slides/slide_8.jpg');
INSERT INTO picture (picture_id, picture_uuid, picture_link) VALUES (45,'a1d87c69-1ce8-11ec-bdd5-dc4a3ee3e76f','https://image.shutterstock.com/image-vector/user-login-authenticate-icon-human-260nw-1365533969.jpg');
INSERT INTO picture (picture_id, picture_uuid, picture_link) VALUES (46,'a657f13e-1ce8-11ec-bdd5-dc4a3ee3e76f','https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/2048px-User_icon_2.svg.png');
INSERT INTO picture (picture_id, picture_uuid, picture_link) VALUES (47,'ac526434-1ce8-11ec-bdd5-dc4a3ee3e76f','https://upload.wikimedia.org/wikipedia/commons/thumb/c/ce/User-info.svg/768px-User-info.svg.png');
INSERT INTO picture (picture_id, picture_uuid, picture_link) VALUES (48,'b056f106-1ce8-11ec-bdd5-dc4a3ee3e76f','http://simpleicon.com/wp-content/uploads/user1.png');
INSERT INTO picture (picture_id, picture_uuid, picture_link) VALUES (49,'b50a980c-1ce8-11ec-bdd5-dc4a3ee3e76f','https://www.kindpng.com/picc/m/24-248253_user-profile-default-image-png-clipart-png-download.png');
INSERT INTO picture (picture_id, picture_uuid, picture_link) VALUES (50,'bad0b505-1ce8-11ec-bdd5-dc4a3ee3e76f','https://image.shutterstock.com/image-vector/default-avatar-profile-icon-vector-260nw-1725655669.jpg');
INSERT INTO picture (picture_id, picture_uuid, picture_link) VALUES (51,'c080586f-1ce8-11ec-bdd5-dc4a3ee3e76f','https://cdn-icons-png.flaticon.com/512/149/149071.png');
INSERT INTO picture (picture_id, picture_uuid, picture_link) VALUES (52,'c53d5647-1ce8-11ec-bdd5-dc4a3ee3e76f','https://upload.wikimedia.org/wikipedia/commons/9/99/Sample_User_Icon.png');
INSERT INTO picture (picture_id, picture_uuid, picture_link) VALUES (53,'c90b0a70-1ce8-11ec-bdd5-dc4a3ee3e76f','https://cdn.icon-icons.com/icons2/2506/PNG/512/user_icon_150670.png');
INSERT INTO picture (picture_id, picture_uuid, picture_link) VALUES (54,'cc8a7df7-1ce8-11ec-bdd5-dc4a3ee3e76f','https://icon-library.com/images/google-user-icon/google-user-icon-11.jpg');
INSERT INTO picture (picture_id, picture_uuid, picture_link) VALUES (55,'d0604590-1ce8-11ec-bdd5-dc4a3ee3e76f','https://sothis.es/wp-content/plugins/all-in-one-seo-pack/images/default-user-image.png');
INSERT INTO picture (picture_id, picture_uuid, picture_link) VALUES (56,'d377b325-1ce8-11ec-bdd5-dc4a3ee3e76f','https://cdn.pixabay.com/photo/2020/07/01/12/58/icon-5359553_1280.png');
INSERT INTO picture (picture_id, picture_uuid, picture_link) VALUES (57,'d6f82ce8-1ce8-11ec-bdd5-dc4a3ee3e76f','https://t4.ftcdn.net/jpg/02/29/75/83/360_F_229758328_7x8jwCwjtBMmC6rgFzLFhZoEpLobB6L8.jpg');
INSERT INTO picture (picture_id, picture_uuid, picture_link) VALUES (58,'daed0be0-1ce8-11ec-bdd5-dc4a3ee3e76f','https://thumbs.dreamstime.com/b/default-avatar-profile-icon-vector-social-media-user-image-182145777.jpg');
INSERT INTO picture (picture_id, picture_uuid, picture_link) VALUES (59,'ddd13612-1ce8-11ec-bdd5-dc4a3ee3e76f','https://cdn5.vectorstock.com/i/1000x1000/04/09/user-icon-vector-5770409.jpg');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed
