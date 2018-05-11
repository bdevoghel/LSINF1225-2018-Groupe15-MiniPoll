PRAGMA foreign_keys = off;
BEGIN TRANSACTION;

-- Table: User
DROP TABLE IF EXISTS User;
CREATE TABLE User (username TEXT NOT NULL UNIQUE PRIMARY KEY,firstname TEXT NOT NULL,name TEXT NOT NULL,mail TEXT NOT NULL,password TEXT NOT NULL,photo TEXT, favori TEXT, FOREIGN KEY (favori) REFERENCES Utilisateur (username));
CREATE TABLE Poll (username_proprietaire TEXT NOT NULL ,idpoll INTEGER NOT NULL PRIMARY KEY UNIQUE,titre TEXT,description TEXT,types TEXT NOT NULL,status_principal INTEGER);
CREATE TABLE Question_list (idpoll INTEGER,idquestion INTEGER,description_question TEXT);
CREATE TABLE Notification (username TEXT NOT NULL,etat INTEGER,message TEXT,username_notif TEXT,poll_notif INTEGER);
CREATE TABLE Friend_list (username TEXT NOT NULL,username_amis TEXT NOT NULL,etat INTEGER NOT NULL);
CREATE TABLE Questionnaire_and_Advice (idquestion INTEGER NOT NULL,texte TEXT NOT NULL,reponse TEXT NOT NULL);
CREATE TABLE Questionnaire_and_Advice_Answer (username TEXT NOT NULL,idquestion INTEGER NOT NULL,reponse_utilisateur TEXT);
CREATE TABLE Survey (idpoll INTEGER NOT NULL,data_reponse TEXT NOT NULL, point INTEGER);
CREATE TABLE Survey_Answer (idpoll INTEGER NOT NULL,username TEXT NOT NULL ,reponse TEXT NOT NULL,ordre INTEGER NOT NULL);
CREATE TABLE Poll_access (idpoll INTEGER NOT NULL,username TEXT NOT NULL,statut_particulier FLOAT);
INSERT INTO User (username,firstname,name,mail,password) VALUES('rogerdu','roger','dupont','r.dupont@yopmail.com','ilovesqlite');
INSERT INTO User (username,firstname,name,mail,password) VALUES('c.haron','carl','haroun','c.haroun@yopmail.com', 'tuesday');
INSERT INTO User (username,firstname,name,mail,password) VALUES('roger.charles','roger','charles','r.dupont@yopmail.com','titi');
INSERT INTO User (username,firstname,name,mail,password) VALUES('nico','nicolas','dupont','n.dupont@yopmail.com','hello');
INSERT INTO User (username,firstname,name,mail,password) VALUES('arnauddelemelle','arnaud','delemelle','a.delemelle@yopmail.com','good');
INSERT INTO User (username,firstname,name,mail,password) VALUES('antoine.ver','antoine','verlaine','a.verlaine@yopmail.com','ilovesqlite');
INSERT INTO User (username,firstname,name,mail,password) VALUES('donadede','donatien','dede','d.dede@yopmail.com','tomorrow');
INSERT INTO User (username,firstname,name,mail,password) VALUES('louisandrein','louis','andrein','l.andrein@yopmail.com','password');
INSERT INTO User (username,firstname,name,mail,password) VALUES('pierre.jean','pierre','jean','p.jean@yopmail.com','sayit');
INSERT INTO User (username,firstname,name,mail,password) VALUES('camilla.verton','camilla','verton','c.verton@yopmail.com','123456');
INSERT INTO User (username,firstname,name,mail,password) VALUES('florence.jenne','florence','jenne','f.jenne@yopmail.com','mysqlpass');
INSERT INTO User (username,firstname,name,mail,password) VALUES('ingid.pierret','ingrid','pierret','i.pierret@yopmail.com','ilovesqlite');
INSERT INTO User (username,firstname,name,mail,password) VALUES('natasha.civ','Natasha','Civi','n.civi@yopmail.com','kothello');

INSERT INTO Notification VALUES('c.haron',1,'Donatien Dede vous demande en amis','donadede',0);

INSERT INTO Friend_list VALUES('donadede','c.haron',0);
INSERT INTO Friend_list VALUES('pierre.jean','rogerdu',1);
INSERT INTO Friend_list VALUES('donadede','nico',0);
INSERT INTO Friend_list VALUES('camilla.verton','c.haron',1);
INSERT INTO Friend_list VALUES('ingid.pierret','arnauddelemelle',1);
INSERT INTO Friend_list VALUES('ingid.pierret','antoine.ver',1);
INSERT INTO Friend_list VALUES('florence.jenne','pierre.jean',1);
INSERT INTO Friend_list VALUES('florence.jenne','natasha.civ',1);
INSERT INTO Friend_list VALUES('arnauddelemelle','nico',1);
INSERT INTO Friend_list VALUES('c.haron','florence.jenne',1);
INSERT INTO Friend_list VALUES('rogerdu','camilla.verton',1);
INSERT INTO Friend_list VALUES('rogerdu','donadede',1);
INSERT INTO Friend_list VALUES('rogerdu','arnauddelemelle',1);
INSERT INTO Friend_list VALUES('antoine.ver','nico',1);
INSERT INTO Friend_list VALUES('nico','louisandrein',1);
INSERT INTO Friend_list VALUES('arnauddelemelle','roger.charles',1);
COMMIT TRANSACTION;
PRAGMA foreign_keys = on;
