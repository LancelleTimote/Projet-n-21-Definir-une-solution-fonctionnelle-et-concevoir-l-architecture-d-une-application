CREATE TABLE `AGENCIES`(
    `id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(40) NOT NULL,
    `address` VARCHAR(255) NOT NULL,
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE `USERS`(
    `id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `firstname` VARCHAR(40) NOT NULL,
    `lastname` VARCHAR(40) NOT NULL,
    `email` VARCHAR(40) NOT NULL UNIQUE,
    `password` VARCHAR(255) NOT NULL,
    `birthdate` DATE NOT NULL,
    `user_type` ENUM('CUSTOMER', 'CUSTOMER_SUPPORT') NOT NULL,
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE `CUSTOMERS`(
    `customer_id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `user_id` INT NOT NULL UNIQUE,
    `address` VARCHAR(255) NOT NULL,
    FOREIGN KEY (`user_id`) REFERENCES `USERS`(`id`) ON DELETE CASCADE
);

CREATE TABLE `CUSTOMER_SUPPORT`(
    `customer_support_id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `user_id` INT NOT NULL UNIQUE,
    `agency_id` INT NOT NULL,
    FOREIGN KEY (`user_id`) REFERENCES `USERS`(`id`) ON DELETE CASCADE,
    FOREIGN KEY (`agency_id`) REFERENCES `AGENCIES`(`id`)
);

CREATE TABLE `VEHICLES`(
    `id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `category` VARCHAR(255) NOT NULL,
	`vehicle_type` VARCHAR(255) NOT NULL,
	`transmission` VARCHAR(255) NOT NULL,
	`fuel` VARCHAR(255) NOT NULL
);

CREATE TABLE `OFFERS`(
    `id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `agency_id` INT NOT NULL,
    `departure_city` VARCHAR(255) NOT NULL,
    `back_city` VARCHAR(255) NOT NULL,
    `departure_timestamp` TIMESTAMP NOT NULL,
    `back_timestamp` TIMESTAMP NOT NULL,
    `vehicle_id` INT NOT NULL,
    `price` INT NOT NULL,
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (`agency_id`) REFERENCES `AGENCIES`(`id`),
    FOREIGN KEY (`vehicle_id`) REFERENCES `VEHICLES`(`id`)
);

CREATE TABLE `RENTALS`(
    `id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `offer_id` INT NOT NULL,
    `customer_renter_id` INT NOT NULL,
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (`offer_id`) REFERENCES `OFFERS`(`id`),
    FOREIGN KEY (`customer_renter_id`) REFERENCES `CUSTOMERS`(`customer_id`)
);

CREATE TABLE `CONVERSATIONS`(
    `id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `customer_id` INT NOT NULL,
    `customer_support_id` INT NOT NULL,
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (`customer_id`) REFERENCES `CUSTOMERS`(`customer_id`),
    FOREIGN KEY (`customer_support_id`) REFERENCES `CUSTOMER_SUPPORT`(`customer_support_id`)
);

CREATE TABLE `MESSAGES`(
    `id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `customer_id` INT NOT NULL,
    `message` VARCHAR(2000) NOT NULL,
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (`customer_id`) REFERENCES `CUSTOMERS`(`customer_id`)
);

CREATE TABLE `ANSWERS`(
    `id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `message_id` INT NOT NULL,
    `user_id` INT NOT NULL,
    `message` VARCHAR(2000) NOT NULL,
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (`message_id`) REFERENCES `MESSAGES`(`id`),
    FOREIGN KEY (`user_id`) REFERENCES `USERS`(`id`)
);

CREATE TABLE `CHAT`(
    `id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `conversation_id` INT NOT NULL,
    `message_sender_id` INT NOT NULL,
    `message` VARCHAR(2000) NOT NULL,
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (`conversation_id`) REFERENCES `CONVERSATIONS`(`id`),
    FOREIGN KEY (`message_sender_id`) REFERENCES `USERS`(`id`)
);