-- /*
--  WARFARE INIT
--  */
-- create table kingdomstroops
-- (
--     id bigint not null
--         primary key
-- );
--
-- alter table kingdomstroops
--     owner to quarkus;
--
-- create table troop
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
-- alter table troop
--     owner to quarkus;
--
-- create table kingdoms_troops
-- (
--     id       bigint not null
--         constraint fkdp7alhmdo87qc24xr51lyhafq
--             references kingdomstroops,
--     troop_id bigint not null
--         unique
--         constraint fkp10704c9n4tfa901tqk3lsl0h
--             references troop
-- );
--
-- alter table kingdoms_troops
--     owner to quarkus;
--
-- create table troopclasslevel
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
-- alter table troopclasslevel
--     owner to quarkus;
--
-- create table troopclasslevel_trooplevel
-- (
--     trooplevel         integer,
--     trooplevel_key     smallint not null
--         constraint troopclasslevel_trooplevel_trooplevel_key_check
--             check ((trooplevel_key >= 0) AND (trooplevel_key <= 6)),
--     troopclasslevel_id bigint   not null
--         constraint fk44w0lwo34xjgw18yc55xcmcgv
--             references troopclasslevel,
--     primary key (trooplevel_key, troopclasslevel_id)
-- );
--
-- alter table troopclasslevel_trooplevel
--     owner to quarkus;
--
--
