package com.monika.homework.customer.service.impl;

import com.monika.homework.customer.domain.BusinessType;
import com.monika.homework.customer.domain.Customer;
import com.monika.homework.customer.domain.CustomerRisks;
import com.monika.homework.customer.service.CustomerTypeStrategy;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

import static com.monika.homework.BigDecimalConstant.*;

public class TypeA1Strategy implements CustomerTypeStrategy {

    private final Optional<BigDecimal> averageFromLast30Days;

    public TypeA1Strategy(Optional<BigDecimal> averageFromLast30Days) {
        this.averageFromLast30Days = averageFromLast30Days;
    }

    @Override
    public CustomerRisks calculate(Customer customer) {
        return new CustomerRisks(
                customer.getCustomerId(),
                customer.getDate(),
                customer.getIncome().divide(BigDecimal.TEN).multiply(calculateF1(customer.getIncome())).setScale(2, RoundingMode.FLOOR),
                customer.getIncome().divide(THOUSAND).multiply(calculateF2(customer.getBusinessType())).setScale(2, RoundingMode.FLOOR)
        );
    }

    private BigDecimal calculateF1(BigDecimal income) {
        if (averageFromLast30Days.isEmpty()) {
            return POINT_TWO_FIVE;
        }
        BigDecimal average = averageFromLast30Days.get();
        if (average.compareTo(POINT_EIGHT.multiply(income)) < 0) {
            return POINT_THREE;
        } else {
            return POINT_TWO;
        }
    }

    private BigDecimal calculateF2(BusinessType businessType) {
        if (BusinessType.BR_3 == businessType) {
            return POINT_EIGHT;
        } else if (BusinessType.BT_1 == businessType || BusinessType.BR_2 == businessType) {
            return POINT_FIVE;
        } else {
            return POINT_NINE;
        }
    }
}
