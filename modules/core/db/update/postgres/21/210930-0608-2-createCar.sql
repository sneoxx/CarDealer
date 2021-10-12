alter table CARDEALER_CAR add constraint FK_CARDEALER_CAR_CAR_MODEL_ID foreign key (CAR_MODEL_ID) references CARDEALER_CAR_MODEL(ID);
alter table CARDEALER_CAR add constraint FK_CARDEALER_CAR_CARD_ID foreign key (CARD_ID) references WF_CARD(ID);
create index IDX_CARDEALER_CAR_CAR_MODEL on CARDEALER_CAR (CAR_MODEL_ID);
