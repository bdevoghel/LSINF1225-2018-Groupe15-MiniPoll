PRAGMA foreign_keys = off;
BEGIN TRANSACTION;

DROP TABLE IF EXISTS profil;
CREATE TABLE [profil] ( [identifiant] TEXT PRIMARY KEY NOT NULL DEFAULT ( NULL ) , [prenom] TEXT NOT NULL DEFAULT ( NULL ) , [nom] TEXT NOT NULL DEFAULT ( NULL ) , [email] TEXT NOT NULL DEFAULT ( NULL ) , [mdp] TEXT NOT NULL DEFAULT ( NULL ) , [photo] BLOB , [favori] INTEGER ) ;
INSERT INTO profil (identifiant, prenom, nom, email, mdp) VALUES ('rogerdu','roger','dupont','r.dupont@yopmail.com','ilovesqlite');

COMMIT TRANSACTION;
PRAGMA foreign_keys = on;