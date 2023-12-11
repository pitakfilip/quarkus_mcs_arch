/* DROP SCHEMAS AND USERS */
drop sequence if exists DomainUser_SEQ;

DROP TABLE IF EXISTS warfare_prod.troopclasslevel_trooplevel;
DROP TABLE IF EXISTS warfare_prod.troopclasslevel;
DROP TABLE IF EXISTS warfare_prod.kingdoms_troops;
DROP TABLE IF EXISTS warfare_prod.troop;
DROP TABLE IF EXISTS warfare_prod.kingdomstroops;

DROP TABLE IF EXISTS foundation_prod.producers;
DROP TABLE IF EXISTS foundation_prod.resourceproducer;
DROP TABLE IF EXISTS foundation_prod.defences;
DROP TABLE IF EXISTS foundation_prod.defence;
DROP TABLE IF EXISTS foundation_prod.foundation;

DROP TABLE IF EXISTS kingdom_prod.kingdom;
DROP TABLE IF EXISTS kingdom_prod.domainuser;

DROP SCHEMA IF EXISTS simulation_prod CASCADE;
DROP SCHEMA IF EXISTS warfare_prod CASCADE;
DROP SCHEMA IF EXISTS foundation_prod CASCADE;
DROP SCHEMA IF EXISTS kingdom_prod CASCADE;

DROP USER IF EXISTS simulation_acc;
DROP USER IF EXISTS warfare_acc;
DROP USER IF EXISTS foundation_acc;
DROP USER IF EXISTS kingdom_acc;











/*CREATE USERS AND SCHEMAS*/
CREATE USER kingdom_acc WITH PASSWORD 'k';
CREATE SCHEMA kingdom_prod AUTHORIZATION kingdom_acc;
ALTER USER kingdom_acc SET SEARCH_PATH=kingdom_prod;

CREATE USER foundation_acc WITH PASSWORD 'f';
CREATE SCHEMA foundation_prod AUTHORIZATION foundation_acc;
ALTER USER foundation_acc SET SEARCH_PATH=foundation_prod;

CREATE USER warfare_acc WITH PASSWORD 'w';
CREATE SCHEMA warfare_prod AUTHORIZATION warfare_acc;
ALTER USER warfare_acc SET SEARCH_PATH=warfare_prod;

CREATE USER simulation_acc WITH PASSWORD 's';
CREATE SCHEMA simulation_prod AUTHORIZATION simulation_acc;
ALTER USER simulation_acc SET SEARCH_PATH=simulation_prod;


create table kingdom_prod.domainuser
(
    id        bigserial not null
        primary key,
    kingdomid bigint not null,
    name      varchar(255),
    password  varchar(255),
    username  varchar(255)
);

alter table kingdom_prod.domainuser
    owner to kingdom_acc;

create table kingdom_prod.kingdom
(
    experience integer,
    level      integer,
    id         bigserial
        primary key,
    name       varchar(255)
);

alter table kingdom_prod.kingdom
    owner to kingdom_acc;





create table foundation_prod.defence
(
    armor   integer     not null,
    health  integer     not null,
    level   integer     not null,
    id      bigserial
        primary key,
    defence varchar(31) not null
);

alter table foundation_prod.defence
    owner to foundation_acc;

create table foundation_prod.foundation
(
    gold      bigint,
    id        bigserial
        primary key,
    iron      bigint,
    kingdomid bigint,
    wood      bigint
);

alter table foundation_prod.foundation
    owner to foundation_acc;

create table foundation_prod.defences
(
    defence_id bigint not null
        unique
        constraint fkisba15tgffobwijhh5ldt80b5
            references foundation_prod.defence,
    id         bigint not null
        constraint fkkfwcw4j7fvefbto73gfgb5x8r
            references foundation_prod.foundation
);

alter table foundation_prod.defences
    owner to foundation_acc;

create table foundation_prod.resourceproducer
(
    level             integer     not null,
    perminutebase     integer,
    produce           smallint
        constraint resourceproducer_produce_check
            check ((produce >= 0) AND (produce <= 2)),
    producemultiplier double precision,
    id                bigserial
        primary key,
    producer          varchar(31) not null
);

alter table foundation_prod.resourceproducer
    owner to foundation_acc;

