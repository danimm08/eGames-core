INSERT INTO `authority` VALUES (1,0,'ROLE_USER');

INSERT INTO `user_account` VALUES (2,0,'\0','\0','\0','email1@email.com','\0','fab0d07c218b3408515216fcee8d1357e9e066c4001b2fb394b04f92b9c635678970c0aa7858cffc','username1'),(3,0,'\0','\0','\0','email1@email.com','\0','b1615ca030be27e52906cfab88d7e8d927cf0999886ef55f0cecd9976671dc8b0e0d64b1d549197e','username2');

INSERT INTO `user` VALUES (2,1,'city1','Country1','state1','street1','21209',0,'User1',NULL,0,'Surname1',2),(3,0,'city2','Country2','state2','street2','21200',0,'User2',NULL,0,'Surname2',3);

INSERT INTO `user_authority` VALUES (2,1),(3,1);

INSERT INTO `platform` VALUES (1,0,'PS4');

INSERT INTO `game` VALUES (1,1,'http://google.com','2017-03-09 12:58:12','EA Sports','Juego de f?tbol','Muy bueno',0,'Fifa 17',1),(2,0,'http://google.com','2017-03-09 12:58:12','Naughty Dogs','Juego de zombies','Muy bueno',26,'The last of us',1);

INSERT INTO `personal_game` (id,version,description, number_of_views, type, exchange_id, game_id, user_id) VALUES (1,0,'Está nuevo, no lo quiero porque me he cansado de él.',0,'Fijo',NULL,1,2),(2,0,'Ya me lo he pasado, y quiero cambiarlo por otro',0,'Fijo',NULL,2,3);

INSERT INTO `hibernate_sequences` VALUES ('user_account',1),('user',1);


