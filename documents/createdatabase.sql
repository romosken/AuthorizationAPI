-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema cinecriticas
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema cinecriticas
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `cinecriticas` ;
USE `cinecriticas` ;

-- -----------------------------------------------------
-- Table `cinecriticas`.`Role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinecriticas`.`Role` (
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`name`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cinecriticas`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinecriticas`.`User` (
  `username` VARCHAR(20) NOT NULL,
  `password` VARCHAR(64) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `xp` INT NOT NULL,
  `role_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`username`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  INDEX `fk_Users_Roles_idx` (`role_name` ASC) VISIBLE,
  CONSTRAINT `fk_User_Role`
    FOREIGN KEY (`role_name`)
    REFERENCES `cinecriticas`.`Role` (`name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cinecriticas`.`Rating`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinecriticas`.`Rating` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `movie_id` VARCHAR(45) NOT NULL,
  `username` VARCHAR(20) NOT NULL,
  `rating` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Rating_User_idx` (`username` ASC) VISIBLE,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  CONSTRAINT `fk_Rating_User`
    FOREIGN KEY (`username`)
    REFERENCES `cinecriticas`.`User` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cinecriticas`.`Comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinecriticas`.`Comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `movie_id` VARCHAR(45) NOT NULL,
  `username` VARCHAR(20) NOT NULL,
  `text` MEDIUMTEXT NOT NULL,
  `likes` INT NULL,
  `dislikes` INT NULL,
  `comment_reference` INT NULL,
  `comment_reply` INT NULL,
  `repeated` TINYINT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_Comment_User_idx` (`username` ASC) VISIBLE,
  INDEX `fk_Comment_Reference_idx` (`comment_reference` ASC) VISIBLE,
  INDEX `fk_Comment_Reply_idx` (`comment_reply` ASC) VISIBLE,
  CONSTRAINT `fk_Comment_User`
    FOREIGN KEY (`username`)
    REFERENCES `cinecriticas`.`User` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Comment_Reference`
    FOREIGN KEY (`comment_reference`)
    REFERENCES `cinecriticas`.`Comment` (`id`)
    ON DELETE SET NULL
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Comment_Reply`
    FOREIGN KEY (`comment_reply`)
    REFERENCES `cinecriticas`.`Comment` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

INSERT INTO `cinecriticas`.`role`
(`name`)
VALUES
("LEITOR"),
("BASICO"),
("AVANCADO"),
("MODERADOR")
;