create table foundation_prod.producers
(
    id          bigint not null
        constraint fk5vu9m5lnsesbbmbhvu6602p5i
            references foundation_prod.foundation,
    producer_id bigint not null
        unique
        constraint fk4ta3qsjarearhrd23w3bc0i18
            references foundation_prod.resourceproducer
);

alter table foundation_prod.producers
    owner to foundation_acc;







create table warfare_prod.kingdomstroops
(
    id bigserial not null
        primary key
);

alter table warfare_prod.kingdomstroops
    owner to warfare_acc;

create table warfare_prod.troop
(
    atwar      boolean     not null,
    level      integer     not null,
    troopclass smallint
        constraint troop_troopclass_check
            check ((troopclass >= 0) AND (troopclass <= 6)),
    trooptype  smallint
        constraint troop_trooptype_check
            check ((trooptype >= 0) AND (trooptype <= 2)),
    id         bigserial
        primary key,
    abstract   varchar(31) not null
);

alter table warfare_prod.troop
    owner to warfare_acc;

create table warfare_prod.kingdoms_troops
(
    id       bigint not null
        constraint fkdp7alhmdo87qc24xr51lyhafq
            references warfare_prod.kingdomstroops,
    troop_id bigint not null
        unique
        constraint fkp10704c9n4tfa901tqk3lsl0h
            references warfare_prod.troop
);

alter table warfare_prod.kingdoms_troops
    owner to warfare_acc;

create table warfare_prod.troopclasslevel
(
    level      integer not null,
    troopclass smallint
        constraint troopclasslevel_troopclass_check
            check ((troopclass >= 0) AND (troopclass <= 6)),
    id         bigserial
        primary key,
    kingdomid  bigint
);

alter table warfare_prod.troopclasslevel
    owner to warfare_acc;

create table warfare_prod.troopclasslevel_trooplevel
(
    trooplevel         integer,
    trooplevel_key     smallint not null
        constraint troopclasslevel_trooplevel_trooplevel_key_check
            check ((trooplevel_key >= 0) AND (trooplevel_key <= 6)),
    troopclasslevel_id bigint   not null
        constraint fk44w0lwo34xjgw18yc55xcmcgv
            references warfare_prod.troopclasslevel,
    primary key (trooplevel_key, troopclasslevel_id)
);

alter table warfare_prod.troopclasslevel_trooplevel
    owner to warfare_acc;




/* inserts for first kingdom */

INSERT INTO kingdom_prod.kingdom (experience, level, id, name) VALUES (0, 1,-3, 'OGKingdom');
INSERT INTO kingdom_prod.domainuser (id, kingdomid, name, password, username) VALUES (-3, -3, 'OGKingdom', 'OGKingdom', 'OGKingdom');

INSERT INTO foundation_prod.foundation(gold, id, iron, kingdomid, wood) VALUES (500,-3,850,-3,1200);
INSERT INTO foundation_prod.resourceproducer (level, perminutebase, produce, producemultiplier, id, producer) VALUES (0, 60, 0, 2.3, -12, 'LUMBER');
INSERT INTO foundation_prod.resourceproducer (level, perminutebase, produce, producemultiplier, id, producer) VALUES (0, 60, 0, 2.3, -11, 'LUMBER');
INSERT INTO foundation_prod.resourceproducer (level, perminutebase, produce, producemultiplier, id, producer) VALUES (0, 45, 1, 2, -10, 'IRON');
INSERT INTO foundation_prod.resourceproducer (level, perminutebase, produce, producemultiplier, id, producer) VALUES (0, 30, 2, 1.4, -9, 'GOLD');
INSERT INTO foundation_prod.defence (armor, health, level, id, defence) VALUES (20, 140, 1, -8,  'WALL');
INSERT INTO foundation_prod.defence (armor, health, level,id,  defence) VALUES (15, 60, 1, -7, 'ARCHER_TOWER');
INSERT INTO foundation_prod.defences (defence_id, id) VALUES (-8, -3);
INSERT INTO foundation_prod.defences (defence_id, id) VALUES (-7, -3);
INSERT INTO foundation_prod.producers (id, producer_id) VALUES (-3, -12);
INSERT INTO foundation_prod.producers (id, producer_id) VALUES (-3, -11);
INSERT INTO foundation_prod.producers (id, producer_id) VALUES (-3, -10);
INSERT INTO foundation_prod.producers (id, producer_id) VALUES (-3, -9);


