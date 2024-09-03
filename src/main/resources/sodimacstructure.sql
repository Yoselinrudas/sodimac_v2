/*
SQLyog Trial v13.1.9 (64 bit)
MySQL - 8.0.34 : Database - sodimac
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`sodimac` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish2_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `sodimac`;

/*Table structure for table `area` */

DROP TABLE IF EXISTS `area`;

CREATE TABLE `area` (
  `id` TINYINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(20) COLLATE utf8mb4_spanish2_ci DEFAULT 'logistic',
  `createdat` DATETIME DEFAULT NULL,
  `createdby` VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `updatedat` DATETIME DEFAULT NULL,
  `updatedby` VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `registry_state` TINYINT DEFAULT NULL COMMENT '0=INACTIVE, 1=ACTIVE',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

/*Table structure for table `bill` */

DROP TABLE IF EXISTS `bill`;

CREATE TABLE `bill` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `legalclient_ruc` VARCHAR(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `proforma_id` BIGINT DEFAULT NULL,
  `subtotal` FLOAT DEFAULT NULL,
  `total` FLOAT DEFAULT NULL,
  `igv` FLOAT DEFAULT NULL,
  `createdat` DATETIME DEFAULT NULL,
  `createdby` VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `updatedat` DATETIME DEFAULT NULL,
  `updatedby` VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `registry_state` TINYINT DEFAULT NULL COMMENT '0=INACTIVE, 1=ACTIVE',
  PRIMARY KEY (`id`),
  KEY `FK_bill` (`registry_state`),
  KEY `FK_bill_proforma` (`proforma_id`),
  KEY `FK_legalClient` (`legalclient_ruc`),
  CONSTRAINT `FK_bill` FOREIGN KEY (`legalclient_ruc`) REFERENCES `legal_client` (`ruc`),
  CONSTRAINT `FK_bill_proforma` FOREIGN KEY (`proforma_id`) REFERENCES `proforma` (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

/*Table structure for table `buy` */

DROP TABLE IF EXISTS `buy`;

CREATE TABLE `buy` (
  `id` BIGINT NOT NULL,
  `buystatus_id` TINYINT DEFAULT NULL,
  `motivostatus` VARCHAR(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `buyemployee_id` BIGINT DEFAULT NULL,
  `createdat` DATETIME DEFAULT NULL,
  `createdby` VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `updatedat` DATETIME DEFAULT NULL,
  `updatedby` VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `registry_state` TINYINT DEFAULT NULL COMMENT '0=INACTIVE, 1=ACTIVE',
  PRIMARY KEY (`id`),
  KEY `status_id` (`registry_state`),
  KEY `FK_buy` (`buystatus_id`),
  KEY `FK_buyemployee` (`buyemployee_id`),
  CONSTRAINT `FK_buyemployee` FOREIGN KEY (`buyemployee_id`) REFERENCES `employee` (`id`),
  CONSTRAINT `FK_buyStatus` FOREIGN KEY (`buystatus_id`) REFERENCES `buystatus` (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

/*Table structure for table `buystatus` */

DROP TABLE IF EXISTS `buystatus`;

CREATE TABLE `buystatus` (
  `id` TINYINT NOT NULL AUTO_INCREMENT,
  `buystatus` VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `createdat` DATETIME DEFAULT NULL,
  `createdby` VARCHAR(20) COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `updatedat` DATETIME DEFAULT NULL,
  `updatedby` VARCHAR(20) COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `registry_state` TINYINT DEFAULT NULL COMMENT '0=INACTIVE, 1=ACTIVE',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

/*Table structure for table `category` */

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `id` TINYINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(30) COLLATE utf8mb4_spanish2_ci DEFAULT 'garden',
  `createdat` DATETIME DEFAULT NULL,
  `createdby` VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `updatedat` DATETIME DEFAULT NULL,
  `updatedby` VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `registry_state` TINYINT DEFAULT NULL COMMENT '0=INACTIVE, 1=ACTIVE',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

/*Table structure for table `detailbill` */

DROP TABLE IF EXISTS `detailbill`;

CREATE TABLE `detailbill` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `product_id` BIGINT DEFAULT NULL,
  `sale_price` FLOAT DEFAULT NULL,
  `quantity` FLOAT DEFAULT NULL,
  `total` FLOAT DEFAULT NULL,
  `bill_id` BIGINT DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_bill_detail` (`bill_id`),
  KEY `FK_product_detail` (`product_id`),
  CONSTRAINT `FK_bill_detail` FOREIGN KEY (`bill_id`) REFERENCES `bill` (`id`),
  CONSTRAINT `FK_product_detail` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

/*Table structure for table `detailticket` */

DROP TABLE IF EXISTS `detailticket`;

CREATE TABLE `detailticket` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `product_id` BIGINT DEFAULT NULL,
  `sale_price` FLOAT DEFAULT NULL,
  `quantity` FLOAT DEFAULT NULL,
  `total` FLOAT DEFAULT NULL,
  `ticket_id` BIGINT DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ticket` (`ticket_id`),
  KEY `FK_product_detailticket` (`product_id`),
  CONSTRAINT `FK_product_detailticket` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `FK_ticket` FOREIGN KEY (`ticket_id`) REFERENCES `ticket` (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

/*Table structure for table `employee` */

DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `lastname` VARCHAR(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `area_id` TINYINT DEFAULT NULL,
  `address` VARCHAR(50) COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `phone` VARCHAR(15) COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `email` VARCHAR(30) COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `createdat` DATETIME DEFAULT NULL,
  `createdby` VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `updatedat` DATETIME DEFAULT NULL,
  `updatedby` VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `registry_state` TINYINT DEFAULT NULL COMMENT '0=INACTIVE, 1=ACTIVE',
  PRIMARY KEY (`id`),
  KEY `FK_area` (`area_id`),
  KEY `status_id` (`registry_state`),
  CONSTRAINT `FK_area` FOREIGN KEY (`area_id`) REFERENCES `area` (`id`)
) ENGINE=INNODB AUTO_INCREMENT=12345678948 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

/*Table structure for table `employeexperfil` */

DROP TABLE IF EXISTS `employeexperfil`;

CREATE TABLE `employeexperfil` (
  `employee_id` BIGINT DEFAULT NULL,
  `perfil_id` TINYINT DEFAULT NULL,
  KEY `FK_PERFIL` (`perfil_id`),
  KEY `FK_EMPLOYEE` (`employee_id`),
  CONSTRAINT `FK_EMPLOYEE` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`),
  CONSTRAINT `FK_PERFIL` FOREIGN KEY (`perfil_id`) REFERENCES `perfil` (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

/*Table structure for table `income` */

DROP TABLE IF EXISTS `income`;

CREATE TABLE `income` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `employee_id` BIGINT DEFAULT NULL,
  `orderbuy_id` BIGINT DEFAULT NULL,
  `incomestatus_id` INT DEFAULT NULL,
  `createdat` DATETIME DEFAULT NULL,
  `createdby` VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `updatedat` DATETIME DEFAULT NULL,
  `updatedby` VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `registry_state` TINYINT DEFAULT NULL COMMENT '0=INACTIVE, 1=ACTIVE',
  PRIMARY KEY (`id`),
  KEY `status_id` (`registry_state`),
  KEY `FK_employee_income` (`employee_id`),
  KEY `FK_income_order` (`orderbuy_id`),
  KEY `Fk_incomeStatus` (`incomestatus_id`),
  CONSTRAINT `FK_employee_income` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`),
  CONSTRAINT `FK_income_order` FOREIGN KEY (`orderbuy_id`) REFERENCES `orderbuy` (`id`),
  CONSTRAINT `FK_statuslogistic_income` FOREIGN KEY (`incomestatus_id`) REFERENCES `statuslogistic` (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

/*Table structure for table `legal_client` */

DROP TABLE IF EXISTS `legal_client`;

CREATE TABLE `legal_client` (
  `ruc` VARCHAR(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish2_ci NOT NULL,
  `nombre/razonsocial` VARCHAR(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `supplier` TINYINT(1) DEFAULT NULL,
  `address` VARCHAR(50) COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `phone` VARCHAR(15) COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `email` VARCHAR(30) COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `createdat` DATETIME DEFAULT NULL,
  `createdby` VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `updatedat` DATETIME DEFAULT NULL,
  `updatedby` VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `registry_state` TINYINT DEFAULT NULL COMMENT '0=INACTIVE, 1=ACTIVE',
  PRIMARY KEY (`ruc`),
  KEY `fk_legal_client` (`registry_state`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

/*Table structure for table `natural_client` */

DROP TABLE IF EXISTS `natural_client`;

CREATE TABLE `natural_client` (
  `documentnumber` VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish2_ci NOT NULL,
  `typedocument_id` TINYINT DEFAULT NULL,
  `name` VARCHAR(50) COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `lastname` VARCHAR(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `address` VARCHAR(50) COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `phone` VARCHAR(15) COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `email` VARCHAR(30) COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `createdat` DATETIME DEFAULT NULL,
  `createdby` VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `updatedat` DATETIME DEFAULT NULL,
  `updatedby` VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `registry_state` TINYINT DEFAULT NULL COMMENT '0=INACTIVE, 1=ACTIVE',
  PRIMARY KEY (`documentnumber`),
  KEY `FK_natural_client` (`registry_state`),
  KEY `FK_document` (`typedocument_id`),
  CONSTRAINT `FK_typedocument` FOREIGN KEY (`typedocument_id`) REFERENCES `typedocument` (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

/*Table structure for table `orderbuy` */

DROP TABLE IF EXISTS `orderbuy`;

CREATE TABLE `orderbuy` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `buy_id` BIGINT DEFAULT NULL,
  `supplier_id` VARCHAR(11) COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `employee_id` BIGINT DEFAULT NULL,
  `statusorder_id` INT DEFAULT NULL,
  `createdat` DATETIME DEFAULT NULL,
  `createdby` VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `updatedat` DATETIME DEFAULT NULL,
  `updatedby` VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `registry_state` TINYINT DEFAULT NULL COMMENT '0=INACTIVE, 1=ACTIVE',
  PRIMARY KEY (`id`),
  KEY `status_id` (`registry_state`),
  KEY `employee_id` (`employee_id`),
  KEY `FK_buy_order` (`buy_id`),
  KEY `FK_orderBuy` (`statusorder_id`),
  KEY `FK_legalclient` (`supplier_id`),
  CONSTRAINT `FK_buy_order` FOREIGN KEY (`buy_id`) REFERENCES `buy` (`id`),
  CONSTRAINT `FK_employee_orderbuy` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`),
  CONSTRAINT `FK_legalclient` FOREIGN KEY (`supplier_id`) REFERENCES `legal_client` (`ruc`),
  CONSTRAINT `FK_statuslogistic` FOREIGN KEY (`statusorder_id`) REFERENCES `statuslogistic` (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

/*Table structure for table `perfil` */

DROP TABLE IF EXISTS `perfil`;

CREATE TABLE `perfil` (
  `id` TINYINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(20) COLLATE utf8mb4_spanish2_ci DEFAULT 'admin',
  `createdat` DATETIME DEFAULT NULL,
  `createdby` VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `updatedat` DATETIME DEFAULT NULL,
  `updatedby` VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `registry_state` TINYINT DEFAULT NULL COMMENT '0=INACTIVE, 1=ACTIVE',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `category_id` TINYINT DEFAULT NULL,
  `quantity` FLOAT DEFAULT NULL,
  `min_quantity` FLOAT DEFAULT NULL,
  `pricesupplier` DOUBLE DEFAULT NULL,
  `pricesale` DOUBLE DEFAULT NULL,
  `codeproduct` VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `createdat` DATETIME DEFAULT NULL,
  `createdby` VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `updatedat` DATETIME DEFAULT NULL,
  `updatedby` VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `registry_state` TINYINT DEFAULT NULL COMMENT '0=INACTIVE, 1=ACTIVE',
  PRIMARY KEY (`id`),
  KEY `FK_category` (`category_id`),
  KEY `FK_STATUS` (`registry_state`),
  CONSTRAINT `FK_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=INNODB AUTO_INCREMENT=15987456325 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

/*Table structure for table `producttobuy` */

DROP TABLE IF EXISTS `producttobuy`;

CREATE TABLE `producttobuy` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `product_id` BIGINT DEFAULT NULL,
  `nameproduct` VARCHAR(30) COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `quantitystock` FLOAT DEFAULT NULL,
  `quantityintransit` FLOAT DEFAULT NULL,
  `requiredquantity` FLOAT DEFAULT NULL,
  `expectedamount` FLOAT DEFAULT NULL,
  `buy_id` BIGINT DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_buy` (`buy_id`),
  KEY `FK_product_buy` (`product_id`),
  CONSTRAINT `FK_buy` FOREIGN KEY (`buy_id`) REFERENCES `buy` (`id`),
  CONSTRAINT `FK_product_buy` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

/*Table structure for table `proforma` */

DROP TABLE IF EXISTS `proforma`;

CREATE TABLE `proforma` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `employee_id` BIGINT DEFAULT NULL,
  `statussale_id` INT DEFAULT NULL,
  `total` DOUBLE DEFAULT NULL,
  `createdat` DATETIME DEFAULT NULL,
  `createdby` VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `updatedat` DATETIME DEFAULT NULL,
  `updatedby` VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `registry_state` TINYINT DEFAULT NULL COMMENT '0=INACTIVE, 1=ACTIVE',
  PRIMARY KEY (`id`),
  KEY `FK_employee_proforma` (`employee_id`),
  KEY `FK_proforma` (`registry_state`),
  KEY `FK_statussale` (`statussale_id`),
  CONSTRAINT `FK_employee_proforma` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`),
  CONSTRAINT `FK_statussale` FOREIGN KEY (`statussale_id`) REFERENCES `statussale` (`id`)
) ENGINE=INNODB AUTO_INCREMENT=125456260 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

/*Table structure for table `sale_detail` */

DROP TABLE IF EXISTS `sale_detail`;

CREATE TABLE `sale_detail` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `product_id` BIGINT DEFAULT NULL,
  `price_sale` DOUBLE DEFAULT NULL,
  `quantity` FLOAT DEFAULT NULL,
  `total` DOUBLE DEFAULT NULL,
  `statusdetail_id` INT DEFAULT NULL,
  `proforma_id` BIGINT DEFAULT NULL,
  `createdat` DATETIME DEFAULT NULL,
  `createdby` VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `updatedat` DATETIME DEFAULT NULL,
  `updatedby` VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `registry_state` TINYINT DEFAULT NULL COMMENT '0=INACTIVE, 1=ACTIVE',
  PRIMARY KEY (`id`),
  KEY `FK_product` (`product_id`),
  KEY `FK_proforma` (`proforma_id`),
  KEY `FK_statusDetail` (`statusdetail_id`),
  CONSTRAINT `FK_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `FK_proforma` FOREIGN KEY (`proforma_id`) REFERENCES `proforma` (`id`),
  CONSTRAINT `FK_statussale_detail` FOREIGN KEY (`statusdetail_id`) REFERENCES `statussale` (`id`)
) ENGINE=INNODB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

/*Table structure for table `statuslogistic` */

DROP TABLE IF EXISTS `statuslogistic`;

CREATE TABLE `statuslogistic` (
  `id` INT NOT NULL,
  `description` VARCHAR(20) COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `statuslogistic` VARCHAR(20) COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `createdat` DATETIME DEFAULT NULL,
  `createdby` VARCHAR(20) COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `updatedat` DATETIME DEFAULT NULL,
  `updatedby` VARCHAR(20) COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `registry_state` TINYINT DEFAULT NULL COMMENT '0=INACTIVE, 1=ACTIVE',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

/*Table structure for table `statussale` */

DROP TABLE IF EXISTS `statussale`;

CREATE TABLE `statussale` (
  `id` INT NOT NULL,
  `description` VARCHAR(20) COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `statusgroup` VARCHAR(20) COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `createdat` DATETIME DEFAULT NULL,
  `createdby` VARCHAR(20) COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `updatedat` DATETIME DEFAULT NULL,
  `updatedby` VARCHAR(20) COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `registry_state` TINYINT DEFAULT NULL COMMENT '0=INACTIVE, 1=ACTIVE',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

/*Table structure for table `ticket` */

DROP TABLE IF EXISTS `ticket`;

CREATE TABLE `ticket` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `naturalclient_document` VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `proforma_id` BIGINT DEFAULT NULL,
  `createdat` DATETIME DEFAULT NULL,
  `createdby` VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `updatedat` DATETIME DEFAULT NULL,
  `updatedby` VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `registry_state` TINYINT DEFAULT NULL COMMENT '0=INACTIVE, 1=ACTIVE',
  PRIMARY KEY (`id`),
  KEY `FK_ticket` (`registry_state`),
  KEY `FK_ticket_proforma` (`proforma_id`),
  KEY `FK_narutalClient` (`naturalclient_document`),
  CONSTRAINT `FK_narutalClient` FOREIGN KEY (`naturalclient_document`) REFERENCES `natural_client` (`documentnumber`),
  CONSTRAINT `FK_ticket_proforma` FOREIGN KEY (`proforma_id`) REFERENCES `proforma` (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

/*Table structure for table `typedocument` */

DROP TABLE IF EXISTS `typedocument`;

CREATE TABLE `typedocument` (
  `id` TINYINT NOT NULL AUTO_INCREMENT,
  `document` VARCHAR(50) COLLATE utf8mb4_spanish2_ci DEFAULT 'dni',
  `createdat` DATETIME DEFAULT NULL,
  `createdby` VARCHAR(20) COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `updatedat` DATETIME DEFAULT NULL,
  `updatedby` VARCHAR(20) COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `registry_state` TINYINT DEFAULT NULL COMMENT '0=INACTIVE, 1=ACTIVE',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

ALTER TABLE `product` AUTO_INCREMENT = 1;
ALTER TABLE `employee` AUTO_INCREMENT = 1;
ALTER TABLE `proforma` AUTO_INCREMENT = 1;
ALTER TABLE `bill` AUTO_INCREMENT = 1;
ALTER TABLE `ticket` AUTO_INCREMENT = 1;

