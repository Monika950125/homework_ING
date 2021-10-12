package com.monika.homework.file.service.impl;

import com.monika.homework.customer.domain.BusinessType;
import com.monika.homework.customer.domain.Customer;
import com.monika.homework.customer.domain.CustomerType;
import com.monika.homework.customer.domain.RiskClass;
import com.monika.homework.customer.repository.CustomerRepository;
import com.monika.homework.customer.service.CustomerService;
import com.monika.homework.file.domain.CustomerInput;
import com.monika.homework.file.domain.ProcessingStatus;
import com.monika.homework.file.repository.CustomerInputRepository;
import com.monika.homework.file.service.FileService;
import com.monika.homework.file.service.Reader;
import com.monika.homework.util.DateParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileServiceImpl implements FileService {

    private final Reader reader;
    private final CustomerInputRepository inputRepository;
    private final CustomerRepository customerRepository;
    private final CustomerService customerService;

    @Value("${defaultPath}")
    private String defaultPath;

    @Value("${delimiter}")
    private String defaultDelimiter;

    @Override
    public void save(Optional<String> path) {
        String filePath = path.orElse(defaultPath);
        List<CustomerInput> customerInputs = reader.readFile(filePath);
        save(customerInputs);
        log.info("Saved [{}] new customers", customerInputs.size());
    }

    @Override
    public void process(Optional<String> optionalDelimiter) {
        String delimiter = optionalDelimiter.orElse(defaultDelimiter);
        List<CustomerInput> customerInputs = inputRepository.findAllByStatus(ProcessingStatus.NEW);
        for(CustomerInput input : customerInputs) {
            Customer customer = parse(delimiter, input);
            if (customer != null) {
                customerRepository.save(customer);
                customerService.calculateParameters(customer.getCustomerId(), customer.getDate());
                customerService.trackChanges(customer);
                inputRepository.updateStatus(ProcessingStatus.DONE, input.getId());
            }
        }
    }

    private Customer parse(String delimiter, CustomerInput input) {
        try {
            String[] strings = input.getInput().split(delimiter);
            LocalDate date = DateParser.parse(strings[0]);
            Long customerId = Long.valueOf(strings[1]);
            String name = strings[2];
            LocalDate startDate = DateParser.parse(strings[3]);
            CustomerType customerType = CustomerType.valueOf(strings[4]);
            BigDecimal income = new BigDecimal(strings[5].replace(",", "."));
            RiskClass riskClass = RiskClass.valueOf(strings[6]);
            BusinessType businessType = BusinessType.valueOf(strings[7]);
            inputRepository.updateStatus(ProcessingStatus.DONE, input.getId());
            return new Customer(customerId, name, date, startDate, customerType, income, riskClass, businessType);
        } catch (Exception e) {
            inputRepository.updateStatusAndException(e.getMessage(), ProcessingStatus.ERROR, input.getId());
        }
        return null;
    }

    private void save(List<CustomerInput> customerInputs) {
        for (CustomerInput input : customerInputs) {
            if (!inputRepository.existsByInput(input.getInput())) {
                inputRepository.save(input);
            }
        }
    }
}
