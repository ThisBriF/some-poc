CREATE SCHEMA IF NOT EXISTS 'poc';

CREATE TABLE IF NOT EXISTS `poc`.`cart` (
  `cart_id` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`cart_id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `poc`.`product` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `description` VARCHAR(100) NULL,
  `price` DECIMAL(10,2) NULL,
  `brand` VARCHAR(25) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `poc`.`cart_has_product` (
  `cart_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  PRIMARY KEY (`cart_id`, `product_id`),
  INDEX `fk_cart_has_product_product1_idx` (`product_id` ASC) VISIBLE,
  INDEX `fk_cart_has_product_cart_idx` (`cart_id` ASC) VISIBLE,
  CONSTRAINT `fk_cart_has_product_cart`
    FOREIGN KEY (`cart_id`)
    REFERENCES `poc`.`cart` (`cart_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cart_has_product_product`
    FOREIGN KEY (`product_id`)
    REFERENCES `poc`.`product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;