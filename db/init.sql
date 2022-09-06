--
-- Database: `poc`
--

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE product (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `price` DECIMAL(10,2) DEFAULT NULL,
  `brand` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `product`
--

INSERT INTO product 
VALUES 
	(1, 'Sample product 1', 'Sample description', 15.00, 'Sample brand 1'),
	(2, 'Sample product 2', 'Sample description', 25.00, 'Sample brand 2'),
	(3, 'Sample product 3', 'Sample description', 35.00, 'Sample brand 3'),
	(4, 'Sample product 4', 'Sample description', 45.00, 'Sample brand 4'),
	(5, 'Sample product 5', 'Sample description', 55.00, 'Sample brand 5');
	
--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `idx_name_unique` (`name`),