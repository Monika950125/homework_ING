package com.monika.homework.customer.domain;

import lombok.Getter;

@Getter
public enum CustomerType {
    TYPE_A1("A1"), TYPE_A2("A2"), TYPE_A5("A5");

    private String type;

    CustomerType(String type) {
        this.type = type;
    }
}
