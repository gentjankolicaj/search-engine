-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema search_db
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `search_db` ;

-- -----------------------------------------------------
-- Schema search_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `search_db` ;
USE `search_db` ;

-- -----------------------------------------------------
-- Table `search_db`.`document`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `search_db`.`document` (
  `id` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `search_db`.`token`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `search_db`.`token` (
  `value` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`value`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `search_db`.`document_token`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `search_db`.`document_token` (
  `token_value` VARCHAR(255) NOT NULL,
  `document_id` INT NOT NULL,
  `token_index` INT NOT NULL,
  PRIMARY KEY (`token_index`, `token_value`, `document_id`),
  INDEX `fk_document_token_document1_idx` (`document_id` ASC) VISIBLE,
  CONSTRAINT `fk_document_token_token`
    FOREIGN KEY (`token_value`)
    REFERENCES `search_db`.`token` (`value`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_document_token_document1`
    FOREIGN KEY (`document_id`)
    REFERENCES `search_db`.`document` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
