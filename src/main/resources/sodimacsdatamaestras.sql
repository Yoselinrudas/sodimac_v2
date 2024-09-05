`category`/*
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

/*Data for the table `area` */

INSERT  INTO `area`(`id`,`name`,`createdat`,`createdby`,`updatedat`,`updatedby`,`registry_state`) VALUES 
(1,'LOGISTIC',NOW(),'BRIXTON',NULL,NULL,1),
(2,'SHELLING',NOW(),'BRIXTON',NULL,NULL,1),
(3,'SHOPPING',NOW(),'BRIXTON',NULL,NULL,1);`product`

/*Data for the table `buystatus` */

INSERT  INTO `buystatus`(`id`,`buystatus`,`createdat`,`createdby`,`updatedat`,`updatedby`,`registry_state`) VALUES 
(1,'TEMPORARY',NOW(),'BRIXTON',NULL,NULL,1),
(2,'CONFIRMED',NOW(),'BRIXTON',NULL,NULL,1),
(3,'APPROVED',NOW(),'BRIXTON',NULL,NULL,1),
(4,'REJECTED',NOW(),'BRIXTON',NULL,NULL,1);

/*Data for the table `category` */

INSERT  INTO `category`(`id`,`name`,`createdat`,`createdby`,`updatedat`,`updatedby`,`registry_state`) VALUES 
(1,'CONSTRUCTION',NOW(),'BRIXTON',NULL,NULL,1),
(2,'GARDEN',NOW(),'BRIXTON',NULL,NULL,1),
(3,'HARDWARE',NOW(),'BRIXTON',NULL,NULL,1),
(4,'BATHROOM_AND_KITCHEN',NOW(),'BRIXTON',NULL,NULL,1),
(5,'VEHICLE',NOW(),'BRIXTON',NULL,NULL,1),
(6,'DECORATION',NOW(),'BRIXTON',NULL,NULL,1);

/*Data for the table `employeexperfil` */

/*Data for the table `perfil` */

INSERT  INTO `perfil`(`id`,`name`,`createdat`,`createdby`,`updatedat`,`updatedby`,`registry_state`) VALUES 
(1,'ADMIN',NOW(),'BRIXTON',NULL,NULL,1),
(2,'SUPERVISOR',NOW(),'BRIXTON',NULL,NULL,1),
(3,'OPERATIONAL',NOW(),'BRIXTON',NULL,NULL,1);

/*Data for the table `statuslogistic` */

INSERT  INTO `statuslogistic`(`id`,`description`,`statuslogistic`,`createdat`,`createdby`,`updatedat`,`updatedby`,`registry_state`) VALUES 
(10,'REQUESTED','ORDER',NOW(),'BRIXTON',NULL,NULL,1),
(11,'CANCELED','ORDER',NOW(),'BRIXTON',NULL,NULL,1),
(12,'RECEIVED','ORDER',NOW(),'BRIXTON',NULL,NULL,1),
(20,'RECEIVED','INCOME',NOW(),'BRIXTON',NULL,NULL,1),
(21,'CANCELED','INCOME',NOW(),'BRIXTON',NULL,NULL,1);

/*Data for the table `statussale` */

INSERT  INTO `statussale`(`id`,`description`,`statussale`,`createdat`,`createdby`,`updatedat`,`updatedby`,`registry_state`) VALUES 
(10,'AVAILABLE','DETAIL',NOW(),'BRIXTON',NULL,NULL,1),
(11,'OUT_OF_STOCK','DETAIL',NOW(),'BRIXTON',NULL,NULL,1),
(20,'CONFIRMED','PROFORMA',NOW(),'BRIXTON',NULL,NULL,1),
(21,'TEMPORAL','PROFORMA',NOW(),'BRIXTON',NULL,NULL,1);

/*Data for the table `typedocument` */

INSERT  INTO `typedocument`(`id`,`document`,`createdat`,`createdby`,`updatedat`,`updatedby`,`registry_state`) VALUES 
(0,'CARNET_EXTRANJERIA',NOW(),'BRIXTON',NULL,NULL,1),
(1,'DNI',NOW(),'BRIXTON',NULL,NULL,1),
(2,'PASSAPORTE',NOW(),'BRIXTON',NULL,NULL,1);

/*Data for the table `typemovement` */

INSERT INTO `typemovement` (`id`, `subType`, `statusMovement`, `createdAt`, `createdBy`, `registry_state`) VALUES 
(10, 'SALE', 'OUTPUT', NOW(), 'BRIXTON', 1);
(11, 'SALE', 'OUTPUT', NOW(), 'BRIXTON', 1); 
(12, 'MOVEMENT_BRANCH', 'OUTPUT', NOW(), 'BRIXTON', 1); 
(20, 'CANCELED', 'INPUT', NOW(), 'BRIXTON', 1); 
(21, 'BUY', 'INPUT', NOW(), 'BRIXTON', '1'); 
(22, 'MOVEMENT_BRANCH', 'INPUT', NOW(), 'BRIXTON', 1); 

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
