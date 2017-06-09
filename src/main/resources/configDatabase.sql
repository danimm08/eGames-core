create database eGames;
create user 'player'@'%' identified by 'player';
grant select, insert, update, delete, create, drop, references, index, alter, create temporary tables, lock tables, create view, create routine, alter routine, execute, trigger, show view on `eGames`.* to 'player'@'%';