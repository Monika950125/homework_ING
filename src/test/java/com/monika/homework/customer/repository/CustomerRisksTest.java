package com.monika.homework.customer.repository;

import com.monika.homework.customer.domain.CustomerRisks;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;


@SpringBootTest
@ExtendWith(SpringExtension.class)
class CustomerRisksTest {

    @Autowired
    private CustomerRisksRepository risksRepository;

    @Test
    public void shouldSaveCustomerRisks() {
        //Given
        LocalDate date = LocalDate.of(2021, 2, 15);
        CustomerRisks customerRisks = new CustomerRisks(date, new BigDecimal(23015), new BigDecimal(256));

        //When
        risksRepository.save(customerRisks);
        Long customerId = customerRisks.getId();
        Optional<CustomerRisks> customerRisks1Optional = risksRepository.findById(customerId);

        //Then
        Assertions.assertTrue(customerRisks1Optional.isPresent());

        //CleanUp
        risksRepository.deleteById(customerId);
    }

    @Test
    public void shouldDeleteCustomerRisks() {
        //Given
        LocalDate date = LocalDate.of(2021, 2, 15);
        LocalDate date2 = LocalDate.of(2021, 5, 26);
        CustomerRisks customerRisks = new CustomerRisks(date, new BigDecimal(23015), new BigDecimal(256));
        CustomerRisks customer2Risks = new CustomerRisks(date2, new BigDecimal(22815), new BigDecimal(356));

        risksRepository.save(customerRisks);
        risksRepository.save(customer2Risks);

        //When
        Long customerId = customerRisks.getId();
        Long customer2Id = customer2Risks.getId();

        risksRepository.deleteById(customerId);
        risksRepository.deleteById(customer2Id);

        Optional<CustomerRisks> customerRisksOptional = risksRepository.findById(customerId);
        Optional<CustomerRisks> customer2RisksOptional = risksRepository.findById(customer2Id);

        //Then
        Assertions.assertFalse(customerRisksOptional.isPresent());
        Assertions.assertFalse(customer2RisksOptional.isPresent());
    }

    @Test
    public void shouldUpdateCustomerR1() {
        //Given
        LocalDate date = LocalDate.of(2021, 2, 15);
        CustomerRisks customerRisks = new CustomerRisks(date, new BigDecimal(23015), new BigDecimal(256));

        risksRepository.save(customerRisks);

        //When
        BigDecimal newR1 = new BigDecimal("1248.00");
        customerRisks.setR1(newR1);
        risksRepository.save(customerRisks);

        Long customerId = customerRisks.getId();
        Optional<CustomerRisks> customerRisksOptional = risksRepository.findById(customerId);

        //Then
        Assertions.assertTrue(customerRisksOptional.isPresent());
        Assertions.assertEquals(newR1, customerRisksOptional.get().getR1());

        //CleanUp
        risksRepository.deleteById(customerId);
    }
}