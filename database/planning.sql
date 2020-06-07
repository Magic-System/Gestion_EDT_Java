-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  Dim 07 juin 2020 à 15:08
-- Version du serveur :  10.4.10-MariaDB
-- Version de PHP :  7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `planning`
--
CREATE DATABASE IF NOT EXISTS `planning` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `planning`;

-- --------------------------------------------------------

--
-- Structure de la table `cours`
--

DROP TABLE IF EXISTS `cours`;
CREATE TABLE IF NOT EXISTS `cours` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(255) NOT NULL,
  `Couleur` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `cours`
--

INSERT INTO `cours` (`ID`, `Nom`, `Couleur`) VALUES
(1, 'POO Java', 'Yellow'),
(2, 'Web Dynamique', 'Cyan'),
(3, 'Analyse de Fourier', 'Green'),
(4, 'Projet d\'électronique 6', 'Cyan'),
(5, 'Traitement de signal 2', 'Blue'),
(6, 'Probabilités et statistiques', 'Orange'),
(7, 'test2', 'Grey');

-- --------------------------------------------------------

--
-- Structure de la table `enseignant`
--

DROP TABLE IF EXISTS `enseignant`;
CREATE TABLE IF NOT EXISTS `enseignant` (
  `ID_Utilisateur` int(11) NOT NULL,
  `ID_Cours` int(11) NOT NULL,
  PRIMARY KEY (`ID_Utilisateur`,`ID_Cours`),
  KEY `ID_Cours` (`ID_Cours`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `enseignant`
--

INSERT INTO `enseignant` (`ID_Utilisateur`, `ID_Cours`) VALUES
(2, 3),
(3, 1),
(3, 2),
(7, 6),
(8, 4),
(8, 5);

-- --------------------------------------------------------

--
-- Structure de la table `etudiant`
--

DROP TABLE IF EXISTS `etudiant`;
CREATE TABLE IF NOT EXISTS `etudiant` (
  `ID_Utilisateur` int(11) NOT NULL,
  `Numero` int(11) NOT NULL,
  `ID_Groupe` int(11) NOT NULL,
  PRIMARY KEY (`ID_Utilisateur`),
  KEY `ID_Groupe` (`ID_Groupe`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `etudiant`
--

INSERT INTO `etudiant` (`ID_Utilisateur`, `Numero`, `ID_Groupe`) VALUES
(4, 931702383, 4),
(5, 931701775, 4),
(6, 931701855, 4),
(9, 931701703, 1),
(10, 931701718, 1),
(11, 931701765, 2),
(12, 931702156, 2),
(13, 931701900, 3),
(14, 931702302, 3),
(15, 931702145, 5),
(16, 931702251, 5),
(17, 931701867, 6),
(18, 931900757, 6),
(19, 931701836, 7),
(20, 931701966, 7),
(21, 931702006, 8),
(22, 931702334, 8),
(23, 931800069, 9),
(24, 931701672, 9),
(25, 931701866, 10),
(26, 931702107, 10);

-- --------------------------------------------------------

--
-- Structure de la table `groupe`
--

DROP TABLE IF EXISTS `groupe`;
CREATE TABLE IF NOT EXISTS `groupe` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(255) NOT NULL,
  `ID_Promotion` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID_Promotion` (`ID_Promotion`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `groupe`
--

INSERT INTO `groupe` (`ID`, `Nom`, `ID_Promotion`) VALUES
(1, 'Groupe 1', 1),
(2, 'Groupe 2', 1),
(3, 'Groupe 3', 2),
(4, 'Groupe 4', 3),
(5, 'Groupe 5', 2),
(6, 'Groupe 6', 3),
(7, 'Groupe 7', 4),
(8, 'Groupe 8', 4),
(9, 'Groupe 9', 5),
(10, 'Groupe 10', 5);

-- --------------------------------------------------------

--
-- Structure de la table `promotion`
--

DROP TABLE IF EXISTS `promotion`;
CREATE TABLE IF NOT EXISTS `promotion` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `promotion`
--

INSERT INTO `promotion` (`ID`, `Nom`) VALUES
(1, 'ING1'),
(2, 'ING2'),
(3, 'ING3'),
(4, 'ING4'),
(5, 'ING5');

-- --------------------------------------------------------

--
-- Structure de la table `salle`
--

DROP TABLE IF EXISTS `salle`;
CREATE TABLE IF NOT EXISTS `salle` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(255) NOT NULL,
  `Capacite` int(11) NOT NULL,
  `ID_Site` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID_Site` (`ID_Site`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `salle`
--

INSERT INTO `salle` (`ID`, `Nom`, `Capacite`, `ID_Site`) VALUES
(1, 'EM009', 200, 1),
(2, 'EM010', 200, 1),
(3, 'P445', 100, 2),
(4, 'P304', 30, 2),
(5, 'G002', 70, 3);

-- --------------------------------------------------------

--
-- Structure de la table `seance`
--

DROP TABLE IF EXISTS `seance`;
CREATE TABLE IF NOT EXISTS `seance` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Semaine` int(11) NOT NULL,
  `Date` date NOT NULL,
  `Heure_Debut` time NOT NULL,
  `Heure_Fin` time NOT NULL,
  `Etat` int(11) NOT NULL COMMENT '0 = annulé, 1 = validé, 2 = en cours de validation',
  `ID_Cours` int(11) NOT NULL,
  `ID_Type` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID_Cours` (`ID_Cours`),
  KEY `ID_Type` (`ID_Type`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `seance`
--

INSERT INTO `seance` (`ID`, `Semaine`, `Date`, `Heure_Debut`, `Heure_Fin`, `Etat`, `ID_Cours`, `ID_Type`) VALUES
(1, 23, '2020-06-05', '08:30:00', '10:00:00', 1, 6, 4),
(2, 23, '2020-06-05', '10:15:00', '11:45:00', 1, 6, 4),
(3, 23, '2020-06-05', '08:30:00', '10:00:00', 1, 1, 1),
(4, 23, '2020-06-05', '10:15:00', '11:45:00', 1, 4, 5),
(5, 23, '2020-06-05', '12:00:00', '13:30:00', 1, 4, 5);

-- --------------------------------------------------------

--
-- Structure de la table `seance_enseignants`
--

DROP TABLE IF EXISTS `seance_enseignants`;
CREATE TABLE IF NOT EXISTS `seance_enseignants` (
  `ID_Seance` int(11) NOT NULL,
  `ID_Enseignant` int(11) NOT NULL,
  PRIMARY KEY (`ID_Seance`,`ID_Enseignant`),
  KEY `seance_enseignants_ibfk_2` (`ID_Enseignant`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `seance_enseignants`
--

INSERT INTO `seance_enseignants` (`ID_Seance`, `ID_Enseignant`) VALUES
(1, 7),
(2, 7),
(3, 3),
(4, 8),
(5, 8);

-- --------------------------------------------------------

--
-- Structure de la table `seance_groupes`
--

DROP TABLE IF EXISTS `seance_groupes`;
CREATE TABLE IF NOT EXISTS `seance_groupes` (
  `ID_Seance` int(11) NOT NULL,
  `ID_Groupe` int(11) NOT NULL,
  PRIMARY KEY (`ID_Seance`,`ID_Groupe`),
  KEY `seance_groupes_ibfk_1` (`ID_Groupe`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `seance_groupes`
--

INSERT INTO `seance_groupes` (`ID_Seance`, `ID_Groupe`) VALUES
(1, 3),
(1, 4),
(1, 5),
(1, 6),
(2, 3),
(2, 4),
(2, 5),
(2, 6),
(3, 4),
(4, 4),
(5, 4);

-- --------------------------------------------------------

--
-- Structure de la table `seance_salles`
--

DROP TABLE IF EXISTS `seance_salles`;
CREATE TABLE IF NOT EXISTS `seance_salles` (
  `ID_Seance` int(11) NOT NULL,
  `ID_Salle` int(11) NOT NULL,
  PRIMARY KEY (`ID_Seance`,`ID_Salle`),
  KEY `seance_salles_ibfk_1` (`ID_Salle`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `seance_salles`
--

INSERT INTO `seance_salles` (`ID_Seance`, `ID_Salle`) VALUES
(1, 1),
(2, 1),
(4, 4),
(5, 4);

-- --------------------------------------------------------

--
-- Structure de la table `site`
--

DROP TABLE IF EXISTS `site`;
CREATE TABLE IF NOT EXISTS `site` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `site`
--

INSERT INTO `site` (`ID`, `Nom`) VALUES
(1, 'E1'),
(2, 'E2'),
(3, 'E4');

-- --------------------------------------------------------

--
-- Structure de la table `type_cours`
--

DROP TABLE IF EXISTS `type_cours`;
CREATE TABLE IF NOT EXISTS `type_cours` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `type_cours`
--

INSERT INTO `type_cours` (`ID`, `Nom`) VALUES
(1, 'CI'),
(2, 'TD'),
(3, 'TP'),
(4, 'Amphi'),
(5, 'EXAM');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Email` varchar(255) NOT NULL,
  `Password` varchar(255) NOT NULL,
  `Nom` varchar(255) NOT NULL,
  `Prenom` varchar(255) NOT NULL,
  `Droit` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`ID`, `Email`, `Password`, `Nom`, `Prenom`, `Droit`) VALUES
(-1, 'dummy', 'dummy', 'dummy', 'dummy', 0),
(1, 'admin@ece.Fr', 'admin', 'Planification', 'Service', 1),
(2, 'coudray@ece.fr', 'coudray', 'Coudray', 'Fabienne', 2),
(3, 'segado@ece.fr', 'jps', 'Segado', 'Jean Pierre', 3),
(4, 'daniel@edu.ece.fr', 'azerty', 'Dias Da Silva', 'Daniel', 4),
(5, 'etienne@edu.ece.Fr', 'azerty', 'Geslin', 'Etienne', 4),
(6, 'constantin@edu.ece.fr', 'azerty', 'Kozlow', 'Constantin', 4),
(7, 'lecor@ece.fr', 'lecor', 'LE COR', 'Luc', 3),
(8, 'mokhber@ece.fr', 'mokhber', 'Mokhber', 'Arash', 3),
(9, 'de-malliard@edu.ece.fr', 'azerty', 'DE MALLIARD', 'Jean', 4),
(10, 'dhiab@edu.ece.fr', 'azerty', 'DHIAB', 'Meriem', 4),
(11, 'garrigoux-desmoulins@edu.ece.fr', 'azerty', 'GARRIGOUX DESMOULINS', 'Aurore', 4),
(12, 'cambier@edu.ece.fr', 'azerty', 'CAMBIER', 'Mathéo', 4),
(13, 'mace-montegut@edu.ece.fr', 'azerty', 'MACE-MONTEGUT', 'Clément', 4),
(14, 'perennou@edu.ece.fr', 'azerty', 'PERENNOU', 'Romain', 4),
(15, 'bourgeois@edu.ece.fr', 'azerty', 'BOURGEOIS', 'Antoine', 4),
(16, 'laouar@edu.ece.fr', 'azerty', 'LAOUAR', 'Rania', 4),
(17, 'laruel@edu.ece.fr', 'azerty', 'LARUEL', 'Camille', 4),
(18, 'derkaoui@edu.ece.fr', 'azerty', 'DERKAOUI', 'Malik', 4),
(19, 'jenselme@edu.ece.fr', 'azerty', 'JENSELME', 'Pierre', 4),
(20, 'ouboujemaa@edu.ece.fr', 'azerty', 'OUBOUJEMAA', 'Ayoub', 4),
(21, 'quidet@edu.ece.fr', 'azerty', 'QUIDET', 'Victor', 4),
(22, 'senard@edu.ece.fr', 'azerty', 'SENARD', 'Paul', 4),
(23, 'talamon@edu.ece.fr', 'azerty', 'TALAMON', 'Corentin', 4),
(24, 'chen@edu.ece.fr', 'azerty', 'CHEN', 'Pascal', 4),
(25, 'larbodiere@edu.ece.fr', 'azerty', 'LARBODIERE', 'Matthis', 4),
(26, 'zhang@edu.ece.fr', 'azerty', 'ZHANG', 'Yimou', 4),
(28, 'email', 'password', 'test', 'prenom', 0),
(29, 'email', 'password', 'nom', 'prenom', 0),
(30, 'email', 'password', 'nom', 'prenom', 0),
(31, 'email', 'password', 'test', 'prenom', 0),
(32, 'email', 'password', 'test', 'prenom', 0),
(33, 'email', 'password', 'test', 'prenom', 0),
(34, 'email', 'password', 'test', 'prenom', 0);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `enseignant`
--
ALTER TABLE `enseignant`
  ADD CONSTRAINT `enseignant_ibfk_1` FOREIGN KEY (`ID_Cours`) REFERENCES `cours` (`ID`),
  ADD CONSTRAINT `enseignant_ibfk_2` FOREIGN KEY (`ID_Utilisateur`) REFERENCES `utilisateur` (`ID`);

--
-- Contraintes pour la table `etudiant`
--
ALTER TABLE `etudiant`
  ADD CONSTRAINT `etudiant_ibfk_1` FOREIGN KEY (`ID_Utilisateur`) REFERENCES `utilisateur` (`ID`),
  ADD CONSTRAINT `etudiant_ibfk_2` FOREIGN KEY (`ID_Groupe`) REFERENCES `groupe` (`ID`);

--
-- Contraintes pour la table `groupe`
--
ALTER TABLE `groupe`
  ADD CONSTRAINT `groupe_ibfk_1` FOREIGN KEY (`ID_Promotion`) REFERENCES `promotion` (`ID`);

--
-- Contraintes pour la table `salle`
--
ALTER TABLE `salle`
  ADD CONSTRAINT `salle_ibfk_1` FOREIGN KEY (`ID_Site`) REFERENCES `site` (`ID`);

--
-- Contraintes pour la table `seance`
--
ALTER TABLE `seance`
  ADD CONSTRAINT `seance_ibfk_1` FOREIGN KEY (`ID_Cours`) REFERENCES `cours` (`ID`),
  ADD CONSTRAINT `seance_ibfk_2` FOREIGN KEY (`ID_Type`) REFERENCES `type_cours` (`ID`);

--
-- Contraintes pour la table `seance_enseignants`
--
ALTER TABLE `seance_enseignants`
  ADD CONSTRAINT `seance_enseignants_ibfk_1` FOREIGN KEY (`ID_Seance`) REFERENCES `seance` (`ID`) ON DELETE CASCADE,
  ADD CONSTRAINT `seance_enseignants_ibfk_2` FOREIGN KEY (`ID_Enseignant`) REFERENCES `enseignant` (`ID_Utilisateur`) ON DELETE CASCADE;

--
-- Contraintes pour la table `seance_groupes`
--
ALTER TABLE `seance_groupes`
  ADD CONSTRAINT `seance_groupes_ibfk_1` FOREIGN KEY (`ID_Groupe`) REFERENCES `groupe` (`ID`) ON DELETE CASCADE,
  ADD CONSTRAINT `seance_groupes_ibfk_2` FOREIGN KEY (`ID_Seance`) REFERENCES `seance` (`ID`) ON DELETE CASCADE;

--
-- Contraintes pour la table `seance_salles`
--
ALTER TABLE `seance_salles`
  ADD CONSTRAINT `seance_salles_ibfk_1` FOREIGN KEY (`ID_Salle`) REFERENCES `salle` (`ID`) ON DELETE CASCADE,
  ADD CONSTRAINT `seance_salles_ibfk_2` FOREIGN KEY (`ID_Seance`) REFERENCES `seance` (`ID`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
