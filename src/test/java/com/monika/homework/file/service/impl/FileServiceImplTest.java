package com.monika.homework.file.service.impl;

import com.monika.homework.customer.repository.CustomerRepository;
import com.monika.homework.customer.service.CustomerService;
import com.monika.homework.file.domain.CustomerInput;
import com.monika.homework.file.domain.ProcessingStatus;
import com.monika.homework.file.repository.CustomerInputRepository;
import com.monika.homework.file.service.Reader;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FileServiceImplTest {

    @Mock
    private Reader reader;

    @Mock
    private CustomerInputRepository inputRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerService customerService;

    @Test
    public void saveTest() {
        //Given
        FileServiceImpl fileService = new FileServiceImpl(reader, inputRepository, customerRepository, customerService);
        Optional<String> stringOptional = Optional.of("path");
        String path = "path";
        List<CustomerInput> inputs = new ArrayList<>();
        CustomerInput customerInput = new CustomerInput();
        inputs.add(customerInput);

        when(reader.readFile(path)).thenReturn(inputs);

        //When
        fileService.save(stringOptional);

        //Then
        verify(reader, times(1)).readFile(path);
    }

    @Test
    public void processTest() {
        //Given
        FileServiceImpl fileService = new FileServiceImpl(reader, inputRepository, customerRepository, customerService);
        Optional<String> optionalDelimiter = Optional.of(";");
        List<CustomerInput> inputs = new ArrayList<>();
        CustomerInput customerInput = new CustomerInput("input",ProcessingStatus.NEW);
        inputs.add(customerInput);

        when(inputRepository.findAllByStatus(ProcessingStatus.NEW)).thenReturn(inputs);

        //When
        fileService.process(optionalDelimiter);

        //Then
        verify(inputRepository, times(1)).findAllByStatus(ProcessingStatus.NEW);
    }
}