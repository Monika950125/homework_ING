package com.monika.homework.customer.repository;

import com.monika.homework.customer.domain.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    @Query
    Optional<Customer> findCustomerByCustomerIdAndDate(Long customerId, LocalDate date);

    @Query
    Optional<Customer> findFirstCustomerByCustomerIdOrderByDateDesc(Long customerId);

    @Query
    List<Customer> findCustomersByCustomerIdAndDateBetween(Long customerId, LocalDate startDate, LocalDate endDate);

    @Query
    Optional<Customer> findCustomerByCustomerIdOrderByDateDesc(Long customerId);
}
