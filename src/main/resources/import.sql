INSERT INTO `authority` VALUES (1,0,'ROLE_USER');

INSERT INTO `user_account` VALUES (2,0,'\0','\0','\0','email1@email.com','\0','fab0d07c218b3408515216fcee8d1357e9e066c4001b2fb394b04f92b9c635678970c0aa7858cffc','username1'),(3,0,'\0','\0','\0','email1@email.com','\0','b1615ca030be27e52906cfab88d7e8d927cf0999886ef55f0cecd9976671dc8b0e0d64b1d549197e','username2');

INSERT INTO `user` VALUES (2,1,'city1','Country1','state1','street1','12345',0,'User1',NULL,0,'Surname1',2),(3,0,'city2','Country2','state2','street2','12345',0,'User2',NULL,0,'Surname2',3);

INSERT INTO `user_authority` VALUES (2,1),(3,1);

INSERT INTO `platform` VALUES (1,0,'PS4');

INSERT INTO `game` VALUES (1,1,'http://google.com','2017-03-09 12:58:12','EA Sports','Juego de f?tbol','Muy bueno',0,'Fifa 17',1),(2,0,'http://google.com','2017-03-09 12:58:12','Naughty Dogs','Juego de zombies','Muy bueno',26,'The last of us',1);

INSERT INTO `personal_game` VALUES (1,0,'Est? nuevo, no lo quiero porque me he cansado de ?l.',0,'Fijo',NULL,1,2),(2,0,'Ya me lo he pasado, y quiero cambiarlo por otro',0,'Fijo',NULL,2,3);

INSERT INTO `hibernate_sequences` VALUES ('user_account',1),('user',1);

