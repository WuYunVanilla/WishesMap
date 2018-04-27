-- Database: WishesMap
--
-- Table structure for table `user`
--


DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `user_ID` int NOT NULL,
  `user_name` text NOT NULL,
  `user_pass` text NOT NULL,
  
  PRIMARY KEY (`user_ID`),
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `user` VALUES ('1','fry','fry123',), ('2','wy','wy123');
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

-- Dump completed on 2016-12-22 18:04:16
