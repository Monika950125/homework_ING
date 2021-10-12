package com.monika.homework.customer.service;

import com.monika.homework.customer.domain.BusinessType;
import com.monika.homework.customer.domain.Customer;
import com.monika.homework.customer.domain.CustomerType;
import com.monika.homework.customer.domain.RiskClass;
import com.monika.homework.customer.repository.CustomerRepository;
import com.monika.homework.customer.service.impl.CustomerParametersServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerParametersServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    private CustomerParametersService service;

    @BeforeEach
    public void setUp() {
        service = new CustomerParametersServiceImpl(customerRepository);
    }

    @Test
    public void shouldFindAverageFromLast30Days() {
        //Given
        LocalDate date = LocalDate.of(2021, 2, 15);
        LocalDate startDate = LocalDate.of(2020, 1, 13);

        Customer customer = new Customer(2L, "BUD-POL", date, startDate, CustomerType.TYPE_A1, new BigDecimal(23015), RiskClass.A1, BusinessType.BR_1);

        List<Customer> customers = new ArrayList<>();
        customers.add(customer);

        when(customerRepository.findCustomersByCustomerIdAndDateBetween(customer.getCustomerId(), LocalDate.now().minusDays(30), LocalDate.now())).thenReturn(customers);

        //When
        Optional<BigDecimal> average = service.findAverageFromLast30Days(customer.getCustomerId(), (customer.getDate().minusDays(30)));

        //Then
        Assertions.assertTrue(average.isPresent());
    }

    @Test
    public void shouldFindAverageFromLast30DaysWhenListIsEmpty() {
        //Given
        LocalDate date = LocalDate.of(2021, 2, 15);
        LocalDate startDate = LocalDate.of(2020, 1, 13);

        Customer customer = new Customer(2L, "BUD-POL", date, startDate, CustomerType.TYPE_A1, new BigDecimal(23015), RiskClass.A1, BusinessType.BR_1);

        List<Customer> customers = new ArrayList<>();

        when(customerRepository.findCustomersByCustomerIdAndDateBetween(customer.getCustomerId(), LocalDate.now().minusDays(30), LocalDate.now())).thenReturn(customers);

        //When
        Optional<BigDecimal> average = service.findAverageFromLast30Days(customer.getCustomerId(), (customer.getDate().minusDays(30)));

        //Then
        Assertions.assertFalse(average.isPresent());
    }
}