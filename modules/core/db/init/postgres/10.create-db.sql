-- begin CARDEALER_CAR_MODEL
create table CARDEALER_CAR_MODEL (
    ID uuid,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    VERSION integer,
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(150) not null,
    CODE varchar(150) not null,
    COMMENT_ varchar(400),
    --
    primary key (ID)
)^
-- end CARDEALER_CAR_MODEL
-- begin CARDEALER_CAR
create table CARDEALER_CAR (
    CARD_ID uuid,
    --
    NUMBER_ varchar(50),
    CAR_MODEL_ID uuid not null,
    NAME varchar(150) not null,
    YEAR_OF_ISSUE date not null,
    CAR_COST decimal(19, 2) not null,
    CAR_TYPE integer not null,
    --
    primary key (CARD_ID)
)^
-- end CARDEALER_CAR
--Add default numerator for cardealer$Car
CREATE OR REPLACE FUNCTION baseInsert()
RETURNS integer
AS $$
DECLARE
    cnt integer = 0;
BEGIN
cnt = (select count(id) from DF_NUMERATOR where CODE = 'CarNumerator' and delete_ts is null);
if(cnt = 0) then
    INSERT INTO DF_NUMERATOR (ID, CREATE_TS, CREATED_BY, VERSION, CODE, NUMERATOR_FORMAT, SCRIPT_ENABLED,
    PERIODICITY, NUMBER_INITIAL_VALUE, LOC_NAME)
    VALUES ('71899eb1-654a-4e76-a572-e820622fe739', now(), 'system', 1, 'CarNumerator', 'CAR-[number]', FALSE, 'Y', 1,
    '{"captionWithLanguageList":[{"language":"ru","caption":"Car"},{"language":"en","caption":"Car"}]}'
    );
end if;
return 0;
END;
$$
LANGUAGE plpgsql;
^
select baseInsert()^
drop function if exists baseInsert()^
-- begin CARDEALER_CAR_PURCHASE_APPLICATION
create table CARDEALER_CAR_PURCHASE_APPLICATION (
    CARD_ID uuid,
    --
    CAR_ID uuid not null,
    CLIENT_ID uuid not null,
    BANK_ID uuid,
    CAR_PAID boolean not null,
    --
    primary key (CARD_ID)
)^-- end CARDEALER_CAR_PURCHASE_APPLICATION
--Add default numerator for cardealer$CarPurchaseApplication
CREATE OR REPLACE FUNCTION baseInsert()
RETURNS integer
AS $$
DECLARE
    cnt integer = 0;
BEGIN
cnt = (select count(id) from DF_NUMERATOR where CODE = 'CarPurchaseApplicationNumerator' and delete_ts is null);
if(cnt = 0) then
    INSERT INTO DF_NUMERATOR (ID, CREATE_TS, CREATED_BY, VERSION, CODE, NUMERATOR_FORMAT, SCRIPT_ENABLED,
    PERIODICITY, NUMBER_INITIAL_VALUE, LOC_NAME)
    VALUES ('a0b7fb61-1d03-422c-8618-47fc6927bf20', now(), 'system', 1, 'CarPurchaseApplicationNumerator', 'CPA-[number]', FALSE, 'Y', 1,
    '{"captionWithLanguageList":[{"language":"ru","caption":"CarPurchaseApplication"},{"language":"en","caption":"CarPurchaseApplication"}]}'
    );
