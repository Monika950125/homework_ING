package com.monika.homework.file.service.impl;

import com.monika.homework.file.domain.CustomerInput;
import com.monika.homework.file.domain.ProcessingStatus;
import com.monika.homework.file.exception.FileNotExistsException;
import com.monika.homework.file.exception.FileReadException;
import com.monika.homework.file.service.Reader;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvFileReader implements Reader {

    @Override
    public List<CustomerInput> readFile(String path) {
        List<CustomerInput> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                CustomerInput input = new CustomerInput(line, ProcessingStatus.NEW);
                lines.add(input);
            }
        } catch (FileNotFoundException e) {
            throw new FileNotExistsException();
        } catch (IOException e) {
            throw new FileReadException();
        }
        return lines;
    }
}
