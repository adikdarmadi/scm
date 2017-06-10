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


-- Dumping database structure for hk
DROP DATABASE IF EXISTS `hk`;
CREATE DATABASE IF NOT EXISTS `hk` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `hk`;

-- Dumping structure for table hk.m_detail_jenis_produk
DROP TABLE IF EXISTS `m_detail_jenis_produk`;
CREATE TABLE IF NOT EXISTS `m_detail_jenis_produk` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `detail_jenis_produk` varchar(50) NOT NULL,
  `fk_jenis_produk` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_cvg9rq2os1e7tsoq3ev1ja4ng` (`fk_jenis_produk`),
  CONSTRAINT `FK_cvg9rq2os1e7tsoq3ev1ja4ng` FOREIGN KEY (`fk_jenis_produk`) REFERENCES `m_jenis_produk` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Dumping data for table hk.m_detail_jenis_produk: ~1 rows (approximately)
DELETE FROM `m_detail_jenis_produk`;
/*!40000 ALTER TABLE `m_detail_jenis_produk` DISABLE KEYS */;
INSERT INTO `m_detail_jenis_produk` (`id`, `detail_jenis_produk`, `fk_jenis_produk`) VALUES
	(1, 'Ini Detail Jenis Produk', 1);
/*!40000 ALTER TABLE `m_detail_jenis_produk` ENABLE KEYS */;

-- Dumping structure for table hk.m_gudang_grup
DROP TABLE IF EXISTS `m_gudang_grup`;
CREATE TABLE IF NOT EXISTS `m_gudang_grup` (
  `GUDANG_GRUP_ID` varchar(255) NOT NULL,
  `CREATE_BY` varchar(50) NOT NULL,
  `CREATE_DATE` datetime NOT NULL,
  `LAST_UPDATE_BY` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_DATE` datetime DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  `DATE_NON_ACTIVE` datetime DEFAULT NULL,
  `IS_ACTIVE` bit(1) DEFAULT NULL,
  `NAMA` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`GUDANG_GRUP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table hk.m_gudang_grup: ~1 rows (approximately)
DELETE FROM `m_gudang_grup`;
/*!40000 ALTER TABLE `m_gudang_grup` DISABLE KEYS */;
INSERT INTO `m_gudang_grup` (`GUDANG_GRUP_ID`, `CREATE_BY`, `CREATE_DATE`, `LAST_UPDATE_BY`, `LAST_UPDATE_DATE`, `VERSION`, `DATE_NON_ACTIVE`, `IS_ACTIVE`, `NAMA`) VALUES
	('TEST3', 'admin', '2017-06-10 08:34:03', NULL, NULL, 0, NULL, b'1', 'sdsd');
/*!40000 ALTER TABLE `m_gudang_grup` ENABLE KEYS */;

-- Dumping structure for table hk.m_jenis_produk
DROP TABLE IF EXISTS `m_jenis_produk`;
CREATE TABLE IF NOT EXISTS `m_jenis_produk` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `jenis_produk` varchar(80) NOT NULL,
  `fk_kelompok_produk` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_q5dyc8t7tk1k49iju618ws0sv` (`fk_kelompok_produk`),
  CONSTRAINT `FK_q5dyc8t7tk1k49iju618ws0sv` FOREIGN KEY (`fk_kelompok_produk`) REFERENCES `m_kelompok_produk` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Dumping data for table hk.m_jenis_produk: ~1 rows (approximately)
DELETE FROM `m_jenis_produk`;
/*!40000 ALTER TABLE `m_jenis_produk` DISABLE KEYS */;
INSERT INTO `m_jenis_produk` (`id`, `jenis_produk`, `fk_kelompok_produk`) VALUES
	(1, 'Ini Jenis Produk', 1);
/*!40000 ALTER TABLE `m_jenis_produk` ENABLE KEYS */;

-- Dumping structure for table hk.m_kelompok_produk
DROP TABLE IF EXISTS `m_kelompok_produk`;
CREATE TABLE IF NOT EXISTS `m_kelompok_produk` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `kelompok_produk` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Dumping data for table hk.m_kelompok_produk: ~1 rows (approximately)
DELETE FROM `m_kelompok_produk`;
/*!40000 ALTER TABLE `m_kelompok_produk` DISABLE KEYS */;
INSERT INTO `m_kelompok_produk` (`id`, `kelompok_produk`) VALUES
	(1, 'Ini Kelompok Produk');
/*!40000 ALTER TABLE `m_kelompok_produk` ENABLE KEYS */;

-- Dumping structure for table hk.m_produk
DROP TABLE IF EXISTS `m_produk`;
CREATE TABLE IF NOT EXISTS `m_produk` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nama_produk` varchar(255) NOT NULL,
  `fk_detail_jenis_produk` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_7cervu3mxnam68pw9fds751jv` (`fk_detail_jenis_produk`),
  CONSTRAINT `FK_7cervu3mxnam68pw9fds751jv` FOREIGN KEY (`fk_detail_jenis_produk`) REFERENCES `m_detail_jenis_produk` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table hk.m_produk: ~0 rows (approximately)
