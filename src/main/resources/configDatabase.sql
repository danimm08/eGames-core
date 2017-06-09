create database eGames;
create user 'player'@'%' identified by 'player';
grant select, insert, update, delete, create, drop, alter on `eGames`.* to 'player'@'%';