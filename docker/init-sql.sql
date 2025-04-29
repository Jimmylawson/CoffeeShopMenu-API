-- create the databases
CREATE DATABASE IF NOT EXISTS coffee_menu;

-- create the users for each database
CREATE USER 'appuser'@'%' IDENTIFIED BY 'apppasswd';
GRANT CREATE, ALTER, INDEX, LOCK TABLES, REFERENCES, UPDATE, DELETE, DROP, SELECT, INSERT ON `coffee_menu`.* TO 'appuser'@'%';

FLUSH PRIVILEGES;