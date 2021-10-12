package com.monika.homework.customer.service.impl;

import com.monika.homework.customer.domain.Customer;
import com.monika.homework.customer.domain.CustomerRisks;
import com.monika.homework.customer.domain.CustomerType;
import com.monika.homework.customer.domain.RiskClass;
import com.monika.homework.customer.exception.InvalidCustomerType;
import com.monika.homework.customer.service.CustomerParametersService;
import com.monika.homework.customer.service.CustomerTypeStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CalculationFacade {

    private final CustomerParametersService parametersService;

    public CustomerRisks calculate(Customer customer){
        return choose(customer).calculate(customer);
    }

    private CustomerTypeStrategy choose(Customer customer) {
        if (CustomerType.TYPE_A1 == customer.getType()) {
            Optional<BigDecimal> averageFromLast30Days = parametersService.findAverageFromLast30Days(customer.getCustomerId(), customer.getDate());
            return new TypeA1Strategy(averageFromLast30Days);
        } else if (CustomerType.TYPE_A5 == customer.getType() && RiskClass.A3 == customer.getRiskClass()) {
            return new TypeA5RiskA3Strategy();
        } else if (CustomerType.TYPE_A2 == customer.getType()) {
            return new TypeA2Strategy();
        } else if (CustomerType.TYPE_A5 == customer.getType()) {
            return new TypeA5Strategy();
        }
        throw new InvalidCustomerType();
    }
}
