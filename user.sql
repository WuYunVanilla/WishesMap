-- Database: WishesMap
--
-- Table structure for table `user`
--


DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  
  PRIMARY KEY (`user_id`),
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `user` VALUES ('fry','fry123',), ('wy','wy123');
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

-- Dump completed on 2016-12-22 18:04:16
