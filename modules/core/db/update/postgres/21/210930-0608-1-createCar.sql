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
);
