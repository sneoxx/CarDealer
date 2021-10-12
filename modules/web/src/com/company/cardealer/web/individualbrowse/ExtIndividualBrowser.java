/*
 * Copyright (c) 2021 com.company.cardealer.web.individualbrowse
 */
package com.company.cardealer.web.individualbrowse;

import com.company.cardealer.service.CarPurchaseApplicationService;
import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.gui.app.core.categories.ScreenAndComponent;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.data.Datasource;
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory;
import com.haulmont.cuba.web.gui.components.WebFilter;
import com.haulmont.thesis.core.entity.Company;
import com.haulmont.thesis.core.entity.Individual;
import com.haulmont.thesis.web.actions.ThesisExcelAction;
import com.haulmont.thesis.web.ui.individual.IndividualBrowser;
import javafx.scene.Node;
import javafx.scene.web.WebEngine;

import javax.inject.Inject;
import java.util.Map;

/**
 * @author zaraevrs
 */
public class ExtIndividualBrowser extends IndividualBrowser {

    @Inject
    CarPurchaseApplicationService carPurchaseApplicationService;

    @Inject
    protected ComponentsFactory componentsFactory;

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);
        individualsTable.addGeneratedColumn(getMessage("CarPurchaseApplicationCount"), new Table.PrintableColumnGenerator<Individual, String>() {
            @Override
            public Component generateCell(Individual individual) {
                // создаем Label и сетаем в него значение для отображения
                Label label = componentsFactory.createComponent(Label.NAME);
                label.setValue(carPurchaseApplicationService.getCarPurchaseApplicationCount(individual));
                // создаем имя колонки в excel
                individualsTable.getColumn(getMessage("CarPurchaseApplicationCount")).setCaption(getMessage("CarPurchaseApplicationCount"));
                return label;
            }

            @Override
            public String getValue(Individual individual) {
                return carPurchaseApplicationService.getCarPurchaseApplicationCount(individual).toString();
            }
        });

//        individualsTable.addGeneratedColumn(getMessage("NumberOfLoanApplications"), new Table.ColumnGenerator() {
//
//            @Override
//            public Component generateCell(Entity entity) {
//                return new Table.PlainTextCell(carPurchaseApplicationService.getCarPurchaseApplicationCount((Individual) entity).toString());
//            }
//        });

//        //так в ексель передасться значение но поля не будет - будет пусто
//        individualsTable.addPrintable("NumberOfLoanApplications", new Table.Printable<Individual, String>() {
//            @Override
//            public String getValue(Individual individual) {
//                return carPurchaseApplicationService.getCarPurchaseApplicationCount(individual).toString();
//            }
//        });

//        individualsTable.addGeneratedColumn(getMessage("NumberOfLoanApplications"), new Table.PrintableColumnGenerator<Order, String>() {
//            @Override
//            public Component generateCell(Order entity) {
//                Label label = componentsFactory.createComponent(Label.NAME);
//                Product product = order.getProduct();
//                label.setValue(product.getName() + ", " + product.getCost());
//                return label;
//            }
//
//            @Override
//            public String getValue(Order entity) {
//                Product product = order.getProduct();
//                return product.getName() + ", " + product.getCost();
//            }
//        });


//        individualsTable.addGeneratedColumn(getMessage("NumberOfLoanApplications"), new Table.ColumnGenerator() {
//
//            @Override
//            public Component generateCell(Entity entity) {
//                   Label label = componentsFactory.createComponent(Label.NAME);
//                   label.setValue(carPurchaseApplicationService.getCarPurchaseApplicationCount((Individual)entity).toString());
//
//                  LookupPickerField field = componentsFactory.createComponent(LookupField.NAME);
//                  label.setDatasource(individualsTable.getItemDatasource(entity),"numberOfLoanApplications");
//                    field.setOptionsDatasource(individualsDs);
//                    field.addLookupAction();
//                    field.addOpenAction();
//                    return field;
//                }



//        individualsTable.removeAction("refresh");
//
//        excel.setVisible(false);
//        if(excel !=null)            {
//            excel.setAction(new ThesisExcelAction(individualsTable, (WebFilter) getComponent("genericFilter")));
//        }

//            @Override
//            public void init(Map<String, Object> params) {
//                fieldGroup.addCustomField("password", new FieldGroup.CustomFieldGenerator() {
//                    @Override
//                    public Component generateField(Datasource datasource, String propertyId) {
//                        PasswordField passwordField = componentsFactory.createComponent(PasswordField.NAME);
//                        passwordField.setDatasource(datasource, propertyId);
//                        return passwordField;
//                    }
//                });


//        @Override
//        public void init(Map<String, Object> params) {
//            super.init(params);
//            individualsTable.addGeneratedColumn("numberOfLoanApplications", new Table.ColumnGenerator() {
//                @Override
//                public Component generateCell(Entity entity) {
//                    LookupPickerField field = componentsFactory.createComponent(LookupPickerField.NAME);
//                    field.setDatasource(individualsTable.getItemDatasource(entity), "numberOfLoanApplications");
//                    field.setOptionsDatasource(individualsDs);
//                    field.addLookupAction();
//                    field.addOpenAction();
//                    return field;
//                }
//            });
//
//        }


//@Override
//public void init(Map<String, Object> params)
//        someTable.addGeneratedColumn("someColumn", new Table.ColumnGenerator<Colour>() {
//            @Override
//            public Component generateCell(Entity entity) {
//                return new Table.PlainTextCell(carPurchaseApplicationService.getCarPurchaseApplicationCount((Individual) entity).toString());
//            }
//        });

//            if (excel != null) {
//        excel.setAction(new ThesisExcelAction(getTable(), (WebFilter) getComponent("genericFilter")));
//    }


//        individualsTable.removeAction("excel");
//        ThesisExcelAction newExcelAction = new ThesisExcelAction(individualsTable, (WebFilter) getComponent("genericFilter"));
//        individualsTable.addAction(newExcelAction);


//        Button excelButton = individualsTable.getButtonsPanel().getComponentNN("excel");
//        excelButton.setAction(newExcelAction);


//        @Inject
//        protected ComponentsFactory componentsFactory;
//
//        @Override
//        public void init(Map<String, Object> params) {
//            carsTable.addGeneratedColumn("colour", new Table.ColumnGenerator() {
//                @Override
//                public Component generateCell(Entity entity) {
//                    LookupPickerField field = componentsFactory.createComponent(LookupPickerField.NAME);
//                    field.setDatasource(carsTable.getItemDatasource(entity), "colour");
//                    field.setOptionsDatasource(coloursDs);
//                    field.addLookupAction();
//                    field.addOpenAction();
//                    return field;
//                }
//            });
//        }


//        targetScreensTable.addGeneratedColumn(
//                "screen",
//                new Table.ColumnGenerator<ScreenAndComponent>() {
//                    @Override
//                    public Component generateCell(ScreenAndComponent entity) {
//                        final LookupField lookupField = componentsFactory.createComponent(LookupField.NAME);
//                        lookupField.setDatasource(targetScreensTable.getItemDatasource(entity), "screen");
//                        lookupField.setOptionsMap(optionsMap);
//                        lookupField.setNewOptionAllowed(true);
//                        lookupField.setNewOptionHandler(new LookupField.NewOptionHandler() {
//                            @Override
//                            public void addNewOption(String caption) {
//                                optionsMap.put(caption, caption);
//                                lookupField.setValue(caption);
//                            }
//                        });
//                        lookupField.setRequired(true);
//                        lookupField.setWidth("100%");
//                        return lookupField;
//                    }
//                }
//        );
//        setupVisibility();

    }
}
