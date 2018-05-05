PRAGMA foreign_keys = off;
BEGIN TRANSACTION;

DROP TABLE IF EXISTS Utilisateur;
CREATE TABLE Utilisateur(Username TEXT NOT NULL UNIQUE PRIMARY KEY, Prenom TEXT NOT NULL, Nom TEXT NOT NULL, email TEXT NOT NULL, mdp TEXT NOT NULL, photo NUMERIC DEFAULT 'default.jpeg', favori INTEGER);
INSERT INTO Utilisateur(Username,Prenom,Nom,email,mdp) VALUES('rogerdu','roger','dupont','r.dupont@yopmail.com','ilovesqlite');

COMMIT TRANSACTION;
PRAGMA foreign_keys = on;