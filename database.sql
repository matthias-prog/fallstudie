DROP DATABASE IF EXISTS fallstudie;

CREATE DATABASE IF NOT EXISTS fallstudie;

USE fallstudie;

CREATE TABLE IF NOT EXISTS Benutzer(
                                       BenutzerID int NOT NULL AUTO_INCREMENT,
                                       Benutzername char(20) NOT NULL UNIQUE,
                                       Passwort char(20) NOT NULL,
                                       IsAdmin boolean DEFAULT false,
                                       PRIMARY KEY (Benutzerid)
);

INSERT INTO Benutzer(benutzername,passwort,isAdmin)
VALUES
("Julian","julian123", true),
("Daniel","daniel456", false),
("Tobias", "tobias789", false);

CREATE TABLE IF NOT EXISTS CITyp (TypID int NOT NULL AUTO_INCREMENT,
                                  TypName char(20) NOT NULL UNIQUE,
                                  Attribut1 char(20),
                                  Attribut2 char(20),
                                  Attribut3 char(20),
                                  Attribut4 char(20),
                                  Attribut5 char(20),
                                  Attribut6 char(20),
                                  Attribut7 char(20),
                                  Attribut8 char(20),
                                  Attribut9 char(20),
                                  Attribut10 char(20),
                                  Attribut11 char(20),
                                  Attribut12 char(20),
                                  Attribut13 char(20),
                                  Attribut14 char(20),
                                  Attribut15 char(20),
                                  CONSTRAINT cityp_pk PRIMARY KEY(TypID)
);

INSERT INTO cityp (TypName, Attribut1, Attribut2, Attribut3, Attribut4, Attribut5)
VALUES ("Drucker", "Name", "Produktnummer", "Hersteller", "Standort", "Kaufpreis");
INSERT INTO cityp (TypName, Attribut1, Attribut2, Attribut3, Attribut4, Attribut5, Attribut6, Attribut7)
VALUES ("Monitor", "Name", "Produktnummer", "Hersteller", "Standort", "Kaufpreis", "Anschluesse", "Aufloesung");
INSERT INTO cityp (TypName, Attribut1, Attribut2, Attribut3, Attribut4, Attribut5, Attribut6)
VALUES ("Tastatur", "Name", "Produktnummer", "Hersteller", "Standort", "Kaufpreis", "Anschluss");


CREATE TABLE Monitor
(RecordID int not null AUTO_INCREMENT, Name char(20), Produktnummer char(20), Hersteller char(20), Standort char(20), Kaufpreis char(20), Anschluesse char(20), aufloesung char(20), CONSTRAINT monitor_pk PRIMARY KEY(RecordID));
INSERT INTO monitor
(Name, Produktnummer, Hersteller, Standort, Kaufpreis, Anschluesse, Aufloesung)
VALUES
("Monitor15", "B1748", "BenQ", "2.OGR25", "725", "2", "1920x1080"),
("Monitor12-D", "GL2450", "BenQ", "4.OGR12", "320", "3", "1920x1080"),
("Monitor19", "S24F35", "Samsung", "H_135", "120", "2", "1920x1080");


CREATE TABLE drucker(RecordID int not null AUTO_INCREMENT, Name char(20), Produktnummer char(20), Hersteller char(20), Standort char(20), Kaufpreis char(20), CONSTRAINT drucker_pk PRIMARY KEY (RecordID));
INSERT INTO drucker(Name, Produktnummer, Hersteller, Standort, Kaufpreis)
VALUES
("Farbdrucker12B","35221","HP","L_12.5","75"),
("Drucker4","B2530","Samsung","1.UGR3","680"),
("SW_Drucker","SW359","Canon","V13","250");


CREATE TABLE tastatur(RecordID int not null AUTO_INCREMENT, Name char(20), Produktnummer char(20), Hersteller char(20), Standort char(20), Kaufpreis char(20), Anschluss char(20), CONSTRAINT tastatur_pk PRIMARY KEY (RecordID));
INSERT INTO tastatur(Name, Produktnummer, Hersteller, Standort, Kaufpreis, Anschluss)
VALUES ("Tastatur36","L4245","Logitech","Stuttgart","12.5","USB"),
       ("Tastatur90","H235","HP","Wolfsburg","21","USB");
