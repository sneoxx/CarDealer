/*
 * Copyright (c) 2021 com.company.cardealer.entity
 */
package com.company.cardealer.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;

/**
 * @author zaraevrs
 */
public enum CarType implements EnumClass<Integer> {

    crossover(10),
    stationWagon(20),
    sedan(30);

    private Integer id;

    CarType(Integer value) {
        this.id = value;
    }

    public Integer getId() {
        return id;
    }

    @Nullable
    public static CarType fromId(Integer id) {
        for (CarType at : CarType.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}