\c postgres
DROP DATABASE IF EXISTS shopping_database;
DROP ROLE IF EXISTS student;
CREATE ROLE student WITH PASSWORD 'himitu' LOGIN;
CREATE DATABASE shopping_database OWNER student ENCODING 'UTF8';