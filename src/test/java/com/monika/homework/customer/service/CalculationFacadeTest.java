package com.monika.homework.customer.service;

import com.monika.homework.customer.domain.*;
import com.monika.homework.customer.service.CustomerParametersService;
import com.monika.homework.customer.service.impl.CalculationFacade;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CalculationFacadeTest {

    @Mock
    private CustomerParametersService parametersService;

    @InjectMocks
    private CalculationFacade calculationFacade;

    @Test
    public void calculateTestWhenCustomerTypeA1(){
        //Given
        LocalDate date = LocalDate.of(2021, 2, 15);
        LocalDate startDate = LocalDate.of(2020, 1, 13);
        Customer customer = new Customer(2L, "BUD-POL", date, startDate, CustomerType.TYPE_A1, new BigDecimal(259874), RiskClass.A1, BusinessType.BR_1);
        Optional<BigDecimal> optionalAverage = Optional.of(new BigDecimal(225694));
        BigDecimal expectedR1 = new BigDecimal("5197.48");
        BigDecimal expectedR2 = new BigDecimal("233.88");

        when(parametersService.findAverageFromLast30Days(customer.getCustomerId(), customer.getDate())).thenReturn(optionalAverage);

        //When
        CustomerRisks risks = calculationFacade.calculate(customer);

        //Then
        Assertions.assertEquals(expectedR1, risks.getR1());
        Assertions.assertEquals(expectedR2, risks.getR2());
    }

    @Test
    public void calculateTestWhenCustomerTypeA2(){
        //Given
        LocalDate date = LocalDate.of(2021, 2, 15);
        LocalDate startDate = LocalDate.of(2020, 1, 13);
        Customer customer = new Customer(2L, "BUD-POL", date, startDate, CustomerType.TYPE_A2, new BigDecimal(568415), RiskClass.A1, BusinessType.BR_1);
        BigDecimal expectedR1 = new BigDecimal("56841.50");
        BigDecimal expectedR2 = new BigDecimal("5684.15");

        //When
        CustomerRisks risks = calculationFacade.calculate(customer);

        //Then
        Assertions.assertEquals(expectedR1, risks.getR1());
        Assertions.assertEquals(expectedR2, risks.getR2());
    }

    @Test
    public void calculateTestWhenCustomerTypeA5AndRiskClassA3(){
        //Given
        LocalDate date = LocalDate.of(2021, 2, 15);
        LocalDate startDate = LocalDate.of(2020, 1, 13);
        Customer customer = new Customer(2L, "BUD-POL", date, startDate, CustomerType.TYPE_A5, new BigDecimal(23015), RiskClass.A3, BusinessType.BR_1);
        BigDecimal expectedR1 = new BigDecimal("460.30");
        BigDecimal expectedR2 = new BigDecimal("230.15");

        //When
        CustomerRisks risks = calculationFacade.calculate(customer);

        //Then
        Assertions.assertEquals(expectedR1, risks.getR1());
        Assertions.assertEquals(expectedR2, risks.getR2());
    }

    @Test
    public void calculateTestWhenCustomerTypeA5(){
        //Given
        LocalDate date = LocalDate.of(2021, 2, 15);
        LocalDate startDate = LocalDate.of(2020, 1, 13);
        Customer customer = new Customer(2L, "BUD-POL", date, startDate, CustomerType.TYPE_A5, new BigDecimal(56015), RiskClass.A1, BusinessType.BR_1);
        BigDecimal expectedR1 = new BigDecimal("5601.50");
        BigDecimal expectedR2 = new BigDecimal("560.15");

        //When
        CustomerRisks risks = calculationFacade.calculate(customer);

        //Then
        Assertions.assertEquals(expectedR1, risks.getR1());
        Assertions.assertEquals(expectedR2, risks.getR2());
    }
}