INSERT INTO `oauth_access_token` VALUES ('6dd3c6073896629fb0ccc11bbb7cb6a8','?\?\0sr\0Corg.springframework.security.oauth2.common.DefaultOAuth2AccessToken??6$?\?\0L\0additionalInformationt\0Ljava/util/Map;L\0\nexpirationt\0Ljava/util/Date;L\0refreshTokent\0?Lorg/springframework/security/oauth2/common/OAuth2RefreshToken;L\0scopet\0Ljava/util/Set;L\0	tokenTypet\0Ljava/lang/String;L\0valueq\0~\0xpsr\0java.util.Collections$EmptyMapY6?Z\?\?\?\0\0xpsr\0java.util.Datehj?KYt\0\0xpw\0\0^^?Xxsr\0Lorg.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken/\?Gc?\??\0L\0\nexpirationq\0~\0xr\0Dorg.springframework.security.oauth2.common.DefaultOAuth2RefreshTokens\?\ncT\?^\0L\0valueq\0~\0xpt\0$11cdf8e4-e865-47d4-bd8c-f14fb5c114bdsq\0~\0	w\0\0[Mq\?Uxsr\0%java.util.Collections$UnmodifiableSet?????U\0\0xr\0,java.util.Collections$UnmodifiableCollectionB\0?\?^?\0L\0ct\0Ljava/util/Collection;xpsr\0java.util.LinkedHashSet\?l\?Z?\?*\0\0xr\0java.util.HashSet?D?????4\0\0xpw\0\0\0?@\0\0\0\0\0t\0readt\0writext\0bearert\0$4240efc3-aaa7-49a3-93e7-c42f694b4d27','b8d426dcbe3333627563f8192a478155','username1','eGamesApp','?\?\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2Authentication?@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationToken?(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableList?%1?\?\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0?\?^?\0L\0cq\0~\0xpsr\0java.util.ArrayListx?\??\?a?\0I\0sizexp\0\0\0w\0\0\0sr\0Borg.springframework.security.core.authority.SimpleGrantedAuthority\0\0\0\0\0\0@\0L\0rolet\0Ljava/lang/String;xpt\0	ROLE_USERxq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUriq\0~\0L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0xr\08org.springframework.security.oauth2.provider.BaseRequest6(z>?qi?\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0xpt\0	eGamesAppsr\0%java.util.Collections$UnmodifiableMap??t?B\0L\0mq\0~\0xpsr\0java.util.HashMap\??\?`\?\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\ngrant_typet\0passwordt\0usernamet\0	username1t\0scopet\0\0xsr\0%java.util.Collections$UnmodifiableSet?????U\0\0xq\0~\0	sr\0java.util.LinkedHashSet\?l\?Z?\?*\0\0xr\0java.util.HashSet?D?????4\0\0xpw\0\0\0?@\0\0\0\0\0t\0readt\0writexsq\0~\0$w\0\0\0?@\0\0\0\0\0sq\0~\0\rt\0	ROLE_USERsq\0~\0\rt\0\nROLE_ADMINxsq\0~\0?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xppsq\0~\0$w\0\0\0?@\0\0\0\0\0\0xsr\0Oorg.springframework.security.authentication.UsernamePasswordAuthenticationToken\0\0\0\0\0\0@\0L\0credentialsq\0~\0L\0	principalq\0~\0xq\0~\0sq\0~\0sq\0~\0\0\0\0w\0\0\0q\0~\0xq\0~\02ppsr\02org.springframework.security.core.userdetails.User\0\0\0\0\0\0@\0Z\0accountNonExpiredZ\0accountNonLockedZ\0credentialsNonExpiredZ\0enabledL\0authoritiesq\0~\0L\0passwordq\0~\0L\0usernameq\0~\0xpsq\0~\0!sr\0java.util.TreeSet?P??\?[\0\0xpsr\0Forg.springframework.security.core.userdetails.User$AuthorityComparator\0\0\0\0\0\0@\0\0xpw\0\0\0q\0~\0xpt\0	username1','0749eeba26f08202f214e0e0f6056ef5'),('e43130f64e98077487b90349ed692e95','?\?\0sr\0Corg.springframework.security.oauth2.common.DefaultOAuth2AccessToken??6$?\?\0L\0additionalInformationt\0Ljava/util/Map;L\0\nexpirationt\0Ljava/util/Date;L\0refreshTokent\0?Lorg/springframework/security/oauth2/common/OAuth2RefreshToken;L\0scopet\0Ljava/util/Set;L\0	tokenTypet\0Ljava/lang/String;L\0valueq\0~\0xpsr\0java.util.Collections$EmptyMapY6?Z\?\?\?\0\0xpsr\0java.util.Datehj?KYt\0\0xpw\0\0^^\???xsr\0Lorg.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken/\?Gc?\??\0L\0\nexpirationq\0~\0xr\0Dorg.springframework.security.oauth2.common.DefaultOAuth2RefreshTokens\?\ncT\?^\0L\0valueq\0~\0xpt\0$a468a4ff-5ff5-4d6e-8c2f-ad8b85ea6550sq\0~\0	w\0\0[Mr\'?xsr\0%java.util.Collections$UnmodifiableSet?????U\0\0xr\0,java.util.Collections$UnmodifiableCollectionB\0?\?^?\0L\0ct\0Ljava/util/Collection;xpsr\0java.util.LinkedHashSet\?l\?Z?\?*\0\0xr\0java.util.HashSet?D?????4\0\0xpw\0\0\0?@\0\0\0\0\0t\0readt\0writext\0bearert\0$280cb7fb-a9e0-4560-9322-d65ed969722a','934690ddb10dc64d20f1b5004e1a40ca','username2','eGamesApp','?\?\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2Authentication?@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationToken?(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableList?%1?\?\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0?\?^?\0L\0cq\0~\0xpsr\0java.util.ArrayListx?\??\?a?\0I\0sizexp\0\0\0w\0\0\0sr\0Borg.springframework.security.core.authority.SimpleGrantedAuthority\0\0\0\0\0\0@\0L\0rolet\0Ljava/lang/String;xpt\0	ROLE_USERxq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUriq\0~\0L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0xr\08org.springframework.security.oauth2.provider.BaseRequest6(z>?qi?\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0xpt\0	eGamesAppsr\0%java.util.Collections$UnmodifiableMap??t?B\0L\0mq\0~\0xpsr\0java.util.HashMap\??\?`\?\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\ngrant_typet\0passwordt\0usernamet\0	username2t\0scopet\0\0xsr\0%java.util.Collections$UnmodifiableSet?????U\0\0xq\0~\0	sr\0java.util.LinkedHashSet\?l\?Z?\?*\0\0xr\0java.util.HashSet?D?????4\0\0xpw\0\0\0?@\0\0\0\0\0t\0readt\0writexsq\0~\0$w\0\0\0?@\0\0\0\0\0sq\0~\0\rt\0	ROLE_USERsq\0~\0\rt\0\nROLE_ADMINxsq\0~\0?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xppsq\0~\0$w\0\0\0?@\0\0\0\0\0\0xsr\0Oorg.springframework.security.authentication.UsernamePasswordAuthenticationToken\0\0\0\0\0\0@\0L\0credentialsq\0~\0L\0	principalq\0~\0xq\0~\0sq\0~\0sq\0~\0\0\0\0w\0\0\0q\0~\0xq\0~\02ppsr\02org.springframework.security.core.userdetails.User\0\0\0\0\0\0@\0Z\0accountNonExpiredZ\0accountNonLockedZ\0credentialsNonExpiredZ\0enabledL\0authoritiesq\0~\0L\0passwordq\0~\0L\0usernameq\0~\0xpsq\0~\0!sr\0java.util.TreeSet?P??\?[\0\0xpsr\0Forg.springframework.security.core.userdetails.User$AuthorityComparator\0\0\0\0\0\0@\0\0xpw\0\0\0q\0~\0xpt\0	username2','1f148762e57ce46f507e9a6a2974d804');