end if;
return 0;
END;
$$
LANGUAGE plpgsql;
^
select baseInsert()^
drop function if exists baseInsert()^
--Insert new doc type for cardealer$CarPurchaseApplication
insert into TS_CARD_TYPE (ID, CREATE_TS, CREATED_BY, NAME, DISCRIMINATOR,FIELDS_XML) values ('5fc97134-79af-4740-9a2f-5897cf7a66b1', current_timestamp, 'admin', 'cardealer$CarPurchaseApplication', 1100, '')^
--Add default doc kind for cardealer$CarPurchaseApplication
CREATE OR REPLACE FUNCTION baseInsert()
RETURNS integer
AS $$
DECLARE
cnt integer = 0;
BEGIN
cnt = (select count(CATEGORY_ID) from DF_DOC_KIND where category_id = '67759721-4e4c-44a7-b292-f65bec448345');
if(cnt = 0) then
    insert into SYS_CATEGORY (ID, NAME, ENTITY_TYPE, IS_DEFAULT, CREATE_TS, CREATED_BY, VERSION, DISCRIMINATOR)
    values ( '67759721-4e4c-44a7-b292-f65bec448345', 'Заявка на покупку автомобиля', 'cardealer$CarPurchaseApplication', false, now(), USER, 1, 1);
    insert into DF_DOC_KIND (category_id, create_ts, created_by, version, doc_type_id, numerator_id, 
    numerator_type, category_attrs_place, tab_name, portal_publish_allowed, disable_add_process_actors, create_only_by_template)
    values ('67759721-4e4c-44a7-b292-f65bec448345', 'now()', 'admin', 1, '5fc97134-79af-4740-9a2f-5897cf7a66b1', '19d6053f-9751-4194-aaa8-dae6b1793a53', 
    1, 1, 'Ð”Ð¾Ð¿. Ð¿Ð¾Ð»Ñ�', false, false, false);
end if;return 0;
END;
$$
LANGUAGE plpgsql;
^
select baseInsert();
^
drop function if exists baseInsert()^
--Update process card_types for entity cardealer$CarPurchaseApplication
update wf_proc set card_types = regexp_replace(card_types, E',cardealer\\$CarPurchaseApplication', '') where code in ('Endorsement','Resolution','Acquaintance','Registration')^
update wf_proc set updated_by='admin', card_types = card_types || 'cardealer$CarPurchaseApplication,' where code in ('Endorsement','Resolution','Acquaintance','Registration')^
--Update security for entity cardealer$CarPurchaseApplication
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE, ROLE_ID) values (newid(),now(),'system',1,now(),null,null,null,20,'cardealer$CarPurchaseApplication:create',0,(select ID from SEC_ROLE where NAME = 'SimpleUser'));
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE, ROLE_ID) values (newid(),now(),'system',1,now(),null,null,null,20,'cardealer$CarPurchaseApplication:update',0,(select ID from SEC_ROLE where NAME = 'SimpleUser'));
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE, ROLE_ID) values (newid(),now(),'system',1,now(),null,null,null,20,'cardealer$CarPurchaseApplication:delete',0,(select ID from SEC_ROLE where NAME = 'SimpleUser'));
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE, ROLE_ID) values (newid(),now(),'system',1,now(),null,null,null,20,'cardealer$CarPurchaseApplication:create',1,(select ID from SEC_ROLE where NAME = 'Administrators'));
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE, ROLE_ID) values (newid(),now(),'system',1,now(),null,null,null,20,'cardealer$CarPurchaseApplication:update',1,(select ID from SEC_ROLE where NAME = 'Administrators'));
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE, ROLE_ID) values (newid(),now(),'system',1,now(),null,null,null,20,'cardealer$CarPurchaseApplication:delete',1,(select ID from SEC_ROLE where NAME = 'Administrators'));
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE, ROLE_ID) values (newid(),now(),'system',1,now(),null,null,null,20,'cardealer$CarPurchaseApplication:create',1,(select ID from SEC_ROLE where NAME = 'doc_initiator'));
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE, ROLE_ID) values (newid(),now(),'system',1,now(),null,null,null,20,'cardealer$CarPurchaseApplication:update',1,(select ID from SEC_ROLE where NAME = 'doc_initiator'));
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE, ROLE_ID) values (newid(),now(),'system',1,now(),null,null,null,20,'cardealer$CarPurchaseApplication:delete',1,(select ID from SEC_ROLE where NAME = 'doc_initiator'));
