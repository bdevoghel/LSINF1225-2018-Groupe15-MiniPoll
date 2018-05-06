PRAGMA foreign_keys = off;
BEGIN TRANSACTION;

-- Table: User
DROP TABLE IF EXISTS User;
CREATE TABLE User (username TEXT NOT NULL UNIQUE PRIMARY KEY,firstname TEXT NOT NULL,name TEXT NOT NULL,mail TEXT NOT NULL,password TEXT NOT NULL,photo TEXT, favori TEXT, FOREIGN KEY (favori) REFERENCES Utilisateur (username));
CREATE TABLE Poll (username_proprietaire TEXT NOT NULL ,idpoll TEXT NOT NULL PRIMARY KEY,titre TEXT NOT NULL,description TEXT NOT NULL,types TEXT(1) NOT NULL,status_principal INTEGER);
CREATE TABLE Question_list (idpoll INTEGER,idquestion TEXT,description_question TEXT);
CREATE TABLE Notification (username TEXT NOT NULL,etat INTEGER,message TEXT,username_notif TEXT,poll_notif INTEGER);
CREATE TABLE Friend_list (username TEXT NOT NULL,username_amis TEXT NOT NULL,etat INTEGER NOT NULL);
CREATE TABLE Questionnaire_and_Advice (idquestion TEXT NOT NULL,texte TEXT NOT NULL,reponse INTEGER NOT NULL);
CREATE TABLE Questionnaire_and_Advice_Answer (username TEXT NOT NULL,idquestion TEXT NOT NULL,reponse_utilisateur TEXT);
CREATE TABLE Survey (idpoll TEXT NOT NULL,data_reponse TEXT NOT NULL);
CREATE TABLE Survey_Answer (idpoll TEXT NOT NULL,username TEXT NOT NULL ,reponse TEXT NOT NULL,ordre INTEGER NOT NULL);
CREATE TABLE Poll_access (idpoll TEXT NOT NULL,username TEXT NOT NULL,statut_particulier FLOAT);
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
INSERT INTO Poll VALUES('roger.charles','P1','Asterosismologie','repondez ci-dessous','Q',0);
INSERT INTO Poll VALUES('rogerdu','P2','Cinema','repondez ci-dessous','S',1);
INSERT INTO Poll VALUES('pierre.jean','P3','Dans quelle capitale va t on ?','repondez ci-dessous','S',0);
INSERT INTO Poll VALUES('nico','P4','Quelle est le meilleur language de programmation','repondez ci-dessous','S',0);
INSERT INTO Poll VALUES('ingid.pierret','P5','Mathematique','Es-tu un genie ? Pour le savoir repond au question :)','Q',1);
INSERT INTO Poll VALUES('ingid.pierret','P7','Aide moi a choisir','Quel vêtement dois-je acheter ?','C',0);
INSERT INTO Notification VALUES('rogerdu',0,'Pierre Jean a cree un sondage, allez vite y repondre',NULL,'P3');
INSERT INTO Notification VALUES('c.haron',1,'Donatien Dede vous demande en amis','donadede',NULL);
INSERT INTO Notification VALUES('arnauddelemelle',1,'Ingrid Pierret demande votre aide',NULL,'P7');
INSERT INTO Notification VALUES('florence.jenne',1,'Pierre Jean a cree un sondage, allez vite y repondre',NULL,'P3');
INSERT INTO Notification VALUES('nico',1,'Donatien Dede vous demande en amis','donadede',NULL);
INSERT INTO Notification VALUES('roger.charles',1,'Pierre Jean a cree un sondage, allez vite y repondre',NULL,'P3');
INSERT INTO Notification VALUES('arnauddelemelle',0,'Ingrid Pierret a cree un questionnaire, allez vite y repondre',NULL,'P5');
INSERT INTO Notification VALUES('natasha.civ',0,'Ingrid Pierret a cree un questionnaire, allez vite y repondre',NULL,'P5');
INSERT INTO Notification VALUES('pierre.jean',0,'Roger Charles a cree un questionnaire, allez vite y repondre',NULL,'P1');
INSERT INTO Notification VALUES('natasha.civ',0,'Roger Charles a cree un questionnaire, allez vite y repondre',NULL,'P1');
INSERT INTO Notification VALUES('arnauddelemelle',0,'Roger Charles a cree un questionnaire, allez vite y repondre',NULL,'P1');
INSERT INTO Notification VALUES('camilla.verton',1,'Roger Dupont a cree un sondage, allez vite y repondre',NULL,'P2');
INSERT INTO Notification VALUES('donadede',1,'Roger Dupont a cree un sondage, allez vite y repondre',NULL,'P2');
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
INSERT INTO Question_list VALUES('P1','C1','qu est-ce que l asterosismologie?');
INSERT INTO Question_list VALUES('P1','C2','Quand est ce que ca a ete cree?');
INSERT INTO Question_list VALUES('P1','C3','Est ce toujours d application?');
INSERT INTO Question_list VALUES('P5','C4','Que font 2*2-4?');
INSERT INTO Question_list VALUES('P5','C5','Quelle est le plus grand commun diviseur entre 3 et 4?');
INSERT INTO Question_list VALUES('P7','C8','Quelle couleur?');
INSERT INTO Questionnaire_and_Advice VALUES('C1','Une technique d etude de la structure interne des etoiles.',1);
INSERT INTO Questionnaire_and_Advice VALUES('C1','Une etude sur les singes',0);
INSERT INTO Questionnaire_and_Advice VALUES('C1','Une etude sur la vivabilite d une autre planete',0);
INSERT INTO Questionnaire_and_Advice VALUES('C2','Dans les annees 90',0);
INSERT INTO Questionnaire_and_Advice VALUES('C2','Dans les annees 80',0);
INSERT INTO Questionnaire_and_Advice VALUES('C2','En Antiquite',1);
INSERT INTO Questionnaire_and_Advice VALUES('C3','OUI',1);
INSERT INTO Questionnaire_and_Advice VALUES('C3','NON',0);
INSERT INTO Questionnaire_and_Advice VALUES('C4','1',0);
INSERT INTO Questionnaire_and_Advice VALUES('C4','-1',0);
INSERT INTO Questionnaire_and_Advice VALUES('C4','Aucune de ces reponses',0);
INSERT INTO Questionnaire_and_Advice VALUES('C5','1',1);
INSERT INTO Questionnaire_and_Advice VALUES('C5','2',0);
INSERT INTO Questionnaire_and_Advice VALUES('C5','3',0);
INSERT INTO Questionnaire_and_Advice VALUES('C5','4',0);
INSERT INTO Questionnaire_and_Advice VALUES('C8','Le pantalon rouge',0);
INSERT INTO Questionnaire_and_Advice VALUES('C8','Le pantalon brun',0);
INSERT INTO Questionnaire_and_Advice_Answer VALUES('pierre.jean','C1','Une etude sur les singes');
INSERT INTO Questionnaire_and_Advice_Answer VALUES('pierre.jean','C2','En Antiquite');
INSERT INTO Questionnaire_and_Advice_Answer VALUES('pierre.jean','C3','NON');
INSERT INTO Questionnaire_and_Advice_Answer VALUES('natasha.civ','C1','Une etude sur les singes');
INSERT INTO Questionnaire_and_Advice_Answer VALUES('natasha.civ','C2','Dans les annees 80');
INSERT INTO Questionnaire_and_Advice_Answer VALUES('natasha.civ','C3','OUI');
INSERT INTO Questionnaire_and_Advice_Answer VALUES('arnauddelemelle','C1','Une etude sur les singes');
INSERT INTO Questionnaire_and_Advice_Answer VALUES('arnauddelemelle','C2','En Antiquite');
INSERT INTO Questionnaire_and_Advice_Answer VALUES('arnauddelemelle','C4','0');
INSERT INTO Questionnaire_and_Advice_Answer VALUES('arnauddelemelle','C5','2');
INSERT INTO Questionnaire_and_Advice_Answer VALUES('antoine.ver','C4','1');
INSERT INTO Questionnaire_and_Advice_Answer VALUES('antoine.ver','C5','1');
INSERT INTO Questionnaire_and_Advice_Answer VALUES('arnauddelemelle','C8','Le pantalon brun');
INSERT INTO Survey VALUES('P2','Forest Gump');
INSERT INTO Survey VALUES('P2','Minority report');
INSERT INTO Survey VALUES('P2','Joshua: Teenager vs. Superpower');
INSERT INTO Survey VALUES('P3','Bruxelle');
INSERT INTO Survey VALUES('P3','Berlin');
INSERT INTO Survey VALUES('P3','Washington');
INSERT INTO Survey VALUES('P3','Rome');
INSERT INTO Survey VALUES('P4','OZ');
INSERT INTO Survey VALUES('P4','C++');
INSERT INTO Survey VALUES('P4','Java');
INSERT INTO Survey VALUES('P4','C');
INSERT INTO Survey_Answer VALUES('P2','camilla.verton','Forest Gump',1);
INSERT INTO Survey_Answer VALUES('P2','camilla.verton','Minority report',3);
INSERT INTO Survey_Answer VALUES('P2','camilla.verton','Joshua: Teenager vs. Superpower',2);
INSERT INTO Survey_Answer VALUES('P2','donadede','Forest Gump',2);
INSERT INTO Survey_Answer VALUES('P2','donadede','Minority report',1);
INSERT INTO Survey_Answer VALUES('P2','donadede','Joshua: Teenager vs. Superpower',3);
INSERT INTO Survey_Answer VALUES('P3','florence.jenne','Berlin',4);
INSERT INTO Survey_Answer VALUES('P3','roger.charles','Rome',4);
INSERT INTO Survey_Answer VALUES('P3','roger.charles','Bruxelle',3);
INSERT INTO Survey_Answer VALUES('P3','roger.charles','Washington',2);
INSERT INTO Survey_Answer VALUES('P4','donadede','OZ',1);
INSERT INTO Survey_Answer VALUES('P4','donadede','C++',2);
INSERT INTO Survey_Answer VALUES('P4','donadede','Java',3);
INSERT INTO Survey_Answer VALUES('P4','donadede','C',4);
INSERT INTO Poll_access VALUES('P1','pierre.jean',1);
INSERT INTO Poll_access VALUES('P1','natasha.civ',1);
INSERT INTO Poll_access VALUES('P1','arnauddelemelle',0.6);
INSERT INTO Poll_access VALUES('P2','camilla.verton',1);
INSERT INTO Poll_access VALUES('P2','donadede',1);
INSERT INTO Poll_access VALUES('P3','rogerdu',0);
INSERT INTO Poll_access VALUES('P3','florence.jenne',1);
INSERT INTO Poll_access VALUES('P3','roger.charles',0);
INSERT INTO Poll_access VALUES('P4','louisandrein',0);
INSERT INTO Poll_access VALUES('P4','donadede',1);
INSERT INTO Poll_access VALUES('P5','arnauddelemelle',1);
INSERT INTO Poll_access VALUES('P5','antoine.ver',1);
INSERT INTO Poll_access VALUES('P5','arnauddelemelle',0);
INSERT INTO Poll_access VALUES('P5','antoine.ver',0);
INSERT INTO Poll_access VALUES('P5','donadede',1);
INSERT INTO Poll_access VALUES('P5','louisandrein',1);
INSERT INTO Poll_access VALUES('P7','arnauddelemelle',1);
COMMIT TRANSACTION;
PRAGMA foreign_keys = on;
