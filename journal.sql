-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Sep 13, 2017 at 02:50 PM
-- Server version: 10.1.19-MariaDB
-- PHP Version: 5.5.38

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `journal`
--

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

CREATE TABLE `categories` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`id`, `name`) VALUES
(1, 'Sports'),
(2, 'Technology'),
(3, 'Security'),
(4, 'Literature'),
(5, 'Music');

-- --------------------------------------------------------

--
-- Table structure for table `comments`
--

CREATE TABLE `comments` (
  `id` bigint(20) NOT NULL,
  `post_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `opinion` text NOT NULL,
  `published` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `comments`
--

INSERT INTO `comments` (`id`, `post_id`, `user_id`, `opinion`, `published`) VALUES
(3, 5, 2, 'test istest', '2017-06-07 10:21:47');

-- --------------------------------------------------------

--
-- Table structure for table `posts`
--

CREATE TABLE `posts` (
  `id` bigint(20) NOT NULL,
  `author_id` bigint(20) NOT NULL,
  `category_id` bigint(20) NOT NULL,
  `picture` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL,
  `body` text NOT NULL,
  `published` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `visibility` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `posts`
--

INSERT INTO `posts` (`id`, `author_id`, `category_id`, `picture`, `title`, `body`, `published`, `visibility`) VALUES
(1, 2, 1, 'picture.jpg', 'What is Lorem Ipsum?', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry''s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.', '2017-05-10 01:02:35', 1),
(2, 2, 3, 'picture.jpg', 'Why do we use it?', 'It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using ''Content here, content here'', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for ''lorem ipsum'' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).', '2017-05-10 01:03:31', 1),
(3, 3, 4, 'picture.jpg', 'Where can I get some?', 'There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don''t look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn''t anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc.', '2017-05-10 01:04:15', 1),
(4, 3, 5, 'picture.jpg', 'Where does it come from?', 'Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of "de Finibus Bonorum et Malorum" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, "Lorem ipsum dolor sit amet..", comes from a line in section 1.10.32.', '2017-05-10 01:05:05', 1),
(5, 2, 1, 'picture.jpg', 'Test is a test', 'Lorem ipsum dolor sit amet, mollis postulant ex mel. Has an offendit liberavisse, eu nusquam eligendi ocurreret vis. Ea sea ludus latine. Id quem ubique est, eam primis scripserit vituperatoribus ad, aliquam abhorreant per id. Eos errem praesent necessitatibus cu. Porro omittantur sed ex, ei qui blandit iracundia omittantur.\r\n\r\nEi sit rebum homero alterum, feugait insolens te quo, cu his eius nulla. Ad placerat evertitur eos. An est reque animal ancillae, admodum delicata repudiare mel te. Labore melius duo ad, audiam invenire eos ut. Sit harum tibique officiis ex.\r\n\r\nIgnota oblique pertinax vix cu, doming officiis philosophia an per. Est eros eripuit periculis et, sed simul scripta offendit ut. Mei stet magna option et, mea affert impedit cu. Mel elitr honestatis ei, stet quando euismod no eam, eu nam aliquip appareat deseruisse.\r\n\r\nIn stet nonumes cum, ea pro nibh solum minimum. Eam magna invidunt dissentiet ut. No vis dolor corrumpit. Has ea aperiam instructior, est in simul detraxit reprehendunt, choro deserunt ius ad. Ei sit accusata interesset intellegebat, mel ei alterum eruditi assueverit.\r\n\r\nQuo ne ullum aperiri sententiae, eros augue ubique mel ex. Vel ad latine gloriatur dissentias, virtute civibus appellantur duo an. Porro sonet principes nam ei, vide splendide liberavisse ut sit. Cu mazim utamur oblique eum. Sonet offendit id qui. Dolor mediocrem splendide ex eum, ut dolore aliquip labitur eos.', '2017-05-25 20:56:12', 1),
(6, 2, 2, 'picture.jpg', 'Best of the Best', 'Lorem ipsum dolor sit amet, mollis postulant ex mel. Has an offendit liberavisse, eu nusquam eligendi ocurreret vis. Ea sea ludus latine. Id quem ubique est, eam primis scripserit vituperatoribus ad, aliquam abhorreant per id. Eos errem praesent necessitatibus cu. Porro omittantur sed ex, ei qui blandit iracundia omittantur.\r\n\r\nEi sit rebum homero alterum, feugait insolens te quo, cu his eius nulla. Ad placerat evertitur eos. An est reque animal ancillae, admodum delicata repudiare mel te. Labore melius duo ad, audiam invenire eos ut. Sit harum tibique officiis ex.\r\n\r\nIgnota oblique pertinax vix cu, doming officiis philosophia an per. Est eros eripuit periculis et, sed simul scripta offendit ut. Mei stet magna option et, mea affert impedit cu. Mel elitr honestatis ei, stet quando euismod no eam, eu nam aliquip appareat deseruisse.\r\n\r\nIn stet nonumes cum, ea pro nibh solum minimum. Eam magna invidunt dissentiet ut. No vis dolor corrumpit. Has ea aperiam instructior, est in simul detraxit reprehendunt, choro deserunt ius ad. Ei sit accusata interesset intellegebat, mel ei alterum eruditi assueverit.\r\n\r\nQuo ne ullum aperiri sententiae, eros augue ubique mel ex. Vel ad latine gloriatur dissentias, virtute civibus appellantur duo an. Porro sonet principes nam ei, vide splendide liberavisse ut sit. Cu mazim utamur oblique eum. Sonet offendit id qui. Dolor mediocrem splendide ex eum, ut dolore aliquip labitur eos.', '2017-06-16 16:28:46', 1),
(7, 3, 4, 'picture.jpg', 'Man are stupid', 'Lorem ipsum dolor sit amet, mollis postulant ex mel. Has an offendit liberavisse, eu nusquam eligendi ocurreret vis. Ea sea ludus latine. Id quem ubique est, eam primis scripserit vituperatoribus ad, aliquam abhorreant per id. Eos errem praesent necessitatibus cu. Porro omittantur sed ex, ei qui blandit iracundia omittantur.\r\n\r\nEi sit rebum homero alterum, feugait insolens te quo, cu his eius nulla. Ad placerat evertitur eos. An est reque animal ancillae, admodum delicata repudiare mel te. Labore melius duo ad, audiam invenire eos ut. Sit harum tibique officiis ex.\r\n\r\nIgnota oblique pertinax vix cu, doming officiis philosophia an per. Est eros eripuit periculis et, sed simul scripta offendit ut. Mei stet magna option et, mea affert impedit cu. Mel elitr honestatis ei, stet quando euismod no eam, eu nam aliquip appareat deseruisse.\r\n\r\nIn stet nonumes cum, ea pro nibh solum minimum. Eam magna invidunt dissentiet ut. No vis dolor corrumpit. Has ea aperiam instructior, est in simul detraxit reprehendunt, choro deserunt ius ad. Ei sit accusata interesset intellegebat, mel ei alterum eruditi assueverit.\r\n\r\nQuo ne ullum aperiri sententiae, eros augue ubique mel ex. Vel ad latine gloriatur dissentias, virtute civibus appellantur duo an. Porro sonet principes nam ei, vide splendide liberavisse ut sit. Cu mazim utamur oblique eum. Sonet offendit id qui. Dolor mediocrem splendide ex eum, ut dolore aliquip labitur eos.', '2017-06-16 16:29:17', 1),
(8, 3, 2, 'picture.jpg', 'Hello my friend!', 'Lorem ipsum dolor sit amet, mollis postulant ex mel. Has an offendit liberavisse, eu nusquam eligendi ocurreret vis. Ea sea ludus latine. Id quem ubique est, eam primis scripserit vituperatoribus ad, aliquam abhorreant per id. Eos errem praesent necessitatibus cu. Porro omittantur sed ex, ei qui blandit iracundia omittantur.\r\n\r\nEi sit rebum homero alterum, feugait insolens te quo, cu his eius nulla. Ad placerat evertitur eos. An est reque animal ancillae, admodum delicata repudiare mel te. Labore melius duo ad, audiam invenire eos ut. Sit harum tibique officiis ex.\r\n\r\nIgnota oblique pertinax vix cu, doming officiis philosophia an per. Est eros eripuit periculis et, sed simul scripta offendit ut. Mei stet magna option et, mea affert impedit cu. Mel elitr honestatis ei, stet quando euismod no eam, eu nam aliquip appareat deseruisse.\r\n\r\nIn stet nonumes cum, ea pro nibh solum minimum. Eam magna invidunt dissentiet ut. No vis dolor corrumpit. Has ea aperiam instructior, est in simul detraxit reprehendunt, choro deserunt ius ad. Ei sit accusata interesset intellegebat, mel ei alterum eruditi assueverit.\r\n\r\nQuo ne ullum aperiri sententiae, eros augue ubique mel ex. Vel ad latine gloriatur dissentias, virtute civibus appellantur duo an. Porro sonet principes nam ei, vide splendide liberavisse ut sit. Cu mazim utamur oblique eum. Sonet offendit id qui. Dolor mediocrem splendide ex eum, ut dolore aliquip labitur eos.', '2017-06-16 16:29:45', 1),
(9, 3, 1, 'picture.jpg', 'Toast Makers!', 'Lorem ipsum dolor sit amet, mollis postulant ex mel. Has an offendit liberavisse, eu nusquam eligendi ocurreret vis. Ea sea ludus latine. Id quem ubique est, eam primis scripserit vituperatoribus ad, aliquam abhorreant per id. Eos errem praesent necessitatibus cu. Porro omittantur sed ex, ei qui blandit iracundia omittantur.\r\n\r\nEi sit rebum homero alterum, feugait insolens te quo, cu his eius nulla. Ad placerat evertitur eos. An est reque animal ancillae, admodum delicata repudiare mel te. Labore melius duo ad, audiam invenire eos ut. Sit harum tibique officiis ex.\r\n\r\nIgnota oblique pertinax vix cu, doming officiis philosophia an per. Est eros eripuit periculis et, sed simul scripta offendit ut. Mei stet magna option et, mea affert impedit cu. Mel elitr honestatis ei, stet quando euismod no eam, eu nam aliquip appareat deseruisse.\r\n\r\nIn stet nonumes cum, ea pro nibh solum minimum. Eam magna invidunt dissentiet ut. No vis dolor corrumpit. Has ea aperiam instructior, est in simul detraxit reprehendunt, choro deserunt ius ad. Ei sit accusata interesset intellegebat, mel ei alterum eruditi assueverit.\r\n\r\nQuo ne ullum aperiri sententiae, eros augue ubique mel ex. Vel ad latine gloriatur dissentias, virtute civibus appellantur duo an. Porro sonet principes nam ei, vide splendide liberavisse ut sit. Cu mazim utamur oblique eum. Sonet offendit id qui. Dolor mediocrem splendide ex eum, ut dolore aliquip labitur eos.', '2017-06-16 16:30:15', 1),
(10, 6, 3, 'picture.jpg', 'Mad Max is on the way', 'Lorem ipsum dolor sit amet, mollis postulant ex mel. Has an offendit liberavisse, eu nusquam eligendi ocurreret vis. Ea sea ludus latine. Id quem ubique est, eam primis scripserit vituperatoribus ad, aliquam abhorreant per id. Eos errem praesent necessitatibus cu. Porro omittantur sed ex, ei qui blandit iracundia omittantur.\r\n\r\nEi sit rebum homero alterum, feugait insolens te quo, cu his eius nulla. Ad placerat evertitur eos. An est reque animal ancillae, admodum delicata repudiare mel te. Labore melius duo ad, audiam invenire eos ut. Sit harum tibique officiis ex.\r\n\r\nIgnota oblique pertinax vix cu, doming officiis philosophia an per. Est eros eripuit periculis et, sed simul scripta offendit ut. Mei stet magna option et, mea affert impedit cu. Mel elitr honestatis ei, stet quando euismod no eam, eu nam aliquip appareat deseruisse.\r\n\r\nIn stet nonumes cum, ea pro nibh solum minimum. Eam magna invidunt dissentiet ut. No vis dolor corrumpit. Has ea aperiam instructior, est in simul detraxit reprehendunt, choro deserunt ius ad. Ei sit accusata interesset intellegebat, mel ei alterum eruditi assueverit.\r\n\r\nQuo ne ullum aperiri sententiae, eros augue ubique mel ex. Vel ad latine gloriatur dissentias, virtute civibus appellantur duo an. Porro sonet principes nam ei, vide splendide liberavisse ut sit. Cu mazim utamur oblique eum. Sonet offendit id qui. Dolor mediocrem splendide ex eum, ut dolore aliquip labitur eos.', '2017-06-16 16:30:54', 1);

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `id` bigint(20) NOT NULL,
  `role` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`id`, `role`) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USER');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(60) NOT NULL,
  `full_name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `full_name`, `email`) VALUES
