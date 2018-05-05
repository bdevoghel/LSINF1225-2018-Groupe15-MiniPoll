--
-- File generated with SQLiteStudio v3.1.1 on sam. mai 5 10:57:56 2018
--
-- Text encoding used: System
--
PRAGMA foreign_keys = off;
BEGIN TRANSACTION;

-- Table: acces_poll
CREATE TABLE acces_poll (
idpoll STRING not null,
identifiant STRING not null,
statut_particulier float  -- 0 si l'utilisateur n'a pas repondu au poll, 1 si il l'a termine, et entre 0 et 1 si le poll est partiellement complete
);
INSERT INTO acces_poll (idpoll, identifiant, statut_particulier) VALUES ('P1', 'pierre.jean', 1);
INSERT INTO acces_poll (idpoll, identifiant, statut_particulier) VALUES ('P1', 'natasha.civ', 1);
INSERT INTO acces_poll (idpoll, identifiant, statut_particulier) VALUES ('P1', 'arnauddelemelle', 0.6);
INSERT INTO acces_poll (idpoll, identifiant, statut_particulier) VALUES ('P2', 'camilla.verton', 1);
INSERT INTO acces_poll (idpoll, identifiant, statut_particulier) VALUES ('P2', 'donadede', 1);
INSERT INTO acces_poll (idpoll, identifiant, statut_particulier) VALUES ('P3', 'rogerdu', 0);
INSERT INTO acces_poll (idpoll, identifiant, statut_particulier) VALUES ('P3', 'florence.jenne', 1);
INSERT INTO acces_poll (idpoll, identifiant, statut_particulier) VALUES ('P3', 'roger.charles', 0);
INSERT INTO acces_poll (idpoll, identifiant, statut_particulier) VALUES ('P4', 'louisandrein', 0);
INSERT INTO acces_poll (idpoll, identifiant, statut_particulier) VALUES ('P4', 'donadede', 1);
INSERT INTO acces_poll (idpoll, identifiant, statut_particulier) VALUES ('P5', 'arnauddelemelle', 1);
INSERT INTO acces_poll (idpoll, identifiant, statut_particulier) VALUES ('P5', 'antoine.ver', 1);
INSERT INTO acces_poll (idpoll, identifiant, statut_particulier) VALUES ('P5', 'arnauddelemelle', 0);
INSERT INTO acces_poll (idpoll, identifiant, statut_particulier) VALUES ('P5', 'antoine.ver', 0);
INSERT INTO acces_poll (idpoll, identifiant, statut_particulier) VALUES ('P5', 'donadede', 1);
INSERT INTO acces_poll (idpoll, identifiant, statut_particulier) VALUES ('P5', 'louisandrein', 1);
INSERT INTO acces_poll (idpoll, identifiant, statut_particulier) VALUES ('P7', 'arnauddelemelle', 1);

-- Table: liste_amis
CREATE TABLE liste_amis(
    	identifiant STRING not null,
	identifiant_amis STRING not null,
	etat int not null --0 = en attente de confirmation et 1 = accepte
    	);
