#!/bin/bash
apt-get update
apt-get install -y mysql-server
apt-get install -y default-jre
apt-get install -y default-jdk
apt-get install -y python python-pip
apt-get install -y python-mysqldb

mysql -uroot -proot < configDatabase.sql
mysql -uplayer -pplayer eGames < initialize.sql

cp -r images /TFGDMM/images

cd /TFGDMM && pip install -r eGames-recommender/requirements.txt
cd eGames-core && ./mvnw spring-boot:run