package com.monika.homework.customer.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public interface CustomerParametersService {

    Optional<BigDecimal> findAverageFromLast30Days(Long customerId, LocalDate date);
}
