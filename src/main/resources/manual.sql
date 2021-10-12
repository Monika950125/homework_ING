CREATE DATABASE IF NOT EXISTS ing_homework;
CREATE USER IF NOT EXISTS 'monika'@'localhost' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON ing_homework.* TO 'monika'@'localhost';