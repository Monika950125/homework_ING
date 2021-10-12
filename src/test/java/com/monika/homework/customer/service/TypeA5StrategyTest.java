package com.monika.homework.customer.service;

import com.monika.homework.customer.domain.*;
import com.monika.homework.customer.service.impl.TypeA5Strategy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;

class TypeA5StrategyTest {

    @Test
    public void calculateTest() {
        //Given
        TypeA5Strategy strategy = new TypeA5Strategy();
        LocalDate date = LocalDate.of(2021, 2, 15);
        LocalDate startDate = LocalDate.of(2020, 1, 13);
        Customer customer = new Customer(1L, "BUD-POL", date, startDate, CustomerType.TYPE_A5, new BigDecimal(22615), RiskClass.A1, BusinessType.BR_3);
        BigDecimal expectedR1 = new BigDecimal("2261.50");
        BigDecimal expectedR2 =new BigDecimal("226.15");

        //When
        CustomerRisks risks = strategy.calculate(customer);

        //Then
        Assertions.assertEquals(customer.getCustomerId(), risks.getCustomerId());
        Assertions.assertEquals(expectedR1, risks.getR1());
        Assertions.assertEquals(expectedR2, risks.getR2());
    }

}