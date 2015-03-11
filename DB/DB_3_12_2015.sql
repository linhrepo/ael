CREATE DATABASE  IF NOT EXISTS `ael` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `ael`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: 127.0.0.1    Database: ael
-- ------------------------------------------------------
-- Server version	5.5.20

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `accountingcus`
--

DROP TABLE IF EXISTS `accountingcus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `accountingcus` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `creator` bigint(20) DEFAULT NULL,
  `updator` bigint(20) DEFAULT NULL,
  `createdDate` datetime DEFAULT NULL,
  `lastUpdateDate` datetime DEFAULT NULL,
  `refNo` varchar(45) DEFAULT NULL,
  `docsgeneral` bigint(20) DEFAULT NULL,
  `counting` bigint(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `custodocgeneral_idx` (`docsgeneral`),
  CONSTRAINT `custodocgeneral` FOREIGN KEY (`docsgeneral`) REFERENCES `docsgeneral` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accountingcus`
--

LOCK TABLES `accountingcus` WRITE;
/*!40000 ALTER TABLE `accountingcus` DISABLE KEYS */;
INSERT INTO `accountingcus` VALUES (8,-2,-2,'2015-03-04 00:00:00','2015-03-10 23:41:34','DBHQLNI0009',71,NULL),(9,-2,-2,'2015-03-08 00:00:00','2015-03-10 23:49:04','DBHQLNI0002',67,NULL);
/*!40000 ALTER TABLE `accountingcus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `accountingcusdetail`
--

DROP TABLE IF EXISTS `accountingcusdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `accountingcusdetail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `accountingcus` bigint(20) DEFAULT NULL,
  `generalVat` decimal(5,2) DEFAULT NULL,
  `description` bigint(20) DEFAULT NULL,
  `note` varchar(2000) DEFAULT NULL,
  `name` bigint(20) DEFAULT NULL,
  `invoice` varchar(1000) DEFAULT NULL,
  `quantity20` int(10) DEFAULT NULL,
  `quantity40` int(10) DEFAULT NULL,
  `quantityLCL` int(10) DEFAULT NULL,
  `total` decimal(20,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `nameDebit_idx` (`name`),
  KEY `toDescCon_idx` (`description`),
  KEY `acountingToDocs_idx` (`accountingcus`),
  CONSTRAINT `acountingToDocs` FOREIGN KEY (`accountingcus`) REFERENCES `accountingcus` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `nameDebit` FOREIGN KEY (`name`) REFERENCES `configuration` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `toDescCon` FOREIGN KEY (`description`) REFERENCES `configuration` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accountingcusdetail`
--

LOCK TABLES `accountingcusdetail` WRITE;
/*!40000 ALTER TABLE `accountingcusdetail` DISABLE KEYS */;
INSERT INTO `accountingcusdetail` VALUES (7,8,4.00,261,'435',260,'435',3,0,0,43.00),(11,8,0.00,261,'',260,'',0,4,0,0.00),(12,8,34.00,262,'324',260,'324',0,0,5,23.00),(14,9,234.00,262,'234',260,'234',0,2,5,234.00),(25,9,4.00,262,'5',260,'5',0,4,4,4.00);
/*!40000 ALTER TABLE `accountingcusdetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `app_user`
--

DROP TABLE IF EXISTS `app_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `app_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account_expired` bit(1) NOT NULL,
  `account_locked` bit(1) NOT NULL,
  `address` varchar(150) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `country` varchar(100) DEFAULT NULL,
  `postal_code` varchar(15) DEFAULT NULL,
  `province` varchar(100) DEFAULT NULL,
  `credentials_expired` bit(1) NOT NULL,
  `email` varchar(255) NOT NULL,
  `account_enabled` bit(1) DEFAULT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `password_hint` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `username` varchar(50) NOT NULL,
  `version` int(11) DEFAULT NULL,
  `website` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_user`
--

LOCK TABLES `app_user` WRITE;
/*!40000 ALTER TABLE `app_user` DISABLE KEYS */;
INSERT INTO `app_user` VALUES (-3,'\0','\0','','Denver','US','80210','CO','\0','two_roles_user@appfuse.org','','Two Roles','User','$2a$10$bH/ssqW8OhkTlIso9/yakubYODUOmh.6m5HEJvcBq3t3VdBh7ebqO','Not a female kitty.','','two_roles_user',1,'http://raibledesigns.com'),(-2,'\0','\0','','Denver','US','80210','CO','\0','matt@raibledesigns.com','','Matt','Raible','$2a$10$bH/ssqW8OhkTlIso9/yakubYODUOmh.6m5HEJvcBq3t3VdBh7ebqO','Not a female kitty.','','admin',1,'http://raibledesigns.com'),(-1,'\0','\0','','Denver','US','80210','CO','\0','matt_raible@yahoo.com','','Tomcat','User','$2a$10$CnQVJ9bsWBjMpeSKrrdDEeuIptZxXrwtI6CZ/OgtNxhIgpKxXeT9y','A male kitty.','','user',1,'http://tomcat.apache.org'),(2,'\0','\0','','','','','','\0','tlinh211110@yahoo.com','','linh','vo','$2a$10$pGFCNkhsmBPopOflv128TuXzFmiNu/ZLbIlVTR78w7qB/tBIn2Ai6','liv1hc','09090909','liv1hc',4,'adsdsad'),(4,'\0','\0','','','','','','\0','tlinh21110@yahoo.com','','Linh','Vo','$2a$10$pGFCNkhsmBPopOflv128TuXzFmiNu/ZLbIlVTR78w7qB/tBIn2Ai6','from 1 to 9','0912344545','saleman',2,''),(5,'\0','\0','','','','','','\0','tlinh2110@yahoo.com','','linh ','vo','$2a$10$3.sRzOGZargn5.syZC5Tdex.uarsilLNAAxlkvPMF0G1Da6rS/XFy','liv1hc','','linh',0,'');
/*!40000 ALTER TABLE `app_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `configuration`
--

DROP TABLE IF EXISTS `configuration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `configuration` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `value` varchar(255) DEFAULT NULL,
  `type` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `enabled` int(1) DEFAULT '1',
  `implyActions` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=269 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `configuration`
--

LOCK TABLES `configuration` WRITE;
/*!40000 ALTER TABLE `configuration` DISABLE KEYS */;
INSERT INTO `configuration` VALUES (-10,'Chi phí chi hộ',20,'Thông tin customs service',0,'{1}'),(-2,'LCL',7,NULL,0,NULL),(-1,'FCL',7,NULL,0,NULL),(60,'Công ty nước ngoài',4,'Thông tin customer',1,NULL),(61,'Công ty Việt Nam',4,'Thông tin customer',1,NULL),(62,'Công ty đầu tư nước ngoài',4,'Thông tin customer',1,NULL),(63,'Công ty liên doanh',4,'Thông tin customer',1,NULL),(64,'Doanh nghiệp chế xuất',4,'Thông tin customer',1,NULL),(65,'Văn phòng đại diện',4,'Thông tin customer',1,NULL),(66,'Vietnam',5,'Thông tin customer',1,NULL),(67,'Thailand',5,'Thông tin customer',1,NULL),(68,'Singapore',5,'Thông tin customer',1,NULL),(69,'Campodia',5,'Thông tin customer',1,NULL),(70,'Malaysia',5,'Thông tin customer',1,NULL),(71,'Indonesia',5,'Thông tin customer',1,NULL),(72,'Philippine',5,'Thông tin customer',1,NULL),(73,'Myanmar',5,'Thông tin customer',1,NULL),(74,'China',5,'Thông tin customer',1,NULL),(75,'Korea',5,'Thông tin customer',1,NULL),(76,'Japan',5,'Thông tin customer',1,NULL),(77,'Taiwan',5,'Thông tin customer',1,NULL),(78,'Hongkong',5,'Thông tin customer',1,NULL),(79,'Germany',5,'Thông tin customer',1,NULL),(80,'Denmark',5,'Thông tin customer',1,NULL),(81,'France',5,'Thông tin customer',1,NULL),(82,'England',5,'Thông tin customer',1,NULL),(83,'Nertherland',5,'Thông tin customer',1,NULL),(84,'Australia',5,'Thông tin customer',1,NULL),(85,'USA',5,'Thông tin customer',1,NULL),(86,'Dịch vụ hải quan',0,'Thông tin báo giá',1,NULL),(87,'Dịch vụ vận chuyển',0,'Thông tin báo giá',1,NULL),(88,'Dịch vụ hải quan và vận chuyển',0,'Thông tin báo giá',1,NULL),(89,'Dịch vụ triển lãm',0,'Thông tin báo giá',1,NULL),(90,'Dịch vụ đóng gói, lắp đặt',0,'Thông tin báo giá',1,NULL),(91,'VND',1,'Thông tin báo giá',1,NULL),(92,'USD',1,'Thông tin báo giá',1,NULL),(93,'SGD',1,'Thông tin báo giá',1,NULL),(94,'AUD',1,'Thông tin báo giá',1,NULL),(95,'Cái',3,'Thông tin báo giá',1,NULL),(96,'Xuất kinh doanh',11,'Thông tin customs service',1,NULL),(97,'Xuất phi mậu dịch',11,'Thông tin customs service',1,NULL),(98,'Tạm xuất tái nhập',11,'Thông tin customs service',1,NULL),(99,'Nhập SXXK',11,'Thông tin customs service',1,NULL),(100,'Tái xuất',11,'Thông tin customs service',1,NULL),(101,'Xuất SXXK',11,'Thông tin customs service',1,NULL),(102,'Nhập kinh doanh',11,'Thông tin customs service',1,NULL),(103,'Nhập phi mậu dịch',11,'Thông tin customs service',1,NULL),(104,'Tạm nhập tái xuất',11,'Thông tin customs service',1,NULL),(105,'Đường không',6,'Thông tin customs service',1,NULL),(106,'Biển',6,'Thông tin customs service',1,NULL),(107,'NYK',12,'Thông tin customs service',1,NULL),(108,'APL',12,'Thông tin customs service',1,NULL),(109,'MOL',12,'Thông tin customs service',1,NULL),(110,'OOCL',12,'Thông tin customs service',1,NULL),(111,'COSCO',12,'Thông tin customs service',1,NULL),(112,'PIL',12,'Thông tin customs service',1,NULL),(113,'NAMSUNG',12,'Thông tin customs service',1,NULL),(114,'INTERASIA',12,'Thông tin customs service',1,NULL),(115,'TSLINE',12,'Thông tin customs service',1,NULL),(116,'MYANGMINH',12,'Thông tin customs service',1,NULL),(117,'SITC',12,'Thông tin customs service',1,NULL),(118,'RCL',12,'Thông tin customs service',1,NULL),(119,'KLINE',12,'Thông tin customs service',1,NULL),(120,'UASC',12,'Thông tin customs service',1,NULL),(121,'MAERSK',12,'Thông tin customs service',1,NULL),(122,'BIEN DONG',12,'Thông tin customs service',1,NULL),(123,'MARINA',12,'Thông tin customs service',1,NULL),(124,'VOSCO',12,'Thông tin customs service',1,NULL),(125,'SINOKOR',12,'Thông tin customs service',1,NULL),(126,'DHL',12,'Thông tin customs service',1,NULL),(127,'FEDEX',12,'Thông tin customs service',1,NULL),(128,'TNT',12,'Thông tin customs service',1,NULL),(129,'Quản lý hàng đầu tư HCM',13,'Thông tin customs service',1,NULL),(130,'Mỹ Phước 1',13,'Thông tin customs service',1,NULL),(131,'Cảng Bình Dương',13,'Thông tin customs service',1,NULL),(132,'VSIP',13,'Thông tin customs service',1,NULL),(133,'Biên Hoà',13,'Thông tin customs service',1,NULL),(134,'Long Bình Tân',13,'Thông tin customs service',1,NULL),(135,'Cảng Cát Lái',13,'Thông tin customs service',1,NULL),(136,'Khánh Hội',13,'Thông tin customs service',1,NULL),(137,'EMS',12,'Thông tin customs service',1,NULL),(138,'UPS',12,'Thông tin customs service',1,NULL),(139,'INTER LOGISTICS',12,'Thông tin customs service',1,NULL),(140,'KONOIKE',12,'Thông tin customs service',1,NULL),(141,'SUPERSTAR',12,'Thông tin customs service',1,NULL),(142,'VINATRANS',12,'Thông tin customs service',1,NULL),(143,'DACHSER',12,'Thông tin customs service',1,NULL),(144,'ANC',12,'Thông tin customs service',1,NULL),(145,'LOGITEM',12,'Thông tin customs service',1,NULL),(146,'NNR',12,'Thông tin customs service',1,NULL),(147,'THAMI',12,'Thông tin customs service',1,NULL),(148,'EVERGREEN',12,'Thông tin customs service',1,NULL),(149,'EVERICH',12,'Thông tin customs service',1,NULL),(150,'P&O',12,'Thông tin customs service',1,NULL),(151,'PT SHIPPING',12,'Thông tin customs service',1,NULL),(152,'KHAI MINH',12,'Thông tin customs service',1,NULL),(153,'SCHENKER',12,'Thông tin customs service',1,NULL),(154,'AGILITY',12,'Thông tin customs service',1,NULL),(155,'VICT',13,'Thông tin customs service',1,NULL),(156,'Bến Nghé',13,'Thông tin customs service',1,NULL),(157,'ICD TRANSIMEX',13,'Thông tin customs service',1,NULL),(158,'ICD Phước Long 1',13,'Thông tin customs service',1,NULL),(159,'ICD Phước Long 3',13,'Thông tin customs service',1,NULL),(160,'ICD TAMAMEXCO',13,'Thông tin customs service',1,NULL),(161,'ICD SOTRANS',13,'Thông tin customs service',1,NULL),(162,'Cảng Hải Phòng',13,'Thông tin customs service',1,NULL),(163,'Bắc Hà Nội',13,'Thông tin customs service',1,NULL),(164,'1',8,'Thông tin customs service',1,NULL),(165,'2',8,'Thông tin customs service',1,NULL),(166,'3',8,'Thông tin customs service',1,NULL),(167,'20 DR',10,'Thông tin CONT',1,NULL),(168,'20 FR',10,'Thông tin CONT',1,NULL),(169,'20 OP',10,'Thông tin CONT',1,NULL),(170,'20 TK',10,'Thông tin CONT',1,NULL),(171,'40 DR',10,'Thông tin CONT',1,NULL),(172,'40 HQ',10,'Thông tin CONT',1,NULL),(173,'40 FR',10,'Thông tin CONT',1,NULL),(174,'40 OP',10,'Thông tin CONT',1,NULL),(175,'40 TK',10,'Thông tin CONT',1,NULL),(176,'45 HQ',10,'Thông tin CONT',1,NULL),(178,'Chi phí AEL',20,'Thông tin customs service',1,NULL),(179,'Cước CONT',20,'Thông tin customs service',1,NULL),(180,'Khác',20,'Thông tin customs service',1,NULL),(181,'Cước vận chuyển nội địa',19,'Thông tin báo giá',1,NULL),(182,'Cước vận chuyển quốc tế',19,'Thông tin báo giá',1,NULL),(183,'Phí hạ',19,'Thông tin báo giá',1,NULL),(184,'Phí nâng',19,'Thông tin báo giá',1,NULL),(185,'Phí chứng từ',19,'Thông tin báo giá',1,NULL),(186,'Phí D/O',19,'Thông tin báo giá',1,NULL),(187,'Phí xếp dỡ',19,'Thông tin báo giá',1,NULL),(188,'Phí lưu bãi',19,'Thông tin báo giá',1,NULL),(189,'Phí lưu cont',19,'Thông tin báo giá',1,NULL),(190,'Phí lưu kho',19,'Thông tin báo giá',1,NULL),(191,'Phí vệ sinh',19,'Thông tin báo giá',1,NULL),(192,'Phí sữa chữa',19,'Thông tin báo giá',1,NULL),(193,'Phí kiểm dịch',19,'Thông tin báo giá',1,NULL),(194,'Chi phí hải quan',19,'Thông tin báo giá',1,NULL),(195,'Lệ phí hải quan',19,'Thông tin báo giá',1,NULL),(196,'Phí bốc hàng',19,'Thông tin báo giá',1,NULL),(197,'Phí ký chứng từ',19,'Thông tin báo giá',1,NULL),(198,'Phí thanh lý',19,'Thông tin báo giá',1,NULL),(199,'Hàng thuỷ bộ',7,'Thông tin Transportation Services',1,NULL),(200,'Xuất',14,'Thông tin Transportation Services',1,NULL),(201,'Nhập',14,'Thông tin Transportation Services',1,NULL),(202,'Nội địa ',14,'Thông tin Transportation Services',1,NULL),(203,'Bắc - Nam',15,'Thông tin Transportation Services',1,NULL),(204,'Nam - Bắc',15,'Thông tin Transportation Services',1,NULL),(205,'Tạm xuất',17,'Thông tin exhibition',1,NULL),(206,'Tạm nhập',17,'Thông tin exhibition',1,NULL),(207,'Xuất KD',17,'Thông tin exhibition',1,NULL),(208,'Tái nhập',17,'Thông tin exhibition',1,NULL),(209,'Nhập sau',17,'Thông tin exhibition',1,NULL),(210,'Onsite',17,'Thông tin exhibition',1,NULL),(211,'Phí xin GP',21,'Thông tin exhibition',1,NULL),(212,'Local charge at depart',21,'Thông tin exhibition',1,NULL),(213,'Local charge at dest',21,'Thông tin exhibition',1,NULL),(214,'Phí onsite',21,'Thông tin exhibition',1,NULL),(215,'Phí XN - CN',21,'Thông tin exhibition',1,NULL),(216,'Phí giao nhận',21,'Thông tin exhibition',1,NULL),(217,'Phí OF',21,'Thông tin exhibition',1,NULL),(218,'Phí đại lý',21,'Thông tin exhibition',1,NULL),(219,'Tiền thuế XNK',21,'Thông tin exhibition',1,NULL),(220,'Phí khác',21,'Thông tin exhibition',1,NULL),(221,'An Hòa - 0915640077 anh Hùng',16,'',1,NULL),(222,'AN LONG TRƯỜNG - CTY CP AN LONG TRƯỜNG MST 03010271497',16,'',1,NULL),(223,'Anh Bình - 0938.450620',16,'',1,NULL),(224,'Anh Linh',16,'',1,NULL),(225,' Anh Nam 0919518468',16,'',1,NULL),(226,'Anh Truong - 0909295249',16,'',1,NULL),(227,'Anh Vuong - 0917192206',16,NULL,1,NULL),(228,'Đông Ngàn - CTY TNHH SX TM DV ĐÔNG NGÀN -  0906.369.499 A. Thuong',16,NULL,1,NULL),(229,'Đông Tú - CTY TNHH - VT ĐÔNG TÚ - 0909.919.667 A. Đông',16,NULL,1,NULL),(230,'Đức Truyền - CTY TNHH TM DV VẬN TẢI ĐỨC TRUYỀN - 0938.29.57.67 C. Thuy',16,NULL,1,NULL),(231,'Hoàng Khanh - 0918.369159 A. Hoàng',16,NULL,1,NULL),(232,'Hồng Toán - CTY TNHH TM DV VẬN TẢI HỒNG TOÁN MST 0305605264 - 0908.21.21.36 - A. Toán',16,'',1,NULL),(233,'ITL - CTY CP GIAO NHẬN VÀ VẬN CHUYỂN IN DO TRẦN MST 0301909173 - 0984.70.14.15 A. Toàn',16,'',1,NULL),(234,'Kiên Cuong - 0908200200 A Cuong',16,'',1,NULL),(235,'Lâm Vinh - CTY TNHH VẬN TẢI LÂM VINH MST 0909988360 - 0903728551 A Vinh',16,'',1,NULL),(236,'Liên Trung - 0983888289 Mr. Lượng',16,'',1,NULL),(237,'Long Phu - CTY TNHH TM DV VẬN TẢI LONG PHÚ MST 0311947409 - 0914.052.488 A NHẬT',16,'',1,NULL),(238,'Minh Nghĩa - 003807491 A Nghĩa',16,'',1,NULL),(239,'Nam Long - CTY TNHH GIẢI PHÁP VẬN CHUYỂN NAM LONG - MST 0311857799 - 0914.722.246 A SON',16,'',1,NULL),(240,'Ngọc Báu - 0903 704 837 Mr BÁU ',16,'',1,NULL),(241,'Ngọc Phú - CTY TNHH VẬN TẢI NGỌC PHÚ - MST 0308982214 - 0919.17.17.13 A THUAN',16,'',1,NULL),(242,'Nhut Quang - DNTN VẬN TẢI NHỰT QUANG MST 3601221915 - 01244.907.778 A NHUT',16,'',1,NULL),(243,'Phú Mỹ - 0913.856667 A Thịnh',16,'',1,NULL),(244,'Phương Nhung - 0909576486 A Phương',16,'',1,NULL),(245,'Quốc Nghĩa - CTY TNHH MTV VẬN TẢI QUỐC NGHĨA MST 3702070606 - 0906396289 ANH ÂN',16,'',1,NULL),(246,'Song Hà - A Phuong 0989847879 ',16,'',1,NULL),(247,'Song Long - CTY TNHH VẬN TẢI XUẤT KHẨU SONG LONG MST 0312864573 - 0938.411.087 A. Tân',16,'',1,NULL),(248,'Tam - TY TNHH GIAO NHẬN VẬN TẢI T.A.M MST 0310906475 - 0916.699.634 A. Tung',16,'',1,NULL),(249,'Thuận Hiếu - 0937.331.078 Ms.VÂN',16,'',1,NULL),(250,'Thuận Phong - CTY CP GIAO NHẬN VẬN TẢI THUẬN PHONG MST 0312038383 - 0982442522 A Hung',16,'',1,NULL),(251,'Trung Việt',16,'',1,NULL),(252,'Trung Nghĩa - CTY TNHH TM VÀ DỊCH VỤ VẬN TẢI TRUNG NGHĨA MST 0200781872',16,'',1,NULL),(253,'Trường Lợi - CTY TNHH DỊCH VỤ VẬN TẢI TRƯỞNG LỢI MST 03039911960 - 0918.066.764 A NGHIA',16,'',1,NULL),(254,'Việt Hoa = NAM VÂN - 0983 799 072 A CHUNG',16,'',1,NULL),(255,'VINATRA - 0906368177 ANH HẢI',16,'',1,NULL),(256,'Phương Lâm - CTY CP VẬN TẢI THƯƠNG MẠI PHƯƠNG LÂM MST 0200744976',16,'',1,NULL),(257,'Bình Minh - CTY CP VẬT TƯ CÔNG NGHIỆP BÌNH MINH MST 0200763993',16,'',1,NULL),(258,'V & T',16,'',1,NULL),(259,'Cẩm Hạnh - DOANH NGHIỆP TƯ NHÂN CẨM HẠNH MST 3900313219',16,'',1,NULL),(260,'Phí dịch vụ hải quan và vận chuyển',23,'',1,NULL),(261,'Customs clearance  & Transportation fee',24,'',1,NULL),(262,'Dịch vụ',24,NULL,1,NULL),(263,'Test',23,NULL,1,NULL),(264,'SEA',6,NULL,1,NULL),(265,'INWARD HANDLING CHARGE',25,NULL,1,NULL),(266,'Handling Charge',26,NULL,1,NULL),(267,'OUTWARD HANDLING CHARGE',25,NULL,1,NULL),(268,'4',26,NULL,1,NULL);
/*!40000 ALTER TABLE `configuration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contseal`
--

DROP TABLE IF EXISTS `contseal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contseal` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `docsgeneral` bigint(20) DEFAULT NULL,
  `noOfCont` varchar(255) DEFAULT NULL,
  `typeOfCont` bigint(20) DEFAULT NULL,
  `noOfPKGS` int(11) DEFAULT NULL,
  `noSeal` varchar(45) DEFAULT NULL,
  `weight` decimal(15,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `toParentPackage` (`docsgeneral`),
  KEY `toContCOnt_idx` (`typeOfCont`),
  CONSTRAINT `toContCOnt` FOREIGN KEY (`typeOfCont`) REFERENCES `configuration` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `toGenerals` FOREIGN KEY (`docsgeneral`) REFERENCES `docsgeneral` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contseal`
--

LOCK TABLES `contseal` WRITE;
/*!40000 ALTER TABLE `contseal` DISABLE KEYS */;
INSERT INTO `contseal` VALUES (26,67,'CON sé',169,243434,'tê lê',32434.00),(27,68,'32asd',172,34234,'wasdasd',3344.00),(28,69,'',167,0,'',0.00),(29,72,'sdfdsf',NULL,2432,'sdfsdf',324.00),(30,67,'sadas',171,4232,'sad',23423.00),(31,67,'32423',172,234324,'324',23434.00),(32,68,'343',170,342,'324',234.00);
/*!40000 ALTER TABLE `contseal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customers` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `taxno` varchar(45) DEFAULT NULL,
  `tel` varchar(45) DEFAULT NULL,
  `fax` varchar(45) DEFAULT NULL,
  `contact` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `companyType` bigint(20) DEFAULT NULL,
  `country` bigint(20) DEFAULT NULL,
  `code` varchar(45) NOT NULL,
  `creator` bigint(20) NOT NULL,
  `updator` bigint(20) NOT NULL,
  `createdDate` datetime DEFAULT NULL,
  `lastUpdateDate` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_UNIQUE` (`code`),
  KEY `ownerUser_idx` (`creator`),
  KEY `companyConfig_idx` (`companyType`),
  KEY `FK600E7C55A11345A0` (`companyType`),
  KEY `FK600E7C553B176372` (`creator`),
  KEY `FK600E7C55F86957FF` (`country`),
  KEY `FK600E7C55EFCFC545` (`updator`),
  CONSTRAINT `ownerUser` FOREIGN KEY (`creator`) REFERENCES `app_user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES (21,'Vo Thi Thuy Linh','trường trung học sdfsd sdasd','123dds34','2323324','2344234','trường trung học','tlinh2110@yahoo.com',62,67,'LNI',-2,-2,'2015-02-23 00:00:00','2015-02-23 19:39:04'),(22,'234','','','','','asdasd','tlinh2110@yahoo.com',63,84,'2334',-2,-2,'2015-02-24 00:00:00','2015-02-23 19:39:30'),(23,'asdasd','','','','','','sadsd@ere.com',62,80,'234',-2,-2,'2015-02-28 11:16:25','2015-02-28 04:16:25');
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `docservices`
--

DROP TABLE IF EXISTS `docservices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `docservices` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nameOfServices` bigint(20) DEFAULT NULL,
  `regDate` datetime DEFAULT NULL,
  `revDate` datetime DEFAULT NULL,
  `docsgeneral` bigint(20) DEFAULT NULL,
  `regNo` varchar(45) DEFAULT NULL,
  `getDate` datetime DEFAULT NULL,
  `typeCO` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `toPackageFromService_idx` (`docsgeneral`),
  CONSTRAINT `docsGeneralFromServices` FOREIGN KEY (`docsgeneral`) REFERENCES `docsgeneral` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `docservices`
--

LOCK TABLES `docservices` WRITE;
/*!40000 ALTER TABLE `docservices` DISABLE KEYS */;
INSERT INTO `docservices` VALUES (1,88,'2015-02-18 00:00:00','2015-02-25 00:00:00',67,'tiếng việt','2015-02-20 00:00:00','tiếng việt'),(5,87,'2015-02-11 00:00:00','2015-02-19 00:00:00',67,'tiếng việt','2015-02-18 00:00:00','tiếng việt'),(6,88,'2015-02-18 00:00:00','2015-02-17 00:00:00',71,'23123','2015-02-16 00:00:00','23'),(7,87,'2015-02-26 00:00:00','2015-02-24 00:00:00',71,'213','2015-02-26 00:00:00','23');
/*!40000 ALTER TABLE `docservices` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `docsgeneral`
--

DROP TABLE IF EXISTS `docsgeneral`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `docsgeneral` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `customer` bigint(20) DEFAULT NULL,
  `jobNo` varchar(45) DEFAULT NULL,
  `processingStaff` bigint(20) DEFAULT NULL,
  `typeOfPackage` bigint(20) DEFAULT NULL,
  `typeOfContainer` bigint(20) DEFAULT NULL,
  `noOfPkgs` int(11) DEFAULT NULL,
  `weigth` decimal(15,2) DEFAULT NULL,
  `cmb` int(11) DEFAULT NULL,
  `placeEmptyDown` varchar(255) DEFAULT NULL,
  `placeEmptyUp` varchar(255) DEFAULT NULL,
  `placeDelivery` varchar(255) DEFAULT NULL,
  `placeRev` varchar(255) DEFAULT NULL,
  `docReceiveDate` datetime DEFAULT NULL,
  `shippingLine` bigint(20) DEFAULT NULL,
  `typeOfDocs` int(10) DEFAULT NULL,
  `contactDelivery` varchar(255) DEFAULT NULL,
  `infoInvoice` varchar(255) DEFAULT NULL,
  `placeRev1` varchar(45) DEFAULT NULL,
  `placeDelivery1` varchar(45) DEFAULT NULL,
  `doDelivery` int(1) DEFAULT NULL,
  `doAccounting` int(1) DEFAULT NULL,
  `portPutCont` varchar(255) DEFAULT NULL,
  `placeGetCont` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `jobNo_UNIQUE` (`jobNo`),
  KEY `docsCustomer_idx` (`customer`),
  KEY `docsStaff_idx` (`processingStaff`),
  KEY `docsTypeOfService_idx` (`typeOfPackage`),
  KEY `docsTypeOfContainer_idx` (`typeOfContainer`),
  KEY `docsShippingline_idx` (`shippingLine`),
  CONSTRAINT `docsCustmer` FOREIGN KEY (`customer`) REFERENCES `customers` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `docsStaff` FOREIGN KEY (`processingStaff`) REFERENCES `app_user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `docsTypeOfContainer` FOREIGN KEY (`typeOfContainer`) REFERENCES `configuration` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `docsTypeOfService` FOREIGN KEY (`typeOfPackage`) REFERENCES `configuration` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `docsgeneral`
--

LOCK TABLES `docsgeneral` WRITE;
/*!40000 ALTER TABLE `docsgeneral` DISABLE KEYS */;
INSERT INTO `docsgeneral` VALUES (67,21,'LNICUS1',-3,NULL,-1,23324,234.00,234,'tiếng việt','tiếng việt','tiếng việt','tiếng việt','2015-02-25 00:00:00',3,0,'tiếng việt','EGLV080400308183',NULL,NULL,1,1,NULL,NULL),(68,21,'LNIVTT1',-3,NULL,-1,23423,23434.56,234234,'qwe','qe','asd','34ad','2015-03-17 00:00:00',NULL,4,'wqe','3eqwe','asd','sad',1,1,NULL,NULL),(69,21,'LNIVTT2',-3,NULL,NULL,234,32434.00,3243,'','','23434','32434',NULL,3,5,'','',NULL,NULL,1,1,'Cảng hạ CONT','Cảng lấy CONT'),(70,21,'LNIVTT3',-3,NULL,-2,343,32434.00,3434,'wqewqe','wqe','qwewq','qwewqe',NULL,1,4,'','','fad','sadasd',1,1,NULL,NULL),(71,21,'LNICUS2',-3,NULL,-2,34,324.00,324,'3easad','asd','asd','asdsad','2015-02-18 00:00:00',3,0,'dsad','asd',NULL,NULL,1,1,NULL,NULL),(72,21,'LNIEXH1',NULL,NULL,-1,32,43.00,4234,NULL,NULL,NULL,NULL,NULL,1,2,'324','324',NULL,NULL,1,1,NULL,NULL);
/*!40000 ALTER TABLE `docsgeneral` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exfeetable`
--

DROP TABLE IF EXISTS `exfeetable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `exfeetable` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` bigint(20) DEFAULT NULL,
  `amount` decimal(20,2) DEFAULT NULL,
  `vat` decimal(5,2) DEFAULT NULL,
  `docsgeneral` bigint(20) DEFAULT NULL,
  `masterFee` bigint(20) DEFAULT NULL,
  `truckingdetail` bigint(20) DEFAULT NULL,
  `exhibition` bigint(20) DEFAULT NULL,
  `approved` int(1) DEFAULT NULL,
  `dateChange` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `nameOfFee_idx` (`name`),
  KEY `feeToexhibition_idx` (`docsgeneral`),
  KEY `toMasterFee_idx` (`masterFee`),
  KEY `totruckingdetail_idx` (`id`,`truckingdetail`),
  KEY `totruckingdetail_idx1` (`truckingdetail`),
  KEY `fromfeetoexh_idx` (`exhibition`),
  CONSTRAINT `feeToexhibition` FOREIGN KEY (`docsgeneral`) REFERENCES `docsgeneral` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fromfeetoexh` FOREIGN KEY (`exhibition`) REFERENCES `exhibition` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `nameOfFee` FOREIGN KEY (`name`) REFERENCES `configuration` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `toMasterFee` FOREIGN KEY (`masterFee`) REFERENCES `configuration` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `totruckingdetail` FOREIGN KEY (`truckingdetail`) REFERENCES `truckingdetail` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=181 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exfeetable`
--

LOCK TABLES `exfeetable` WRITE;
/*!40000 ALTER TABLE `exfeetable` DISABLE KEYS */;
INSERT INTO `exfeetable` VALUES (1,181,23434.00,3.00,67,-10,NULL,NULL,1,'2015-03-12 02:54:23'),(2,183,433333234544.00,3.00,67,-10,NULL,NULL,1,'2015-03-12 02:54:44'),(4,185,342343.00,434.00,68,179,NULL,NULL,1,'2015-03-12 02:55:10'),(5,194,324.00,234.00,69,178,NULL,NULL,0,NULL),(6,194,324.00,324.00,70,-10,NULL,NULL,NULL,NULL),(7,194,213.00,213.00,71,-10,NULL,NULL,0,NULL),(9,185,34334.00,1.00,67,-10,NULL,NULL,0,'2015-03-12 02:48:10'),(12,196,34.00,34.00,68,-10,NULL,NULL,1,NULL),(13,182,234.00,34.00,72,216,NULL,NULL,1,NULL),(14,181,24.00,234.00,72,216,NULL,NULL,NULL,NULL),(20,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(21,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(30,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(32,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(34,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(35,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(36,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(37,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(38,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(42,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(43,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(44,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(45,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(46,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(50,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(51,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(52,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(53,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(54,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(55,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(56,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(57,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(58,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(60,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(61,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(63,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(64,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(65,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(66,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(67,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(68,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(69,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(70,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(71,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(72,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(73,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(74,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(75,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(76,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(77,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(78,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(79,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(80,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(81,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(82,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(83,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(84,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(85,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(86,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(87,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(88,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(89,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(91,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(92,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(93,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(94,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(95,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(96,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(97,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(98,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(100,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(101,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(102,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(103,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(104,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(105,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(106,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(107,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(108,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(109,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(110,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(111,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(112,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(113,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(114,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(115,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(116,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(117,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(118,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(119,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(120,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(121,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(122,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(123,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(124,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(125,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(126,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(127,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(128,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(129,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(130,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(132,194,234.00,34.00,NULL,NULL,29,NULL,NULL,NULL),(133,194,343.00,34.00,NULL,NULL,30,NULL,NULL,NULL),(134,194,3434.00,453.00,NULL,NULL,31,NULL,NULL,NULL),(136,185,3.00,3.00,NULL,NULL,30,NULL,NULL,NULL),(137,194,2343.00,3.00,NULL,NULL,32,NULL,NULL,NULL),(138,196,342432.00,34.00,NULL,NULL,32,NULL,NULL,NULL),(139,182,32434.00,3.00,NULL,NULL,33,NULL,NULL,NULL),(140,182,435.00,345.00,71,-10,NULL,NULL,1,NULL),(141,186,324.00,234.00,67,-10,NULL,NULL,0,'2015-03-12 02:48:05'),(144,194,343.00,1.00,NULL,NULL,39,NULL,NULL,NULL),(145,181,324.00,234.00,NULL,NULL,39,NULL,NULL,NULL),(147,181,3.00,23.00,NULL,NULL,41,NULL,NULL,NULL),(148,186,324.00,324.00,NULL,NULL,41,NULL,NULL,NULL),(149,182,3.00,34.00,NULL,NULL,42,NULL,NULL,NULL),(150,183,234.00,324.00,NULL,NULL,42,NULL,NULL,NULL),(151,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(153,194,0.00,0.00,NULL,NULL,45,NULL,NULL,NULL),(154,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(155,194,0.00,0.00,NULL,NULL,36,NULL,NULL,NULL),(157,182,3435.00,44.00,68,-10,NULL,NULL,1,'2015-03-12 02:55:06'),(158,194,0.00,0.00,NULL,NULL,46,NULL,NULL,NULL),(159,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(160,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(161,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(162,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(163,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(164,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(165,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(166,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(167,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(168,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(169,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(170,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(171,NULL,34.00,34.00,NULL,NULL,NULL,NULL,NULL,NULL),(172,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(173,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(175,266,445.00,45.00,NULL,267,NULL,5,NULL,NULL),(176,268,454.00,45.00,NULL,265,NULL,5,NULL,NULL),(177,185,34.00,324.00,67,179,NULL,NULL,1,'2015-03-12 02:48:39'),(178,182,345.00,4.00,71,-10,NULL,NULL,1,NULL),(179,181,435.00,44.00,71,-10,NULL,NULL,NULL,NULL),(180,185,0.00,0.00,68,179,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `exfeetable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exhibition`
--

DROP TABLE IF EXISTS `exhibition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `exhibition` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `docsgeneral` bigint(10) DEFAULT NULL,
  `exName` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `typeOfEx` bigint(10) DEFAULT NULL,
  `openDate` datetime DEFAULT NULL,
  `endDate` datetime DEFAULT NULL,
  `devDate` datetime DEFAULT NULL,
  `exhibitor` varchar(255) DEFAULT NULL,
  `exhPlace` varchar(255) DEFAULT NULL,
  `operator` bigint(10) DEFAULT NULL,
  `etd` datetime DEFAULT NULL,
  `eta` datetime DEFAULT NULL,
  `pol` varchar(255) DEFAULT NULL,
  `poa` varchar(255) DEFAULT NULL,
  `demdet` varchar(255) DEFAULT NULL,
  `tkhqNo` varchar(255) DEFAULT NULL,
  `tkhqDate` datetime DEFAULT NULL,
  `cusDept` bigint(10) DEFAULT NULL,
  `updator` bigint(20) DEFAULT NULL,
  `creator` bigint(20) DEFAULT NULL,
  `lastUpdateDate` datetime DEFAULT NULL,
  `createdDate` datetime DEFAULT NULL,
  `doRecDocs` int(1) DEFAULT '0',
  `doPrepareDocs` int(1) DEFAULT '0',
  `doRequestDocs` int(1) DEFAULT '0',
  `doBooking` int(1) DEFAULT '0',
  `doTrans` int(1) DEFAULT '0',
  `doCus` int(1) DEFAULT '0',
  `doPacking` int(1) DEFAULT '0',
  `albwNo` varchar(255) DEFAULT NULL,
  `counting` bigint(20) DEFAULT NULL,
  `mode` bigint(20) DEFAULT NULL,
  `invoiceNo` varchar(45) DEFAULT NULL,
  `accountNo` varchar(45) DEFAULT NULL,
  `attn` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `extodocgeneral_idx` (`docsgeneral`),
  KEY `typeOfEx_idx` (`typeOfEx`),
  KEY `operatorConf_idx` (`operator`),
  KEY `toCusDept_idx` (`cusDept`),
  CONSTRAINT `extodocgeneral` FOREIGN KEY (`docsgeneral`) REFERENCES `docsgeneral` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `operatorConf` FOREIGN KEY (`operator`) REFERENCES `configuration` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `toCusDept` FOREIGN KEY (`cusDept`) REFERENCES `configuration` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `typeOfEx` FOREIGN KEY (`typeOfEx`) REFERENCES `configuration` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exhibition`
--

LOCK TABLES `exhibition` WRITE;
/*!40000 ALTER TABLE `exhibition` DISABLE KEYS */;
INSERT INTO `exhibition` VALUES (5,72,'324','324',209,'2015-02-04 00:00:00','2015-02-10 00:00:00','2015-02-11 00:00:00','324','324',NULL,'2015-02-25 00:00:00','2015-02-25 00:00:00','324','34','324','324','2015-02-23 00:00:00',163,-2,-2,'2015-03-12 02:54:15','2015-02-23 00:00:00',0,0,0,0,0,0,0,'324',1,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `exhibition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `extendfeeacc`
--

DROP TABLE IF EXISTS `extendfeeacc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `extendfeeacc` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` bigint(20) DEFAULT NULL,
  `note` varchar(1000) DEFAULT NULL,
  `invoice` varchar(1000) DEFAULT NULL,
  `feeowner` bigint(20) DEFAULT NULL,
  `accountingcus` bigint(20) DEFAULT NULL,
  `quantity20` int(10) DEFAULT NULL,
  `quantity40` int(10) DEFAULT NULL,
  `quantityLCL` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `toFee_idx` (`feeowner`),
  KEY `toCounting_idx` (`accountingcus`),
  KEY `tofeedebit_idx` (`description`),
  CONSTRAINT `toCounting` FOREIGN KEY (`accountingcus`) REFERENCES `accountingcus` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `toFee` FOREIGN KEY (`feeowner`) REFERENCES `exfeetable` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `tofeedebit` FOREIGN KEY (`description`) REFERENCES `configuration` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `extendfeeacc`
--

LOCK TABLES `extendfeeacc` WRITE;
/*!40000 ALTER TABLE `extendfeeacc` DISABLE KEYS */;
INSERT INTO `extendfeeacc` VALUES (24,261,'','',7,NULL,0,0,0),(25,261,'','',140,NULL,0,0,0),(26,261,'5345','345',1,NULL,3,5,0),(27,261,'345','345',2,NULL,4,6,4),(43,261,'435','345',9,NULL,4,0,0),(44,261,'435','345',141,NULL,0,0,0);
/*!40000 ALTER TABLE `extendfeeacc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inland`
--

DROP TABLE IF EXISTS `inland`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inland` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `typeOfInland` bigint(20) DEFAULT NULL,
  `lastUpdateDate` datetime DEFAULT NULL,
  `createdDate` datetime DEFAULT NULL,
  `creator` bigint(20) DEFAULT NULL,
  `updator` bigint(20) DEFAULT NULL,
  `docsgeneral` bigint(20) DEFAULT NULL,
  `counting` int(10) DEFAULT NULL,
  `nameOfPack` varchar(45) DEFAULT NULL,
  `otherInfo` varchar(45) DEFAULT NULL,
  `refNo` varchar(45) DEFAULT NULL,
  `dateRecPack` datetime DEFAULT NULL,
  `dateDevPack` datetime DEFAULT NULL,
  `dateCutOff` datetime DEFAULT NULL,
  `dateExpired` datetime DEFAULT NULL,
  `isInland` int(1) DEFAULT '1',
  `route` bigint(20) DEFAULT NULL,
  `nameVehicle` varchar(255) DEFAULT NULL,
  `etd` datetime DEFAULT NULL,
  `eta` datetime DEFAULT NULL,
  `freeTimeInHCM` int(10) DEFAULT NULL,
  `freeTimeInHP` int(10) DEFAULT NULL,
  `attachServices` varchar(1000) DEFAULT NULL,
  `accountingPrice` decimal(20,2) DEFAULT NULL,
  `otherFees` decimal(20,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `typeOfInland_idx` (`typeOfInland`),
  KEY `toCreator_idx` (`creator`),
  KEY `inlandToGeneral_idx` (`docsgeneral`),
  KEY `routeToConf_idx` (`route`),
  KEY `priceItemAcc_idx` (`accountingPrice`),
  CONSTRAINT `inlandToGeneral` FOREIGN KEY (`docsgeneral`) REFERENCES `docsgeneral` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `routeToConf` FOREIGN KEY (`route`) REFERENCES `configuration` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `toCreator` FOREIGN KEY (`creator`) REFERENCES `app_user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `typeOfInland` FOREIGN KEY (`typeOfInland`) REFERENCES `configuration` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inland`
--

LOCK TABLES `inland` WRITE;
/*!40000 ALTER TABLE `inland` DISABLE KEYS */;
INSERT INTO `inland` VALUES (15,201,'2015-03-12 02:38:57','2015-02-23 00:00:00',-2,-2,68,1,'qwe','qewqe','','2015-02-10 00:00:00','2015-02-18 00:00:00','2015-03-25 00:00:00','2015-03-27 00:00:00',1,NULL,NULL,NULL,NULL,NULL,NULL,'wqewqe',234.00,343.00),(16,NULL,'2015-03-09 02:11:43','2015-02-23 00:00:00',-2,-2,69,2,'','',NULL,'2015-02-09 00:00:00','2015-02-18 00:00:00',NULL,NULL,0,203,'',NULL,NULL,NULL,NULL,'',34.00,34.00),(17,201,'2015-02-28 02:36:50','2015-02-23 00:00:00',-2,-2,70,3,'','','','2015-02-11 00:00:00','2015-03-12 00:00:00',NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,'',NULL,NULL);
/*!40000 ALTER TABLE `inland` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inlandsize`
--

DROP TABLE IF EXISTS `inlandsize`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inlandsize` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `deep` int(10) DEFAULT NULL,
  `width` int(10) DEFAULT NULL,
  `height` int(10) DEFAULT NULL,
  `docsgeneral` bigint(20) DEFAULT NULL,
  `kg` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `toInlandFCL_idx` (`docsgeneral`),
  CONSTRAINT `toInland` FOREIGN KEY (`docsgeneral`) REFERENCES `docsgeneral` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inlandsize`
--

LOCK TABLES `inlandsize` WRITE;
/*!40000 ALTER TABLE `inlandsize` DISABLE KEYS */;
INSERT INTO `inlandsize` VALUES (6,343454,345454,345454,70,33424.00),(7,43545,3454,345435,72,434.00);
/*!40000 ALTER TABLE `inlandsize` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `multitypes`
--

DROP TABLE IF EXISTS `multitypes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `multitypes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` bigint(20) DEFAULT NULL,
  `amount` int(10) DEFAULT NULL,
  `docsgeneral` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `multiToDocs_idx` (`docsgeneral`),
  CONSTRAINT `multiToDocs` FOREIGN KEY (`docsgeneral`) REFERENCES `docsgeneral` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `multitypes`
--

LOCK TABLES `multitypes` WRITE;
/*!40000 ALTER TABLE `multitypes` DISABLE KEYS */;
INSERT INTO `multitypes` VALUES (6,167,34,68),(7,169,345,68),(8,167,0,69);
/*!40000 ALTER TABLE `multitypes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nhathau`
--

DROP TABLE IF EXISTS `nhathau`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `nhathau` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `contactName` varchar(45) DEFAULT NULL,
  `name` varchar(1000) DEFAULT NULL,
  `phoneNumber` varchar(15) DEFAULT NULL,
  `taxNo` varchar(45) DEFAULT NULL,
  `type` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhathau`
--

LOCK TABLES `nhathau` WRITE;
/*!40000 ALTER TABLE `nhathau` DISABLE KEYS */;
INSERT INTO `nhathau` VALUES (1,'A. Hòa','CTY CP AN LONG TRƯỜNG','0912.146839','03010271497',0),(2,'A. Hùng','An Hòa','0915640077','',0),(3,'','NYK','','',1);
/*!40000 ALTER TABLE `nhathau` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `offeritem`
--

DROP TABLE IF EXISTS `offeritem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `offeritem` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nameOfService` bigint(20) DEFAULT NULL,
  `feeWithVAT` decimal(20,2) DEFAULT NULL,
  `feeNoVAT` decimal(20,2) DEFAULT NULL,
  `feeUnit` bigint(20) DEFAULT NULL,
  `creator` bigint(20) DEFAULT NULL,
  `updator` bigint(20) DEFAULT NULL,
  `createdDate` datetime DEFAULT NULL,
  `lastUpdateDate` datetime DEFAULT NULL,
  `offerPrice` bigint(20) NOT NULL,
  `currency` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `offerPriceLink_idx` (`offerPrice`),
  KEY `configNameServices_idx` (`nameOfService`),
  KEY `configCurrency_idx` (`currency`),
  KEY `configFeeUnit_idx` (`feeUnit`),
  KEY `FKEB549B6FFEFFB3C` (`nameOfService`),
  KEY `FKEB549B6FE19DF07A` (`currency`),
  KEY `FKEB549B6FBABD227` (`offerPrice`),
  KEY `FKEB549B6F852D3273` (`feeUnit`),
  CONSTRAINT `configCurrency` FOREIGN KEY (`currency`) REFERENCES `configuration` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `configFeeUnit` FOREIGN KEY (`feeUnit`) REFERENCES `configuration` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `configNameServices` FOREIGN KEY (`nameOfService`) REFERENCES `configuration` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `offerPriceLink` FOREIGN KEY (`offerPrice`) REFERENCES `offerprice` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `offeritem`
--

LOCK TABLES `offeritem` WRITE;
/*!40000 ALTER TABLE `offeritem` DISABLE KEYS */;
INSERT INTO `offeritem` VALUES (1,87,234.00,34234.00,95,NULL,NULL,NULL,NULL,34,93),(2,88,242.00,44435.00,95,NULL,NULL,NULL,NULL,34,93),(3,88,3452.00,2342.00,95,NULL,NULL,NULL,NULL,33,91),(4,87,565.00,888.00,95,NULL,NULL,NULL,NULL,35,91),(5,89,234324.00,23434.00,95,NULL,NULL,NULL,NULL,33,91),(6,89,4234.00,234.00,95,NULL,NULL,NULL,NULL,33,94),(9,NULL,3423.00,234.00,NULL,NULL,NULL,NULL,NULL,36,NULL),(10,86,0.00,0.00,95,NULL,NULL,NULL,NULL,37,94),(11,86,0.00,0.00,95,NULL,NULL,NULL,NULL,37,94),(12,86,0.00,0.00,95,NULL,NULL,NULL,NULL,37,94),(16,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,37,NULL);
/*!40000 ALTER TABLE `offeritem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `offerprice`
--

DROP TABLE IF EXISTS `offerprice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `offerprice` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `typeOfService` bigint(20) DEFAULT NULL,
  `dateOffer` datetime DEFAULT NULL,
  `creator` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `updator` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `createdDate` datetime DEFAULT NULL,
  `lastUpdateDate` datetime DEFAULT NULL,
  `customer` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `typeOfService_idx` (`typeOfService`),
  KEY `owner_idx` (`customer`),
  KEY `FK7FA09C6DECE1D869` (`customer`),
  CONSTRAINT `owner` FOREIGN KEY (`customer`) REFERENCES `customers` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `offerprice`
--

LOCK TABLES `offerprice` WRITE;
/*!40000 ALTER TABLE `offerprice` DISABLE KEYS */;
INSERT INTO `offerprice` VALUES (33,0,'2015-02-25 00:00:00','-2','-2','2015-02-23 00:00:00','2015-03-11 00:16:19',21),(34,1,'2015-03-12 00:00:00','-2','-2','2015-02-23 00:00:00','2015-03-11 01:30:14',21),(35,2,'2015-02-17 00:00:00','-2','-2','2015-02-24 00:00:00','2015-02-24 03:30:36',21),(36,0,'2015-02-18 00:00:00','-2','-2','2015-02-28 00:00:00','2015-03-02 03:11:33',23),(37,0,NULL,'-2','-2','2015-03-10 00:00:00','2015-03-10 23:48:27',21);
/*!40000 ALTER TABLE `offerprice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `packageinfo`
--

DROP TABLE IF EXISTS `packageinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `packageinfo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `shipper` varchar(255) DEFAULT NULL,
  `typeOfTransport` bigint(20) DEFAULT NULL,
  `consignee` varchar(255) DEFAULT NULL,
  `po` varchar(255) DEFAULT NULL,
  `invoice` varchar(255) DEFAULT NULL,
  `nameVehicle` varchar(255) DEFAULT NULL,
  `noVehicle` int(11) DEFAULT NULL,
  `billOfLading` varchar(255) DEFAULT NULL,
  `etd` datetime DEFAULT NULL,
  `eta` datetime DEFAULT NULL,
  `portOfLoading` varchar(255) DEFAULT NULL,
  `portOfArrival` varchar(255) DEFAULT NULL,
  `freeDemDate` datetime DEFAULT NULL,
  `freeDetDate` datetime DEFAULT NULL,
  `wareHouseNo` varchar(255) DEFAULT NULL,
  `cusDecOnNo` varchar(255) DEFAULT NULL,
  `customsDate` datetime DEFAULT NULL,
  `colourApplying` bigint(20) DEFAULT NULL,
  `customsDept` bigint(20) DEFAULT NULL,
  `doRecFullDocs` int(1) DEFAULT NULL,
  `doSendDecDraft` int(1) DEFAULT NULL,
  `doConDec` int(1) DEFAULT NULL,
  `doRecOriginalDocs` int(1) DEFAULT NULL,
  `doPreDecDoc` int(1) DEFAULT NULL,
  `doGetDO` int(1) DEFAULT NULL,
  `doGetTaxDec` int(1) DEFAULT NULL,
  `doRegAcc` int(1) DEFAULT NULL,
  `doCheckPack` int(1) DEFAULT NULL,
  `doRelease` int(1) DEFAULT NULL,
  `creator` bigint(20) DEFAULT NULL,
  `updator` bigint(20) DEFAULT NULL,
  `lastUpdateDate` datetime DEFAULT NULL,
  `createdDate` datetime DEFAULT NULL,
  `docsGeneral` bigint(20) DEFAULT NULL,
  `counting` bigint(10) NOT NULL DEFAULT '0',
  `imExMode` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `docsGeneral1_idx` (`docsGeneral`),
  KEY `toColorApplying_idx` (`colourApplying`),
  KEY `toTypeOfTransp_idx` (`typeOfTransport`),
  KEY `toCustDept_idx` (`customsDept`),
  KEY `exEmnk_idx` (`imExMode`),
  CONSTRAINT `exEmnk` FOREIGN KEY (`imExMode`) REFERENCES `configuration` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `toColorApplying` FOREIGN KEY (`colourApplying`) REFERENCES `configuration` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `toCustDept` FOREIGN KEY (`customsDept`) REFERENCES `configuration` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `toDocsGeneral` FOREIGN KEY (`docsGeneral`) REFERENCES `docsgeneral` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `toTypeOfTransp` FOREIGN KEY (`typeOfTransport`) REFERENCES `configuration` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `packageinfo`
--

LOCK TABLES `packageinfo` WRITE;
/*!40000 ALTER TABLE `packageinfo` DISABLE KEYS */;
INSERT INTO `packageinfo` VALUES (11,'Thuỳ Linh',105,'tiếng việt','tiếng việt','tiếng việt','tiếng việt',NULL,'asd','2015-02-09 00:00:00','2015-02-20 00:00:00','tiếng việt','tiếng việt','2015-02-26 00:00:00','2015-02-25 00:00:00','tiếng việt','100201778610','2015-02-17 00:00:00',165,129,1,1,1,0,0,0,0,0,0,0,-2,-2,'2015-03-12 02:34:11','2015-02-23 00:00:00',67,1,96),(12,'34',106,'32','324','434','34',NULL,'324','2015-02-23 00:00:00','2015-02-23 00:00:00','as','dasdsad','2015-02-18 00:00:00','2015-02-18 00:00:00','tiếng việt','sadas','2015-02-23 00:00:00',164,163,0,0,0,0,0,0,0,0,0,0,-2,-2,'2015-03-12 02:36:28','2015-02-23 00:00:00',71,2,102);
/*!40000 ALTER TABLE `packageinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `planservice`
--

DROP TABLE IF EXISTS `planservice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `planservice` (
  `id` bigint(20) NOT NULL,
  `priority` int(11) DEFAULT NULL,
  `deparment` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `arrival` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `noCont` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `seal` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `bidder` bigint(20) DEFAULT NULL,
  `numOfCar` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `feeTrans` decimal(20,2) DEFAULT NULL,
  `upDown` decimal(20,2) DEFAULT NULL,
  `extend` decimal(20,2) DEFAULT NULL,
  `repair` decimal(20,2) DEFAULT NULL,
  `others` decimal(20,2) DEFAULT NULL,
  `updator` bigint(20) DEFAULT NULL,
  `lastUpdateDate` datetime DEFAULT NULL,
  `createdDate` datetime DEFAULT NULL,
  `creator` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `planservice`
--

LOCK TABLES `planservice` WRITE;
/*!40000 ALTER TABLE `planservice` DISABLE KEYS */;
/*!40000 ALTER TABLE `planservice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productinfo`
--

DROP TABLE IF EXISTS `productinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `productinfo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(50) DEFAULT NULL,
  `name` varchar(1000) DEFAULT NULL,
  `unit` bigint(20) DEFAULT NULL,
  `creator` bigint(20) DEFAULT NULL,
  `updator` bigint(20) DEFAULT NULL,
  `createdDate` datetime DEFAULT NULL,
  `lastUpdateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `toUnit_idx` (`unit`),
  CONSTRAINT `toUnit` FOREIGN KEY (`unit`) REFERENCES `configuration` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productinfo`
--

LOCK TABLES `productinfo` WRITE;
/*!40000 ALTER TABLE `productinfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `productinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(64) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (-2,'Default role for all Users','ROLE_USER'),(-1,'Administrator role (can edit Users)','ROLE_ADMIN');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `truckingdetail`
--

DROP TABLE IF EXISTS `truckingdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `truckingdetail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `consteal` bigint(20) DEFAULT NULL,
  `trucking` bigint(20) DEFAULT NULL,
  `vehicleNo` varchar(45) DEFAULT NULL,
  `noOfVehicle` int(10) DEFAULT NULL,
  `nhathau` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `toContealtrucking_idx` (`consteal`),
  KEY `toTruckingFD_idx` (`trucking`),
  KEY `toNhathauDe_idx` (`nhathau`),
  CONSTRAINT `toContealtrucking` FOREIGN KEY (`consteal`) REFERENCES `contseal` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `toNhathauDe` FOREIGN KEY (`nhathau`) REFERENCES `nhathau` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `toTruckingFD` FOREIGN KEY (`trucking`) REFERENCES `truckingservice` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `truckingdetail`
--

LOCK TABLES `truckingdetail` WRITE;
/*!40000 ALTER TABLE `truckingdetail` DISABLE KEYS */;
INSERT INTO `truckingdetail` VALUES (12,NULL,NULL,NULL,NULL,NULL),(13,NULL,NULL,NULL,NULL,NULL),(14,NULL,NULL,NULL,NULL,NULL),(15,NULL,NULL,NULL,NULL,NULL),(16,NULL,NULL,NULL,NULL,NULL),(18,NULL,NULL,NULL,NULL,NULL),(19,NULL,NULL,NULL,NULL,NULL),(20,NULL,NULL,NULL,NULL,NULL),(21,NULL,NULL,NULL,NULL,NULL),(23,NULL,NULL,NULL,NULL,NULL),(24,NULL,NULL,NULL,NULL,NULL),(26,NULL,NULL,NULL,NULL,NULL),(29,26,18,'',NULL,1),(30,30,18,'',NULL,1),(31,31,18,'',NULL,1),(32,NULL,19,'',NULL,1),(33,NULL,19,'234234',NULL,2),(36,27,22,'33545',NULL,1),(39,NULL,25,'',NULL,3),(41,NULL,26,'erw',NULL,2),(42,NULL,26,'344',NULL,2),(43,32,NULL,NULL,NULL,NULL),(44,32,NULL,NULL,NULL,NULL),(45,32,22,'3434sfsdf',NULL,1),(46,29,27,'',NULL,1);
/*!40000 ALTER TABLE `truckingdetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `truckingservice`
--

DROP TABLE IF EXISTS `truckingservice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `truckingservice` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `departure` varchar(255) DEFAULT NULL,
  `arrival` varchar(255) DEFAULT NULL,
  `creator` bigint(20) DEFAULT NULL,
  `updator` bigint(20) DEFAULT NULL,
  `createdDate` datetime DEFAULT NULL,
  `lastUpdateDate` datetime DEFAULT NULL,
  `docsgeneral` bigint(20) DEFAULT NULL,
  `doRecInfo` int(1) DEFAULT NULL,
  `doPlaning` int(1) DEFAULT NULL,
  `doBooking` int(1) DEFAULT NULL,
  `doDelivery` int(1) DEFAULT NULL,
  `doDoneDeli` int(1) DEFAULT NULL,
  `doDonePack` int(1) DEFAULT NULL,
  `doCollectInfo` int(1) DEFAULT NULL,
  `doAccounting` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `truckingdocs_idx` (`docsgeneral`),
  CONSTRAINT `truckingdocs` FOREIGN KEY (`docsgeneral`) REFERENCES `docsgeneral` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `truckingservice`
--

LOCK TABLES `truckingservice` WRITE;
/*!40000 ALTER TABLE `truckingservice` DISABLE KEYS */;
INSERT INTO `truckingservice` VALUES (18,'','',-2,-2,'2015-03-03 00:00:00','2015-03-03 00:31:56',67,0,0,0,0,0,0,0,NULL),(19,'','',-2,-2,'2015-03-03 00:32:54','2015-03-03 00:32:54',71,0,0,0,0,0,0,0,NULL),(22,'2','2',-2,-2,'2015-03-11 00:00:00','2015-03-11 01:03:36',68,0,0,0,0,0,0,0,NULL),(25,'3','3',-2,-2,'2015-03-11 00:00:00','2015-03-11 00:09:53',69,0,0,0,0,0,0,0,NULL),(26,'','',-2,-2,'2015-03-11 00:00:00','2015-03-11 00:13:15',70,0,0,0,0,0,0,0,NULL),(27,'','',-2,-2,'2015-03-11 02:54:58','2015-03-11 02:54:58',72,0,0,0,0,0,0,0,NULL);
/*!40000 ALTER TABLE `truckingservice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FK143BF46A4FD90D75` (`role_id`),
  KEY `FK143BF46AF503D155` (`user_id`),
  CONSTRAINT `FK143BF46A4FD90D75` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `FK143BF46AF503D155` FOREIGN KEY (`user_id`) REFERENCES `app_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (-3,-2),(-1,-2),(-3,-1),(-2,-1);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-03-12  2:56:02
