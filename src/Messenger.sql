-- MariaDB dump 10.19  Distrib 10.9.2-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: Messenger
-- ------------------------------------------------------
-- Server version	10.9.2-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Messages`
--

DROP TABLE IF EXISTS `Messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Messages` (
  `send` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `recv` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Message` text COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Messages`
--

LOCK TABLES `Messages` WRITE;
/*!40000 ALTER TABLE `Messages` DISABLE KEYS */;
INSERT INTO `Messages` VALUES
('dan','dan','very productive, noicH?',NULL),
('dan','dan','asdfkjöaskdf','2022-09-14 19:18:24'),
('dan','dan','daniel','2022-09-14 19:19:05'),
('dan','dan','hallo','2022-09-14 19:19:08'),
('wertzuz','dan','dan, wie gehts','2022-09-14 19:29:09'),
('dan','wertzuz','dan, wie gehts','2022-09-14 19:29:46'),
('cula','dan','Hallo, wie geht\'s','2022-09-14 19:50:42'),
('cula','wertzuz','Du p*nis','2022-09-14 19:51:25'),
('dan','wertzuz','Mir geht\'s gut!','2022-09-14 20:03:18'),
('dan','cula','Mir geht es nicht gut','2022-09-14 20:03:33');
/*!40000 ALTER TABLE `Messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `People`
--

DROP TABLE IF EXISTS `People`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `People` (
  `Username` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `CD` datetime DEFAULT NULL,
  `Password` varchar(512) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `People`
--

LOCK TABLES `People` WRITE;
/*!40000 ALTER TABLE `People` DISABLE KEYS */;
INSERT INTO `People` VALUES
('dan','2022-09-14 18:27:10','912ec803b2ce49e4a541068d495ab570'),
('wertzuz','2022-09-14 19:28:57','aa1d769b3265ea4f62e09acad4804baa'),
('cula','2022-09-14 19:49:11','3002d13848963ebb002878049393f77b');
/*!40000 ALTER TABLE `People` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-09-14 22:40:55
