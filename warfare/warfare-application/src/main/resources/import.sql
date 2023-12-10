-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;

-- insert into troop (atwar, level, troopclass, trooptype, abstract) values (false,1,1,1,'jozo');
-- insert into kingdomstroops (id) values (1);
-- insert into kingdoms_troops(id, troop_id) values (1,1)

insert into foundation(gold, id, iron, kingdomid, wood) VALUES (500,1,0,1,0);