INSERT INTO liste_amis (identifiant, identifiant_amis, etat) VALUES ('donadede', 'c.haron', 0);
INSERT INTO liste_amis (identifiant, identifiant_amis, etat) VALUES ('pierre.jean', 'rogerdu', 1);
INSERT INTO liste_amis (identifiant, identifiant_amis, etat) VALUES ('donadede', 'nico', 0);
INSERT INTO liste_amis (identifiant, identifiant_amis, etat) VALUES ('camilla.verton', 'c.haron', 1);
INSERT INTO liste_amis (identifiant, identifiant_amis, etat) VALUES ('ingid.pierret', 'arnauddelemelle', 1);
INSERT INTO liste_amis (identifiant, identifiant_amis, etat) VALUES ('ingid.pierret', 'antoine.ver', 1);
INSERT INTO liste_amis (identifiant, identifiant_amis, etat) VALUES ('florence.jenne', 'pierre.jean', 1);
INSERT INTO liste_amis (identifiant, identifiant_amis, etat) VALUES ('florence.jenne', 'natasha.civ', 1);
INSERT INTO liste_amis (identifiant, identifiant_amis, etat) VALUES ('arnauddelemelle', 'nico', 1);
INSERT INTO liste_amis (identifiant, identifiant_amis, etat) VALUES ('c.haron', 'florence.jenne', 1);
INSERT INTO liste_amis (identifiant, identifiant_amis, etat) VALUES ('rogerdu', 'camilla.verton', 1);
INSERT INTO liste_amis (identifiant, identifiant_amis, etat) VALUES ('rogerdu', 'donadede', 1);
INSERT INTO liste_amis (identifiant, identifiant_amis, etat) VALUES ('rogerdu', 'arnauddelemelle', 1);
INSERT INTO liste_amis (identifiant, identifiant_amis, etat) VALUES ('antoine.ver', 'nico', 1);
INSERT INTO liste_amis (identifiant, identifiant_amis, etat) VALUES ('nico', 'louisandrein', 1);
INSERT INTO liste_amis (identifiant, identifiant_amis, etat) VALUES ('arnauddelemelle', 'roger.charles', 1);

-- Table: liste_questions
CREATE TABLE liste_questions(
	idpoll int,
	idquestion STRING,
	description_question STRING
  	);
INSERT INTO liste_questions (idpoll, idquestion, description_question) VALUES ('P1', 'C1', 'qu''est-ce que l''asterosismologie?');
INSERT INTO liste_questions (idpoll, idquestion, description_question) VALUES ('P1', 'C2', 'Quand est ce que ca a ete cree?');
INSERT INTO liste_questions (idpoll, idquestion, description_question) VALUES ('P1', 'C3', 'Est ce toujours d''application?');
INSERT INTO liste_questions (idpoll, idquestion, description_question) VALUES ('P5', 'C4', 'Que font 2*2-4?');
INSERT INTO liste_questions (idpoll, idquestion, description_question) VALUES ('P5', 'C5', 'Quelle est le plus grand commun diviseur entre 3 et 4?');
INSERT INTO liste_questions (idpoll, idquestion, description_question) VALUES ('P7', 'C8', 'Quelle couleur?');

-- Table: notifications
CREATE TABLE notifications (
	identifiant STRING not null,
	etat int,
	message STRING,
	identifiant_notif STRING,
	poll_notif int
	);
