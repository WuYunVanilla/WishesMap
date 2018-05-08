
DROP TABLE IF EXISTS `wishes`;

CREATE TABLE `wishes`(
  wishes_ID INT  NOT NULL AUTO_INCREMENT PRIMARY KEY ,
  lat DOUBLE ,
  lng DOUBLE ,
  status TINYINT ,
  title TEXT,
  content TEXT ,
  location TEXT ,
  date DATE,
  date_finish DATE,
  pic MEDIUMBLOB,
  user_ID INT ,
  FOREIGN KEY (user_ID) REFERENCES users(user_ID)

 )ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `wishes`(wishes_ID,lat,lng,status,title,content,location,date,date_finish,user_ID)  VALUE (1, 31.205619, 121.603259, 0, '在冰雪主题乐园游玩半天','溜冰&滑雪','冰雪主题乐园悠游堂','2018-05-10','2018-06-20', 1 );
INSERT INTO `wishes`(wishes_ID,lat,lng,status,title,content,location,date,date_finish,user_ID)  VALUE (2, 31.205995, 121.600896, 0, '吃甜品','和朋友聚餐','避风塘','2018-05-10','2018-06-20', 1 );

SELECT  * FROM wishes WHERE status = '0';