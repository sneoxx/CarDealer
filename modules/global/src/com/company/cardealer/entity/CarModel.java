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
import com.haulmont.chile.core.annotations.NamePattern;
import javax.persistence.Column;
import com.haulmont.cuba.core.entity.StandardEntity;

@NamePattern("%s|name")
@Table(name = "CARDEALER_CAR_MODEL")
@Entity(name = "cardealer$CarModel")
@EnableRestore
@TrackEditScreenHistory
public class CarModel extends StandardEntity {
    private static final long serialVersionUID = -6130723846162307827L;

    @Column(name = "NAME", nullable = false, length = 150)
    protected String name;

    @Column(name = "CODE", nullable = false, length = 150)
    protected String code;

    @Column(name = "COMMENT_", length = 400)
    protected String comment;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }


}