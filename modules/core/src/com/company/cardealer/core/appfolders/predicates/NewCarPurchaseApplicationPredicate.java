/*
 * Copyright (c) ${YEAR} ${PACKAGE_NAME}
 */

package com.company.cardealer.core.appfolders.predicates;

import com.company.cardealer.entity.CarPurchaseApplication;
import com.hazelcast.query.Predicate;

import java.util.Map;

/**
 * @author zaraevrs
 *
 * Предикат для фильтрации только заявок на покупку автомобиля в папке поиска Новые заявки
 */
public class NewCarPurchaseApplicationPredicate implements Predicate {

    public NewCarPurchaseApplicationPredicate() {
    }

    @Override
    public boolean apply(Map.Entry mapEntry) {
       return evaluate(mapEntry.getValue());
    }

     public boolean evaluate(Object object) {
        return object instanceof CarPurchaseApplication && !Boolean.TRUE.equals(((CarPurchaseApplication) object).getRegistered());
    }
}