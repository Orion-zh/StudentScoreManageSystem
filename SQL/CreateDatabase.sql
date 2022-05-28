CREATE DATABASE SSMS;
USE SSMS;
-- ----------------------------
-- Table structure for Course
-- ----------------------------
DROP TABLE IF EXISTS `Course`;
CREATE TABLE `Course` (
  `courseID` varchar(6) NOT NULL,
  `courseName` varchar(30) NOT NULL,
  `courseCredit` decimal(2,1) DEFAULT NULL,
  PRIMARY KEY (`courseID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- ----------------------------
-- Table structure for Score
-- ----------------------------
DROP TABLE IF EXISTS `Score`;
CREATE TABLE `Score` (
  `stuID` varchar(11) NOT NULL,
  `courseID` varchar(11) NOT NULL,
  `studyYear` char(4) DEFAULT NULL,
  `score` int DEFAULT NULL,
  PRIMARY KEY (`stuID`,`courseID`),
  KEY `courseID` (`courseID`),
  CONSTRAINT `Score_ibfk_2` FOREIGN KEY (`courseID`) REFERENCES `Course` (`courseID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- ----------------------------
-- Table structure for Student
-- ----------------------------
DROP TABLE IF EXISTS `Student`;
CREATE TABLE `Student` (
  `stuID` varchar(11) NOT NULL,
  `stuName` varchar(30) NOT NULL,
  `stuSex` varchar(4) DEFAULT NULL,
  PRIMARY KEY (`stuID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- ----------------------------
-- Table structure for Users
-- ----------------------------
DROP TABLE IF EXISTS `Users`;
CREATE TABLE `Users` (
  `userLoginName` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `userName` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `userPassword` varchar(30) DEFAULT NULL,
  `userLevel` int NOT NULL,
  PRIMARY KEY (`userLoginName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- ----------------------------
-- View structure for Score_View
-- ----------------------------
DROP VIEW IF EXISTS `Score_View`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `Score_View` AS select `Score`.`courseID` AS `courseID`,`Course`.`courseName` AS `courseName`,`Score`.`studyYear` AS `studyYear`,`Course`.`courseCredit` AS `courseCredit`,`Score`.`stuID` AS `stuID`,`Score`.`score` AS `score` from (`Score` join `Course` on((`Score`.`courseID` = `Course`.`courseID`))) ;