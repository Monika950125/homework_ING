package com.monika.homework.customer.service.impl;

import com.monika.homework.customer.domain.BusinessType;
import com.monika.homework.customer.domain.Customer;
import com.monika.homework.customer.domain.CustomerRisks;
import com.monika.homework.customer.service.CustomerTypeStrategy;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.monika.homework.BigDecimalConstant.*;

public class TypeA5RiskA3Strategy implements CustomerTypeStrategy {
    @Override
    public CustomerRisks calculate(Customer customer) {
        return new CustomerRisks(
                customer.getCustomerId(),
                customer.getDate(),
                customer.getIncome().divide(BigDecimal.TEN).multiply(calculateF1(customer.getBusinessType())).setScale(2, RoundingMode.FLOOR),
                customer.getIncome().divide(HUNDRED).setScale(2, RoundingMode.FLOOR)
        );
    }

    private BigDecimal calculateF1(BusinessType businessType) {
        if (BusinessType.BR_3 == businessType) {
            return POINT_ONE;
        } else {
            return POINT_TWO;
        }
    }
}
