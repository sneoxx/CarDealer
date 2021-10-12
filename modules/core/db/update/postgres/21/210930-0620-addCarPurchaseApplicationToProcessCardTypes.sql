--Update process card_types for entity cardealer$CarPurchaseApplication
update wf_proc set card_types = regexp_replace(card_types, E',cardealer\\$CarPurchaseApplication', '') where code in ('Endorsement','Resolution','Acquaintance','Registration')^
update wf_proc set updated_by='admin', card_types = card_types || 'cardealer$CarPurchaseApplication,' where code in ('Endorsement','Resolution','Acquaintance','Registration')^