INSERT INTO notifications (identifiant, etat, message, identifiant_notif, poll_notif) VALUES ('rogerdu', 0, 'Pierre Jean a cree un sondage, allez vite y repondre', NULL, 'P3');
INSERT INTO notifications (identifiant, etat, message, identifiant_notif, poll_notif) VALUES ('c.haron', 1, 'Donatien Dede vous demande en amis', 'donadede', NULL);
INSERT INTO notifications (identifiant, etat, message, identifiant_notif, poll_notif) VALUES ('arnauddelemelle', 1, 'Ingrid Pierret demande votre aide', NULL, 'P7');
INSERT INTO notifications (identifiant, etat, message, identifiant_notif, poll_notif) VALUES ('florence.jenne', 1, 'Pierre Jean a cree un sondage, allez vite y repondre', NULL, 'P3');
INSERT INTO notifications (identifiant, etat, message, identifiant_notif, poll_notif) VALUES ('nico', 1, 'Donatien Dede vous demande en amis', 'donadede', NULL);
INSERT INTO notifications (identifiant, etat, message, identifiant_notif, poll_notif) VALUES ('roger.charles', 1, 'Pierre Jean a cree un sondage, allez vite y repondre', NULL, 'P3');
INSERT INTO notifications (identifiant, etat, message, identifiant_notif, poll_notif) VALUES ('arnauddelemelle', 0, 'Ingrid Pierret a cree un questionnaire, allez vite y repondre', NULL, 'P5');
INSERT INTO notifications (identifiant, etat, message, identifiant_notif, poll_notif) VALUES ('natasha.civ', 0, 'Ingrid Pierret a cree un questionnaire, allez vite y repondre', NULL, 'P5');
INSERT INTO notifications (identifiant, etat, message, identifiant_notif, poll_notif) VALUES ('pierre.jean', 0, 'Roger Charles a cree un questionnaire, allez vite y repondre', NULL, 'P1');
INSERT INTO notifications (identifiant, etat, message, identifiant_notif, poll_notif) VALUES ('natasha.civ', 0, 'Roger Charles a cree un questionnaire, allez vite y repondre', NULL, 'P1');
INSERT INTO notifications (identifiant, etat, message, identifiant_notif, poll_notif) VALUES ('arnauddelemelle', 0, 'Roger Charles a cree un questionnaire, allez vite y repondre', NULL, 'P1');
INSERT INTO notifications (identifiant, etat, message, identifiant_notif, poll_notif) VALUES ('camilla.verton', 1, 'Roger Dupont a cree un sondage, allez vite y repondre', NULL, 'P2');
INSERT INTO notifications (identifiant, etat, message, identifiant_notif, poll_notif) VALUES ('donadede', 1, 'Roger Dupont a cree un sondage, allez vite y repondre', NULL, 'P2');

-- Table: poll
CREATE TABLE poll(
	identifiant_proprietaire STRING not null ,
	idpoll STRING not null primary key,
	titre STRING not null,
	description STRING not null,
	types STRING(1) not null, -- Q pour questionnaire, S pour sondage et A pour choix entre Amis.
	deadline DATETIME,
	status_principal int --avancement du poll en pourcentage (nombre de reponses/nombre total de personne sondees)
    	);
INSERT INTO poll (identifiant_proprietaire, idpoll, titre, description, types, deadline, status_principal) VALUES ('roger.charles', 'P1', 'Asterosismologie', 'repondez ci-dessous', 'Q', 0, 0.2);
INSERT INTO poll (identifiant_proprietaire, idpoll, titre, description, types, deadline, status_principal) VALUES ('rogerdu', 'P2', 'Cinema', 'repondez ci-dessous', 'S', NULL, 0.5);
INSERT INTO poll (identifiant_proprietaire, idpoll, titre, description, types, deadline, status_principal) VALUES ('pierre.jean', 'P3', 'Dans quelle capitale va t''on ?', 'repondez ci-dessous', 'S', 1000, 0);
INSERT INTO poll (identifiant_proprietaire, idpoll, titre, description, types, deadline, status_principal) VALUES ('nico', 'P4', 'Quelle est le meilleur language de programmation', 'repondez ci-dessous', 'S', 50000, 0);
INSERT INTO poll (identifiant_proprietaire, idpoll, titre, description, types, deadline, status_principal) VALUES ('ingid.pierret', 'P5', 'Mathematique', 'Est tu un genie ? Pour le savoir repond au question :)', 'Q', 500, 0.5);
INSERT INTO poll (identifiant_proprietaire, idpoll, titre, description, types, deadline, status_principal) VALUES ('ingid.pierret', 'P7', 'Aide moi a choisir', 'Quel vêtement dois-je acheter ?', 'C', NULL, 0);

-- Table: profil
CREATE TABLE profil (
    	identifiant STRING not null primary key,
    	prenom STRING not null,
	nom STRING not null,
    	email STRING not null,
	mdp STRING not null,
	photo binary,
	favoris STRING
	);
