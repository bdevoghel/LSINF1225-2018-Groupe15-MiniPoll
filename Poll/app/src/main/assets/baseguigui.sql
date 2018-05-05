PRAGMA foreign_keys = off;
BEGIN TRANSACTION;

-- Table: Utilisateur
DROP TABLE IF EXISTS Utilisateur;
CREATE TABLE Utilisateur (Username TEXT NOT NULL UNIQUE PRIMARY KEY, Nom TEXT NOT NULL, Prénom TEXT NOT NULL, mdp TEXT NOT NULL, Photo NUMERIC DEFAULT 'default.jpeg' NOT NULL, "Meilleur ami" TEXT, FOREIGN KEY ("Meilleur ami") REFERENCES Utilisateur (Username));
INSERT INTO Utilisateur (Username, Nom, Prénom, mdp, Photo, "Meilleur ami") VALUES ('michel', 'Smith', 'Harry', '12345', 'default.jpeg', 'arthur98@gmail.com');

COMMIT TRANSACTION;
PRAGMA foreign_keys = on;