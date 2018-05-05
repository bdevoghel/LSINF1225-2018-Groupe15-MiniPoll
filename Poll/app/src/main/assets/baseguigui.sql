PRAGMA foreign_keys = off;
BEGIN TRANSACTION;

-- Table: Utilisateur
DROP TABLE IF EXISTS Utilisateur;
CREATE TABLE Utilisateur (Mail TEXT NOT NULL UNIQUE PRIMARY KEY, Nom TEXT NOT NULL, Prénom TEXT NOT NULL, "Mot de passe" TEXT NOT NULL, Photo NUMERIC DEFAULT 'default.jpeg' NOT NULL, "Meilleur ami" TEXT, FOREIGN KEY ("Meilleur ami") REFERENCES Utilisateur (Mail));
INSERT INTO Utilisateur (Mail, Nom, Prénom, "Mot de passe", Photo, "Meilleur ami") VALUES ('harry.smith@mymail.com', 'Smith', 'Harry', 'hsmIth123', 'default.jpeg', 'arthur98@gmail.com');

COMMIT TRANSACTION;
PRAGMA foreign_keys = on;