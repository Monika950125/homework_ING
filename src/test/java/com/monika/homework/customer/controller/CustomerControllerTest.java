package com.monika.homework.customer.controller;

import com.monika.homework.customer.domain.*;
import com.monika.homework.customer.service.CustomerService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Test
    public void shouldGetCustomerByCustomerId() throws Exception {
        //Given
        LocalDate date = LocalDate.of(2021, 2, 15);
        LocalDate startDate = LocalDate.of(2020, 1, 13);
        Customer customer = new Customer(1L, "BUD-POL", date, startDate, CustomerType.TYPE_A1, new BigDecimal(23015), RiskClass.A1, BusinessType.BR_1);
        CustomerDTO customerDTO = CustomerDTO.builder()
                .customerId(1L)
                .name("BUD-POL")
                .startDate(startDate)
                .type(CustomerType.TYPE_A1)
                .income(new BigDecimal(234015))
                .riskClass(RiskClass.A1)
                .businessType(BusinessType.BR_1)
                .r1(new BigDecimal(7500))
                .r2(new BigDecimal(451))
                .build();

        when(customerService.findByCustomerId(customer.getCustomerId())).thenReturn(customerDTO);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/customer/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.customerId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("BUD-POL")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.type", Matchers.is("TYPE_A1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.r1", Matchers.is(7500)));

    }
}