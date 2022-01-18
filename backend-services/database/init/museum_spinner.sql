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
VALUES (1, 'EN', 'Skara Brae', 'KW16 3LR', 'Stromnes', 'Scotland', '+441856841815', 'Archeology', 'https://www.google.com/maps/place/Skara+Brae+Visitor+Centre/@59.0488034,-3.3382059,17z/data=!3m1!4b1!4m5!3m4!1s0x489af7efe4ff0be7:0xbf7b11dad5095a5b!8m2!3d59.0488034!4d-3.3360172'),
       (1, 'SR', 'Skara Brae', 'KW16 3LR', 'Stromnes', 'Škotska', '+441856841815', 'Arheologija', 'https://www.google.com/maps/place/Skara+Brae+Visitor+Centre/@59.0488034,-3.3382059,17z/data=!3m1!4b1!4m5!3m4!1s0x489af7efe4ff0be7:0xbf7b11dad5095a5b!8m2!3d59.0488034!4d-3.3360172'),
       (2, 'EN', 'Pier Arts Centre', 'KW16 3AA', 'Stromness', 'Scotland', '+441856850209', 'Art', 'https://www.google.com/maps/place/Pier+Arts+Centre/@59.0488034,-3.3382059,17z/data=!4m5!3m4!1s0x489afa0422c6a17b:0xdd341659b0f37814!8m2!3d58.962962!4d-3.2982513'),
       (2, 'SR', 'Pijer centar za umjetnost', 'KW16 3AA', 'Stromnes', 'Škotska', '+441856850209', 'Umjetnost', 'https://www.google.com/maps/place/Pier+Arts+Centre/@59.0488034,-3.3382059,17z/data=!4m5!3m4!1s0x489afa0422c6a17b:0xdd341659b0f37814!8m2!3d58.962962!4d-3.2982513'),
       (3, 'EN', 'Orkney Wireless Museum', 'KW15 1DH', 'Kirkwall', 'Scotland', '+441856873191', 'Technology', 'https://www.google.com/maps/place/The+Orkney+Museum/@58.9813487,-2.9631323,17z/data=!3m1!4b1!4m5!3m4!1s0x489b0163cc5fb9f1:0xe0572be8bf9cada9!8m2!3d58.9813464!4d-2.9609343'),
       (3, 'SR', 'Orknej bežični muzej', 'KW15 1DH', 'Kerkval', 'Škotska', '+441856873191', 'Tehnologija', 'https://www.google.com/maps/place/The+Orkney+Museum/@58.9813487,-2.9631323,17z/data=!3m1!4b1!4m5!3m4!1s0x489b0163cc5fb9f1:0xe0572be8bf9cada9!8m2!3d58.9813464!4d-2.9609343'),
       (4, 'EN', 'Hackness Martello Tower and Battery', 'KW16 3PQ', 'South Walls', 'Scotland', '+441856701727', 'Military', 'https://www.google.com/maps/place/Hackness+Martello+Tower+and+Battery/@58.8052964,-3.1508489,17z/data=!3m1!4b1!4m5!3m4!1s0x489ae40634f41811:0xf413ce67aa6b6d0e!8m2!3d58.8052964!4d-3.1486602'),
       (4, 'SR', 'Haknes Martelo toranj i artiljerija', 'KW16 3PQ', 'Južni zidovi', 'Škotska', '+441856701727', 'Vojska', 'https://www.google.com/maps/place/Hackness+Martello+Tower+and+Battery/@58.8052964,-3.1508489,17z/data=!3m1!4b1!4m5!3m4!1s0x489ae40634f41811:0xf413ce67aa6b6d0e!8m2!3d58.8052964!4d-3.1486602'),
       (5, 'EN', 'Burns Cottage', 'Alloway', 'Ayr KA7 4PY', 'Scotland', '+441292443700', 'History', 'https://www.google.com/maps/place/Burns+Cottage/@55.4328015,-4.6355989,17z/data=!3m1!4b1!4m5!3m4!1s0x4889d6719946bff7:0x1e640c47aa303e16!8m2!3d55.4328212!4d-4.6334427'),
       (5, 'SR', 'Koliba Burns', 'Alovej', 'Ayr KA7 4PY', 'Škotska', '+441292443700', 'Istorija', 'https://www.google.com/maps/place/Burns+Cottage/@55.4328015,-4.6355989,17z/data=!3m1!4b1!4m5!3m4!1s0x4889d6719946bff7:0x1e640c47aa303e16!8m2!3d55.4328212!4d-4.6334427'),
       (6, 'EN', 'Torosay Castle', 'PA64 6AP', 'Isle of Mull', 'Scotland', '+441680812309', 'History', 'https://www.google.com/maps/place/Duart+Castle/@56.4561763,-5.6573884,17z/data=!3m1!4b1!4m5!3m4!1s0x488be00c6de9aaab:0x1d2b51bbc3088c26!8m2!3d56.4561153!4d-5.6553035'),
       (6, 'SR', 'Dvorac Torosej', 'PA64 6AP', 'Ostrvo Mul', 'Škotska', '+441680812309', 'Istorija', 'https://www.google.com/maps/place/Duart+Castle/@56.4561763,-5.6573884,17z/data=!3m1!4b1!4m5!3m4!1s0x488be00c6de9aaab:0x1d2b51bbc3088c26!8m2!3d56.4561153!4d-5.6553035'),
       (7, 'EN', 'The Devil''s Porridge Museum', 'DG12 6TF', 'Eastriggs', 'Scotland', '+441461700021', 'History', 'https://www.google.com/maps/place/The+Devils+Porridge/@54.9852966,-3.1720937,17z/data=!3m1!4b1!4m5!3m4!1s0x487d3b8b3fb06589:0xf8f239ead5c75683!8m2!3d54.9853375!4d-3.1698964'),
       (7, 'SR', 'Muzej Đavolja kaša', 'DG12 6TF', 'Istrigs', 'Škotska', '+441461700021', 'Istorija', 'https://www.google.com/maps/place/The+Devils+Porridge/@54.9852966,-3.1720937,17z/data=!3m1!4b1!4m5!3m4!1s0x487d3b8b3fb06589:0xf8f239ead5c75683!8m2!3d54.9853375!4d-3.1698964'),
       (8, 'EN', 'Aberdeen Arts Centre', 'AB24 5AA', 'Aberdeen', 'Scotland', '+441224635208', 'Art', 'https://www.google.com/maps/place/Aberdeen+Arts+Centre/@57.149375,-2.0961915,17z/data=!3m1!4b1!4m5!3m4!1s0x48840e3e80ca0557:0xf67ca568f18fac51!8m2!3d57.1495419!4d-2.0940385'),
       (8, 'SR', 'Aberdin centar za umjetnost', 'AB24 5AA', 'Aberdin', 'Škotska', '+441224635208', 'Umjetnost', 'https://www.google.com/maps/place/Aberdeen+Arts+Centre/@57.149375,-2.0961915,17z/data=!3m1!4b1!4m5!3m4!1s0x48840e3e80ca0557:0xf67ca568f18fac51!8m2!3d57.1495419!4d-2.0940385'),
       (9, 'EN', 'Blairs Museum', '3RX4+H4', 'Blairs', 'Scotland', '+441224863767', 'Religion', 'https://www.google.com/maps/place/Blairs+Museum/@57.098896,-2.1968147,17z/data=!3m1!4b1!4m5!3m4!1s0x4884109cf44131b7:0x86c01d5c129ae0ce!8m2!3d57.098896!4d-2.194626'),
       (9, 'SR', 'Blers muzej', '3RX4+H4', 'Blers', 'Škotska', '+441224863767', 'Religija', 'https://www.google.com/maps/place/Blairs+Museum/@57.098896,-2.1968147,17z/data=!3m1!4b1!4m5!3m4!1s0x4884109cf44131b7:0x86c01d5c129ae0ce!8m2!3d57.098896!4d-2.194626'),
       (10, 'EN', 'Corgarff Castle', '8m W of Strathdon on the, A939, Strathdon AB36 8YP', 'Corgarff', 'Scotland', '+441975651460', 'History', 'https://www.google.com/maps/place/Corgarff+Castle/@57.1630628,-3.2364231,17z/data=!3m1!4b1!4m5!3m4!1s0x4885c14167b1ac35:0x737eed58545eeac3!8m2!3d57.1630628!4d-3.2342344'),
       (10, 'SR', 'Dvorac Korgarf', '13KM zapadno od Stratdona na A939, Stratdon AB36 8YP', 'Korgarf', 'Škotska', '+441975651460', 'Istorija', 'https://www.google.com/maps/place/Corgarff+Castle/@57.1630628,-3.2364231,17z/data=!3m1!4b1!4m5!3m4!1s0x4885c14167b1ac35:0x737eed58545eeac3!8m2!3d57.1630628!4d-3.2342344'),
       (11, 'EN', 'Glenesk Folk Museum', 'Glenesk, Brechin DD9 7YT', 'Tarfside', 'Scotland', '+441356648070', 'History', 'https://www.google.com/maps/place/The+Glenesk+Retreat/@56.898886,-2.8105617,17z/data=!3m1!4b1!4m5!3m4!1s0x48842aad086ba127:0xcbf5b8d527763b91!8m2!3d56.898886!4d-2.808373'),
       (11, 'SR', 'Glenesk narodni muzej', 'Glenesk, Brečin DD9 7YT', 'Tarfsajd', 'Škotska', '+441356648070', 'Istorija', 'https://www.google.com/maps/place/The+Glenesk+Retreat/@56.898886,-2.8105617,17z/data=!3m1!4b1!4m5!3m4!1s0x48842aad086ba127:0xcbf5b8d527763b91!8m2!3d56.898886!4d-2.808373'),
       (12, 'EN', 'St Vigeans Sculpture Stones', 'Arbroath DD11 4RB', 'St Vigeans', 'Scotland', '+441241878756', 'Archeology', 'https://www.google.com/maps/place/St+Vigeans+Sculptured+Stones+and+Museum/@56.5768377,-2.5929044,17z/data=!3m1!4b1!4m5!3m4!1s0x48868d210446adc7:0xa2144d8e1bf75b6a!8m2!3d56.576811!4d-2.5906593'),
       (12, 'SR', 'Kamene skulpture Sv. Vigens', 'Arbroat DD11 4RB', 'Sv Vigens', 'Škotska', '+441241878756', 'Arheologija', 'https://www.google.com/maps/place/St+Vigeans+Sculptured+Stones+and+Museum/@56.5768377,-2.5929044,17z/data=!3m1!4b1!4m5!3m4!1s0x48868d210446adc7:0xa2144d8e1bf75b6a!8m2!3d56.576811!4d-2.5906593'),
       (13, 'EN', 'Kilmartin Museum', 'Lochgilphead PA31 8RQ', 'Kilmartin', 'Scotland', '+441546510278', 'Archeology', 'https://www.google.com/maps/place/Kilmartin+Museum/@56.1337807,-5.4889301,17z/data=!3m1!4b1!4m5!3m4!1s0x48897ed1d2bd14f9:0x79c104e5f18f552a!8m2!3d56.1337797!4d-5.4867414'),
       (13, 'SR', 'Kilmartin muzej', 'Lohgilphed PA31 8RQ', 'Kilmartin', 'Škotska', '+441546510278', 'Arheologija', 'https://www.google.com/maps/place/Kilmartin+Museum/@56.1337807,-5.4889301,17z/data=!3m1!4b1!4m5!3m4!1s0x48897ed1d2bd14f9:0x79c104e5f18f552a!8m2!3d56.1337797!4d-5.4867414'),
       (14, 'EN', 'Castle Campbell', '58FG+W4', 'Dollar', 'Scotland', '+441259742408', 'History', 'https://www.google.com/maps/place/Castle+Campbell/@56.1748735,-3.6769294,17z/data=!3m1!4b1!4m5!3m4!1s0x48862a9f044023d9:0x58ebd8e456336968!8m2!3d56.1749729!4d-3.674797'),
       (14, 'SR', 'Dvorac Kampbel', '58FG+W4', 'Dolar', 'Škotska', '+441259742408', 'Istorija', 'https://www.google.com/maps/place/Castle+Campbell/@56.1748735,-3.6769294,17z/data=!3m1!4b1!4m5!3m4!1s0x48862a9f044023d9:0x58ebd8e456336968!8m2!3d56.1749729!4d-3.674797'),
       (15, 'EN', 'Gracefield Arts Centre', '28 Edinburgh Rd, DG1 1JQ', 'Dumfries', 'Scotland', '+441387262084', 'Art', 'https://www.google.com/maps/place/Gracefield+Arts+Centre/@55.0740395,-3.6097212,17z/data=!3m1!4b1!4m5!3m4!1s0x4862ca72132d467f:0xc8e3243d5a0a1252!8m2!3d55.0740388!4d-3.6075837'),
       (15, 'SR', 'Grejsfield centar za umjetnost', 'Ulica Edinburg 28, DG1 1JQ', 'Dumfries', 'Škotska', '+441387262084', 'Umjetnost', 'https://www.google.com/maps/place/Gracefield+Arts+Centre/@55.0740395,-3.6097212,17z/data=!3m1!4b1!4m5!3m4!1s0x4862ca72132d467f:0xc8e3243d5a0a1252!8m2!3d55.0740388!4d-3.6075837'),
       (16, 'EN', 'Dundee Contemporary Arts', '152 Nethergarde, DD1 4DY', 'Dundee', 'Scotland', '+441382909900', 'Art', 'https://www.google.com/maps/place/Dundee+Contemporary+Arts/@56.4574302,-2.9772374,17z/data=!3m1!4b1!4m5!3m4!1s0x48865cbfa109cec7:0x61fe186f99fa91bc!8m2!3d56.4574302!4d-2.9750487'),
       (16, 'SR', 'Dandi privremena umjetnost', '152 Netergarde, DD1 4DY', 'Dandi', 'Škotska', '+441382909900', 'Umjetnost', 'https://www.google.com/maps/place/Dundee+Contemporary+Arts/@56.4574302,-2.9772374,17z/data=!3m1!4b1!4m5!3m4!1s0x48865cbfa109cec7:0x61fe186f99fa91bc!8m2!3d56.4574302!4d-2.9750487'),
       (17, 'EN', 'Mills Observatory', 'Balgay Park, Glamis Rd, DD2 2UB', 'Dundee', 'Scotland', '+441382435967', 'Science', 'https://www.google.com/maps/place/Mills+Observatory/@56.4649912,-3.014757,17z/data=!3m1!4b1!4m5!3m4!1s0x4886433bc2f1dfab:0x452de64f15f1688b!8m2!3d56.4649559!4d-3.0125686'),
       (17, 'SR', 'Mils obzervatorij', 'Balgaj park, ulica Glamis, DD2 2UB', 'Dandi', 'Škotska', '+441382435967', 'Nauka', 'https://www.google.com/maps/place/Mills+Observatory/@56.4649912,-3.014757,17z/data=!3m1!4b1!4m5!3m4!1s0x4886433bc2f1dfab:0x452de64f15f1688b!8m2!3d56.4649559!4d-3.0125686'),
       (18, 'EN', 'Verdant Works', 'W Henderson''s Wynd, DD1 5BT', 'Dundee', 'Scotland', '+441382309060', 'Industry', 'https://www.google.com/maps/place/Verdant+Works/@56.4614571,-2.9856651,17z/data=!3m1!4b1!4m5!3m4!1s0x48865cc7b2613e8f:0xf10c58b8aee11738!8m2!3d56.4614583!4d-2.9834568'),
       (18, 'SR', 'Verdant Vorks', 'W Hendersonova ulica, DD1 5BT', 'Dandi', 'Škotska', '+441382309060', 'Industrija', 'https://www.google.com/maps/place/Verdant+Works/@56.4614571,-2.9856651,17z/data=!3m1!4b1!4m5!3m4!1s0x48865cc7b2613e8f:0xf10c58b8aee11738!8m2!3d56.4614583!4d-2.9834568'),
       (19, 'EN', 'Dumfries House', 'KA18 2NJ', 'Cumnock', 'Scotland', '+441290425959', 'History', 'https://www.google.com/maps/place/Dumfries+House:+part+of+The+Prince''s+Foundation/@55.455561,-4.3101467,17z/data=!3m1!4b1!4m5!3m4!1s0x4888285c7c0209a5:0xbb95a95acaee65a!8m2!3d55.455561!4d-4.307958'),
       (19, 'SR', 'Dumfries Kuća', 'KA18 2NJ', 'Kamnok', 'Škotska', '+441290425959', 'Istorija', 'https://www.google.com/maps/place/Dumfries+House:+part+of+The+Prince''s+Foundation/@55.455561,-4.3101467,17z/data=!3m1!4b1!4m5!3m4!1s0x4888285c7c0209a5:0xbb95a95acaee65a!8m2!3d55.455561!4d-4.307958'),
       (20, 'EN', 'Myreton Motor Museum', 'Longniddry EH32 0PZ', 'Aberlady', 'Scotland', '+4412123425959', 'Transportation', 'https://www.google.com/maps/place/Myreton+Motor+Museum/@56.0044837,-2.8267694,17z/data=!3m1!4b1!4m5!3m4!1s0x4887a8b201d26a03:0x5bfd221d78078fe3!8m2!3d56.0044837!4d-2.8245807'),
       (20, 'SR', 'Majreton muzej motora', 'Longenidraj EH32 0PZ', 'Aberlejdi', 'Škotska', '4412123425959', 'Saobraćaj', 'https://www.google.com/maps/place/Myreton+Motor+Museum/@56.0044837,-2.8267694,17z/data=!3m1!4b1!4m5!3m4!1s0x4887a8b201d26a03:0x5bfd221d78078fe3!8m2!3d56.0044837!4d-2.8245807'),
       (21, 'EN', 'National Museum of Flight', 'East Fortune Airfield, B1347, North Berwick, EH39 5LF', 'East Fortune', 'Scotland', '+443001236789', 'Transportation', 'https://www.google.com/maps/place/National+Museum+Of+Flight/@55.995776,-2.7220354,17z/data=!3m1!4b1!4m5!3m4!1s0x4887a800a6438d1b:0x977844b1ace86890!8m2!3d55.995776!4d-2.7198467'),
       (21, 'SR', 'Nacionalni muzej leta', 'Aerodrom Ist Fortjun, B1347, Sjeverni Bervik, EH39 5LF', 'Istočni Fortjun', 'Škotska', '+443001236789', 'Saobraćaj', 'https://www.google.com/maps/place/National+Museum+Of+Flight/@55.995776,-2.7220354,17z/data=!3m1!4b1!4m5!3m4!1s0x4887a800a6438d1b:0x977844b1ace86890!8m2!3d55.995776!4d-2.7198467'),
       (22, 'EN', 'Prestongrange Industrial Heritage Museum', 'Morrisons Haven, Prestonpans EH32 9RX', 'Prestongrange', 'Scotland', '+441316532904', 'Industry', 'https://www.google.com/maps/place/Prestongrange+Museum/@55.9517546,-3.0110884,17z/data=!3m1!4b1!4m5!3m4!1s0x4887ba563581a70d:0xf4ff37377a9aa735!8m2!3d55.9517062!4d-3.0088487'),
       (22, 'SR', 'Muzej industrijskog naslijeđa PRestongrandž', 'Morisons Hejven, Prestonpans EH32 9RX', 'Prestongrandž', 'Škotska', '+441316532904', 'Industrija', 'https://www.google.com/maps/place/Prestongrange+Museum/@55.9517546,-3.0110884,17z/data=!3m1!4b1!4m5!3m4!1s0x4887ba563581a70d:0xf4ff37377a9aa735!8m2!3d55.9517062!4d-3.0088487'),
       (23, 'EN', 'City Art Centre', '2 Market St, EH1 1DE', 'Edinburgh', 'Scotland', '+441315293993', 'Art', 'https://www.google.com/maps/place/City+Art+Centre/@55.9510817,-3.1915918,17z/data=!3m1!4b1!4m5!3m4!1s0x4887c7907f516035:0x547782aa375b0eb1!8m2!3d55.9510767!4d-3.1894004'),
       (23, 'SR', 'Gradski centar umjetnosti', 'Ulica Market 2, EH1 1DE', 'Edinburg', 'Škotska', '+441315293993', 'Umjetnost', 'https://www.google.com/maps/place/City+Art+Centre/@55.9510817,-3.1915918,17z/data=!3m1!4b1!4m5!3m4!1s0x4887c7907f516035:0x547782aa375b0eb1!8m2!3d55.9510767!4d-3.1894004'),
       (24, 'EN', 'Modern Two', '75 Belford Rd, EH4 3DR', 'Edinburgh', 'Scotland', '+441316246200', 'Art', 'https://www.google.com/maps/place/Scottish+National+Gallery+of+Modern+Art+(Modern+One)/@55.9508229,-3.2298248,17z/data=!3m1!4b1!4m5!3m4!1s0x4887c648eae6c6eb:0x46ec2c31332c0457!8m2!3d55.950849!4d-3.22758'),
       (24, 'SR', 'Modern dva', 'Ulica Belford 75, EH4 3DR', 'Edinburg', 'Škotska', '+441316246200', 'Umjetnost', 'https://www.google.com/maps/place/Scottish+National+Gallery+of+Modern+Art+(Modern+One)/@55.9508229,-3.2298248,17z/data=!3m1!4b1!4m5!3m4!1s0x4887c648eae6c6eb:0x46ec2c31332c0457!8m2!3d55.950849!4d-3.22758'),
       (25, 'EN', 'Fruitmarket Gallery', '45 Market St, EH1 1DF', 'Edinburgh', 'Scotland', '+441312252383', 'Art', 'https://www.google.com/maps/place/Fruitmarket/@55.9513094,-3.1916321,17z/data=!3m1!4b1!4m5!3m4!1s0x4887c78f99515683:0x6b5d0ae5cddc0b8d!8m2!3d55.9512817!4d-3.1893649'),
       (25, 'SR', 'Galerija Frutmarket', 'Ulica Market 45, EH1 1DF', 'Edinburg', 'Škotska', '+441312252383', 'Umjetnost', 'https://www.google.com/maps/place/Fruitmarket/@55.9513094,-3.1916321,17z/data=!3m1!4b1!4m5!3m4!1s0x4887c78f99515683:0x6b5d0ae5cddc0b8d!8m2!3d55.9512817!4d-3.1893649'),
       (26, 'EN', 'Gladstone''s Land', '477B Lawnmarket, EH1 2NT', 'Edinburgh', 'Scotland', '+441312265856', 'History', 'https://www.google.com/maps/place/Gladstone''s+Land/@55.9494484,-3.195858,17z/data=!3m2!4b1!5s0x4887c79a97daea61:0x21b0c01dafe540c3!4m5!3m4!1s0x4887c79a97b6a553:0x6dfaf0b865439b23!8m2!3d55.9494239!4d-3.1936493'),
       (26, 'SR', 'Gladstonova zemlja', '477B Launmarket, EH1 2NT', 'Edinburg', 'Škotska', '+441312265856', 'Istorija', 'https://www.google.com/maps/place/Gladstone''s+Land/@55.9494484,-3.195858,17z/data=!3m2!4b1!5s0x4887c79a97daea61:0x21b0c01dafe540c3!4m5!3m4!1s0x4887c79a97b6a553:0x6dfaf0b865439b23!8m2!3d55.9494239!4d-3.1936493'),
       (27, 'EN', 'Museum of Childhood (Edinburgh)', '42 High St, EH1 1TG', 'Edinburgh', 'Scotland', '+441315294142', 'Toys', 'https://www.google.com/maps/place/Museum+of+Childhood/@55.9503639,-3.1876331,17z/data=!3m1!4b1!4m5!3m4!1s0x4887c785f7944bd1:0xe6eb3ba2da9e2f93!8m2!3d55.950367!4d-3.185446'),
       (27, 'SR', 'Muzej djetinjstva Edingburg', 'Ulica Haj 42, EH1 1TG', 'Edinburg', 'Škotska', '+441315294142', 'Igračke', 'https://www.google.com/maps/place/Museum+of+Childhood/@55.9503639,-3.1876331,17z/data=!3m1!4b1!4m5!3m4!1s0x4887c785f7944bd1:0xe6eb3ba2da9e2f93!8m2!3d55.950367!4d-3.185446'),
       (28, 'EN', 'Scottish National Gallery', 'The Mound, EH2 2EL', 'Edinburgh', 'Scotland', '+441316246200', 'Art', 'https://www.google.com/maps/place/Scottish+National+Gallery/@55.950902,-3.1978749,17z/data=!3m1!4b1!4m5!3m4!1s0x4887c648eae6c6eb:0xc47f9545e742193!8m2!3d55.950902!4d-3.1956862'),
       (28, 'SR', 'Škotski nacionalni muzej', 'Maund, EH2 2EL', 'Edinburg', 'Škotska', '+441316246200', 'Umjetnost', 'https://www.google.com/maps/place/Scottish+National+Gallery/@55.950902,-3.1978749,17z/data=!3m1!4b1!4m5!3m4!1s0x4887c648eae6c6eb:0xc47f9545e742193!8m2!3d55.950902!4d-3.1956862'),
       (29, 'EN', 'Queen''s Gallery, Edinburgh', 'Palace of Holyroodhouse, EH8 8DX', 'Edinburgh', 'Scotland', '+443031237306', 'Art', 'https://www.google.com/maps/place/The+Queen''s+Gallery,+Palace+of+Holyroodhouse/@55.95262,-3.1760847,17z/data=!3m1!4b1!4m5!3m4!1s0x4887b87717a3a003:0xd0f067e7bd6cc738!8m2!3d55.9526035!4d-3.1740082'),
       (29, 'SR', 'Kraljičina galerija u Edingburgu', 'Palata Holirudhaus, EH8 8DX', 'Edinburg', 'Škotska', '+443031237306', 'Umjetnost', 'https://www.google.com/maps/place/The+Queen''s+Gallery,+Palace+of+Holyroodhouse/@55.95262,-3.1760847,17z/data=!3m1!4b1!4m5!3m4!1s0x4887b87717a3a003:0xd0f067e7bd6cc738!8m2!3d55.9526035!4d-3.1740082'),
       (30, 'EN', 'Royal Scottish Academy Building', 'The Mound, EH2 2EL', 'Edinburgh', 'Scotland', '+441316246110', 'Art', 'https://www.google.com/maps/place/The+Royal+Scottish+Academy/@55.9516752,-3.1984777,17z/data=!3m1!4b1!4m5!3m4!1s0x4887c790f227a157:0xdcaa0a421b1c44a3!8m2!3d55.9519677!4d-3.1964469'),
       (30, 'SR', 'Zgrada rojalne škotske akademije', 'Maund, EH2 2EL', 'Edinburg', 'Škotska', '+441316246110', 'Umjetnost', 'https://www.google.com/maps/place/The+Royal+Scottish+Academy/@55.9516752,-3.1984777,17z/data=!3m1!4b1!4m5!3m4!1s0x4887c790f227a157:0xdcaa0a421b1c44a3!8m2!3d55.9519677!4d-3.1964469'),
       (31, 'EN', 'St Cecilia''s Hall', '50 Niddry St, EH1 1LG', 'Edinburgh', 'Scotland', '+441316502600', 'Music', 'https://www.google.com/maps/place/St.+Cecilia''s+Hall,+The+University+of+Edinburgh/@55.9490224,-3.1886728,17z/data=!3m1!4b1!4m5!3m4!1s0x4887c785916e0ef9:0xffcfa304631b6412!8m2!3d55.9490224!4d-3.1864841'),
       (31, 'SR', 'Hala Sv. Cecilije', 'Ulica Nidraj 50, EH1 1LG', 'Edinburg', 'Škotska', '+441316502600', 'Muzika', 'https://www.google.com/maps/place/St.+Cecilia''s+Hall,+The+University+of+Edinburgh/@55.9490224,-3.1886728,17z/data=!3m1!4b1!4m5!3m4!1s0x4887c785916e0ef9:0xffcfa304631b6412!8m2!3d55.9490224!4d-3.1864841'),
       (32, 'EN', 'Surgeons'' Hall Museum', 'Nicolson St, EH8 9DW', 'Edinburgh', 'Scotland', '+441315271711', 'Medicine', 'https://www.google.com/maps/place/Surgeons''+Hall+Museums/@55.9466549,-3.1874668,17z/data=!3m2!4b1!5s0x4887c7843c58c797:0xf6bcd3e04875421d!4m5!3m4!1s0x4887c784231b81f9:0xdd38e56f5febdafc!8m2!3d55.9466549!4d-3.1852781'),
       (32, 'SR', 'Hala hirurga', 'Ulica Nikolson, EH8 9DW', 'Edinburg', 'Škotska', '+441315271711', 'Medicina', 'https://www.google.com/maps/place/Surgeons''+Hall+Museums/@55.9466549,-3.1874668,17z/data=!3m2!4b1!5s0x4887c7843c58c797:0xf6bcd3e04875421d!4m5!3m4!1s0x4887c784231b81f9:0xdd38e56f5febdafc!8m2!3d55.9466549!4d-3.1852781'),
       (33, 'EN', 'Blackness Castle', 'Blackness, Linlithgow EH49 7NH', 'Falkirk', 'Scotland', '+441506834807', 'History', 'https://www.google.com/maps/place/Blackness+Castle/@56.0060359,-3.5182852,17z/data=!3m1!4b1!4m5!3m4!1s0x4887d0c5f2826139:0x38310d02755a9828!8m2!3d56.0060423!4d-3.5161485'),
       (33, 'SR', 'Dvorac Bleknes', 'Bleknes, Linlitgou EH49 7NH', 'Falkerk', 'Škotska', '+441506834807', 'Istorija', 'https://www.google.com/maps/place/Blackness+Castle/@56.0060359,-3.5182852,17z/data=!3m1!4b1!4m5!3m4!1s0x4887d0c5f2826139:0x38310d02755a9828!8m2!3d56.0060423!4d-3.5161485'),
       (34, 'EN', 'British Golf Museum', 'Bruce Embankment, St Andrews KY16 9AB', 'St Andrews', 'Scotland', '+441334460046', 'Sports', 'https://www.google.com/maps/place/The+R%26A+World+Golf+Museum/@56.3439536,-2.8039485,17z/data=!3m1!4b1!4m5!3m4!1s0x488657845b24991d:0x8139c7da844ce0e4!8m2!3d56.3438538!4d-2.8018783'),
       (34, 'SR', 'Britanski muzej golfa', 'Brus Embankment, Ulica Andrevs KY16 9AB', 'Sv Andrevs', 'Škotska', '+441334460046', 'Sport', 'https://www.google.com/maps/place/The+R%26A+World+Golf+Museum/@56.3439536,-2.8039485,17z/data=!3m1!4b1!4m5!3m4!1s0x488657845b24991d:0x8139c7da844ce0e4!8m2!3d56.3438538!4d-2.8018783'),
       (35, 'EN', 'Dunfermline Abbey', 'St Margaret St, KY12 7PE', 'Dunfermline', 'Scotland', '+441383724586', 'Religion', 'https://www.google.com/maps/place/Dunfermline+Abbey/@56.0701237,-3.4656829,17z/data=!3m1!4b1!4m5!3m4!1s0x4887d221224fc4ab:0x933ea569059ac30b!8m2!3d56.0701237!4d-3.4634942'),
       (35, 'SR', 'Kapela Dunfermlajn', 'Ulica Sv. Margaret, KY12 7PE', 'Danfermlajn', 'Škotska', '+441383724586', 'Religija', 'https://www.google.com/maps/place/Dunfermline+Abbey/@56.0701237,-3.4656829,17z/data=!3m1!4b1!4m5!3m4!1s0x4887d221224fc4ab:0x933ea569059ac30b!8m2!3d56.0701237!4d-3.4634942'),
       (36, 'EN', 'Inchcolm Abbey', 'Burntisland KY3 0UA', 'Inchcolm', 'Scotland', '+441316686899', 'Religion', 'https://www.google.com/maps/place/Inchcolm+Abbey/@56.0299831,-3.3038017,17z/data=!3m1!4b1!4m5!3m4!1s0x4887c94e9359d381:0xcb4aa4259e8b0db2!8m2!3d56.0299831!4d-3.301613'),
       (36, 'SR', 'Kapela Inkholm', 'Burntisland KY3 0UA', 'Inkholm', 'Škotska', '+441316686899', 'Religija', 'https://www.google.com/maps/place/Inchcolm+Abbey/@56.0299831,-3.3038017,17z/data=!3m1!4b1!4m5!3m4!1s0x4887c94e9359d381:0xcb4aa4259e8b0db2!8m2!3d56.0299831!4d-3.301613'),
       (37, 'EN', 'Kirkcaldy Galleries', 'War Memorial Gardens, Abbotshall Rd, KY1 1YG', 'Kirkcaldy', 'Scotland', '+441592583206', 'Art', 'https://www.google.com/maps/place/Kirkcaldy+Galleries/@56.1122809,-3.1681709,17z/data=!3m1!4b1!4m5!3m4!1s0x4887b5a219497627:0x9481397b48938e56!8m2!3d56.1122558!4d-3.1659267'),
       (37, 'SR', 'Galerija Kerkaldi', 'Vrtovi u spomen rata, Abotšal cesta, KY1 1YG', 'Kerkaldi', 'Škotska', '+441592583206', 'Umjetnost', 'https://www.google.com/maps/place/Kirkcaldy+Galleries/@56.1122809,-3.1681709,17z/data=!3m1!4b1!4m5!3m4!1s0x4887b5a219497627:0x9481397b48938e56!8m2!3d56.1122558!4d-3.1659267'),
       (38, 'EN', 'Scottish Vintage Bus Museum', 'M 90 Commerce Park, KY12, 0SJ', 'Lathalmond', 'Scotland', '+441383623380', 'Transportation', 'https://www.google.com/maps/place/Scottish+Vintage+Bus+Museum/@56.1137639,-3.4546952,17z/data=!3m1!4b1!4m5!3m4!1s0x4887d2890f3df1fb:0x65f2ddd10f76b9d2!8m2!3d56.1137639!4d-3.4525065'),
       (38, 'SR', 'Škotski muzej starih autobusa', 'M 90 Komerc Park, KY12 0SJ', 'Latalmond', 'Škotska', '+441383623380', 'Saobraćaj', 'https://www.google.com/maps/place/Scottish+Vintage+Bus+Museum/@56.1137639,-3.4546952,17z/data=!3m1!4b1!4m5!3m4!1s0x4887d2890f3df1fb:0x65f2ddd10f76b9d2!8m2!3d56.1137639!4d-3.4525065'),
       (39, 'EN', 'Burrell Collection', 'Bellahouston, G43 1AT', 'Glasgow', 'Scotland', '+441383627780', 'Art', 'https://www.google.com/maps/place/The+Burrell+Collection,+Bellahouston,+Glasgow+G43+1AT,+UK/@55.830886,-4.309819,17z/data=!3m1!4b1!4m5!3m4!1s0x48884638934620cd:0x9130dd41e928eeb6!8m2!3d55.830886!4d-4.3076303'),
       (39, 'SR', 'Kolekcija Burel', 'Belahjuston, G43 1AT', 'Glazgov', 'Škotska', '+441383627780', 'Umjetnost', 'https://www.google.com/maps/place/The+Burrell+Collection,+Bellahouston,+Glasgow+G43+1AT,+UK/@55.830886,-4.309819,17z/data=!3m1!4b1!4m5!3m4!1s0x48884638934620cd:0x9130dd41e928eeb6!8m2!3d55.830886!4d-4.3076303'),
       (40, 'EN', 'Glasgow Print Studio', '103 Trongate, G1 5HD', 'Glasgow', 'Scotland', '+441415520704', 'Art', 'https://www.google.com/maps/place/Glasgow+Print+Studio/@55.8566667,-4.2488555,17z/data=!3m1!4b1!4m5!3m4!1s0x488846a3d4367323:0x6363dc2c7759bc85!8m2!3d55.8566106!4d-4.2470766'),
       (40, 'SR', 'Glazgov studio štampe', '103 Trongejt, G1 5HD', 'Glazgov', 'Škotska', '+441415520704', 'Umjetnost', 'https://www.google.com/maps/place/Glasgow+Print+Studio/@55.8566667,-4.2488555,17z/data=!3m1!4b1!4m5!3m4!1s0x488846a3d4367323:0x6363dc2c7759bc85!8m2!3d55.8566106!4d-4.2470766'),
       (41, 'EN', 'Glasgow Women''s Library', '23 Landressy St, Bridgeton, G40 1BP', 'Glasgow', 'Scotland', '+441415502267', 'History', 'https://www.google.com/maps/place/Glasgow+Women''s+Library/@55.8488436,-4.2299493,17z/data=!3m1!4b1!4m5!3m4!1s0x488846b17cb412e7:0x77a67149f7d3157e!8m2!3d55.8488491!4d-4.2277856'),
       (41, 'SR', 'Ženska biblioteka u Glazgovu', 'Ulica Landresi 23, Bridžton, G40 1BP', 'Glazgov', 'Škotska', '+441415502267', 'Istorija', 'https://www.google.com/maps/place/Glasgow+Women''s+Library/@55.8488436,-4.2299493,17z/data=!3m1!4b1!4m5!3m4!1s0x488846b17cb412e7:0x77a67149f7d3157e!8m2!3d55.8488491!4d-4.2277856'),
       (42, 'EN', 'National Piping Center – Museum of Piping', '30-34 McPhater St, G4 0HW', 'Glasgow', 'Scotland', '+441413535551', 'Music', 'https://www.google.com/maps/place/The+National+Piping+Centre/@55.8670523,-4.258633,17z/data=!3m1!4b1!4m5!3m4!1s0x488844217722dbd1:0xfd26639b6779fcd!8m2!3d55.8670523!4d-4.2564443'),
       (42, 'SR', 'Nacionalni centar gajdi', 'Ulica MekFater 30-34, G3 0HW', 'Glazgov', 'Škotska', '+441413535551', 'Muzika', 'https://www.google.com/maps/place/The+National+Piping+Centre/@55.8670523,-4.258633,17z/data=!3m1!4b1!4m5!3m4!1s0x488844217722dbd1:0xfd26639b6779fcd!8m2!3d55.8670523!4d-4.2564443'),
       (43, 'EN', 'Riverside Museum', '100 Pointhouse Rd, Patrick, G3 8RS', 'Glasgow', 'Scotland', '+441412872720', 'Transportation', 'https://www.google.com/maps/place/Riverside+Museum/@55.865103,-4.3083949,17z/data=!3m1!4b1!4m5!3m4!1s0x488845d0f24231ef:0xb7cf6d9b3165003!8m2!3d55.865103!4d-4.3062062'),
       (43, 'SR', 'Riversajd muzej', 'Cesta Pointhaus 100, Patrik, G3 8RS', 'Glazgov', 'Škotska', '+441412872720', 'Saobraćaj', 'https://www.google.com/maps/place/Riverside+Museum/@55.865103,-4.3083949,17z/data=!3m1!4b1!4m5!3m4!1s0x488845d0f24231ef:0xb7cf6d9b3165003!8m2!3d55.865103!4d-4.3062062'),
       (44, 'EN', 'Scottish Football Museum', 'Hampden Park, G42 9BA', 'Glasgow', 'Scotland', '+441416166139', 'Sports', 'https://www.google.com/maps/place/Scottish+Football+Museum/@55.8249121,-4.2532868,17z/data=!3m1!4b1!4m5!3m4!1s0x488846e69dffdc29:0x75afc3d83f6efefd!8m2!3d55.8249121!4d-4.2510981'),
       (44, 'SR', 'Škotski muzej fudbala', 'Park Hampden, G42 9BA', 'Glazgov', 'Škotska', '+441416166139', 'Sport', 'https://www.google.com/maps/place/Scottish+Football+Museum/@55.8249121,-4.2532868,17z/data=!3m1!4b1!4m5!3m4!1s0x488846e69dffdc29:0x75afc3d83f6efefd!8m2!3d55.8249121!4d-4.2510981'),
       (45, 'EN', 'Cawdor Castle', 'B909. Nairn IV12 5RD', 'Cawdor', 'Scotland', '+441667404401', 'History', 'https://www.google.com/maps/place/Cawdor+Castle+and+Gardens/@57.5242749,-3.9287508,17z/data=!3m1!4b1!4m5!3m4!1s0x488f7e598fdeb1cf:0x87c4f8c52f8474df!8m2!3d57.5242749!4d-3.9265621'),
       (45, 'SR', 'Dvorac Kaudor', 'B909. Nejrn IV12 5RD', 'Kaudor', 'Škotska', '+441667404401', 'Istorija', 'https://www.google.com/maps/place/Cawdor+Castle+and+Gardens/@57.5242749,-3.9287508,17z/data=!3m1!4b1!4m5!3m4!1s0x488f7e598fdeb1cf:0x87c4f8c52f8474df!8m2!3d57.5242749!4d-3.9265621'),
       (46, 'EN', 'Fort George', 'Iverness IV2 7TD', 'Arderseir', 'Scotland', '+441667460232', 'Military', 'https://www.google.com/maps/place/Fort+George/@57.5835388,-4.0734965,17z/data=!3m1!4b1!4m5!3m4!1s0x487605556d261acb:0xe37555b111ae8dd8!8m2!3d57.5835555!4d-4.0713193'),
       (46, 'SR', 'Tvrđava Džordž', 'Ivernes IV2 7TD', 'Ardersejr', 'Škotska', '+441667460232', 'Vojska', 'https://www.google.com/maps/place/Fort+George/@57.5835388,-4.0734965,17z/data=!3m1!4b1!4m5!3m4!1s0x487605556d261acb:0xe37555b111ae8dd8!8m2!3d57.5835555!4d-4.0713193'),
       (47, 'EN', 'Culloden Battlefield', 'Iverness IV2 5EU', 'Culloden', 'Scotland', '+441463796090', 'Military', 'https://www.google.com/maps/place/Culloden+Battlefield/@57.4777898,-4.0978997,17z/data=!3m1!4b1!4m5!3m4!1s0x488f7a1afe782713:0xd87d3475f1f54627!8m2!3d57.4777898!4d-4.095711'),
       (47, 'SR', 'Bojište Kuloden', 'Ivernes IV2 5EU', 'Kuloden', 'Škotska', '+441463796090', 'Vojska', 'https://www.google.com/maps/place/Culloden+Battlefield/@57.4777898,-4.0978997,17z/data=!3m1!4b1!4m5!3m4!1s0x488f7a1afe782713:0xd87d3475f1f54627!8m2!3d57.4777898!4d-4.095711'),
       (48, 'EN', 'Highland Museum of Childhood', 'Old Victorian Station, Strathpeffer IV14 9DH', 'Strathpeffer', 'Scotland', '+441997421031', 'Toys', 'https://www.google.com/maps/place/Highland+Museum+of+Childhood/@57.5900165,-4.5369913,17z/data=!3m1!4b1!4m5!3m4!1s0x488fa73e90dc8aa9:0xa4fd8c8be1a679f!8m2!3d57.5900165!4d-4.5348026'),
       (48, 'SR', 'Muzej djetinjstva Hajlend', 'Stara Viktorijanska stanica, Stratpefer IV14 9DH', 'Stratpefer', 'Škotska', '+441997421031', 'Igračke', 'https://www.google.com/maps/place/Highland+Museum+of+Childhood/@57.5900165,-4.5369913,17z/data=!3m1!4b1!4m5!3m4!1s0x488fa73e90dc8aa9:0xa4fd8c8be1a679f!8m2!3d57.5900165!4d-4.5348026'),
       (49, 'EN', 'Summerlee, Museum of Scottish Industrial Life', 'Heritage Way, Coatbridge ML5 1QD', 'Motherwell', 'Scotland', '+441236638460', 'Industry', 'https://www.google.com/maps/place/Summerlee+Museum+of+Scottish+Industrial+Life/@55.8652778,-4.0341331,17z/data=!3m1!4b1!4m5!3m4!1s0x48886bd737c19bed:0xcdb9676036eaee16!8m2!3d55.8653009!4d-4.031958'),
       (49, 'SR', 'Muzej škotskog industrijskog života Samerli', 'Heritedž Vej, Koutbridž ML5 1QD', 'Motervel', 'Škotska', '+441236638460', 'Industrija', 'https://www.google.com/maps/place/Summerlee+Museum+of+Scottish+Industrial+Life/@55.8652778,-4.0341331,17z/data=!3m1!4b1!4m5!3m4!1s0x48886bd737c19bed:0xcdb9676036eaee16!8m2!3d55.8653009!4d-4.031958'),
       (50, 'EN', 'Broch of Gurness', 'Aikerness, Evie, KW17 2NH', 'Mainland, Orkney', 'Scotland', '+441856751414', 'Archaeology', 'https://www.google.com/maps/place/Broch+of+Gurness/@59.123825,-3.0835749,17z/data=!3m1!4b1!4m5!3m4!1s0x489a54da9efa97a9:0x51bd22cc4887dcf4!8m2!3d59.123825!4d-3.0813862'),
       (50, 'SR', 'Sušana u Gurnesu', 'Ejkernes, Ejvi, KW17 2NH', 'Mejnlend, Orknej', 'Škotska', '+441856751414', 'Arheologija', 'https://www.google.com/maps/place/Broch+of+Gurness/@59.123825,-3.0835749,17z/data=!3m1!4b1!4m5!3m4!1s0x489a54da9efa97a9:0x51bd22cc4887dcf4!8m2!3d59.123825!4d-3.0813862');
