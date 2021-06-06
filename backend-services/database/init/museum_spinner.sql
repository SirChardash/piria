CREATE SCHEMA museum_spinner;
USE museum_spinner;

CREATE TABLE language
(
    code varchar(50) NOT NULL,
    PRIMARY KEY (code)
);

CREATE TABLE language_names
(
    language    varchar(50)  NOT NULL,
    in_language varchar(50)  NOT NULL,
    name        varchar(100) NOT NULL,
    PRIMARY KEY (language, in_language)
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

DROP USER IF EXISTS  'museum_user'@'%';
CREATE USER 'museum_user'@'%' IDENTIFIED BY 'heckingsecure';
GRANT SELECT, UPDATE, INSERT, DELETE ON museum_spinner.* TO 'museum_user'@'%';
FLUSH PRIVILEGES;

INSERT INTO museum (id)
VALUES (1);

INSERT INTO language (code)
VALUES ('SR'),
       ('EN');

INSERT INTO language_names(language, in_language, name)
VALUES ('EN', 'EN', 'English'),
       ('EN', 'SR', 'Engleski'),
       ('SR', 'EN', 'Serbian'),
       ('SR', 'SR', 'Srpski');

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
