-- MySQL Script generated by MySQL Workbench
-- Mon 25 Sep 2017 09:45:53 PM CDT
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema GymSystem
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema GymSystem
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `GymSystem` ;
USE `GymSystem` ;

-- -----------------------------------------------------
-- Table `GymSystem`.`User`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GymSystem`.`User` ;

CREATE TABLE IF NOT EXISTS `GymSystem`.`User` (
  `idUser` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `userName` VARCHAR(256) NOT NULL,
  `password` VARCHAR(256) NOT NULL,
  `level` VARCHAR(256) NOT NULL,
  PRIMARY KEY (`idUser`),
  UNIQUE INDEX `idUser_UNIQUE` (`idUser` ASC),
  UNIQUE INDEX `userName_UNIQUE` (`userName` ASC))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;