DELETE FROM `m_produk`;
/*!40000 ALTER TABLE `m_produk` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_produk` ENABLE KEYS */;

-- Dumping structure for table hk.m_rekening
DROP TABLE IF EXISTS `m_rekening`;
CREATE TABLE IF NOT EXISTS `m_rekening` (
  `id` varchar(255) NOT NULL,
  `nama` varchar(255) NOT NULL,
  `nomor` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_2wdie888qb7dg1e6hqe4xl5ga` (`nomor`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table hk.m_rekening: ~0 rows (approximately)
DELETE FROM `m_rekening`;
/*!40000 ALTER TABLE `m_rekening` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_rekening` ENABLE KEYS */;

-- Dumping structure for table hk.m_user
DROP TABLE IF EXISTS `m_user`;
CREATE TABLE IF NOT EXISTS `m_user` (
  `USER_ID` varchar(50) NOT NULL,
  `CREATE_BY` varchar(50) NOT NULL,
  `CREATE_DATE` datetime NOT NULL,
  `LAST_UPDATE_BY` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_DATE` datetime DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  `NAMA` varchar(50) NOT NULL,
  `PASSWORD` varchar(50) NOT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table hk.m_user: ~1 rows (approximately)
DELETE FROM `m_user`;
/*!40000 ALTER TABLE `m_user` DISABLE KEYS */;
INSERT INTO `m_user` (`USER_ID`, `CREATE_BY`, `CREATE_DATE`, `LAST_UPDATE_BY`, `LAST_UPDATE_DATE`, `VERSION`, `NAMA`, `PASSWORD`) VALUES
	('admin', 'admin', '2017-04-13 09:11:42', NULL, NULL, 1, 'admin', '7R2bV7up7EoB5LsJqEo08yVz1kQ=$t5D3plyB2IY=');
/*!40000 ALTER TABLE `m_user` ENABLE KEYS */;

-- Dumping structure for table hk.schema_version
DROP TABLE IF EXISTS `schema_version`;
CREATE TABLE IF NOT EXISTS `schema_version` (
  `installed_rank` int(11) NOT NULL,
  `version` varchar(50) DEFAULT NULL,
  `description` varchar(200) NOT NULL,
  `type` varchar(20) NOT NULL,
  `script` varchar(1000) NOT NULL,
  `checksum` int(11) DEFAULT NULL,
  `installed_by` varchar(100) NOT NULL,
  `installed_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `execution_time` int(11) NOT NULL,
  `success` tinyint(1) NOT NULL,
  PRIMARY KEY (`installed_rank`),
  KEY `schema_version_s_idx` (`success`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table hk.schema_version: ~0 rows (approximately)
DELETE FROM `schema_version`;
/*!40000 ALTER TABLE `schema_version` DISABLE KEYS */;
/*!40000 ALTER TABLE `schema_version` ENABLE KEYS */;

-- Dumping structure for table hk.t_detail_komponen_harga
DROP TABLE IF EXISTS `t_detail_komponen_harga`;
CREATE TABLE IF NOT EXISTS `t_detail_komponen_harga` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `deskripsi` varchar(80) NOT NULL,
  `fk_struk_order_detail` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_q8atwbd0v63tv26evce6syjdl` (`fk_struk_order_detail`),
  CONSTRAINT `FK_q8atwbd0v63tv26evce6syjdl` FOREIGN KEY (`fk_struk_order_detail`) REFERENCES `t_struk_order_detail` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table hk.t_detail_komponen_harga: ~0 rows (approximately)
DELETE FROM `t_detail_komponen_harga`;
/*!40000 ALTER TABLE `t_detail_komponen_harga` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_detail_komponen_harga` ENABLE KEYS */;

-- Dumping structure for table hk.t_struk_order
DROP TABLE IF EXISTS `t_struk_order`;
CREATE TABLE IF NOT EXISTS `t_struk_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tanggalOrder` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table hk.t_struk_order: ~0 rows (approximately)
DELETE FROM `t_struk_order`;
/*!40000 ALTER TABLE `t_struk_order` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_struk_order` ENABLE KEYS */;

-- Dumping structure for table hk.t_struk_order_detail
DROP TABLE IF EXISTS `t_struk_order_detail`;
CREATE TABLE IF NOT EXISTS `t_struk_order_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fk_produk` int(11) NOT NULL,
  `fk_struk_order` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_cmjiipbm6aaly5739higs2aon` (`fk_produk`),
  KEY `FK_fmeknp5pqniryygqt2is8qeak` (`fk_struk_order`),
  CONSTRAINT `FK_cmjiipbm6aaly5739higs2aon` FOREIGN KEY (`fk_produk`) REFERENCES `m_produk` (`id`),
  CONSTRAINT `FK_fmeknp5pqniryygqt2is8qeak` FOREIGN KEY (`fk_struk_order`) REFERENCES `t_struk_order` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table hk.t_struk_order_detail: ~0 rows (approximately)
DELETE FROM `t_struk_order_detail`;
/*!40000 ALTER TABLE `t_struk_order_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_struk_order_detail` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
