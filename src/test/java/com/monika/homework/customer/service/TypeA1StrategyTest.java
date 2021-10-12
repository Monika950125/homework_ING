package com.monika.homework.customer.service;

import com.monika.homework.customer.domain.*;
import com.monika.homework.customer.service.impl.TypeA1Strategy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;


class TypeA1StrategyTest {

    @Test
    public void calculateTestWhenBusinessTypeBR_3(){
        //Given
        TypeA1Strategy strategy = new TypeA1Strategy(Optional.of(BigDecimal.ONE));
        LocalDate date = LocalDate.of(2021, 2, 15);
        LocalDate startDate = LocalDate.of(2020, 1, 13);
        Customer customer = new Customer(1L, "BUD-POL", date, startDate, CustomerType.TYPE_A1, new BigDecimal(23015), RiskClass.A1, BusinessType.BR_3);
        BigDecimal expectedR1 = new BigDecimal("690.45");
        BigDecimal expectedR2 = new BigDecimal("18.41");

        //When
        CustomerRisks risks = strategy.calculate(customer);

        //Then
        Assertions.assertEquals(customer.getCustomerId(), risks.getCustomerId());
        Assertions.assertEquals(expectedR1, risks.getR1());
        Assertions.assertEquals(expectedR2, risks.getR2());
    }

    @Test
    public void calculateTestWhenBusinessTypeBT_1(){
        //Given
        TypeA1Strategy strategy = new TypeA1Strategy(Optional.of(BigDecimal.ONE));
        LocalDate date = LocalDate.of(2021, 2, 15);
        LocalDate startDate = LocalDate.of(2020, 1, 13);
        Customer customer = new Customer(1L, "BUD-POL", date, startDate, CustomerType.TYPE_A1, new BigDecimal(25015), RiskClass.A1, BusinessType.BT_1);
        BigDecimal expectedR1 = new BigDecimal("750.45");
        BigDecimal expectedR2 = new BigDecimal("12.50");

        //When
        CustomerRisks risks = strategy.calculate(customer);

        //Then
        Assertions.assertEquals(customer.getCustomerId(), risks.getCustomerId());
        Assertions.assertEquals(expectedR1, risks.getR1());
        Assertions.assertEquals(expectedR2, risks.getR2());
    }

    @Test
    public void calculateTestWhenBusinessTypeIsOtherThanBT_1BR_2BR_3(){
        //Given
        TypeA1Strategy strategy = new TypeA1Strategy(Optional.of(BigDecimal.ONE));
        LocalDate date = LocalDate.of(2021, 2, 15);
        LocalDate startDate = LocalDate.of(2020, 1, 13);
        Customer customer = new Customer(1L, "BUD-POL", date, startDate, CustomerType.TYPE_A1, new BigDecimal(45015), RiskClass.A1, BusinessType.BR_4);
        BigDecimal expectedR1 = new BigDecimal("1350.45");
        BigDecimal expectedR2 = new BigDecimal("40.51");

        //When
        CustomerRisks risks = strategy.calculate(customer);

        //Then
        Assertions.assertEquals(customer.getCustomerId(), risks.getCustomerId());
        Assertions.assertEquals(expectedR1, risks.getR1());
        Assertions.assertEquals(expectedR2, risks.getR2());
    }
}