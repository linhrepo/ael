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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accountingcus`
--

LOCK TABLES `accountingcus` WRITE;
/*!40000 ALTER TABLE `accountingcus` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accountingcusdetail`
--

LOCK TABLES `accountingcusdetail` WRITE;
/*!40000 ALTER TABLE `accountingcusdetail` DISABLE KEYS */;
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
INSERT INTO `app_user` VALUES (-3,'\0','\0','','Denver','US','80210','CO','\0','two_roles_user@appfuse.org','','Two Roles','User','$2a$10$bH/ssqW8OhkTlIso9/yakubYODUOmh.6m5HEJvcBq3t3VdBh7ebqO','Not a female kitty.','','two_roles_user',1,'http://raibledesigns.com'),(-2,'\0','\0','','Denver','US','80210','CO','\0','matt@raibledesigns.com','','Matt','Raible','$2a$10$bH/ssqW8OhkTlIso9/yakubYODUOmh.6m5HEJvcBq3t3VdBh7ebqO','Not a female kitty.','','admin',1,'http://raibledesigns.com'),(-1,'\0','\0','','Denver','US','80210','CO','\0','matt_raible@yahoo.com','','Tomcat','User','$2a$10$CnQVJ9bsWBjMpeSKrrdDEeuIptZxXrwtI6CZ/OgtNxhIgpKxXeT9y','A male kitty.','','user',1,'http://tomcat.apache.org'),(2,'\0','\0','','','','','','\0','tlinh211110@yahoo.com','','linh','vo','$2a$10$pGFCNkhsmBPopOflv128TuXzFmiNu/ZLbIlVTR78w7qB/tBIn2Ai6','liv1hc','09090909','liv1hc',5,'adsdsad'),(4,'\0','\0','','','','','','\0','tlinh21110@yahoo.com','','Linh','Vo','$2a$10$pGFCNkhsmBPopOflv128TuXzFmiNu/ZLbIlVTR78w7qB/tBIn2Ai6','from 1 to 9','0912344545','saleman',2,''),(5,'\0','\0','','','','','','\0','tlinh2110@yahoo.com','','linh ','vo','$2a$10$3.sRzOGZargn5.syZC5Tdex.uarsilLNAAxlkvPMF0G1Da6rS/XFy','liv1hc','','linh',0,'');
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
) ENGINE=InnoDB AUTO_INCREMENT=274 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `configuration`
--