INSERT INTO `oauth_refresh_token` VALUES ('ac668074db8376f28a13d1dde254a1fc','?\?\0sr\0Lorg.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken/\?Gc?\??\0L\0\nexpirationt\0Ljava/util/Date;xr\0Dorg.springframework.security.oauth2.common.DefaultOAuth2RefreshTokens\?\ncT\?^\0L\0valuet\0Ljava/lang/String;xpt\0$774f6e56-35cd-416f-97f5-09704c4abb73sr\0java.util.Datehj?KYt\0\0xpw\0\0[;\?x','?\?\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2Authentication?@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationToken?(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableList?%1?\?\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0?\?^?\0L\0cq\0~\0xpsr\0java.util.ArrayListx?\??\?a?\0I\0sizexp\0\0\0w\0\0\0sr\0Borg.springframework.security.core.authority.SimpleGrantedAuthority\0\0\0\0\0\0@\0L\0rolet\0Ljava/lang/String;xpt\0	ROLE_USERxq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUriq\0~\0L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0xr\08org.springframework.security.oauth2.provider.BaseRequest6(z>?qi?\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0xpt\0	eGamesAppsr\0%java.util.Collections$UnmodifiableMap??t?B\0L\0mq\0~\0xpsr\0java.util.HashMap\??\?`\?\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\ngrant_typet\0passwordt\0usernamet\0	username1t\0scopet\0\0xsr\0%java.util.Collections$UnmodifiableSet?????U\0\0xq\0~\0	sr\0java.util.LinkedHashSet\?l\?Z?\?*\0\0xr\0java.util.HashSet?D?????4\0\0xpw\0\0\0?@\0\0\0\0\0t\0readt\0writexsq\0~\0$w\0\0\0?@\0\0\0\0\0sq\0~\0\rt\0	ROLE_USERsq\0~\0\rt\0\nROLE_ADMINxsq\0~\0?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xppsq\0~\0$w\0\0\0?@\0\0\0\0\0\0xsr\0Oorg.springframework.security.authentication.UsernamePasswordAuthenticationToken\0\0\0\0\0\0@\0L\0credentialsq\0~\0L\0	principalq\0~\0xq\0~\0sq\0~\0sq\0~\0\0\0\0w\0\0\0q\0~\0xq\0~\02ppsr\02org.springframework.security.core.userdetails.User\0\0\0\0\0\0@\0Z\0accountNonExpiredZ\0accountNonLockedZ\0credentialsNonExpiredZ\0enabledL\0authoritiesq\0~\0L\0passwordq\0~\0L\0usernameq\0~\0xpsq\0~\0!sr\0java.util.TreeSet?P??\?[\0\0xpsr\0Forg.springframework.security.core.userdetails.User$AuthorityComparator\0\0\0\0\0\0@\0\0xpw\0\0\0q\0~\0xpt\0	username1'),('0749eeba26f08202f214e0e0f6056ef5','?\?\0sr\0Lorg.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken/\?Gc?\??\0L\0\nexpirationt\0Ljava/util/Date;xr\0Dorg.springframework.security.oauth2.common.DefaultOAuth2RefreshTokens\?\ncT\?^\0L\0valuet\0Ljava/lang/String;xpt\0$11cdf8e4-e865-47d4-bd8c-f14fb5c114bdsr\0java.util.Datehj?KYt\0\0xpw\0\0[Mq\?Ux','?\?\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2Authentication?@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationToken?(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableList?%1?\?\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0?\?^?\0L\0cq\0~\0xpsr\0java.util.ArrayListx?\??\?a?\0I\0sizexp\0\0\0w\0\0\0sr\0Borg.springframework.security.core.authority.SimpleGrantedAuthority\0\0\0\0\0\0@\0L\0rolet\0Ljava/lang/String;xpt\0	ROLE_USERxq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUriq\0~\0L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0xr\08org.springframework.security.oauth2.provider.BaseRequest6(z>?qi?\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0xpt\0	eGamesAppsr\0%java.util.Collections$UnmodifiableMap??t?B\0L\0mq\0~\0xpsr\0java.util.HashMap\??\?`\?\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\ngrant_typet\0passwordt\0usernamet\0	username1t\0scopet\0\0xsr\0%java.util.Collections$UnmodifiableSet?????U\0\0xq\0~\0	sr\0java.util.LinkedHashSet\?l\?Z?\?*\0\0xr\0java.util.HashSet?D?????4\0\0xpw\0\0\0?@\0\0\0\0\0t\0readt\0writexsq\0~\0$w\0\0\0?@\0\0\0\0\0sq\0~\0\rt\0	ROLE_USERsq\0~\0\rt\0\nROLE_ADMINxsq\0~\0?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xppsq\0~\0$w\0\0\0?@\0\0\0\0\0\0xsr\0Oorg.springframework.security.authentication.UsernamePasswordAuthenticationToken\0\0\0\0\0\0@\0L\0credentialsq\0~\0L\0	principalq\0~\0xq\0~\0sq\0~\0sq\0~\0\0\0\0w\0\0\0q\0~\0xq\0~\02ppsr\02org.springframework.security.core.userdetails.User\0\0\0\0\0\0@\0Z\0accountNonExpiredZ\0accountNonLockedZ\0credentialsNonExpiredZ\0enabledL\0authoritiesq\0~\0L\0passwordq\0~\0L\0usernameq\0~\0xpsq\0~\0!sr\0java.util.TreeSet?P??\?[\0\0xpsr\0Forg.springframework.security.core.userdetails.User$AuthorityComparator\0\0\0\0\0\0@\0\0xpw\0\0\0q\0~\0xpt\0	username1'),('1f148762e57ce46f507e9a6a2974d804','?\?\0sr\0Lorg.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken/\?Gc?\??\0L\0\nexpirationt\0Ljava/util/Date;xr\0Dorg.springframework.security.oauth2.common.DefaultOAuth2RefreshTokens\?\ncT\?^\0L\0valuet\0Ljava/lang/String;xpt\0$a468a4ff-5ff5-4d6e-8c2f-ad8b85ea6550sr\0java.util.Datehj?KYt\0\0xpw\0\0[Mr\'?x','?\?\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2Authentication?@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationToken?(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableList?%1?\?\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0?\?^?\0L\0cq\0~\0xpsr\0java.util.ArrayListx?\??\?a?\0I\0sizexp\0\0\0w\0\0\0sr\0Borg.springframework.security.core.authority.SimpleGrantedAuthority\0\0\0\0\0\0@\0L\0rolet\0Ljava/lang/String;xpt\0	ROLE_USERxq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUriq\0~\0L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0xr\08org.springframework.security.oauth2.provider.BaseRequest6(z>?qi?\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0xpt\0	eGamesAppsr\0%java.util.Collections$UnmodifiableMap??t?B\0L\0mq\0~\0xpsr\0java.util.HashMap\??\?`\?\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\ngrant_typet\0passwordt\0usernamet\0	username2t\0scopet\0\0xsr\0%java.util.Collections$UnmodifiableSet?????U\0\0xq\0~\0	sr\0java.util.LinkedHashSet\?l\?Z?\?*\0\0xr\0java.util.HashSet?D?????4\0\0xpw\0\0\0?@\0\0\0\0\0t\0readt\0writexsq\0~\0$w\0\0\0?@\0\0\0\0\0sq\0~\0\rt\0	ROLE_USERsq\0~\0\rt\0\nROLE_ADMINxsq\0~\0?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xppsq\0~\0$w\0\0\0?@\0\0\0\0\0\0xsr\0Oorg.springframework.security.authentication.UsernamePasswordAuthenticationToken\0\0\0\0\0\0@\0L\0credentialsq\0~\0L\0	principalq\0~\0xq\0~\0sq\0~\0sq\0~\0\0\0\0w\0\0\0q\0~\0xq\0~\02ppsr\02org.springframework.security.core.userdetails.User\0\0\0\0\0\0@\0Z\0accountNonExpiredZ\0accountNonLockedZ\0credentialsNonExpiredZ\0enabledL\0authoritiesq\0~\0L\0passwordq\0~\0L\0usernameq\0~\0xpsq\0~\0!sr\0java.util.TreeSet?P??\?[\0\0xpsr\0Forg.springframework.security.core.userdetails.User$AuthorityComparator\0\0\0\0\0\0@\0\0xpw\0\0\0q\0~\0xpt\0	username2');



