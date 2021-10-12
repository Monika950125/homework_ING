package com.monika.homework.customer.domain;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class CustomerDTO {

    private Long customerId;
    private String name;
    private LocalDate startDate;
    private CustomerType type;
    private BigDecimal income;
    private RiskClass riskClass;
    private BusinessType businessType;
    private BigDecimal r1;
    private BigDecimal r2;
}
