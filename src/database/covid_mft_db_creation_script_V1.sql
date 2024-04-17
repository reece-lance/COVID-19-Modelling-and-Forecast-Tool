DROP DATABASE IF EXISTS covid_mft_db;
CREATE DATABASE covid_mft_db;
USE covid_mft_db;

CREATE TABLE `fcovid` (
  `areaType` text,
  `areaName` text,
  `areaCode` text,
  `date` text,
  `newCasesBySpecimenDate` int DEFAULT NULL,
  `cumCasesBySpecimenDate` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
