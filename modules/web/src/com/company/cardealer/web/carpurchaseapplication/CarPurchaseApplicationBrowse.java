package com.company.cardealer.web.carpurchaseapplication;

import java.util.Map;
import com.haulmont.cuba.gui.components.AbstractLookup;
import com.haulmont.thesis.web.ui.basicdoc.browse.AbstractDocBrowser;
import com.company.cardealer.entity.CarPurchaseApplication;

public class CarPurchaseApplicationBrowse extends AbstractDocBrowser<CarPurchaseApplication> {

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);
        entityName = "cardealer$CarPurchaseApplication";
    }
}