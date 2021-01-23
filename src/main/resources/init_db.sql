CREATE DATABASE `taxi_service` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;

CREATE TABLE `taxi_service`.`manufacturer` (
                                               `manufacturer_id` BIGINT(11) NOT NULL AUTO_INCREMENT,
                                               `manufacturer_name` VARCHAR(225) NOT NULL,
                                               `manufacturer_country` VARCHAR(225) NOT NULL,
                                               `deleted` VARCHAR(5) NOT NULL,
                                               PRIMARY KEY (`manufacturer_id`))
    ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
    CHANGE COLUMN `deleted` `deleted` VARCHAR(5) NULL DEFAULT 'FALSE' ,
    CHANGE COLUMN `deleted` `is_deleted` VARCHAR(5) NULL DEFAULT 'FALSE' ,
    CHANGE COLUMN `manufacturer_name` `name` VARCHAR(225) NOT NULL ,
    CHANGE COLUMN `manufacturer_country` `country` VARCHAR(225) NOT NULL ,
    CHANGE COLUMN `is_deleted` `deleted` VARCHAR(5) NULL DEFAULT 'FALSE' ;
    CHANGE COLUMN `deleted` `deleted` TINYINT(1) NOT NULL DEFAULT 0 ;
CREATE TABLE `taxi_service`.`cars` (
                                       `cars_id` BIGINT(11) NOT NULL AUTO_INCREMENT,
                                       `manufacturers_id` BIGINT(11) NOT NULL,
                                       `model` VARCHAR(225) NOT NULL,
                                       `deleted` VARCHAR(5) NOT NULL DEFAULT 'FALSE',
                                       PRIMARY KEY (`cars_id`),
                                       INDEX `cars_manufacturer_id_idx` (`manufacturer_id` ASC) VISIBLE,
                                       CONSTRAINT `cars_manufacturer_id`
                                           FOREIGN KEY (`manufacturers_id`)
                                               REFERENCES `taxi_service`.`manufacturer` (`manufacturer_id`)
                                               ON DELETE NO ACTION
                                               ON UPDATE NO ACTION)
CHANGE COLUMN `model` `cars_model` VARCHAR(225) NOT NULL ,
CHANGE COLUMN `deleted` `cars_deleted` VARCHAR(5) NOT NULL DEFAULT 'FALSE' ;
CHANGE COLUMN `cars_deleted` `cars_deleted` TINYINT(1) NOT NULL DEFAULT 0 ;
CREATE TABLE `taxi_service`.`drivers` (
                                          `drivers_id` BIGINT(11) NOT NULL AUTO_INCREMENT,
                                          `driver_name` VARCHAR(225) NOT NULL,
                                          `driver_lisence_number` VARCHAR(225) NOT NULL,
                                          `driver_deleted` VARCHAR(5) NOT NULL DEFAULT 'FALSE',
                                          PRIMARY KEY (`drivers_id`));
                                          ADD COLUMN `driver_password` VARCHAR(225) NOT NULL AFTER `driver_lisence_number`,
                                          ADD COLUMN `driver_login` VARCHAR(225) NOT NULL AFTER `driver_password`;
CHANGE COLUMN `driver_deleted` `driver_deleted` TINYINT(1) NOT NULL DEFAULT 0 ;
CREATE TABLE `taxi_service`.`car_drivers` (
                                              `driver_id` BIGINT(11) NOT NULL,
                                              `car_id` BIGINT(11) NOT NULL,
                                              INDEX `driver_id_FK_idx` (`driver_id` ASC) VISIBLE,
                                              INDEX `car_id_FK_idx` (`car_id` ASC) VISIBLE,
                                              CONSTRAINT `driver_id_FK`
                                                  FOREIGN KEY (`driver_id`)
                                                      REFERENCES `taxi_service`.`drivers` (`drivers_id`)
                                                      ON DELETE NO ACTION
                                                      ON UPDATE NO ACTION,
                                              CONSTRAINT `car_id_FK`
                                                  FOREIGN KEY (`car_id`)
                                                      REFERENCES `taxi_service`.`cars` (`cars_id`)
                                                      ON DELETE NO ACTION
                                                      ON UPDATE NO ACTION);
