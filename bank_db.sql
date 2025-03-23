-- phpMyAdmin SQL Dump
-- version 5.2.2
-- https://www.phpmyadmin.net/
--
-- Host: mysql_db:3306
-- Generation Time: Mar 23, 2025 at 01:25 PM
-- Server version: 8.0.41
-- PHP Version: 8.2.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bank_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `Accounts`
--

CREATE TABLE `Accounts` (
  `accountID` int NOT NULL,
  `studentID` int NOT NULL,
  `accountAlias` text NOT NULL,
  `accountBalance` float NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `Students`
--

CREATE TABLE `Students` (
  `studentID` int NOT NULL,
  `studentName` text NOT NULL,
  `studentAddress` text NOT NULL,
  `studentEmail` text NOT NULL,
  `studentPhone` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `Students`
--

INSERT INTO `Students` (`studentID`, `studentName`, `studentAddress`, `studentEmail`, `studentPhone`) VALUES
(1, 'Jack Jibb', '123 Generic Road, London, UK', 'jj@gre.test', '123456789'),
(2, 'Jack Jibb', '123 Generic Road, London, UK', 'jj@gre.test', '123456789'),
(3, 'Jack J', '123 Generic Road, London, UK', 'jack@jibb.com', '123456789'),
(4, 'John Smith', '123 Oracle Avenue', 'js@javascript.com', '789456123'),
(5, 'Test Test', '123 Test Road, London, UK', 'test@test.com', '123456789'),
(6, 'John Doe', '221b Baker Street', 'johndoe@email.com', '07812 012232'),
(7, 'tt', '123 Test Road, Test, TT', '12@12.12', '1234112321'),
(8, 'Test2', '1234 Baker', 'test@test.test', '1234');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Accounts`
--
ALTER TABLE `Accounts`
  ADD KEY `FK_Student_Account` (`studentID`);

--
-- Indexes for table `Students`
--
ALTER TABLE `Students`
  ADD PRIMARY KEY (`studentID`),
  ADD UNIQUE KEY `studentID` (`studentID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Students`
--
ALTER TABLE `Students`
  MODIFY `studentID` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `Accounts`
--
ALTER TABLE `Accounts`
  ADD CONSTRAINT `FK_Student_Account` FOREIGN KEY (`studentID`) REFERENCES `Students` (`studentID`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