INSERT INTO warfare_prod.kingdomstroops (id) VALUES (-3);
INSERT INTO warfare_prod.troopclasslevel (level, troopclass, id, kingdomid) VALUES (0, null, -3, -3);

INSERT INTO warfare_prod.troopclasslevel_trooplevel (trooplevel, trooplevel_key, troopclasslevel_id) VALUES (1, 4, -3);
INSERT INTO warfare_prod.troopclasslevel_trooplevel (trooplevel, trooplevel_key, troopclasslevel_id) VALUES (1, 2, -3);
INSERT INTO warfare_prod.troopclasslevel_trooplevel (trooplevel, trooplevel_key, troopclasslevel_id) VALUES (1, 6, -3);
INSERT INTO warfare_prod.troopclasslevel_trooplevel (trooplevel, trooplevel_key, troopclasslevel_id) VALUES (1, 3, -3);
INSERT INTO warfare_prod.troopclasslevel_trooplevel (trooplevel, trooplevel_key, troopclasslevel_id) VALUES (1, 1, -3);
INSERT INTO warfare_prod.troopclasslevel_trooplevel (trooplevel, trooplevel_key, troopclasslevel_id) VALUES (1, 0, -3);
INSERT INTO warfare_prod.troopclasslevel_trooplevel (trooplevel, trooplevel_key, troopclasslevel_id) VALUES (1, 5, -3);


INSERT INTO warfare_prod.troop (atwar, level, troopclass, trooptype,  abstract) VALUES (false, 1, 2, 0,  'calvary');
INSERT INTO warfare_prod.troop (atwar, level, troopclass, trooptype,  abstract) VALUES (false, 1, 2, 0,  'calvary');
INSERT INTO warfare_prod.troop (atwar, level, troopclass, trooptype,  abstract) VALUES (false, 1, 2, 0,  'calvary');
INSERT INTO warfare_prod.troop (atwar, level, troopclass, trooptype,  abstract) VALUES (false, 1, 0, 0, 'infantrysword');
INSERT INTO warfare_prod.troop (atwar, level, troopclass, trooptype,  abstract) VALUES (false, 1, 0, 0,  'infantrysword');
INSERT INTO warfare_prod.troop (atwar, level, troopclass, trooptype,  abstract) VALUES (false, 1, 1, 0,  'macemen');
INSERT INTO warfare_prod.troop (atwar, level, troopclass, trooptype,  abstract) VALUES (false, 1, 1, 0, 'macemen');
INSERT INTO warfare_prod.troop (atwar, level, troopclass, trooptype,  abstract) VALUES (false, 1, 1, 0,  'macemen');
INSERT INTO warfare_prod.troop (atwar, level, troopclass, trooptype,  abstract) VALUES (false, 1, 1, 0,  'macemen');
INSERT INTO warfare_prod.troop (atwar, level, troopclass, trooptype,  abstract) VALUES (false, 1, 1, 0,  'macemen');
INSERT INTO warfare_prod.troop (atwar, level, troopclass, trooptype,  abstract) VALUES (false, 1, 1, 0,  'macemen');
INSERT INTO warfare_prod.troop (atwar, level, troopclass, trooptype,  abstract) VALUES (false, 1, 1, 0,  'macemen');
INSERT INTO warfare_prod.troop (atwar, level, troopclass, trooptype,  abstract) VALUES (false, 1, 1, 0,  'macemen');
INSERT INTO warfare_prod.troop (atwar, level, troopclass, trooptype,  abstract) VALUES (false, 1, 1, 0,  'macemen');
INSERT INTO warfare_prod.troop (atwar, level, troopclass, trooptype,  abstract) VALUES (false, 1, 1, 0,  'macemen');
INSERT INTO warfare_prod.troop (atwar, level, troopclass, trooptype,  abstract) VALUES (false, 1, 3, 1,  'archers');
INSERT INTO warfare_prod.troop (atwar, level, troopclass, trooptype,  abstract) VALUES (false, 1, 3, 1,  'archers');
INSERT INTO warfare_prod.troop (atwar, level, troopclass, trooptype,  abstract) VALUES (false, 1, 3, 1,  'archers');
INSERT INTO warfare_prod.troop (atwar, level, troopclass, trooptype,  abstract) VALUES (false, 1, 3, 1, 'archers');
INSERT INTO warfare_prod.troop (atwar, level, troopclass, trooptype,  abstract) VALUES (false, 1, 3, 1, 'archers');
INSERT INTO warfare_prod.troop (atwar, level, troopclass, trooptype,  abstract) VALUES (false, 1, 4, 1,  'crossbowmen');
INSERT INTO warfare_prod.troop (atwar, level, troopclass, trooptype,  abstract) VALUES (false, 1, 4, 1,  'crossbowmen');
INSERT INTO warfare_prod.troop (atwar, level, troopclass, trooptype,  abstract) VALUES (false, 1, 4, 1,  'crossbowmen');
INSERT INTO warfare_prod.troop (atwar, level, troopclass, trooptype,  abstract) VALUES (false, 1, 4, 1,  'crossbowmen');
INSERT INTO warfare_prod.troop (atwar, level, troopclass, trooptype,  abstract) VALUES (false, 1, 5, 2,  'rams');
INSERT INTO warfare_prod.troop (atwar, level, troopclass, trooptype, abstract) VALUES (false, 1, 5, 2, 'rams');
INSERT INTO warfare_prod.troop (atwar, level, troopclass, trooptype,  abstract) VALUES (false, 1, 6, 2,  'trebuchets');

