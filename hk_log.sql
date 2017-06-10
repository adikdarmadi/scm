-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.1.19-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win32
-- HeidiSQL Version:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for hk_log
DROP DATABASE IF EXISTS `hk_log`;
CREATE DATABASE IF NOT EXISTS `hk_log` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `hk_log`;

-- Dumping structure for table hk_log.auditlog
DROP TABLE IF EXISTS `auditlog`;
CREATE TABLE IF NOT EXISTS `auditlog` (
  `AUDIT_LOG_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ACTION` varchar(100) DEFAULT NULL,
  `CREATED_DATE` datetime DEFAULT NULL,
  `DETAIL` varchar(1000) DEFAULT NULL,
  `ENTITY_ID` varchar(255) DEFAULT NULL,
  `ENTITY_NAME` varchar(255) DEFAULT NULL,
  `MODULE` varchar(100) DEFAULT NULL,
  `USERNAME` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`AUDIT_LOG_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=229 DEFAULT CHARSET=latin1;

-- Dumping data for table hk_log.auditlog: ~1 rows (approximately)
DELETE FROM `auditlog`;
/*!40000 ALTER TABLE `auditlog` DISABLE KEYS */;
INSERT INTO `auditlog` (`AUDIT_LOG_ID`, `ACTION`, `CREATED_DATE`, `DETAIL`, `ENTITY_ID`, `ENTITY_NAME`, `MODULE`, `USERNAME`) VALUES
	(228, 'INSERT', '2017-06-10 08:34:03', 'Gudang Grup ID : TEST3', 'TEST3', 'class com.scm.entities.GudangGrup', 'GudangGrup', 'admin');
/*!40000 ALTER TABLE `auditlog` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
