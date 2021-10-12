package com.monika.homework.customer.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"customerId", "date"})
})
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long customerId;

    private String name;

    @Column(columnDefinition = "DATE")
    private LocalDate date;

    @Column(columnDefinition = "DATE")
    private LocalDate startDate;

    private CustomerType type;

    private BigDecimal income;

    private RiskClass riskClass;

    private BusinessType businessType;

    @Column(name = "created_date", nullable = false, updatable = false)
    @CreatedDate
    private long createdDate;

    @Column(name = "modified_date")
    @LastModifiedDate
    private long modifiedDate;

    public Customer() {
    }

    public Customer(Long customerId, String name, LocalDate date, LocalDate startDate, CustomerType type, BigDecimal income, RiskClass riskClass, BusinessType businessType) {
        this.customerId = customerId;
        this.name = name;
        this.date = date;
        this.startDate = startDate;
        this.type = type;
        this.income = income;
        this.riskClass = riskClass;
        this.businessType = businessType;
    }
}
