
CREATE DATABASE DemoBCI;

USE DemoBCI;


CREATE TABLE User(

uuid varchar PRIMARY KEY,
name varchar,
email  varchar,
password varchar,
created datetime,
modified datetime,
lastLogin datetime,
token varchar,
isActive boolean
);


CREATE TABLE Phone(

idPhone integer NOT NULL PRIMARY KEY,
number integer,
cityCode integer,
countryCode integer,
uuidUser varchar
CONSTRAINT FK_User FOREIGN KEY (uuidUser)
    REFERENCES User(uuid)
);
