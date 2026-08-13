CREATE DATABASE  IF NOT EXISTS `tech_match` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `tech_match`;
-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: tech_match
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.32-MariaDB

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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(120) NOT NULL,
  `email` varchar(150) NOT NULL,
  `senha` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'Administrador','admin@gmail.com','123admin');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `avaliacao`
--

DROP TABLE IF EXISTS `avaliacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `avaliacao` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `chamado_id` bigint(20) NOT NULL,
  `profissional_id` bigint(20) NOT NULL,
  `nota` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_aval_chamado` (`chamado_id`),
  KEY `fk_aval_profissional` (`profissional_id`),
  CONSTRAINT `fk_aval_chamado` FOREIGN KEY (`chamado_id`) REFERENCES `chamado` (`id`),
  CONSTRAINT `fk_aval_profissional` FOREIGN KEY (`profissional_id`) REFERENCES `profissional` (`id`),
  CONSTRAINT `avaliacao_chk_1` CHECK (`nota` between 1 and 5)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `avaliacao`
--

LOCK TABLES `avaliacao` WRITE;
/*!40000 ALTER TABLE `avaliacao` DISABLE KEYS */;
/*!40000 ALTER TABLE `avaliacao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chamado`
--

DROP TABLE IF EXISTS `chamado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chamado` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `empresa_id` bigint(20) NOT NULL,
  `especialidade_id` bigint(20) NOT NULL,
  `profissional_id` bigint(20) DEFAULT NULL,
  `descricao` text NOT NULL,
  `urgencia` enum('baixa','media','critica') NOT NULL,
  `status` enum('aberto','aceito','concluido','cancelado') DEFAULT 'aberto',
  `orcamento_maximo` decimal(10,2) NOT NULL,
  `cidade` varchar(100) NOT NULL,
  `estado` char(2) NOT NULL,
  `aberto_em` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `fk_chamado_empresa` (`empresa_id`),
  KEY `fk_chamado_especialidade` (`especialidade_id`),
  KEY `fk_chamado_profissional` (`profissional_id`),
  CONSTRAINT `fk_chamado_empresa` FOREIGN KEY (`empresa_id`) REFERENCES `empresa` (`id`),
  CONSTRAINT `fk_chamado_especialidade` FOREIGN KEY (`especialidade_id`) REFERENCES `especialidade` (`id`),
  CONSTRAINT `fk_chamado_profissional` FOREIGN KEY (`profissional_id`) REFERENCES `profissional` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chamado`
--

LOCK TABLES `chamado` WRITE;
/*!40000 ALTER TABLE `chamado` DISABLE KEYS */;
/*!40000 ALTER TABLE `chamado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empresa`
--

DROP TABLE IF EXISTS `empresa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empresa` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(150) NOT NULL,
  `senha` varchar(255) NOT NULL,
  `cnpj` varchar(18) NOT NULL,
  `telefone` varchar(20) DEFAULT NULL,
  `cidade` varchar(100) NOT NULL,
  `estado` char(2) NOT NULL,
  `status` enum('ativo','suspenso','pendente') DEFAULT 'pendente',
  `nome` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `cnpj` (`cnpj`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empresa`
--

LOCK TABLES `empresa` WRITE;
/*!40000 ALTER TABLE `empresa` DISABLE KEYS */;
INSERT INTO `empresa` VALUES (1,'souzaempresa@gmail.co','12345','65.950.850/0001-04','439657435467','Londrina','PR','pendente','Souza Empresa');
/*!40000 ALTER TABLE `empresa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `especialidade`
--

DROP TABLE IF EXISTS `especialidade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `especialidade` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `categoria` varchar(80) NOT NULL,
  `valor_hora_minimo` decimal(8,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `especialidade`
--

LOCK TABLES `especialidade` WRITE;
/*!40000 ALTER TABLE `especialidade` DISABLE KEYS */;
INSERT INTO `especialidade` VALUES (1,'Suporte de rede','Infraestrutura',150.00),(2,'Hardware e manutenção','Hardware',120.00),(3,'Sistemas operacionais','Software',130.00),(4,'Segurança da informação','Segurança',250.00),(5,'Cloud e virtualização','Infraestrutura',200.00),(6,'Banco de dados','Software',180.00),(7,'Suporte helpdesk','Suporte',100.00);
/*!40000 ALTER TABLE `especialidade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `match_result`
--

DROP TABLE IF EXISTS `match_result`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `match_result` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `chamado_id` bigint(20) NOT NULL,
  `profissional_id` bigint(20) NOT NULL,
  `score_total` decimal(5,2) NOT NULL,
  `status_resposta` enum('pendente','aceito','recusado','expirado') DEFAULT 'pendente',
  PRIMARY KEY (`id`),
  KEY `fk_match_chamado` (`chamado_id`),
  KEY `fk_match_profissional` (`profissional_id`),
  CONSTRAINT `fk_match_chamado` FOREIGN KEY (`chamado_id`) REFERENCES `chamado` (`id`),
  CONSTRAINT `fk_match_profissional` FOREIGN KEY (`profissional_id`) REFERENCES `profissional` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `match_result`
--

LOCK TABLES `match_result` WRITE;
/*!40000 ALTER TABLE `match_result` DISABLE KEYS */;
/*!40000 ALTER TABLE `match_result` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profissional`
--

DROP TABLE IF EXISTS `profissional`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `profissional` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(120) NOT NULL,
  `email` varchar(150) NOT NULL,
  `senha` varchar(255) NOT NULL,
  `cpf` varchar(14) NOT NULL,
  `telefone` varchar(20) DEFAULT NULL,
  `cidade` varchar(100) NOT NULL,
  `estado` char(2) NOT NULL,
  `valor_hora` decimal(8,2) NOT NULL DEFAULT 0.00,
  `status` enum('ativo','suspenso','pendente') DEFAULT 'ativo',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `cpf` (`cpf`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profissional`
--

LOCK TABLES `profissional` WRITE;
/*!40000 ALTER TABLE `profissional` DISABLE KEYS */;
INSERT INTO `profissional` VALUES (1,'Carlos Silva','carlos.silva@email.com','senhaSegura123','123.456.789-00','(11) 91234-5678','São Paulo','SP',150.00,'ativo'),(3,'João Silva','joao.novo@email.com','123','987.654.321-99','43999999999','Londrina','PR',50.00,'pendente'),(4,'Felipe Souza Ribeiro','felipe@gmail.com','12345','11243565412','43976646586','Londrina','PR',250.00,'pendente');
/*!40000 ALTER TABLE `profissional` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profissional_configuracao`
--

DROP TABLE IF EXISTS `profissional_configuracao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `profissional_configuracao` (
  `profissional_id` bigint(20) NOT NULL,
  `raio_atendimento_km` int(11) NOT NULL DEFAULT 20,
  `score` decimal(5,2) NOT NULL DEFAULT 100.00,
  `disponivel` tinyint(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`profissional_id`),
  CONSTRAINT `fk_config_profissional` FOREIGN KEY (`profissional_id`) REFERENCES `profissional` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profissional_configuracao`
--

LOCK TABLES `profissional_configuracao` WRITE;
/*!40000 ALTER TABLE `profissional_configuracao` DISABLE KEYS */;
INSERT INTO `profissional_configuracao` VALUES (3,20,100.00,1),(4,10,100.00,1);
/*!40000 ALTER TABLE `profissional_configuracao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profissional_especialidade`
--

DROP TABLE IF EXISTS `profissional_especialidade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `profissional_especialidade` (
  `profissional_id` bigint(20) NOT NULL,
  `especialidade_id` bigint(20) NOT NULL,
  `nivel` enum('basico','intermediario','avancado') NOT NULL,
  `anos_experiencia` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`profissional_id`,`especialidade_id`),
  KEY `fk_pe_especialidade` (`especialidade_id`),
  CONSTRAINT `fk_pe_especialidade` FOREIGN KEY (`especialidade_id`) REFERENCES `especialidade` (`id`),
  CONSTRAINT `fk_pe_profissional` FOREIGN KEY (`profissional_id`) REFERENCES `profissional` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profissional_especialidade`
--

LOCK TABLES `profissional_especialidade` WRITE;
/*!40000 ALTER TABLE `profissional_especialidade` DISABLE KEYS */;
INSERT INTO `profissional_especialidade` VALUES (4,1,'intermediario',3),(4,2,'intermediario',4),(4,4,'intermediario',3);
/*!40000 ALTER TABLE `profissional_especialidade` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-08-13 17:26:22
