DROP TABLE if exists messages;
DROP TABLE if exists articles_descriptions;
DROP TABLE if exists articles_images;
DROP TABLE if exists articles_keywords;
DROP TABLE if exists api_keys;
DROP TABLE if exists articles;
DROP TABLE if exists users_authorities;
DROP TABLE if exists users;

CREATE TABLE `users` (
	`ID` INT(10) NOT NULL AUTO_INCREMENT,
	`NAME` CHAR(50) NOT NULL,
	`NAME_CRC` INT NOT NULL,
	`EMAIL` CHAR(50) NOT NULL,
	`EMAIL_CRC` INT(11) NOT NULL,
	`PASSWORD` VARCHAR(100) NOT NULL,
	`TELEPHONE` CHAR(50) NOT NULL,
	PRIMARY KEY (`ID`),
	INDEX `EMAIL_CRC` (`EMAIL_CRC`),
	INDEX `NAME_CRC` (`NAME_CRC`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;

CREATE TABLE `articles` (
	`ID` INT(10) NOT NULL AUTO_INCREMENT,
	`IS_SOLD` TINYINT(1) NOT NULL DEFAULT '0',
	`USER_ID` INT(10) NULL DEFAULT NULL,
	`NAME` CHAR(50) NOT NULL,
	`MIDDLE_CATEGORY` INT(11) NOT NULL,
	`LARGE_CATEGORY` INT(11) NOT NULL,
	`PRICE` INT(11) NOT NULL,
	`STATE` CHAR(50) NOT NULL,
	`QUANTITY` CHAR(50) NOT NULL,
	`MAKER` CHAR(50) NOT NULL,
	`REGION` INT(11) NOT NULL,
	PRIMARY KEY (`ID`),
	INDEX `FK_articles_users` (`USER_ID`),
	CONSTRAINT `FK_articles_users` FOREIGN KEY (`USER_ID`) REFERENCES `users` (`ID`) ON UPDATE CASCADE ON DELETE CASCADE
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;

CREATE TABLE `articles_descriptions` (
	`ARTICLE_ID` INT(10) NOT NULL,
	`CONTENT` VARCHAR(255) NULL DEFAULT NULL,
	`PREVIEW` CHAR(50) NULL DEFAULT NULL,
	INDEX `FK__articles_des` (`ARTICLE_ID`),
	CONSTRAINT `FK__articles_des` FOREIGN KEY (`ARTICLE_ID`) REFERENCES `articles` (`ID`) ON UPDATE CASCADE ON DELETE CASCADE
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;

CREATE TABLE `articles_images` (
	`ARTICLE_ID` INT(10) NOT NULL,
	`ORIGINAL` CHAR(100) NOT NULL,
	`ARTICLE_THUMBNAIL` CHAR(100) NOT NULL,
	`ARTICLE_LIST_THUMBNAIL` CHAR(100) NOT NULL,
	INDEX `FK__articles_image` (`ARTICLE_ID`),
	CONSTRAINT `FK__articles_image` FOREIGN KEY (`ARTICLE_ID`) REFERENCES `articles` (`ID`) ON UPDATE CASCADE ON DELETE CASCADE
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;

CREATE TABLE `articles_keywords` (
	`ARTICLE_ID` INT(10) NOT NULL,
	`KEYWORD` CHAR(50) NOT NULL,
	INDEX `FK__articles_keywords` (`ARTICLE_ID`),
	CONSTRAINT `FK__articles_keywords` FOREIGN KEY (`ARTICLE_ID`) REFERENCES `articles` (`ID`) ON UPDATE CASCADE ON DELETE CASCADE
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;

CREATE TABLE `messages` (
	`ID` INT(10) NOT NULL AUTO_INCREMENT,
	`TO_USER_ID` INT(10) NOT NULL,
	`FROM_USER_ID` INT(11) NOT NULL,
	`PREVIEW` CHAR(50) NOT NULL,
	`CONTENT` VARCHAR(255) NOT NULL,
	`SENDING_DATE` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	INDEX `FK_messages_users` (`TO_USER_ID`),
	PRIMARY KEY (`ID`),
	CONSTRAINT `FK_messages_users` FOREIGN KEY (`TO_USER_ID`) REFERENCES `users` (`ID`) ON UPDATE CASCADE ON DELETE CASCADE
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;

CREATE TABLE `users_authorities` (
	`USER_ID` INT(10) NOT NULL,
	`AUTHORITY` CHAR(50) NOT NULL,
	CONSTRAINT `FK__users` FOREIGN KEY (`USER_ID`) REFERENCES `users` (`ID`) ON UPDATE CASCADE ON DELETE CASCADE
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;
