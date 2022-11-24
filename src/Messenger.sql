-- MariaDB dump 10.19  Distrib 10.9.4-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: Messenger
-- ------------------------------------------------------
-- Server version	10.9.4-MariaDB

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
-- Table structure for table `KeyCache`
--

DROP TABLE IF EXISTS `KeyCache`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `KeyCache` (
  `user1` varchar(20) DEFAULT NULL,
  `user2` varchar(20) DEFAULT NULL,
  `g` int(20) DEFAULT NULL,
  `p` int(20) DEFAULT NULL,
  `A` int(20) DEFAULT NULL,
  `B` int(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `KeyCache`
--

LOCK TABLES `KeyCache` WRITE;
/*!40000 ALTER TABLE `KeyCache` DISABLE KEYS */;
INSERT INTO `KeyCache` VALUES
('daniel','daniel',304473950,437406349,405188939,NULL),
('daniel','quint',1609631,2351353,851532,NULL),
('daniel','asdf',253112307,997112591,743437004,NULL),
('asdfasdf','quint',622267065,897070099,843660363,NULL),
('urne','quint',12207630,261870139,160983260,NULL),
('asdfasdfasddf','daniel',83412115,346293047,343023665,NULL),
('daniela','quint',166348669,331210969,51093098,NULL),
('danielasdfasdfadsdf','daniel',480339498,860433317,552868720,NULL),
('danielasdfasdfadsdf','quint',128619152,149343373,68001773,NULL),
('dfsdaniel','quint',609414,281853487,203382818,NULL),
('danialdsfj','quint',117969426,127422787,105248961,NULL),
('wqdf23','quint',237105143,960207221,189772805,NULL),
('asdkfje8','quint',457957303,974140121,878740786,NULL);
/*!40000 ALTER TABLE `KeyCache` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Messages`
--

DROP TABLE IF EXISTS `Messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Messages` (
  `send` varchar(20) DEFAULT NULL,
  `recv` varchar(20) DEFAULT NULL,
  `Message` text DEFAULT NULL,
  `Time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Messages`
--

LOCK TABLES `Messages` WRITE;
/*!40000 ALTER TABLE `Messages` DISABLE KEYS */;
INSERT INTO `Messages` VALUES
('daniel','daniel','hahahahah!!â¬',NULL),
('daniel','daniel','â¬â¬â¬â¬â¬â¬â¬â¬â¬â¬â¬',NULL),
('daniel','daniel','€¶ŧæſſđð¢ŋ¶ŧ←↓ŋħħ←↓→←',NULL),
('daniel','daniel','ſ€¶.ðſ.ŋ→¶”µðſđŋ→€¶ŧ',NULL),
('daniel','daniel','very productive, noicH?',NULL),
('daniel','daniel','asdfkjöaskdf','2022-09-14 19:18:24'),
('daniel','daniel','daniel','2022-09-14 19:19:05'),
('daniel','daniel','hallo','2022-09-14 19:19:08'),
('quint','daniel','daniel, wie gehts','2022-09-14 19:29:09'),
('daniel','quint','daniel, wie gehts','2022-09-14 19:29:46'),
('Valentin','daniel','Hallo, wie geht\'s','2022-09-14 19:50:42'),
('Valentin','quint','Du penis','2022-09-14 19:51:25'),
('daniel','quint','Mir geht\'s gut!','2022-09-14 20:03:18'),
('daniel','valentin','Mir geht es nicht gut','2022-09-14 20:03:33');
/*!40000 ALTER TABLE `Messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `People`
--

DROP TABLE IF EXISTS `People`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `People` (
  `Username` varchar(20) DEFAULT NULL,
  `CD` datetime DEFAULT NULL,
  `Password` varchar(512) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `People`
--

LOCK TABLES `People` WRITE;
/*!40000 ALTER TABLE `People` DISABLE KEYS */;
INSERT INTO `People` VALUES
('daniel','2022-09-14 18:27:10','912ec803b2ce49e4a541068d495ab570'),
('quint','2022-09-14 19:28:57','aa1d769b3265ea4f62e09acad4804baa'),
('Valentin','2022-09-14 19:49:11','3002d13848963ebb002878049393f77b'),
('asdf','2022-11-24 20:16:46','912ec803b2ce49e4a541068d495ab570'),
('asdfasdf','2022-11-24 20:33:45','6a204bd89f3c8348afd5c77c717a097a'),
('urne','2022-11-24 20:35:12','2918f3b4f699f80bcafb2607065451e1'),
('asdfasdfasddf','2022-11-24 20:36:59','2addd73b0ae2e4109c72ea82ba3016cf'),
('daniela','2022-11-24 20:48:22','912ec803b2ce49e4a541068d495ab570'),
('danielasdfasdfadsdf','2022-11-24 20:50:58','a95c530a7af5f492a74499e70578d150'),
('asdfe5t','2022-11-24 20:55:24','e332a76c29654fcb7f6e6b31ced090c7'),
('danielaskldjöf','2022-11-24 20:57:10','31627fa594ac82156d11ec0943d6d82e'),
('danielad','2022-11-24 21:00:11','a95c530a7af5f492a74499e70578d150'),
('dfsdaniel','2022-11-24 21:01:35','912ec803b2ce49e4a541068d495ab570'),
('danialdsfj','2022-11-24 21:03:06','f37e4a21aa0c742c704f441a48962bf6'),
('wqdf23','2022-11-24 21:07:59','912ec803b2ce49e4a541068d495ab570'),
('asdkfje8','2022-11-24 21:12:54','ef124c2e091b6457c05d206f7da4418d');
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

-- Dump completed on 2022-11-24 23:17:39
