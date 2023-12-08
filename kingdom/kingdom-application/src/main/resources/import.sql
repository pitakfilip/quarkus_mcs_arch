-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database

insert into kingdom (name, level, experience) values('Jozkova Dedina na Krivani', 8, 59);
insert into kingdom (name, level, experience) values('TUTUTU TU Max Verstappen ', 16, 420);
insert into kingdom (name, level, experience) values('Egove mesto snov', 2, 69);
insert into kingdom (name, level, experience) values('Lorem Ipsum', 30, 150);
insert into kingdom (name, level, experience) values('[TAA] Trenbolone Acetate Abusers', 19, 55);

insert into domainuser (id, kingdomid, name, password, username) values (1, 1, 'Jozo', 'a', 'Jozo');
insert into domainuser (id, kingdomid, name, password, username) values (2, 2, 'MaxVerstappen', 'a', 'MaxVerstappen');
insert into domainuser (id, kingdomid, name, password, username) values (3, 3, 'EGOJEBO', 'a', 'EGOJEBO');
insert into domainuser (id, kingdomid, name, password, username) values (4, 4, 'ipsumLorem', 'a', 'ipsumLorem');
insert into domainuser (id, kingdomid, name, password, username) values (5, 5, 'Cbum', 'a', 'Cbum');