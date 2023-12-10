-- /*CREATE USERS AND SCHEMAS*/
-- CREATE USER kingdom_acc WITH PASSWORD 'k';
-- CREATE SCHEMA kingdom_prod AUTHORIZATION kingdom_acc;
-- ALTER USER kingdom_acc SET SEARCH_PATH=kingdom_prod;
--
-- CREATE USER foundation_acc WITH PASSWORD 'f';
-- CREATE SCHEMA foundation_prod AUTHORIZATION foundation_acc;
-- ALTER USER foundation_acc SET SEARCH_PATH=foundation_prod;
--
-- CREATE USER troop_acc WITH PASSWORD 't';
-- CREATE SCHEMA troop_prod AUTHORIZATION troop_acc;
-- ALTER USER troop_acc SET SEARCH_PATH=troop_prod;
--
-- CREATE USER simulation_acc WITH PASSWORD 's';
-- CREATE SCHEMA simulation_prod AUTHORIZATION simulation_acc;
-- ALTER USER simulation_acc SET SEARCH_PATH=simulation_prod;
--
--
-- create table kingdom_prod.domainuser
-- (
--     id        bigint not null
--         primary key,
--     kingdomid bigint not null,
--     name      varchar(255),
--     password  varchar(255),
--     username  varchar(255)
-- );
--
-- alter table kingdom_prod.domainuser
--     owner to kingdom_acc;
--
-- create table kingdom_prod.kingdom
-- (
--     experience integer,
--     level      integer,
--     id         bigserial
--         primary key,
--     name       varchar(255)
-- );
--
-- alter table kingdom_prod.kingdom
--     owner to kingdom_acc;
--
--
--
--
--
-- create table foundation_prod.defence
-- (
--     armor   integer     not null,
--     health  integer     not null,
--     level   integer     not null,
--     id      bigserial
--         primary key,
--     defence varchar(31) not null
-- );
--
-- alter table foundation_prod.defence
--     owner to foundation_acc;
--
-- create table foundation_prod.foundation
-- (
--     gold      bigint,
--     id        bigint not null
--         primary key,
--     iron      bigint,
--     kingdomid bigint,
--     wood      bigint
-- );
--
-- alter table foundation_prod.foundation
--     owner to foundation_acc;
--
-- create table foundation_prod.defences
-- (
--     defence_id bigint not null
--         unique
--         constraint fkisba15tgffobwijhh5ldt80b5
--             references foundation_prod.defence,
--     id         bigint not null
--         constraint fkkfwcw4j7fvefbto73gfgb5x8r
--             references foundation_prod.foundation
-- );
--
-- alter table foundation_prod.defences
--     owner to foundation_acc;
--
-- create table foundation_prod.resourceproducer
-- (
--     level             integer     not null,
--     perminutebase     integer,
--     produce           smallint
--         constraint resourceproducer_produce_check
--             check ((produce >= 0) AND (produce <= 2)),
--     producemultiplier double precision,
--     id                bigserial
--         primary key,
--     producer          varchar(31) not null
-- );
--
-- alter table foundation_prod.resourceproducer
--     owner to foundation_acc;
--
-- create table foundation_prod.producers
-- (
--     id          bigint not null
--         constraint fk5vu9m5lnsesbbmbhvu6602p5i
--             references foundation_prod.foundation,
--     producer_id bigint not null
--         unique
--         constraint fk4ta3qsjarearhrd23w3bc0i18
--             references foundation_prod.resourceproducer
-- );
--
-- alter table foundation_prod.producers
--     owner to foundation_acc;
--
--
--
--
--
--
--
-- create table troop_prod.kingdomstroops
-- (
--     id bigint not null
--         primary key
-- );
--
-- alter table troop_prod.kingdomstroops
--     owner to troop_acc;
--
-- create table troop_prod.troop
-- (
--     atwar      boolean     not null,
--     level      integer     not null,
--     troopclass smallint
--         constraint troop_troopclass_check
--             check ((troopclass >= 0) AND (troopclass <= 6)),
--     trooptype  smallint
--         constraint troop_trooptype_check
--             check ((trooptype >= 0) AND (trooptype <= 2)),
--     id         bigserial
--         primary key,
--     abstract   varchar(31) not null
-- );
--
-- alter table troop_prod.troop
--     owner to troop_acc;
--
-- create table troop_prod.kingdoms_troops
-- (
--     id       bigint not null
--         constraint fkdp7alhmdo87qc24xr51lyhafq
--             references troop_prod.kingdomstroops,
--     troop_id bigint not null
--         unique
--         constraint fkp10704c9n4tfa901tqk3lsl0h
--             references troop_prod.troop
-- );
--
-- alter table troop_prod.kingdoms_troops
--     owner to troop_acc;
--
-- create table troop_prod.troopclasslevel
-- (
--     level      integer not null,
--     troopclass smallint
--         constraint troopclasslevel_troopclass_check
--             check ((troopclass >= 0) AND (troopclass <= 6)),
--     id         bigserial
--         primary key,
--     kingdomid  bigint
-- );
--
-- alter table troop_prod.troopclasslevel
--     owner to troop_acc;
--
-- create table troop_prod.troopclasslevel_trooplevel
-- (
--     trooplevel         integer,
--     trooplevel_key     smallint not null
--         constraint troopclasslevel_trooplevel_trooplevel_key_check
--             check ((trooplevel_key >= 0) AND (trooplevel_key <= 6)),
--     troopclasslevel_id bigint   not null
--         constraint fk44w0lwo34xjgw18yc55xcmcgv
--             references troop_prod.troopclasslevel,
--     primary key (trooplevel_key, troopclasslevel_id)
-- );
--
-- alter table troop_prod.troopclasslevel_trooplevel
--     owner to troop_acc;
--
--
--
--
--
--
-- INSERT INTO kingdom_prod.domainuser (id, kingdomid, name, password, username) VALUES (1, 1, 'OG', 'somOG', 'OG');
-- INSERT INTO kingdom_prod.kingdom (experience, level, id, name) VALUES (0, 1, 1, 'OGKingdom');
--
-- INSERT INTO foundation_prod.producers (id, producer_id) VALUES (1, 1);
-- INSERT INTO foundation_prod.producers (id, producer_id) VALUES (1, 2);
-- INSERT INTO foundation_prod.producers (id, producer_id) VALUES (1, 3);
-- INSERT INTO foundation_prod.producers (id, producer_id) VALUES (1, 4);
-- INSERT INTO foundation_prod.resourceproducer (level, perminutebase, produce, producemultiplier, id, producer) VALUES (0, 60, 0, 2.3, 1, 'LUMBER');
-- INSERT INTO foundation_prod.resourceproducer (level, perminutebase, produce, producemultiplier, id, producer) VALUES (0, 60, 0, 2.3, 2, 'LUMBER');
-- INSERT INTO foundation_prod.resourceproducer (level, perminutebase, produce, producemultiplier, id, producer) VALUES (0, 45, 1, 2, 3, 'IRON');
-- INSERT INTO foundation_prod.resourceproducer (level, perminutebase, produce, producemultiplier, id, producer) VALUES (0, 30, 2, 1.4, 4, 'GOLD');
-- INSERT INTO foundation_prod.defence (armor, health, level, id, defence) VALUES (20, 140, 1, 1, 'WALL');
-- INSERT INTO foundation_prod.defence (armor, health, level, id, defence) VALUES (15, 60, 1, 2, 'ARCHER_TOWER');
-- INSERT INTO foundation_prod.defences (defence_id, id) VALUES (1, 1);
-- INSERT INTO foundation_prod.defences (defence_id, id) VALUES (2, 1);
-- INSERT INTO foundation_prod.foundation (gold, id, iron, kingdomid, wood) VALUES (0, 1, 0, 1, 0);
--
-- INSERT INTO troop_prod.kingdomstroops (id) VALUES (1);
-- INSERT INTO troop_prod.troopclasslevel (level, troopclass, id, kingdomid) VALUES (0, null, 1, 1);
--
-- INSERT INTO troop_prod.troopclasslevel_trooplevel (trooplevel, trooplevel_key, troopclasslevel_id) VALUES (1, 4, 1);
-- INSERT INTO troop_prod.troopclasslevel_trooplevel (trooplevel, trooplevel_key, troopclasslevel_id) VALUES (1, 2, 1);
-- INSERT INTO troop_prod.troopclasslevel_trooplevel (trooplevel, trooplevel_key, troopclasslevel_id) VALUES (1, 6, 1);
-- INSERT INTO troop_prod.troopclasslevel_trooplevel (trooplevel, trooplevel_key, troopclasslevel_id) VALUES (1, 3, 1);
-- INSERT INTO troop_prod.troopclasslevel_trooplevel (trooplevel, trooplevel_key, troopclasslevel_id) VALUES (1, 1, 1);
-- INSERT INTO troop_prod.troopclasslevel_trooplevel (trooplevel, trooplevel_key, troopclasslevel_id) VALUES (1, 0, 1);
-- INSERT INTO troop_prod.troopclasslevel_trooplevel (trooplevel, trooplevel_key, troopclasslevel_id) VALUES (1, 5, 1);
--
--
--