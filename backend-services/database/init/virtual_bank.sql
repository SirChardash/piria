CREATE SCHEMA `virtual_bank`;
USE `virtual_bank`;

CREATE TABLE `account`
(
    `id`             int          NOT NULL AUTO_INCREMENT,
    `account_number` char(16)     NOT NULL,
    `owner`          varchar(500) NOT NULL,
    `balance`        decimal      NOT NULL DEFAULT 0,
    PRIMARY KEY (`id`)
);

CREATE TABLE `credit_card`
(
    `id`                 int                                             NOT NULL AUTO_INCREMENT,
    `account_id`         int                                             NOT NULL,
    `card_type`          ENUM ('VISA', 'MASTERCARD', 'AMERICAN_EXPRESS') NOT NULL,
    `card_number`        char(16)                                        NOT NULL,
    `first_name`         varchar(100)                                    NOT NULL,
    `last_name`          varchar(100)                                    NOT NULL,
    `expiration_date`    char(5)                                         NOT NULL,
    `security_code_hash` varchar(100)                                    NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`account_id`) REFERENCES account (`id`)
);

CREATE TABLE `payment`
(
    `id`       int     NOT NULL AUTO_INCREMENT,
    `amount`   decimal NOT NULL,
    `time`     datetime DEFAULT NOW(),
    `card_id`  int     NOT NULL,
    `in_favor` int     NOT NULL,
    `reference_number` varchar(22),
    PRIMARY KEY (`id`),
    FOREIGN KEY (card_id) REFERENCES credit_card (id),
    FOREIGN KEY (in_favor) REFERENCES account (id)
);

CREATE USER 'virtual_bank_user'@'%' IDENTIFIED BY 'heckingsecure';
GRANT SELECT, UPDATE, INSERT, DELETE ON virtual_bank.* TO 'virtual_bank_user'@'%';
FLUSH PRIVILEGES;

INSERT INTO account (id, account_number, owner, balance)
VALUES  (1, '3205732519283123', 'Spinner''s public transaction account', 0),
        (2, '1206830186229973', 'Nikola Karnstein', 420),
        (3, '1348095612385802', 'Oliver Collins', 20),
        (4, '1348056140560164', 'Jack Rutherford', 65),
        (5, '1540973654714509', 'Eva Roux', 1337);

INSERT INTO credit_card (id, account_id, card_type, card_number, first_name, last_name, expiration_date, security_code_hash)
VALUES  (1, 2, 'VISA', '4024007183796514', 'Nikola', 'Karnstein', '12/25', 'BDBm2vIQlSOnSQ1L+tR2baVxmVCitfltGS/AU36E8yo='), # 152
        (2, 3, 'MASTERCARD', '5374605675671807', 'Oliver', 'Collins', '07/22', 's6jg4fmrG/46NvIx9nb3i7MKUZ0rIebFMMDu6Ou0pdA='), # 456
        (3, 4, 'AMERICAN_EXPRESS', '376624574091313', 'Jack', 'Rutherford', '09/20', 'HSjBIFaMEOGbnYq+i2bQmD+j0uEe53UaylD4PG9KQ6o='), # 147
        (4, 5, 'MASTERCARD', '5552849469928387', 'Eva', 'Roux', '04/22', 'kp8ANzGpf5FdEYk8ZlK7x9sLNhGOtDV8xyH39orrJf8='); # 852