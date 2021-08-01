CREATE SCHEMA museum_spinner;
USE museum_spinner;

CREATE TABLE language
(
    code varchar(50) NOT NULL,
    PRIMARY KEY (code)
);

CREATE TABLE museum
(
    id int NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (id)
);

CREATE TABLE museum_localized
(
    id              int          NOT NULL AUTO_INCREMENT,
    master_id       int          NOT NULL,
    language        varchar(50)  NOT NULL,
    name            varchar(500) NOT NULL,
    address         text         NOT NULL,
    city            varchar(100) NOT NULL,
    country         varchar(100) NOT NULL,
    phone_number    varchar(100) DEFAULT NULL,
    museum_type     varchar(100) DEFAULT NULL,
    google_location text         DEFAULT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (master_id) REFERENCES museum (id),
    FOREIGN KEY (language) REFERENCES language (code),
    UNIQUE (master_id, language)
);

CREATE TABLE tracking_logs
(
    id          int       NOT NULL AUTO_INCREMENT,
    time        timestamp NOT NULL,
    category    varchar(500) DEFAULT NULL,
    subcategory varchar(500) DEFAULT NULL,
    label       varchar(500) DEFAULT NULL,
    value       varchar(500) DEFAULT NULL,
    PRIMARY KEY (id)
);

DROP USER IF EXISTS 'museum_user'@'%';
CREATE USER 'museum_user'@'%' IDENTIFIED BY 'heckingsecure';
GRANT SELECT, UPDATE, INSERT, DELETE ON museum_spinner.* TO 'museum_user'@'%';
FLUSH PRIVILEGES;

INSERT INTO museum (id)
VALUES (1);

INSERT INTO language (code)
VALUES ('SR'),
       ('EN');

CREATE TABLE `user`
(
    id int NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (id)
);

CREATE TABLE `virtual_tour`
(
    id          int          NOT NULL AUTO_INCREMENT,
    museum_id   int          NOT NULL,
    title       varchar(500) NOT NULL,
    description text         NOT NULL,
    start_time  timestamp    NOT NULL,
    end_time    timestamp    NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (museum_id) REFERENCES museum (id)
);

CREATE TABLE `virtual_tour_attendance`
(
    id             int       NOT NULL,
    tour_id        int       NOT NULL,
    user_id        int       NOT NULL,
    time_confirmed timestamp NOT NULL,
    end_time       timestamp NOT NULL,
    ticket_id      char(16)  NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (tour_id) REFERENCES virtual_tour (id),
    FOREIGN KEY (user_id) REFERENCES user (id)
);

INSERT INTO museum_localized (id, master_id, language, name, address, city, country, phone_number, museum_type,
                              google_location)
VALUES (1, 1, 'EN', 'Anatomy Museum, Faculty of Medicine and Veterinary Medicine',
        'University Medical School Teviot Place, Edinburgh EH8 9AG', 'Edinburgh', 'United Kingdom',
        '(+44)0131 650 3113', 'Medicine',
        'https://www.google.com/maps/search/Anatomy+Museum,+Faculty+of+Medicine+and+Veterinary+Medicine+edinburgh/@55.9471773,-3.20669,15z'),
       (2, 1, 'SR', 'Muzej anatomije, fakultet medicine i veterine',
        'University Medical School Teviot Place, Edinburgh EH8 9AG', 'Edinburgh', 'United Kingdom',
        '(+44)0131 650 3113', 'Medicina',
        'https://www.google.com/maps/search/Anatomy+Museum,+Faculty+of+Medicine+and+Veterinary+Medicine+edinburgh/@55.9471773,-3.20669,15z');
