/*
 * Copyright (c) 2021 com.company.cardealer.core.app.reassignment.commands
 */
package com.company.cardealer.core.app.reassignment.commands;


import com.haulmont.thesis.core.app.reassignment.commands.AbstractDocReassignmentCommand;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;

import com.company.cardealer.entity.CarPurchaseApplication;

/**
 * @author zaraevrs
 */
@ManagedBean(CarPurchaseApplicationReassignmentCommand.NAME)
public class CarPurchaseApplicationReassignmentCommand extends AbstractDocReassignmentCommand<CarPurchaseApplication> {
    protected static final String NAME = "carpurchaseapplication_reassignment_command";

    @PostConstruct
    protected void postInit() {
        type = "CarPurchaseApplication";
        docQuery = String.format(DOC_QUERY_TEMPLATE, "cardealer$CarPurchaseApplication");
    }

    @Override
    public String getName() {
        return NAME;
    }
}