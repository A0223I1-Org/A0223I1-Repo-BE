-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: a0223i1_pharmacy
-- ------------------------------------------------------
-- Server version	8.0.33

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `account_id` int NOT NULL AUTO_INCREMENT,
  `delete_flag` bit(1) DEFAULT NULL,
  `email` varchar(150) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`account_id`),
  UNIQUE KEY `UK_q0uja26qgu1atulenwup9rxyr` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,_binary '\0','loiTran@gmail.com','123'),(2,_binary '\0','nhiNguyen@gmail.com','123'),(3,_binary '\0','tayDuong@gmail.com','123'),(4,_binary '','ngaHo@gmail.com','123'),(5,_binary '\0','khangNguyen@gmail.com','123'),(6,_binary '\0','hauDoan@gmail.com','123'),(7,_binary '\0','minhHy@gmail.com','123'),(8,_binary '\0','toanNguyen@gmail.com','123'),(9,_binary '\0','hienDo@gmail.com','123'),(10,_binary '\0','manhDang@gmail.com','123'),(11,_binary '','tranNhat@gmail.com','123'),(12,_binary '','thuyThanh@gmail.com','123'),(13,_binary '\0','duongKhue@gmail.com','123'),(14,_binary '\0','mki@gmail.com','1234567'),(15,_binary '\0','OLI@Gmail.Com','123456'),(16,_binary '',NULL,'123'),(17,_binary '\0',NULL,'123'),(18,_binary '','khang@gmail.com','123456'),(19,_binary '','khue@gmail.com','123456'),(20,_binary '\0','lan@gmail.com','123456'),(21,_binary '\0',NULL,'123'),(22,_binary '\0','bao@gmail.com','123'),(23,_binary '\0','khoa@gmail.com','123'),(24,_binary '\0','long@gmail.com','123'),(25,_binary '','hoanh@gmail.com','123456'),(26,_binary '\0',NULL,'123');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `account_role`
--

