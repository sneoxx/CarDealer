package com.company.cardealer.web.carpurchaseapplication;

import com.haulmont.chile.core.common.ValueListener;
import com.haulmont.chile.core.model.Instance;
import com.haulmont.cuba.gui.ComponentsHelper;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import com.haulmont.cuba.gui.data.CollectionDatasourceListener;
import com.haulmont.cuba.gui.data.impl.AbstractDatasource;
import com.haulmont.cuba.gui.data.impl.DsListenerAdapter;
import com.haulmont.thesis.core.entity.Employee;
import com.haulmont.thesis.gui.components.ReopenableWindow;
import com.haulmont.thesis.web.DocflowApp;
import com.haulmont.thesis.web.actions.PrintReportAction;
import com.haulmont.thesis.web.ui.basicdoc.editor.AbstractDocEditor;
import com.haulmont.thesis.web.ui.common.ActionsFrame;
import com.haulmont.workflow.core.app.WfUtils;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import com.haulmont.cuba.core.entity.Entity;

import java.util.*;

import com.company.cardealer.entity.CarPurchaseApplication;

import javax.inject.Inject;
import javax.inject.Named;


public class CarPurchaseApplicationEdit extends AbstractDocEditor<CarPurchaseApplication> {

    @Named("fieldGroup.carPaid")
    Field field;

    FieldGroup fieldGroup;

//    @Inject
//    private Sc screens;



    @Inject
    private CollectionDatasource<Employee, UUID> employeesDs;

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);
        //добавляем слушателя на оплату автомобиля, при смене меняем доступность кнопки
        cardDs.addListener(new DsListenerAdapter<CarPurchaseApplication>() {
            @Override
            public void valueChanged(CarPurchaseApplication source, String property, Object prevValue, Object value) {
                if ("carPaid".equals(property)) {
                    if (getItem().getCarPaid() == false) {
                        if (actionsFrame.getButtonAction("Proverka_zayavki.Otkazano") != null && actionsFrame.getButtonAction("Proverka_zayavki.Provereno") != null) {
                            actionsFrame.getButtonAction("Proverka_zayavki.Provereno").setEnabled(false);
                        }
                    } else { if (actionsFrame.getButtonAction("Proverka_zayavki.Otkazano") != null && actionsFrame.getButtonAction("Proverka_zayavki.Provereno") != null) {
                        actionsFrame.getButtonAction("Proverka_zayavki.Provereno").setEnabled(true);
                    }
                    }
                }
            }
        });
    }

    @Override
    protected String getHiddenTabsConfig() {
        return "correspondenceHistoryTab,docLogTab,cardLinksTab,processTab,securityTab,docTransferLogTab,cardProjectsTab,versionsTab,openHistoryTab";
    }

    @Override
    public void setItem(Entity item) {
        super.setItem(item);
        printButton.addAction(new PrintReportAction("printExecutionList", this, "printDocExecutionListReportName"));

        List<String> rolesList = (List<String>)userSession.getRoles();
//        List<String> rolesList = (List<String>) ((CarPurchaseApplicationEdit)this).userSession.getRoles();

        //даем право роли Bank operator редактирования поля оплаты автомобиля
        for (String role:  rolesList ) {
            if (role.equals("Bank operator")){
                field.setEditable(true);
                field.setEnabled(true);
            }
        }
       //меняем доступность кнопки Проверено, в случае отстутствия оплаты
        if (((CarPurchaseApplication) item).getCarPaid() != null ) {
            if (((CarPurchaseApplication) item).getCarPaid() == false) {
                if (actionsFrame.getButtonAction("Proverka_zayavki.Otkazano") != null && actionsFrame.getButtonAction("Proverka_zayavki.Provereno") != null) {
                    actionsFrame.getButtonAction("Proverka_zayavki.Provereno").setEnabled(false);
                }
            }
        }
    }

    @Override
    protected Component createState() {
        if (WfUtils.isCardInState(getItem(), "New") || StringUtils.isEmpty(getItem().getState())) {
            Label label = componentsFactory.createComponent(Label.NAME);
            label.setValue(StringUtils.isEmpty(getItem().getState()) ? "" : getItem().getLocState());
            return label;
        } else {
            return super.createState();
        }
    }

    @Override
    protected void fillHiddenTabs() {
        hiddenTabs.put("office", getMessage("office"));
        hiddenTabs.put("attachmentsTab", getMessage("attachmentsTab"));
        hiddenTabs.put("docTreeTab", getMessage("docTreeTab"));
        hiddenTabs.put("cardCommentTab", getMessage("cardCommentTab"));
        super.fillHiddenTabs();
    }
}