-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema azbow_lead_crm
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema azbow_lead_crm
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `azbow_lead_crm` DEFAULT CHARACTER SET utf8mb3 ;
USE `azbow_lead_crm` ;

-- -----------------------------------------------------
-- Table `azbow_lead_crm`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `azbow_lead_crm`.`users` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `firstname` VARCHAR(255) NULL DEFAULT NULL,
  `lastname` VARCHAR(255) NULL DEFAULT NULL,
  `email` VARCHAR(255) NULL DEFAULT NULL,
  `password` VARCHAR(255) NULL DEFAULT NULL,
  `created_at` TIMESTAMP(6) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `azbow_lead_crm`.`customers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `azbow_lead_crm`.`customers` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `users_id` BIGINT UNSIGNED NULL DEFAULT NULL,
  `Preferred_name` VARCHAR(300) NOT NULL,
  `preferred_contact_number` VARCHAR(20) NOT NULL,
  `budget` DECIMAL(30,2) NOT NULL,
  `created_at` TIMESTAMP(6) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `preferred_contact_number_UNIQUE` (`preferred_contact_number` ASC) VISIBLE,
  UNIQUE INDEX `users_id_UNIQUE` (`users_id` ASC) VISIBLE,
  CONSTRAINT `fk_customer_user_id`
    FOREIGN KEY (`users_id`)
    REFERENCES `azbow_lead_crm`.`users` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `azbow_lead_crm`.`departments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `azbow_lead_crm`.`departments` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(250) NOT NULL,
  `description` VARCHAR(300) NULL DEFAULT NULL,
  `created_at` TIMESTAMP(6) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `azbow_lead_crm`.`employees`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `azbow_lead_crm`.`employees` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `users_id` BIGINT UNSIGNED NOT NULL,
  `departments_id` BIGINT UNSIGNED NOT NULL,
  `created_at` TIMESTAMP(6) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `users_id_UNIQUE` (`users_id` ASC) VISIBLE,
  UNIQUE INDEX `departments_id_UNIQUE` (`departments_id` ASC) VISIBLE,
  CONSTRAINT `fk_departments_id`
    FOREIGN KEY (`departments_id`)
    REFERENCES `azbow_lead_crm`.`departments` (`id`),
  CONSTRAINT `fk_employees_users_id`
    FOREIGN KEY (`users_id`)
    REFERENCES `azbow_lead_crm`.`users` (`id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `azbow_lead_crm`.`financial_statuses`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `azbow_lead_crm`.`financial_statuses` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `code` VARCHAR(3) NOT NULL,
  `description` VARCHAR(300) NULL DEFAULT NULL,
  `created_at` TIMESTAMP(6) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `code_UNIQUE` (`code` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `azbow_lead_crm`.`lead_statuses`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `azbow_lead_crm`.`lead_statuses` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `code` VARCHAR(3) NOT NULL,
  `description` VARCHAR(300) NULL DEFAULT NULL,
  `created_at` TIMESTAMP(6) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `code_UNIQUE` (`code` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `azbow_lead_crm`.`lead_types`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `azbow_lead_crm`.`lead_types` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `code` VARCHAR(3) NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `description` VARCHAR(300) NULL DEFAULT NULL,
  `created_at` TIMESTAMP(6) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `code_UNIQUE` (`code` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `azbow_lead_crm`.`leads`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `azbow_lead_crm`.`leads` (
  `id` BIGINT UNSIGNED NOT NULL,
  `created_at` DATETIME(6) NOT NULL,
  `description` VARCHAR(255) NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `source` VARCHAR(255) NOT NULL,
  `customer_initiator` BIGINT UNSIGNED NULL DEFAULT NULL,
  `lead_status` BIGINT UNSIGNED NOT NULL,
  `lead_type` BIGINT NOT NULL,
  `sales_agent` BIGINT UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_leads_lead_type_is` (`lead_type` ASC) VISIBLE,
  INDEX `fk_leads_customer_id` (`customer_initiator` ASC) VISIBLE,
  INDEX `fk_leads_status_id` (`lead_status` ASC) VISIBLE,
  INDEX `fk_leads_sales_agent_id` (`sales_agent` ASC) VISIBLE,
  CONSTRAINT `fk_leads_sales_agent_id`
    FOREIGN KEY (`sales_agent`)
    REFERENCES `azbow_lead_crm`.`employees` (`id`),
  CONSTRAINT `fk_leads_status_id`
    FOREIGN KEY (`lead_status`)
    REFERENCES `azbow_lead_crm`.`lead_statuses` (`id`),
  CONSTRAINT `fk_leads_customer_id`
    FOREIGN KEY (`customer_initiator`)
    REFERENCES `azbow_lead_crm`.`customers` (`id`),
  CONSTRAINT `fk_leads_lead_type_is`
    FOREIGN KEY (`lead_type`)
    REFERENCES `azbow_lead_crm`.`lead_types` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `azbow_lead_crm`.`properties`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `azbow_lead_crm`.`properties` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `location` VARCHAR(250) NOT NULL,
  `price` DECIMAL(30,2) NOT NULL,
  `created_at` TIMESTAMP(6) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `azbow_lead_crm`.`reservations`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `azbow_lead_crm`.`reservations` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `lead_id` BIGINT UNSIGNED NOT NULL,
  `sales_agent` BIGINT UNSIGNED NOT NULL,
  `property_id` BIGINT UNSIGNED NOT NULL,
  `created_at` TIMESTAMP(6) NOT NULL,
  `reservation_fee` DECIMAL(30,2) NOT NULL,
  `expiried_at` TIMESTAMP(6) NOT NULL,
  `financial_status` BIGINT UNSIGNED NOT NULL,
  `loan_amount` DECIMAL(30,2) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_reservations_property_id` (`property_id` ASC) VISIBLE,
  INDEX `fk_reservation_financial_status_id_idx` (`financial_status` ASC) VISIBLE,
  INDEX `fk_reservations_sales_agent_id_idx` (`sales_agent` ASC) VISIBLE,
  CONSTRAINT `fk_reservations_leads_it`
    FOREIGN KEY (`lead_id`)
    REFERENCES `azbow_lead_crm`.`leads` (`id`),
  CONSTRAINT `fk_reservations_financial_status_id`
    FOREIGN KEY (`financial_status`)
    REFERENCES `azbow_lead_crm`.`financial_statuses` (`id`),
  CONSTRAINT `fk_reservations_property_id`
    FOREIGN KEY (`property_id`)
    REFERENCES `azbow_lead_crm`.`properties` (`id`),
  CONSTRAINT `fk_reservations_sales_agent_id`
    FOREIGN KEY (`sales_agent`)
    REFERENCES `azbow_lead_crm`.`employees` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `azbow_lead_crm`.`legal_proceedings`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `azbow_lead_crm`.`legal_proceedings` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `reservation_id` BIGINT UNSIGNED NOT NULL,
  `is_contract_signed` BIT(1) NOT NULL,
  `legal_notes` VARCHAR(400) NULL DEFAULT NULL,
  `created_at` TIMESTAMP(6) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_legal_proceeding_reservation_id_idx` (`reservation_id` ASC) VISIBLE,
  CONSTRAINT `fk_legal_proceeding_reservation_id`
    FOREIGN KEY (`reservation_id`)
    REFERENCES `azbow_lead_crm`.`reservations` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `azbow_lead_crm`.`roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `azbow_lead_crm`.`roles` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `description` VARCHAR(300) NULL DEFAULT NULL,
  `created_at` TIMESTAMP(6) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `azbow_lead_crm`.`sales`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `azbow_lead_crm`.`sales` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `reservation_id` BIGINT UNSIGNED NOT NULL,
  `sold_at` TIMESTAMP(6) NULL DEFAULT NULL,
  `created_at` TIMESTAMP(6) NULL DEFAULT NULL,
  `commission_details` VARCHAR(150) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_sales_reservation_id_idx` (`reservation_id` ASC) VISIBLE,
  CONSTRAINT `fk_sales_reservation_id`
    FOREIGN KEY (`reservation_id`)
    REFERENCES `azbow_lead_crm`.`reservations` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `azbow_lead_crm`.`usersroles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `azbow_lead_crm`.`usersroles` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `users_id` BIGINT UNSIGNED NOT NULL,
  `roles_id` BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_usersroles_user_id_idx` (`users_id` ASC) VISIBLE,
  INDEX `fk_usersroles_roles_id_idx` (`roles_id` ASC) VISIBLE,
  CONSTRAINT `fk_usersroles_roles_id`
    FOREIGN KEY (`roles_id`)
    REFERENCES `azbow_lead_crm`.`roles` (`id`),
  CONSTRAINT `fk_usersroles_users_id`
    FOREIGN KEY (`users_id`)
    REFERENCES `azbow_lead_crm`.`users` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

INSERT into lead_statuses (name,code,description,created_at) values ('Unassigned','UAS','Sales Agent not assisnged','2025-02-28 14:24:18.846077');
INSERT into lead_types (name,code,description,created_at) values ('Inquiry','INQ','Customer\'s Inquiry','2025-02-28 14:24:18.846077');
