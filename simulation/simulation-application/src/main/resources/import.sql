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
-- 1v1 brawl (already started)
insert into troop (abstract, troopType, troopClass, hp, originalHp, dps, armor, target) values('Troop', 0, 2, 84, 100, 12, 0, 0);
insert into troop (abstract, troopType, troopClass, hp, originalHp, dps, armor, target) values('Troop', 1, 3, 176, 200, 8, 0, 0);
-- 2v3 melee
insert into troop (abstract, troopType, troopClass, hp, originalHp, dps, armor, target) values('Troop', 0, 0, 100, 100, 4, 0, -1);
insert into troop (abstract, troopType, troopClass, hp, originalHp, dps, armor, target) values('Troop', 0, 0, 100, 100, 4, 0, -1);
insert into troop (abstract, troopType, troopClass, hp, originalHp, dps, armor, target) values('Troop', 0, 0, 100, 100, 4, 0, -1);
insert into troop (abstract, troopType, troopClass, hp, originalHp, dps, armor, target) values('Troop', 0, 1, 100, 100, 7, 0, -1);
insert into troop (abstract, troopType, troopClass, hp, originalHp, dps, armor, target) values('Troop', 0, 1, 100, 100, 7, 0, -1);
-- 2v3 melee + archers and big weak rams
insert into troop (abstract, troopType, troopClass, hp, originalHp, dps, armor, target) values('Troop', 0, 0, 100, 100, 4, 1, -1);
insert into troop (abstract, troopType, troopClass, hp, originalHp, dps, armor, target) values('Troop', 0, 0, 100, 100, 4, 1, -1);
insert into troop (abstract, troopType, troopClass, hp, originalHp, dps, armor, target) values('Troop', 0, 0, 100, 100, 4, 1, -1);
insert into troop (abstract, troopType, troopClass, hp, originalHp, dps, armor, target) values('Troop', 0, 1, 100, 100, 6, 1, -1);
insert into troop (abstract, troopType, troopClass, hp, originalHp, dps, armor, target) values('Troop', 0, 1, 100, 100, 6, 1, -1);
insert into troop (abstract, troopType, troopClass, hp, originalHp, dps, armor, target) values('Troop', 1, 3, 50, 50, 3, 0, -1);
insert into troop (abstract, troopType, troopClass, hp, originalHp, dps, armor, target) values('Troop', 1, 3, 50, 50, 3, 0, -1);
insert into troop (abstract, troopType, troopClass, hp, originalHp, dps, armor, target) values('Troop', 1, 3, 50, 50, 3, 0, -1);
insert into troop (abstract, troopType, troopClass, hp, originalHp, dps, armor, target) values('Troop', 1, 3, 50, 50, 3, 0, -1);
insert into troop (abstract, troopType, troopClass, hp, originalHp, dps, armor, target) values('Troop', 1, 3, 50, 50, 3, 0, -1);
insert into troop (abstract, troopType, troopClass, hp, originalHp, dps, armor, target) values('Troop', 0, 5, 200, 200, 2, 3, -1);
insert into troop (abstract, troopType, troopClass, hp, originalHp, dps, armor, target) values('Troop', 0, 5, 200, 200, 2, 3, -1);

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
insert into battle (status, result, round, attacker, defender) values(0, 0, 0, 4, 5);

-- 1v1 brawl (already started)
insert into attacker_troops (id, troop_id) values(1, 1);
insert into defender_troops (id, troop_id) values(1, 2);
-- 2v3 melee
insert into attacker_troops (id, troop_id) values(2, 3);
insert into attacker_troops (id, troop_id) values(2, 4);
insert into attacker_troops (id, troop_id) values(2, 5);
insert into defender_troops (id, troop_id) values(2, 6);
insert into defender_troops (id, troop_id) values(2, 7);
-- 2v3 melee + archers and big weak rams
insert into attacker_troops (id, troop_id) values(3, 8);
insert into attacker_troops (id, troop_id) values(3, 9);
insert into attacker_troops (id, troop_id) values(3, 10);
insert into defender_troops (id, troop_id) values(3, 11);
insert into defender_troops (id, troop_id) values(3, 12);
insert into attacker_troops (id, troop_id) values(3, 13);
insert into attacker_troops (id, troop_id) values(3, 14);
insert into defender_troops (id, troop_id) values(3, 15);
insert into defender_troops (id, troop_id) values(3, 16);
insert into defender_troops (id, troop_id) values(3, 17);
insert into attacker_troops (id, troop_id) values(3, 18);
insert into defender_troops (id, troop_id) values(3, 19);
