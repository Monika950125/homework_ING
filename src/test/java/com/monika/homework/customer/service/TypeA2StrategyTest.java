package com.monika.homework.customer.service;

import com.monika.homework.customer.domain.*;
import com.monika.homework.customer.service.impl.TypeA2Strategy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

class TypeA2StrategyTest {

    @Test
    public void calculateTest() {
        //Given
        TypeA2Strategy strategy = new TypeA2Strategy();
        LocalDate date = LocalDate.of(2021, 2, 15);
        LocalDate startDate = LocalDate.of(2020, 1, 13);
        Customer customer = new Customer(1L, "BUD-POL", date, startDate, CustomerType.TYPE_A2, new BigDecimal(27115), RiskClass.A1, BusinessType.BR_1);
        BigDecimal expectedR1 = new BigDecimal("2711.50");
        BigDecimal expectedR2 = new BigDecimal("271.15");

        //When
        CustomerRisks risks = strategy.calculate(customer);

        //Then
        Assertions.assertEquals(1L, risks.getCustomerId());
        Assertions.assertEquals(expectedR1, risks.getR1());
        Assertions.assertEquals(expectedR2, risks.getR2());
    }
}