INSERT INTO warfare_prod.kingdoms_troops (id, troop_id) VALUES (-3, 1);
INSERT INTO warfare_prod.kingdoms_troops (id, troop_id) VALUES (-3, 2);
INSERT INTO warfare_prod.kingdoms_troops (id, troop_id) VALUES (-3, 3);
INSERT INTO warfare_prod.kingdoms_troops (id, troop_id) VALUES (-3, 4);
INSERT INTO warfare_prod.kingdoms_troops (id, troop_id) VALUES (-3, 5);
INSERT INTO warfare_prod.kingdoms_troops (id, troop_id) VALUES (-3, 6);
INSERT INTO warfare_prod.kingdoms_troops (id, troop_id) VALUES (-3, 7);
INSERT INTO warfare_prod.kingdoms_troops (id, troop_id) VALUES (-3, 8);
INSERT INTO warfare_prod.kingdoms_troops (id, troop_id) VALUES (-3, 9);
INSERT INTO warfare_prod.kingdoms_troops (id, troop_id) VALUES (-3, 10);
INSERT INTO warfare_prod.kingdoms_troops (id, troop_id) VALUES (-3, 11);
INSERT INTO warfare_prod.kingdoms_troops (id, troop_id) VALUES (-3, 12);
INSERT INTO warfare_prod.kingdoms_troops (id, troop_id) VALUES (-3, 13);
INSERT INTO warfare_prod.kingdoms_troops (id, troop_id) VALUES (-3, 14);
INSERT INTO warfare_prod.kingdoms_troops (id, troop_id) VALUES (-3, 15);
INSERT INTO warfare_prod.kingdoms_troops (id, troop_id) VALUES (-3, 16);
INSERT INTO warfare_prod.kingdoms_troops (id, troop_id) VALUES (-3, 17);
INSERT INTO warfare_prod.kingdoms_troops (id, troop_id) VALUES (-3, 18);
INSERT INTO warfare_prod.kingdoms_troops (id, troop_id) VALUES (-3, 19);
INSERT INTO warfare_prod.kingdoms_troops (id, troop_id) VALUES (-3, 20);
INSERT INTO warfare_prod.kingdoms_troops (id, troop_id) VALUES (-3, 21);
INSERT INTO warfare_prod.kingdoms_troops (id, troop_id) VALUES (-3, 22);
INSERT INTO warfare_prod.kingdoms_troops (id, troop_id) VALUES (-3, 23);
INSERT INTO warfare_prod.kingdoms_troops (id, troop_id) VALUES (-3, 24);
INSERT INTO warfare_prod.kingdoms_troops (id, troop_id) VALUES (-3, 25);
INSERT INTO warfare_prod.kingdoms_troops (id, troop_id) VALUES (-3, 26);
INSERT INTO warfare_prod.kingdoms_troops (id, troop_id) VALUES (-3, 27);
--
--
--
--
--
--
--
--
--
--
--
--
--
--
--
--
INSERT INTO kingdom_prod.kingdom (experience, level, id, name) VALUES (0, 1,-2, 'NewKingdom');
INSERT INTO kingdom_prod.domainuser (id, kingdomid, name, password, username) VALUES (-2, -2, 'NewKingdom', 'NewKingdom','NewKingdom');

