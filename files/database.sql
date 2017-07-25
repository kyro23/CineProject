-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 25-Jul-2017 às 21:43
-- Versão do servidor: 10.1.9-MariaDB
-- PHP Version: 5.6.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cinema`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `cineroom`
--

CREATE TABLE `cineroom` (
  `id` int(11) NOT NULL,
  `capacity` int(11) NOT NULL,
  `status` varchar(200) NOT NULL,
  `roomNumber` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `cineroom`
--

INSERT INTO `cineroom` (`id`, `capacity`, `status`, `roomNumber`) VALUES
(6, 30, 'Livre', 3),
(7, 25, 'Livre', 4);

-- --------------------------------------------------------

--
-- Estrutura da tabela `film`
--

CREATE TABLE `film` (
  `id` int(11) NOT NULL,
  `title` varchar(200) NOT NULL,
  `actors` varchar(200) NOT NULL,
  `sinopse` text NOT NULL,
  `genere` varchar(200) NOT NULL,
  `duraction` int(11) NOT NULL,
  `directors` varchar(200) NOT NULL,
  `classIndicative` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `film`
--

INSERT INTO `film` (`id`, `title`, `actors`, `sinopse`, `genere`, `duraction`, `directors`, `classIndicative`) VALUES
(4, 'Teste', 'Teste', 'TesteTesteTesteTeste', 'Teste', 123, 'Teste', 'Livre'),
(6, 'Interestellar', 'Seila', 'O filme interestellar é foda	', 'Ficcção Cientìfica', 120, 'Seila', 'Livre');

-- --------------------------------------------------------

--
-- Estrutura da tabela `filmsession`
--

CREATE TABLE `filmsession` (
  `id` int(11) NOT NULL,
  `film` int(11) DEFAULT NULL,
  `day` date DEFAULT NULL,
  `hour` time DEFAULT NULL,
  `room` int(11) DEFAULT NULL,
  `type` enum('Dublado','Legendado') DEFAULT NULL,
  `dimension` enum('2D','3D') DEFAULT NULL,
  `sessionStatus` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `filmsession`
--

INSERT INTO `filmsession` (`id`, `film`, `day`, `hour`, `room`, `type`, `dimension`, `sessionStatus`) VALUES
(30, 6, '2017-07-25', '16:30:00', 6, 'Dublado', '3D', 'Aberta'),
(31, 4, '2017-07-25', '18:30:00', 7, 'Legendado', '2D', 'Aberta');

-- --------------------------------------------------------

--
-- Estrutura da tabela `ticketsonsale`
--

CREATE TABLE `ticketsonsale` (
  `id` int(11) NOT NULL,
  `sessionId` int(11) DEFAULT NULL,
  `priece` double DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `ticketsonsale`
--

INSERT INTO `ticketsonsale` (`id`, `sessionId`, `priece`, `quantity`) VALUES
(5, 31, 15, 25);

-- --------------------------------------------------------

--
-- Estrutura da tabela `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(200) NOT NULL,
  `pass` varchar(200) NOT NULL,
  `privilegeLevel` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `users`
--

INSERT INTO `users` (`id`, `username`, `pass`, `privilegeLevel`) VALUES
(1, 'admin', '21232F297A57A5A743894A0E4A801FC3', '3'),
(4, 'Funcionario', '202CB962AC59075B964B07152D234B70', '2');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cineroom`
--
ALTER TABLE `cineroom`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `roomNumber` (`roomNumber`);

--
-- Indexes for table `film`
--
ALTER TABLE `film`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `filmsession`
--
ALTER TABLE `filmsession`
  ADD PRIMARY KEY (`id`),
  ADD KEY `film` (`film`),
  ADD KEY `room` (`room`);

--
-- Indexes for table `ticketsonsale`
--
ALTER TABLE `ticketsonsale`
  ADD PRIMARY KEY (`id`),
  ADD KEY `sessionId` (`sessionId`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cineroom`
--
ALTER TABLE `cineroom`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `film`
--
ALTER TABLE `film`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `filmsession`
--
ALTER TABLE `filmsession`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;
--
-- AUTO_INCREMENT for table `ticketsonsale`
--
ALTER TABLE `ticketsonsale`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `filmsession`
--
ALTER TABLE `filmsession`
  ADD CONSTRAINT `filmsession_ibfk_1` FOREIGN KEY (`film`) REFERENCES `film` (`id`),
  ADD CONSTRAINT `filmsession_ibfk_2` FOREIGN KEY (`room`) REFERENCES `cineroom` (`id`);

--
-- Limitadores para a tabela `ticketsonsale`
--
ALTER TABLE `ticketsonsale`
  ADD CONSTRAINT `ticketsonsale_ibfk_1` FOREIGN KEY (`sessionId`) REFERENCES `filmsession` (`id`) ON DELETE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
