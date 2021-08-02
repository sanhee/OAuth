ALTER DATABASE `pintree` DEFAULT CHARACTER SET = utf8mb4;

DROP DATABASE pintree;
CREATE DATABASE IF NOT EXISTS pintree CHARACTER SET utf8mb4;
USE pintree;

DROP TABLE IF EXISTS `pintree`.`user`;
CREATE TABLE `pintree`.`user`
(
    `id`              VARCHAR(50) NOT NULL PRIMARY KEY,
    `name`            VARCHAR(50),
    `avatarUrl` VARCHAR(300)
);

DROP TABLE IF EXISTS `pintree`.`email`;
CREATE TABLE `pintree`.`email`
(
    `email`  VARCHAR(50)  NOT NULL PRIMARY KEY,
    `userId` VARCHAR(50) NOT NULL,
    FOREIGN KEY (`userId`) REFERENCES `pintree`.`user` (`id`)
);