CREATE DATABASE `taxi_service` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;

CREATE TABLE `taxi_service`.`manufacturer` (
                                               `manufacturer_id` BIGINT(11) NOT NULL AUTO_INCREMENT,
                                               `manufacturer_name` VARCHAR(225) NOT NULL,
                                               `manufacturer_country` VARCHAR(225) NOT NULL,
                                               `deleted` VARCHAR(5) NOT NULL,
                                               PRIMARY KEY (`manufacturer_id`))
    ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;