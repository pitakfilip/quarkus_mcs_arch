-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database

INSERT INTO kingdom (experience, level, id, name) VALUES (0, 1,-3, 'OGKingdom');
INSERT INTO domainuser (id, kingdomid, name, password, username) VALUES (-3, -3, 'OGKingdom', 'OGKingdom', 'OGKingdom');

INSERT INTO kingdom (experience, level, id, name) VALUES (0, 1,-2, 'NewKingdom');
INSERT INTO domainuser (id, kingdomid, name, password, username) VALUES (-2, -2, 'NewKingdom', 'NewKingdom','NewKingdom');

INSERT INTO kingdom (experience, level, id, name) VALUES (0, 1,-1, 'TinyKingdom');
INSERT INTO domainuser (id, kingdomid, name, password, username) VALUES (-1, -1, 'TinyKingdom', 'TinyKingdom','TinyKingdom');
