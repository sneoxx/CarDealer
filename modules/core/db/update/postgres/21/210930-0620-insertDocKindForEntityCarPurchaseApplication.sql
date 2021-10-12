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
