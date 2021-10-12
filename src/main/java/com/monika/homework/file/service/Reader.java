package com.monika.homework.file.service;

import com.monika.homework.file.domain.CustomerInput;

import java.util.List;

public interface Reader {

    List<CustomerInput> readFile(String path);
}