LOCK TABLES `configuration` WRITE;
/*!40000 ALTER TABLE `configuration` DISABLE KEYS */;
INSERT INTO `configuration` VALUES (-10,'Chi phí chi hộ',20,'Thông tin customs service',0,'{1}'),(-2,'LCL',7,NULL,0,NULL),(-1,'FCL',7,NULL,0,NULL),(60,'Công ty nước ngoài',4,'Thông tin customer',1,NULL),(61,'Công ty Việt Nam',4,'Thông tin customer',1,NULL),(62,'Công ty đầu tư nước ngoài',4,'Thông tin customer',1,NULL),(63,'Công ty liên doanh',4,'Thông tin customer',1,NULL),(64,'Doanh nghiệp chế xuất',4,'Thông tin customer',1,NULL),(65,'Văn phòng đại diện',4,'Thông tin customer',1,NULL),(66,'Vietnam',5,'Thông tin customer',1,NULL),(67,'Thailand',5,'Thông tin customer',1,NULL),(68,'Singapore',5,'Thông tin customer',1,NULL),(69,'Campodia',5,'Thông tin customer',1,NULL),(70,'Malaysia',5,'Thông tin customer',1,NULL),(71,'Indonesia',5,'Thông tin customer',1,NULL),(72,'Philippine',5,'Thông tin customer',1,NULL),(73,'Myanmar',5,'Thông tin customer',1,NULL),(74,'China',5,'Thông tin customer',1,NULL),(75,'Korea',5,'Thông tin customer',1,NULL),(76,'Japan',5,'Thông tin customer',1,NULL),(77,'Taiwan',5,'Thông tin customer',1,NULL),(78,'Hongkong',5,'Thông tin customer',1,NULL),(79,'Germany',5,'Thông tin customer',1,NULL),(80,'Denmark',5,'Thông tin customer',1,NULL),(81,'France',5,'Thông tin customer',1,NULL),(82,'England',5,'Thông tin customer',1,NULL),(83,'Nertherland',5,'Thông tin customer',1,NULL),(84,'Australia',5,'Thông tin customer',1,NULL),(85,'USA',5,'Thông tin customer',1,NULL),(86,'Dịch vụ hải quan',0,'Thông tin báo giá',1,NULL),(87,'Dịch vụ vận chuyển',0,'Thông tin báo giá',1,NULL),(88,'Dịch vụ hải quan và vận chuyển',0,'Thông tin báo giá',1,NULL),(89,'Dịch vụ triển lãm',0,'Thông tin báo giá',1,NULL),(90,'Dịch vụ đóng gói, lắp đặt',0,'Thông tin báo giá',1,NULL),(91,'VND',1,'Thông tin báo giá',1,NULL),(92,'USD',1,'Thông tin báo giá',1,NULL),(93,'SGD',1,'Thông tin báo giá',1,NULL),(94,'AUD',1,'Thông tin báo giá',1,NULL),(96,'Xuất kinh doanh',11,'Thông tin customs service',1,NULL),(97,'Xuất phi mậu dịch',11,'Thông tin customs service',1,NULL),(98,'Tạm xuất tái nhập',11,'Thông tin customs service',1,NULL),(99,'Nhập SXXK',11,'Thông tin customs service',1,NULL),(100,'Tái xuất',11,'Thông tin customs service',1,NULL),(101,'Xuất SXXK',11,'Thông tin customs service',1,NULL),(102,'Nhập kinh doanh',11,'Thông tin customs service',1,NULL),(103,'Nhập phi mậu dịch',11,'Thông tin customs service',1,NULL),(104,'Tạm nhập tái xuất',11,'Thông tin customs service',1,NULL),(105,'Đường không',6,'Thông tin customs service',1,NULL),(106,'Biển',6,'Thông tin customs service',1,NULL),(107,'NYK',12,'Thông tin customs service',1,NULL),(108,'APL',12,'Thông tin customs service',1,NULL),(109,'MOL',12,'Thông tin customs service',1,NULL),(110,'OOCL',12,'Thông tin customs service',1,NULL),(111,'COSCO',12,'Thông tin customs service',1,NULL),(112,'PIL',12,'Thông tin customs service',1,NULL),(113,'NAMSUNG',12,'Thông tin customs service',1,NULL),(114,'INTERASIA',12,'Thông tin customs service',1,NULL),(115,'TSLINE',12,'Thông tin customs service',1,NULL),(116,'MYANGMINH',12,'Thông tin customs service',1,NULL),(117,'SITC',12,'Thông tin customs service',1,NULL),(118,'RCL',12,'Thông tin customs service',1,NULL),(119,'KLINE',12,'Thông tin customs service',1,NULL),(120,'UASC',12,'Thông tin customs service',1,NULL),(121,'MAERSK',12,'Thông tin customs service',1,NULL),(122,'BIEN DONG',12,'Thông tin customs service',1,NULL),(123,'MARINA',12,'Thông tin customs service',1,NULL),(124,'VOSCO',12,'Thông tin customs service',1,NULL),(125,'SINOKOR',12,'Thông tin customs service',1,NULL),(126,'DHL',12,'Thông tin customs service',1,NULL),(127,'FEDEX',12,'Thông tin customs service',1,NULL),(128,'TNT',12,'Thông tin customs service',1,NULL),(129,'Quản lý hàng đầu tư HCM',13,'Thông tin customs service',1,NULL),(130,'Mỹ Phước 1',13,'Thông tin customs service',1,NULL),(131,'Cảng Bình Dương',13,'Thông tin customs service',1,NULL),(132,'VSIP',13,'Thông tin customs service',1,NULL),(133,'Biên Hoà',13,'Thông tin customs service',1,NULL),(134,'Long Bình Tân',13,'Thông tin customs service',1,NULL),(135,'Cảng Cát Lái',13,'Thông tin customs service',1,NULL),(136,'Khánh Hội',13,'Thông tin customs service',1,NULL),(155,'VICT',13,'Thông tin customs service',1,NULL),(156,'Bến Nghé',13,'Thông tin customs service',1,NULL),(157,'ICD TRANSIMEX',13,'Thông tin customs service',1,NULL),(158,'ICD Phước Long 1',13,'Thông tin customs service',1,NULL),(159,'ICD Phước Long 3',13,'Thông tin customs service',1,NULL),(160,'ICD TAMAMEXCO',13,'Thông tin customs service',1,NULL),(161,'ICD SOTRANS',13,'Thông tin customs service',1,NULL),(162,'Cảng Hải Phòng',13,'Thông tin customs service',1,NULL),(163,'Bắc Hà Nội',13,'Thông tin customs service',1,NULL),(164,'1',8,'Thông tin customs service',1,NULL),(165,'2',8,'Thông tin customs service',1,NULL),(166,'3',8,'Thông tin customs service',1,NULL),(167,'20 DR',10,'Thông tin CONT',1,NULL),(168,'20 FR',10,'Thông tin CONT',1,NULL),(169,'20 OP',10,'Thông tin CONT',1,NULL),(170,'20 TK',10,'Thông tin CONT',1,NULL),(171,'40 DR',10,'Thông tin CONT',1,NULL),(172,'40 HQ',10,'Thông tin CONT',1,NULL),(173,'40 FR',10,'Thông tin CONT',1,NULL),(174,'40 OP',10,'Thông tin CONT',1,NULL),(175,'40 TK',10,'Thông tin CONT',1,NULL),(176,'45 HQ',10,'Thông tin CONT',1,NULL),(178,'Chi phí AEL',20,'Thông tin customs service',1,NULL),(179,'Cước CONT',20,'Thông tin customs service',1,NULL),(180,'Khác',20,'Thông tin customs service',1,NULL),(181,'Cước vận chuyển nội địa',19,'Thông tin báo giá',1,NULL),(182,'Cước vận chuyển quốc tế',19,'Thông tin báo giá',1,NULL),(183,'Phí hạ',19,'Thông tin báo giá',1,NULL),(184,'Phí nâng',19,'Thông tin báo giá',1,NULL),(185,'Phí chứng từ',19,'Thông tin báo giá',1,NULL),(186,'Phí D/O',19,'Thông tin báo giá',1,NULL),(187,'Phí xếp dỡ',19,'Thông tin báo giá',1,NULL),(188,'Phí lưu bãi',19,'Thông tin báo giá',1,NULL),(189,'Phí lưu cont',19,'Thông tin báo giá',1,NULL),(190,'Phí lưu kho',19,'Thông tin báo giá',1,NULL),(191,'Phí vệ sinh',19,'Thông tin báo giá',1,NULL),(192,'Phí sữa chữa',19,'Thông tin báo giá',1,NULL),(193,'Phí kiểm dịch',19,'Thông tin báo giá',1,NULL),(194,'Chi phí hải quan',19,'Thông tin báo giá',1,NULL),(195,'Lệ phí hải quan',19,'Thông tin báo giá',1,NULL),(196,'Phí bốc hàng',19,'Thông tin báo giá',1,NULL),(197,'Phí ký chứng từ',19,'Thông tin báo giá',1,NULL),(198,'Phí thanh lý',19,'Thông tin báo giá',1,NULL),(199,'Hàng thuỷ bộ',7,'Thông tin Transportation Services',1,NULL),(200,'Xuất',14,'Thông tin Transportation Services',1,NULL),(201,'Nhập',14,'Thông tin Transportation Services',1,NULL),(202,'Nội địa ',14,'Thông tin Transportation Services',1,NULL),(203,'Bắc - Nam',15,'Thông tin Transportation Services',1,NULL),(204,'Nam - Bắc',15,'Thông tin Transportation Services',1,NULL),(205,'Tạm xuất',17,'Thông tin exhibition',1,NULL),(206,'Tạm nhập',17,'Thông tin exhibition',1,NULL),(207,'Xuất KD',17,'Thông tin exhibition',1,NULL),(208,'Tái nhập',17,'Thông tin exhibition',1,NULL),(209,'Nhập sau',17,'Thông tin exhibition',1,NULL),(210,'Onsite',17,'Thông tin exhibition',1,NULL),(211,'Phí xin GP',21,'Thông tin exhibition',1,NULL),(212,'Local charge at depart',21,'Thông tin exhibition',1,NULL),(213,'Local charge at dest',21,'Thông tin exhibition',1,NULL),(214,'Phí onsite',21,'Thông tin exhibition',1,NULL),(215,'Phí XN - CN',21,'Thông tin exhibition',1,NULL),(216,'Phí giao nhận',21,'Thông tin exhibition',1,NULL),(217,'Phí OF',21,'Thông tin exhibition',1,NULL),(218,'Phí đại lý',21,'Thông tin exhibition',1,NULL),(219,'Tiền thuế XNK',21,'Thông tin exhibition',1,NULL),(220,'Phí khác',21,'Thông tin exhibition',1,NULL),(260,'Phí dịch vụ hải quan và vận chuyển',23,'',1,NULL),(261,'Customs clearance  & Transportation fee',24,'',1,NULL),(271,'Cái',3,NULL,1,NULL),(272,'Con',3,NULL,1,NULL),(273,'TEST',1,NULL,1,NULL);
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contseal`
--

LOCK TABLES `contseal` WRITE;
/*!40000 ALTER TABLE `contseal` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES (24,'34234','3dasdas','34234','32434','234','','sda@adsd.com',62,74,'ZXSD',-2,-2,'2015-03-13 01:00:14','2015-03-12 18:00:14');
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `docservices`
--

LOCK TABLES `docservices` WRITE;
/*!40000 ALTER TABLE `docservices` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `docsgeneral`
--

LOCK TABLES `docsgeneral` WRITE;
/*!40000 ALTER TABLE `docsgeneral` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exfeetable`
--

