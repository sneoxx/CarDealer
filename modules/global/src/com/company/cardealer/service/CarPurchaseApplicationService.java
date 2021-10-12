/*
 * Copyright (c) 2021 com.company.cardealer.service
 */
package com.company.cardealer.service;

import com.haulmont.thesis.core.entity.Contractor;

/**
 * @author zaraevrs
 */
public interface CarPurchaseApplicationService {
    String NAME = "cardealer_CarPurchaseApplicationService";

    Integer getCarPurchaseApplicationCount (Contractor client);
}