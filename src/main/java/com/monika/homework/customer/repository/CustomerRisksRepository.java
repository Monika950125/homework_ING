package com.monika.homework.customer.repository;

import com.monika.homework.customer.domain.CustomerRisks;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

public interface CustomerRisksRepository extends CrudRepository<CustomerRisks, Long> {

    Optional<CustomerRisks> findCustomerRisksByCustomerIdAndAndDate(Long customerId, LocalDate date);
}
