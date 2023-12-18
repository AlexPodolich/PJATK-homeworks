    -- const authUtil = require('../../utils/authUtils');
    -- const passHash = authUtil.hashPassword('12345');

    CREATE SCHEMA IF NOT EXISTS `tin-project`;

    CREATE TABLE IF NOT EXISTS `tin-project`. `Resident`
        (`_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
        `first_name` varchar(50) NOT NULL,
        `last_name` varchar(50) NOT NULL,
        `phone_number` varchar(50) NOT NULL,
        `password` varchar(100) NOT NULL,
        PRIMARY KEY (`_id`),
        UNIQUE INDEX `res_id_UNIQUE` (`_id` ASC)
        ) ENGINE=InnoDB CHARSET=utf8 COLLATE=utf8_general_ci;

    CREATE TABLE IF NOT EXISTS `tin-project`. `Room`
        (`_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
        `number` varchar(50) NOT NULL,
        `price` decimal(10,2) UNSIGNED NOT NULL,
        PRIMARY KEY (`_id`),
        UNIQUE INDEX `room_id_UNIQUE` (`_id` ASC)
        ) ENGINE=InnoDB CHARSET=utf8 COLLATE=utf8_general_ci;

    CREATE TABLE IF NOT EXISTS `tin-project`. `ReservedRoom`
        (`_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
        `start_date` date NOT NULL,
        `end_date` date NOT NULL,
        `room_id` INT UNSIGNED NOT NULL,
        `res_id` INT UNSIGNED NOT NULL,
        PRIMARY KEY (`_id`),
        UNIQUE INDEX `reserved_room_id_UNIQUE` (`_id` ASC),
        CONSTRAINT `room_fk` FOREIGN KEY (`room_id`) REFERENCES `tin-project`.`Room` (`_id`),
        CONSTRAINT `res_fk` FOREIGN KEY (`res_id`) REFERENCES `tin-project`.`Resident` (`_id`)
        ) ENGINE=InnoDB CHARSET=utf8 COLLATE=utf8_general_ci;

    INSERT IGNORE INTO `tin-project`. `Resident` (`_id`, `first_name`, `last_name`, `phone_number`, `password`) VALUES
        (1, 'Alex', 'Podolich', '0675329590', '$2a$08$fULcdbYHCF/s1EYswAusj.UHgdjui7kj3j0lEWpoS1V0Kw1tzCLHy'),
        (2, 'Artem', 'Sydorovych', '0985347172', '$2a$08$fULcdbYHCF/s1EYswAusj.DY2vnEGzd6RvnoZ.kMW0s8PtUOt0Pna'),
        (3, 'Azamat', 'Maraimbekov', '0951238485', '$2a$08$fULcdbYHCF/s1EYswAusj.aiLBcKkr.rB9bjGRUAKHZZuJ9D1/Il.'),
        (4, 'Main', 'Admin', '0675328180', '$2a$08$fULcdbYHCF/s1EYswAusj.ZGb2m5hZBWmg.rX9BEeXndY.TN5BvRi');

    INSERT IGNORE INTO `tin-project`. `Room` (`_id`, `number`, `price`) VALUES
        (1, '204B', '2050'),
        (2, '501VIP', '4080'),
        (3, '322A', '3200');

    INSERT IGNORE INTO `tin-project`. `ReservedRoom` (`_id`, `start_date`, `end_date`, `room_id`, `res_id`) VALUES
        (1, '2023-01-01', '2023-02-01', 1,1),
        (2, '2023-02-02', '2023-03-02', 1,2),
        (3, '2023-04-01', '2023-05-01', 2,2),
        (4, '2023-05-10', '2023-10-10', 3,3),
        (5, '2023-10-10', '2023-12-10', 3,3);