INSERT INTO profil (identifiant, prenom, nom, email, mdp, photo, favoris) VALUES ('rogerdu', 'roger', 'dupont', 'r.dupont@yopmail.com', 'ilovesqlite', NULL, NULL);
INSERT INTO profil (identifiant, prenom, nom, email, mdp, photo, favoris) VALUES ('c.haron', 'carl', 'haroun', 'c.haroun@yopmail.com', 'tuesday', NULL, NULL);
INSERT INTO profil (identifiant, prenom, nom, email, mdp, photo, favoris) VALUES ('roger.charles', 'roger', 'charles', 'r.dupont@yopmail.com', 'titi', NULL, NULL);
INSERT INTO profil (identifiant, prenom, nom, email, mdp, photo, favoris) VALUES ('nico', 'nicolas', 'dupont', 'n.dupont@yopmail.com', 'hello', NULL, NULL);
INSERT INTO profil (identifiant, prenom, nom, email, mdp, photo, favoris) VALUES ('arnauddelemelle', 'arnaud', 'delemelle', 'a.delemelle@yopmail.com', 'good', NULL, NULL);
INSERT INTO profil (identifiant, prenom, nom, email, mdp, photo, favoris) VALUES ('antoine.ver', 'antoine', 'verlaine', 'a.verlaine@yopmail.com', 'ilovesqlite', NULL, NULL);
INSERT INTO profil (identifiant, prenom, nom, email, mdp, photo, favoris) VALUES ('donadede', 'donatien', 'dede', 'd.dede@yopmail.com', 'tomorrow', NULL, NULL);
INSERT INTO profil (identifiant, prenom, nom, email, mdp, photo, favoris) VALUES ('louisandrein', 'louis', 'andrein', 'l.andrein@yopmail.com', 'password', NULL, NULL);
INSERT INTO profil (identifiant, prenom, nom, email, mdp, photo, favoris) VALUES ('pierre.jean', 'pierre', 'jean', 'p.jean@yopmail.com', 'sayit', NULL, NULL);
INSERT INTO profil (identifiant, prenom, nom, email, mdp, photo, favoris) VALUES ('camilla.verton', 'camilla', 'verton', 'c.verton@yopmail.com', 123456, NULL, NULL);
INSERT INTO profil (identifiant, prenom, nom, email, mdp, photo, favoris) VALUES ('florence.jenne', 'florence', 'jenne', 'f.jenne@yopmail.com', 'mysqlpass', NULL, NULL);
INSERT INTO profil (identifiant, prenom, nom, email, mdp, photo, favoris) VALUES ('ingid.pierret', 'ingrid', 'pierret', 'i.pierret@yopmail.com', 'ilovesqlite', NULL, NULL);
INSERT INTO profil (identifiant, prenom, nom, email, mdp, photo, favoris) VALUES ('natasha.civ', 'Natasha', 'Civi', 'n.civi@yopmail.com', 'kothello', NULL, NULL);