LOCK TABLES `exfeetable` WRITE;
/*!40000 ALTER TABLE `exfeetable` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exhibition`
--

LOCK TABLES `exhibition` WRITE;
/*!40000 ALTER TABLE `exhibition` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `extendfeeacc`
--

LOCK TABLES `extendfeeacc` WRITE;
/*!40000 ALTER TABLE `extendfeeacc` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inland`
--

LOCK TABLES `inland` WRITE;
/*!40000 ALTER TABLE `inland` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inlandsize`
--

LOCK TABLES `inlandsize` WRITE;
/*!40000 ALTER TABLE `inlandsize` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `multitypes`
--

LOCK TABLES `multitypes` WRITE;
/*!40000 ALTER TABLE `multitypes` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhathau`
--

LOCK TABLES `nhathau` WRITE;
/*!40000 ALTER TABLE `nhathau` DISABLE KEYS */;
INSERT INTO `nhathau` VALUES (1,'A. Hòa','CTY CP AN LONG TRƯỜNG','0912.146839','03010271497',0),(2,'A. Hùng','An Hòa','0915640077','',0),(3,'','NYK','','',1),(5,'','APL','',NULL,1),(6,'','MOL','',NULL,1),(7,'','OOCL','',NULL,1),(8,'','COSCO','',NULL,1),(9,'','PIL','',NULL,1),(10,'','NAMSUNG','',NULL,1),(11,'','INTERASIA','',NULL,1),(12,'','TSLINE','',NULL,1),(13,'','MYANGMINH','',NULL,1),(14,'','SITC','',NULL,1),(15,'','RCL','',NULL,1),(16,'','KLINE','',NULL,1),(17,'','UASC','',NULL,1),(18,'','MAERSK','',NULL,1),(19,'','BIEN DONG','',NULL,1),(20,'','MARINA','',NULL,1),(21,'','VOSCO','',NULL,1),(22,'','SINOKOR','',NULL,1),(23,'','DHL','',NULL,1),(24,'','FEDEX','',NULL,1),(25,'','TNT','',NULL,1),(26,'','EMS','',NULL,1),(27,'','UPS','',NULL,1),(28,'','INTER LOGISTICS','',NULL,1),(29,'','KONOIKE','',NULL,1),(30,'','SUPERSTAR','',NULL,1),(31,'','VINATRANS','',NULL,1),(32,'','DACHSER','',NULL,1),(33,'','ANC','',NULL,1),(34,'','LOGITEM','',NULL,1),(35,'','NNR','',NULL,1),(36,'','THAMI','',NULL,1),(37,'','EVERGREEN','',NULL,1),(38,'','EVERICH','',NULL,1),(39,'','P&O','',NULL,1),(40,'','PT SHIPPING','',NULL,1),(41,'','KHAI MINH','',NULL,1),(42,'','SCHENKER','',NULL,1),(43,'','AGILITY','',NULL,1);
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
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `offeritem`
--

