package com.monika.homework.file.repository;


import com.monika.homework.file.domain.CustomerInput;
import com.monika.homework.file.domain.ProcessingStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class CustomerInputTest {

    @Autowired
    private CustomerInputRepository customerInputRepository;

    @Test
    public void customerInputSaveTest() {
        //Given
        CustomerInput customerInput = new CustomerInput("input", ProcessingStatus.NEW);

        //When
        customerInputRepository.save(customerInput);
        Long id = customerInput.getId();
        Optional<CustomerInput> optionalCustomerInput = customerInputRepository.findById(id);

        //Then
        Assertions.assertTrue(optionalCustomerInput.isPresent());

        //CleanUp
        customerInputRepository.deleteById(id);
    }

    @Test
    public void customerInputDeleteTest() {
        //Given
        CustomerInput customerInput = new CustomerInput("input", ProcessingStatus.NEW);
        customerInputRepository.save(customerInput);

        //When
        Long id = customerInput.getId();
        customerInputRepository.deleteById(id);
        Optional<CustomerInput> optionalCustomerInput = customerInputRepository.findById(id);

        //Then
        Assertions.assertFalse(optionalCustomerInput.isPresent());
    }

    @Test
    public void customerInputUpdateTest() {
        //Given
        CustomerInput customerInput = new CustomerInput("input", ProcessingStatus.NEW);
        customerInputRepository.save(customerInput);

        //When
        Long id = customerInput.getId();
        customerInput.setStatus(ProcessingStatus.DONE);
        customerInputRepository.save(customerInput);
        Optional<CustomerInput> optionalCustomerInput = customerInputRepository.findById(id);

        //Then
        Assertions.assertEquals(ProcessingStatus.DONE, optionalCustomerInput.get().getStatus());

        //CleanUp
        customerInputRepository.deleteById(id);
    }

}