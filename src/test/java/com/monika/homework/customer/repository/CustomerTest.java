package com.monika.homework.customer.repository;

import com.monika.homework.customer.domain.BusinessType;
import com.monika.homework.customer.domain.Customer;
import com.monika.homework.customer.domain.CustomerType;
import com.monika.homework.customer.domain.RiskClass;
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
class CustomerTest {

    @Autowired
    private CustomerRepository repository;

    @Test
    public void shouldSaveCustomer() {
        //Given
        LocalDate date = LocalDate.of(2021, 2, 15);
        LocalDate startDate = LocalDate.of(2020, 1, 13);
        Customer customer = new Customer(1L, "BUD-POL", date, startDate, CustomerType.TYPE_A1, new BigDecimal(23015), RiskClass.A1, BusinessType.BR_1);

        //When
        repository.save(customer);
        Long customerId = customer.getId();
        Optional<Customer> customerOptional = repository.findById(customerId);

        //Then
        Assertions.assertTrue(customerOptional.isPresent());

        //CleanUp
        repository.delete(customer);
    }

    @Test
    public void shouldDeleteCustomer() {
        //Given
        LocalDate date = LocalDate.of(2021, 2, 15);
        LocalDate startDate = LocalDate.of(2020, 1, 13);
        LocalDate date2 = LocalDate.of(2021, 3, 15);
        LocalDate startDate2 = LocalDate.of(2019, 5, 17);
        Customer customer = new Customer(2L, "BUD-POL", date, startDate, CustomerType.TYPE_A1, new BigDecimal(23015), RiskClass.A1, BusinessType.BR_1);
        Customer customer2 = new Customer(3L, "ANDRZEJ-POL", date2, startDate2, CustomerType.TYPE_A2, new BigDecimal(2458965), RiskClass.A3, BusinessType.BR_5);

        repository.save(customer);
        repository.save(customer2);

        //When
        Long customerId = customer.getId();
        Long customer2Id = customer2.getId();

        repository.deleteById(customerId);

        Optional<Customer> optionalCustomer = repository.findFirstCustomerByCustomerIdOrderByDateDesc(customer.getCustomerId());
        Optional<Customer> optionalCustomer2 = repository.findFirstCustomerByCustomerIdOrderByDateDesc(customer2.getCustomerId());

        //Then
        Assertions.assertFalse(optionalCustomer.isPresent());
        Assertions.assertTrue(optionalCustomer2.isPresent());

        //CleanUp
        repository.deleteById(customer2Id);
    }

    @Test
    public void shouldUpdateCustomerIncome() {
        //Given
        LocalDate date = LocalDate.of(2021, 2, 15);
        LocalDate startDate = LocalDate.of(2020, 1, 13);
        Customer customer = new Customer(2L, "BUD-POL", date, startDate, CustomerType.TYPE_A1, new BigDecimal(23015), RiskClass.A1, BusinessType.BR_1);

        repository.save(customer);

        //When
        BigDecimal newIncome = new BigDecimal("215846.00");
        customer.setIncome(newIncome);
        repository.save(customer);
        Optional<Customer> optionalCustomer = repository.findById(customer.getId());

        //Then
        Assertions.assertTrue(optionalCustomer.isPresent());
        Assertions.assertEquals(newIncome, optionalCustomer.get().getIncome());

        //CleanUp
        repository.deleteById(customer.getId());
    }
}