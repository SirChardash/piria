CREATE SCHEMA `virtual_bank`;
USE `virtual_bank`;

CREATE TABLE `account`
(
    `id`      int          NOT NULL AUTO_INCREMENT,
    `owner`   varchar(500) NOT NULL,
    `balance` decimal      NOT NULL DEFAULT 0,
    PRIMARY KEY (`id`)
);

CREATE TABLE `credit_card`
(
    `id`              int                                             NOT NULL AUTO_INCREMENT,
    `account_id`      int                                             NOT NULL,
    `card_type`       ENUM ('VISA', 'MASTERCARD', 'AMERICAN_EXPRESS') NOT NULL,
    `card_number`     char(16)                                        NOT NULL,
    `first_name`      varchar(100)                                    NOT NULL,
    `last_name`       varchar(100)                                    NOT NULL,
    `expiration_date` char(5)                                         NOT NULL,
    `security_code`   char(3)                                         NOT NULL,
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

INSERT INTO account (id, owner, balance)
VALUES  (1, 'Spinner''s public transaction account', 0),
        (2, 'Nikola Karnstein', 420),
        (3, 'Oliver Collins', 20),
        (4, 'Jack Rutherford', 65),
        (5, 'Eva Roux', 1337);

INSERT INTO credit_card (id, account_id, card_type, card_number, first_name, last_name, expiration_date, security_code)
VALUES  (1, 2, 'VISA', '4024007183796514', 'Nikola', 'Karnstein', '12/25', '152'),
        (2, 3, 'MASTERCARD', '5374605675671807', 'Oliver', 'Collins', '07/22', '456'),
        (3, 4, 'AMERICAN_EXPRESS', '376624574091313', 'Jack', 'Rutherford', '09/20', '147'),
        (4, 5, 'MASTERCARD', '5552849469928387', 'Eva', 'Roux', '04/22', '852');