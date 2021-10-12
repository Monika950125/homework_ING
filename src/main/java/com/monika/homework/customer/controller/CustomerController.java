package com.monika.homework.customer.controller;

import com.monika.homework.customer.domain.CustomerDTO;
import com.monika.homework.customer.service.CustomerService;
import com.monika.homework.util.DateParser;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/customer")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/{customer_id}")
    public CustomerDTO getCustomer(@PathVariable("customer_id") Long customerId, @RequestParam(value = "date", required = false) String date) {
        if (date != null) {
            return customerService.findByCustomerIdAndDate(customerId, DateParser.parse(date));
        }
        return customerService.findByCustomerId(customerId);
    }
}
