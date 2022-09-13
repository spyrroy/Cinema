-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE =
        'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema cinema
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `cinema`;

-- -----------------------------------------------------
-- Schema cinema
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `cinema` DEFAULT CHARACTER SET utf8mb3;
USE `cinema`;

-- -----------------------------------------------------
-- Table `cinema`.`film`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cinema`.`film`;

CREATE TABLE IF NOT EXISTS `cinema`.`film`
(
    `film_id`     INT          NOT NULL AUTO_INCREMENT,
    `name`        VARCHAR(45)  NOT NULL,
    `description` VARCHAR(255) NULL DEFAULT NULL,
    `duration`    INT          NOT NULL,
    PRIMARY KEY (`film_id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `cinema`.`role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cinema`.`role`;

CREATE TABLE IF NOT EXISTS `cinema`.`role`
(
    `role_id` INT          NOT NULL AUTO_INCREMENT,
    `role`    VARCHAR(255) NOT NULL UNIQUE,
    PRIMARY KEY (`role_id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `cinema`.`seat`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cinema`.`seat`;

CREATE TABLE IF NOT EXISTS `cinema`.`seat`
(
    `seat_id` INT NOT NULL AUTO_INCREMENT,
    `number`  INT NOT NULL,
    PRIMARY KEY (`seat_id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `cinema`.`session`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cinema`.`session`;

CREATE TABLE IF NOT EXISTS `cinema`.`session`
(
    `session_id` INT  NOT NULL AUTO_INCREMENT,
    `date`       DATE NOT NULL,
    `time`       TIME NOT NULL,
    `film_id`    INT  NOT NULL,
    PRIMARY KEY (`session_id`, `film_id`),
    UNIQUE KEY (`date`, `time`),
    INDEX `fk_session_film1_idx` (`film_id` ASC) VISIBLE,
    CONSTRAINT `fk_session_film1`
        FOREIGN KEY (`film_id`)
            REFERENCES `cinema`.`film` (`film_id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `cinema`.`session_seats`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cinema`.`session_seats`;

CREATE TABLE IF NOT EXISTS `cinema`.`session_seats`
(
    `session_seat_id` INT        NOT NULL AUTO_INCREMENT,
    `session_id`      INT        NOT NULL,
    `seat_id`         INT        NOT NULL,
    `free`            TINYINT(1) NULL DEFAULT '1',
    PRIMARY KEY (`session_seat_id`, `session_id`, `seat_id`),
    INDEX `fk_free_seats_session1_idx` (`session_id` ASC) VISIBLE,
    INDEX `fk_free_seats_seat1_idx` (`seat_id` ASC) VISIBLE,
    CONSTRAINT `fk_free_seats_seat1`
        FOREIGN KEY (`seat_id`)
            REFERENCES `cinema`.`seat` (`seat_id`),
    CONSTRAINT `fk_free_seats_session1`
        FOREIGN KEY (`session_id`)
            REFERENCES `cinema`.`session` (`session_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `cinema`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cinema`.`user`;

CREATE TABLE IF NOT EXISTS `cinema`.`user`
(
    `user_id`     INT         NOT NULL AUTO_INCREMENT,
    `login`       VARCHAR(45) NOT NULL,
    `first_name`  VARCHAR(45) NOT NULL,
    `second_name` VARCHAR(45) NOT NULL,
    `password`    VARCHAR(45) NOT NULL,
    `email`       VARCHAR(45) NULL,
    `salt`        VARCHAR(100) NOT NULL,
    `role_id`     INT         NOT NULL,
    PRIMARY KEY (`user_id`, `role_id`),
    INDEX `fk_users_roles_idx` (`role_id` ASC) VISIBLE,
    UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE,
    CONSTRAINT `fk_users_roles`
        FOREIGN KEY (`role_id`)
            REFERENCES `cinema`.`role` (`role_id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `cinema`.`ticket`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cinema`.`ticket`;

CREATE TABLE IF NOT EXISTS `cinema`.`ticket`
(
    `ticket_id`  INT       NOT NULL AUTO_INCREMENT,
    `user_id`    INT       NOT NULL,
    `seat_id`    INT       NOT NULL,
    `session_id` INT       NOT NULL,
    `date`       TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`ticket_id`, `user_id`, `seat_id`, `session_id`),
    INDEX `fk_ticket_user1_idx` (`user_id` ASC) VISIBLE,
    INDEX `fk_ticket_seat1_idx` (`seat_id` ASC) VISIBLE,
    INDEX `fk_ticket_session1_idx` (`session_id` ASC) VISIBLE,
    CONSTRAINT `fk_ticket_seat1`
        FOREIGN KEY (`seat_id`)
            REFERENCES `cinema`.`seat` (`seat_id`),
    CONSTRAINT `fk_ticket_session1`
        FOREIGN KEY (`session_id`)
            REFERENCES `cinema`.`session` (`session_id`)
            ON DELETE RESTRICT,
    CONSTRAINT `fk_ticket_user1`
        FOREIGN KEY (`user_id`)
            REFERENCES `cinema`.`user` (`user_id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;

USE `cinema`;

DELIMITER $$

USE `cinema`$$
DROP TRIGGER IF EXISTS `cinema`.`session_AFTER_INSERT` $$
USE `cinema`$$
CREATE
    DEFINER =`root`@`%`
    TRIGGER `cinema`.`session_AFTER_INSERT`
    AFTER INSERT
    ON `cinema`.`session`
    FOR EACH ROW
BEGIN
    DECLARE i INT DEFAULT 1;
    SET @sid := (select MAX(session_id) from session);
    WHILE (i <= (select MAX(seat_id) from seat))
        DO
            INSERT INTO session_seats(session_id, seat_id)
            VALUES ((select MAX(session_id) from session), i);
            SET i = i + 1;
        END WHILE;
    SET @sid = null;
END$$


USE `cinema`$$
DROP TRIGGER IF EXISTS `cinema`.`ticket_AFTER_INSERT` $$
USE `cinema`$$
CREATE
    DEFINER =`root`@`%`
    TRIGGER `cinema`.`ticket_AFTER_INSERT`
    AFTER INSERT
    ON `cinema`.`ticket`
    FOR EACH ROW
BEGIN
    UPDATE session_seats
    SET free = 0
    WHERE session_id = (SELECT session_id from ticket WHERE ticket_id = (select MAX(ticket_id) from ticket))
      AND seat_id = (SELECT seat_id from ticket WHERE ticket_id = (select MAX(ticket_id) from ticket));
END$$


DELIMITER ;

SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;