INSERT INTO foundation_prod.foundation(gold, id, iron, kingdomid, wood) VALUES (500,-2,850,-2,1200);
INSERT INTO foundation_prod.resourceproducer (level, perminutebase, produce, producemultiplier, id, producer) VALUES (0, 60, 0, 2.3, -8, 'LUMBER');
INSERT INTO foundation_prod.resourceproducer (level, perminutebase, produce, producemultiplier, id, producer) VALUES (0, 60, 0, 2.3, -7, 'LUMBER');
INSERT INTO foundation_prod.resourceproducer (level, perminutebase, produce, producemultiplier, id, producer) VALUES (0, 45, 1, 2, -6, 'IRON');
INSERT INTO foundation_prod.resourceproducer (level, perminutebase, produce, producemultiplier, id, producer) VALUES (0, 30, 2, 1.4, -5, 'GOLD');
INSERT INTO foundation_prod.defence (armor, health, level, id, defence) VALUES (20, 140, 1, -6,  'WALL');
INSERT INTO foundation_prod.defence (armor, health, level,id,  defence) VALUES (15, 60, 1, -5, 'ARCHER_TOWER');
INSERT INTO foundation_prod.defences (defence_id, id) VALUES (-6, -2);
INSERT INTO foundation_prod.defences (defence_id, id) VALUES (-5, -2);
INSERT INTO foundation_prod.producers (id, producer_id) VALUES (-2, -8);
INSERT INTO foundation_prod.producers (id, producer_id) VALUES (-2, -7);
INSERT INTO foundation_prod.producers (id, producer_id) VALUES (-2, -6);
INSERT INTO foundation_prod.producers (id, producer_id) VALUES (-2, -5);


INSERT INTO warfare_prod.kingdomstroops (id) VALUES (-2);
INSERT INTO warfare_prod.troopclasslevel (level, troopclass, id, kingdomid) VALUES (0, null, -2, -2);

INSERT INTO warfare_prod.troopclasslevel_trooplevel (trooplevel, trooplevel_key, troopclasslevel_id) VALUES (1, 4, -2);
INSERT INTO warfare_prod.troopclasslevel_trooplevel (trooplevel, trooplevel_key, troopclasslevel_id) VALUES (1, 2, -2);
INSERT INTO warfare_prod.troopclasslevel_trooplevel (trooplevel, trooplevel_key, troopclasslevel_id) VALUES (1, 6, -2);
INSERT INTO warfare_prod.troopclasslevel_trooplevel (trooplevel, trooplevel_key, troopclasslevel_id) VALUES (1, 3, -2);
INSERT INTO warfare_prod.troopclasslevel_trooplevel (trooplevel, trooplevel_key, troopclasslevel_id) VALUES (1, 1, -2);
INSERT INTO warfare_prod.troopclasslevel_trooplevel (trooplevel, trooplevel_key, troopclasslevel_id) VALUES (1, 0, -2);
INSERT INTO warfare_prod.troopclasslevel_trooplevel (trooplevel, trooplevel_key, troopclasslevel_id) VALUES (1, 5, -2);