LOCK TABLES `offeritem` WRITE;
/*!40000 ALTER TABLE `offeritem` DISABLE KEYS */;
INSERT INTO `offeritem` VALUES (17,86,234.00,234.00,272,NULL,NULL,NULL,NULL,38,94),(18,89,23.00,234.00,272,NULL,NULL,NULL,NULL,38,93),(19,86,32434.00,23434.00,271,NULL,NULL,NULL,NULL,39,273),(20,87,34.00,234.00,272,NULL,NULL,NULL,NULL,39,91),(21,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,39,NULL),(22,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,39,NULL);
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
  `isValid` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `typeOfService_idx` (`typeOfService`),
  KEY `owner_idx` (`customer`),
  KEY `FK7FA09C6DECE1D869` (`customer`),
  CONSTRAINT `owner` FOREIGN KEY (`customer`) REFERENCES `customers` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `offerprice`
--

LOCK TABLES `offerprice` WRITE;
/*!40000 ALTER TABLE `offerprice` DISABLE KEYS */;
INSERT INTO `offerprice` VALUES (38,0,'2015-03-25 00:00:00','-2','2','2015-03-13 00:00:00','2015-03-13 02:26:47',24,1),(39,0,'2015-03-18 00:00:00','2','2','2015-03-13 00:00:00','2015-03-13 01:44:02',24,1);
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `packageinfo`
--

LOCK TABLES `packageinfo` WRITE;
/*!40000 ALTER TABLE `packageinfo` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (-2,'Default role for all Users','ROLE_USER'),(-1,'Administrator role (can edit Users)','ROLE_ADMIN'),(1,'Sales','ROLE_SALES'),(2,'Docs','ROLE_DOCS'),(3,'Trucking','ROLE_TRUCK');
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `truckingdetail`
--

LOCK TABLES `truckingdetail` WRITE;
/*!40000 ALTER TABLE `truckingdetail` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `truckingservice`
--

LOCK TABLES `truckingservice` WRITE;
/*!40000 ALTER TABLE `truckingservice` DISABLE KEYS */;
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
INSERT INTO `user_role` VALUES (-3,-2),(-1,-2),(2,-2),(-3,-1),(-2,-1),(2,1),(2,2),(2,3);
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

-- Dump completed on 2015-03-13 20:52:42
