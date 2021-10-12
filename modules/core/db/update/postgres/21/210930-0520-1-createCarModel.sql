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
    COMMENT_ varchar(400) not null,
    --
    primary key (ID)
);
