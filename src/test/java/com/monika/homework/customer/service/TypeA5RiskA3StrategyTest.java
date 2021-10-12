package com.monika.homework.customer.service;

import com.monika.homework.customer.domain.*;
import com.monika.homework.customer.service.impl.TypeA5RiskA3Strategy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

class TypeA5RiskA3StrategyTest {

    @Test
    public void calculateWhenBusinessTypeBR_3Test() {
        //Given
        TypeA5RiskA3Strategy strategy = new TypeA5RiskA3Strategy();
        LocalDate date = LocalDate.of(2021, 2, 15);
        LocalDate startDate = LocalDate.of(2020, 1, 13);
        Customer customer = new Customer(1L, "BUD-POL", date, startDate, CustomerType.TYPE_A5, new BigDecimal(27115), RiskClass.A3, BusinessType.BR_3);
        BigDecimal expectedR1 = new BigDecimal("271.15");
        BigDecimal expectedR2 = new BigDecimal("271.15");

        //When
        CustomerRisks risks = strategy.calculate(customer);

        //Then
        Assertions.assertEquals(1L, risks.getCustomerId());
        Assertions.assertEquals(expectedR1, risks.getR1());
        Assertions.assertEquals(expectedR2, risks.getR2());
    }

    @Test
    public void calculateWhenBusinessTypeIsOtherThanBR_3Test() {
        //Given
        TypeA5RiskA3Strategy strategy = new TypeA5RiskA3Strategy();
        LocalDate date = LocalDate.of(2021, 2, 15);
        LocalDate startDate = LocalDate.of(2020, 1, 13);
        Customer customer = new Customer(1L, "BUD-POL", date, startDate, CustomerType.TYPE_A5, new BigDecimal(27115), RiskClass.A3, BusinessType.BR_5);
        BigDecimal expectedR1 = new BigDecimal("542.30");
        BigDecimal expectedR2 = new BigDecimal("271.15");

        //When
        CustomerRisks risks = strategy.calculate(customer);

        //Then
        Assertions.assertEquals(1L, risks.getCustomerId());
        Assertions.assertEquals(expectedR1, risks.getR1());
        Assertions.assertEquals(expectedR2, risks.getR2());
    }
}