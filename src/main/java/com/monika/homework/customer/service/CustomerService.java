package com.monika.homework.customer.service;

import com.monika.homework.customer.domain.Customer;
import com.monika.homework.customer.domain.CustomerDTO;

import java.time.LocalDate;

public interface CustomerService {

    CustomerDTO findByCustomerId(Long customerId);
    CustomerDTO findByCustomerIdAndDate(Long customerId, LocalDate date);
    void calculateParameters(Long customerId, LocalDate date);
    void trackChanges(Customer customer);
}
