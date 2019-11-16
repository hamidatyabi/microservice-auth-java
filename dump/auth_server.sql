-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: db
-- Generation Time: Nov 16, 2019 at 11:29 AM
-- Server version: 5.7.27
-- PHP Version: 7.2.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `auth_server`
--
USE auth_server;
-- --------------------------------------------------------

--
-- Table structure for table `clients`
--

DROP TABLE IF EXISTS `clients`;
CREATE TABLE IF NOT EXISTS `clients` (
  `client_id` varchar(50) NOT NULL,
  `client_secret` text NOT NULL,
  `resource_ids` text NOT NULL,
  `scope` varchar(250) NOT NULL,
  `authorized_grant_types` text NOT NULL,
  `registered_redirect_uri` text NOT NULL,
  `authorities` text NOT NULL,
  `access_token_validity_seconds` int(11) NOT NULL DEFAULT '3600',
  `refresh_token_validity_seconds` int(11) NOT NULL DEFAULT '7200',
  `additional_information` text NOT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `clients`
--

INSERT INTO `clients` (`client_id`, `client_secret`, `resource_ids`, `scope`, `authorized_grant_types`, `registered_redirect_uri`, `authorities`, `access_token_validity_seconds`, `refresh_token_validity_seconds`, `additional_information`) VALUES
('client', '$2a$10$UK75qLwrOf7OIANWxqHfhe3DEr/IHkSovzIdDocbCW7FXRCVAXU6G', 'AUTHENTICATION', 'read,write', 'refresh_token,password,client_credentials', '', 'ROLE_ADMINISTRATOR,ROLE_SUPER_ADMIN,ROLE_USER', 3600, 7200, '');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(40) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` text NOT NULL,
  `full_name` varchar(100) DEFAULT NULL,
  `company_name` varchar(100) DEFAULT NULL,
  `type` enum('super_admin','administrator','user') NOT NULL DEFAULT 'user',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `credit` double(24,2) NOT NULL DEFAULT '0.00',
  `status` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `email`, `password`, `full_name`, `company_name`, `type`, `create_time`, `credit`, `status`) VALUES
(1, 'admin', 'admin@example.com', '$2a$10$UK75qLwrOf7OIANWxqHfhe3DEr/IHkSovzIdDocbCW7FXRCVAXU6G', 'Hamid Atyabi', NULL, 'super_admin', '2019-11-15 17:39:46', 0.00, 1);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