-- Table: questionnaire_et_aide
CREATE TABLE questionnaire_et_aide ( --reponse au questionnaire et choixentre amis)
idquestion STRING not null,
texte STRING not null,-- le texte qui decrit la reponse.
reponse int not null-- bonne reponse -> 0 = mauvaise reponse et 1 = bonne reponse. Il ne peut y avoir que une seul bonne reponse
);
INSERT INTO questionnaire_et_aide (idquestion, texte, reponse) VALUES ('C1', 'Une technique d''etude de la structure interne des etoiles.', 1);
INSERT INTO questionnaire_et_aide (idquestion, texte, reponse) VALUES ('C1', 'Une etude sur les singes', 0);
INSERT INTO questionnaire_et_aide (idquestion, texte, reponse) VALUES ('C1', 'Une etude sur la vivabilite d''une autre planete', 0);
INSERT INTO questionnaire_et_aide (idquestion, texte, reponse) VALUES ('C2', 'Dans les annees 90''', 0);
INSERT INTO questionnaire_et_aide (idquestion, texte, reponse) VALUES ('C2', 'Dans les annees 80''', 0);
INSERT INTO questionnaire_et_aide (idquestion, texte, reponse) VALUES ('C2', 'En Antiquite', 1);
INSERT INTO questionnaire_et_aide (idquestion, texte, reponse) VALUES ('C3', 'OUI', 1);
INSERT INTO questionnaire_et_aide (idquestion, texte, reponse) VALUES ('C3', 'NON', 0);
INSERT INTO questionnaire_et_aide (idquestion, texte, reponse) VALUES ('C4', 1, 0);
INSERT INTO questionnaire_et_aide (idquestion, texte, reponse) VALUES ('C4', -1, 0);
INSERT INTO questionnaire_et_aide (idquestion, texte, reponse) VALUES ('C4', 'Aucune de ces reponses', 0);
INSERT INTO questionnaire_et_aide (idquestion, texte, reponse) VALUES ('C5', 1, 1);
INSERT INTO questionnaire_et_aide (idquestion, texte, reponse) VALUES ('C5', 2, 0);
INSERT INTO questionnaire_et_aide (idquestion, texte, reponse) VALUES ('C5', 3, 0);
INSERT INTO questionnaire_et_aide (idquestion, texte, reponse) VALUES ('C5', 4, 0);
INSERT INTO questionnaire_et_aide (idquestion, texte, reponse) VALUES ('C8', 'Le pentalon rouge', 0);
INSERT INTO questionnaire_et_aide (idquestion, texte, reponse) VALUES ('C8', 'Le pentalon brun', 0);

-- Table: reponse_questionnaire_et_aide
CREATE TABLE reponse_questionnaire_et_aide ( --reponse au questionnaire et choixentre amis)
identifiant STRING not null,  --id de ceux qui repondent
idquestion STRING not null, -- question a laquelle l'utilisateur repond
reponse_utilisateur STRING   --reponse de l'utilisateur"
);
INSERT INTO reponse_questionnaire_et_aide (identifiant, idquestion, reponse_utilisateur) VALUES ('pierre.jean', 'C1', 'Une etude sur les singes');
INSERT INTO reponse_questionnaire_et_aide (identifiant, idquestion, reponse_utilisateur) VALUES ('pierre.jean', 'C2', 'En Antiquite');
INSERT INTO reponse_questionnaire_et_aide (identifiant, idquestion, reponse_utilisateur) VALUES ('pierre.jean', 'C3', 'NON');
INSERT INTO reponse_questionnaire_et_aide (identifiant, idquestion, reponse_utilisateur) VALUES ('natasha.civ', 'C1', 'Une etude sur les singes');
INSERT INTO reponse_questionnaire_et_aide (identifiant, idquestion, reponse_utilisateur) VALUES ('natasha.civ', 'C2', 'Dans les annees 80''');
INSERT INTO reponse_questionnaire_et_aide (identifiant, idquestion, reponse_utilisateur) VALUES ('natasha.civ', 'C3', 'OUI');
INSERT INTO reponse_questionnaire_et_aide (identifiant, idquestion, reponse_utilisateur) VALUES ('arnauddelemelle', 'C1', 'Une etude sur les singes');
INSERT INTO reponse_questionnaire_et_aide (identifiant, idquestion, reponse_utilisateur) VALUES ('arnauddelemelle', 'C2', 'En Antiquite');
INSERT INTO reponse_questionnaire_et_aide (identifiant, idquestion, reponse_utilisateur) VALUES ('arnauddelemelle', 'C4', 0);
INSERT INTO reponse_questionnaire_et_aide (identifiant, idquestion, reponse_utilisateur) VALUES ('arnauddelemelle', 'C5', 2);
INSERT INTO reponse_questionnaire_et_aide (identifiant, idquestion, reponse_utilisateur) VALUES ('antoine.ver', 'C4', 1);
INSERT INTO reponse_questionnaire_et_aide (identifiant, idquestion, reponse_utilisateur) VALUES ('antoine.ver', 'C5', 1);
INSERT INTO reponse_questionnaire_et_aide (identifiant, idquestion, reponse_utilisateur) VALUES ('arnauddelemelle', 'C8', 'Le pentalon brun');

