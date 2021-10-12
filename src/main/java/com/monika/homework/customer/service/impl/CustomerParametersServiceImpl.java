package com.monika.homework.customer.service.impl;

import com.monika.homework.customer.domain.Customer;
import com.monika.homework.customer.repository.CustomerRepository;
import com.monika.homework.customer.service.CustomerParametersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerParametersServiceImpl implements CustomerParametersService {

    private final CustomerRepository customerRepository;

    @Override
    public Optional<BigDecimal> findAverageFromLast30Days(Long customerId, LocalDate importDate) {
        List<Customer> customers = customerRepository.findCustomersByCustomerIdAndDateBetween(customerId, LocalDate.now().minusDays(30), LocalDate.now());
        if (customers.isEmpty()) {
            return Optional.empty();
        }
        BigDecimal[] totalWithCount = customers.stream()
                .filter(customer -> !importDate.isEqual(customer.getDate()))
                .filter(customer -> customer.getIncome() != null)
                .map(customer -> new BigDecimal[]{customer.getIncome(), BigDecimal.ONE})
                .reduce((a, b) -> new BigDecimal[]{a[0].add(b[0]), a[1].add(BigDecimal.ONE)})
                .get();
        return Optional.of(totalWithCount[0].divide(totalWithCount[1], RoundingMode.FLOOR));
    }
}
