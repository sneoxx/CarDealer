/*
 * Copyright (c) 2021 com.company.cardealer.entity
 */
package com.company.cardealer.entity;


/**
 * @author zaraevrs
 */
import com.haulmont.cuba.core.entity.annotation.EnableRestore;
import com.haulmont.cuba.core.entity.annotation.TrackEditScreenHistory;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.DiscriminatorValue;
import javax.persistence.InheritanceType;
import javax.persistence.Inheritance;
import javax.persistence.PrimaryKeyJoinColumn;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.haulmont.thesis.core.entity.TsCard;
import com.haulmont.cuba.core.entity.annotation.Listeners;

@Listeners("cardealer_CarListener")
@PrimaryKeyJoinColumn(name = "CARD_ID", referencedColumnName = "ID")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorValue("1")
@Table(name = "CARDEALER_CAR")
@Entity(name = "cardealer$Car")
@EnableRestore
@TrackEditScreenHistory
public class Car extends TsCard {
    private static final long serialVersionUID = 4027772913209562213L;

    @Column(name = "NUMBER_", length = 50)
    protected String number;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CAR_MODEL_ID")
    protected CarModel carModel;

    @Column(name = "NAME", nullable = false, length = 150)
    protected String name;

    @Temporal(TemporalType.DATE)
    @Column(name = "YEAR_OF_ISSUE", nullable = false, length = 50)
    protected Date yearOfIssue;

    @Column(name = "CAR_COST", nullable = false)
    protected BigDecimal carCost;

    @Column(name = "CAR_TYPE", nullable = false)
    protected Integer carType;

    public void setCarType(CarType carType) {
        this.carType = carType == null ? null : carType.getId();
    }

    public CarType getCarType() {
        return carType == null ? null : CarType.fromId(carType);
    }


    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setCarModel(CarModel carModel) {
        this.carModel = carModel;
    }

    public CarModel getCarModel() {
        return carModel;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setYearOfIssue(Date yearOfIssue) {
        this.yearOfIssue = yearOfIssue;
    }

    public Date getYearOfIssue() {
        return yearOfIssue;
    }

    public void setCarCost(BigDecimal carCost) {
        this.carCost = carCost;
    }

    public BigDecimal getCarCost() {
        return carCost;
    }


}