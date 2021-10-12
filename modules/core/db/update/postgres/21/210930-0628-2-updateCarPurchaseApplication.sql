-- update CARDEALER_CAR_PURCHASE_APPLICATION set CAR_ID = <default_value> where CAR_ID is null ;
alter table CARDEALER_CAR_PURCHASE_APPLICATION alter column CAR_ID set not null ;