DROP TABLE IF EXISTS `account_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account_role` (
  `account_role_id` bigint NOT NULL AUTO_INCREMENT,
  `account_id` int DEFAULT NULL,
  `role_id` bigint DEFAULT NULL,
  PRIMARY KEY (`account_role_id`),
  KEY `FK1f8y4iy71kb1arff79s71j0dh` (`account_id`),
  KEY `FKrs2s3m3039h0xt8d5yhwbuyam` (`role_id`),
  CONSTRAINT `FK1f8y4iy71kb1arff79s71j0dh` FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`),
  CONSTRAINT `FKrs2s3m3039h0xt8d5yhwbuyam` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_role`
--

LOCK TABLES `account_role` WRITE;
/*!40000 ALTER TABLE `account_role` DISABLE KEYS */;
INSERT INTO `account_role` VALUES (1,14,NULL),(2,15,NULL),(3,18,NULL),(4,19,NULL),(5,20,NULL),(6,22,2),(7,23,2),(8,24,2),(9,25,2);
/*!40000 ALTER TABLE `account_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `cart_id` int NOT NULL AUTO_INCREMENT,
  `note` varchar(255) DEFAULT NULL,
  `customer_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cart_id`),
  UNIQUE KEY `UK_867x3yysb1f3jk41cv3vsoejj` (`customer_id`),
  CONSTRAINT `FKdebwvad6pp1ekiqy5jtixqbaj` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart_detail`
--

DROP TABLE IF EXISTS `cart_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart_detail` (
  `cart_detail_id` int NOT NULL AUTO_INCREMENT,
  `date_create` datetime DEFAULT NULL,
  `price` float DEFAULT NULL,
  `quantity` int NOT NULL,
  `unit` varchar(255) DEFAULT NULL,
  `cart_id` int DEFAULT NULL,
  `medicine_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cart_detail_id`),
  KEY `FKrg4yopd2252nwj8bfcgq5f4jp` (`cart_id`),
  KEY `FKkexctyx67fngykbyh4dr5clwe` (`medicine_id`),
  CONSTRAINT `FKkexctyx67fngykbyh4dr5clwe` FOREIGN KEY (`medicine_id`) REFERENCES `medicine` (`medicine_id`),
  CONSTRAINT `FKrg4yopd2252nwj8bfcgq5f4jp` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`cart_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_detail`
--

LOCK TABLES `cart_detail` WRITE;
/*!40000 ALTER TABLE `cart_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `customer_id` varchar(255) NOT NULL,
  `address` varchar(250) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `age` int DEFAULT NULL,
  `customer_name` varchar(250) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `customer_type` varchar(255) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `phone_number` varchar(15) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `account_id` int DEFAULT NULL,
  PRIMARY KEY (`customer_id`),
  UNIQUE KEY `UK_jwt2qo9oj3wd7ribjkymryp8s` (`account_id`),
  CONSTRAINT `FKn9x2k8svpxj3r328iy1rpur83` FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES ('KH01','Quảng Nam',38,'Trần Hữu Tuân','Khách lẻ','','0795456657',1),('KH02','Quảng Nam',30,'Nguyễn Thu Hạ Nhi','Khách sỉ','','0845791144',2),('KH03','Hue',30,'Hồ Thị ','Khách lẻ','','0397554664',4),('KH04','Huế',25,'Đỗ Ngọc Hiển','Khách lẻ',NULL,'0934567890',9),('KH05','An Giang',42,'Đặng Văn Mạnh','Khách sỉ',NULL,'0123456789',10),('KH06','DakLak',20,'Trần Đình Long Nhật','Khách theo đơn',NULL,'0345678901',11),('KH07','Đà Nẵng',35,'Nguyễn Thúy Thanh','Khách lẻ',NULL,'0987654321',12),('KH08','Đà Nẵng',32,'Dương Khuê','Khách theo đơn',NULL,'0978964451',13),('KH09','MK',20,'Ou','Khách sỉ','','0258741596',16),('KH10','dn',20,'Nhi','Khách lẻ','','0458966542',17),('KH11','123 Trần Cao Vân',39,'Tây','Khách sỉ','','0931971923',21);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `employee_id` varchar(255) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `date_start` datetime(6) DEFAULT NULL,
  `employee_name` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `salary` int NOT NULL,
  `account_id` int DEFAULT NULL,
  PRIMARY KEY (`employee_id`),
  UNIQUE KEY `UK_lsnx7na4u8ohrhoeag7un4wh3` (`account_id`),
  CONSTRAINT `FKcfg6ajo8oske94exynxpf7tf9` FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES ('Mk0016','k12 đà nẵng','2024-03-16 10:41:00.000000','khang','C:\\fakepath\\avt.jpg','','0905111444',1999999,18),('Mk0017','k19/37','2024-03-16 12:54:00.000000','khueee','C:\\fakepath\\avt.jpg','','0905111222',20000000,19),('Mk0018','k19/8 đà nẵng','2024-03-16 16:08:00.000000','nguyễn văn lan','C:\\fakepath\\avt.jpg','','0905333666',11999999,20),('Mk0019','k15/8 đà nẵng','2024-03-23 14:26:00.000000','hoàng văn bảo','C:\\fakepath\\avt.jpg','','0905112336',11999999,22),('Mk0020','k45 đà nẵng','2024-03-23 14:31:00.000000','huỳnh đăng khoa','C:\\fakepath\\avt.jpg','','0905888777',9999999,23),('Mk0021','k15/8 đà nẵng','2024-03-25 16:54:00.000000','Nguyễn Văn Long','C:\\fakepath\\avt.jpg','','0906222555',13000000,24),('Mk0023','k19/28 mai xuân thưởng, đà nẵng','2024-03-25 10:19:00.000000',' nguyễn văn hoành','C:\\fakepath\\avt.jpg','','0905126587',20000000,25),('NV01','Đà Nẵng','2022-02-02 00:00:00.000000','Tây Dương',NULL,NULL,'0876987334',12000,3),('NV02','Huế','2022-02-03 00:00:00.000000','Nguyễn Khang',NULL,NULL,'0976342424',16000,5),('NV03','Ninh Bình','2022-01-01 00:00:00.000000','Đoàn Văn Hậu',NULL,NULL,'0912543673',13000,6),('NV04','Đà Nẵng ','2022-01-04 00:00:00.000000','Hy Minh',NULL,NULL,'0962343744',13000,7),('NV05','An Giang','2022-02-07 00:00:00.000000','Toàn Nguyễn',NULL,NULL,'0312533785',16000,8);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoice`
--

DROP TABLE IF EXISTS `invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invoice` (
  `invoice_id` varchar(255) NOT NULL,
  `date_create` datetime DEFAULT NULL,
  `invoice_type` varchar(255) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `status` int DEFAULT NULL,
  `total` float DEFAULT NULL,
  `cart_id` int DEFAULT NULL,
  `customer_id` varchar(255) DEFAULT NULL,
  `employee_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`invoice_id`),
  KEY `FKnn4rb9dhqyfjnilc5ajlnhwj8` (`cart_id`),
  KEY `FK5e32ukwo9uknwhylogvta4po6` (`customer_id`),
  KEY `FKau92vqwrrlsflir3v65262ucw` (`employee_id`),
  CONSTRAINT `FK5e32ukwo9uknwhylogvta4po6` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`),
  CONSTRAINT `FKau92vqwrrlsflir3v65262ucw` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`employee_id`),
  CONSTRAINT `FKnn4rb9dhqyfjnilc5ajlnhwj8` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`cart_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice`
--

LOCK TABLES `invoice` WRITE;
/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;
INSERT INTO `invoice` VALUES ('HDL01','2024-02-03 11:00:00','Bán lẻ',NULL,NULL,100000,NULL,'KH01','NV02'),('HDL02','2024-02-10 20:00:00','Bán sỉ',NULL,NULL,50000,NULL,'KH01','NV01'),('HDL03','2024-02-22 10:00:00','Bán lẻ',NULL,NULL,100000,NULL,'KH02','NV02'),('HDL04','2024-02-24 15:00:00','Bán lẻ',NULL,NULL,120000,NULL,'KH02','NV02'),('HDL05','2024-02-23 15:00:00','Bán lẻ',NULL,NULL,150000,NULL,'KH03','NV02'),('HDL06','2024-02-21 15:00:00','Bán theo đơn',NULL,NULL,100000,NULL,'KH01','NV02'),('HDS01','2024-02-07 07:00:00','Bán sỉ',NULL,NULL,125000,NULL,'KH03','NV01'),('HDS02','2024-02-19 07:00:00','Bán sỉ',NULL,NULL,250000,NULL,'KH05','NV01'),('HDS03','2024-02-17 07:00:00','Bán sỉ',NULL,NULL,200000,NULL,'KH04','NV01'),('TT0001','2024-03-24 00:17:58','Bán lẻ','',2,30000,NULL,'KH02','NV05'),('TT0002','2024-03-24 17:41:12','Bán lẻ','',2,40000,NULL,'KH01','NV04'),('TT0003','2024-03-24 20:09:35','Bán lẻ','',2,60000,NULL,'KH02','Mk0018');
/*!40000 ALTER TABLE `invoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoice_detail`
--

DROP TABLE IF EXISTS `invoice_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invoice_detail` (
  `invoice_detail_id` int NOT NULL AUTO_INCREMENT,
  `price` float DEFAULT NULL,
  `quantity` int NOT NULL,
  `unit` varchar(255) DEFAULT NULL,
  `invoice_id` varchar(255) DEFAULT NULL,
  `invoice_pres_id` varchar(255) DEFAULT NULL,
  `medicine_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`invoice_detail_id`),
  KEY `FKit1rbx4thcr6gx6bm3gxub3y4` (`invoice_id`),
  KEY `FK3od85mqhd1pb3xuat39wiuw6y` (`invoice_pres_id`),
  KEY `FKegefbkpw9k8sr5yq70vaj3jdf` (`medicine_id`),
  CONSTRAINT `FK3od85mqhd1pb3xuat39wiuw6y` FOREIGN KEY (`invoice_pres_id`) REFERENCES `invoice_pres` (`invoice_pres_id`),
  CONSTRAINT `FKegefbkpw9k8sr5yq70vaj3jdf` FOREIGN KEY (`medicine_id`) REFERENCES `medicine` (`medicine_id`),
  CONSTRAINT `FKit1rbx4thcr6gx6bm3gxub3y4` FOREIGN KEY (`invoice_id`) REFERENCES `invoice` (`invoice_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice_detail`
--

LOCK TABLES `invoice_detail` WRITE;
/*!40000 ALTER TABLE `invoice_detail` DISABLE KEYS */;
INSERT INTO `invoice_detail` VALUES (1,10000,4,'viên',NULL,'HDD01','MED001'),(2,20000,3,'viên',NULL,'HDD01','MED002'),(3,5000,50,'viên',NULL,'HDD02','MED004'),(4,20000,10,'viên',NULL,'HDD03','MED006'),(5,10000,10,'viên',NULL,'HDD03','MED005'),(6,20000,5,'chai',NULL,'HDD04','MED008'),(7,10000,25,'viên',NULL,'HDD05','MED007'),(8,10000,10,'viên','HDL01',NULL,'MED003'),(9,5000,5,'viên','HDS01',NULL,'MED007'),(10,10000,10,'viên','HDS01',NULL,'MED004'),(11,5000,5,'viên','HDL02',NULL,'MED002'),(12,5000,5,'viên','HDL02',NULL,'MED001'),(13,20000,5,'chai','HDL03',NULL,'MED009'),(14,10000,10,'viên','HDL04',NULL,'MED006'),(15,2000,10,'viên','HDL04',NULL,'MED006'),(16,15000,10,'viên','HDL05',NULL,'MED003'),(17,10000,10,'viên','HDL06',NULL,'MED007'),(18,25000,10,'viên','HDS02',NULL,'MED001'),(19,20000,10,'viên','HDS03',NULL,'MED004'),(22,30000,1,'hộp','TT0001',NULL,'MED003'),(23,40000,1,' hộp ','TT0002',NULL,'MED002'),(24,30000,2,'hộp','TT0003',NULL,'MED003');
/*!40000 ALTER TABLE `invoice_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoice_pres`
--

DROP TABLE IF EXISTS `invoice_pres`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invoice_pres` (
  `invoice_pres_id` varchar(255) NOT NULL,
  `date_create` datetime DEFAULT NULL,
  `doctor_diagnosis` varchar(255) DEFAULT NULL,
  `doctor_name` varchar(255) DEFAULT NULL,
  `doctor_phone` varchar(255) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `symptom` varchar(255) DEFAULT NULL,
  `total` float DEFAULT NULL,
  `customer_id` varchar(255) DEFAULT NULL,
  `employee_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`invoice_pres_id`),
  KEY `FKni57pvw1b1fujxyn3tb2ur6ml` (`customer_id`),
  KEY `FKecka1d9xk568xd47b3dprnpvp` (`employee_id`),
  CONSTRAINT `FKecka1d9xk568xd47b3dprnpvp` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`employee_id`),
  CONSTRAINT `FKni57pvw1b1fujxyn3tb2ur6ml` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice_pres`
--

LOCK TABLES `invoice_pres` WRITE;
/*!40000 ALTER TABLE `invoice_pres` DISABLE KEYS */;
INSERT INTO `invoice_pres` VALUES ('HDD01','2024-02-20 10:00:00','viêm họng','Thảo','0987786667',NULL,NULL,'đau họng, rát cổ, ho nhiều',100000,'KH04','NV01'),('HDD02','2024-02-17 14:00:00','sốt','Thúy','0987786667',NULL,NULL,'sốt cao, sổ mũi',40000,'KH04','NV02'),('HDD03','2024-02-15 17:00:00','viêm dạ dày','Thảo','0987786667',NULL,NULL,'đau thắt vùng bụng',114000,'KH04','NV03'),('HDD04','2024-02-07 16:00:00','viêm phế quản','An','0987786667',NULL,NULL,'đau họng, rát cổ',231000,'KH05','NV01'),('HDD05','2024-02-17 11:00:00','viêm phổi','Mai','0987786667',NULL,NULL,'tức ngực, ho nhiều',176000,'KH05','NV03');
/*!40000 ALTER TABLE `invoice_pres` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medicine`
--

DROP TABLE IF EXISTS `medicine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medicine` (
  `medicine_id` varchar(255) NOT NULL,
  `active_ingredient` varchar(255) DEFAULT NULL,
  `conversion_rate` int DEFAULT NULL,
  `conversion_unit` varchar(255) DEFAULT NULL,
  `import_price` float DEFAULT NULL,
  `material` varchar(255) DEFAULT NULL,
  `medicine_name` varchar(255) DEFAULT NULL,
  `origin` varchar(255) DEFAULT NULL,
  `profit_margin_retail` float DEFAULT NULL,
  `profit_margin_wholesale` float DEFAULT NULL,
  `quantity` int NOT NULL,
  `retail_price` float DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `supplier_discount` float DEFAULT NULL,
  `unit` varchar(255) DEFAULT NULL,
  `wholesale_price` float DEFAULT NULL,
  `medicine_group_id` int DEFAULT NULL,
  `supplier_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`medicine_id`),
  KEY `FKc90ptyd6kg01hcdyt7fvoytb1` (`medicine_group_id`),
  KEY `FKdk2ywmgysoha2dqgkc0do3okw` (`supplier_id`),
  CONSTRAINT `FKc90ptyd6kg01hcdyt7fvoytb1` FOREIGN KEY (`medicine_group_id`) REFERENCES `medicine_group` (`medicine_group_id`),
  CONSTRAINT `FKdk2ywmgysoha2dqgkc0do3okw` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`supplier_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medicine`
--

LOCK TABLES `medicine` WRITE;
/*!40000 ALTER TABLE `medicine` DISABLE KEYS */;
INSERT INTO `medicine` VALUES ('MED001','Racecadotril',40,' viên',20000,'Sucrose, keo silica khan, 30% polyacrylate dạng phân tán','Thuốc hidrasec','Vietnam',NULL,NULL,10,25000,NULL,NULL,'hộp',22000,6,'MEKOFA'),('MED002','Enterogermina',40,' viên',35000,'bào tử Bacillus Clausii','Thuốc Enterogermina','Vietnam',NULL,NULL,13,40000,NULL,NULL,' hộp ',3700,6,'TW3'),('MED003','Acetylcystein',40,' viên',25000,'Acetylcystein','Thuốc Acetylcystein','Vietnam',NULL,NULL,7,30000,NULL,NULL,'hộp',2000,4,'SE'),('MED004','Cetirizine',40,' viên',20000,'Cetirizine','Thuốc Cetirizine','Vietnam',NULL,NULL,1,25000,NULL,NULL,'hộp',22000,3,'MENDINSCO'),('MED005','theralen',40,' viên',35000,'theralen','Thuốc theralen','Vietnam',NULL,NULL,10,40000,NULL,NULL,' hộp ',38000,2,'MEKOFA'),('MED006','Codein',40,' viên',35000,'Codein','Thuốc Codein','Vietnam',NULL,NULL,10,40000,NULL,NULL,'hộp',37000,1,'DOMESCO'),('MED007','Loratadine',40,' viên',40000,'Loratadine','Thuốc Loratadine','Vietnam',NULL,NULL,2,45000,NULL,NULL,'hộp',43000,5,'HDVN'),('MED008','bạc hà',1,'chai',20000,'Bạc hà, ngân thảo','Vị Khang Ninh','Vietnam',NULL,NULL,10,25000,NULL,NULL,'chai',22000,6,'DDL'),('MED009','bạc hà, mật ong',1,'chai',20000,'bạc hà, mật ong','Bổ phế Nam Hà','Vietnam',NULL,NULL,10,25000,NULL,NULL,'chai',22000,5,'TWH');
/*!40000 ALTER TABLE `medicine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medicine_group`
--

DROP TABLE IF EXISTS `medicine_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medicine_group` (
  `medicine_group_id` int NOT NULL AUTO_INCREMENT,
  `medicine_group_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`medicine_group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medicine_group`
--

LOCK TABLES `medicine_group` WRITE;
/*!40000 ALTER TABLE `medicine_group` DISABLE KEYS */;
INSERT INTO `medicine_group` VALUES (1,'hạ sốt'),(2,' chống viêm'),(3,'thuốc chống dị ứng'),(4,'thuốc Ho và Long đờm'),(5,'dạ dày'),(6,'tiêu hóa'),(7,'Đầu đầu'),(9,'trẻ anh');
/*!40000 ALTER TABLE `medicine_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medicine_img`
--

DROP TABLE IF EXISTS `medicine_img`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medicine_img` (
  `medicine_img_id` int NOT NULL AUTO_INCREMENT,
  `img_name` varchar(255) DEFAULT NULL,
  `link` varchar(255) DEFAULT NULL,
  `medicine_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`medicine_img_id`),
  KEY `FK2k74p31n5dd4kcf3w10fb8u8k` (`medicine_id`),
  CONSTRAINT `FK2k74p31n5dd4kcf3w10fb8u8k` FOREIGN KEY (`medicine_id`) REFERENCES `medicine` (`medicine_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medicine_img`
--

LOCK TABLES `medicine_img` WRITE;
/*!40000 ALTER TABLE `medicine_img` DISABLE KEYS */;
/*!40000 ALTER TABLE `medicine_img` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prescription`
--

DROP TABLE IF EXISTS `prescription`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prescription` (
  `prescription_id` varchar(255) NOT NULL,
  `delete_flag` bit(1) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `prescription_name` varchar(255) DEFAULT NULL,
  `target` varchar(255) DEFAULT NULL,
  `treatment_period` varchar(255) DEFAULT NULL,
  `symptom_id` int DEFAULT NULL,
  `create_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`prescription_id`),
  KEY `FKh7cg5oc5rxd3ksejr3nus1t67` (`symptom_id`),
  CONSTRAINT `FKh7cg5oc5rxd3ksejr3nus1t67` FOREIGN KEY (`symptom_id`) REFERENCES `symptom` (`symptom_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prescription`
--

LOCK TABLES `prescription` WRITE;
/*!40000 ALTER TABLE `prescription` DISABLE KEYS */;
INSERT INTO `prescription` VALUES ('TT0010',_binary '',NULL,'đau mắt đỏ','1','4',10,'2024-03-23 16:15:11.000000'),('TT0011',_binary '',NULL,'dau bungg','1','12',11,'2024-03-24 19:41:13.327328'),('TT0012',_binary '',NULL,'dau dau','1','12',12,'2024-03-24 19:41:42.447924'),('TT0013',_binary '',NULL,'viem phe quan','1','12',13,'2024-03-24 19:43:41.996617'),('TT0014',_binary '',NULL,'sot','1','12',14,'2024-03-24 19:44:23.802943'),('TT0015',_binary '',NULL,'dau chan','1','12',15,'2024-03-24 19:44:54.764293'),('TT0016',_binary '\0',NULL,'sdfs','1','12',16,'2024-03-24 19:45:16.955971'),('TT0017',_binary '\0',NULL,'viem hong nl','1','4',17,'2024-03-24 20:13:38.445798');
/*!40000 ALTER TABLE `prescription` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prescription_detail`
--

DROP TABLE IF EXISTS `prescription_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prescription_detail` (
  `prescription_detail_id` bigint NOT NULL AUTO_INCREMENT,
  `medicine_list` varchar(255) DEFAULT NULL,
  `quantity` varchar(255) DEFAULT NULL,
  `quantity_per_times` varchar(255) DEFAULT NULL,
  `times` varchar(255) DEFAULT NULL,
  `prescription_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`prescription_detail_id`),
  KEY `FKdq2yagbksdomo6t1o1b8g3poe` (`prescription_id`),
  CONSTRAINT `FKdq2yagbksdomo6t1o1b8g3poe` FOREIGN KEY (`prescription_id`) REFERENCES `prescription` (`prescription_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prescription_detail`
--

LOCK TABLES `prescription_detail` WRITE;
/*!40000 ALTER TABLE `prescription_detail` DISABLE KEYS */;
INSERT INTO `prescription_detail` VALUES (7,'MED004,,,,,,','3,,,,,,','1,,,,,,','1,,,,,,','TT0010'),(8,'MED006,,,,,,','1,,,,,,','1,,,,,,','1,,,,,,','TT0011'),(9,'MED010,,,,,,','1,,,,,,','1,,,,,,','1,,,,,,','TT0012'),(10,'MED001,,,,,,','1,,,,,,','1,,,,,,','1,,,,,,','TT0013'),(11,'MED008,,,,,,','1,,,,,,','1,,,,,,','1,,,,,,','TT0014'),(12,'MED002,,,,,,','1,,,,,,','1,,,,,,','1,,,,,,','TT0015'),(13,'MED001,,,,,,','1,,,,,,','1,,,,,,','1,,,,,,','TT0016'),(14,'MED009,,,,,,','4,,,,,,','1,,,,,,','1,,,,,,','TT0017');
/*!40000 ALTER TABLE `prescription_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `role_id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'nhân viên'),(2,'quản lý');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `supplier` (
  `supplier_id` varchar(255) NOT NULL,
  `address` text,
  `delete_flag` bit(1) DEFAULT NULL,
  `email` text,
  `note` text,
  `phone_number` text,
  `supplier_name` text,
  `to_pay_debt` int DEFAULT NULL,
  PRIMARY KEY (`supplier_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` VALUES ('ALAN','268 Hải Phòng, Đà Nẵng',_binary '\0','alan@gmail.com','sdsd','0971456162','Công ty Alan',0),('DANAPHA','28 Lê Quảng, Huế',_binary '','danapha@gmail.com','Gần Đà Nẵng','097315645','Công ty Cổ phần Dược Danapha',0),('DANTE','58 Lê Cơ, Đà Nẵng',_binary '','dante@gmail.com','','0985314658','Công ty Dante',0),('DATRACO','198 Lê Đại Hành, Đà Nẵng',_binary '\0','datraco@gmail.com','Còn nợ','098564644','Công ty dược Datraco',500000),('DDL','05 Lê Nổ, quận Hải Châu, Đà Nẵng',_binary '','nguyenvhang29@gmail.com','Hết nợ','097315431','Công DOROSEWA',0),('DELELA','58 Lê Đà Nẵng',_binary '','domesco@gmail.com','','0985314658','Công ty Detae 123',0),('Delelaa','05 Ca Văn Thỉnh, Hải Châu, Đà Nẵng',_binary '','khangvan30600@gmail.com','ds','04971116405','Delela',0),('Detech','56 Lê Văn Liêm, Đà Nẵng',_binary '\0','detech@gmail.com','','0985314657','Công ty Detech',0),('DOMESCO','58 Lê Thanh Nghị, Đà Nẵng',_binary '\0','domesco@gmail.com','Trong thành phố hơi xa','0985314658','Công ty DOMESCO',0),('DOMESCO1','Khối phố Hà Dừa, Tỉnh Quảng Nam',_binary '\0','mediphar@gmail.com','','0903850866','Công ty TNHH DOMESCO',0),('HDVN','192 Đường Cách mạng tháng 8, Đà Nẵng',_binary '\0','hoaduocvn@gmail.com','Công ty lớn','0973154316','Công ty Cổ phần Hóa Dược Việt Nam',1000000),('INMEX','81 Lê Thánh Tông, Đồng Tháp',_binary '\0','inmex@gmail.com','Còn nợ','098564647','Công ty Cổ phần Dược phẩm Imexpharm',600000),('LoREN','298 Hải Phòng, Hà Nội',_binary '\0','loren@gmail.com','Giá hợp lý','0978881640','Công ty Loren',0),('MEDIPHAR','93 Đất Thánh, Tân Bình, Tp.HCM',_binary '\0','mediphar@gmail.com','chất lượng và chiết khấu','0903850866','Công ty TNHH Mediphar USA',0),('MEKOFA','05 Ca Văn Thỉnh, quận Hải Châu, Đà Nẵng',_binary '\0','khangvan300620@gmail.com','Còn đơn chưa về hết','097121640','Công ty Cổ phần Hóa - Dược phẩm Mekophar',0),('MENDINSCO','236 Hoàng Hoa Thám, Hà Nội',_binary '','medinsco@gmail.com','Còn nợ','095646642','Công ty Cổ phần thiết bị Y tế Medinsco',800000),('OPC','123 Phan Đăng Lưu, Đà Nẵng',_binary '\0','nguyenvthang29@gmail.com','Hết nợ','097315431','Công ty Cổ phần Dược phẩm O.P.C',0),('SE','Sa dec, Dong Thap',_binary '','baga@gmail.com','sadasdad','0913897988','Công ty dược SE',8988),('TW2','24 Nguyễn Thị Nghĩa, Quận 1, TP.HCM',_binary '\0','trunguong2@gmail.com','Còn nợ','056456815','Công ty Cổ phần Dược liệu TW2',600000),('TW3','48 Lê Văn Tộ, Thành phố Hồ Chí Minh',_binary '','trunguong3@gmail.com','Còn nợ','094212313','Công ty Cổ phần Dược liệu TW3',700000),('TWH','05 Lê Cơ, quận Hải Châu, Đà Nẵng',_binary '\0','trunguonghue@gmail.com','Không nợ','098564647','Công ty Cổ phần Dược TW Huế',0),('VIMEC','89 Lương Định Của, Hà Nội',_binary '\0','vimec@gmail.com','Giàu','098564645','Công ty Cổ phần Thiết bị Y tế Vimec',1200000);
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `symptom`
--

DROP TABLE IF EXISTS `symptom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `symptom` (
  `symptom_id` int NOT NULL AUTO_INCREMENT,
  `symptom_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`symptom_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `symptom`
--

LOCK TABLES `symptom` WRITE;
/*!40000 ALTER TABLE `symptom` DISABLE KEYS */;
INSERT INTO `symptom` VALUES (10,'mắt đỏ'),(11,'dau bung'),(12,'dau dau'),(13,'dau dau'),(14,'dau dau'),(15,'dau chan'),(16,'sdaf'),(17,'viem hong');
/*!40000 ALTER TABLE `symptom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ware_in_detail`
--

DROP TABLE IF EXISTS `ware_in_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ware_in_detail` (
  `ware_in_detail_id` varchar(255) NOT NULL,
  `batch_number` int NOT NULL,
  `expired_date` datetime(6) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `quantity` int NOT NULL,
  `unit` varchar(255) DEFAULT NULL,
  `medicine_id` varchar(255) DEFAULT NULL,
  `warehouse_in_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ware_in_detail_id`),
  KEY `FKeihy5nprgjq70xg8hrqhuvrcv` (`medicine_id`),
  KEY `FK1i2mapm7qvjifa67mntfw83ue` (`warehouse_in_id`),
  CONSTRAINT `FK1i2mapm7qvjifa67mntfw83ue` FOREIGN KEY (`warehouse_in_id`) REFERENCES `warehouse_in` (`warehouse_in_id`),
  CONSTRAINT `FKeihy5nprgjq70xg8hrqhuvrcv` FOREIGN KEY (`medicine_id`) REFERENCES `medicine` (`medicine_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ware_in_detail`
--

LOCK TABLES `ware_in_detail` WRITE;
/*!40000 ALTER TABLE `ware_in_detail` DISABLE KEYS */;
INSERT INTO `ware_in_detail` VALUES ('1',1212,'2024-03-10 00:00:00.000000',25000,100,'hộp','MED001','1'),('10',1225,'2024-03-02 00:00:00.000000',25000,100,'chai','MED009','4'),('11',1225,'2024-03-18 00:00:00.000000',25000,2,'chai','MED009','5'),('12',1225,'2024-03-18 00:00:00.000000',30000,100,'chai','MED004','5'),('13',1225,'2024-03-18 00:00:00.000000',20000,100,'chai','MED007','5'),('2',1213,'2024-03-10 00:00:00.000000',30000,100,'hộp','MED003','1'),('3',1214,'2024-03-10 00:00:00.000000',40000,100,'hộp','MED006','1'),('4',1215,'2024-03-16 00:00:00.000000',40000,50,'hộp','MED003','2'),('5',1216,'2024-03-16 00:00:00.000000',45000,100,'hộp','MED002','2'),('6',1217,'2024-03-08 00:00:00.000000',25000,100,'hộp','MED005','3'),('7',1218,'2024-03-08 00:00:00.000000',40000,100,'hộp','MED007','3'),('8',1221,'2024-03-08 00:00:00.000000',40000,50,'hộp','MED004','3'),('9',1224,'2024-03-02 00:00:00.000000',25000,100,'chai','MED008','4');
/*!40000 ALTER TABLE `ware_in_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `warehouse_in`
--

DROP TABLE IF EXISTS `warehouse_in`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `warehouse_in` (
  `warehouse_in_id` varchar(255) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `payment_voucher` varchar(255) DEFAULT NULL,
  `pharmacy_pay` float DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `total_amount` float DEFAULT NULL,
  `employee_id` varchar(255) DEFAULT NULL,
  `supplier_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`warehouse_in_id`),
  KEY `FK4v9xojeidxdhq2gpr5hkfy97g` (`employee_id`),
  KEY `FK2tron252jfipm0m6tix30yrq` (`supplier_id`),
  CONSTRAINT `FK2tron252jfipm0m6tix30yrq` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`supplier_id`),
  CONSTRAINT `FK4v9xojeidxdhq2gpr5hkfy97g` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `warehouse_in`
--

LOCK TABLES `warehouse_in` WRITE;
/*!40000 ALTER TABLE `warehouse_in` DISABLE KEYS */;
INSERT INTO `warehouse_in` VALUES ('1','2024-02-05 07:00:00',NULL,3000000,NULL,9500000,'NV01','DATRACO'),('2','2024-02-07 10:00:00',NULL,5000000,NULL,6500000,'NV02','HDVN'),('3','2024-02-17 15:00:00',NULL,4300000,NULL,8500000,'NV02','INMEX'),('4','2024-02-19 14:00:00',NULL,3500000,NULL,5000000,'NV04','VIMEC'),('5','2024-02-16 15:30:00',NULL,1050000,NULL,5050000,'NV03','TW2');
/*!40000 ALTER TABLE `warehouse_in` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `warehouse_out`
--

DROP TABLE IF EXISTS `warehouse_out`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `warehouse_out` (
  `warehouse_out_id` varchar(255) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `export_type` varchar(255) DEFAULT NULL,
  `payment_voucher` varchar(255) DEFAULT NULL,
  `reason` varchar(255) DEFAULT NULL,
  `total_amount` float DEFAULT NULL,
  `employee_id` varchar(255) DEFAULT NULL,
  `supplier_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`warehouse_out_id`),
  KEY `FKd0bx6ilaw6p780loxsw8yke1q` (`employee_id`),
  KEY `FKer9x3sjgd0w8mjamjcdnecnux` (`supplier_id`),
  CONSTRAINT `FKd0bx6ilaw6p780loxsw8yke1q` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`employee_id`),
  CONSTRAINT `FKer9x3sjgd0w8mjamjcdnecnux` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`supplier_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `warehouse_out`
--

LOCK TABLES `warehouse_out` WRITE;
/*!40000 ALTER TABLE `warehouse_out` DISABLE KEYS */;
/*!40000 ALTER TABLE `warehouse_out` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `warehouse_out_detail`
--

DROP TABLE IF EXISTS `warehouse_out_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `warehouse_out_detail` (
  `ware_out_detail_id` varchar(255) NOT NULL,
  `expired_date` datetime(6) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `quantity` int NOT NULL,
  `unit` varchar(255) DEFAULT NULL,
  `medicine_id` varchar(255) DEFAULT NULL,
  `warehouse_out_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ware_out_detail_id`),
  KEY `FKknkq9t31a7l6q9v6e9nc1dg2g` (`medicine_id`),
  KEY `FKk1owttkviqp0cdc7tqiwoirm3` (`warehouse_out_id`),
  CONSTRAINT `FKk1owttkviqp0cdc7tqiwoirm3` FOREIGN KEY (`warehouse_out_id`) REFERENCES `warehouse_out` (`warehouse_out_id`),
  CONSTRAINT `FKknkq9t31a7l6q9v6e9nc1dg2g` FOREIGN KEY (`medicine_id`) REFERENCES `medicine` (`medicine_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `warehouse_out_detail`
--

LOCK TABLES `warehouse_out_detail` WRITE;
/*!40000 ALTER TABLE `warehouse_out_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `warehouse_out_detail` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-03-25 20:04:16
