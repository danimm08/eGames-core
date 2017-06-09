#!/bin/bash
apt-get update
apt-get install -y mysql-server
apt-get install -y default-jre
apt-get install -y default-jdk
apt-get install -y python python-pip
apt-get install -y python-mysqldb

mysql -uroot -proot < eGames-core/src/main/resources/configDatabase.sql
mysql -uplayer -pplayer eGames < eGames-core/src/main/resources/initialize.sql

mkdir /TFGDMM
cp -r /TFGDMM/eGames-core/src/main/resources/images /TFGDMM/images

pip install -r eGames-recommender/requirements.txt

/eGames-core/./mvnw spring-boot:run