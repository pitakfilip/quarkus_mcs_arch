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
insert into troop (abstract, troopType, troopClass, hp, originalHp, dps, armor, target) values('Troop', 0, 2, 84, 100, 12, 0, 0);
insert into troop (abstract, troopType, troopClass, hp, originalHp, dps, armor, target) values('Troop', 1, 3, 176, 200, 8, 0, 0);
insert into troop (abstract, troopType, troopClass, hp, originalHp, dps, armor, target) values('Troop', 0, 0, 100, 100, 4, 0, -1);
insert into troop (abstract, troopType, troopClass, hp, originalHp, dps, armor, target) values('Troop', 0, 0, 100, 100, 4, 0, -1);
insert into troop (abstract, troopType, troopClass, hp, originalHp, dps, armor, target) values('Troop', 0, 0, 100, 100, 4, 0, -1);
insert into troop (abstract, troopType, troopClass, hp, originalHp, dps, armor, target) values('Troop', 0, 1, 100, 100, 7, 0, -1);
insert into troop (abstract, troopType, troopClass, hp, originalHp, dps, armor, target) values('Troop', 0, 1, 100, 100, 7, 0, -1);
alter sequence troop_id_seq restart with 8;

-- private BattleStatus status;
    -- WAITING,
    -- ONGOING,
    -- FINISHING,
    -- FINISHED
-- private BattleResult result;
    -- UNDECIDED,
    -- ATTACKER_WON,
    -- DEFENDER_WON
insert into battle (status, result, round, attacker, defender) values(1, 0, 2, 1, 2);
insert into battle (status, result, round, attacker, defender) values(0, 0, 0, 2, 3);
alter sequence battle_id_seq restart with 3;

insert into attacker_troops (id, troop_id) values(1, 1);
insert into defender_troops (id, troop_id) values(1, 2);
insert into attacker_troops (id, troop_id) values(2, 3);
insert into attacker_troops (id, troop_id) values(2, 4);
insert into attacker_troops (id, troop_id) values(2, 5);
insert into defender_troops (id, troop_id) values(2, 6);
insert into defender_troops (id, troop_id) values(2, 7);
