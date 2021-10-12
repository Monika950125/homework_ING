package com.monika.homework.customer.service.impl;

import com.monika.homework.customer.domain.Customer;
import com.monika.homework.customer.domain.CustomerDTO;
import com.monika.homework.customer.domain.CustomerRisks;
import com.monika.homework.customer.exception.CustomerNotFoundException;
import com.monika.homework.customer.repository.CustomerRepository;
import com.monika.homework.customer.repository.CustomerRisksRepository;
import com.monika.homework.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerRisksRepository risksRepository;
    private final CalculationFacade facade;
    private final RiskClassMonitorFacade riskClassMonitorFacade;

    @Override
    public CustomerDTO findByCustomerId(Long customerId) {
        Customer customer = customerRepository.findFirstCustomerByCustomerIdOrderByDateDesc(customerId).orElseThrow(CustomerNotFoundException::new);
        Optional<CustomerRisks> optionalCustomerRisks = risksRepository.findCustomerRisksByCustomerIdAndAndDate(customerId, customer.getDate());
        return buildCustomerDTO(customer, optionalCustomerRisks);
    }

    @Override
    public CustomerDTO findByCustomerIdAndDate(Long customerId, LocalDate date) {
        Customer customer = customerRepository.findCustomerByCustomerIdAndDate(customerId, date).orElseThrow(CustomerNotFoundException::new);
        Optional<CustomerRisks> optionalCustomerRisks = risksRepository.findCustomerRisksByCustomerIdAndAndDate(customerId, customer.getDate());
        return buildCustomerDTO(customer, optionalCustomerRisks);
    }

    @Override
    public void calculateParameters(Long customerId, LocalDate date) {
        customerRepository.findCustomerByCustomerIdAndDate(customerId, date).ifPresent(
                customer -> {
                    CustomerRisks risks = facade.calculate(customer);
                    risksRepository.save(risks);
                }
        );

    }

    @Override
    public void trackChanges(Customer customer) {
        customerRepository.findCustomerByCustomerIdOrderByDateDesc(customer.getCustomerId()).ifPresent(previous -> {
            if (previous.getRiskClass() != customer.getRiskClass()) {
                riskClassMonitorFacade.riskClassChanges(customer);
            }
        });

    }

    private CustomerDTO buildCustomerDTO(Customer customer, Optional<CustomerRisks> optionalCustomerRisks) {
        return CustomerDTO.builder()
                .customerId(customer.getCustomerId())
                .name(customer.getName())
                .startDate(customer.getStartDate())
                .type(customer.getType())
                .income(customer.getIncome())
                .riskClass(customer.getRiskClass())
                .businessType(customer.getBusinessType())
                .r1(optionalCustomerRisks.map(CustomerRisks::getR1).orElse(null))
                .r2(optionalCustomerRisks.map(CustomerRisks::getR2).orElse(null))
                .build();
    }
}
