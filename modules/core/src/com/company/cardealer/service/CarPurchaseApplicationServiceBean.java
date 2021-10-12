/*
 * Copyright (c) 2021 com.company.cardealer.service
 */
package com.company.cardealer.service;

import com.company.cardealer.entity.CarPurchaseApplication;
import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Transaction;
import com.haulmont.cuba.core.TypedQuery;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.core.global.View;
import com.haulmont.thesis.core.entity.Contractor;
import com.hazelcast.query.Predicate;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

/**
 * @author zaraevrs
 */
@Service(CarPurchaseApplicationService.NAME)
public class CarPurchaseApplicationServiceBean implements CarPurchaseApplicationService {

    @Inject
    protected Persistence persistence ;

    @Inject
    protected Metadata metadata ;

    //Создадим новый сервис  для подсчета количество документов «Заявка на покупку автомобиля» для контрагента.
    @Override
    public Integer getCarPurchaseApplicationCount (Contractor client) {

        Transaction tx = persistence.createTransaction();
        if (client != null) {
            try {
                EntityManager em = persistence.getEntityManager();
                TypedQuery<CarPurchaseApplication> query = em.createQuery(
                        "select c from cardealer$CarPurchaseApplication c where c.client.id = :clientId",
                        CarPurchaseApplication.class);
                query.setView(metadata.getViewRepository().getView(CarPurchaseApplication.class, View.MINIMAL));
                query.setParameter("clientId", client.getId());
                List<CarPurchaseApplication> list = query.getResultList();
                tx.commit();
                if (CollectionUtils.isNotEmpty(list)) {
                    return list.size();
                } else {
                    return 0;
                }
            } finally {
                tx.end();
            }
        }
        return 0;
    }

    
    

}