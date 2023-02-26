-- `serfinanza-book`.bk_book definition

CREATE TABLE `bk_book` (
   `id` bigint(20) NOT NULL AUTO_INCREMENT,
   `book_name` varchar(255) DEFAULT NULL,
   `idlib` varchar(255) DEFAULT NULL,
   `status` varchar(255) DEFAULT NULL,
   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- `serfinanza-book`.bk_customer definition

CREATE TABLE `bk_customer` (
   `id` bigint(20) NOT NULL AUTO_INCREMENT,
   `date_of_birth` datetime(6) NOT NULL,
   `first_name` varchar(255) NOT NULL,
   `identification` varchar(255) NOT NULL,
   `last_name` varchar(255) NOT NULL,
   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- `serfinanza-book`.bk_taken_books definition

CREATE TABLE `bk_taken_books` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `question_str` varchar(255) DEFAULT NULL,
  `book_id` bigint(20) DEFAULT NULL,
  `customer_id` bigint(20) DEFAULT NULL,
  `customer_feedback_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `book_id_fk` (`book_id`),
  KEY `customer_id_fk` (`customer_id`),
  KEY `customer_feedback_id_fk` (`customer_feedback_id`),
  CONSTRAINT `book_id_fk` FOREIGN KEY (`book_id`) REFERENCES `bk_book` (`id`),
  CONSTRAINT `customer_id_fk` FOREIGN KEY (`customer_id`) REFERENCES `bk_customer` (`id`),
  CONSTRAINT `customer_feedback_id_fk` FOREIGN KEY (`customer_feedback_id`) REFERENCES `bk_book` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
