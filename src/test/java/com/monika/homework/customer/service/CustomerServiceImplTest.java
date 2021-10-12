package com.monika.homework.customer.service;

import com.monika.homework.customer.domain.*;
import com.monika.homework.customer.exception.CustomerNotFoundException;
import com.monika.homework.customer.repository.CustomerRepository;
import com.monika.homework.customer.repository.CustomerRisksRepository;
import com.monika.homework.customer.service.impl.CalculationFacade;
import com.monika.homework.customer.service.impl.CustomerServiceImpl;
import com.monika.homework.customer.service.impl.RiskClassMonitorFacade;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerRisksRepository risksRepository;

    @Mock
    private CalculationFacade facade;

    @Mock
    private RiskClassMonitorFacade riskClassMonitorFacade;

    private CustomerService customerService;

    @BeforeEach
    public void setUp() {
        customerService = new CustomerServiceImpl(customerRepository, risksRepository, facade, riskClassMonitorFacade);
    }

    @Test
    public void shouldFindByCustomerIdEmptyCustomerRisks() {
        //Given
        LocalDate date = LocalDate.of(2021, 2, 15);
        LocalDate startDate = LocalDate.of(2020, 1, 13);
        Customer customer = new Customer(2L, "BUD-POL", date, startDate, CustomerType.TYPE_A1, new BigDecimal(23015), RiskClass.A1, BusinessType.BR_1);
        Optional<Customer> optionalCustomer = Optional.of(customer);

        when(customerRepository.findFirstCustomerByCustomerIdOrderByDateDesc(customer.getCustomerId())).thenReturn(optionalCustomer);

        //When
        CustomerDTO customerDTO = customerService.findByCustomerId(customer.getCustomerId());

        //Then
        Assertions.assertEquals("BUD-POL", customerDTO.getName());
        Assertions.assertNull(customerDTO.getR1());
        Assertions.assertNull(customerDTO.getR2());
    }

    @Test
    public void shouldFindByCustomerIdCustomerRisksExists() {
        //Given
        LocalDate date = LocalDate.of(2021, 2, 15);
        LocalDate startDate = LocalDate.of(2020, 1, 13);
        Customer customer = new Customer(2L, "BUD-POL", date, startDate, CustomerType.TYPE_A1, new BigDecimal(23015), RiskClass.A1, BusinessType.BR_1);
        Optional<Customer> optionalCustomer = Optional.of(customer);
        CustomerRisks risks = new CustomerRisks(2L, date, BigDecimal.ONE, BigDecimal.TEN);

        when(customerRepository.findFirstCustomerByCustomerIdOrderByDateDesc(2L)).thenReturn(optionalCustomer);
        when(risksRepository.findCustomerRisksByCustomerIdAndAndDate(2L, date)).thenReturn(Optional.of(risks));

        //When
        CustomerDTO customerDTO = customerService.findByCustomerId(customer.getCustomerId());

        //Then
        Assertions.assertEquals("BUD-POL", customerDTO.getName());
        Assertions.assertEquals(BigDecimal.ONE, customerDTO.getR1());
        Assertions.assertEquals(BigDecimal.TEN, customerDTO.getR2());
    }

    @Test
    public void shouldReturnExceptionIfCustomerNotExists() {
        //Given
        when(customerRepository.findFirstCustomerByCustomerIdOrderByDateDesc(anyLong())).thenReturn(Optional.empty());

        //When
        //Then
        Assertions.assertThrows(CustomerNotFoundException.class, () -> customerService.findByCustomerId(1L));
    }

    @Test
    public void shouldFindByCustomerIdAndDate() {
        //Given
        LocalDate date = LocalDate.of(2021, 2, 15);
        LocalDate startDate = LocalDate.of(2020, 1, 13);
        Customer customer = new Customer(2L, "BUD-POL", date, startDate, CustomerType.TYPE_A1, new BigDecimal(23015), RiskClass.A1, BusinessType.BR_1);
        Optional<Customer> optionalCustomer = Optional.of(customer);
        when(customerRepository.findCustomerByCustomerIdAndDate(customer.getCustomerId(), customer.getDate())).thenReturn(optionalCustomer);

        //When
        CustomerDTO customerDTO = customerService.findByCustomerIdAndDate(customer.getCustomerId(), customer.getDate());

        //Then
        Assertions.assertEquals("BUD-POL", customerDTO.getName());
    }

    @Test
    public void shouldCalculateParameters() {
        //Given
        LocalDate date = LocalDate.of(2021, 2, 15);
        LocalDate startDate = LocalDate.of(2020, 1, 13);
        Customer customer = new Customer(2L, "BUD-POL", date, startDate, CustomerType.TYPE_A1, new BigDecimal(23015), RiskClass.A1, BusinessType.BR_1);
        CustomerRisks risks = new CustomerRisks(2L, date, BigDecimal.ONE, BigDecimal.TEN);
        Optional<Customer> optionalCustomer = Optional.of(customer);

        when(customerRepository.findCustomerByCustomerIdAndDate(customer.getCustomerId(), customer.getDate())).thenReturn(optionalCustomer);
        when(facade.calculate(customer)).thenReturn(risks);

        //When
        customerService.calculateParameters(customer.getCustomerId(), customer.getDate());

        //Then
        verify(risksRepository, times(1)).save(risks);
    }
}