
CREATE schema BankingApp;

CREATE TABLE  "banks" (
  "Id" int(30) NOT NULL AUTO_INCREMENT,
  "Name" varchar(30) NOT NULL,
  "SortCode" int(20) NOT NULL,
  "Address" varchar(120) NOT NULL,
  PRIMARY KEY ("Id")
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table "Banks"
--

LOCK TABLES "Banks" WRITE;
/*!40000 ALTER TABLE "Banks" DISABLE KEYS */;
INSERT INTO "Banks" VALUES (1,'TSB',123,'Swords Manor');
/*!40000 ALTER TABLE "Banks" ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table "Transactions"
--

DROP TABLE IF EXISTS "Transactions";
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE "Transactions" (
  "Id" int(30) NOT NULL AUTO_INCREMENT,
  "U_Id" int(30) NOT NULL,
  "Description" varchar(30) NOT NULL,
  "Adding" double DEFAULT NULL,
  "subtracting" double DEFAULT NULL,
  "trans_date" timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY ("Id")
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table "Transactions"
--

LOCK TABLES "Transactions" WRITE;
/*!40000 ALTER TABLE "Transactions" DISABLE KEYS */;
INSERT INTO "Transactions" VALUES (1,1,'income',2000,0,'2016-11-15 17:59:07'),(2,1,'Sales',0,200,'2016-11-15 17:59:45'),(3,2,'Lodgment',2000,0,'2016-11-16 21:23:16'),(4,2,'Lodgment',2000,0,'2016-11-16 21:23:24'),(5,1,'Transfer',1000,0,'2016-11-16 21:38:38'),(6,2,'Transfer',0,1000,'2016-11-16 21:38:38'),(7,1,'Transfer',1000,0,'2016-11-16 21:41:26'),(8,2,'Transfer',0,1000,'2016-11-16 21:41:26'),(9,1,'Transfer',1000,0,'2016-11-16 21:44:03'),(10,2,'Transfer',0,1000,'2016-11-16 21:44:03'),(11,1,'Transfer',500,0,'2016-11-16 21:44:47'),(12,2,'Transfer',0,500,'2016-11-16 21:44:47'),(13,1,'Transfer',500,0,'2016-11-16 22:00:55'),(14,2,'Transfer',0,500,'2016-11-16 22:00:55'),(15,1,'Transfer',500,0,'2016-11-16 22:01:06'),(16,2,'Transfer',0,500,'2016-11-16 22:01:06'),(17,1,'Transfer',500,0,'2016-11-16 22:01:16'),(18,2,'Transfer',0,500,'2016-11-16 22:01:16'),(19,1,'Transfer',500,0,'2016-11-16 22:01:27'),(20,2,'Transfer',0,500,'2016-11-16 22:01:27'),(21,1,'Transfer',500,0,'2016-11-16 22:01:52'),(22,2,'Transfer',0,500,'2016-11-16 22:01:52'),(23,2,'Transfer',500,0,'2016-11-16 22:14:59'),(24,1,'Transfer',0,500,'2016-11-16 22:14:59'),(25,2,'Transfer',500,0,'2016-11-16 22:16:14'),(26,1,'Transfer',0,500,'2016-11-16 22:16:14'),(27,2,'Transfer',500,0,'2016-11-16 22:16:15'),(28,1,'Transfer',0,500,'2016-11-16 22:16:15'),(29,2,'Transfer',500,0,'2016-11-16 22:16:31'),(30,1,'Transfer',0,500,'2016-11-16 22:16:31'),(31,2,'Transfer',500,0,'2016-11-16 23:18:20'),(32,1,'Transfer',0,500,'2016-11-16 23:18:20'),(33,1,'Lodgment',0,1000,'2016-11-16 23:24:40'),(34,1,'Lodgment',0,1000,'2016-11-16 23:25:44');
/*!40000 ALTER TABLE "Transactions" ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table "Users"
--

DROP TABLE IF EXISTS "Users";
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE "Users" (
  "Id" int(30) NOT NULL AUTO_INCREMENT,
  "Name" varchar(30) NOT NULL,
  "email" varchar(50) DEFAULT NULL,
  "Password" varchar(12) DEFAULT NULL,
  "AccNum" int(20) NOT NULL,
  "Address" varchar(120) NOT NULL,
  "AccBalance" double NOT NULL,
  "B_SortCode" int(11) DEFAULT NULL,
  "reg_date" timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY ("Id"),
  UNIQUE KEY "AccNum" ("AccNum")
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table "Users"
--

LOCK TABLES "Users" WRITE;
/*!40000 ALTER TABLE "Users" DISABLE KEYS */;
INSERT INTO "Users" VALUES (1,'Admin','Joe@gmail.com','admin',4321,'swords',4500,123,'2016-11-17 14:12:14');
/*!40000 ALTER TABLE "Users" ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-11-17 14:20:39
