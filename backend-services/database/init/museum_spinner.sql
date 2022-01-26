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
VALUES (1),
       (2),
       (3),
       (4),
       (5),
       (6),
       (7),
       (8),
       (9),
       (10),
       (11),
       (12),
       (13),
       (14),
       (15),
       (16),
       (17),
       (18),
       (19),
       (20),
       (21),
       (22),
       (23),
       (24),
       (25),
       (26),
       (27),
       (28),
       (29),
       (30),
       (31),
       (32),
       (33),
       (34),
       (35),
       (36),
       (37),
       (38),
       (39),
       (40),
       (41),
       (42),
       (43),
       (44),
       (45),
       (46),
       (47),
       (48),
       (49),
       (50);

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

INSERT INTO museum_localized (master_id, language, name, address, city, country, phone_number, museum_type,
                              google_location)
VALUES (1, 'EN', 'Skara Brae', 'KW16 3LR', 'Stromnes', 'Scotland', '+441856841815', 'Archeology', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2051.9945648741905!2d-3.3382058839390094!3d59.048803381561534!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x489af7efe4ff0be7%3A0xbf7b11dad5095a5b!2sSkara%20Brae%20Visitor%20Centre!5e0!3m2!1sen!2sba!4v1642962924927!5m2!1sen!2sba'),
       (1, 'SR', 'Skara Brae', 'KW16 3LR', 'Stromnes', 'Škotska', '+441856841815', 'Arheologija', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2051.9945648741905!2d-3.3382058839390094!3d59.048803381561534!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x489af7efe4ff0be7%3A0xbf7b11dad5095a5b!2sSkara%20Brae%20Visitor%20Centre!5e0!3m2!1sen!2sba!4v1642962924927!5m2!1sen!2sba'),
       (2, 'EN', 'Pier Arts Centre', 'KW16 3AA', 'Stromness', 'Scotland', '+441856850209', 'Art', 'https://www.google.com/maps/embed?pb=!1m14!1m8!1m3!1d2051.9945637730493!2d-3.3382059!3d59.0488034!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x489afa0422c6a17b%3A0xdd341659b0f37814!2sPier%20Arts%20Centre!5e0!3m2!1sen!2sba!4v1642963000856!5m2!1sen!2sba'),
       (2, 'SR', 'Pijer centar za umjetnost', 'KW16 3AA', 'Stromnes', 'Škotska', '+441856850209', 'Umjetnost', 'https://www.google.com/maps/embed?pb=!1m14!1m8!1m3!1d2051.9945637730493!2d-3.3382059!3d59.0488034!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x489afa0422c6a17b%3A0xdd341659b0f37814!2sPier%20Arts%20Centre!5e0!3m2!1sen!2sba!4v1642963000856!5m2!1sen!2sba'),
       (3, 'EN', 'Orkney Wireless Museum', 'KW15 1DH', 'Kirkwall', 'Scotland', '+441856873191', 'Technology', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2056.02152116414!2d-2.963132283941724!3d58.981348681538385!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x489b0163cc5fb9f1%3A0xe0572be8bf9cada9!2sThe%20Orkney%20Museum!5e0!3m2!1sen!2sba!4v1642963057022!5m2!1sen!2sba'),
       (3, 'SR', 'Orknej bežični muzej', 'KW15 1DH', 'Kerkval', 'Škotska', '+441856873191', 'Tehnologija', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2056.02152116414!2d-2.963132283941724!3d58.981348681538385!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x489b0163cc5fb9f1%3A0xe0572be8bf9cada9!2sThe%20Orkney%20Museum!5e0!3m2!1sen!2sba!4v1642963057022!5m2!1sen!2sba'),
       (4, 'EN', 'Hackness Martello Tower and Battery', 'KW16 3PQ', 'South Walls', 'Scotland', '+441856701727', 'Military', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2066.5181715797994!2d-3.1508488839488122!3d58.805296381478534!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x489ae40634f41811%3A0xf413ce67aa6b6d0e!2sHackness%20Martello%20Tower%20and%20Battery!5e0!3m2!1sen!2sba!4v1642963203032!5m2!1sen!2sba'),
       (4, 'SR', 'Haknes Martelo toranj i artiljerija', 'KW16 3PQ', 'Južni zidovi', 'Škotska', '+441856701727', 'Vojska', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2066.5181715797994!2d-3.1508488839488122!3d58.805296381478534!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x489ae40634f41811%3A0xf413ce67aa6b6d0e!2sHackness%20Martello%20Tower%20and%20Battery!5e0!3m2!1sen!2sba!4v1642963203032!5m2!1sen!2sba'),
       (5, 'EN', 'Burns Cottage', 'Alloway', 'Ayr KA7 4PY', 'Scotland', '+441292443700', 'History', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2263.712841973247!2d-4.635598884081857!3d55.432801480469!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x4889d6719946bff7%3A0x1e640c47aa303e16!2sBurns%20Cottage!5e0!3m2!1sen!2sba!4v1642963248233!5m2!1sen!2sba'),
       (5, 'SR', 'Koliba Burns', 'Alovej', 'Ayr KA7 4PY', 'Škotska', '+441292443700', 'Istorija', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2263.712841973247!2d-4.635598884081857!3d55.432801480469!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x4889d6719946bff7%3A0x1e640c47aa303e16!2sBurns%20Cottage!5e0!3m2!1sen!2sba!4v1642963248233!5m2!1sen!2sba'),
       (6, 'EN', 'Torosay Castle', 'PA64 6AP', 'Isle of Mull', 'Scotland', '+441680812309', 'History', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2204.672408234015!2d-5.657388384042025!3d56.45617628074729!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x488be00c6de9aaab%3A0x1d2b51bbc3088c26!2sDuart%20Castle!5e0!3m2!1sen!2sba!4v1642963295089!5m2!1sen!2sba'),
       (6, 'SR', 'Dvorac Torosej', 'PA64 6AP', 'Ostrvo Mul', 'Škotska', '+441680812309', 'Istorija', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2204.672408234015!2d-5.657388384042025!3d56.45617628074729!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x488be00c6de9aaab%3A0x1d2b51bbc3088c26!2sDuart%20Castle!5e0!3m2!1sen!2sba!4v1642963295089!5m2!1sen!2sba'),
       (7, 'EN', 'The Devil''s Porridge Museum', 'DG12 6TF', 'Eastriggs', 'Scotland', '+441461700021', 'History', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2289.3044114785175!2d-3.172093684099134!3d54.985296580355076!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x487d3b8b3fb06589%3A0xf8f239ead5c75683!2sThe%20Devils%20Porridge!5e0!3m2!1sen!2sba!4v1642963554247!5m2!1sen!2sba'),
       (7, 'SR', 'Muzej Đavolja kaša', 'DG12 6TF', 'Istrigs', 'Škotska', '+441461700021', 'Istorija', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2289.3044114785175!2d-3.172093684099134!3d54.985296580355076!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x487d3b8b3fb06589%3A0xf8f239ead5c75683!2sThe%20Devils%20Porridge!5e0!3m2!1sen!2sba!4v1642963554247!5m2!1sen!2sba'),
       (8, 'EN', 'Aberdeen Arts Centre', 'AB24 5AA', 'Aberdeen', 'Scotland', '+441224635208', 'Art', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2164.279744370713!2d-2.0961914840147835!3d57.149374980949915!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x48840e3e80ca0557%3A0xf67ca568f18fac51!2sAberdeen%20Arts%20Centre!5e0!3m2!1sen!2sba!4v1642963626905!5m2!1sen!2sba'),
       (8, 'SR', 'Aberdin centar za umjetnost', 'AB24 5AA', 'Aberdin', 'Škotska', '+441224635208', 'Umjetnost', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2164.279744370713!2d-2.0961914840147835!3d57.149374980949915!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x48840e3e80ca0557%3A0xf67ca568f18fac51!2sAberdeen%20Arts%20Centre!5e0!3m2!1sen!2sba!4v1642963626905!5m2!1sen!2sba'),
       (9, 'EN', 'Blairs Museum', '3RX4+H4', 'Blairs', 'Scotland', '+441224863767', 'Religion', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2167.2319201899645!2d-2.1968146840167644!3d57.09889598093473!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x4884109cf44131b7%3A0x86c01d5c129ae0ce!2sBlairs%20Museum!5e0!3m2!1sen!2sba!4v1642963653835!5m2!1sen!2sba'),
       (9, 'SR', 'Blers muzej', '3RX4+H4', 'Blers', 'Škotska', '+441224863767', 'Religija', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2167.2319201899645!2d-2.1968146840167644!3d57.09889598093473!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x4884109cf44131b7%3A0x86c01d5c129ae0ce!2sBlairs%20Museum!5e0!3m2!1sen!2sba!4v1642963653835!5m2!1sen!2sba'),
       (10, 'EN', 'Corgarff Castle', '8m W of Strathdon on the, A939, Strathdon AB36 8YP', 'Corgarff', 'Scotland', '+441975651460', 'History', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2163.4789477657177!2d-3.2364230840142505!3d57.163062780953986!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x4885c14167b1ac35%3A0x737eed58545eeac3!2sCorgarff%20Castle!5e0!3m2!1sen!2sba!4v1642963679741!5m2!1sen!2sba'),
       (10, 'SR', 'Dvorac Korgarf', '13KM zapadno od Stratdona na A939, Stratdon AB36 8YP', 'Korgarf', 'Škotska', '+441975651460', 'Istorija', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2163.4789477657177!2d-3.2364230840142505!3d57.163062780953986!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x4885c14167b1ac35%3A0x737eed58545eeac3!2sCorgarff%20Castle!5e0!3m2!1sen!2sba!4v1642963679741!5m2!1sen!2sba'),
       (11, 'EN', 'Glenesk Folk Museum', 'Glenesk, Brechin DD9 7YT', 'Tarfside', 'Scotland', '+441356648070', 'History', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2178.9125946502963!2d-2.810561684024632!3d56.89888598087536!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x48842aad086ba127%3A0xcbf5b8d527763b91!2sThe%20Glenesk%20Retreat!5e0!3m2!1sen!2sba!4v1642963717274!5m2!1sen!2sba'),
       (11, 'SR', 'Glenesk narodni muzej', 'Glenesk, Brečin DD9 7YT', 'Tarfsajd', 'Škotska', '+441356648070', 'Istorija', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2178.9125946502963!2d-2.810561684024632!3d56.89888598087536!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x48842aad086ba127%3A0xcbf5b8d527763b91!2sThe%20Glenesk%20Retreat!5e0!3m2!1sen!2sba!4v1642963717274!5m2!1sen!2sba'),
       (12, 'EN', 'St Vigeans Sculpture Stones', 'Arbroath DD11 4RB', 'St Vigeans', 'Scotland', '+441241878756', 'Archeology', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2197.664504179419!2d-2.59290438403729!3d56.576837680781765!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x48868d210446adc7%3A0xa2144d8e1bf75b6a!2sSt%20Vigeans%20Sculptured%20Stones%20and%20Museum!5e0!3m2!1sen!2sba!4v1642963735214!5m2!1sen!2sba'),
       (12, 'SR', 'Kamene skulpture Sv. Vigens', 'Arbroat DD11 4RB', 'Sv Vigens', 'Škotska', '+441241878756', 'Arheologija', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2197.664504179419!2d-2.59290438403729!3d56.576837680781765!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x48868d210446adc7%3A0xa2144d8e1bf75b6a!2sSt%20Vigeans%20Sculptured%20Stones%20and%20Museum!5e0!3m2!1sen!2sba!4v1642963735214!5m2!1sen!2sba'),
       (13, 'EN', 'Kilmartin Museum', 'Lochgilphead PA31 8RQ', 'Kilmartin', 'Scotland', '+441546510278', 'Archeology', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2223.3488015739786!2d-5.48893008405463!3d56.13378068065693!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x48897ed1d2bd14f9%3A0x79c104e5f18f552a!2sKilmartin%20Museum!5e0!3m2!1sen!2sba!4v1642963758391!5m2!1sen!2sba'),
       (13, 'SR', 'Kilmartin muzej', 'Lohgilphed PA31 8RQ', 'Kilmartin', 'Škotska', '+441546510278', 'Arheologija', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2223.3488015739786!2d-5.48893008405463!3d56.13378068065693!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x48897ed1d2bd14f9%3A0x79c104e5f18f552a!2sKilmartin%20Museum!5e0!3m2!1sen!2sba!4v1642963758391!5m2!1sen!2sba'),
       (14, 'EN', 'Castle Campbell', '58FG+W4', 'Dollar', 'Scotland', '+441259742408', 'History', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2220.9721957172073!2d-3.676929384053021!3d56.1748734806684!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x48862a9f044023d9%3A0x58ebd8e456336968!2sCastle%20Campbell!5e0!3m2!1sen!2sba!4v1642963779716!5m2!1sen!2sba'),
       (14, 'SR', 'Dvorac Kampbel', '58FG+W4', 'Dolar', 'Škotska', '+441259742408', 'Istorija', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2220.9721957172073!2d-3.676929384053021!3d56.1748734806684!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x48862a9f044023d9%3A0x58ebd8e456336968!2sCastle%20Campbell!5e0!3m2!1sen!2sba!4v1642963779716!5m2!1sen!2sba'),
       (15, 'EN', 'Gracefield Arts Centre', '28 Edinburgh Rd, DG1 1JQ', 'Dumfries', 'Scotland', '+441387262084', 'Art', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2284.2405017350143!2d-3.6097211840957013!3d55.07403948037733!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x4862ca72132d467f%3A0xc8e3243d5a0a1252!2sGracefield%20Arts%20Centre!5e0!3m2!1sen!2sba!4v1642963825167!5m2!1sen!2sba'),
       (15, 'SR', 'Grejsfield centar za umjetnost', 'Ulica Edinburg 28, DG1 1JQ', 'Dumfries', 'Škotska', '+441387262084', 'Umjetnost', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2284.2405017350143!2d-3.6097211840957013!3d55.07403948037733!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x4862ca72132d467f%3A0xc8e3243d5a0a1252!2sGracefield%20Arts%20Centre!5e0!3m2!1sen!2sba!4v1642963825167!5m2!1sen!2sba'),
       (16, 'EN', 'Dundee Contemporary Arts', '152 Nethergarde, DD1 4DY', 'Dundee', 'Scotland', '+441382909900', 'Art', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2204.5996330882112!2d-2.9772373840419726!3d56.45743018074772!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x48865cbfa109cec7%3A0x61fe186f99fa91bc!2sDundee%20Contemporary%20Arts!5e0!3m2!1sen!2sba!4v1642963872516!5m2!1sen!2sba'),
       (16, 'SR', 'Dandi privremena umjetnost', '152 Netergarde, DD1 4DY', 'Dandi', 'Škotska', '+441382909900', 'Umjetnost', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2204.5996330882112!2d-2.9772373840419726!3d56.45743018074772!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x48865cbfa109cec7%3A0x61fe186f99fa91bc!2sDundee%20Contemporary%20Arts!5e0!3m2!1sen!2sba!4v1642963872516!5m2!1sen!2sba'),
       (17, 'EN', 'Mills Observatory', 'Balgay Park, Glamis Rd, DD2 2UB', 'Dundee', 'Scotland', '+441382435967', 'Science', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2204.1607775674006!2d-3.0147569840416897!3d56.464991180749884!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x4886433bc2f1dfab%3A0x452de64f15f1688b!2sMills%20Observatory!5e0!3m2!1sen!2sba!4v1642963898950!5m2!1sen!2sba'),
       (17, 'SR', 'Mils obzervatorij', 'Balgaj park, ulica Glamis, DD2 2UB', 'Dandi', 'Škotska', '+441382435967', 'Nauka', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2204.1607775674006!2d-3.0147569840416897!3d56.464991180749884!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x4886433bc2f1dfab%3A0x452de64f15f1688b!2sMills%20Observatory!5e0!3m2!1sen!2sba!4v1642963898950!5m2!1sen!2sba'),
       (18, 'EN', 'Verdant Works', 'W Henderson''s Wynd, DD1 5BT', 'Dundee', 'Scotland', '+441382309060', 'Industry', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2204.365908558507!2d-2.985665084041792!3d56.461457080748765!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x48865cc7b2613e8f%3A0xf10c58b8aee11738!2sVerdant%20Works!5e0!3m2!1sen!2sba!4v1642963927547!5m2!1sen!2sba'),
       (18, 'SR', 'Verdant Vorks', 'W Hendersonova ulica, DD1 5BT', 'Dandi', 'Škotska', '+441382309060', 'Industrija', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2204.365908558507!2d-2.985665084041792!3d56.461457080748765!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x48865cc7b2613e8f%3A0xf10c58b8aee11738!2sVerdant%20Works!5e0!3m2!1sen!2sba!4v1642963927547!5m2!1sen!2sba'),
       (19, 'EN', 'Dumfries House', 'KA18 2NJ', 'Cumnock', 'Scotland', '+441290425959', 'History', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2262.4075855904894!2d-4.310146684080966!3d55.45556098047484!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x4888285c7c0209a5%3A0xbb95a95acaee65a!2sDumfries%20House%3A%20part%20of%20The%20Prince&#39;s%20Foundation!5e0!3m2!1sen!2sba!4v1642963945860!5m2!1sen!2sba'),
       (19, 'SR', 'Dumfries Kuća', 'KA18 2NJ', 'Kamnok', 'Škotska', '+441290425959', 'Istorija', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2262.4075855904894!2d-4.310146684080966!3d55.45556098047484!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x4888285c7c0209a5%3A0xbb95a95acaee65a!2sDumfries%20House%3A%20part%20of%20The%20Prince&#39;s%20Foundation!5e0!3m2!1sen!2sba!4v1642963945860!5m2!1sen!2sba'),
       (20, 'EN', 'Myreton Motor Museum', 'Longniddry EH32 0PZ', 'Aberlady', 'Scotland', '+4412123425959', 'Transportation', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2230.8192392863325!2d-2.8267693840596575!3d56.00448368062145!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x4887a8b201d26a03%3A0x5bfd221d78078fe3!2sMyreton%20Motor%20Museum!5e0!3m2!1sen!2sba!4v1642963969786!5m2!1sen!2sba'),
       (20, 'SR', 'Majreton muzej motora', 'Longenidraj EH32 0PZ', 'Aberlejdi', 'Škotska', '4412123425959', 'Saobraćaj', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2230.8192392863325!2d-2.8267693840596575!3d56.00448368062145!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x4887a8b201d26a03%3A0x5bfd221d78078fe3!2sMyreton%20Motor%20Museum!5e0!3m2!1sen!2sba!4v1642963969786!5m2!1sen!2sba'),
       (21, 'EN', 'National Museum of Flight', 'East Fortune Airfield, B1347, North Berwick, EH39 5LF', 'East Fortune', 'Scotland', '+443001236789', 'Transportation', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2231.32193921427!2d-2.7220353840600064!3d55.99577598061911!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x4887a800a6438d1b%3A0x977844b1ace86890!2sNational%20Museum%20Of%20Flight!5e0!3m2!1sen!2sba!4v1642964006694!5m2!1sen!2sba'),
       (21, 'SR', 'Nacionalni muzej leta', 'Aerodrom Ist Fortjun, B1347, Sjeverni Bervik, EH39 5LF', 'Istočni Fortjun', 'Škotska', '+443001236789', 'Saobraćaj', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2231.32193921427!2d-2.7220353840600064!3d55.99577598061911!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x4887a800a6438d1b%3A0x977844b1ace86890!2sNational%20Museum%20Of%20Flight!5e0!3m2!1sen!2sba!4v1642964006694!5m2!1sen!2sba'),
       (22, 'EN', 'Prestongrange Industrial Heritage Museum', 'Morrisons Haven, Prestonpans EH32 9RX', 'Prestongrange', 'Scotland', '+441316532904', 'Industry', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2233.862527812889!2d-3.0110883840617086!3d55.95175458060706!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x4887ba563581a70d%3A0xf4ff37377a9aa735!2sPrestongrange%20Museum!5e0!3m2!1sen!2sba!4v1642964036099!5m2!1sen!2sba'),
       (22, 'SR', 'Muzej industrijskog naslijeđa PRestongrandž', 'Morisons Hejven, Prestonpans EH32 9RX', 'Prestongrandž', 'Škotska', '+441316532904', 'Industrija', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2233.862527812889!2d-3.0110883840617086!3d55.95175458060706!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x4887ba563581a70d%3A0xf4ff37377a9aa735!2sPrestongrange%20Museum!5e0!3m2!1sen!2sba!4v1642964036099!5m2!1sen!2sba'),
       (23, 'EN', 'City Art Centre', '2 Market St, EH1 1DE', 'Edinburgh', 'Scotland', '+441315293993', 'Art', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2233.901352379693!2d-3.1915917840617456!3d55.951081680606826!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x4887c7907f516035%3A0x547782aa375b0eb1!2sCity%20Art%20Centre!5e0!3m2!1sen!2sba!4v1642964119556!5m2!1sen!2sba'),
       (23, 'SR', 'Gradski centar umjetnosti', 'Ulica Market 2, EH1 1DE', 'Edinburg', 'Škotska', '+441315293993', 'Umjetnost', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2233.901352379693!2d-3.1915917840617456!3d55.951081680606826!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x4887c7907f516035%3A0x547782aa375b0eb1!2sCity%20Art%20Centre!5e0!3m2!1sen!2sba!4v1642964119556!5m2!1sen!2sba'),
       (24, 'EN', 'Modern Two', '75 Belford Rd, EH4 3DR', 'Edinburgh', 'Scotland', '+441316246200', 'Art', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2233.9162843795175!2d-3.229824784061771!3d55.950822880606786!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x4887c648eae6c6eb%3A0x46ec2c31332c0457!2sScottish%20National%20Gallery%20of%20Modern%20Art%20(Modern%20One)!5e0!3m2!1sen!2sba!4v1642964149016!5m2!1sen!2sba'),
       (24, 'SR', 'Modern dva', 'Ulica Belford 75, EH4 3DR', 'Edinburg', 'Škotska', '+441316246200', 'Umjetnost', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2233.9162843795175!2d-3.229824784061771!3d55.950822880606786!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x4887c648eae6c6eb%3A0x46ec2c31332c0457!2sScottish%20National%20Gallery%20of%20Modern%20Art%20(Modern%20One)!5e0!3m2!1sen!2sba!4v1642964149016!5m2!1sen!2sba'),
       (25, 'EN', 'Fruitmarket Gallery', '45 Market St, EH1 1DF', 'Edinburgh', 'Scotland', '+441312252383', 'Art', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2233.8882147208233!2d-3.191632084061734!3d55.95130938060692!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x4887c78f99515683%3A0x6b5d0ae5cddc0b8d!2sFruitmarket!5e0!3m2!1sen!2sba!4v1642964176540!5m2!1sen!2sba'),
       (25, 'SR', 'Galerija Frutmarket', 'Ulica Market 45, EH1 1DF', 'Edinburg', 'Škotska', '+441312252383', 'Umjetnost', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2233.8882147208233!2d-3.191632084061734!3d55.95130938060692!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x4887c78f99515683%3A0x6b5d0ae5cddc0b8d!2sFruitmarket!5e0!3m2!1sen!2sba!4v1642964176540!5m2!1sen!2sba'),
       (26, 'EN', 'Gladstone''s Land', '477B Lawnmarket, EH1 2NT', 'Edinburgh', 'Scotland', '+441312265856', 'History', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2233.9955882283534!2d-3.1958579840618073!3d55.949448380606356!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x4887c79a97b6a553%3A0x6dfaf0b865439b23!2sGladstone&#39;s%20Land!5e0!3m2!1sen!2sba!4v1642964194351!5m2!1sen!2sba'),
       (26, 'SR', 'Gladstonova zemlja', '477B Launmarket, EH1 2NT', 'Edinburg', 'Škotska', '+441312265856', 'Istorija', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2233.9955882283534!2d-3.1958579840618073!3d55.949448380606356!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x4887c79a97b6a553%3A0x6dfaf0b865439b23!2sGladstone&#39;s%20Land!5e0!3m2!1sen!2sba!4v1642964194351!5m2!1sen!2sba'),
       (27, 'EN', 'Museum of Childhood (Edinburgh)', '42 High St, EH1 1TG', 'Edinburgh', 'Scotland', '+441315294142', 'Toys', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2233.942767219198!2d-3.1876330840617726!3d55.95036388060662!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x4887c785f7944bd1%3A0xe6eb3ba2da9e2f93!2sMuseum%20of%20Childhood!5e0!3m2!1sen!2sba!4v1642964224021!5m2!1sen!2sba'),
       (27, 'SR', 'Muzej djetinjstva Edingburg', 'Ulica Haj 42, EH1 1TG', 'Edinburg', 'Škotska', '+441315294142', 'Igračke', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2233.942767219198!2d-3.1876330840617726!3d55.95036388060662!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x4887c785f7944bd1%3A0xe6eb3ba2da9e2f93!2sMuseum%20of%20Childhood!5e0!3m2!1sen!2sba!4v1642964224021!5m2!1sen!2sba'),
       (28, 'EN', 'Scottish National Gallery', 'The Mound, EH2 2EL', 'Edinburgh', 'Scotland', '+441316246200', 'Art', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2233.911720546696!2d-3.197874884061751!3d55.95090198060678!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x4887c648eae6c6eb%3A0xc47f9545e742193!2sScottish%20National%20Gallery!5e0!3m2!1sen!2sba!4v1642964242217!5m2!1sen!2sba'),
       (28, 'SR', 'Škotski nacionalni muzej', 'Maund, EH2 2EL', 'Edinburg', 'Škotska', '+441316246200', 'Umjetnost', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2233.911720546696!2d-3.197874884061751!3d55.95090198060678!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x4887c648eae6c6eb%3A0xc47f9545e742193!2sScottish%20National%20Gallery!5e0!3m2!1sen!2sba!4v1642964242217!5m2!1sen!2sba'),
       (29, 'EN', 'Queen''s Gallery, Edinburgh', 'Palace of Holyroodhouse, EH8 8DX', 'Edinburgh', 'Scotland', '+443031237306', 'Art', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2233.812596047558!2d-3.176084684061689!3d55.95261998060727!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x4887b87717a3a003%3A0xd0f067e7bd6cc738!2sThe%20Queen&#39;s%20Gallery%2C%20Palace%20of%20Holyroodhouse!5e0!3m2!1sen!2sba!4v1642964262407!5m2!1sen!2sba'),
       (29, 'SR', 'Kraljičina galerija u Edingburgu', 'Palata Holirudhaus, EH8 8DX', 'Edinburg', 'Škotska', '+443031237306', 'Umjetnost', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2233.812596047558!2d-3.176084684061689!3d55.95261998060727!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x4887b87717a3a003%3A0xd0f067e7bd6cc738!2sThe%20Queen&#39;s%20Gallery%2C%20Palace%20of%20Holyroodhouse!5e0!3m2!1sen!2sba!4v1642964262407!5m2!1sen!2sba'),
       (30, 'EN', 'Royal Scottish Academy Building', 'The Mound, EH2 2EL', 'Edinburgh', 'Scotland', '+441316246110', 'Art', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2233.867109000871!2d-3.1984776840617384!3d55.95167518060702!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x4887c790f227a157%3A0xdcaa0a421b1c44a3!2sThe%20Royal%20Scottish%20Academy!5e0!3m2!1sen!2sba!4v1642964283395!5m2!1sen!2sba'),
       (30, 'SR', 'Zgrada rojalne škotske akademije', 'Maund, EH2 2EL', 'Edinburg', 'Škotska', '+441316246110', 'Umjetnost', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2233.867109000871!2d-3.1984776840617384!3d55.95167518060702!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x4887c790f227a157%3A0xdcaa0a421b1c44a3!2sThe%20Royal%20Scottish%20Academy!5e0!3m2!1sen!2sba!4v1642964283395!5m2!1sen!2sba'),
       (31, 'EN', 'St Cecilia''s Hall', '50 Niddry St, EH1 1LG', 'Edinburgh', 'Scotland', '+441316502600', 'Music', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2234.02016667934!2d-3.188672784061845!3d55.9490223806063!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x4887c785916e0ef9%3A0xffcfa304631b6412!2sSt.%20Cecilia&#39;s%20Hall%2C%20The%20University%20of%20Edinburgh!5e0!3m2!1sen!2sba!4v1642964307412!5m2!1sen!2sba'),
       (31, 'SR', 'Hala Sv. Cecilije', 'Ulica Nidraj 50, EH1 1LG', 'Edinburg', 'Škotska', '+441316502600', 'Muzika', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2234.02016667934!2d-3.188672784061845!3d55.9490223806063!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x4887c785916e0ef9%3A0xffcfa304631b6412!2sSt.%20Cecilia&#39;s%20Hall%2C%20The%20University%20of%20Edinburgh!5e0!3m2!1sen!2sba!4v1642964307412!5m2!1sen!2sba'),
       (32, 'EN', 'Surgeons'' Hall Museum', 'Nicolson St, EH8 9DW', 'Edinburgh', 'Scotland', '+441315271711', 'Medicine', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2234.1567594588155!2d-3.187466784061917!3d55.94665488060556!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x4887c784231b81f9%3A0xdd38e56f5febdafc!2sSurgeons&#39;%20Hall%20Museums!5e0!3m2!1sen!2sba!4v1642964327736!5m2!1sen!2sba'),
       (32, 'SR', 'Hala hirurga', 'Ulica Nikolson, EH8 9DW', 'Edinburg', 'Škotska', '+441315271711', 'Medicina', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2234.1567594588155!2d-3.187466784061917!3d55.94665488060556!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x4887c784231b81f9%3A0xdd38e56f5febdafc!2sSurgeons&#39;%20Hall%20Museums!5e0!3m2!1sen!2sba!4v1642964327736!5m2!1sen!2sba'),
       (33, 'EN', 'Blackness Castle', 'Blackness, Linlithgow EH49 7NH', 'Falkirk', 'Scotland', '+441506834807', 'History', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2230.7296245834077!2d-3.5182851840595983!3d56.00603588062182!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x4887d0c5f2826139%3A0x38310d02755a9828!2sBlackness%20Castle!5e0!3m2!1sen!2sba!4v1642964351320!5m2!1sen!2sba'),
       (33, 'SR', 'Dvorac Bleknes', 'Bleknes, Linlitgou EH49 7NH', 'Falkerk', 'Škotska', '+441506834807', 'Istorija', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2230.7296245834077!2d-3.5182851840595983!3d56.00603588062182!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x4887d0c5f2826139%3A0x38310d02755a9828!2sBlackness%20Castle!5e0!3m2!1sen!2sba!4v1642964351320!5m2!1sen!2sba'),
       (34, 'EN', 'British Golf Museum', 'Bruce Embankment, St Andrews KY16 9AB', 'St Andrews', 'Scotland', '+441334460046', 'Sports', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2211.1814250891935!2d-2.8039484840464133!3d56.343953580715606!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x488657845b24991d%3A0x8139c7da844ce0e4!2sThe%20R%26A%20World%20Golf%20Museum!5e0!3m2!1sen!2sba!4v1642964368083!5m2!1sen!2sba'),
       (34, 'SR', 'Britanski muzej golfa', 'Brus Embankment, Ulica Andrevs KY16 9AB', 'Sv Andrevs', 'Škotska', '+441334460046', 'Sport', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2211.1814250891935!2d-2.8039484840464133!3d56.343953580715606!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x488657845b24991d%3A0x8139c7da844ce0e4!2sThe%20R%26A%20World%20Golf%20Museum!5e0!3m2!1sen!2sba!4v1642964368083!5m2!1sen!2sba'),
       (35, 'EN', 'Dunfermline Abbey', 'St Margaret St, KY12 7PE', 'Dunfermline', 'Scotland', '+441383724586', 'Religion', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2227.0281515252645!2d-3.4656828840570952!3d56.0701236806394!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x4887d221224fc4ab%3A0x933ea569059ac30b!2sDunfermline%20Abbey!5e0!3m2!1sen!2sba!4v1642964385586!5m2!1sen!2sba'),
       (35, 'SR', 'Kapela Dunfermlajn', 'Ulica Sv. Margaret, KY12 7PE', 'Danfermlajn', 'Škotska', '+441383724586', 'Religija', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2227.0281515252645!2d-3.4656828840570952!3d56.0701236806394!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x4887d221224fc4ab%3A0x933ea569059ac30b!2sDunfermline%20Abbey!5e0!3m2!1sen!2sba!4v1642964385586!5m2!1sen!2sba'),
       (36, 'EN', 'Inchcolm Abbey', 'Burntisland KY3 0UA', 'Inchcolm', 'Scotland', '+441316686899', 'Religion', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2229.346849691354!2d-3.303801684058671!3d56.02998308062836!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x4887c94e9359d381%3A0xcb4aa4259e8b0db2!2sInchcolm%20Abbey!5e0!3m2!1sen!2sba!4v1642964402039!5m2!1sen!2sba'),
       (36, 'SR', 'Kapela Inkholm', 'Burntisland KY3 0UA', 'Inkholm', 'Škotska', '+441316686899', 'Religija', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2229.346849691354!2d-3.303801684058671!3d56.02998308062836!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x4887c94e9359d381%3A0xcb4aa4259e8b0db2!2sInchcolm%20Abbey!5e0!3m2!1sen!2sba!4v1642964402039!5m2!1sen!2sba'),
       (37, 'EN', 'Kirkcaldy Galleries', 'War Memorial Gardens, Abbotshall Rd, KY1 1YG', 'Kirkcaldy', 'Scotland', '+441592583206', 'Art', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2224.591788844965!2d-3.168170884055459!3d56.112280880650964!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x4887b5a219497627%3A0x9481397b48938e56!2sKirkcaldy%20Galleries!5e0!3m2!1sen!2sba!4v1642964420187!5m2!1sen!2sba'),
       (37, 'SR', 'Galerija Kerkaldi', 'Vrtovi u spomen rata, Abotšal cesta, KY1 1YG', 'Kerkaldi', 'Škotska', '+441592583206', 'Umjetnost', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2224.591788844965!2d-3.168170884055459!3d56.112280880650964!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x4887b5a219497627%3A0x9481397b48938e56!2sKirkcaldy%20Galleries!5e0!3m2!1sen!2sba!4v1642964420187!5m2!1sen!2sba'),
       (38, 'EN', 'Scottish Vintage Bus Museum', 'M 90 Commerce Park, KY12, 0SJ', 'Lathalmond', 'Scotland', '+441383623380', 'Transportation', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2224.506060888358!2d-3.4546951840554008!3d56.11376388065148!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x4887d2890f3df1fb%3A0x65f2ddd10f76b9d2!2sScottish%20Vintage%20Bus%20Museum!5e0!3m2!1sen!2sba!4v1642964438714!5m2!1sen!2sba'),
       (38, 'SR', 'Škotski muzej starih autobusa', 'M 90 Komerc Park, KY12 0SJ', 'Latalmond', 'Škotska', '+441383623380', 'Saobraćaj', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2224.506060888358!2d-3.4546951840554008!3d56.11376388065148!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x4887d2890f3df1fb%3A0x65f2ddd10f76b9d2!2sScottish%20Vintage%20Bus%20Museum!5e0!3m2!1sen!2sba!4v1642964438714!5m2!1sen!2sba'),
       (39, 'EN', 'Burrell Collection', 'Bellahouston, G43 1AT', 'Glasgow', 'Scotland', '+441383627780', 'Art', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2240.831381230331!2d-4.309818984066408!3d55.83088598057435!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x48884638934620cd%3A0x9130dd41e928eeb6!2sThe%20Burrell%20Collection%2C%20Bellahouston%2C%20Glasgow%20G43%201AT%2C%20UK!5e0!3m2!1sen!2sba!4v1642964460851!5m2!1sen!2sba'),
       (39, 'SR', 'Kolekcija Burel', 'Belahjuston, G43 1AT', 'Glazgov', 'Škotska', '+441383627780', 'Umjetnost', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2240.831381230331!2d-4.309818984066408!3d55.83088598057435!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x48884638934620cd%3A0x9130dd41e928eeb6!2sThe%20Burrell%20Collection%2C%20Bellahouston%2C%20Glasgow%20G43%201AT%2C%20UK!5e0!3m2!1sen!2sba!4v1642964460851!5m2!1sen!2sba'),
       (40, 'EN', 'Glasgow Print Studio', '103 Trongate, G1 5HD', 'Glasgow', 'Scotland', '+441415520704', 'Art', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2239.345793415213!2d-4.248855484065411!3d55.8566666805812!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x488846a3d4367323%3A0x6363dc2c7759bc85!2sGlasgow%20Print%20Studio!5e0!3m2!1sen!2sba!4v1642964477376!5m2!1sen!2sba'),
       (40, 'SR', 'Glazgov studio štampe', '103 Trongejt, G1 5HD', 'Glazgov', 'Škotska', '+441415520704', 'Umjetnost', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2239.345793415213!2d-4.248855484065411!3d55.8566666805812!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x488846a3d4367323%3A0x6363dc2c7759bc85!2sGlasgow%20Print%20Studio!5e0!3m2!1sen!2sba!4v1642964477376!5m2!1sen!2sba'),
       (41, 'EN', 'Glasgow Women''s Library', '23 Landressy St, Bridgeton, G40 1BP', 'Glasgow', 'Scotland', '+441415502267', 'History', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2239.7966398887083!2d-4.229949284065736!3d55.84884358057915!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x488846b17cb412e7%3A0x77a67149f7d3157e!2sGlasgow%20Women&#39;s%20Library!5e0!3m2!1sen!2sba!4v1642964493134!5m2!1sen!2sba'),
       (41, 'SR', 'Ženska biblioteka u Glazgovu', 'Ulica Landresi 23, Bridžton, G40 1BP', 'Glazgov', 'Škotska', '+441415502267', 'Istorija', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2239.7966398887083!2d-4.229949284065736!3d55.84884358057915!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x488846b17cb412e7%3A0x77a67149f7d3157e!2sGlasgow%20Women&#39;s%20Library!5e0!3m2!1sen!2sba!4v1642964493134!5m2!1sen!2sba'),
       (42, 'EN', 'National Piping Center – Museum of Piping', '30-34 McPhater St, G4 0HW', 'Glasgow', 'Scotland', '+441413535551', 'Music', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2238.7472051688346!2d-4.258632984065025!3d55.86705228058407!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x488844217722dbd1%3A0xfd26639b6779fcd!2sThe%20National%20Piping%20Centre!5e0!3m2!1sen!2sba!4v1642964518514!5m2!1sen!2sba'),
       (42, 'SR', 'Nacionalni centar gajdi', 'Ulica MekFater 30-34, G3 0HW', 'Glazgov', 'Škotska', '+441413535551', 'Muzika', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2238.7472051688346!2d-4.258632984065025!3d55.86705228058407!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x488844217722dbd1%3A0xfd26639b6779fcd!2sThe%20National%20Piping%20Centre!5e0!3m2!1sen!2sba!4v1642964518514!5m2!1sen!2sba'),
       (43, 'EN', 'Riverside Museum', '100 Pointhouse Rd, Patrick, G3 8RS', 'Glasgow', 'Scotland', '+441412872720', 'Transportation', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2238.859561345743!2d-4.308394884065081!3d55.86510298058354!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x488845d0f24231ef%3A0xb7cf6d9b3165003!2sRiverside%20Museum!5e0!3m2!1sen!2sba!4v1642964543103!5m2!1sen!2sba'),
       (43, 'SR', 'Riversajd muzej', 'Cesta Pointhaus 100, Patrik, G3 8RS', 'Glazgov', 'Škotska', '+441412872720', 'Saobraćaj', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2238.859561345743!2d-4.308394884065081!3d55.86510298058354!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x488845d0f24231ef%3A0xb7cf6d9b3165003!2sRiverside%20Museum!5e0!3m2!1sen!2sba!4v1642964543103!5m2!1sen!2sba'),
       (44, 'EN', 'Scottish Football Museum', 'Hampden Park, G42 9BA', 'Glasgow', 'Scotland', '+441416166139', 'Sports', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2241.1755566867964!2d-4.253286784066657!3d55.82491208057267!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x488846e69dffdc29%3A0x75afc3d83f6efefd!2sScottish%20Football%20Museum!5e0!3m2!1sen!2sba!4v1642964564131!5m2!1sen!2sba'),
       (44, 'SR', 'Škotski muzej fudbala', 'Park Hampden, G42 9BA', 'Glazgov', 'Škotska', '+441416166139', 'Sport', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2241.1755566867964!2d-4.253286784066657!3d55.82491208057267!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x488846e69dffdc29%3A0x75afc3d83f6efefd!2sScottish%20Football%20Museum!5e0!3m2!1sen!2sba!4v1642964564131!5m2!1sen!2sba'),
       (45, 'EN', 'Cawdor Castle', 'B909. Nairn IV12 5RD', 'Cawdor', 'Scotland', '+441667404401', 'History', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2142.3019658261123!2d-3.9287507839999365!3d57.524274881064116!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x488f7e598fdeb1cf%3A0x87c4f8c52f8474df!2sCawdor%20Castle%20and%20Gardens!5e0!3m2!1sen!2sba!4v1642964579873!5m2!1sen!2sba'),
       (45, 'SR', 'Dvorac Kaudor', 'B909. Nejrn IV12 5RD', 'Kaudor', 'Škotska', '+441667404401', 'Istorija', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2142.3019658261123!2d-3.9287507839999365!3d57.524274881064116!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x488f7e598fdeb1cf%3A0x87c4f8c52f8474df!2sCawdor%20Castle%20and%20Gardens!5e0!3m2!1sen!2sba!4v1642964579873!5m2!1sen!2sba'),
       (46, 'EN', 'Fort George', 'Iverness IV2 7TD', 'Arderseir', 'Scotland', '+441667460232', 'Military', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2138.819315036822!2d-4.073496483997587!3d57.58353878108235!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x487605556d261acb%3A0xe37555b111ae8dd8!2sFort%20George!5e0!3m2!1sen!2sba!4v1642964596588!5m2!1sen!2sba'),
       (46, 'SR', 'Tvrđava Džordž', 'Ivernes IV2 7TD', 'Ardersejr', 'Škotska', '+441667460232', 'Vojska', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2138.819315036822!2d-4.073496483997587!3d57.58353878108235!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x487605556d261acb%3A0xe37555b111ae8dd8!2sFort%20George!5e0!3m2!1sen!2sba!4v1642964596588!5m2!1sen!2sba'),
       (47, 'EN', 'Culloden Battlefield', 'Iverness IV2 5EU', 'Culloden', 'Scotland', '+441463796090', 'Military', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2145.0320649921887!2d-4.0978996840017805!3d57.47778978104964!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x488f7a1afe782713%3A0xd87d3475f1f54627!2sCulloden%20Battlefield!5e0!3m2!1sen!2sba!4v1642964626428!5m2!1sen!2sba'),
       (47, 'SR', 'Bojište Kuloden', 'Ivernes IV2 5EU', 'Kuloden', 'Škotska', '+441463796090', 'Vojska', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2145.0320649921887!2d-4.0978996840017805!3d57.47778978104964!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x488f7a1afe782713%3A0xd87d3475f1f54627!2sCulloden%20Battlefield!5e0!3m2!1sen!2sba!4v1642964626428!5m2!1sen!2sba'),
       (48, 'EN', 'Highland Museum of Childhood', 'Old Victorian Station, Strathpeffer IV14 9DH', 'Strathpeffer', 'Scotland', '+441997421031', 'Toys', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2138.4385133603164!2d-4.536991283997336!3d57.59001648108438!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x488fa73e90dc8aa9%3A0xa4fd8c8be1a679f!2sHighland%20Museum%20of%20Childhood!5e0!3m2!1sen!2sba!4v1642964644176!5m2!1sen!2sba'),
       (48, 'SR', 'Muzej djetinjstva Hajlend', 'Stara Viktorijanska stanica, Stratpefer IV14 9DH', 'Stratpefer', 'Škotska', '+441997421031', 'Igračke', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2138.4385133603164!2d-4.536991283997336!3d57.59001648108438!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x488fa73e90dc8aa9%3A0xa4fd8c8be1a679f!2sHighland%20Museum%20of%20Childhood!5e0!3m2!1sen!2sba!4v1642964644176!5m2!1sen!2sba'),
       (49, 'EN', 'Summerlee, Museum of Scottish Industrial Life', 'Heritage Way, Coatbridge ML5 1QD', 'Motherwell', 'Scotland', '+441236638460', 'Industry', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2238.8494861117924!2d-4.034133084065058!3d55.865277780583526!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x48886bd737c19bed%3A0xcdb9676036eaee16!2sSummerlee%20Museum%20of%20Scottish%20Industrial%20Life!5e0!3m2!1sen!2sba!4v1642964663763!5m2!1sen!2sba'),
       (49, 'SR', 'Muzej škotskog industrijskog života Samerli', 'Heritedž Vej, Koutbridž ML5 1QD', 'Motervel', 'Škotska', '+441236638460', 'Industrija', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2238.8494861117924!2d-4.034133084065058!3d55.865277780583526!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x48886bd737c19bed%3A0xcdb9676036eaee16!2sSummerlee%20Museum%20of%20Scottish%20Industrial%20Life!5e0!3m2!1sen!2sba!4v1642964663763!5m2!1sen!2sba'),
       (50, 'EN', 'Broch of Gurness', 'Aikerness, Evie, KW17 2NH', 'Mainland, Orkney', 'Scotland', '+441856751414', 'Archaeology', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2047.5125342756617!2d-3.0835748839360106!3d59.123824981587425!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x489a54da9efa97a9%3A0x51bd22cc4887dcf4!2sBroch%20of%20Gurness!5e0!3m2!1sen!2sba!4v1642964681400!5m2!1sen!2sba'),
       (50, 'SR', 'Sušana u Gurnesu', 'Ejkernes, Ejvi, KW17 2NH', 'Mejnlend, Orknej', 'Škotska', '+441856751414', 'Arheologija', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2047.5125342756617!2d-3.0835748839360106!3d59.123824981587425!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x489a54da9efa97a9%3A0x51bd22cc4887dcf4!2sBroch%20of%20Gurness!5e0!3m2!1sen!2sba!4v1642964681400!5m2!1sen!2sba');
