package com.monika.homework.customer.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
public class CustomerRisks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long customerId;

    @Column(columnDefinition = "DATE")
    private LocalDate date;

    private BigDecimal r1;

    private BigDecimal r2;

    @Column(name = "created_date", nullable = false, updatable = false)
    @CreatedDate
    private long createdDate;

    @Column(name = "modified_date")
    @LastModifiedDate
    private long modifiedDate;

    public CustomerRisks(Long customerId, LocalDate date, BigDecimal r1, BigDecimal r2) {
        this.customerId = customerId;
        this.date = date;
        this.r1 = r1;
        this.r2 = r2;
    }

    public CustomerRisks(LocalDate date, BigDecimal r1, BigDecimal r2) {
        this.date = date;
        this.r1 = r1;
        this.r2 = r2;
    }
}
