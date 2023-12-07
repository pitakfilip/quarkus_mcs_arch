-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;

-- private TroopType troopType;
    -- MELEE,
    -- RANGED,
    -- SIEGE
-- private TroopClass troopClass;
    -- INFANTRYSWORD,
    -- MACEMAN,
    -- CALVARYSWORD,
    -- ARCHER,
    -- CROSSBOW_TROOP,
    -- RAM_VEHICLE,
    -- TREBUCHET,
insert into troop (abstract, troopType, troopClass, hp, originalHp, dps, armor) values('Troop', 0, 2, 100, 100, 8, 0);
insert into troop (abstract, troopType, troopClass, hp, originalHp, dps, armor) values('Troop', 1, 3, 200, 200, 12, 0);
insert into troop (abstract, troopType, troopClass, hp, originalHp, dps, armor) values('Troop', 0, 0, 100, 100, 4, 0);
insert into troop (abstract, troopType, troopClass, hp, originalHp, dps, armor) values('Troop', 0, 0, 100, 100, 6, 0);
insert into troop (abstract, troopType, troopClass, hp, originalHp, dps, armor) values('Troop', 0, 0, 100, 100, 6, 0);
insert into troop (abstract, troopType, troopClass, hp, originalHp, dps, armor) values('Troop', 0, 0, 100, 100, 6, 0);
insert into troop (abstract, troopType, troopClass, hp, originalHp, dps, armor) values('Troop', 0, 0, 100, 100, 6, 0);
alter sequence troop_id_seq restart with 8;

insert into battle (status, attacker, defender) values(0, 1, 2);
insert into battle (status, attacker, defender) values(1, 2, 3);
alter sequence battle_id_seq restart with 3;

insert into attacker_troops (id, troop_id) values(1, 1);
insert into defender_troops (id, troop_id) values(1, 2);
insert into attacker_troops (id, troop_id) values(2, 3);
insert into attacker_troops (id, troop_id) values(2, 4);
insert into attacker_troops (id, troop_id) values(2, 5);
insert into defender_troops (id, troop_id) values(2, 6);
insert into defender_troops (id, troop_id) values(2, 7);