INSERT INTO warfare_prod.troop (atwar, level, troopclass, trooptype,  abstract) VALUES (false, 1, 2, 0,  'calvary');
INSERT INTO warfare_prod.troop (atwar, level, troopclass, trooptype,  abstract) VALUES (false, 1, 2, 0,  'calvary');
INSERT INTO warfare_prod.troop (atwar, level, troopclass, trooptype,  abstract) VALUES (false, 1, 0, 0, 'infantrysword');
INSERT INTO warfare_prod.troop (atwar, level, troopclass, trooptype,  abstract) VALUES (false, 1, 0, 0,  'infantrysword');
INSERT INTO warfare_prod.troop (atwar, level, troopclass, trooptype,  abstract) VALUES (false, 1, 1, 0,  'macemen');
INSERT INTO warfare_prod.troop (atwar, level, troopclass, trooptype,  abstract) VALUES (false, 1, 1, 0, 'macemen');
INSERT INTO warfare_prod.troop (atwar, level, troopclass, trooptype,  abstract) VALUES (false, 1, 1, 0,  'macemen');
INSERT INTO warfare_prod.troop (atwar, level, troopclass, trooptype,  abstract) VALUES (false, 1, 1, 0,  'macemen');
INSERT INTO warfare_prod.troop (atwar, level, troopclass, trooptype,  abstract) VALUES (false, 1, 1, 0,  'macemen');
INSERT INTO warfare_prod.troop (atwar, level, troopclass, trooptype,  abstract) VALUES (false, 1, 3, 1,  'archers');
INSERT INTO warfare_prod.troop (atwar, level, troopclass, trooptype,  abstract) VALUES (false, 1, 3, 1,  'archers');
INSERT INTO warfare_prod.troop (atwar, level, troopclass, trooptype,  abstract) VALUES (false, 1, 3, 1, 'archers');
INSERT INTO warfare_prod.troop (atwar, level, troopclass, trooptype,  abstract) VALUES (false, 1, 3, 1, 'archers');
INSERT INTO warfare_prod.troop (atwar, level, troopclass, trooptype,  abstract) VALUES (false, 1, 4, 1,  'crossbowmen');
INSERT INTO warfare_prod.troop (atwar, level, troopclass, trooptype,  abstract) VALUES (false, 1, 5, 2,  'rams');
INSERT INTO warfare_prod.troop (atwar, level, troopclass, trooptype, abstract) VALUES (false, 1, 5, 2, 'rams');
INSERT INTO warfare_prod.troop (atwar, level, troopclass, trooptype,  abstract) VALUES (false, 1, 6, 2,  'trebuchets');

INSERT INTO warfare_prod.kingdoms_troops (id, troop_id) VALUES (-2, 28);
INSERT INTO warfare_prod.kingdoms_troops (id, troop_id) VALUES (-2, 29);
INSERT INTO warfare_prod.kingdoms_troops (id, troop_id) VALUES (-2, 30);
INSERT INTO warfare_prod.kingdoms_troops (id, troop_id) VALUES (-2, 31);
INSERT INTO warfare_prod.kingdoms_troops (id, troop_id) VALUES (-2, 32);
INSERT INTO warfare_prod.kingdoms_troops (id, troop_id) VALUES (-2, 33);
INSERT INTO warfare_prod.kingdoms_troops (id, troop_id) VALUES (-2, 34);
INSERT INTO warfare_prod.kingdoms_troops (id, troop_id) VALUES (-2, 35);
INSERT INTO warfare_prod.kingdoms_troops (id, troop_id) VALUES (-2, 36);
INSERT INTO warfare_prod.kingdoms_troops (id, troop_id) VALUES (-2, 37);
INSERT INTO warfare_prod.kingdoms_troops (id, troop_id) VALUES (-2, 38);
INSERT INTO warfare_prod.kingdoms_troops (id, troop_id) VALUES (-2, 39);
INSERT INTO warfare_prod.kingdoms_troops (id, troop_id) VALUES (-2, 40);
INSERT INTO warfare_prod.kingdoms_troops (id, troop_id) VALUES (-2, 41);
INSERT INTO warfare_prod.kingdoms_troops (id, troop_id) VALUES (-2, 42);
INSERT INTO warfare_prod.kingdoms_troops (id, troop_id) VALUES (-2, 43);
INSERT INTO warfare_prod.kingdoms_troops (id, troop_id) VALUES (-2, 44);


















INSERT INTO kingdom_prod.kingdom (experience, level, id, name) VALUES (0, 1,-1, 'TinyKingdom');
INSERT INTO kingdom_prod.domainuser (id, kingdomid, name, password, username) VALUES (-1, -1, 'TinyKingdom', 'TinyKingdom','TinyKingdom');