-- Table: reponse_sondage
CREATE TABLE reponse_sondage (
idpoll STRING not null,
identifiant STRING not null , --identifiant du sonde
reponse STRING not null,     -- reponse du sonde
ordre int not null-- ordre donne pour la reponse
);
INSERT INTO reponse_sondage (idpoll, identifiant, reponse, ordre) VALUES ('P2', 'camilla.verton', 'Forest Gump', 1);
INSERT INTO reponse_sondage (idpoll, identifiant, reponse, ordre) VALUES ('P2', 'camilla.verton', 'Minority report', 3);
INSERT INTO reponse_sondage (idpoll, identifiant, reponse, ordre) VALUES ('P2', 'camilla.verton', 'Joshua: Teenager vs. Superpower', 2);
INSERT INTO reponse_sondage (idpoll, identifiant, reponse, ordre) VALUES ('P2', 'donadede', 'Forest Gump', 2);
INSERT INTO reponse_sondage (idpoll, identifiant, reponse, ordre) VALUES ('P2', 'donadede', 'Minority report', 1);
INSERT INTO reponse_sondage (idpoll, identifiant, reponse, ordre) VALUES ('P2', 'donadede', 'Joshua: Teenager vs. Superpower', 3);
INSERT INTO reponse_sondage (idpoll, identifiant, reponse, ordre) VALUES ('P3', 'florence.jenne', 'Berlin', 4);
INSERT INTO reponse_sondage (idpoll, identifiant, reponse, ordre) VALUES ('P3', 'roger.charles', 'Rome', 4);
INSERT INTO reponse_sondage (idpoll, identifiant, reponse, ordre) VALUES ('P3', 'roger.charles', 'Bruxelle', 3);
INSERT INTO reponse_sondage (idpoll, identifiant, reponse, ordre) VALUES ('P3', 'roger.charles', 'Washington', 2);
INSERT INTO reponse_sondage (idpoll, identifiant, reponse, ordre) VALUES ('P4', 'donadede', 'OZ', 1);
INSERT INTO reponse_sondage (idpoll, identifiant, reponse, ordre) VALUES ('P4', 'donadede', 'C++', 2);
INSERT INTO reponse_sondage (idpoll, identifiant, reponse, ordre) VALUES ('P4', 'donadede', 'Java', 3);
INSERT INTO reponse_sondage (idpoll, identifiant, reponse, ordre) VALUES ('P4', 'donadede', 'C', 4);

-- Table: sondage
CREATE TABLE sondage (
idpoll STRING not null,
data_reponse STRING not null   --Reponses possibles au sondage
);
INSERT INTO sondage (idpoll, data_reponse) VALUES ('P2', 'Forest Gump');
INSERT INTO sondage (idpoll, data_reponse) VALUES ('P2', 'Minority report');
INSERT INTO sondage (idpoll, data_reponse) VALUES ('P2', 'Joshua: Teenager vs. Superpower');
INSERT INTO sondage (idpoll, data_reponse) VALUES ('P3', 'Bruxelle');
INSERT INTO sondage (idpoll, data_reponse) VALUES ('P3', 'Berlin');
INSERT INTO sondage (idpoll, data_reponse) VALUES ('P3', 'Washington');
INSERT INTO sondage (idpoll, data_reponse) VALUES ('P3', 'Rome');
INSERT INTO sondage (idpoll, data_reponse) VALUES ('P4', 'OZ');
INSERT INTO sondage (idpoll, data_reponse) VALUES ('P4', 'C++');
INSERT INTO sondage (idpoll, data_reponse) VALUES ('P4', 'Java');
INSERT INTO sondage (idpoll, data_reponse) VALUES ('P4', 'C');

COMMIT TRANSACTION;
PRAGMA foreign_keys = on;
