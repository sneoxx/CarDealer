create table CARDEALER_CAR_PURCHASE_APPLICATION (
    CARD_ID uuid,
    --
    CAR_ID uuid,
    CLIENT_ID uuid not null,
    BANK_ID uuid,
    CAR_PAID boolean not null,
    --
    primary key (CARD_ID)
);
