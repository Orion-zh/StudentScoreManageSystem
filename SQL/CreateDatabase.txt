CREATE DATABASE SSMS;
USE SSMS;

CREATE TABLE Department(
deptID VARCHAR(6) PRIMARY KEY,
deptName VARCHAR(30) NOT NULL
);

CREATE TABLE Course(
courseID VARCHAR(6) PRIMARY KEY,
courseName VARCHAR(30) NOT NULL
);

CREATE TABLE Major(
majorID VARCHAR(6) PRIMARY KEY,
majorName VARCHAR(6) NOT NULL,
deptID VARCHAR(6) NOT NULL,
FOREIGN KEY (deptID) REFERENCES Department(deptID)
);

CREATE TABLE Class(
classID VARCHAR(6) PRIMARY KEY,
className VARCHAR(30) NOT NULL,
majorID VARCHAR(6) NOT NULL,
grade CHAR(4) NOT NULL,
FOREIGN KEY (majorID) REFERENCES Major(majorID)
);

CREATE TABLE Student(
stuID varchar(11) PRIMARY KEY,
stuName VARCHAR(30) NOT NULL,
stuSex varchar(4) ,
stuClass varchar(6) NOT NULL,
FOREIGN KEY (stuClass) REFERENCES Class(ClassID)
);

CREATE TABLE Score(
stuID varchar(11),
courseID varchar(11),
studyYear char(4),
score int,
PRIMARY KEY (stuID,CourseID),
FOREIGN KEY (stuID) REFERENCES Student(stuID),
FOREIGN KEY (courseID) REFERENCES Course(courseID)
);

CREATE TABLE Users(
userLoginName VARCHAR(11) PRIMARY KEY,
userName VARCHAR(30) NOT NULL,
userPassword VARCHAR(30),
userLevel int NOT NULL
);