(1, 'admin', '$2a$10$KQt3y81JrrsQ7AUITrAmYOpm5d3vBXY2Sl0MmH2wcxckiW79LjP5G', 'Jean Claude Van Damme', 'admin@admin.com'),
(2, 'max', '$2a$10$CAQVvV0n8iYcWJIfQ220n.RYFrTw.tWkhy617FXvCt.uNb12bFnCm', 'max giger', 'max@max.com'),
(3, 'tom', '$2a$10$6RfczhuDBbUOSDcgq9SpO.dXgT/AnsuvaDNs7gpDiS1R6SOhbpUBu', 'tom hardy', 'tom@tom.com'),
(4, 'jack', '$2a$10$p9tlg10GcGSDNbCSZYKM2uI7hA0sPa3VwlPBQ8VlUR.4QVvzJL8Cm', 'jack london', 'jack@jack.com'),
(5, 'mike', '$2a$10$030F3xjO6F2XoRAF55CcveyYm5d9f9oBfxSMk0XgUeVp7p0zv26fK', 'mike jones', 'mike@mike.com'),
(6, 'test', '$2a$10$TzeDs0M8.W/TgEtdAG1fXui2MzAnVcgl2OektLw82TcwFfjN/LygK', 'test', 'test@test.com');

-- --------------------------------------------------------

