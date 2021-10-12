/*
 * Copyright (c) 2021 com.company.cardealer.web.companybrowse
 */
package com.company.cardealer.web.companybrowse;

import com.company.cardealer.service.CarPurchaseApplicationService;
import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.Label;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory;
import com.haulmont.thesis.core.entity.Company;
import com.haulmont.thesis.core.entity.Individual;
import com.haulmont.thesis.web.ui.company.CompanyBrowser;

import javax.inject.Inject;
import java.util.Map;

/**
 * @author zaraevrs
 */
public class ExtCompanyBrowser extends CompanyBrowser {

    @Inject
    CarPurchaseApplicationService carPurchaseApplicationService;

    @Inject
    protected ComponentsFactory componentsFactory;

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);
        companiesTable.addGeneratedColumn(getMessage("CarPurchaseApplicationCount"), new Table.PrintableColumnGenerator<Company, String>() {
            @Override
            public Component generateCell(Company company) {
                // создаем Label и сетаем в него значение для отображения в брауз
                Label label = componentsFactory.createComponent(Label.NAME);
                label.setValue(carPurchaseApplicationService.getCarPurchaseApplicationCount(company));
                // создаем имя колонки в брауз и в excel, иначе создасться только в брауз по имени ключа
                companiesTable.getColumn(getMessage("CarPurchaseApplicationCount")).setCaption(getMessage("CarPurchaseApplicationCount"));
                return label;
            }

            //возвраем данные, которые будут находиться в генерируемой ячейке таблицы в ексель
            @Override
            public String getValue(Company company) {
                return carPurchaseApplicationService.getCarPurchaseApplicationCount(company).toString();
            }
        });
    }
}
