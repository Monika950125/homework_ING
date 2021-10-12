package com.monika.homework.customer.service.impl;

import com.monika.homework.customer.domain.Customer;
import com.monika.homework.customer.domain.CustomerRisks;
import com.monika.homework.customer.service.CustomerTypeStrategy;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.monika.homework.BigDecimalConstant.HUNDRED;

public class TypeA5Strategy implements CustomerTypeStrategy {
    @Override
    public CustomerRisks calculate(Customer customer) {
        return new CustomerRisks(
                customer.getCustomerId(),
                customer.getDate(),
                customer.getIncome().divide(BigDecimal.TEN).setScale(2, RoundingMode.FLOOR),
                customer.getIncome().divide(HUNDRED).setScale(2, RoundingMode.FLOOR)
        );
    }
}
