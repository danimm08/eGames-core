INSERT INTO user_account (id,version,username,email, password) VALUES (1,0,'admin', 'admin@mail.me', 'b8f57d6d6ec0a60dfe2e20182d4615b12e321cad9e2979e0b9f81e0d6eda78ad9b6dcfe53e4e22d1');
INSERT INTO user_account (id,version,username,email, password) VALUES (2,0,'user', 'user@mail.me', 'd6dfa9ff45e03b161e7f680f35d90d5ef51d243c2a8285aa7e11247bc2c92acde0c2bb626b1fac74');
INSERT INTO user_account (id,version,username,email, password) VALUES (3,0,'dani', 'dani@abc.com', '4ec7a04102b1663ce90e43dde8f39485680f4304417566191e9225e3e8fcfc52c4d7b7dcbe3823ef');


INSERT INTO authority (name) VALUES ('ROLE_USER');
INSERT INTO authority (name) VALUES ('ROLE_ADMIN');

INSERT INTO user_authority (user_account,authority) VALUES (3, 'ROLE_USER');
INSERT INTO user_authority (user_account,authority) VALUES (2, 'ROLE_USER');
INSERT INTO user_authority (user_account,authority) VALUES (1, 'ROLE_USER');
INSERT INTO user_authority (user_account,authority) VALUES (1, 'ROLE_ADMIN');