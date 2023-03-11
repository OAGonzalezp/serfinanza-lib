-- CREATE SCHEMA `serfinanza-book` ;
-- `serfinanza-book`.bk_book definition

CREATE TABLE `bk_book` (
   `id` bigint NOT NULL AUTO_INCREMENT,
   `book_name` varchar(255) NOT NULL,
   `idlib` varchar(255) NOT NULL,
   `last_taken_date` datetime(6) DEFAULT NULL,
   `status` varchar(255) DEFAULT NULL,
   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- `serfinanza-book`.bk_customer definition

CREATE TABLE `bk_customer` (
   `id` bigint NOT NULL AUTO_INCREMENT,
   `date_of_birth` datetime(6) NOT NULL,
   `first_name` varchar(255) NOT NULL,
   `identification` varchar(255) NOT NULL,
   `last_name` varchar(255) NOT NULL,
   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- `serfinanza-book`.bk_taken_books definition

CREATE TABLE `bk_taken_books` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `delivery_date` datetime(6) NOT NULL,
  `taken_date` datetime(6) NOT NULL,
  `book_id` bigint DEFAULT NULL,
  `customer_id` bigint DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `book_id_fk` (`book_id`),
  KEY `customer_id_fk` (`customer_id`),
  CONSTRAINT `book_id_fk` FOREIGN KEY (`book_id`) REFERENCES `bk_book` (`id`),
  CONSTRAINT `customer_id_fk` FOREIGN KEY (`customer_id`) REFERENCES `bk_customer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
