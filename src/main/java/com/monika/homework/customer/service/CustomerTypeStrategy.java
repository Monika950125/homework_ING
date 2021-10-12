package com.monika.homework.customer.service;

import com.monika.homework.customer.domain.Customer;
import com.monika.homework.customer.domain.CustomerRisks;

public interface CustomerTypeStrategy {
    CustomerRisks calculate(Customer customer);
}