INSERT INTO foundation_prod.foundation(gold, id, iron, kingdomid, wood) VALUES (500,-1,850,-1,1200);
INSERT INTO foundation_prod.resourceproducer (level, perminutebase, produce, producemultiplier, id, producer) VALUES (0, 60, 0, 2.3, -4, 'LUMBER');
INSERT INTO foundation_prod.resourceproducer (level, perminutebase, produce, producemultiplier, id, producer) VALUES (0, 60, 0, 2.3, -3, 'LUMBER');
INSERT INTO foundation_prod.resourceproducer (level, perminutebase, produce, producemultiplier, id, producer) VALUES (0, 45, 1, 2, -2, 'IRON');
INSERT INTO foundation_prod.resourceproducer (level, perminutebase, produce, producemultiplier, id, producer) VALUES (0, 30, 2, 1.4, -1, 'GOLD');
INSERT INTO foundation_prod.defence (armor, health, level, id, defence) VALUES (20, 140, 1, -4,  'WALL');
INSERT INTO foundation_prod.defence (armor, health, level,id,  defence) VALUES (15, 60, 1, -3, 'ARCHER_TOWER');
INSERT INTO foundation_prod.defences (defence_id, id) VALUES (-4, -1);
INSERT INTO foundation_prod.defences (defence_id, id) VALUES (-3, -1);
INSERT INTO foundation_prod.producers (id, producer_id) VALUES (-1, -4);
INSERT INTO foundation_prod.producers (id, producer_id) VALUES (-1, -3);
INSERT INTO foundation_prod.producers (id, producer_id) VALUES (-1, -2);
INSERT INTO foundation_prod.producers (id, producer_id) VALUES (-1, -1);


INSERT INTO warfare_prod.kingdomstroops (id) VALUES (-1);
INSERT INTO warfare_prod.troopclasslevel (level, troopclass, id, kingdomid) VALUES (0, null, -1, -1);

INSERT INTO warfare_prod.troopclasslevel_trooplevel (trooplevel, trooplevel_key, troopclasslevel_id) VALUES (1, 4, -1);
INSERT INTO warfare_prod.troopclasslevel_trooplevel (trooplevel, trooplevel_key, troopclasslevel_id) VALUES (1, 2, -1);
INSERT INTO warfare_prod.troopclasslevel_trooplevel (trooplevel, trooplevel_key, troopclasslevel_id) VALUES (1, 6, -1);
INSERT INTO warfare_prod.troopclasslevel_trooplevel (trooplevel, trooplevel_key, troopclasslevel_id) VALUES (1, 3, -1);
INSERT INTO warfare_prod.troopclasslevel_trooplevel (trooplevel, trooplevel_key, troopclasslevel_id) VALUES (1, 1, -1);
INSERT INTO warfare_prod.troopclasslevel_trooplevel (trooplevel, trooplevel_key, troopclasslevel_id) VALUES (1, 0, -1);
INSERT INTO warfare_prod.troopclasslevel_trooplevel (trooplevel, trooplevel_key, troopclasslevel_id) VALUES (1, 5, -1);


INSERT INTO warfare_prod.troop (atwar, level, troopclass, trooptype,  abstract) VALUES (false, 1, 2, 0,  'calvary');
INSERT INTO warfare_prod.troop (atwar, level, troopclass, trooptype,  abstract) VALUES (false, 1, 0, 0, 'infantrysword');
INSERT INTO warfare_prod.troop (atwar, level, troopclass, trooptype,  abstract) VALUES (false, 1, 1, 0,  'macemen');
INSERT INTO warfare_prod.troop (atwar, level, troopclass, trooptype,  abstract) VALUES (false, 1, 3, 1,  'archers');
INSERT INTO warfare_prod.troop (atwar, level, troopclass, trooptype,  abstract) VALUES (false, 1, 4, 1,  'crossbowmen');
INSERT INTO warfare_prod.troop (atwar, level, troopclass, trooptype, abstract) VALUES (false, 1, 5, 2, 'rams');
INSERT INTO warfare_prod.troop (atwar, level, troopclass, trooptype,  abstract) VALUES (false, 1, 6, 2,  'trebuchets');

INSERT INTO warfare_prod.kingdoms_troops (id, troop_id) VALUES (-1, 45);
INSERT INTO warfare_prod.kingdoms_troops (id, troop_id) VALUES (-1, 46);
INSERT INTO warfare_prod.kingdoms_troops (id, troop_id) VALUES (-1, 47);
INSERT INTO warfare_prod.kingdoms_troops (id, troop_id) VALUES (-1, 48);
INSERT INTO warfare_prod.kingdoms_troops (id, troop_id) VALUES (-1, 49);
INSERT INTO warfare_prod.kingdoms_troops (id, troop_id) VALUES (-1, 50);
INSERT INTO warfare_prod.kingdoms_troops (id, troop_id) VALUES (-1, 51);






