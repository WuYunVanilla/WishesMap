
DROP TABLE IF EXISTS `users`;

CREATE TABLE `users`(
  user_ID INT  NOT NULL AUTO_INCREMENT PRIMARY KEY ,
  user_name text ,
  user_pass text
 )ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `users` (user_ID, user_name, user_pass) VALUE (1, 'admin', '123456');
INSERT INTO `users` (user_ID, user_name, user_pass) VALUE (2, 'root', '123456');
INSERT INTO `users` (user_ID, user_name, user_pass) VALUE (3, 'tomcat', '123456');
INSERT INTO `users` (user_ID, user_name, user_pass) VALUE (4, 'servlet', '123456');

SELECT  user_ID FROM users WHERE user_name = 'root' AND user_pass = '123456';