CREATE DATABASE  IF NOT EXISTS `storage` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `storage`;
-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: storage
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `durable_product`
--

DROP TABLE IF EXISTS `durable_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `durable_product` (
  `article_number` varchar(10) NOT NULL,
  `name` varchar(150) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `brand` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `family` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `netto_price` int NOT NULL,
  `tax_id` int NOT NULL,
  `quantity` int NOT NULL,
  `amount_units` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `critical_quantity` int NOT NULL,
  `warranty_period` int NOT NULL,
  `gross_weight` decimal(4,1) NOT NULL,
  PRIMARY KEY (`article_number`),
  KEY `fk_durable_product_tax_id` (`tax_id`),
  CONSTRAINT `fk_durable_product_tax_id` FOREIGN KEY (`tax_id`) REFERENCES `state_sales_tax` (`tax_key`),
  CONSTRAINT `durable_product_chk_1` CHECK ((`netto_price` >= 0)),
  CONSTRAINT `durable_product_chk_2` CHECK ((`quantity` >= 0)),
  CONSTRAINT `durable_product_chk_3` CHECK ((`critical_quantity` >= 0)),
  CONSTRAINT `durable_product_chk_4` CHECK ((`gross_weight` > 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `durable_product`
--

LOCK TABLES `durable_product` WRITE;
/*!40000 ALTER TABLE `durable_product` DISABLE KEYS */;
INSERT INTO `durable_product` VALUES ('DP12345678','Samsung TV','Samsung','Televisions',40000,27,12,'db',1,24,2.0),('DP98765432','Laptop','ASUS','Laptops',250000,27,0,'db',1,24,3.0);
/*!40000 ALTER TABLE `durable_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `perishable_product`
--

DROP TABLE IF EXISTS `perishable_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `perishable_product` (
  `article_number` varchar(10) NOT NULL,
  `name` varchar(150) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `brand` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `family` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `netto_price` int NOT NULL,
  `tax_id` int NOT NULL,
  `quantity` int NOT NULL,
  `amount_units` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `critical_quantity` int NOT NULL,
  `expiration_date` date NOT NULL,
  `production_date` date NOT NULL,
  PRIMARY KEY (`article_number`),
  KEY `fk_perishable_product_tax_id` (`tax_id`),
  CONSTRAINT `fk_perishable_product_tax_id` FOREIGN KEY (`tax_id`) REFERENCES `state_sales_tax` (`tax_key`),
  CONSTRAINT `perishable_product_chk_1` CHECK ((`netto_price` >= 0)),
  CONSTRAINT `perishable_product_chk_2` CHECK ((`quantity` >= 0)),
  CONSTRAINT `perishable_product_chk_3` CHECK ((`critical_quantity` >= 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `perishable_product`
--

LOCK TABLES `perishable_product` WRITE;
/*!40000 ALTER TABLE `perishable_product` DISABLE KEYS */;
INSERT INTO `perishable_product` VALUES ('PP12345678','Banana','Lidl','Fruits',900,27,200,'kg',20,'2023-04-01','2023-03-11'),('PP98765432','Apple','Tesco','Fruits',400,27,100,'kg',10,'2023-03-20','2023-01-01');
/*!40000 ALTER TABLE `perishable_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `state_sales_tax`
--

DROP TABLE IF EXISTS `state_sales_tax`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `state_sales_tax` (
  `tax_key` int NOT NULL,
  `description` varchar(20) NOT NULL,
  `multiplier` double NOT NULL,
  PRIMARY KEY (`tax_key`),
  CONSTRAINT `state_sales_tax_chk_1` CHECK ((`tax_key` between 0 and 100))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `state_sales_tax`
--

LOCK TABLES `state_sales_tax` WRITE;
/*!40000 ALTER TABLE `state_sales_tax` DISABLE KEYS */;
INSERT INTO `state_sales_tax` VALUES (27,'27%',1.27);
/*!40000 ALTER TABLE `state_sales_tax` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'storage'
--
/*!50003 DROP PROCEDURE IF EXISTS `insert_durable_product` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_durable_product`(
    IN article_number NVARCHAR(10),
    IN name NVARCHAR(150),
    IN brand NVARCHAR(50),
    IN family NVARCHAR(50),
    IN netto_price INT,
    IN tax INT,
    IN quantity INT,
    IN amount_units NVARCHAR(10),
    IN critical_quantity INT,
    IN warranty_period INT,
    IN gross_weight DECIMAL(4,1)
)
BEGIN
    DECLARE success INT;
    DECLARE row_count INT;
    SET success = 0;
    IF LENGTH(article_number) = 10 AND LEFT(article_number, 2) REGEXP '^[A-Za-z]{2}$' AND SUBSTRING(article_number, 3) REGEXP '^[0-9]{8}$' THEN
        SELECT COUNT(*) INTO row_count FROM state_sales_tax WHERE tax_key = tax;
        IF row_count = 0 THEN
            INSERT INTO state_sales_tax (tax_key, description, multiplier)
            VALUES (tax, CONCAT(tax, '%'), 1 + (tax / 100.0));
        END IF;
        IF LEFT(article_number, 2) = 'DP' THEN
            
            INSERT INTO durable_product (
                article_number,
                name,
                brand,
                family,
                netto_price,
                tax_id,
                quantity,
                amount_units,
                critical_quantity,
                warranty_period,
                gross_weight
            )
            VALUES (
                article_number,
                name,
                brand,
                family,
                netto_price,
                tax,
                quantity,
                amount_units,
                critical_quantity,
                warranty_period,
                gross_weight
            );
        END IF;
        SET success = 1;
    END IF;
    SELECT success AS 'success';
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `insert_perishable_product` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_perishable_product`(
    IN article_number VARCHAR(10),
    IN name NVARCHAR(150),
    IN brand NVARCHAR(50),
    IN family NVARCHAR(50),
    IN netto_price INT,
    IN tax INT,
    IN quantity INT,
    IN amount_units NVARCHAR(10),
    IN critical_quantity INT,
    IN expiration_date DATE,
    IN production_date DATE
)
BEGIN
    DECLARE success INT;
    DECLARE row_count INT;
    SET success = 0;
    IF LENGTH(article_number) = 10 AND LEFT(article_number, 2) REGEXP '^[A-Za-z]{2}$' AND SUBSTRING(article_number, 3) REGEXP '^[0-9]{8}$' THEN
    SELECT COUNT(*) INTO row_count FROM state_sales_tax WHERE tax_key = tax;
    IF row_count = 0 THEN
        INSERT INTO state_sales_tax (tax_key, description, multiplier)
        VALUES (tax, CONCAT(tax, '%'), 1 + (tax / 100.0));
    END IF;
    IF LEFT(article_number, 2) = 'PP' THEN
     
        INSERT INTO perishable_product (
            article_number,
            name,
            brand,
            family,
            netto_price,
            tax_id,
            quantity,
            amount_units,
            critical_quantity,
            expiration_date,
            production_date
        )
        VALUES (
            article_number,
            name,
            brand,
            family,
            netto_price,
            tax,
            quantity,
            amount_units,
            critical_quantity,
            expiration_date,
            production_date
        );
    END IF;
      SET success = 1;
    End IF;
     SELECT success AS 'success';
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-03-11 11:37:39