--
-- Table structure for table `users_roles`
--

CREATE TABLE `users_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users_roles`
--

INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES
(1, 1),
(2, 2),
(3, 2),
(4, 2),
(5, 2),
(6, 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `comments`
--
ALTER TABLE `comments`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK8omq0tc18jd43bu5tjh6jvraq` (`user_id`),
  ADD KEY `FKh4c7lvsc298whoyd4w9ta25cr` (`post_id`);

--
-- Indexes for table `posts`
--
ALTER TABLE `posts`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK6xvn0811tkyo3nfjk2xvqx6ns` (`author_id`),
  ADD KEY `FKijnwr3brs8vaosl80jg9rp7uc` (`category_id`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users_roles`
--
ALTER TABLE `users_roles`
  ADD KEY `FKj6m8fwv7oqv74fcehir1a9ffy` (`role_id`),
  ADD KEY `FK2o0jvgh89lemvvo17cbqvdxaa` (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `categories`
--
ALTER TABLE `categories`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `comments`
--
ALTER TABLE `comments`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `posts`
--
ALTER TABLE `posts`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `comments`
--
ALTER TABLE `comments`
  ADD CONSTRAINT `FK8omq0tc18jd43bu5tjh6jvraq` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FKh4c7lvsc298whoyd4w9ta25cr` FOREIGN KEY (`post_id`) REFERENCES `posts` (`id`);

--
-- Constraints for table `posts`
--
ALTER TABLE `posts`
  ADD CONSTRAINT `FK6xvn0811tkyo3nfjk2xvqx6ns` FOREIGN KEY (`author_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FKijnwr3brs8vaosl80jg9rp7uc` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`);

--
-- Constraints for table `users_roles`
--
ALTER TABLE `users_roles`
  ADD CONSTRAINT `FK2o0jvgh89lemvvo17cbqvdxaa` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FKj6m8fwv7oqv74fcehir1a9ffy` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
