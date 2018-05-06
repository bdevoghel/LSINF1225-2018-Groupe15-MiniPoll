PRAGMA foreign_keys = off;
BEGIN TRANSACTION;

DROP TABLE IF EXISTS User;
CREATE TABLE User(Username TEXT NOT NULL UNIQUE PRIMARY KEY, fName TEXT, lName TEXT, email TEXT, password TEXT NOT NULL, photo NUMERIC DEFAULT 'default.jpeg', bestFriend TEXT, FOREIGN KEY (bestFriend) REFERENCES User (Username) );
INSERT INTO User(Username,fName,lName,email,password) VALUES('rogerdu','roger','dupont','r.dupont@yopmail.com','ilovesqlite');

COMMIT TRANSACTION;
PRAGMA foreign_keys = on;